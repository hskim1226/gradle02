package com.apexsoft.ysprj.applicants.application.service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.exception.ErrorInfo;
import com.apexsoft.framework.exception.YSBizException;
import com.apexsoft.framework.exception.YSBizNoticeException;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.ysprj.applicants.application.domain.*;
import com.apexsoft.ysprj.applicants.common.util.FileUtil;
import com.apexsoft.ysprj.applicants.payment.service.PaymentService;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by hanmomhanda on 15. 1. 23.
 */
@Service
public class DocumentServiceImpl implements DocumentService {

    private static final Logger logger = LoggerFactory.getLogger(DocumentServiceImpl.class);

    private final static String NAME_SPACE = "com.apexsoft.ysprj.applicants.application.sqlmap.";

    @Autowired
    private CommonDAO commonDAO;

    @Autowired
    private ServletContext context;

    @Autowired
    private PaymentService paymentService;

    @Value("#{app['file.baseDir']}")
    private String BASE_DIR;

    @Resource(name = "messageResolver")
    MessageResolver messageResolver;

    @Value("#{app['s3.url']}")
    private String s3URL;

    @Value("#{app['s3.bucketName']}")
    private String s3BucketName;

    @Value("#{app['s3.midPath']}")
    private String s3MidPath;

    private final String APP_NULL_STATUS = "00000";      // 에러일 때 반환값
    private final String FILE_UPLOAD_SAVED = "00004";    // 첨부파일 저장
    private final String APPLICATION_SUBMITTED = "00010";    // 원서 작성 및 제출 완료

    @Override
    public ExecutionContext retrieveDocument(int applNo) {

        ExecutionContext ec = new ExecutionContext();
        Map<String, Object> ecDataMap = new HashMap<String, Object>();

        Document document = new Document();

        Application applicationFromDB = commonDAO.queryForObject(NAME_SPACE + "ApplicationMapper.selectByPrimaryKey",
                applNo, Application.class);
        document.setApplication(applicationFromDB);

        List<TotalApplicationDocumentContainer> documentContainerList =
                retrieveManatoryApplicatoinlDocListByApplNo(applNo);
        document.setDocumentContainerList(documentContainerList);

        ecDataMap.put("document", document);
        ec.setData(ecDataMap);

        return ec;
    }

    @Override
    public ExecutionContext retrieveDocument(Document document) {

        ExecutionContext ec = new ExecutionContext();
        Map<String, Object> ecDataMap = new HashMap<String, Object>();

        Application applicationFromUser = document.getApplication();

        int applNo = applicationFromUser.getApplNo();

        Application applicationFromDB = commonDAO.queryForObject(NAME_SPACE + "ApplicationMapper.selectByPrimaryKey",
                applNo, Application.class);
        document.setApplication(applicationFromDB);

        List<TotalApplicationDocumentContainer> documentContainerList =
                retrieveManatoryApplicatoinlDocListByApplNo(applNo);
        document.setDocumentContainerList(documentContainerList);

        ecDataMap.put("document", document);
        ec.setData(ecDataMap);

        return ec;
    }

    @Override
    public ExecutionContext saveDocument(Document document) {

        ExecutionContext ec = new ExecutionContext();
        Application application = document.getApplication();
        int applNo = application.getApplNo();
        String userId = application.getUserId();

        int r1 = 0, rSave = 0;

        int currentStsCode = Integer.parseInt(application.getApplStsCode());
        if (currentStsCode <= Integer.parseInt(FILE_UPLOAD_SAVED)) {
            rSave++;
            Date date = new Date();

            application.setModDate(date);
            application.setModId(userId);
//            application.setDocChckYn(application.getDocChckYn());
            application.setApplStsCode(FILE_UPLOAD_SAVED);
            r1 = commonDAO.updateItem(application, NAME_SPACE, "ApplicationMapper");
        }

        int r2 = 0, rSaveEtc = 0;
        ExecutionContext ec2;
        List<TotalApplicationDocument> errorDoc = new ArrayList<TotalApplicationDocument>();
        List<TotalApplicationDocumentContainer> docContainerList = document.getDocumentContainerList();
        for (TotalApplicationDocumentContainer groupList : docContainerList) {
            if ("00009".equals(groupList.getDocTypeCode())) {
                List<TotalApplicationDocumentContainer> aDocList = groupList.getSubContainer();
                for (TotalApplicationDocumentContainer aDoc : aDocList) {
                    if ("Y".equals(aDoc.getLastYn()) && aDoc.isCheckedFg()) {
                        r2++;
                        ec2 = saveOneDocument(aDoc);
                        if (ExecutionContext.SUCCESS.equals(ec2.getResult())) {
                            rSaveEtc++;
                        } else {
                            errorDoc.add(aDoc);
                        }
                    }
                }
            }
        }

        if (r1 == rSave && r2 == rSaveEtc) {
            ec.setResult(ExecutionContext.SUCCESS);
            ec.setMessage(messageResolver.getMessage("U325"));
        } else {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(messageResolver.getMessage("U326"));
            if (r1 != rSave) {
                ec.setData(application);
                ec.setErrCode("ERR0003");
            } else if (r2 != rSaveEtc) {
                ec.setErrCode("ERR0033");
                ec.setData(errorDoc);
            }
            Map<String, String> errorInfo = new HashMap<String, String>();
            errorInfo.put("applNo", String.valueOf(applNo));
            errorInfo.put("userId", userId);
            ec.setErrorInfo(new ErrorInfo(errorInfo));
            throw new YSBizException(ec);
        }
        return ec;
    }

