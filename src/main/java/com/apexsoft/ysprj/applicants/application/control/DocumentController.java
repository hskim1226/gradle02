package com.apexsoft.ysprj.applicants.application.control;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.exception.ErrorInfo;
import com.apexsoft.framework.exception.StackTraceFilter;
import com.apexsoft.framework.exception.YSBizException;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.framework.persistence.file.callback.FileUploadEventCallbackHandler;
import com.apexsoft.framework.persistence.file.exception.*;
import com.apexsoft.framework.persistence.file.handler.FileHandler;
import com.apexsoft.framework.persistence.file.manager.FilePersistenceManager;
import com.apexsoft.framework.persistence.file.model.FileInfo;
import com.apexsoft.framework.persistence.file.model.FileItem;
import com.apexsoft.framework.persistence.file.model.FileMetaForm;
import com.apexsoft.ysprj.applicants.application.domain.*;
import com.apexsoft.ysprj.applicants.application.service.DocumentService;
import com.apexsoft.ysprj.applicants.application.validator.DocumentValidator;
import com.apexsoft.ysprj.applicants.common.service.BirtService;
import com.apexsoft.ysprj.applicants.common.service.FilePersistenceService;
import com.apexsoft.ysprj.applicants.common.service.PDFService;
import com.apexsoft.ysprj.applicants.common.util.FilePathUtil;
import com.apexsoft.ysprj.applicants.common.util.StringUtil;
import com.apexsoft.ysprj.applicants.common.util.WebUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.io.FileUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.net.URLEncoder;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.DataFormatException;

/**
 * Created by hanmomhanda on 15. 1. 26.
 *
 * 원서 어학/경력 정보 컨트롤러
 */
@Controller
@RequestMapping(value="/application/document")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @Autowired
    private FilePersistenceService filePersistenceService;

    @Autowired
    private BirtService birtService;

    @Autowired
    private PDFService pdfService;

    @Autowired
    private DocumentValidator documentValidator;

    @Autowired
    private LocaleResolver localeResolver;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("#{app['file.baseDir']}")
    private String fileBaseDir;

    @Value("#{app['constraint.dueTime.yyyyMMddhhmmss']}")
    private String dueTime;

    @Value("#{app['file.prefix']}")
    private String filePrefix;

    @Value("#{app['file.maxSize']}")
    private String fileMaxSize;

    @Value("#{app['file.maxPage']}")
    private String fileMaxPage;

    @Value("#{app['site.tel']}")
    private String siteTel;

    @Value("#{app['site.helpdesk']}")
    private String helpdeskMail;

    @Autowired
    WebUtil webUtil;

    private static final Logger logger = LoggerFactory.getLogger(DocumentController.class);
    private final String TARGET_VIEW = "application/document";

