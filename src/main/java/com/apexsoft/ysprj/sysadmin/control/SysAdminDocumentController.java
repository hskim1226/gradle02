package com.apexsoft.ysprj.sysadmin.control;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
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
import com.apexsoft.ysprj.applicants.common.domain.BirtRequest;
import com.apexsoft.ysprj.applicants.common.service.BirtService;
import com.apexsoft.ysprj.applicants.common.service.PDFService;
import com.apexsoft.ysprj.applicants.common.util.FileUtil;
import com.apexsoft.ysprj.applicants.common.util.StringUtil;
import com.apexsoft.ysprj.applicants.common.util.WebUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.pdfbox.util.PDFMergerUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
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
@RequestMapping(value="/sysadmin/document")
public class SysAdminDocumentController {

    @Autowired
    private DocumentService documentService;

    @Autowired
    private BirtService birtService;

    @Autowired
    PDFService pdfService;

    @Autowired
    private DocumentValidator documentValidator;

    @Autowired
    LocaleResolver localeResolver;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("#{app['file.baseDir']}")
    private String fileBaseDir;

    @Value("#{app['s3.bucketName']}")
    private String s3BucketName;

    @Value("#{app['constraint.dueTime.yyyyMMddhhmmss']}")
    private String dueTime;

    @Autowired
    WebUtil webUtil;

    private static final Logger logger = LoggerFactory.getLogger(SysAdminDocumentController.class);
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
                                    ModelAndView mv) {
        webUtil.blockGetMethod(request, formData.getApplication());
        mv.setViewName(TARGET_VIEW);
        if (bindingResult.hasErrors()) return mv;

        ExecutionContext ec = documentService.retrieveDocument(formData);

        Map<String, Object> map = (Map<String, Object>)ec.getData();
        addObjectToMV(mv, map, ec);

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
            return mv;
        }

        ExecutionContext ec = null;
//        String userId = principal.getName();

        Application application = formData.getApplication();
        int applNo = application.getApplNo();