    @Override
    public ExecutionContext submit( Document document ) {

//        DateTime now = new DateTime();
//        DateTimeZone seoul = DateTimeZone.forID("Asia/Seoul");
//        DateTime dueTime = new DateTime(2015, 4, 10, 18, 50, 3, seoul);
//
//        Application tApplication = document.getApplication();
//        String tUserId = tApplication != null ? tApplication.getUserId() : "APPLICATION IS NULL";
//        int tApplNo = tApplication != null ? tApplication.getApplNo() : -1;
//        if (now.isAfter(dueTime)) {
//            logger.error("DUE : " + dueTime);
//            logger.error("NOW : " + now);
//            logger.error("STATUS LATE");
//            logger.error("APPL STATUS CODE : " + tApplication.getApplStsCode());
//            logger.error("userId : [" + tUserId + "], " + "applNo : [" + tApplNo + "]" );
//            ExecutionContext ec = new ExecutionContext(ExecutionContext.FAIL);
//            ec.setMessage(messageResolver.getMessage("U04517"));
//            ec.setErrCode("ERR3011");
//
//            throw new YSBizException(ec);
//        }

        ExecutionContext ec = new ExecutionContext();
        Application application = document.getApplication();

        int r1, applNo = application.getApplNo();

        // 동일한 주민번호로 제출된 원서 존재 여부 확인
        if (!"C".equals(application.getAdmsTypeCode()) && !"D".equals(application.getAdmsTypeCode())) {
            if (isRgstNoDuplicate(applNo)) {
                ec.setResult(ExecutionContext.FAIL);
                ec.setMessage(messageResolver.getMessage("U346"));
                ec.setErrCode("ERR0042");
                Map<String, String> errorInfo = new HashMap<String, String>();
                errorInfo.put("applNo", String.valueOf(applNo));
                errorInfo.put("userId", application.getUserId());
                ec.setErrorInfo(new ErrorInfo(errorInfo));
                throw new YSBizNoticeException(ec);
            }
        }

        Date date = new Date();
        String userId = application.getUserId();
        application.setModDate(date);
        application.setModId(userId);
        application.setDocChckYn("on".equals(application.getDocChckYn())?"Y":"N");
        application.setApplStsCode(APPLICATION_SUBMITTED);

        r1 = commonDAO.updateItem(application, NAME_SPACE, "ApplicationMapper");

        ExecutionContext ecPay = paymentService.saveApplicationPayment(application);

        if (r1 == 1 && ExecutionContext.SUCCESS.equals(ecPay.getResult())) {
            ec.setResult(ExecutionContext.SUCCESS);
            ec.setMessage(messageResolver.getMessage("U327"));
        } else {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(messageResolver.getMessage("U328"));
            if (r1 != 1 ) {
                ec.setData(application);
                ec.setErrCode("ERR0041");
            } else if (ExecutionContext.FAIL.equals(ecPay.getResult())) {
                ec.setData(applNo);
                ec.setErrCode(ec.getErrCode());
            }
            Map<String, String> errorInfo = new HashMap<String, String>();
            errorInfo.put("applNo", String.valueOf(applNo));
            errorInfo.put("userId", userId);
            ec.setErrorInfo(new ErrorInfo(errorInfo));
            throw new YSBizException(ec);
        }
        return ec;
    }

    @Override
    public <T> ExecutionContext retrieveInfoListByParamObj(Object parameter, String mapperNameSqlId, Class<T> clazz) {
        ExecutionContext ec = new ExecutionContext();
        List<T> infoList = commonDAO.queryForList(NAME_SPACE + mapperNameSqlId,
                parameter, clazz);

        ec.setData(infoList);
        return ec;
    }

    @Override
    public ExecutionContext retrieveOneDocument(ApplicationDocumentKey docKey) {

        ExecutionContext ec = new ExecutionContext();
        TotalApplicationDocumentContainer totalDoc =
                commonDAO.queryForObject(NAME_SPACE + "CustomApplicationDocumentMapper.selectOneDocument",
                        docKey, TotalApplicationDocumentContainer.class);

        if ( totalDoc != null ) {
            totalDoc.setFileUploadFg(true);
            ec.setResult(ExecutionContext.SUCCESS);
            ec.setData(totalDoc);
//            ec.setMessage(messageResolver.getMessage("U325"));
        } else {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(messageResolver.getMessage("U337"));
            ec.setErrCode("ERR0035");
            throw new YSBizException(ec);
        }
        return ec;
    }

    @Override
    public ExecutionContext saveOneDocument(TotalApplicationDocument oneDocument ) {


        ExecutionContext ec = new ExecutionContext();
        int rUpdate = 0, rInsert = 0,applNo = oneDocument.getApplNo();
        int update=0, insert =0;

        Date date = new Date();
        String userId = oneDocument.getCreId();

        // applStsCode 수정 - TODO 적용할까말까
//        int applUpdate = 0;
//        ExecutionContext ecRetrieve = retrieveDocument(applNo);
//        Map<String, Object> map = (Map<String, Object>)ecRetrieve.getData();
//        Document document1 = (Document)map.get("document");
//        Application application = document1.getApplication();
//        int currentStsCode = Integer.parseInt(application.getApplStsCode());
//        if (currentStsCode < Integer.parseInt(FILE_UPLOAD_SAVED)) {
//            application.setApplStsCode(FILE_UPLOAD_SAVED);
//            application.setModDate(new Date());
//            applUpdate = commonDAO.updateItem(application, NAME_SPACE, "ApplicationMapper");
//        }

        //기존 파일이 업로드 되어 있는 경우
        if( oneDocument.isFileUploadFg()){
            rUpdate++;
            oneDocument.setModDate(date);
            oneDocument.setModId(userId);
            update = update + commonDAO.updateItem(oneDocument, NAME_SPACE, "ApplicationDocumentMapper");

        }else{
            rInsert++;

            int maxSeq = commonDAO.queryForInt(NAME_SPACE +"CustomApplicationDocumentMapper.selectMaxSeqByApplNo", applNo ) ;
            oneDocument.setFileUploadFg(true);
            oneDocument.setDocSeq(++maxSeq);
            oneDocument.setCreDate(date);
            insert = insert + commonDAO.insertItem(oneDocument, NAME_SPACE, "ApplicationDocumentMapper");

        }

//        if (  insert == rInsert && update == rUpdate && applUpdate == 1 ) {
        if (  insert == rInsert && update == rUpdate ) {
            ec.setResult(ExecutionContext.SUCCESS);
            ec.setMessage(messageResolver.getMessage("U325"));
            ec.setData(oneDocument);
        } else {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(messageResolver.getMessage("U326"));
            ec.setData(new ApplicationIdentifier(applNo, APP_NULL_STATUS));
            String errCode = null;
            if ( insert != rInsert ) errCode = "ERR0031";
            if ( update != rUpdate ) errCode = "ERR0033";
            ec.setErrCode(errCode);
            Map<String, String> errorInfo = new HashMap<String, String>();
            errorInfo.put("applNo", String.valueOf(applNo));
            errorInfo.put("userId", userId);
            errorInfo.put("docSeq", String.valueOf(oneDocument.getDocSeq()));
            errorInfo.put("docItemCode", oneDocument.getDocItemCode());
            errorInfo.put("docItemName", oneDocument.getDocItemName());
            ec.setErrorInfo(new ErrorInfo(errorInfo));
            throw new YSBizException(ec);
        }
        return ec;
    }