//    /**
//     * 제출 문서 정보를 조회해서 파일 첨부 화면에 뿌려질 데이터 구성
//     *
//     * @param applicationIdentifier
//     * @return
//     */
//    private ExecutionContext setupDocument(ApplicationIdentifier applicationIdentifier) {
//        ExecutionContext ec;
//
//        Map<String, Object> ecDataMap = new HashMap<String, Object>();
//        Map<String, Object> commonCodeMap = new HashMap<String, Object>();
//        Document document;
//
//        int applNo = applicationIdentifier.getApplNo();
//        String admsNo = applicationIdentifier.getAdmsNo();
//        String entrYear = applicationIdentifier.getEntrYear();
//        String admsTypeCode = applicationIdentifier.getAdmsTypeCode();
//
//        ec = documentService.retrieveDocument(applNo);
//        document = (Document)ec.getData();
//        Application application = document.getApplication();
//
//        ParamForAdmissionCourseMajor param = new ParamForAdmissionCourseMajor();
//        param.setAdmsNo(admsNo);
//        param.setDeptCode(application.getDeptCode());
//        param.setCorsTypeCode(application.getCorsTypeCode());
//        param.setDetlMajCode(application.getDetlMajCode());
//
//        ecDataMap.put("document", document);
//        ecDataMap.put("common", commonCodeMap);
//        ec.setData(ecDataMap);
//
//        return ec;
//    }

    /**
     * 첨부 파일 최초 작성 및 수정 화면
     *
     * @param formData
     * @param bindingResult
     * @param mv
     * @return
     */
    @RequestMapping(value="/edit")
    public ModelAndView getDocument(@ModelAttribute Document formData,
                                    BindingResult bindingResult,
                                    HttpServletRequest request,
                                    Principal principal,
                                    ModelAndView mv) {
        webUtil.blockGetMethod(request, formData.getApplication());
        mv.setViewName(TARGET_VIEW);
        if (bindingResult.hasErrors()) {
            setFileLimit(mv);
            return mv;
        }

        mv.addObject("isSYSADMIN", "Apex1234".equals(principal.getName()));

        ExecutionContext ec = documentService.retrieveDocument(formData);

        Map<String, Object> map = (Map<String, Object>)ec.getData();
        addObjectToMV(mv, map, ec);
        mv.addObject("filePrefix", filePrefix);
        setFileLimit(mv);
        return mv;
    }

    /**
     * 첨부 파일 저장
     * 실제 물리적 저장 및 DB 저장은 fileupload에서 건별로 처리되고
     * 여기서는 필수서류에 대한 validation과 validation 통과 시 applStsCode만 변경함
     *
     * @param formData
     * @param principal
     * @param bindingResult
     * @param mv
     * @return
     */
    @RequestMapping(value="/save", method = RequestMethod.POST)
    public ModelAndView saveDocument(@ModelAttribute Document formData,
                                     Principal principal,
                                     BindingResult bindingResult,
                                     HttpServletRequest request,
                                     ModelAndView mv) {
        webUtil.blockGetMethod(request, formData.getApplication());
        webUtil.blockGetMethod(request, formData.getApplication().getDocChckYn());
        documentValidator.validate(formData, bindingResult, localeResolver.resolveLocale(request));
        mv.setViewName(TARGET_VIEW);
        if (bindingResult.hasErrors()) {
            mv.addObject("resultMsg", MessageResolver.getMessage("U334"));
            setFileLimit(mv);
            return mv;
        }

        ExecutionContext ec = null;

        ec = documentService.saveDocument(formData);

        if (ExecutionContext.SUCCESS.equals(ec.getResult())) {
            ExecutionContext ecRetrieve = documentService.retrieveDocument(formData);

            if (ExecutionContext.SUCCESS.equals(ecRetrieve.getResult())) {
                Map<String, Object> setupMap = (Map<String, Object>)ecRetrieve.getData();
                addObjectToMV(mv, setupMap, ec);
            } else {
                mv = getErrorMV("common/error", ecRetrieve);
            }
        } else {
            mv = getErrorMV("common/error", ec);
        }
        mv.addObject("isSYSADMIN", "Apex1234".equals(principal.getName()));
        setFileLimit(mv);
        return mv;
    }

    /**
     * 원서 작성 완료
     *
     * @param formData
     * @param principal
     * @param bindingResult
     * @param mv
     * @return
     */
    @RequestMapping(value="/submit", method = RequestMethod.POST)
    public ModelAndView submitApplication(@ModelAttribute Document formData,
                                          Principal principal,
                                          BindingResult bindingResult,
                                          HttpServletRequest request,
                                          ModelAndView mv) {
        webUtil.blockGetMethod(request, formData.getApplication());

        // FOR MANAGE
        String adminID = principal.getName();
        if (adminID.equals("Apex1234") || adminID.startsWith("Yssub")) {

        } else {
            DateTime now = new DateTime();
            DateTimeZone seoul = DateTimeZone.forID("Asia/Seoul");
            int year = Integer.parseInt(dueTime.substring(0, 4));
            int month = Integer.parseInt(dueTime.substring(4, 6));
            int date = Integer.parseInt(dueTime.substring(6, 8));
            int hour = Integer.parseInt(dueTime.substring(8, 10));
            int min = Integer.parseInt(dueTime.substring(10, 12));
            int sec = Integer.parseInt(dueTime.substring(12, 14));
//            DateTime dueTime = new DateTime(2015, 4, 10, 18, 50, 3, seoul);
            DateTime dueTime = new DateTime(year, month, date, hour, min, sec, seoul);

            Application tApplication = formData.getApplication();
            String tUserId = tApplication != null ? tApplication.getUserId() : "APPLICATION IS NULL";
            int tApplNo = tApplication != null ? tApplication.getApplNo() : -1;
            if (now.isAfter(dueTime)) {
                logger.error("DUE : " + dueTime);
                logger.error("NOW : " + now);
                logger.error("STATUS LATE");
                logger.error("APPL STATUS CODE : " + tApplication.getApplStsCode());
                logger.error("userId : [" + tUserId + "], " + "applNo : [" + tApplNo + "]" );
                ExecutionContext ec = new ExecutionContext(ExecutionContext.FAIL);
                ec.setMessage(MessageResolver.getMessage("U04517"));
                ec.setErrCode("ERR3011");

                throw new YSBizException(ec);
            }
        }
        // FOR MANAGE

        documentValidator.validate(formData, bindingResult, localeResolver.resolveLocale(request));
        mv.setViewName("application/mylist");
        if (bindingResult.hasErrors()) {
            mv.setViewName("application/document");
            mv.addObject("resultMsg", MessageResolver.getMessage("U334"));
            setFileLimit(mv);
            return mv;
        }

        ExecutionContext ec = null;
        Application application = formData.getApplication();
        int applNo = application.getApplNo();

        application = documentService.getApplication(applNo);

        ExecutionContext ecSaveInfo = documentService.saveApplicationPaperInfo(application);
        // 타 대학원 확장 시 TODO - 학교 이름을 파라미터로 받도록
        // 미리보기 생성 시 원서 생성하므로 제출 시에는 다시 생성하지 않음
//        String admsTypeCode = application.getAdmsTypeCode();
//        String lang = application.isForeignAppl() ? "en" : "kr";
//        String reportName = "yonsei-" + "appl" + "-" + lang;
//        ExecutionContext ecGenerate = birtService.generateBirtFile(application.getApplNo(), reportName);

        try {
            ExecutionContext ec1 = pdfService.genAndUploadPDFByApplicants(application);
            if (ExecutionContext.SUCCESS.equals(ec1.getResult())) { // 지원자 제출 서류 처리 성공일때만 제출
                String userId = application.getUserId();

                application.setModId(userId);

                ec = documentService.submit(formData);

                if (ExecutionContext.SUCCESS.equals(ec.getResult())) {

                    ParamForApplication p = new ParamForApplication();
                    p.setUserId(userId);
                    ExecutionContext ecRetrieve = documentService.retrieveInfoListByParamObj(p, "CustomApplicationMapper.selectApplByUserId", CustomMyList.class);

                    if (ExecutionContext.SUCCESS.equals(ecRetrieve.getResult())) {
                        mv.addObject("myList", ecRetrieve.getData());
                        mv.addObject("resultMsg", ec.getMessage());
                    } else {
                        mv = getErrorMV("common/error", ecRetrieve);
                    }
                } else {
                    mv = getErrorMV("common/error", ec);
                }
            } else { // 파일 합치기 실패하면
                ec = new ExecutionContext(ExecutionContext.FAIL);
                ec.setMessage(MessageResolver.getMessage("U06103"));
                ec.setErrCode("ERR1104");
                Map<String, String> errorInfo = new HashMap<String, String>();
                errorInfo.put("applNo", String.valueOf(applNo));
                ec.setErrorInfo(new ErrorInfo(errorInfo));
                throw new YSBizException(ec);
            }
        } catch (PDFMergeException e) { // 파일 합치기 실패하면
            ec = new ExecutionContext(ExecutionContext.FAIL);
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(MessageResolver.getMessage("U06104"));
            ec.setErrCode("ERR1104");
            Map<String, String> errorInfo = new HashMap<String, String>();
            errorInfo.put("applNo", String.valueOf(applNo));
            ec.setErrorInfo(new ErrorInfo(errorInfo));
//            throw new YSBizException(ec);

            ExecutionContext ecR = documentService.retrieveDocument(formData);
            ecR.setResult(ExecutionContext.FAIL);
            Map<String, Object> map = (Map<String, Object>)ecR.getData();
            mv.setViewName(TARGET_VIEW);
            addObjectToMV(mv, map, ecR);
            mv.addObject("resultMsg", ec.getMessage());

            return mv;
        }
        mv.addObject("isSYSADMIN", "Apex1234".equals(principal.getName()));
        setFileLimit(mv);
        return mv;
    }

    private void setFileLimit(ModelAndView mv) {
        mv.addObject("fileMaxSize", Integer.parseInt(fileMaxSize) * 1024 * 1024);
        mv.addObject("fileMaxPage", fileMaxPage);
    }

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
    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
    @ResponseBody
    public ExecutionContext fileUpload(Document document,
                                       BindingResult binding,
                                       final Principal principal,
                                       HttpServletRequest request,
                                       MultipartHttpServletRequest multipartHttpServletRequest,
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
                    String userId = fileMetaForm.getUserId();
                    String applNo = fileMetaForm.getApplNo();

                    return FilePathUtil.getUploadDirectory(admsNo, userId, Integer.parseInt(applNo));
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

                    for ( FileItem fileItem : fileItems){

                        // 파일 크기 제한 체크
                        checkFileSize(fileItem, document);

                        if (fileItem.isPDF()) {
                            // 페이지 제한 체크
                            checkFilePage(fileItem, document);

                            // 넘버링 가능 여부 테스트
                            checkNumbering(fileItem, fileMetaForm);
                        }

                        FileInputStream fis = null;

                        // PDF 합치지 않고 넘버링만 하기로 하여 주석 처리
//                        mergeFile(document, fileItem);

                        try{
                            // 파일의 물리적 저장
                            fileInfo = persist(fileMetaForm, fileItem, persistence, document);

                            // 파일 정보 DB 저장
                            ExecutionContext ec1 = saveToDB(fileInfo, fileMetaForm, fileItem, document);

                            // 저장 결과 fileMetaForm
                            FileMetaForm resultMeta = getResultFileMetaForm(fileMetaForm, ec1);

                            // json 문자열로 변환
                            jsonFileMetaForm = objectMapper.writeValueAsString(resultMeta);

                        } catch (AmazonServiceException ase) {
                            ec = new ExecutionContext(ExecutionContext.FAIL);
                            ec.setMessage(MessageResolver.getMessage("U339"));
                            ec.setErrCode("ERR0052");
                            Map<String, String> errorInfo = new HashMap<String, String>();
                            errorInfo.put("userId", String.valueOf(fileMetaForm.getUserId()));
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
                        } catch (EncryptedPDFException e) {
                            throw e;
                        } catch (PasswordedPDFException e) {
                            throw e;
                        } catch (WrongFileFormatException e) {
                            throw e;
                        } catch (Exception e) {
                            logger.error("S3 저장시 아마존 예외 외의 오류 : " + e.getMessage());
                            throw getYSBizException(document, principal, "U339", "ERR0052");
                        }finally {
                            File tmpFile = fileItem.getFile();
                            if (tmpFile != null) tmpFile.delete();
                            try {
                                if (fis!= null) fis.close();
                            } catch (IOException e) {}
                        }
                    }

                    return jsonFileMetaForm;
                }

                private void checkFileSize(FileItem fileItem, TotalApplicationDocument document) {
                    if (fileItem.getFile().length() > Integer.parseInt(fileMaxSize) * 1024 * 1024) {
                        ExecutionContext ec = new ExecutionContext(ExecutionContext.FAIL);
                        Map<String, String> errorInfo = new HashMap<String, String>();
                        errorInfo.put("applNo", String.valueOf(document.getApplNo()));
                        ec.setErrorInfo(new ErrorInfo(errorInfo));
                        throw new FileNoticeException(ec,
                                MessageResolver.getMessage("U04524", new Object[] { fileMaxSize }),
                                "ERR0060");
                    }
                }

                private void checkFilePage(FileItem fileItem, TotalApplicationDocument document) {
                    if (!pdfService.checkPageLimit(fileItem.getFile(), document.getApplNo(), Integer.parseInt(fileMaxPage))) {
                        ExecutionContext ec = new ExecutionContext(ExecutionContext.FAIL);
                        Map<String, String> errorInfo = new HashMap<String, String>();
                        errorInfo.put("applNo", String.valueOf(document.getApplNo()));
                        ec.setErrorInfo(new ErrorInfo(errorInfo));
                        throw new FileNoticeException(ec,
                                MessageResolver.getMessage("U04533", new Object[] { fileMaxPage }),
                                "ERR0064");
                    }
                }

                private void checkNumbering(FileItem fileItem, FileMetaForm fileMetaForm) {

                    File aFile = fileItem.getFile(); // tomcat의 temp 폴더에 있는 임시 파일
                    File tmpDir = FileUtils.getTempDirectory();
                    File copiedFile= new File(tmpDir + "/" + "copy-" + aFile.getName());
                    try {
                        FileUtils.copyFile(aFile, copiedFile);
                    } catch (IOException e) {
                        System.err.println("Exception in 파일 넘버링 체크");
                        e.printStackTrace();
                        Map<String, String> errMap = new HashMap<>();
                        errMap.put("applNo", String.valueOf(fileMetaForm.getApplNo()));
                        errMap.put("fileName", fileItem.getOriginalFileName());
                        ExecutionContext ec = new ExecutionContext(ExecutionContext.FAIL);
                        ec.setErrorInfo(new ErrorInfo(errMap));
                        throw new FileNoticeException(ec,
                                MessageResolver.getMessage("U04534"),
                                "ERR0065");
                    }

                    // 모든 페이지에 대한 넘버링을 테스트 할 필요가 있냐/없냐에서
                    // 있다로 판단해서 아래의 메서드로 전체 페이지에 대한 넘버링 테스트 수행
                    pdfService.getPageNumberedPDF(copiedFile, Integer.parseInt(fileMetaForm.getApplNo()));
                    copiedFile.delete();
                }

                private FileInfo persist(FileMetaForm fileMetaForm, FileItem fileItem,
                                     FilePersistenceManager persistence,
                                     TotalApplicationDocument document) {
                    String uploadDir = getDirectory(fileMetaForm);
                    String uploadFileName = createFileName(fileMetaForm, fileItem);
                    String originalFileName = fileItem.getOriginalFileName();
                    FileInputStream fis = null;
                    ExecutionContext ec;
                    FileInfo fileInfo = null;
                    try {
                        fileInfo = persistence.save(uploadDir, uploadFileName, originalFileName,
                                fis = new FileInputStream(fileItem.getFile())
                        );
                    } catch (EncryptedPDFException e) {
                        ec = new ExecutionContext(ExecutionContext.FAIL);
                        Map<String, String> errorInfo = new HashMap<String, String>();
                        errorInfo.put("applNo", String.valueOf(document.getApplNo()));
                        errorInfo.put("originalFileName", fileItem.getOriginalFileName());
                        ec.setErrorInfo(new ErrorInfo(errorInfo));
                        throw new EncryptedPDFException(ec, "U04514", "ERR1101");
                    } catch (WrongFileFormatException e) {
                        ec = new ExecutionContext(ExecutionContext.FAIL);
                        Map<String, String> errorInfo = new HashMap<String, String>();
                        errorInfo.put("applNo", String.valueOf(document.getApplNo()));
                        errorInfo.put("originalFileName", fileItem.getOriginalFileName());
                        ec.setErrorInfo(new ErrorInfo(errorInfo));
                        throw new WrongFileFormatException(ec, "U06106", "ERR1101", fileItem.getOriginalFileName());
                    } catch (IOException ioe) {
                        if (ioe.getCause().getCause() instanceof DataFormatException) {
                            ec = new ExecutionContext(ExecutionContext.FAIL);
                            Map<String, String> errorInfo = new HashMap<String, String>();
                            errorInfo.put("applNo", String.valueOf(document.getApplNo()));
                            errorInfo.put("originalFileName", fileItem.getOriginalFileName());
                            ec.setErrorInfo(new ErrorInfo(errorInfo));
                            throw new PasswordedPDFException(ec, "U04515", "ERR0052");
                        } else {
                            logger.error("PDF NOT loaded to PDDocument, DocumentController.fileUpload(), modId : " + document.getModId() + ", applNo: " + document.getApplNo() + ", orgFileName: " + fileItem.getOriginalFileName());
                            ec = new ExecutionContext(ExecutionContext.FAIL);
                            Map<String, String> errorInfo = new HashMap<String, String>();
                            errorInfo.put("applNo", String.valueOf(document.getApplNo()));
                            errorInfo.put("originalFileName", fileItem.getOriginalFileName());
                            ec.setErrorInfo(new ErrorInfo(errorInfo));
                            throw new FileNoticeException(ec,
                                    MessageResolver.getMessage("U04518"), "ERR0052");
                        }
                    } finally {
                        try {
                            if (fis!= null) fis.close();
                        } catch (IOException e) {}
                    }
                    return fileInfo;
                }

                private ExecutionContext saveToDB(FileInfo fileInfo, FileMetaForm fileMetaForm, FileItem fileItem,
                                      TotalApplicationDocument document) throws JsonProcessingException {
                    ExecutionContext ec;
                    String originalFileName = fileItem.getOriginalFileName();
                    String path = fileInfo.getDirectory();

                    fileMetaForm.setPath(path);
                    fileMetaForm.setFileName(fileInfo.getFileName());
                    fileMetaForm.setOriginalFileName(originalFileName);

                    document.setFilePath(fileInfo.getDirectory());
                    document.setFileName(fileInfo.getFileName());
                    document.setOrgFileName(originalFileName);
                    document.setFileExt(originalFileName.substring(originalFileName.lastIndexOf('.') + 1));
                    document.setPageCnt(fileInfo.getPageCnt());
                    document.setModId(fileMetaForm.getUserId());
                    ec = documentService.saveOneDocument(document);

                    return ec;
                }

                private FileMetaForm getResultFileMetaForm(FileMetaForm fileMetaForm, ExecutionContext ec) {
                    if (ExecutionContext.SUCCESS.equals(ec.getResult())) {
                        fileMetaForm.setTotalApplicationDocument((TotalApplicationDocument)ec.getData());
                        fileMetaForm.setResultMessage(MessageResolver.getMessage("U348"));
                    } else {
                        fileMetaForm.setResultMessage(MessageResolver.getMessage("U339"));
                    }
                    return fileMetaForm;
                }


            }, FileMetaForm.class, TotalApplicationDocument.class);
        } catch (YSBizException ybe) {
            System.err.println("Error in DocumentController.fileUpload()");
            ybe.printStackTrace();
            ec = ybe.getExecutionContext();
            throw new PDFNotNumberedException(ec);
        }

        ec.setData(returnFileMetaForm);

        return ec;
    }


    /**
     * 원서 첨부 파일 다운로드
     *
     * @param applNo
     * @param docSeq
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value="/fileDownload/{applNo}/{docSeq}", produces = "application/pdf")
    @ResponseBody
    public byte[] fileDownload(@PathVariable("applNo") int applNo,
                               @PathVariable("docSeq") int docSeq,
                               HttpServletResponse response)
            throws IOException {
        ExecutionContext ec;

        ApplicationDocumentKey appDocKey = new ApplicationDocumentKey();
        appDocKey.setApplNo(applNo);
        appDocKey.setDocSeq(docSeq);
        ec = documentService.retrieveOneDocument(appDocKey);
        TotalApplicationDocument totalDoc = (TotalApplicationDocument)ec.getData();

        byte[] bytes = filePersistenceService.getBytesFromFileRepo(totalDoc.getFilePath());

        String downaloadFileName = StringUtil.urlEncodeSpecialCharacter(URLEncoder.encode(totalDoc.getOrgFileName(), "UTF-8"));

        response.setHeader("Content-Disposition", "attachment; filename=\"" + downaloadFileName + "\"");
        response.setHeader("Content-Transfer-Encoding", "binary;");
        response.setHeader("Pragma", "no-cache;");
        response.setHeader("Expires", "-1;");
        response.setHeader("Content-Type", "application/octet-stream");
        response.setContentLength(bytes.length);

        return bytes;
    }

    /**
     * 원서 첨부 파일 삭제
     * 건별로 실제 물리적 파일 삭제 및 DB 저장 내용 삭제움
     *
     * @param applNo
     * @param docSeq
     * @return
     * @throws IOException
     */
    @RequestMapping(value="/fileDelete/{applNo}/{docSeq}", method=RequestMethod.POST)
    @ResponseBody
    public ExecutionContext fileDelete(@PathVariable("applNo") int applNo,
                                       @PathVariable("docSeq") int docSeq,
                                       HttpServletRequest request) {
        ExecutionContext ec;

        ApplicationDocumentKey appDocKey = new ApplicationDocumentKey();
        appDocKey.setApplNo(applNo);
        appDocKey.setDocSeq(docSeq);
        ec = documentService.retrieveOneDocument(appDocKey);
        TotalApplicationDocument totalDoc = (TotalApplicationDocument)ec.getData();

        try {
            ec = documentService.deleteOneDocument(totalDoc);
        } catch (YSBizException ybe) {
            ec = ybe.getExecutionContext();
            ErrorInfo eInfo = ec.getErrorInfo();
            logger.error("YSBizException Occured :: URL=" + request.getRequestURL() + ", ErrorInfo :: " + eInfo.toString() + ", ErrorType :: " + ybe.toString()+ ", SimpleStackTrace ::" +
                    StackTraceFilter.getFilteredCallStack(ybe.getStackTrace(), "com.apexsoft", false));
        }

        return ec;
    }

    /**
     * 원서+첨부 파일 미리보기 용 원서 파일 정보 DB 저장 및
     * Birt가 생성한 PDF 파일을 S3에 저장
     *
     * @param document
     * @param principal
     * @return
     * @throws IOException
     */
    @RequestMapping(value="/generate/{reqType}", method=RequestMethod.POST)
    @ResponseBody
    public ExecutionContext generateApplicationFiles(Document document, Principal principal,
                                                     @PathVariable(value = "reqType") String reqType) {
        Application application = document.getApplication();

        ExecutionContext ecSaveInfo = documentService.saveApplicationPaperInfo(application);
        // 타 대학원 확장 시 TODO - 학교 이름을 파라미터로 받도록
        String lang = application.isForeignAppl() ? "en" : "kr";
        String reportName = "yonsei-" + reqType + "-" + lang;
//        ExecutionContext ecGenerate = birtService.generateBirtFile(application.getApplNo(), reportName);
        ExecutionContext ecGenerate = birtService.generateBirtFile(application, reportName);
        Map<String, Object> map = (Map<String, Object>)ecGenerate.getData();

        String fileDir = (String)map.get("pdfDirectoryFullPath");
        String fileName = (String)map.get("pdfFileName");
        File file = new File(fileDir, fileName);

        boolean uploadOk;
        try {
            uploadOk = filePersistenceService.uploadToFileRepo(fileBaseDir, file, application.getApplNo());
        } catch (Exception e) {
            return new ExecutionContext(ExecutionContext.FAIL);
        }

        if (ExecutionContext.SUCCESS.equals(ecSaveInfo.getResult()) &&
            ExecutionContext.SUCCESS.equals(ecGenerate.getResult()) &&
            uploadOk)
            return ecSaveInfo;
        else {
            ExecutionContext ecFail = new ExecutionContext(ExecutionContext.FAIL);
            return ecFail;
        }
    }


    /**
     * 에러 발생 시 ExecutionContext를 model에 넣고 에러 페이지로 전달
     *
     * @param errorViewName
     * @param ec
     * @return
     */
    private ModelAndView getErrorMV(String errorViewName, ExecutionContext ec) {
        ModelAndView mv = new ModelAndView(errorViewName);
        mv.addObject("ec", ec);
        return mv;
    }

    /**
     * ModelAndView에 데이터 추가
     *
     * @param mv
     * @param map
     * @param ec
     */
    private void addObjectToMV(ModelAndView mv, Map<String, Object> map, ExecutionContext ec) {
        mv.addAllObjects(map);
        mv.addObject("resultMsg", ec.getMessage());
    }

    /**
     * 파일 핸들링 시 발생하는 예외에 대한 YSBizException Wrapper
     *
     * @param document
     * @param principal
     * @param messageCode
     * @param errCode
     * @return
     */
    private YSBizException getYSBizException( TotalApplicationDocument document, Principal principal,
                                              String messageCode, String errCode ) {
        ExecutionContext ec = new ExecutionContext(ExecutionContext.FAIL);
        String applNo = String.valueOf(document.getApplNo());
        String docSeq = String.valueOf(document.getDocSeq());
        String docItemCode = document.getDocItemCode();
        String docItemName = document.getDocItemName();
        Map<String, String> eInfo = new HashMap<String, String>();
        eInfo.put("applNo", applNo);
        eInfo.put("docSeq", docSeq);
        eInfo.put("docItemCode", docItemCode);
        eInfo.put("docItemName", docItemName);
        ec.setMessage(MessageResolver.getMessage(messageCode));
        ec.setErrCode(errCode);
        ec.setErrorInfo(new ErrorInfo(eInfo));
        return new YSBizException(ec);
    }
}