//        application.setUserId(userId);
        application.setModId(application.getUserId());

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
        return mv;
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
    public ExecutionContext fileUpload(Application application,
                                       BindingResult binding,
                                       final Principal principal,
                                       HttpServletRequest request,
                                       MultipartHttpServletRequest multipartHttpServletRequest,
                                       FileHandler fileHandler) {

        ExecutionContext ec = new ExecutionContext();
        final String userId = application.getUserId();
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
//                    String userId = principal.getName();
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
                    String uploadDir = getDirectory(fileMetaForm);
                    String uploadFileName = "";
                    for ( FileItem fileItem : fileItems){
                        String originalFileName = fileItem.getOriginalFileName();

                        String ext = originalFileName.substring(originalFileName.lastIndexOf('.')+1).toLowerCase();
                        FileInputStream fis = null;
                        if ("pdf".equals(ext)) {
                            // 합침 가능 여부 확인
                            String tmpFileFullPath = "/opt/ysproject/temp/pdf-test-" + document.getApplNo() + ".pdf";

                            try {
                                fis = new FileInputStream(fileItem.getFile());
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }

                            ByteArrayOutputStream baos = null;
                            try {
                                baos = new ByteArrayOutputStream();
                                byte[] buffer = new byte[1024];
                                int len;
                                while ((len = fis.read(buffer)) != -1) {
                                    baos.write(buffer, 0, len);
                                }
                                baos.flush();
                            } catch (IOException e) {
                                throw new YSBizException(e);
                            }

                            PDFMergerUtility mergerUtility = new PDFMergerUtility();
                            mergerUtility.setDestinationFileName(tmpFileFullPath);
                            org.springframework.core.io.Resource pdfResource = new ClassPathResource("pdf/merge-sample.pdf");
                            InputStream mergeSamplePdf = null;
                            try {
                                mergeSamplePdf = pdfResource.getInputStream();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            mergerUtility.addSource(mergeSamplePdf);
                            try {
                                mergerUtility.addSource(new ByteArrayInputStream(baos.toByteArray()));
                                mergerUtility.mergeDocuments();
                            } catch (Exception e) {
                                logger.debug("merging PDF files fails, in DocumentController.handleEvent(), FileName : " + fileItem.getOriginalFileName());
                                ExecutionContext ec1 = new ExecutionContext(ExecutionContext.FAIL);
                                ec1.setResult(ExecutionContext.FAIL);
                                ec1.setMessage(MessageResolver.getMessage("U06102"));
                                ec1.setErrCode("ERR1104");
                                Map<String, String> errorInfo = new HashMap<String, String>();
                                errorInfo.put("applNo", String.valueOf(document.getApplNo()));
                                errorInfo.put("fileName", fileItem.getOriginalFileName());
                                ec1.setErrorInfo(new ErrorInfo(errorInfo));
                                throw new PDFMergeException(ec1, "U06105", "ERR1104");
                            } finally {
                                FileUtils.deleteQuietly(new File(tmpFileFullPath));
                            }
                        }


                        try{
                            uploadDir = getDirectory(fileMetaForm);
                            uploadFileName = createFileName(fileMetaForm, fileItem);
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
                                    throw new FileNoticeException(ec, "U04518", "ERR0052");
//                                throw getYSBizException(document, principal, "U339", "ERR0063");
                                }
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
//                            document.setCreId(principal.getName());
                            document.setModId(principal.getName());
                            ec = documentService.saveOneDocument(document);

                            if (ExecutionContext.SUCCESS.equals(ec.getResult())) {
                                fileMetaForm.setTotalApplicationDocument((TotalApplicationDocument)ec.getData());
                                fileMetaForm.setResultMessage(MessageResolver.getMessage("U348"));
                            } else {
                                fileMetaForm.setResultMessage(MessageResolver.getMessage("U339"));
                            }

                            jsonFileMetaForm = objectMapper.writeValueAsString(fileMetaForm);

                        } catch (AmazonServiceException ase) {
                            ec = new ExecutionContext(ExecutionContext.FAIL);
                            ec.setMessage(MessageResolver.getMessage("U339"));
                            ec.setErrCode("ERR0052");
                            Map<String, String> errorInfo = new HashMap<String, String>();
//                            errorInfo.put("userId", String.valueOf(principal.getName()));
                            errorInfo.put("userId", String.valueOf(userId));
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
                            throw getYSBizException(document, userId, "U339", "ERR0052");
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
            }, FileMetaForm.class, TotalApplicationDocument.class);
        } catch (YSBizException ybe) {
            logger.error("ErrorInfo :: " + ybe.getExecutionContext().getErrorInfo().toString() + ", ErrorType :: " + ybe.toString() + ", SimpleStackTrace ::" +
                    StackTraceFilter.getFilteredCallStack(ybe.getStackTrace(), "com.apexsoft", false));
            ec = ybe.getExecutionContext();
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

        AmazonS3 s3 = new AmazonS3Client();
        S3Object object = s3.getObject(new GetObjectRequest(s3BucketName, totalDoc.getFilePath()));
        InputStream inputStream = object.getObjectContent();
        byte[] bytes = IOUtils.toByteArray(inputStream);

        response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(totalDoc.getOrgFileName(), "UTF-8") + "\"");
        response.setHeader("Content-Transfer-Encoding", "binary;");
        response.setHeader("Pragma", "no-cache;");
        response.setHeader("Expires", "-1;");
        response.setHeader("Content-Type", "application/octet-stream");
        response.setContentLength(bytes.length);

        return bytes;
    }
//    public byte[] fileDownload(@PathVariable("applNo") int applNo,
//                               @PathVariable("docSeq") int docSeq,
//                               HttpServletResponse response)
//            throws IOException {
//        ExecutionContext ec;
//
//        ApplicationDocumentKey appDocKey = new ApplicationDocumentKey();
//        appDocKey.setApplNo(applNo);
//        appDocKey.setDocSeq(docSeq);
//        ec = documentService.retrieveOneDocument(appDocKey);
//        TotalApplicationDocument totalDoc = (TotalApplicationDocument)ec.getData();
//        File file = new File(totalDoc.getFilePath(), totalDoc.getFileName());
//        byte[] bytes = org.springframework.util.FileCopyUtils.copyToByteArray(file);
//
//        response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(totalDoc.getOrgFileName(), "UTF-8") + "\"");
//        response.setHeader("Content-Transfer-Encoding", "binary;");
//        response.setHeader("Pragma", "no-cache;");
//        response.setHeader("Expires", "-1;");
//        response.setHeader("Content-Type", "application/octet-stream");
////        response.setHeader("Content-Type", "application/pdf");
//        response.setContentLength(bytes.length);
//
//        return bytes;
//    }

    /**
     * 원서 첨부 파일 삭제
     * 건별로 실제 물리적 파일 삭제 및 DB 저장 내용 삭제움
     *
     * @param applNo
     * @param docSeq
     * @return
     * @throws IOException
     */
//    @RequestMapping(value="/attached/{admsNo}/{applNo}/{fileName:.+}/{originalFileName}")
    @RequestMapping(value="/fileDelete/{applNo}/{docSeq}", method=RequestMethod.POST)