    @Override
    public ExecutionContext deleteOneDocument(TotalApplicationDocument oneDocument ) {

        ExecutionContext ec = new ExecutionContext();
        int rDelete = 0;
        int delete=0;
        boolean deleteOk = false;
        int applNo = oneDocument.getApplNo();
        int docSeq = oneDocument.getDocSeq();


        //기존 파일이 업로드 되어 있는 경우 DB에서 정보 지우는 것이 오류없이 성공하면 S3의 파일을 지운다.
        if( oneDocument.isFileUploadFg()){
            rDelete++;
            delete = commonDAO.delete( NAME_SPACE + "ApplicationDocumentMapper.deleteByPrimaryKey", oneDocument);

            try {
                AmazonS3 s3 = new AmazonS3Client();
                s3.deleteObject(s3BucketName, oneDocument.getFilePath());
                deleteOk = true;
            } catch (AmazonServiceException ase) {
                deleteOk = false;
                ec = new ExecutionContext(ExecutionContext.FAIL);
                ec.setMessage(messageResolver.getMessage("U338"));
                ec.setErrCode("ERR0051");
                Map<String, String> errorInfo = new HashMap<String, String>();
                errorInfo.put("applNo", String.valueOf(oneDocument.getApplNo()));
                errorInfo.put("docSeq", String.valueOf(oneDocument.getDocSeq()));
                errorInfo.put("AWS Error Message", ase.getMessage());
                errorInfo.put("AWS HTTP Status Code", String.valueOf(ase.getStatusCode()));
                errorInfo.put("AWS HTTP Error Code", String.valueOf(ase.getErrorCode()));
                errorInfo.put("AWS Error Type", ase.getErrorType().toString());
                errorInfo.put("AWS Request ID", ase.getRequestId());
                ec.setErrorInfo(new ErrorInfo(errorInfo));
                throw new YSBizException(ec);
            } catch (AmazonClientException ace) {
                deleteOk = false;
                ec = new ExecutionContext(ExecutionContext.FAIL);
                ec.setMessage(messageResolver.getMessage("U338"));
                ec.setErrCode("ERR0051");
                Map<String, String> errorInfo = new HashMap<String, String>();
                errorInfo.put("applNo", String.valueOf(oneDocument.getApplNo()));
                errorInfo.put("docSeq", String.valueOf(oneDocument.getDocSeq()));
                errorInfo.put("AWS Error Message", ace.getMessage());
                ec.setErrorInfo(new ErrorInfo(errorInfo));
                throw new YSBizException(ec);
            }

//            File file = new File(oneDocument.getFilePath(), oneDocument.getFileName());
//            deleteOk = file.delete();
        }

        if (  delete == rDelete && deleteOk ) {
            ec.setResult(ExecutionContext.SUCCESS);
            ec.setMessage(messageResolver.getMessage("U340"));
        } else {
            if ( delete != rDelete ) {
                ec.setResult(ExecutionContext.FAIL);
                ec.setMessage(messageResolver.getMessage("U338"));
                ec.setErrCode("ERR0034");
            } else if (!deleteOk) {
                ec.setResult(ExecutionContext.FAIL);
                ec.setMessage(messageResolver.getMessage("U338"));
                ec.setErrCode("ERR0051");
            }
            Map<String, String> errorInfo = new HashMap<String, String>();
            errorInfo.put("applNo", String.valueOf(applNo));
            errorInfo.put("userId", oneDocument.getCreId());
            errorInfo.put("docSeq", String.valueOf(oneDocument.getDocSeq()));
            errorInfo.put("docItemCode", oneDocument.getDocItemCode());
            errorInfo.put("docItemName", oneDocument.getDocItemName());
            ec.setErrorInfo(new ErrorInfo(errorInfo));
            throw new YSBizException(ec);
        }
        return ec;

    }

