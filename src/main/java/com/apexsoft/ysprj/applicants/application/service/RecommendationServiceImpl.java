package com.apexsoft.ysprj.applicants.application.service;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.exception.ErrorInfo;
import com.apexsoft.framework.exception.YSBizException;
import com.apexsoft.framework.mail.Mail;
import com.apexsoft.framework.mail.MailType;
import com.apexsoft.framework.mail.SESMailService;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.framework.persistence.file.exception.EncryptedPDFException;
import com.apexsoft.framework.persistence.file.manager.FilePersistenceManager;
import com.apexsoft.framework.persistence.file.model.FileInfo;
import com.apexsoft.ysprj.applicants.application.domain.*;
import com.apexsoft.ysprj.applicants.common.domain.MailContentsParamKey;
import com.apexsoft.ysprj.applicants.common.service.PDFService;
import com.apexsoft.ysprj.applicants.common.util.CryptoUtil;
import com.apexsoft.ysprj.applicants.common.util.FilePathUtil;
import com.apexsoft.ysprj.applicants.common.util.MailFactory;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.util.*;
import java.util.zip.DataFormatException;

/**
 * Created by hanmomhanda on 15. 1. 13.
 */
@Service
public class RecommendationServiceImpl implements RecommendationService {

    private final static String NAME_SPACE = "com.apexsoft.ysprj.applicants.application.sqlmap.";

    @Autowired
    private CommonDAO commonDAO;

    @Autowired
    private SESMailService sesMailService;

    @Autowired
    private ServletContext context;

    @Value("#{app['site.url']}")
    private String SITE_URL;

    @Value("#{app['recommendation.duedate']}")
    private String REC_DUE_DATE;

    @Value("#{app['institution.name.kr']}")
    private String INST_NAME_KR;

    @Value("#{app['institution.name.en']}")
    private String INST_NAME_EN;

    @Value("#{app['file.midPath']}")
    private String midPath;

    @Value("#{app['recommendation.alternative.url']}")
    private String recommendAlternativeURL;

    private final String APP_NULL_STATUS = "00000";      // 에러일 때 반환값

    @Autowired
    private FilePersistenceManager s3PersistenceManager;

    @Autowired
    private PDFService pdfService;

    private final String sampleRecKey = "f865d2b5becebbf95b65871442fcccb95695340e7a5967ab247baf34183f4027";

    private static final Logger logger = LoggerFactory.getLogger(RecommendationServiceImpl.class);

    @Override
    public ExecutionContext retrieveRecommendation(int recNo) {
        ExecutionContext ec = new ExecutionContext();

        Recommendation recommendation =
                commonDAO.queryForObject(NAME_SPACE + "CustomRecommendationMapper.selectByRecNo",
                        recNo, Recommendation.class);

        ec.setData(recommendation);
        return ec;
    }

    @Override
    public ExecutionContext retrieveDocInfo() {
        ExecutionContext ec = new ExecutionContext();
        RecommendationDocument recDocInfo =
                commonDAO.queryForObject(NAME_SPACE + "CustomRecommendationMapper.selectRecDocInfo",
                        RecommendationDocument.class);
        ec.setData(recDocInfo);
        return ec;
    }

    @Override
    public ExecutionContext retrieveRecommendationList(int applNo) {
        ExecutionContext ec = new ExecutionContext();

        List<Recommendation> recommendationList =
                commonDAO.queryForList(NAME_SPACE + "CustomRecommendationMapper.selectListByApplNo",
                        applNo, Recommendation.class);

        ec.setData(recommendationList);
        return ec;
    }

    @Override
    public ExecutionContext previewRecommendation(Recommendation recommendation) {
        ExecutionContext ec = new ExecutionContext();
        recommendation.setReqSubject(MessageResolver.getMessage("MAIL_REQUEST_RECOMMENDATION_SUBJECT",
                new Object[]{INST_NAME_EN}));
        recommendation.setRecKey(sampleRecKey);
        recommendation.setDueDate(REC_DUE_DATE);
        fillEtcInfo(recommendation);

        Mail mail = MailFactory.create(MailType.RECOMMENDATION_REQUEST);
        mail.setInfo(recommendation);
        mail.setInfoType(Recommendation.class);
        mail.setTo(new String[]{recommendation.getProfMailAddr()});
        mail.setSubject(recommendation.getReqSubject());
        mail.withContentsParam("contextPath", context.getContextPath())
                .withContentsParam("siteURL", SITE_URL);
        mail.makeContents();

        ec.setData(mail);
        return ec;
    }

