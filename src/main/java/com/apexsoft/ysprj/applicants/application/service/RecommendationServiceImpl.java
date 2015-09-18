package com.apexsoft.ysprj.applicants.application.service;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.exception.ErrorInfo;
import com.apexsoft.framework.exception.YSBizException;
import com.apexsoft.framework.mail.Mail;
import com.apexsoft.framework.mail.MailType;
import com.apexsoft.framework.mail.SESMailService;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.framework.persistence.file.manager.FilePersistenceManager;
import com.apexsoft.framework.persistence.file.model.FileInfo;
import com.apexsoft.ysprj.applicants.application.domain.*;
import com.apexsoft.ysprj.applicants.common.domain.MailContentsParamKey;
import com.apexsoft.ysprj.applicants.common.util.CryptoUtil;
import com.apexsoft.ysprj.applicants.common.util.FileUtil;
import com.apexsoft.ysprj.applicants.common.util.MailFactory;
import com.apexsoft.ysprj.applicants.common.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

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

    @Value("#{app['s3.midPath']}")
    private String s3MidPath;

    private final String APP_NULL_STATUS = "00000";      // 에러일 때 반환값

    @Autowired
    private FilePersistenceManager s3PersistenceManager;

    private final String sampleRecKey = "f865d2b5becebbf95b65871442fcccb95695340e7a5967ab247baf34183f4027";

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
        Mail mail = MailFactory.create(MailType.RECOMMENDATION_REQUEST);
        mail.setInfo(recommendation);
        mail.setInfoType(Recommendation.class);
        mail.setTo(new String[]{recommendation.getProfMailAddr()});
        mail.setSubject(recommendation.getReqSubject());
        mail.withContentsParam("contextPath", context.getContextPath())
                .withContentsParam("siteURL", SITE_URL)
                .withContentsParam("dueTime", REC_DUE_DATE);
        mail.makeContents();
        recommendation.setMailContents(mail.getContents());

        boolean isUpdate = recSeq > 0 || recNo > 0;

        int r1 = saveRecommendation(recommendation); // 요청 완료

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
        Date date = new Date();

        int applNo = Integer.parseInt(multipartHttpServletRequest.getParameter("applNo"));
        String userId = multipartHttpServletRequest.getParameter("userId");
        String admsNo = multipartHttpServletRequest.getParameter("admsNo");

        // 파일 업로드
        String uploadDir = FileUtil.getUploadDirectory(admsNo, userId, applNo);
        String uploadFileName = "file-recommendation-" + recNo + "-" + multipartFile.getOriginalFilename();
        String originalFileName = multipartFile.getOriginalFilename();
        FileInfo fileInfo = null;

        try {
            fileInfo = s3PersistenceManager.save(uploadDir, uploadFileName, originalFileName,
                    multipartFile.getInputStream());
        } catch (IOException ioe) {
            throw new YSBizException(ioe);
        }

        // APPL_REC 업데이트
        recommendation.setRecStsCode(RecommendStatus.COMPLETED.codeVal());  // 추천서 접수 완료
        recommendation.setModDate(date);
        recommendation.setFileUploadedYn("Y");

        int r1 = commonDAO.updateItem(recommendation, NAME_SPACE, "CustomRecommendationMapper", ".updateSelective");
        Recommendation result = commonDAO.queryForObject(NAME_SPACE + "CustomRecommendationMapper.selectByRecNo", recNo, Recommendation.class);

        if (r1 == 1) {
            ec.setResult(ExecutionContext.SUCCESS);

            // APPL_DOC 업데이트
            TotalApplicationDocument oneDocument = new TotalApplicationDocument();
            oneDocument.setApplNo(applNo);
            oneDocument.setDocTypeCode(multipartHttpServletRequest.getParameter("docTypeCode"));
            oneDocument.setDocGrp(recNo);
            oneDocument.setDocItemCode(multipartHttpServletRequest.getParameter("docItemCode"));
            oneDocument.setDocItemName(multipartHttpServletRequest.getParameter("docItemName"));
            oneDocument.setDocItemNameXxen(multipartHttpServletRequest.getParameter("docItemNameXxen"));
            oneDocument.setFileExt("pdf");
            oneDocument.setImgYn("N");
            oneDocument.setFilePath(s3MidPath + "/" + uploadDir + "/" + uploadFileName);
            oneDocument.setFileName(uploadFileName);
            oneDocument.setOrgFileName(multipartFile.getOriginalFilename());
            oneDocument.setPageCnt(fileInfo.getPageCnt());
            oneDocument.setFileUploadFg("Y".equals(multipartHttpServletRequest.getParameter("fileUploadedYn")));


            int rUpdate = 0, rInsert = 0, update=0, insert =0;

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
                oneDocument.setCreId(userId);
                oneDocument.setCreDate(date);
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

            // 메일 발송
            Mail mail = MailFactory.create(MailType.RECOMMENDATION_COMPLETED);
            mail.setInfo(result);
            mail.setInfoType(Recommendation.class);

            Application application =
                    commonDAO.queryForObject(NAME_SPACE + "CustomRecommendationMapper.selectApplicantMailByRecNo",
                            recNo, Application.class);
//            String applicantName = StringUtil.getEmptyIfNull(application.getKorName()).length() > 0 ?
//                    application.getKorName() :
//                    application.getEngName();
            String applicantName = application.getEngName() + "(" + application.getKorName() + ")";                    ;
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

    @Override
    public ExecutionContext retrieveUncompletedRecommendationList() {
        ExecutionContext ec = new ExecutionContext();
        List<Recommendation> uncompletedRecList =
                commonDAO.queryForList(NAME_SPACE + "CustomRecommendationMapper.selectUncompletedRecs", Recommendation.class);
        ec.setData(uncompletedRecList);
        return ec;
    }

    @Override
    public ExecutionContext sendUrgeMail(List<Recommendation> recommendationList) {
        ExecutionContext ec = new ExecutionContext();
        List<Mail> failedList = new ArrayList<Mail>();
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
                    .withContentsParam("instNameEN", INST_NAME_EN)
                    .withContentsParam("dueTime", REC_DUE_DATE);
            mailToProf.makeContents();
            if (!sendUrgeMail(mailToProf)) {
                failedList.add(mailToProf);
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
            int maxRecNo = commonDAO.queryForInt(NAME_SPACE + "CustomRecommendationMapper.selectMaxRecNoByApplNo", applNo) ;
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
     * @param mail
     * @return
     */
    private boolean sendUrgeMail(Mail mail) {

        boolean isSentToProf = false;

        try {
            sesMailService.sendMail(mail);
            isSentToProf = true;
        } catch (Exception e) {
            e.printStackTrace();
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