    @Override
    public ExecutionContext saveApplicationPaperInfo(Application application) {
        ExecutionContext ec = new ExecutionContext();
        String userId = application.getUserId();
        String admsNo = application.getAdmsNo();
        int applNo = application.getApplNo();

        TotalApplicationDocument aDoc = new TotalApplicationDocument();
        aDoc.setApplNo(applNo);
        aDoc.setFileExt("pdf");
        aDoc.setImgYn("N");
        aDoc.setFilePath(FileUtil.getUploadDirectoryFullPath(BASE_DIR, s3MidPath, admsNo, userId, applNo));
        aDoc.setDocItemName("지원서");
        aDoc.setFileName(FileUtil.getApplicationFileName(userId));
        aDoc.setOrgFileName(FileUtil.getApplicationFileName(userId));
        aDoc.setPageCnt(2);
        // 지원서 정보가 이미 저장 되어 있으면(즉 원서 작성 단계에서 미리보기를 했으면) true
        List<ApplicationDocument> applPaperInfosList = retrieveApplicationPaperInfo(applNo);
        if (applPaperInfosList.size() == 0)
            aDoc.setFileUploadFg(false);
        else if (applPaperInfosList.size() == 1) {
            aDoc.setFileUploadFg(true);
            aDoc.setDocSeq(applPaperInfosList.get(0).getDocSeq());
        } else {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(messageResolver.getMessage("U344"));
            ec.setErrCode("ERR0033");

            Map<String, String> errorInfo = new HashMap<String, String>();
            errorInfo.put("applNo", String.valueOf(applNo));
            errorInfo.put("userId", userId);
            errorInfo.put("docItemName", "지원서");
            ec.setErrorInfo(new ErrorInfo(errorInfo));
            throw new YSBizException(ec);
        }

        ec = saveOneDocument(aDoc);

        return ec;
    }

    @Override
    public List<ApplicationDocument> retrieveApplicationPaperInfo(int applNo) {

        List<ApplicationDocument> applDocList = commonDAO.queryForList(NAME_SPACE + "CustomApplicationDocumentMapper.selectApplPaperInfoByApplNo", applNo, ApplicationDocument.class);
        return applDocList;
    }

    /**
     * 결제 후 수험번호가 표시된 수험표 저장 정보를 DB에 저장
     *
     * @param application
     * @return
     */
    @Override
    public ExecutionContext saveAdmissionSlipPaperInfo(Application application) {
        String userId = application.getUserId();
        String admsNo = application.getAdmsNo();
        int applNo = application.getApplNo();

        TotalApplicationDocument aDoc = new TotalApplicationDocument();
        aDoc.setApplNo(applNo);
        aDoc.setFileExt("pdf");
        aDoc.setImgYn("N");
        aDoc.setFilePath(FileUtil.getUploadDirectoryFullPath(BASE_DIR, s3MidPath, admsNo, userId, applNo));
        aDoc.setDocItemName("수험표");
        aDoc.setDocItemNameXxen("Application Slip");
        aDoc.setFileName(FileUtil.getSlipFileName(userId));
        aDoc.setOrgFileName(FileUtil.getSlipFileName(userId));
        aDoc.setPageCnt(1);
        aDoc.setFileUploadFg(false);

        ExecutionContext ec =saveOneDocument(aDoc);

        return ec;
    }

    private List<TotalApplicationDocumentContainer> retrieveManatoryApplicatoinlDocListByApplNo(int applNo) {

        List<TotalApplicationDocumentContainer> applContList = new ArrayList<TotalApplicationDocumentContainer>();

        Application tempApp = commonDAO.queryForObject(NAME_SPACE + "ApplicationMapper.selectByPrimaryKey", applNo, Application.class);
        String admsNo = tempApp.getAdmsNo();
        //학과별정보 조회
        applContList.add(retrieveDeptDocumentByApplNo(applNo ));

        //학력정보 조회
        applContList.add(retrieveAcademyDocumentByApplNo(applNo, admsNo));

        //어학정보 조회
        applContList.add(retrieveLanguageDocumentByApplNo(applNo));

        //코드별 조건 조회
        applContList.addAll(retrieveCodeDocumentByApplNo(applNo, admsNo));

        return applContList;
    }


    //학과별 문서요건 정보 조회
    private TotalApplicationDocumentContainer retrieveDeptDocumentByApplNo( int applNo) {
        TotalApplicationDocumentContainer rApplDoc = null;
        List<TotalApplicationDocumentContainer> rList = new ArrayList<TotalApplicationDocumentContainer>();
        List<TotalApplicationDocumentContainer> applDocList;
        applDocList = commonDAO.queryForList(NAME_SPACE + "CustomApplicationDocumentMapper.selectBasicTotalDocListByApplNo", applNo, TotalApplicationDocumentContainer.class);
        if( applDocList != null){
            for ( TotalApplicationDocumentContainer aCont : applDocList){
                aCont.setSubContainer(getSubDeptDocumentContainer(aCont, rList));
            }
        }
        rApplDoc = new TotalApplicationDocumentContainer();
        rApplDoc.setSubContainer(applDocList);
        rApplDoc.setGrpLabel("L04301");
        rApplDoc.setDisplayGrpFg(true);

        return rApplDoc;
    }

    //점수가 입력된 어학문서 정보 조회
    private  TotalApplicationDocumentContainer retrieveLanguageDocumentByApplNo( int applNo) {
        TotalApplicationDocumentContainer rApplDoc = null;
        List<TotalApplicationDocumentContainer> rList = new ArrayList<TotalApplicationDocumentContainer>();
        List<TotalApplicationDocumentContainer> applDocList;


        applDocList = commonDAO.queryForList(NAME_SPACE + "CustomApplicationDocumentMapper.selectLanguageTotalDocListByApplNo", applNo, TotalApplicationDocumentContainer.class);
        if( applDocList != null){

            for( int idx = applDocList.size()-1; idx > -1; idx--){
                TotalApplicationDocumentContainer aCont = applDocList.get(idx);
                if( "EXMP_TYPE".equals(aCont.getDocItemGrp())){
                    applDocList.remove(idx);
                }else {
                    rList.add(aCont);
                    aCont.setDocItemName(aCont.getDocItemName() + " 성적표(증명)");
                    aCont.setDocItemNameXxen(aCont.getDocItemNameXxen() + " Test Results");
                    aCont.setDocItemCode("00016");
                    if (aCont.getDocSeq() != null && aCont.getDocSeq() > 0) {
                        aCont.setFileUploadFg(true);
                    }
                    aCont.setCheckedFg(true);
                }
            }
        }
        rApplDoc = new TotalApplicationDocumentContainer();
        rApplDoc.setSubContainer(applDocList);
        rApplDoc.setGrpLabel("L04307");
        rApplDoc.setDisplayGrpFg(true);

        return rApplDoc;
    }