    @Override
    public ExecutionContext saveRecommendationRequest(Recommendation recommendation) {
        ExecutionContext ec = new ExecutionContext();
        int applNo = recommendation.getApplNo();
        int recSeq = recommendation.getRecSeq() == null ? -1 : recommendation.getRecSeq();

        recommendation.setReqSubject(MessageResolver.getMessage("MAIL_REQUEST_RECOMMENDATION_SUBJECT",
                new Object[]{INST_NAME_EN}));
        fillEtcInfo(recommendation);

        Mail mail = MailFactory.create(MailType.RECOMMENDATION_REQUEST);
        mail.setInfo(recommendation);
        mail.setInfoType(Recommendation.class);
        mail.setTo(new String[]{recommendation.getProfMailAddr()});
        mail.setSubject(recommendation.getReqSubject());
        mail.makeContents();

//        recommendation.setMailContents(makeLinkText(recommendation, true));
        recommendation.setMailContents(mail.getContents());
        recommendation.setDueDate(REC_DUE_DATE);
        recommendation.setRecStsCode(RecommendStatus.TEMP.codeVal());

        boolean isUpdate = recSeq > 0;

        int r1 = saveRecommendation(recommendation);

        if (r1 == 1) {
            ec.setResult(ExecutionContext.SUCCESS);
            ParamForApplicationRecommendation param = new ParamForApplicationRecommendation();
            param.setApplNo(applNo);
            param.setRecSeq(recommendation.getRecSeq());
            Recommendation result = commonDAO.queryForObject(NAME_SPACE + "CustomRecommendationMapper.selectByApplNoRecSeq",
                    param, Recommendation.class);
            ec.setData(result);
            ec.setMessage(MessageResolver.getMessage("U06505")); // 추천서 요청을 임시 저장했습니다.
        } else {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(MessageResolver.getMessage("U06506")); // 추천서 요청 임시 저장에 실패했습니다.

            String errCode = null;
            errCode = isUpdate ? "ERR0083" : "ERR0081";
            ec.setErrCode(errCode);

            Map<String, String> errorInfo = new HashMap<String, String>();
            errorInfo.put("applNo", String.valueOf(applNo));
            ec.setErrorInfo(new ErrorInfo(errorInfo));

            throw new YSBizException(ec);
        }

        return ec;
    }

    @Override
    public ExecutionContext deleteRecommendationRequest(Recommendation recommendation) {
        ExecutionContext ec = new ExecutionContext();
        int applNo = recommendation.getApplNo();
        int recNo = recommendation.getRecNo();
        int r1 = 0;

        r1 = commonDAO.delete(NAME_SPACE + "CustomRecommendationMapper.deleteByPrimaryKey", recommendation);

        if (r1 == 1) {
            ec.setResult(ExecutionContext.SUCCESS);
            ec.setMessage(MessageResolver.getMessage("U06507")); // 추천서 요청을 취소했습니다.
        } else {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(MessageResolver.getMessage("U06508")); // 추천서 요청 취소에 실패했습니다.
            ec.setErrCode("ERR0084");
            Map<String, String> errorInfo = new HashMap<String, String>();
            errorInfo.put("applNo", String.valueOf(applNo));
            errorInfo.put("recNo", String.valueOf(recNo));
            ec.setErrorInfo(new ErrorInfo(errorInfo));

            throw new YSBizException(ec);
        }
        return ec;
    }

