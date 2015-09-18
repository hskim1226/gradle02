package com.apexsoft.ysprj.applicants.application.control;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.exception.ErrorInfo;
import com.apexsoft.framework.exception.StackTraceFilter;
import com.apexsoft.framework.exception.YSBizException;
import com.apexsoft.framework.mail.Mail;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.framework.persistence.file.callback.FileUploadEventCallbackHandler;
import com.apexsoft.framework.persistence.file.exception.FileNoticeException;
import com.apexsoft.framework.persistence.file.handler.FileHandler;
import com.apexsoft.framework.persistence.file.manager.FilePersistenceManager;
import com.apexsoft.framework.persistence.file.model.FileInfo;
import com.apexsoft.framework.persistence.file.model.FileItem;
import com.apexsoft.framework.persistence.file.model.FileMetaForm;
import com.apexsoft.ysprj.applicants.admission.service.AdmissionService;
import com.apexsoft.ysprj.applicants.application.domain.*;
import com.apexsoft.ysprj.applicants.application.service.DocumentService;
import com.apexsoft.ysprj.applicants.application.service.RecommendationService;
import com.apexsoft.ysprj.applicants.application.validator.RecommendationValidator;
import com.apexsoft.ysprj.applicants.common.util.CryptoUtil;
import com.apexsoft.ysprj.applicants.common.util.FileUtil;
import com.apexsoft.ysprj.applicants.common.util.StringUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.util.WebUtils;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by hanmomhanda on 15. 2. 14.
 */
@Controller
@RequestMapping(value="/application")
public class PreApplicationController {

    @Autowired
    private ObjectMapper jacksonObjectMapper;

    @Autowired
    private AdmissionService admissionService;

    @Autowired
    private DocumentService documentService;

    @Autowired
    private RecommendationService recommendationService;

    @Autowired
    private RecommendationValidator recommendationValidator;

    @Resource(name = "messageResolver")
    private MessageResolver messageResolver;

    @Autowired
    private ServletContext context;

    @Autowired
    private CommonDAO commonDAO;

    @Autowired
    private PasswordEncoder encoder;

    @Value("#{app['adms.general']}")
    private String admsGeneral;

    @Value("#{app['adms.foreign']}")
    private String admsForeign;

    @Value("#{app['adms.early']}")
    private String admsEarly;

    @Value("#{app['recommendation.notice.sendkey']}")
    private String SEND_KEY;

    private static final Logger logger = LoggerFactory.getLogger(PreApplicationController.class);
    /**
     * 공고 목록 화면
     *
     * @return
     */
    @RequestMapping(value = "/admsList", method = RequestMethod.GET)
    public ModelAndView showList() {
        ModelAndView mv = new ModelAndView("application/admsList");

        mv.addObject("admsGeneral", admissionService.retrieveAdmissionByAdmsNo(admsGeneral));
        mv.addObject("admsForeign", admissionService.retrieveAdmissionByAdmsNo(admsForeign));
        // 2016-01 은 조기 전형 없으므로 아래 행 주석 처리
//        mv.addObject("admsEarly", admissionService.retrieveAdmissionByAdmsNo(admsEarly));

        return mv;
    }

    /**
     * 일반 전형
     *
     * @return
     */
    @RequestMapping(value = "/general", method = RequestMethod.POST)
    public ModelAndView showGeneral() {
        ModelAndView mv = new ModelAndView("application/general");

        mv.addObject("admsGeneral", admissionService.retrieveAdmissionByAdmsNo(admsGeneral));

        return mv;
    }

    /**
     * 외국인 전형
     *
     * @return
     */
    @RequestMapping(value = "/foreign", method = RequestMethod.POST)
    public ModelAndView showForeign() {
        ModelAndView mv = new ModelAndView("application/foreign");

        mv.addObject("admsForeign", admissionService.retrieveAdmissionByAdmsNo(admsForeign));

        return mv;
    }

    /**
     * 조기 전형
     *
     * @return
     */
    @RequestMapping(value = "/early", method = RequestMethod.POST)
    public ModelAndView showEarly() {
        ModelAndView mv = new ModelAndView("application/early");

        mv.addObject("admsEarly", admissionService.retrieveAdmissionByAdmsNo(admsEarly));

        return mv;
    }