    //입력된 학력정보별 문서요건 정보 조회
    private  TotalApplicationDocumentContainer retrieveAcademyDocumentByApplNo( int applNo, String admsNo ) {
        TotalApplicationDocumentContainer rApplDoc = null;
        List<TotalApplicationDocumentContainer> rList = new ArrayList<TotalApplicationDocumentContainer>();
        List<TotalApplicationDocumentContainer> subAcadList = new ArrayList<TotalApplicationDocumentContainer>();
        TotalApplicationDocumentContainer aCont = null;
        ParamForAcademy param = new ParamForAcademy();


        rApplDoc = new TotalApplicationDocumentContainer();
        rApplDoc.setSubContainer(new ArrayList<TotalApplicationDocumentContainer>());
        rApplDoc.setGrpLabel("L04302");
        rApplDoc.setDisplayGrpFg(true);

        param.setApplNo(applNo);
        param.setAcadTypeCode("00002");//대학-ACAD_TYPE_CODE
        List<CustomApplicationAcademy>acadList;
        acadList = commonDAO.queryForList(NAME_SPACE + "CustomApplicationAcademyMapper.selectByApplNoAcadTypeCode", param, CustomApplicationAcademy.class);

        //대학 갯수만큼 켄네이너 생성
        for(CustomApplicationAcademy aAcad : acadList){
            aCont = new TotalApplicationDocumentContainer();
            aCont.setApplNo( aAcad.getApplNo());
            aCont.setGrpLabel(aAcad.getSchlName());
            aCont.setGrpLabelXxen( aAcad.getSchlName());
            rList.add(aCont);


            subAcadList.add(aCont);

            //학위별 필수서류 셋팅
            ParamForCodeDocument codeParam = new ParamForCodeDocument();
            codeParam.setApplNo(applNo);
            codeParam.setAdmsNo(admsNo);
            codeParam.setAdmsCodeGrp("ACAD_TYPE");
            codeParam.setAdmsCode(aAcad.getAcadTypeCode());
            codeParam.setGrpLevel(1);
            codeParam.setItemCode("00003");//대학

            List<TotalApplicationDocumentContainer> subDocList;
            subDocList = commonDAO.queryForList(NAME_SPACE +"CustomApplicationDocumentMapper.selectCodeMandatoryGroupByCode",codeParam,TotalApplicationDocumentContainer.class);

            //해외학위 필수서류 셋팅
            codeParam.setAdmsCodeGrp("SCHL_CNTR");
            codeParam.setAdmsCode(aAcad.getSchlCntrCode());
            codeParam.setItemCode("00002");//해외학위
            subDocList.addAll(commonDAO.queryForList(NAME_SPACE +"CustomApplicationDocumentMapper.selectCodeMandatoryGroupByCode",codeParam,TotalApplicationDocumentContainer.class));
            codeParam.setItemCode("00021");//중국학위
            subDocList.addAll(commonDAO.queryForList(NAME_SPACE +"CustomApplicationDocumentMapper.selectCodeMandatoryGroupByCode",codeParam,TotalApplicationDocumentContainer.class));

            for( TotalApplicationDocumentContainer aSubDoc : subDocList ){
                //저장 시퀀스, 모집전형 기입
                aSubDoc.setDocGrp(aAcad.getAcadSeq());
                aSubDoc.setApplNo(applNo);
                aSubDoc.setAdmsNo(admsNo);
                if( aSubDoc.getMsgNo()!= null && aSubDoc.getMsgNo()!= "" ) {
                    aSubDoc.setMsg(messageResolver.getMessage(aSubDoc.getMsgNo()));
                }
                aSubDoc.setSubContainer( getSubCodeDocumentContainer(aSubDoc,rList));
            }
            adjustAcademyDocByGrdaType(aAcad.getGrdaTypeCode(),subDocList);
            aCont.setSubContainer(subDocList);


            //전체 학력 컨테이너에 추가
            rApplDoc.getSubContainer().add(aCont);

        }


        //대학원 갯수만큼 켄네이너 생성
        param.setAcadTypeCode("00003");//대학원-ACAD_TYPE_CODE
        acadList =commonDAO.queryForList(NAME_SPACE + "CustomApplicationAcademyMapper.selectByApplNoAcadTypeCode",param,CustomApplicationAcademy.class);

        for(CustomApplicationAcademy aAcad : acadList){

            aCont = new TotalApplicationDocumentContainer();
            aCont.setApplNo( aAcad.getApplNo());
            aCont.setGrpLabel( aAcad.getSchlName() );
            aCont.setGrpLabelXxen( aAcad.getSchlName());
            rList.add(aCont);

            //학위별 필수서류 셋팅
            ParamForCodeDocument codeParam = new ParamForCodeDocument();
            codeParam.setApplNo(applNo);
            codeParam.setAdmsNo(admsNo);
            codeParam.setAdmsCodeGrp( "ACAD_TYPE");
            codeParam.setAdmsCode(aAcad.getAcadTypeCode());
            codeParam.setGrpLevel(1);
            codeParam.setItemCode("00004");//대학원

            List<TotalApplicationDocumentContainer> subDocList;
            subDocList = commonDAO.queryForList(NAME_SPACE + "CustomApplicationDocumentMapper.selectCodeMandatoryGroupByCode",codeParam,TotalApplicationDocumentContainer.class);

            //해외학위 필수서류 셋팅
            codeParam.setAdmsCodeGrp("SCHL_CNTR");
            codeParam.setAdmsCode(aAcad.getSchlCntrCode());
            codeParam.setItemCode("00002");//해외학위
            subDocList.addAll(commonDAO.queryForList(NAME_SPACE + "CustomApplicationDocumentMapper.selectCodeMandatoryGroupByCode", codeParam, TotalApplicationDocumentContainer.class));
            codeParam.setItemCode("00021");//중국학위
            subDocList.addAll(commonDAO.queryForList(NAME_SPACE +"CustomApplicationDocumentMapper.selectCodeMandatoryGroupByCode",codeParam,TotalApplicationDocumentContainer.class));

            for( TotalApplicationDocumentContainer aSubDoc : subDocList ){
                aSubDoc.setDocGrp(aAcad.getAcadSeq());
                aSubDoc.setApplNo(applNo);
                aSubDoc.setAdmsNo(admsNo);
                if( aSubDoc.getMsgNo()!= null && aSubDoc.getMsgNo()!= "" ) {
                    aSubDoc.setMsg(messageResolver.getMessage(aSubDoc.getMsgNo()));
                }
                aSubDoc.setSubContainer( getSubCodeDocumentContainer(aSubDoc,rList));
            }
            adjustAcademyDocByGrdaType(aAcad.getGrdaTypeCode(),subDocList);
            aCont.setSubContainer(subDocList);
            //전체 학력 컨테이너에 추가
            rApplDoc.getSubContainer().add(aCont);

        }

        return rApplDoc;
    }