    @Override
    public ExecutionContext sendRecommendationRequest(Recommendation recommendation) {
        ExecutionContext ec = new ExecutionContext();
        int recNo = recommendation.getRecNo();
        int applNo = recommendation.getApplNo();
        int recSeq = recommendation.getRecSeq() == null ? -1 : recommendation.getRecSeq();

        ExecutionContext ec0 = retrieveRecommendation(recNo);
        Object obj = ec0.getData();
        if (obj != null) {
            recommendation = (Recommendation) obj;
            recommendation.setDueDate(REC_DUE_DATE);
            recommendation.setRecStsCode(RecommendStatus.SENT.codeVal());
        } else {

            recommendation.setDueDate(REC_DUE_DATE);
            recommendation.setRecStsCode(RecommendStatus.SENT.codeVal());
            recommendation.setReqSubject(MessageResolver.getMessage("MAIL_REQUEST_RECOMMENDATION_SUBJECT",
                    new Object[]{INST_NAME_EN}));
        }

        fillEtcInfo(recommendation);

        int r1 = saveRecommendation(recommendation);

        Mail mail = MailFactory.create(MailType.RECOMMENDATION_REQUEST);
        mail.setInfo(recommendation);
        mail.setInfoType(Recommendation.class);
        mail.setTo(new String[]{recommendation.getProfMailAddr()});
        mail.setSubject(recommendation.getReqSubject());
        mail.withContentsParam("contextPath", context.getContextPath())
                .withContentsParam("siteURL", SITE_URL)
                .withContentsParam("alternativeURL", recommendAlternativeURL)
                .withContentsParam("dueTime", REC_DUE_DATE);
        mail.makeContents();
        recommendation.setMailContents(mail.getContents());

        boolean isUpdate = recSeq > 0 || recNo > 0;

        if (r1 == 1) {
            ec.setResult(ExecutionContext.SUCCESS);
            if (sendRequestMail(mail)) {
                ec.setMessage(MessageResolver.getMessage("U06509")); // 교수님께 추천서 요청을 발송했습니다.
            } else {
                ec.setResult(ExecutionContext.FAIL);
                ec.setMessage(MessageResolver.getMessage("U06510")); // 추천서 요청 메일 발송에 실패했습니다.
                ec.setErrCode("ERR0085");
                Map<String, String> errorInfo = new HashMap<String, String>();
                errorInfo.put("recNo", String.valueOf(recNo));
                errorInfo.put("applNo", String.valueOf(applNo));
                errorInfo.put("recSeq", String.valueOf(recSeq));
                ec.setErrorInfo(new ErrorInfo(errorInfo));

                throw new YSBizException(ec);
            }
        } else {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(MessageResolver.getMessage("U06506")); // 추천서 요청 임시 저장에 실패했습니다.
            String errCode = null;
            errCode = isUpdate ? "ERR0083" : "ERR0081";
            ec.setErrCode(errCode);

            Map<String, String> errorInfo = new HashMap<String, String>();
            errorInfo.put("recNo", String.valueOf(recNo));
            errorInfo.put("applNo", String.valueOf(applNo));
            errorInfo.put("recSeq", String.valueOf(recSeq));
            ec.setErrorInfo(new ErrorInfo(errorInfo));

            throw new YSBizException(ec);
        }

        return ec;
    }

    @Override
    public ExecutionContext openRecommendationRequestByProfessor(Recommendation param) {
        ExecutionContext ec = new ExecutionContext();
        Recommendation recommendation = commonDAO.queryForObject(NAME_SPACE + "CustomRecommendationMapper.selectByRecNo",
                param, Recommendation.class);
        // 등록 완료가 아니면 교수 확인(OPENED)로 상태 변경
        if (!RecommendStatus.COMPLETED.codeVal().equals(recommendation.getRecStsCode())) {
            int r1 = 0;
            param.setRecStsCode(RecommendStatus.OPENED.codeVal());
            param.setModDate(new Date());
            r1 = commonDAO.updateItem(param, NAME_SPACE, "CustomRecommendationMapper", ".updateSelective");

            if (r1 != 1) {
                ec.setResult(ExecutionContext.FAIL);
                ec.setMessage(MessageResolver.getMessage("U06513")); // 추천서 상태 변경에 실패했습니다.
                ec.setErrCode("ERR0081");

                Map<String, String> errorInfo = new HashMap<String, String>();
                errorInfo.put("recNo", String.valueOf(param.getRecNo()));
                errorInfo.put("applNo", String.valueOf(param.getApplNo()));
                errorInfo.put("recSeq", String.valueOf(param.getRecSeq()));
                ec.setErrorInfo(new ErrorInfo(errorInfo));

                throw new YSBizException(ec);
            }
        }
        ec.setResult(ExecutionContext.SUCCESS);
        return ec;
    }

    @Override
    public ExecutionContext retrieveRecommendationByProfessor(Recommendation param) {
        ExecutionContext ec = new ExecutionContext();

        RecommendationApplicationInfo recApplInfo =
                commonDAO.queryForObject(NAME_SPACE + "CustomRecommendationMapper.selectRecommendationByApplNoKey",
                        param, RecommendationApplicationInfo.class);
        ec.setData(recApplInfo);



        return ec;
    }