    /**
     * 내원서 화면
     * @param principal
     * @param mv
     * @return
     */
    @RequestMapping(value="/mylist")
    public ModelAndView myApplicationList(Principal principal, ModelAndView mv) {
        mv.setViewName("application/mylist");
        ParamForApplication parameter = new ParamForApplication();
        parameter.setUserId(principal.getName());

        List<CustomMyList> myList =
                commonDAO.queryForList("com.apexsoft.ysprj.applicants.application.sqlmap.CustomApplicationMapper.selectApplByUserId",
                    parameter, CustomMyList.class);

        mv.addObject("myList", myList);
        return mv;
    }

    /**
     * 원서 작성 동의 화면
     * SimpleForwardingController에서 이전
     * @return
     */
    @RequestMapping(value = "/agreement", method = RequestMethod.POST)
    public ModelAndView checkAgreement(@RequestParam(value = "admsNo") String admsNo,
                                       @RequestParam(value = "entrYear") String entrYear,
                                       @RequestParam(value = "admsTypeCode") String admsTypeCode) {
        ModelAndView mv = new ModelAndView("application/agreement");
        mv.addObject("admsNo", admsNo);
        mv.addObject("entrYear", entrYear);
        mv.addObject("admsTypeCode", admsTypeCode);
        return mv;
    }

    /**
     * 추천서 요청 목록
     *
     * @param basis
     * @param mv
     * @return
     */
    @RequestMapping(value = "/recReq/list")
    public ModelAndView recommendationList(Basis basis, ModelAndView mv) {
        mv.setViewName("application/recReqList");
        Application application = basis.getApplication();
        int applNo = application.getApplNo();

        ExecutionContext ec = recommendationService.retrieveRecommendationList(applNo);

        mv.addObject("recommendationList", ec.getData());
        mv.addObject("applNo", applNo);
        return mv;
    }

    /**
     * 추천서 요청 작성
     *
     * @param recommendation
     * @param mv
     * @return
     */
    @RequestMapping(value = "/recReq/edit", method = RequestMethod.POST)
    public ModelAndView recommendationEdit(Recommendation recommendation, ModelAndView mv) {
        mv.setViewName("application/recReqEdit");
        int recNo = recommendation.getRecNo();
        if (recNo > 0) {
            ExecutionContext ec = recommendationService.retrieveRecommendation(recommendation.getRecNo());
            mv.addObject("recommendation", ec.getData());
        } else {
            mv.addObject("recommendation", recommendation);
        }

        return mv;
    }

    /**
     * 작성 중인 추천서 미리보기
     *
     * @param recommendation
     * @return
     */
    @RequestMapping(value = "/recReq/preview", method = RequestMethod.POST)
    @ResponseBody
    public ExecutionContext recommendationPreview(Recommendation recommendation) {

        ExecutionContext ec = recommendationService.previewRecommendation(recommendation);
        Mail preview = (Mail) ec.getData();

        String json = null;
        try {
            json = jacksonObjectMapper.writeValueAsString(preview);
        } catch (JsonProcessingException e) {
            throw new YSBizException(e);
        }

        ec.setData(json);

        return ec;
    }

    /**
     * 추천서 요청 저장
     *
     * @param recommendation
     * @param bindingResult
     * @param mv
     * @return
     */
    @RequestMapping(value = "/recReq/save", method = RequestMethod.POST)
    public ModelAndView recommendationSave(Recommendation recommendation,
                                           BindingResult bindingResult,
                                           Principal principal,
                                           ModelAndView mv) {

        recommendationValidator.validate(recommendation, bindingResult);
        mv.setViewName("application/recReqEdit");
        if (bindingResult.hasErrors()) {
            mv.addObject("resultMsg", MessageResolver.getMessage("U334"));
            return mv;
        }

        recommendation.setModId(principal.getName());
        ExecutionContext ec = recommendationService.saveRecommendationRequest(recommendation);

        Recommendation result = (Recommendation)ec.getData();
        mv.addObject("recommendation", result);
        mv.addObject("resultMsg", ec.getMessage());

        return mv;
    }

    /**
     * 추천서 요청 취소 -> DB 에서 삭제
     *
     * @param recommendation
     * @param mv
     * @return
     */
    @RequestMapping(value = "/recReq/cancel", method = RequestMethod.POST)
    public ModelAndView recommendationCancel(Recommendation recommendation, ModelAndView mv) {
        mv.setViewName("application/recReqList");

        ExecutionContext ec = recommendationService.deleteRecommendationRequest(recommendation);
        ExecutionContext ec2 = recommendationService.retrieveRecommendationList(recommendation.getApplNo());

        mv.addObject("recommendationList", ec2.getData());
        mv.addObject("resultMsg", ec.getMessage());
        mv.addObject("applNo", recommendation.getApplNo());

        return mv;
    }