    //지원특성별 문서요건 정보 조회 - from mad_code 테이블
    private  List<TotalApplicationDocumentContainer> retrieveCodeDocumentByApplNo( int applNo, String admsNo  ) {
        List<TotalApplicationDocumentContainer> rContList = new ArrayList<TotalApplicationDocumentContainer>() ;
        TotalApplicationDocumentContainer aCont = null;
        List<TotalApplicationDocumentContainer> rList;

        Application tempApp = commonDAO.queryForObject(NAME_SPACE + "ApplicationMapper.selectByPrimaryKey", applNo, Application.class);
        ParamForCodeDocument codeParam = new ParamForCodeDocument();
        codeParam.setApplNo(applNo);
        codeParam.setAdmsNo(admsNo);

        //학연산 , 새터민 조회
        if( "00002".equals(tempApp.getApplAttrCode())||"00004".equals(tempApp.getApplAttrCode())) {
            rList = new ArrayList<TotalApplicationDocumentContainer>();
            codeParam.setAdmsCodeGrp("APPL_ATTR");
            codeParam.setAdmsCode(tempApp.getApplAttrCode());
            codeParam.setGrpLevel(1);
            codeParam.setItemCode("00006");//학연산, 새터민

            List<TotalApplicationDocumentContainer> subDocList;
            subDocList = commonDAO.queryForList(NAME_SPACE + "CustomApplicationDocumentMapper.selectCodeMandatoryGroupByCode", codeParam, TotalApplicationDocumentContainer.class);
            for (TotalApplicationDocumentContainer aSubDoc : subDocList) {
                aSubDoc.setApplNo(applNo);
                rList.add(aSubDoc);
                aSubDoc.setAdmsNo(admsNo);
                if( aSubDoc.getMsgNo()!= null && aSubDoc.getMsgNo()!= "" ) {
                    aSubDoc.setMsg(messageResolver.getMessage(aSubDoc.getMsgNo()));
                }
                aSubDoc.setSubContainer(getSubCodeDocumentContainer(aSubDoc,rList));
            }
            aCont = new TotalApplicationDocumentContainer();
            aCont.setGrpLabel("L04305");
            aCont.setDisplayGrpFg(true);
            aCont.setSubContainer(subDocList);
            rContList.add(aCont);
        }



        //외국인 조회
        if(  "00001".equals(tempApp.getFornTypeCode())||"00002".equals(tempApp.getFornTypeCode())||"00003".equals(tempApp.getFornTypeCode()) ) {
            rList = new ArrayList<TotalApplicationDocumentContainer>();
            codeParam.setAdmsCodeGrp("FORN_TYPE");
            codeParam.setAdmsCode(tempApp.getFornTypeCode());
            codeParam.setGrpLevel(1);
            codeParam.setItemCode("00007");

            List<TotalApplicationDocumentContainer> subDocList;
            subDocList = commonDAO.queryForList(NAME_SPACE + "CustomApplicationDocumentMapper.selectCodeMandatoryGroupByCode", codeParam, TotalApplicationDocumentContainer.class);
            for (TotalApplicationDocumentContainer aSubDoc : subDocList) {
                aSubDoc.setApplNo(applNo);
                rList.add(aSubDoc);
                aSubDoc.setAdmsNo(admsNo);
                if( aSubDoc.getMsgNo()!= null && aSubDoc.getMsgNo()!= "" ) {
                    aSubDoc.setMsg(messageResolver.getMessage(aSubDoc.getMsgNo()));
                }
                aSubDoc.setSubContainer(getSubCodeDocumentContainer(aSubDoc,rList));
            }
            aCont = new TotalApplicationDocumentContainer();
            aCont.setGrpLabel("L04306");
            aCont.setDisplayGrpFg(true);
            aCont.setSubContainer(subDocList);
            rContList.add(aCont);
        }

        //기타 및 자유입력 조회

        codeParam.setGrpLevel(1);
        codeParam.setItemTypeCode("00009");// 기타 및 추가제출 Doc 테이블의 Item Type 으로 바로 조회하는 용도, MDT에서 가져오는 용도가 아님
        List<TotalApplicationDocumentContainer> subDocList;
        subDocList = commonDAO.queryForList(NAME_SPACE + "CustomApplicationDocumentMapper.selectMandatoryDocumentByDocType", codeParam, TotalApplicationDocumentContainer.class);
        aCont = new TotalApplicationDocumentContainer();
        for (TotalApplicationDocumentContainer aSubDoc : subDocList) {
            aSubDoc.setApplNo(applNo);
            aSubDoc.setAdmsNo(admsNo);
            aSubDoc.setLastYn("Y");
            aSubDoc.setCheckedFg(true);
            aSubDoc.setFileUploadFg(true);
            aSubDoc.setUploadYn("Y");
            if( aSubDoc.getMsgNo()!= null && aSubDoc.getMsgNo()!= "" ) {
                aSubDoc.setMsg(messageResolver.getMessage(aSubDoc.getMsgNo()));
            }
        }
        aCont.setGrpLabel("L04308");
        aCont.setDocTypeCode(codeParam.getItemTypeCode());
        aCont.setDisplayGrpFg(true);
        aCont.setSubContainer(subDocList);
        rContList.add(aCont);

        return rContList;
    }