    @Override
    public ExecutionContext registerRecommendationByProfessor(MultipartHttpServletRequest multipartHttpServletRequest,
                                                              MultipartFile multipartFile,
                                                              Recommendation recommendation) {
        ExecutionContext ec = new ExecutionContext();
        int recNo = recommendation.getRecNo();

        // 파일 업로드
        FileInfo fileInfo = uploadRecommendationFileToS3(multipartHttpServletRequest, multipartFile, recommendation);

        // APPL_REC 업데이트
        int r1 = updateApplRec(recommendation);

        if (r1 == 1) {
            ec.setResult(ExecutionContext.SUCCESS);

            // APPL_DOC 업데이트
            updateApplDoc(multipartHttpServletRequest, multipartFile, recommendation, fileInfo);

            // 합치기 안하므로 원서, 수험표 재생성 필요 없음
            // 첨부 파일 저장 후이면 어차피 원서 미리보기 생성을 다시 해야 이메일 추천서 함침 여부를 알 수 있으므로 여기서 합쳐줄 필요 없음
            // 작성 완료 단계이면 원서 수정모드로 갈 수 없으므로 이메일 추천서 합침 여부 확인은 결제 이후에나 가능. 여기서 합쳐줄 필요 없음
            // 입금 대기의 경우도 작성 완료와 같으므로 여기서 합쳐줄 필요 없음
            // 결국 이미 결제가 완료된 상태에서만 추천서 등록 시마다 다시 합쳐주면 됨.
//            if (application.isCompleted()) {
//                genAndUploadApplicationFormAndSlipFile(application);
//            }
            Application applicationFromDB = commonDAO.queryForObject(NAME_SPACE + "ApplicationMapper.selectByPrimaryKey",
                    recommendation.getApplNo(), Application.class);
            if (applicationFromDB.isCompleted()) {
                pdfService.genAndUploadPDFByApplicants(applicationFromDB);
            }

            // 메일 발송
            sendNotificationMail(recommendation);

        } else {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(MessageResolver.getMessage("U06735")); // 추천서 등록에 실패했습니다.
            ec.setErrCode("ERR0083");
            Map<String, String> errorInfo = new HashMap<String, String>();
            errorInfo.put("recNo", String.valueOf(recNo));
            ec.setErrorInfo(new ErrorInfo(errorInfo));

            throw new YSBizException(ec);
        }

        return ec;
    }

    // 서버 로컬에 임시 저장할 디렉토리
    private String getUploadDir(MultipartHttpServletRequest multipartHttpServletRequest) {
        int applNo = Integer.parseInt(multipartHttpServletRequest.getParameter("applNo"));
        String userId = multipartHttpServletRequest.getParameter("userId");
        String admsNo = multipartHttpServletRequest.getParameter("admsNo");

        String uploadDir = FilePathUtil.getUploadDirectory(admsNo, userId, applNo);
        return uploadDir;
    }

    // 추천서 업로드 파일이름 생성
    private String getUploadFileName(MultipartFile multipartFile, Recommendation recommendation) {
        return "file-recommendation-" + recommendation.getRecNo() + "-" + multipartFile.getOriginalFilename();
    }

    // 추천서 PDF 파일 S3에 업로드
    private FileInfo uploadRecommendationFileToS3(MultipartHttpServletRequest multipartHttpServletRequest, MultipartFile multipartFile, Recommendation recommendation) {
        ExecutionContext ec = null;
        int applNo = Integer.parseInt(multipartHttpServletRequest.getParameter("applNo"));
        String originalFileName = multipartFile.getOriginalFilename();
        Locale locale = new Locale(multipartHttpServletRequest.getParameter("lang"));
        FileInfo fileInfo = null;

        String uploadDir = getUploadDir(multipartHttpServletRequest);
        String uploadFileName = getUploadFileName(multipartFile, recommendation);
        try {
            fileInfo = s3PersistenceManager.save(uploadDir, uploadFileName, originalFileName,
                    multipartFile.getInputStream());
        } catch (EncryptedPDFException e) {
            ec = new ExecutionContext(ExecutionContext.FAIL);
            Map<String, String> errorInfo = new HashMap<String, String>();
            errorInfo.put("applNo", String.valueOf(applNo));
            errorInfo.put("originalFileName", originalFileName);
            ec.setErrorInfo(new ErrorInfo(errorInfo));
//            throw new EncryptedPDFException(ec, "U04514", "ERR0052");
            ec.setMessage(MessageResolver.getMessage("U04514", locale));
            ec.setErrCode("ERR0052");
            throw new YSBizException(ec);
        } catch (IOException ioe) {
            if (ioe.getCause().getCause() instanceof DataFormatException) {
                ec = new ExecutionContext(ExecutionContext.FAIL);
                Map<String, String> errorInfo = new HashMap<String, String>();
                errorInfo.put("applNo", String.valueOf(applNo));
                errorInfo.put("originalFileName", originalFileName);
                ec.setErrorInfo(new ErrorInfo(errorInfo));
//                throw new PasswordedPDFException(ec, "U04515", "ERR0052");
                ec.setMessage(MessageResolver.getMessage("U04515", locale));
                ec.setErrCode("ERR0052");
                throw new YSBizException(ec);
            } else {
                logger.error("Upload PDF is NOT loaded to PDDocument, DocumentController.fileUpload()");
                logger.error("applNo: " + applNo);
                logger.error("orgFileName: " + originalFileName);
                ec = new ExecutionContext(ExecutionContext.FAIL);
                Map<String, String> errorInfo = new HashMap<String, String>();
                errorInfo.put("applNo", String.valueOf(applNo));
                errorInfo.put("originalFileName", originalFileName);
                ec.setErrorInfo(new ErrorInfo(errorInfo));
//                throw new FileNoticeException(ec, "U04518", "ERR0052");
                ec.setMessage(MessageResolver.getMessage("U04518", locale));
                ec.setErrCode("ERR0052");
                throw new YSBizException(ec);
            }
        }
        return fileInfo;
    }