//    @RequestMapping(value="/fileDelete/{applNo}/{docSeq}")
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
     * Birt가 생성한 PDF 파일을 서버 로컬 디스크에 저장
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
//        application.setUserId(principal.getName());
//        application.setUserId(application.getUserId());
        ExecutionContext ecSaveInfo = documentService.saveApplicationPaperInfo(application);
        // 타 대학원 확장 시 TODO - 학교 이름을 파라미터로 받도록
        String admsTypeCode = application.getAdmsTypeCode();
        String lang = "C".equals(admsTypeCode) || "D".equals(admsTypeCode) ? "en" : "kr";
        String reportName = "yonsei-" + reqType + "-" + lang;
        ExecutionContext ecGenerate = birtService.generateBirtFile(application.getApplNo(), reportName);

        if (ExecutionContext.SUCCESS.equals(ecSaveInfo.getResult()) &&
                ExecutionContext.SUCCESS.equals(ecGenerate.getResult()) )
            return ecSaveInfo;
        else
            return new ExecutionContext(ExecutionContext.FAIL);
    }

    /**
     * 사용자가 S3로 업로드 한 pdf 파일을 로컬에 다운받아서 하나로 합치고(B파일)
     * #/# 형식의 쪽 번호를 우상단에 추가하여 로컬에 PDF로 저장(C파일)
     * A파일과 C파일을 합친 PDF 파일을 로컬에 저장(D파일)
     * D파일을 S3에 업로드(v03 - 원서 미리보기 시 서버 로컬에서의 다운로드 부하를 S3로 이전)
     * 중간 작업 파일인 A파일, B파일, C파일 삭제
     *
     * @param birtRequest
     * @param request
     * @return
     */
    @RequestMapping(value="/generate/tempMergedApplicationForm")
    @ResponseBody
    public String generateTempMergedApplicationForm(BirtRequest birtRequest, Principal principal, HttpServletRequest request) {

        Application application = birtRequest.getApplication();
//        application.setUserId(principal.getName());
        ExecutionContext ec = pdfService.genAndUploadPDFByApplicants(application);
        if (ExecutionContext.SUCCESS.equals(ec.getResult())) {
            return ExecutionContext.SUCCESS;
        } else {
            return ExecutionContext.FAIL;
        }
    }

    /**
     * DB에 저장된 원서 정보를 토대로 S3에서 원서+첨부파일 PDF 파일 다운로드
     *
     * @param basis
     * @param principal
     * @param response
     * @return
     * @throws java.io.IOException
     */
    @RequestMapping(value="/download/tempMergedApplicationForm", produces = "application/pdf")
    @ResponseBody
    public byte[] fileDownload(Basis basis,
                               Principal principal,
                               HttpServletRequest request,
                               HttpServletResponse response) {
        webUtil.blockGetMethod(request, basis.getApplication());
//        String userId = principal.getName();
        Application application = basis.getApplication();
        String admsNo = application.getAdmsNo();
        int applNo = application.getApplNo();
        String userId = application.getUserId();
        byte[] bytes = null;

        List<ApplicationDocument> applPaperInfosList =
                documentService.retrieveApplicationPaperInfo(applNo); // DB에서 filePath가져온다
        if (applPaperInfosList.size() == 1) {
            String uploadDirPath = applPaperInfosList.get(0).getFilePath();
            String s3UploadDirPath = FileUtil.getS3PathFromLocalFullPath(uploadDirPath, fileBaseDir);
            AmazonS3 s3 = new AmazonS3Client();
            S3Object object = null;
            try {
                object = s3.getObject(new GetObjectRequest(s3BucketName, FileUtil.getFinalMergedFileFullPath(s3UploadDirPath, applNo)));
            } catch (Exception e) {
                logger.error("Err in s3.getObject FiledDownload in PDFController");
                logger.error(e.getMessage());
                ExecutionContext ec = new ExecutionContext(ExecutionContext.FAIL);
                ec.setMessage(MessageResolver.getMessage("U00242"));
                Map<String, Object> ecMap = new HashMap<String, Object>();
                ecMap.put("s3BucketName", "[" + s3BucketName + "]");
                ecMap.put("admsNo", "[" + admsNo + "]");
                ecMap.put("userId", "[" + userId + "]");
                ecMap.put("objectKey", "[" + FileUtil.getFinalMergedFileFullPath(s3UploadDirPath, applNo) + "]");
                ec.setErrorInfo(new ErrorInfo(ecMap));
                throw new YSBizException(ec);
            }

            InputStream inputStream = object.getObjectContent();
            ObjectMetadata meta = object.getObjectMetadata();
            try {
                bytes = IOUtils.toByteArray(inputStream);
            } catch (IOException e) {
                ExecutionContext ecError = new ExecutionContext(ExecutionContext.FAIL);

                ecError.setMessage(MessageResolver.getMessage("U350"));
                ecError.setErrCode("ERR0062");

                Map<String, String> errorInfo = new HashMap<String, String>();
                errorInfo.put("applNo", String.valueOf(applNo));
                errorInfo.put("userId", basis.getApplication().getUserId());

                ecError.setErrorInfo(new ErrorInfo(errorInfo));
                throw new YSBizException(ecError);
            }

            response.setContentType(meta.getContentType());
            response.setHeader("Content-Length", String.valueOf(meta.getContentLength()));
            response.setHeader("Content-Encoding", meta.getContentEncoding());
            response.setHeader("ETag", meta.getETag());
            response.setHeader("Last-Modified", meta.getLastModified().toString());
//            아래 헤더 추가하면 파일명은 지정할 수 있으나 미리 보기는 안되고 다운로드만 됨
            try {
                response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(FileUtil.getFinalUserDownloadFileName(userId), "UTF-8") + "\"");
            } catch (UnsupportedEncodingException e) {
                throw new YSBizException(MessageResolver.getMessage("U04516"));  /*지원하지 않는 인코딩 방식입니다.*/
            }

        } else {
            ExecutionContext ecError = new ExecutionContext(ExecutionContext.FAIL);

            ecError.setMessage(MessageResolver.getMessage("U349"));
            ecError.setErrCode("ERR0061");

            Map<String, String> errorInfo = new HashMap<String, String>();
            errorInfo.put("applNo", String.valueOf(applNo));
            errorInfo.put("userId", basis.getApplication().getUserId());

            ecError.setErrorInfo(new ErrorInfo(errorInfo));
            throw new YSBizException(ecError);
        }

        return bytes;
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