    private  List<TotalApplicationDocumentContainer> getSubDeptDocumentContainer( TotalApplicationDocumentContainer pCont){
        List<TotalApplicationDocumentContainer> rContList = null;

        if (!"Y".equals( pCont.getLastYn())) {
            rContList = commonDAO.queryForList(NAME_SPACE + "CustomApplicationDocumentMapper.selectTotalApplicationDocumentList", pCont, TotalApplicationDocumentContainer.class);
            if (rContList != null) {
                for (TotalApplicationDocumentContainer aCont : rContList) {
                    aCont.setSubContainer(getSubDeptDocumentContainer(aCont));

                }
            }
            pCont.setGrpLabel( pCont.getDocItemName());
            pCont.setGrpLabelXxen( pCont.getDocItemNameXxen());
        }
        return rContList;
    }

    private  List<TotalApplicationDocumentContainer> getSubCodeDocumentContainer( TotalApplicationDocumentContainer pCont){
        List<TotalApplicationDocumentContainer> rContList = null;

        if (!"Y".equals( pCont.getLastYn())) {
            rContList = commonDAO.queryForList(NAME_SPACE + "CustomApplicationDocumentMapper.selectTotalCodeApplicationDocumentList", pCont, TotalApplicationDocumentContainer.class);
            if (rContList != null) {
                for (TotalApplicationDocumentContainer aCont : rContList) {
                    aCont.setSubContainer(getSubCodeDocumentContainer(aCont));

                }
            }
            pCont.setGrpLabel(pCont.getDocItemName());
            pCont.setGrpLabelXxen( pCont.getDocItemNameXxen());
        }else{
            ApplicationDocument aDoc;

            aDoc = commonDAO.queryForObject(NAME_SPACE + "CustomApplicationDocumentMapper.selectCodeApplicationDocumentByTotalDocumentContainner", pCont, ApplicationDocument.class);
            if( aDoc != null){
                pCont.setDocSeq( aDoc.getDocSeq());
                pCont.setDocName( aDoc.getDocName());
                pCont.setFileExt( aDoc.getFileExt());
                pCont.setImgYn( aDoc.getImgYn());
                pCont.setFilePath( aDoc.getFilePath());
                pCont.setFileName(aDoc.getFileName());
                pCont.setOrgFileName(aDoc.getOrgFileName());
                pCont.setPageCnt(aDoc.getPageCnt());
                pCont.setDocItemNameXxen( aDoc.getDocItemNameXxen());
                pCont.setDocGrpName( aDoc.getDocGrpName());
                pCont.setFileUploadFg(true);
            }
        }
        return rContList;
    }
    private  List<TotalApplicationDocumentContainer> getSubDeptDocumentContainer( TotalApplicationDocumentContainer pCont, List<TotalApplicationDocumentContainer> pList){
        List<TotalApplicationDocumentContainer> rContList = null;

        if (!"Y".equals( pCont.getLastYn())) {
            pCont.setGrpLabel(pCont.getDocItemName());
            pCont.setGrpLabelXxen(pCont.getDocItemNameXxen());
            if( pCont.getMsgNo()!= null && pCont.getMsgNo()!= "" ) {
                pCont.setMsg(messageResolver.getMessage(pCont.getMsgNo()));
            }
            pList.add(pCont);
            rContList = commonDAO.queryForList(NAME_SPACE + "CustomApplicationDocumentMapper.selectTotalApplicationDocumentList", pCont, TotalApplicationDocumentContainer.class);
            if (rContList != null) {
                for (TotalApplicationDocumentContainer aCont : rContList) {
                    aCont.setSubContainer(getSubDeptDocumentContainer(aCont, pList));

                }
            }

        }else{
            //pCont에 이미 APPL_DOC 정보를 join  해서 가져옴, 플래그 처리만 함
            if( pCont.getDocSeq() !=null &&pCont.getDocSeq() > 0) {
                pCont.setFileUploadFg(true);
            }
            if( "DOC_ITEM".equals(pCont.getDocItemGrp()) && "00001".equals(pCont.getDocItemCode())){
                pCont.setImgYn("Y");
            }
//            if( pCont.getMsgNo()!= null && pCont.getMsgNo()!= "" ) { // 4/8에러 발생
            if( pCont.getMsgNo()!= null && "".equals(pCont.getMsgNo())) { // 4/8에러 고침
                pCont.setMsg(messageResolver.getMessage(pCont.getMsgNo()));
            }
            pList.add(pCont);
        }
        return rContList;
    }