    // APPL_REC 업데이트
    private int updateApplRec(Recommendation recommendation) {
        recommendation.setRecStsCode(RecommendStatus.COMPLETED.codeVal());  // 추천서 접수 완료
        recommendation.setModDate(new Date());
        recommendation.setFileUploadedYn("Y");

        int r1 = commonDAO.updateItem(recommendation, NAME_SPACE, "CustomRecommendationMapper", ".updateSelective");

        return r1;
    }

    // APPL_DOC 업데이트
    private void updateApplDoc(MultipartHttpServletRequest multipartHttpServletRequest,
                               MultipartFile multipartFile,
                               Recommendation recommendation,
                               FileInfo fileInfo) {

        ExecutionContext ec = new ExecutionContext();
        int applNo = Integer.parseInt(multipartHttpServletRequest.getParameter("applNo"));
        String userId = multipartHttpServletRequest.getParameter("userId");
        int recNo = recommendation.getRecNo();
        String uploadDir = getUploadDir(multipartHttpServletRequest);
        String uploadFileName = getUploadFileName(multipartFile, recommendation);

        TotalApplicationDocument oneDocument = new TotalApplicationDocument();
        oneDocument.setApplNo(applNo);
        oneDocument.setDocTypeCode(multipartHttpServletRequest.getParameter("docTypeCode"));
        oneDocument.setDocGrp(recNo);
        oneDocument.setDocItemCode(multipartHttpServletRequest.getParameter("docItemCode"));
        oneDocument.setDocItemName(multipartHttpServletRequest.getParameter("docItemName"));
        oneDocument.setDocItemNameXxen(multipartHttpServletRequest.getParameter("docItemNameXxen"));
        oneDocument.setFileExt("pdf");
        oneDocument.setImgYn("N");
        oneDocument.setFilePath(midPath + "/" + uploadDir + "/" + uploadFileName);
        oneDocument.setFileName(uploadFileName);
        oneDocument.setOrgFileName(multipartFile.getOriginalFilename());
        oneDocument.setPageCnt(fileInfo.getPageCnt());
        oneDocument.setFileUploadFg("Y".equals(multipartHttpServletRequest.getParameter("fileUploadedYn")));


        int rUpdate = 0, rInsert = 0, update=0, insert =0;

        //기존 파일이 업로드 되어 있는 경우
        if( oneDocument.isFileUploadFg()){
            ParamForDocOfRecommend param = new ParamForDocOfRecommend();
            param.setApplNo(applNo);
            param.setDocGrp(recNo);
            ApplicationDocument aDoc = commonDAO.queryForObject(NAME_SPACE +
                    "CustomApplicationDocumentMapper.selectApplicationDocumentOfRecommendation", param, ApplicationDocument.class);
            rUpdate++;
            oneDocument.setDocSeq(aDoc.getDocSeq());
            oneDocument.setModDate(new Date());
            oneDocument.setModId(userId);
            update = update + commonDAO.updateItem(oneDocument, NAME_SPACE, "ApplicationDocumentMapper");

        }else{
            rInsert++;

            int maxSeq = commonDAO.queryForInt(NAME_SPACE +"CustomApplicationDocumentMapper.selectMaxSeqByApplNo", applNo ) ;
            oneDocument.setFileUploadFg(true);
            oneDocument.setDocSeq(++maxSeq);
            oneDocument.setCreId(userId);
            oneDocument.setCreDate(new Date());
            insert = insert + commonDAO.insertItem(oneDocument, NAME_SPACE, "ApplicationDocumentMapper");
        }
//        if (  insert == rInsert && update == rUpdate && applUpdate == 1 ) {
        if (  insert == rInsert && update == rUpdate ) {
            ec.setResult(ExecutionContext.SUCCESS);
            ec.setMessage(MessageResolver.getMessage("U325"));
            ec.setData(oneDocument);
        } else {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(MessageResolver.getMessage("U326"));
            ec.setData(new ApplicationIdentifier(applNo, APP_NULL_STATUS));
            String errCode = null;
            if ( insert != rInsert ) errCode = "ERR0031";
            if ( update != rUpdate ) errCode = "ERR0033";
            ec.setErrCode(errCode);
            Map<String, String> errorInfo = new HashMap<String, String>();
            errorInfo.put("applNo", String.valueOf(applNo));
            errorInfo.put("creId", oneDocument.getCreId());
            errorInfo.put("modId", oneDocument.getModId());
            errorInfo.put("docSeq", String.valueOf(oneDocument.getDocSeq()));
            errorInfo.put("docItemCode", oneDocument.getDocItemCode());
            errorInfo.put("docItemName", oneDocument.getDocItemName());
            ec.setErrorInfo(new ErrorInfo(errorInfo));
            throw new YSBizException(ec);
        }
    }