    /**
     * 교수에게 추천서 요청 메일 발송
     *
     * @param recommendation
     * @param bindingResult
     * @param mv
     * @return
     */
    @RequestMapping(value = "/recReq/send", method = RequestMethod.POST)
    public ModelAndView recommendationSend(Recommendation recommendation, BindingResult bindingResult,
                                           Principal principal, ModelAndView mv) {
        recommendationValidator.validate(recommendation, bindingResult);
        mv.setViewName("application/recReqList");
        if (bindingResult.hasErrors()) {
            mv.addObject("resultMsg", MessageResolver.getMessage("U334"));
            return mv;
        }


        recommendation.setModId(principal.getName());

        // DB 저장 후 메일 보내기
        ExecutionContext ec = recommendationService.sendRecommendationRequest(recommendation);
        ExecutionContext ec2 = recommendationService.retrieveRecommendationList(recommendation.getApplNo());

        mv.addObject("recommendationList", ec2.getData());
        mv.addObject("resultMsg", ec.getMessage());
        mv.addObject("applNo", recommendation.getApplNo());

        return mv;
    }

    @RequestMapping(value = "/recReq/resend", method = RequestMethod.POST)
    public ModelAndView resendRecommendationRequest(Recommendation recommendation,
                                                    BindingResult bindingResult,
                                                    Principal principal,
                                                    ModelAndView mv) {
        mv.setViewName("application/recReqList");
        if (bindingResult.hasErrors()) {
            mv.addObject("resultMsg", MessageResolver.getMessage("U334"));
            return mv;
        }
        recommendation.setModId(principal.getName());

        ExecutionContext ec = recommendationService.sendRecommendationRequest(recommendation);
        ExecutionContext ec2 = recommendationService.retrieveRecommendationList(recommendation.getApplNo());

        mv.addObject("recommendationList", ec2.getData());
        mv.addObject("resultMsg", ec.getMessage());
        mv.addObject("applNo", recommendation.getApplNo());

        return mv;
    }