    private  List<TotalApplicationDocumentContainer> getSubCodeDocumentContainer( TotalApplicationDocumentContainer pCont,List<TotalApplicationDocumentContainer> pList){
        List<TotalApplicationDocumentContainer> rContList = null;

        if (!"Y".equals( pCont.getLastYn())) {
            pCont.setGrpLabel( pCont.getDocItemName());
            pCont.setGrpLabelXxen( pCont.getDocItemNameXxen());
            pList.add(pCont);
            if( pCont.getMsgNo()!= null && pCont.getMsgNo()!= "" ) {
                pCont.setMsg(messageResolver.getMessage(pCont.getMsgNo()));
            }
            rContList = commonDAO.queryForList(NAME_SPACE + "CustomApplicationDocumentMapper.selectTotalCodeApplicationDocumentList", pCont, TotalApplicationDocumentContainer.class);
            if (rContList != null) {
                for (TotalApplicationDocumentContainer aCont : rContList) {
                    aCont.setDocGrp(pCont.getDocGrp());
                    aCont.setApplNo(pCont.getApplNo());
                    aCont.setSubContainer(getSubCodeDocumentContainer(aCont,pList));
                }
            }

        }else{
            //pCont에는  APPL_DOC 정보가 없이 필요한 문서정보와 저장 seq 만 있음
            //APPL_DOC에서 해당 문서가 저장되었는지 조회해야함
            ApplicationDocument aDoc;
            aDoc = commonDAO.queryForObject(NAME_SPACE + "CustomApplicationDocumentMapper.selectCodeApplicationDocumentByTotalDocumentContainner", pCont, ApplicationDocument.class);
            if( aDoc != null){
                pCont.setDocSeq( aDoc.getDocSeq());
                pCont.setDocName(aDoc.getDocName());
                pCont.setFileExt(aDoc.getFileExt());
                pCont.setImgYn(aDoc.getImgYn());
                pCont.setFilePath(aDoc.getFilePath());
                pCont.setFileName(aDoc.getFileName());
                pCont.setOrgFileName(aDoc.getOrgFileName());
                pCont.setPageCnt(aDoc.getPageCnt());
                pCont.setDocItemNameXxen(aDoc.getDocItemNameXxen());
                pCont.setDocGrpName(aDoc.getDocGrpName());
                pCont.setFileUploadFg(true);
                System.out.println("");
            }
            if( pCont.getMsgNo()!= null && !"".equals(pCont.getMsgNo()) ) {
                pCont.setMsg(messageResolver.getMessage(pCont.getMsgNo()));
            }
            pList.add(pCont);
        }

        return rContList;
    }

    @Override
    public String retrievePhotoUri(int applNo) {
        ParamForDocumentType aParam = new ParamForDocumentType();
        String urlEncodedPhotoURL = null;
        aParam.setApplNo( applNo);
        aParam.setDocTypeCode("00001");//기본
        aParam.setDocItemCode("00001");//사진
        List<TotalApplicationDocument> rList = null;

        rList = commonDAO.queryForList(NAME_SPACE + "CustomApplicationDocumentMapper.selectApplicationDocumentByDocumentType", aParam, TotalApplicationDocument.class );
        if( rList != null && rList.size()>0 ) {
            try {
                urlEncodedPhotoURL =  new StringBuilder()
                        .append(s3URL).append('/')
                        .append(s3BucketName).append('/')
                        .append(URLEncoder.encode(rList.get(0).getFilePath(), "UTF-8"))
                        .toString();
            } catch (UnsupportedEncodingException e) {

            }
        }

        return urlEncodedPhotoURL;
    }

    private boolean isRgstNoDuplicate(int applNo) {
        boolean isDup = false;
        Application applFromDB = commonDAO.queryForObject(NAME_SPACE + "ApplicationMapper.selectByPrimaryKey", applNo, Application.class);
        String rgstBornDate = applFromDB.getRgstBornDate();
        if (rgstBornDate.length() == 6) {
            List<String> submittedRgstHashList = commonDAO.queryForList(NAME_SPACE + "CustomApplicationMapper.selectSubmittedApplNoHashes", String.class );
            Set<String> submittedRgstHashSet = new HashSet<String>(submittedRgstHashList);
            String thisRgstHash = applFromDB.getRgstHash();
            isDup = submittedRgstHashSet.contains(thisRgstHash);
        }

        return isDup;
    }

    private String getDecryptedString(String encrypted) throws IOException {
        Properties prop = new Properties();
        InputStream is = context.getResourceAsStream("WEB-INF/grad-ks");
        String decrypted = null;

        try {
            prop.load(is);
            TextEncryptor textEncryptor = Encryptors.queryableText(prop.getProperty("ENC_PSWD"), prop.getProperty("ENC_SALT"));
            decrypted = textEncryptor.decrypt(encrypted);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                throw e;
            }
        }
        return decrypted;
    }

    private void adjustAcademyDocByGrdaType(String grdaType, List<TotalApplicationDocumentContainer> subDocList){

        for( int i = subDocList.size()-1; i>=0; i--){
            TotalApplicationDocumentContainer aCont = subDocList.get(i);
            if("Y".equals(aCont.getLastYn())){ //마지막 컨테이너(문서)인 경우
                if(! "00001".equals(grdaType)) { //졸업이 아니면
                    if( "00008".equals(subDocList.get(i).getDocItemCode()) || "00012".equals(subDocList.get(i).getDocItemCode())) {//졸증 삭제
                        subDocList.remove(i);
                        continue;
                    }
                }
                if(! "00002".equals(grdaType)) { //졸업예정이
                    if("00047".equals(subDocList.get(i).getDocItemCode()) || "00049".equals(subDocList.get(i).getDocItemCode())) {//졸예증 삭제
                        subDocList.remove(i);
                        continue;
                    }
                }
                if(! "00005".equals(grdaType)) { //재학이 아니면
                    if("00046".equals(subDocList.get(i).getDocItemCode()) || "00048".equals(subDocList.get(i).getDocItemCode())) {//재학증 삭제
                        subDocList.remove(i);
                        continue;
                    }
                }
            }else {
                adjustAcademyDocByGrdaType( grdaType, aCont.getSubContainer());
            }
        }
    }
}