    // 원서 수험표 재생성
//    private ExecutionContext genAndUploadApplicationFormAndSlipFile(Application application) {
//        ExecutionContext ec = new ExecutionContext();
//
//        String lang = application.isForeignAppl() ? "en" : "kr";
//        String reportName = "yonsei-appl-" + lang;
//        ExecutionContext ecGenAppl = birtService.generateBirtFile(application.getApplNo(), reportName);
//        reportName = "yonsei-adms-" + lang;
//        ExecutionContext ecGenAdms = birtService.generateBirtFile(application.getApplNo(), reportName);
//        ExecutionContext ecPdfMerge = pdfService.genAndUploadPDFByApplicants(application);
//        if ( ExecutionContext.FAIL.equals(ecGenAppl.getResult()) ||
//                ExecutionContext.FAIL.equals(ecGenAdms.getResult()) ||
//                ExecutionContext.FAIL.equals(ecPdfMerge.getResult()) ) {
//            ExecutionContext ecError = new ExecutionContext(ExecutionContext.FAIL);
//
//            ecError.setMessage(MessageResolver.getMessage("U06903"));
//            ecError.setErrCode("ERR0073");
//
//            Map<String, String> errorInfo = new HashMap<String, String>();
//            errorInfo.put("applNo", String.valueOf(application.getApplNo()));
//
//            ecError.setErrorInfo(new ErrorInfo(errorInfo));
//            throw new YSBizException(ecError);
//        }
//
//        return ec;
//    }

    // 추천서 등록 완료 알림 메일 to 지원자
    private void sendNotificationMail(Recommendation recommendation) {
        ExecutionContext ec = new ExecutionContext();
        int recNo = recommendation.getRecNo();

        Application application =
                commonDAO.queryForObject(NAME_SPACE + "CustomRecommendationMapper.selectApplicantMailByRecNo",
                        recNo, Application.class);
        Recommendation result = commonDAO.queryForObject(NAME_SPACE + "CustomRecommendationMapper.selectByRecNo", recNo, Recommendation.class);
        Mail mail = MailFactory.create(MailType.RECOMMENDATION_COMPLETED);
        mail.setInfo(result);
        mail.setInfoType(Recommendation.class);
        String applicantKorName = application.getKorName();
        boolean hasKorName = applicantKorName != null && !StringUtils.isEmpty(applicantKorName);
        String applicantName = application.getEngName() + " " + (hasKorName ? "(" + application.getKorName() + ")" : "");                    ;
        mail.setTo(new String[]{application.getMailAddr()});
        mail.setSubject(MessageResolver.getMessage("MAIL_COMPLETED_RECOMMENDATION_SUBJECT"));
        Map<Object, String> contentsParam = mail.getContentsParam();
        contentsParam.put(MailContentsParamKey.USER_NAME, applicantName);
        contentsParam.put(MailContentsParamKey.PROF_NAME, result.getProfName());
        mail.makeContents();

        if (sendCompletedMail(mail)) {
            ec.setMessage(MessageResolver.getMessage("U06737")); // 추천서 등록을 완료하고 지원자에게 알림 메일을 보냈습니다.
        } else {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(MessageResolver.getMessage("U06736")); // 지원자에게 추천서 등록 완료 알림 메일을 보내는데 실패했습니다.
            ec.setErrCode("ERR0105");
            Map<String, String> errorInfo = new HashMap<String, String>();
            errorInfo.put("recNo", String.valueOf(recNo));
            ec.setErrorInfo(new ErrorInfo(errorInfo));

            throw new YSBizException(ec);
        }
    }

