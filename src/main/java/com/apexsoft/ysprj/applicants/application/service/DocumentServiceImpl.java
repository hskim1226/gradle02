package com.apexsoft.ysprj.applicants.application.service;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.exception.ErrorInfo;
import com.apexsoft.framework.exception.YSBizException;
import com.apexsoft.framework.exception.YSNoRedirectBizException;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.ysprj.applicants.application.domain.*;
import com.apexsoft.ysprj.applicants.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.*;

/**
 * Created by hanmomhanda on 15. 1. 23.
 */
@Service
public class DocumentServiceImpl implements DocumentService {

    private final static String NAME_SPACE = "com.apexsoft.ysprj.applicants.application.sqlmap.";

    @Autowired
    private CommonDAO commonDAO;

    @Autowired
    private PaymentService paymentService;

    @Resource(name = "messageResolver")
    MessageResolver messageResolver;

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
        if (currentStsCode < Integer.parseInt(FILE_UPLOAD_SAVED)) {
            rSave++;
            Date date = new Date();

            application.setModDate(date);
            application.setModId(userId);
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
        ExecutionContext ec = new ExecutionContext();
        Application application = document.getApplication();

        int r1, applNo = application.getApplNo();
        Date date = new Date();
        String userId = application.getUserId();
        application.setModDate(date);
        application.setModId(userId);
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
            oneDocument.setCreId("" );
            oneDocument.setModDate(date );
            oneDocument.setModId(userId );
            update = update + commonDAO.updateItem(oneDocument, NAME_SPACE, "ApplicationDocumentMapper");

        }else{
            rInsert++;

            int maxSeq = commonDAO.queryForInt(NAME_SPACE +"CustomApplicationDocumentMapper.selectMaxSeqByApplNo", applNo ) ;
            oneDocument.setFileUploadFg(true);
            oneDocument.setDocSeq(++maxSeq);
            oneDocument.setCreDate(date );
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
        boolean deleteOk = true;
        int applNo = oneDocument.getApplNo();
        int docSeq = oneDocument.getDocSeq();


        //기존 파일이 업로드 되어 있는 경우
        if( oneDocument.isFileUploadFg()){
            rDelete++;
            delete = commonDAO.delete( NAME_SPACE + "ApplicationDocumentMapper.deleteByPrimaryKey", oneDocument);
            File file = new File(oneDocument.getFilePath(), oneDocument.getFileName());
            deleteOk = file.delete();
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
        rApplDoc.setGrpLabel("기본-학과지정 제출서류");
        rApplDoc.setDisplayGrpFg(true);

        return rApplDoc;
    }

    //점수가 입력된 어학문서 정보 조회
    private  TotalApplicationDocumentContainer retrieveLanguageDocumentByApplNo( int applNo) {
        TotalApplicationDocumentContainer rApplDoc = null;
        List<TotalApplicationDocumentContainer> rList = new ArrayList<TotalApplicationDocumentContainer>();
        List<TotalApplicationDocumentContainer> applDocList;

        int i =1;
        applDocList = commonDAO.queryForList(NAME_SPACE + "CustomApplicationDocumentMapper.selectLanguageTotalDocListByApplNo", applNo, TotalApplicationDocumentContainer.class);
        if( applDocList != null){
            for ( TotalApplicationDocumentContainer aCont : applDocList){
                rList.add(aCont);
                aCont.setDocItemName(aCont.getDocItemName()+" 성적표(증명)");
                aCont.setDocItemCode("00016");
                if(aCont.getDocSeq()!=null && aCont.getDocSeq()>0){
                    aCont.setFileUploadFg(true);
                }
                aCont.setCheckedFg(true);
            }
        }
        rApplDoc = new TotalApplicationDocumentContainer();
        rApplDoc.setSubContainer(applDocList);
        rApplDoc.setGrpLabel("어학 관련서류");
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
        rApplDoc.setGrpLabel("대학 관련서류");
        rApplDoc.setDisplayGrpFg(true);

        param.setApplNo(applNo);
        param.setAcadTypeCode("00002");//대학-ACAD_TYPE_CODE
        List<CustomApplicationAcademy>acadList;
        acadList = commonDAO.queryForList(NAME_SPACE + "CustomApplicationAcademyMapper.selectByApplNoAcadTypeCode", param, CustomApplicationAcademy.class);

        //대학 갯수만큼 켄네이너 생성
        for(CustomApplicationAcademy aAcad : acadList){
            aCont = new TotalApplicationDocumentContainer();
            aCont.setApplNo( aAcad.getApplNo());
            aCont.setGrpLabel(aAcad.getSchlName() + " 관련서류");

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
            aCont.setGrpLabel( aAcad.getSchlName() + " 관련서류" );
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

        //학연산 조회
        if( "00002".equals(tempApp.getApplAttrCode())) {
            rList = new ArrayList<TotalApplicationDocumentContainer>();
            codeParam.setAdmsCodeGrp("APPL_ATTR");
            codeParam.setAdmsCode("00002");
            codeParam.setGrpLevel(1);
            codeParam.setItemTypeCode("00006");//학연산

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
            aCont.setGrpLabel("학연산 관련서류");
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
            codeParam.setItemCode("00007");//

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
            aCont.setGrpLabel("외국인전형 관련서류");
            aCont.setDisplayGrpFg(true);
            aCont.setSubContainer(subDocList);
            rContList.add(aCont);
        }

        //기타 및 자유입력 조회

        codeParam.setGrpLevel(1);
        codeParam.setItemTypeCode("00009");// 기타 및 추가제출
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
        aCont.setGrpLabel("기타 및 추가제출");
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
            pCont.setGrpLabel( pCont.getDocItemName());
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
            pCont.setGrpLabel( pCont.getDocItemName());
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
            if( pCont.getMsgNo()!= null && pCont.getMsgNo()!= "" ) {
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
                System.out.println("");
            }
            if( pCont.getMsgNo()!= null && pCont.getMsgNo()!= "" ) {
                pCont.setMsg(messageResolver.getMessage(pCont.getMsgNo()));
            }
            pList.add(pCont);
        }

        return rContList;
    }

    @Override
    public String retrievePhotoUri(int applNo) {
        ParamForDocumentType aParam = new ParamForDocumentType();
        String photoUrl = null;
        aParam.setApplNo( applNo);
        aParam.setDocTypeCode("00001");//기본
        aParam.setDocItemCode("00001");//사진
        List<TotalApplicationDocument> rList = null;

        rList = commonDAO.queryForList(NAME_SPACE + "CustomApplicationDocumentMapper.selectApplicationDocumentByDocumentType", aParam, TotalApplicationDocument.class );
        if( rList != null && rList.size()>0 ) {
            photoUrl =  rList.get(0).getFilePath() + "/" + rList.get(0).getFileName();
        }
        return photoUrl;
    }


}