//        // FOR MANAGE
//        String adminID = principal.getName();
//        if (adminID.equals("Apex1234") || adminID.startsWith("Yssub")) {
//
//        } else {
//            DateTime now = new DateTime();
//            DateTimeZone seoul = DateTimeZone.forID("Asia/Seoul");
//            int year = Integer.parseInt(dueTime.substring(0, 4));
//            int month = Integer.parseInt(dueTime.substring(4, 6));
//            int date = Integer.parseInt(dueTime.substring(6, 8));
//            int hour = Integer.parseInt(dueTime.substring(8, 10));
//            int min = Integer.parseInt(dueTime.substring(10, 12));
//            int sec = Integer.parseInt(dueTime.substring(12, 14));
////            DateTime dueTime = new DateTime(2015, 4, 10, 18, 50, 3, seoul);
//            DateTime dueTime = new DateTime(year, month, date, hour, min, sec, seoul);
//
//            Application tApplication = formData.getApplication();
//            String tUserId = tApplication != null ? tApplication.getUserId() : "APPLICATION IS NULL";
//            int tApplNo = tApplication != null ? tApplication.getApplNo() : -1;
//            if (now.isAfter(dueTime)) {
//                logger.error("DUE : " + dueTime);
//                logger.error("NOW : " + now);
//                logger.error("STATUS LATE");
//                logger.error("APPL STATUS CODE : " + tApplication.getApplStsCode());
//                logger.error("userId : [" + tUserId + "], " + "applNo : [" + tApplNo + "]" );
//                ExecutionContext ec = new ExecutionContext(ExecutionContext.FAIL);
//                ec.setMessage(MessageResolver.getMessage("U04517"));
//                ec.setErrCode("ERR3011");
//
//                throw new YSBizException(ec);
//            }
//        }
        // FOR MANAGE

        documentValidator.validate(formData, bindingResult, localeResolver.resolveLocale(request));
        mv.setViewName("application/mylist");
        if (bindingResult.hasErrors()) {
            mv.setViewName("application/document");
            mv.addObject("resultMsg", MessageResolver.getMessage("U334"));
            return mv;
        }

        ExecutionContext ec = null;
        Application application = formData.getApplication();
        int applNo = application.getApplNo();
        String userId = application.getUserId();

//        application.setUserId(principal.getName());
        ExecutionContext ecSaveInfo = documentService.saveApplicationPaperInfo(application);
        // 타 대학원 확장 시 TODO - 학교 이름을 파라미터로 받도록

        application.setModId(userId);

        ec = documentService.submit(formData);

        if (ExecutionContext.SUCCESS.equals(ec.getResult())) {

            ParamForApplication p = new ParamForApplication();
//                    p.setUserId(principal.getName());
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
        return mv;
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