    @Override
    public ExecutionContext retrieveUncompletedRecommendationList() {
        ExecutionContext ec = new ExecutionContext();
        List<Recommendation> uncompletedRecList =
                commonDAO.queryForList(NAME_SPACE + "CustomRecommendationMapper.selectUncompletedRecs", Recommendation.class);
//                commonDAO.queryForList(NAME_SPACE + "CustomRecommendationMapper.selectUncompletedRecsTest", Recommendation.class);
        ec.setData(uncompletedRecList);
        return ec;
    }

    @Override
    public ExecutionContext sendUrgeMail(List<Recommendation> recommendationList) {
        ExecutionContext ec = new ExecutionContext();
        List<Mail> failedList = new ArrayList<Mail>();
        int num = 0;
        for (Recommendation recommendation : recommendationList) {
            fillEtcInfo(recommendation);
            Mail mailToProf = MailFactory.create(MailType.RECOMMENDATION_URGE);
            mailToProf.setTo(new String[]{recommendation.getProfMailAddr()});
            mailToProf.setSubject(MessageResolver.getMessage("MAIL_URGENCY_RECOMMENDATION_SUBJECT",
                    new Object[]{INST_NAME_EN}));
            mailToProf.setInfo(recommendation);
            mailToProf.setInfoType(Recommendation.class);
            mailToProf.withContentsParam("contextPath", context.getContextPath())
                    .withContentsParam("siteURL", SITE_URL)
                    .withContentsParam("alternativeURL", recommendAlternativeURL)
                    .withContentsParam("instNameEN", INST_NAME_EN)
                    .withContentsParam("dueTime", REC_DUE_DATE);
            mailToProf.makeContents();
            if (!sendUrgeMail(mailToProf)) {
                // 수신자 주소가 잘못되어있더라도 failedList에는 들어오지 않음
                // 정말로 SES 수준에서 발송 오류난 것만 포함됨
                failedList.add(mailToProf);
                // sendUrgeMail()에서 error 로그를 쏘므로 여기서는 별도 처리 안함
            } else {
                System.out.println("[Succeeded : " + ++num + "] APPL_NO : " + recommendation.getApplNo() + ", To : " + mailToProf.getTo());
            }

            // 지원자에게는 안 보내기로 함(by 연대 담당자)
//            Mail mailToApplicant = MailFactory.create(MailType.RECOMMENDATION_URGE_NOTICE);
//            int recNo = recommendation.getRecNo();
//            Application application =
//                    commonDAO.queryForObject(NAME_SPACE + "CustomRecommendationMapper.selectApplicantMailByRecNo",
//                            recNo, Application.class);
//            String applicantName = StringUtil.getEmptyIfNull(application.getKorName()).length() > 0 ?
//                    application.getKorName() :
//                    application.getEngName();
//            mailToApplicant.setTo(new String[]{application.getMailAddr()});
//            mailToApplicant.setSubject(MessageResolver.getMessage("MAIL_URGENCY_RECOMMENDATION_NOTICE_SUBJECT"));
//            mailToApplicant.setInfo(recommendation);
//            mailToApplicant.setInfoType(Recommendation.class);
//            mailToApplicant.withContentsParam("dueTime", REC_DUE_DATE)
//                    .withContentsParam("applicantName", applicantName);
//            mailToApplicant.makeContents();
//            if (!sendUrgeMail(mailToApplicant)) {
//                failedList.add(mailToApplicant);
//            }
        }
        int i = 0;
        Recommendation rec = null;
        for (Mail mail : failedList) {
            rec = (Recommendation)mail.getInfo();
            StringBuilder tos = new StringBuilder();
            for (String to : mail.getTo()) {
                tos.append("  ").append(to);
            }
            System.out.println("[Failed : " + ++i + "] APPL_NO : " + rec.getApplNo() + ", To : " + tos.toString());
        }
        ec.setData(failedList);
        return ec;
    }