    /**
     * 교수가 보는 추천서 등록 화면
     *
     * @param key
     * @param mv
     * @return
     */
    @RequestMapping(value = "/recommend", method = RequestMethod.GET)
    public ModelAndView recommendationForm(@RequestParam(value = "key") String key,
                                           @RequestParam(value = "lang") String lang,
                                           HttpServletRequest request,
                                           ModelAndView mv) {

        if (lang != null) {
            WebUtils.setSessionAttribute(request, SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, new Locale(lang));
        }
        String decrypted = null;
        // key 복호화해서 추천서 정보 추출
        try {
            decrypted = CryptoUtil.getCryptedString(context, key, false);
        } catch (Exception e) {
            mv.setViewName("application/recNotice");
            mv.addObject("title", messageResolver.getMessage("L06901"));
            mv.addObject("notice", messageResolver.getMessage("U06901"));
            return mv;
        }

        // 복호화해서 recNo 로 추천서 DB 조회
        Recommendation recommendation = new Recommendation();
        recommendation.setRecNo(Integer.parseInt(decrypted.split(";")[0]));
        ExecutionContext ec0 = recommendationService.openRecommendationRequestByProfessor(recommendation);
        ExecutionContext ec = recommendationService.retrieveRecommendationByProfessor(recommendation);
        ExecutionContext ec1 = recommendationService.retrieveDocInfo();
        RecommendationDocument recDocInfo = (RecommendationDocument) ec1.getData();
        recDocInfo.setDocTypeCode("00007");

        // 추천서 상태가 요청발송, 교수확인이면 등록화면으로 등록완료면 등록 완료 안내 화면으로
        if (ExecutionContext.SUCCESS.equals(ec.getResult())) {
            RecommendationApplicationInfo recApplInfo = (RecommendationApplicationInfo)ec.getData();
            if (RecommendStatus.SENT.codeVal().equals(recApplInfo.getRecStsCode())
                    || RecommendStatus.OPENED.codeVal().equals(recApplInfo.getRecStsCode())) {
                mv.setViewName("application/formRecommendation");
                mv.addObject("applInfo", ec.getData());
                mv.addObject("applicantId", recApplInfo.getUserId());
                mv.addObject("recKey", key);
                mv.addObject("fileUploadedYn", recApplInfo.getFileUploadedYn());

                mv.addObject("docTypeCode", recDocInfo.getDocTypeCode());
                mv.addObject("docItemCode", recDocInfo.getDocItemCode());
                mv.addObject("docItemName", recDocInfo.getDocItemName());
                mv.addObject("docItemNameXxen", recDocInfo.getDocItemNameXxen());
            } else if (RecommendStatus.COMPLETED.codeVal().equals(recApplInfo.getRecStsCode())) {
                mv.setViewName("application/recNotice");
                mv.addObject("title", messageResolver.getMessage("L06902"));
                mv.addObject("notice", messageResolver.getMessage("U06902"));
            }
        }

        return mv;
    }

//    @RequestMapping(value = "/recommend/fileupload", method = RequestMethod.POST)
//    @ResponseBody
//    public String uploadLetterOfRecommendation(Recommendation recommendation, FileMetaForm fileMetaForm,
//                                               MultipartHttpServletRequest request,
//                                                     BindingResult bindingResult,
//                                                     Principal principal, ModelAndView mv) {
//
//        return "";
//    }
    /**
     * 파일 업로드
     * 개별 파일 단위로 물리적 업로드 및 파일 업로드 테이블에도 저장
     *
     * @param document
     * @param binding
     * @param principal
     * @param fileHandler
     * @return
     */
    @RequestMapping(value = "/recommend/fileupload", method = RequestMethod.POST)
    @ResponseBody
    public ExecutionContext uploadLetterOfRecommendation(Document document,
                                                         BindingResult binding,
                                                         final Principal principal,
                                                         HttpServletRequest request,
                                                         FileHandler fileHandler) {

        ExecutionContext ec = new ExecutionContext();
        String returnFileMetaForm = "";
        try {
            returnFileMetaForm = fileHandler.handleMultiPartRequest(new FileUploadEventCallbackHandler<String, FileMetaForm, TotalApplicationDocument>() {
                /**
                 * target 폴더 반환
                 *
                 * @param fileMetaForm
                 *
                 * @returnattribute
                 */
                @Override
                protected String getDirectory(FileMetaForm fileMetaForm) {

                    String admsNo = fileMetaForm.getAdmsNo();
                    String userId = fileMetaForm.getApplicantId();
                    String applNo = fileMetaForm.getApplNo();

                    return FileUtil.getUploadDirectory(admsNo, userId, Integer.parseInt(applNo));
                }

                /**
                 * 실제 저장될 파일 이름 반환
                 *
                 * @return
                 */
                @Override
                protected String createFileName(FileMetaForm fileMetaForm, FileItem fileItem) {
                    return fileMetaForm.getFieldName() + "-" + StringUtil.reverseSlashToSlash(fileItem.getOriginalFileName());
                }

                /**
                 * 파일 업로드와 첨부 파일 관련 정보 DB 저장
                 * @param fileItems
                 * @param fileMetaForm
                 * @param persistence
                 * @param document
                 * @return
                 */
                @Override
                public String handleEvent(List<FileItem> fileItems,
                                          FileMetaForm fileMetaForm,
                                          FilePersistenceManager persistence,
                                          TotalApplicationDocument document) {
                    ExecutionContext ec = null;
                    String jsonFileMetaForm = null;
                    FileInfo fileInfo = null;
//                    String uploadDir = getDirectory(fileMetaForm);
//                    String uploadFileName = "";
                    for ( FileItem fileItem : fileItems){
                        // FOR MANAGE
//                        if (fileItem.getFile().length() > MAX_LENGTH) {
//                            ec = new ExecutionContext(ExecutionContext.FAIL);
//                            Map<String, String> errorInfo = new HashMap<String, String>();
//                            errorInfo.put("applNo", String.valueOf(document.getApplNo()));
//                            ec.setErrorInfo(new ErrorInfo(errorInfo));
//                            throw new FileNoticeException(ec, "U04301", "ERR0060");
//                        }
                        // FOR MANAGE
                        if (fileItem.getOriginalFileName().toLowerCase().endsWith("pdf")) {
                            PDDocument pdf = null;
                            try {
                                pdf = PDDocument.load(fileItem.getFile());

                                if (pdf.isEncrypted()) {
                                    ec = new ExecutionContext(ExecutionContext.FAIL);
                                    Map<String, String> errorInfo = new HashMap<String, String>();
                                    errorInfo.put("applNo", String.valueOf(document.getApplNo()));
                                    errorInfo.put("originalFileName", fileItem.getOriginalFileName());
                                    ec.setErrorInfo(new ErrorInfo(errorInfo));
                                    throw new FileNoticeException(ec, "U04514", "ERR0060");
                                }
                            } catch (IOException e) {
                                logger.error("Upload PDF is NOT loaded to PDDocument, DocumentController.fileUpload()");
                                logger.error("modId : " + document.getModId());
                                logger.error("applNo: " + document.getApplNo());
                                ec = new ExecutionContext(ExecutionContext.FAIL);
                                Map<String, String> errorInfo = new HashMap<String, String>();
                                errorInfo.put("applNo", String.valueOf(document.getApplNo()));
                                errorInfo.put("originalFileName", fileItem.getOriginalFileName());
                                ec.setErrorInfo(new ErrorInfo(errorInfo));
                                throw new FileNoticeException(ec, "U04514", "ERR0060");
                            } finally {
                                if (pdf != null) {
                                    try {
                                        pdf.close();
                                    } catch (IOException e) {
                                        logger.error("PDF is NOT closed, DocumentController.fileUpload()");
                                    }
                                }
                            }
                        }

                        FileInputStream fis = null;
                        String originalFileName = fileItem.getOriginalFileName();

                        try{
                            String uploadDir = getDirectory(fileMetaForm);
                            String uploadFileName = createFileName(fileMetaForm, fileItem);
                            try {
                                fileInfo = persistence.save(uploadDir, uploadFileName, originalFileName,
                                        fis = new FileInputStream(fileItem.getFile())
                                );
                            } catch (IOException ioe) {
                                throw getYSBizException(document, fileMetaForm.getApplicantId(), "U339", "ERR0063");
                            }

                            String path = fileInfo.getDirectory();

                            fileMetaForm.setPath(path);
                            fileMetaForm.setFileName(fileInfo.getFileName());
                            fileMetaForm.setOriginalFileName(originalFileName);

                            document.setFilePath(fileInfo.getDirectory());
                            document.setFileName(fileInfo.getFileName());
                            document.setOrgFileName(originalFileName);
                            document.setFileExt(originalFileName.substring(originalFileName.lastIndexOf('.') + 1));
                            document.setPageCnt(fileInfo.getPageCnt());
                            document.setModId(fileMetaForm.getApplicantId());
                            document.setFileUploadFg("Y".equals(fileMetaForm.getFileUploadedYn()) ? true : false);
                            ec = documentService.saveOneDocument(document);

                            if (ExecutionContext.SUCCESS.equals(ec.getResult())) {
                                fileMetaForm.setTotalApplicationDocument((TotalApplicationDocument)ec.getData());
                                fileMetaForm.setResultMessage(MessageResolver.getMessage("U348"));
                            } else {
                                fileMetaForm.setResultMessage(MessageResolver.getMessage("U339"));
                            }

                            jsonFileMetaForm = jacksonObjectMapper.writeValueAsString(fileMetaForm);

                        } catch (AmazonServiceException ase) {
                            ec = new ExecutionContext(ExecutionContext.FAIL);
                            ec.setMessage(MessageResolver.getMessage("U339"));
                            ec.setErrCode("ERR0052");
                            Map<String, String> errorInfo = new HashMap<String, String>();
                            errorInfo.put("userId", String.valueOf(fileMetaForm.getApplicantId()));
                            errorInfo.put("applNo", String.valueOf(document.getApplNo()));
                            errorInfo.put("docSeq", String.valueOf(document.getDocSeq()));
                            errorInfo.put("AWS Error Message", ase.getMessage());
                            errorInfo.put("AWS HTTP Status Code", String.valueOf(ase.getStatusCode()));
                            errorInfo.put("AWS HTTP Error Code", String.valueOf(ase.getErrorCode()));
                            errorInfo.put("AWS Error Type", ase.getErrorType().toString());
                            errorInfo.put("AWS Request ID", ase.getRequestId());
                            ec.setErrorInfo(new ErrorInfo(errorInfo));
                            throw new YSBizException(ec);
                        } catch (AmazonClientException ace) {
                            ec = new ExecutionContext(ExecutionContext.FAIL);
                            ec.setMessage(MessageResolver.getMessage("U339"));
                            ec.setErrCode("ERR0052");
                            Map<String, String> errorInfo = new HashMap<String, String>();
                            errorInfo.put("applNo", String.valueOf(document.getApplNo()));
                            errorInfo.put("docSeq", String.valueOf(document.getDocSeq()));
                            errorInfo.put("AWS Error Message", ace.getMessage());
                            ec.setErrorInfo(new ErrorInfo(errorInfo));
                            throw new YSBizException(ec);
                        } catch (Exception e) {
                            logger.error("S3 저장시 아마존 예외 외의 오류");
                            logger.error(e.getMessage());
                            e.printStackTrace();
                            throw getYSBizException(document, fileMetaForm.getApplicantId(), "U339", "ERR0052");
                        }finally {
                            try {
                                if (fis!= null) fis.close();
                            } catch (IOException e) {}
                        }
                    }

                    return jsonFileMetaForm;
                }
            }, FileMetaForm.class, TotalApplicationDocument.class);
        } catch (YSBizException ybe) {
            logger.error("ErrorInfo :: " + ybe.getExecutionContext().getErrorInfo().toString());
            logger.error("ErrorType :: " + ybe.toString());
            logger.error("SimpleStackTrace ::" +
                    StackTraceFilter.getFilteredCallStack(ybe.getStackTrace(), "com.apexsoft", false));
            ec = ybe.getExecutionContext();
        }

        ec.setData(returnFileMetaForm);

        return ec;
    }

