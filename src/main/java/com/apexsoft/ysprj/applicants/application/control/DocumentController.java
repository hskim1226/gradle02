package com.apexsoft.ysprj.applicants.application.control;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.exception.YSBizException;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.framework.persistence.file.callback.FileUploadEventCallbackHandler;
import com.apexsoft.framework.persistence.file.exception.FileUploadException;
import com.apexsoft.framework.persistence.file.handler.FileHandler;
import com.apexsoft.framework.persistence.file.manager.FilePersistenceManager;
import com.apexsoft.framework.persistence.file.model.FileInfo;
import com.apexsoft.framework.persistence.file.model.FileItem;
import com.apexsoft.framework.persistence.file.model.FileMetaForm;
import com.apexsoft.framework.persistence.file.model.FileVO;
import com.apexsoft.ysprj.applicants.application.domain.*;
import com.apexsoft.ysprj.applicants.application.service.DocumentService;
import com.apexsoft.ysprj.applicants.application.validator.DocumentValidator;
import com.apexsoft.ysprj.applicants.common.service.CommonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private CommonService commonService;

    @Autowired
    private DocumentValidator documentValidator;

    @Autowired
    private ObjectMapper objectMapper;

    @Resource(name = "messageResolver")
    MessageResolver messageResolver;

    @Value("#{app['file.baseDir']}")
    private String fileBaseDir;

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
     * 어학/경력 정보 최초작성/수정 화면
     *
     * @param formData
     * @return
     */
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
                                    ModelAndView mv) {
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
                                     ModelAndView mv) {
        documentValidator.validate(formData, bindingResult);
        mv.setViewName(TARGET_VIEW);
        if (bindingResult.hasErrors()) {
            mv.addObject("resultMsg", messageResolver.getMessage("U334"));
            return mv;
        }

        ExecutionContext ec = null;
        String userId = principal.getName();

        Application application = formData.getApplication();
        int applNo = application.getApplNo();
        application.setUserId(userId);

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
                                          ModelAndView mv) {
        documentValidator.validate(formData, bindingResult);
        mv.setViewName("application/mylist");
        if (bindingResult.hasErrors()) {
            mv.addObject("resultMsg", messageResolver.getMessage("U334"));
            return mv;
        }

        ExecutionContext ec = null;
        String userId = principal.getName();

        Application application = formData.getApplication();
        int applNo = application.getApplNo();
        application.setUserId(userId);

        ec = documentService.submit(formData);

        if (ExecutionContext.SUCCESS.equals(ec.getResult())) {

            ParamForApplication p = new ParamForApplication();
            p.setUserId(principal.getName());
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
                                   FileHandler fileHandler) {

        ExecutionContext ec = new ExecutionContext();

        String returnFileMetaForm = fileHandler.handleMultiPartRequest(new FileUploadEventCallbackHandler<String, FileMetaForm, TotalApplicationDocument>() {
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
                String userId = principal.getName();
                String firstString = userId.substring(0, 1);
                String applNo = fileMetaForm.getApplNo();

                return admsNo + "/" + firstString + "/" + userId + "/" + applNo;
            }

            /**
             * 실제 저장될 파일 이름 반환
             *
             * @return
             */
            @Override
            protected String createFileName(FileMetaForm fileMetaForm, FileItem fileItem) {
                return fileMetaForm.getFieldName() + "-" + fileItem.getOriginalFileName();
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
                ExecutionContext ec;
                FileInfo fileInfo;
                String uploadDir = getDirectory(fileMetaForm);
                String uploadFileName = "";
                for ( FileItem fileItem : fileItems){
                    FileInputStream fis = null;
                    String originalFileName = fileItem.getOriginalFileName();
                    try{
                        uploadDir = getDirectory(fileMetaForm);
                        uploadFileName = createFileName(fileMetaForm, fileItem);
                        fileInfo = persistence.save(uploadDir,
                                uploadFileName,
                                originalFileName,
                                fis = new FileInputStream(fileItem.getFile()));
                        String path = fileInfo.getDirectory();
                        String pathWithoutContextPath;
                        if (path.startsWith(fileBaseDir)) {
                            pathWithoutContextPath = path.substring(fileBaseDir.length());
                        } else {
                            throw new FileUploadException("업로드 경로 에러");
                        }
                        fileMetaForm.setPath(pathWithoutContextPath);
                        fileMetaForm.setFileName(fileInfo.getFileName());
                        fileMetaForm.setOriginalFileName(originalFileName);

                        document.setFilePath(fileInfo.getDirectory());
                        document.setFileName(fileInfo.getFileName());
                        document.setOrgFileName(originalFileName);
                        document.setFileExt(originalFileName.substring(originalFileName.lastIndexOf('.') + 1));
                        document.setCreId(principal.getName());

                        ec = documentService.saveOneDocument(document);

                        if (ExecutionContext.SUCCESS.equals(ec.getResult())) {
                            fileMetaForm.setTotalApplicationDocument((TotalApplicationDocument)ec.getData());
                        }

                    }catch(FileNotFoundException fnfe) {
                        persistence.deleteFile(uploadDir, uploadFileName);
                        throw new FileUploadException("", fnfe);
                    } catch (FileUploadException foe) {
                        persistence.deleteFile(uploadDir, uploadFileName);
                        throw new FileUploadException("", foe);
                    } catch (Exception e) {
                        persistence.deleteFile(uploadDir, uploadFileName);
                        throw new YSBizException("callback.handleEvent()");
                    }finally {
                        try {
                            if (fis!= null) fis.close();
                        } catch (IOException e) {}
                        FileUtils.deleteQuietly(fileItem.getFile());
                    }
                }

                String jsonFileMetaForm = null;
                try {
                    jsonFileMetaForm = objectMapper.writeValueAsString(fileMetaForm);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }

                return jsonFileMetaForm;
            }
        }, FileMetaForm.class, TotalApplicationDocument.class);

        ec.setData(returnFileMetaForm);

        return ec;

//        ec.setData(returnFileMetaForm);
//
//
//        Application application = document.getApplication();
//
//        ApplicationIdentifier data = (ApplicationIdentifier)ec.getData();
//
//        return getEntireInfo(data.getApplNo(), application.getAdmsNo(),
//                application.getEntrYear(), application.getAdmsTypeCode(), "application/appinfo",
//                entireApplication);
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
//    @RequestMapping(value="/attached/{admsNo}/{applNo}/{fileName:.+}/{originalFileName}")
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
        File file = new File(totalDoc.getFilePath(), totalDoc.getFileName());
        byte[] bytes = org.springframework.util.FileCopyUtils.copyToByteArray(file);

        response.setHeader("Content-Disposition", "attachment; filename=\"" + new String(totalDoc.getOrgFileName().getBytes("UTF-8"), "ISO-8859-1") + "\"");
        response.setHeader("Content-Transfer-Encoding", "binary;");
        response.setHeader("Pragma", "no-cache;");
        response.setHeader("Expires", "-1;");
        response.setHeader("Content-Type", "application/octet-stream");
//        response.setHeader("Content-Type", "application/pdf");
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
//    @RequestMapping(value="/attached/{admsNo}/{applNo}/{fileName:.+}/{originalFileName}")
    @RequestMapping(value="/fileDelete/{applNo}/{docSeq}", method=RequestMethod.POST)
    @ResponseBody
    public ExecutionContext fileDelete(@PathVariable("applNo") int applNo,
                                       @PathVariable("docSeq") int docSeq) {
        ExecutionContext ec;
        boolean deleteOk;

        ApplicationDocumentKey appDocKey = new ApplicationDocumentKey();
        appDocKey.setApplNo(applNo);
        appDocKey.setDocSeq(docSeq);
        ec = documentService.retrieveOneDocument(appDocKey);
        TotalApplicationDocument totalDoc = (TotalApplicationDocument)ec.getData();
        File file = new File(totalDoc.getFilePath(), totalDoc.getFileName());

        ec = documentService.deleteOneDocument(totalDoc);
        deleteOk = file.delete();

        if (ExecutionContext.FAIL.equals(ec.getResult())) {
            ec.setMessage(messageResolver.getMessage("U338"));
            ec.setErrCode("ERR0034");
            throw new YSBizException(ec);
        } else if (!deleteOk) {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(messageResolver.getMessage("U338"));
            ec.setErrCode("ERR0051");
            throw new YSBizException(ec);
        }

        return ec;
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
}