    /**
     * 추천서 요청 저장
     *
     * @param recommendation
     * @return
     */
    private int saveRecommendation(Recommendation recommendation) {
        int recNo = recommendation.getRecNo();
        int applNo = recommendation.getApplNo();
        int recSeq = recommendation.getRecSeq() == null ? -1 : recommendation.getRecSeq();
        String id = recommendation.getModId();
        int r1 = 0;
        boolean isUpdate = recSeq > 0;
        Date date = new Date();
        if (isUpdate) {
            recommendation.setModDate(date);
            r1 = commonDAO.updateItem(recommendation, NAME_SPACE, "CustomRecommendationMapper", ".updateSelective");
        } else {
            int maxRecNo = commonDAO.queryForInt(NAME_SPACE + "CustomRecommendationMapper.selectMaxRecNo", applNo) ;
            recommendation.setRecNo(++maxRecNo);
            recSeq = commonDAO.queryForInt(NAME_SPACE + "CustomRecommendationMapper.selectMaxRecSeqByApplNo", applNo);
            recommendation.setRecSeq(++recSeq);
            String encrypted = getEncryptedRecKey(recommendation);
            recommendation.setRecKey(encrypted);
            recommendation.setCreDate(date);
            recommendation.setCreId(id);
            recommendation.setModId(null);
            r1 = commonDAO.insertItem(recommendation, NAME_SPACE, "CustomRecommendationMapper");
        }
        return r1;
    }

    @Override
    public Recommendation fillEtcInfo(Recommendation recommendation) {
        int recNo = recommendation.getApplNo();
        RecommendationApplicationInfo recApplInfo = commonDAO.queryForObject(NAME_SPACE + "CustomRecommendationMapper.selectRecommendApplInfo",
                recNo, RecommendationApplicationInfo.class);
        String korName = recApplInfo.getKorName();
        if (korName != null && !korName.isEmpty())
            recommendation.setApplicantName(recApplInfo.getEngName() + "(" + recApplInfo.getKorName() +")");
        else
            recommendation.setApplicantName(recApplInfo.getEngName());
        recommendation.setApplicantNationality(recApplInfo.getNationality());
        recommendation.setDegree(recApplInfo.getDegree());
        recommendation.setMajor(recApplInfo.getDept());
        return recommendation;
    }

    /**
     * 추천서 등록화면 접근 URL을 위한 암호화 해쉬 문자열 반환
     * @param recommendation
     * @return
     */
    private String getEncryptedRecKey(Recommendation recommendation) {
        int recNo = recommendation.getRecNo();
        int applNo = recommendation.getApplNo();
        String profName = recommendation.getProfName();
        String profMailAddr = recommendation.getProfMailAddr();
        String input = recNo + ";" + applNo + ";" + profName + ";" + profMailAddr;

        String encrypted = null;
        try {
            encrypted = CryptoUtil.getCryptedString(context, input, true);
        } catch (IOException e) {
            throw new YSBizException(e);
        }
        return encrypted;
    }

    /**
     * 추천서 등록 화면 링크 암호화 해쉬 문자열을 생성하고 메일 발송
     * @param mail
     * @return
     */
    private boolean sendRequestMail(Mail mail) {
        boolean isSent = false;
        try {
            sesMailService.sendMail(mail);
            isSent = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isSent;
    }

    /**
     * 추천서 등록 안 될 때 독려 메일 발송
     *
     *
     * @param mail
     * @return
     */
    private boolean sendUrgeMail(Mail mail) {

        boolean isSentToProf = false;

        try {
            sesMailService.sendMail(mail);
            isSentToProf = true;
        } catch (Exception e) {
            Object obj = mail.getInfo();
            if (obj instanceof Recommendation) {
                Recommendation recommendation = (Recommendation)obj;
                int applNo = recommendation.getApplNo();
                int recNo = recommendation.getRecNo();
                String mailAddr = recommendation.getProfMailAddr();
                logger.error("[SEND-URGE-MAIL-FAIL]applNo : " + applNo + ", recNo : " + recNo + ", mailAddr : " + mailAddr);
            } else {
                logger.error("[SEND-URGE-MAIL-FAIL]mail.getInfo() is NOT a Recommendation");
            }
        }
        return isSentToProf;

    }


    /**
     * 추천서 등록 후 교수가 지원자에게 확인 메일 발송
     *
     * @param mail
     * @return
     */
    private boolean sendCompletedMail(Mail mail) {
        boolean isSent = false;
        try {
            sesMailService.sendMail(mail);
            isSent = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isSent;

    }


}