    /**
     * 교수가 추천서 등록화면에서 등록 완료
     *
     * @param recommendation
     * @param mv
     * @return
     */
    @RequestMapping(value = "/recommend", method = RequestMethod.POST)
    public ModelAndView recommendationSave(Recommendation recommendation,
                                           MultipartHttpServletRequest multipartHttpServletRequest,
                                           @RequestParam("fileRec") MultipartFile file,
                                           ModelAndView mv) {
        mv.setViewName("application/recNotice");

        // 파일 업로드


        // 추천 상태 변경 DB 반영 및 지원자에게 메일 발송 처리
        ExecutionContext ec = recommendationService.registerRecommendationByProfessor(multipartHttpServletRequest, file, recommendation);

        mv.addObject("title", messageResolver.getMessage("L06902"));
        mv.addObject("notice", messageResolver.getMessage("U06902"));

        return mv;
    }

    @RequestMapping(value = "/sendUrgeRecommendationMail", method = RequestMethod.GET)
    public void sendUrgeRecommendationMail(@RequestParam(value = "sendkey") String sendKey) {
        // 독려 메일 발송 키 확인
        if (SEND_KEY.equals(sendKey)) {
            // APPL_REC 테이블에서 REC_STS_CODE 가 00002(요청완료), 00003(교수확인)인 목록 조회
            ExecutionContext ec = recommendationService.retrieveUncompletedRecommendationList();
            if (ExecutionContext.SUCCESS.equals(ec.getResult())) {
                List<Recommendation> uncompletedRecList = (List<Recommendation>) ec.getData();
                ExecutionContext ec1 = recommendationService.sendUrgeMail(uncompletedRecList);
            }
            // 목록 반복 돌면서 교수, 학생에게 메일 발송
            // 발송 오류시 파일로그 또는 DB에 기록
            // 발송 결과 관리자에게 메일 발송
        } else {
            // 어드민 아니면 파일로그에 남기고 종료
        }
    }

    /**
     * 파일 핸들링 시 발생하는 예외에 대한 YSBizException Wrapper
     *
     * @param document
     * @param userId
     * @param messageCode
     * @param errCode
     * @return
     */
    private YSBizException getYSBizException( TotalApplicationDocument document, String userId,
                                              String messageCode, String errCode ) {
        ExecutionContext ec = new ExecutionContext(ExecutionContext.FAIL);
        String applNo = String.valueOf(document.getApplNo());
        String docSeq = String.valueOf(document.getDocSeq());
        String docItemCode = document.getDocItemCode();
        String docItemName = document.getDocItemName();
        Map<String, String> eInfo = new HashMap<String, String>();
        eInfo.put("applNo", applNo);
        eInfo.put("userId", userId);
        eInfo.put("docSeq", docSeq);
        eInfo.put("docItemCode", docItemCode);
        eInfo.put("docItemName", docItemName);
        ec.setMessage(MessageResolver.getMessage(messageCode));
        ec.setErrCode(errCode);
        ec.setErrorInfo(new ErrorInfo(eInfo));
        return new YSBizException(ec);
    }
}
