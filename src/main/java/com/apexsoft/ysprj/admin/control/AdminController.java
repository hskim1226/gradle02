package com.apexsoft.ysprj.admin.control;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.ysprj.admin.control.form.CourseSearchPageForm;
import com.apexsoft.ysprj.admin.domain.ApplicantInfo;
import com.apexsoft.ysprj.admin.service.AdminService;
import com.apexsoft.ysprj.admin.service.PostApplicationService;
import com.apexsoft.ysprj.applicants.application.domain.Application;
import com.apexsoft.ysprj.applicants.application.domain.ApplicationDocument;
import com.apexsoft.ysprj.applicants.application.service.DocumentService;
import com.apexsoft.ysprj.applicants.common.domain.AdmsNo;
import com.apexsoft.ysprj.applicants.common.service.BirtService;
import com.apexsoft.ysprj.applicants.common.service.PDFService;
import com.apexsoft.ysprj.applicants.common.service.ZipService;
import com.apexsoft.ysprj.applicants.common.util.FilePathUtil;
import com.apexsoft.ysprj.applicants.common.util.StringUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by hanmomhanda on 14. 8. 6.
 */
@Controller
@RequestMapping(value="/admin")
public class AdminController {

    @Autowired
    PDFService pdfService;

    @Autowired
    DocumentService documentService;

    @Autowired
    private AdminService adminService;

    @Autowired
    CommonDAO commonDAO;

    @Autowired
    private ServletContext context;

    @Autowired
    private PostApplicationService postApplicationService;

    @Autowired
    ZipService zipService;

    @Autowired
    private AdmsNo admsNo;

    @SuppressWarnings("restriction")
    @Resource(name = "messageResolver")
    private MessageResolver messageResolver;

    @Autowired
    BirtService birtService;

    @Autowired
    private AmazonS3Client s3Client;

    @Value("#{app['file.baseDir']}")
    private String fileBaseDir;

    @Value("#{app['s3.bucketName']}")
    private String s3BucketName;

    @Value("#{app['s3.midPath']}")
    private String s3MidPath;

    @Value("#{app['rpt.format']}")
    private String REPORT_FORMAT;

    private ModelAndView getErrorMV(String errorViewName, ExecutionContext ec) {
        ModelAndView mv = new ModelAndView(errorViewName);
        mv.addObject("ec", ec);
        return mv;
    }

    @RequestMapping(value="/search/applicants")
    public String searchApplicantsInit() {
        return "admin/search/applicants";
    }

    @RequestMapping(value="/search/applicantsId")
    public String searchApplicantsIdInit() {
        return "admin/search/applicantsId";
    }

    @RequestMapping(value="/search/applicantsDept")
    public String searchApplicantsDeptInit()  {
        return "admin/search/applicantsDept";
    }

    @RequestMapping(value="/search/applicantsName")
    public String searchApplicantsNameInit()  {
        return "admin/search/applicantsName";
    }


    @RequestMapping(value="/search/applicants/nameSearch")
    public ModelAndView searchApplicantByName( @ModelAttribute CourseSearchPageForm courseSearchPageForm,
                                               BindingResult bindingResult,
                                               ModelAndView mv)
            throws NoSuchAlgorithmException, JsonProcessingException, UnsupportedEncodingException {
        mv.setViewName("admin/search/applicantsName");
        ExecutionContext ec;
        if (bindingResult.hasErrors()) {
            mv.addObject("resultMsg", messageResolver.getMessage("U334"));

        }
        ExecutionContext ecRetrieve = adminService.retrieveApplicantPaginatedListByApplicantInfo(courseSearchPageForm);
        mv = setSearchOptions(ecRetrieve, mv);
//        if (ecRetrieve.isSuccessful()) {
//            Map<String, Object> map = (Map<String, Object>)ecRetrieve.getData();
//            mv.addObject("selection", map.get("selection"));
//            mv.addObject("searchPageForm", map.get("searchPageForm"));
//            mv.addObject("applList", map.get("applList"));
//
//        } else {
//            mv = getErrorMV("common/error", ecRetrieve);
//        }
        return mv;
    }

    @RequestMapping(value="/search/applicants/idSearch")
    public  ModelAndView searchApplicantById( @ModelAttribute CourseSearchPageForm courseSearchPageForm,
                                              BindingResult bindingResult,
                                              ModelAndView mv)
            throws NoSuchAlgorithmException, JsonProcessingException, UnsupportedEncodingException {
        mv.setViewName("admin/search/applicantsId");
        ExecutionContext ec;
        if (bindingResult.hasErrors()) {
            mv.addObject("resultMsg", messageResolver.getMessage("U334"));

        }
        ExecutionContext ecRetrieve = adminService.retrieveApplicantPaginatedListByApplicantInfo(courseSearchPageForm);
        mv = setSearchOptions(ecRetrieve, mv);
//        if (ecRetrieve.isSuccessful()) {
//            Map<String, Object> map = (Map<String, Object>)ecRetrieve.getData();
//            mv.addObject("selection", map.get("selection"));
//            mv.addObject("searchPageForm", map.get("searchPageForm"));
//            mv.addObject("applList", map.get("applList"));
//
//        } else {
//            mv = getErrorMV("common/error", ecRetrieve);
//        }
        return mv;
    }

    @RequestMapping(value="/search/applicants/deptSearch")
    public ModelAndView searchApplicantByDept( @ModelAttribute CourseSearchPageForm courseSearchPageForm,
                                               BindingResult bindingResult,
                                               ModelAndView mv)
            throws NoSuchAlgorithmException, JsonProcessingException, UnsupportedEncodingException {
        mv.setViewName("admin/search/applicantsDept");
        ExecutionContext ec;
        if (bindingResult.hasErrors()) {
            mv.addObject("resultMsg", messageResolver.getMessage("U334"));

        }
        ExecutionContext ecRetrieve = adminService.retrieveApplicantPaginatedListByDept(courseSearchPageForm);
        mv = setSearchOptions(ecRetrieve, mv);
//        if (ecRetrieve.isSuccessful()) {
//            Map<String, Object> map = (Map<String, Object>)ecRetrieve.getData();
//            mv.addObject("selection", map.get("selection"));
//            mv.addObject("searchPageForm", map.get("searchPageForm"));
//            mv.addObject("applList", map.get("applList"));
//        } else {
//            mv = getErrorMV("common/error", ecRetrieve);
//        }
        return mv;
    }

    @RequestMapping(value="/search/applicant/applInfoDetail")
    public String displayQnaDetail(@RequestParam("applNo") int applNo, Model model){
        ExecutionContext ecRetrieve = adminService.getApplicantDetail(applNo,"");
        Map<String, Object> map = (Map<String, Object>)ecRetrieve.getData();

        model.addAttribute("cntr", map.get("cntr"));
        model.addAttribute("applForn", map.get("applForn"));
        model.addAttribute("applGene", map.get("applGene"));
        model.addAttribute("selection", map.get("selection"));
        model.addAttribute("applInfo", map.get("applInfo"));


        return "admin/search/applInfoDetail";
    }


    @RequestMapping(value="/cancel/application")
    public String cancelApplication() {
        return "admin/cancel/application";
    }

    @RequestMapping(value="/data/download")
    public String dataDownload() {
        return "/admin/data/download";
    }

    @RequestMapping(value="/data/payment")
    public String dataPayment() {
        return "/admin/data/payment";
    }


    @RequestMapping(value="/guideline/deptManage")
    public String retreiveDetManageInfo() {
        return "admin/guideline/changeUnit";
    }

    @RequestMapping(value="/guideline/langMandManage")
    public String retrieveLangMandManageInfo() {
        return "admin/guideline/application";
    }

    @RequestMapping(value="/guideline/docMandManage")
    public String retrieveDocMandManageInfo() {  return "admin/guideline/application"; }


    @RequestMapping(value="/guideline/feeManage")
    public String retrieveFeeManageInfo() {
        return "/admin/guideline/download";
    }


    @RequestMapping(value="")
    public String initZeroAdmin() {
        return "admin/stats/main";
    }

    @RequestMapping(value="/main")
    public ModelAndView initAdmin( @ModelAttribute CourseSearchPageForm courseSearchPageForm,
                                   BindingResult bindingResult,
                                   ModelAndView mv)
            throws NoSuchAlgorithmException, JsonProcessingException, UnsupportedEncodingException {
        mv.setViewName("admin/stats/main");
        ExecutionContext ec;
        if (bindingResult.hasErrors()) {
            mv.addObject("resultMsg", messageResolver.getMessage("U334"));

        }
        ExecutionContext ecRetrieve = adminService.retrieveInitInfo();
        if (ecRetrieve.isSuccessful()) {
            Map<String, Object> map = (Map<String, Object>)ecRetrieve.getData();
            mv.addObject("applCntList", map.get("applCntList"));
            mv.addObject("chgCntList", map.get("chgCntList"));
            mv.addObject("chgList", map.get("chgList"));
            mv.addObject("weekCntList", map.get("weekCntList"));
            mv.addObject("corsCntList", map.get("corsCntList"));
        } else {
            mv = getErrorMV("common/error", ecRetrieve);
        }
        return mv;
    }

    /**
     * 지원서 다운로드
     * @return
     * @throws java.io.IOException
     */

    @RequestMapping(value="/search/pdfDownload", produces = "application/pdf")
    @ResponseBody
    public byte[] fileDownload(@RequestParam("applNo") int applNo,
                               @RequestParam("type") String type,
                               Principal principal, HttpServletResponse response)
            throws IOException, InterruptedException {

        Application application = documentService.getApplication(applNo);

        byte[] bytes = getDownloadableFileAsBytes(application, type);

        ExecutionContext ecRetrieve = null;
        ecRetrieve = postApplicationService.checkDocumentRead(applNo, application.getUserId());
        Map<String, Object> map = (Map<String, Object>)ecRetrieve.getData();

        String fileName = FilePathUtil.getDownloadableZipFileName(application);
        String downaloadFileName = StringUtil.urlEncodeSpecialCharacter(URLEncoder.encode(fileName, "UTF-8"));

        response.setHeader("Content-Disposition", "attachment; filename=\"" + downaloadFileName + "\"");
        response.setHeader("Content-Transfer-Encoding", "binary;");
        response.setHeader("Pragma", "no-cache;");
        response.setHeader("Expires", "-1;");
        response.setHeader("Content-Type", "application/octet-stream");
        response.setContentLength(bytes.length);

        return bytes;
    }

    private byte[] getDownloadableFileAsBytes(Application application, String type) throws IOException, InterruptedException {
        int applNo = application.getApplNo();
        String userId = application.getUserId();
        String localDirPath = FilePathUtil.getUploadDirectoryFullPath(fileBaseDir, s3MidPath, application.getAdmsNo(), userId, applNo);
        String s3FilePath = FilePathUtil.getS3PathFromLocalFullPath(localDirPath, fileBaseDir);
        String filePath = null;
        byte[] bytes = null;

        if ("slip".equals(type)) {
            filePath = FilePathUtil.getApplicationSlipFileFullPath(s3FilePath, userId);
            bytes = getBytesFromS3Object(filePath);
        }
        else if ("form".equals(type)) {
            filePath = FilePathUtil.getApplicationFormFileFullPath(s3FilePath, userId);
            bytes = getBytesFromS3Object(filePath);
        }
        else if ("merged".equals(type)) {
            String applFormfilePath = FilePathUtil.getApplicationFormFileFullPath(s3FilePath, userId);
            bytes = getBytesFromS3Object(applFormfilePath);
            File applFormFile = new File(fileBaseDir, applFormfilePath);
            FileUtils.writeByteArrayToFile(applFormFile, bytes);

            filePath = s3FilePath + "/" + FilePathUtil.getZippedFileName(application);
            bytes = getBytesFromS3Object(filePath);
            String fileName = FilePathUtil.getDownloadableZipFileName(application);
            File zipFile = new File(localDirPath, fileName);
            FileUtils.writeByteArrayToFile(zipFile, bytes);

            List<File> fileList = new ArrayList<>();
            fileList.add(applFormFile);
            File mergedZipFile = zipService.appendFilesToZipFile(fileList, zipFile);
            bytes = FileUtils.readFileToByteArray(mergedZipFile);

            if (applFormFile.exists()) applFormFile.delete();
            if (zipFile.exists()) zipFile.delete();
        }

        return bytes;
    }

    private byte[] getBytesFromS3Object(String filePath) throws IOException {
        S3Object object = s3Client.getObject(new GetObjectRequest(s3BucketName, filePath));
        InputStream inputStream = object.getObjectContent();
        byte[] bytes = IOUtils.toByteArray(inputStream);
        return bytes;
    }

    /**
     * 수험표 다운로드
     * @return
     * @throws java.io.IOException
     */

    @RequestMapping(value="/search/applSlipDownload", produces = "application/pdf")
    public ModelAndView applSlipDownload(@RequestParam("applNo") int applNo,
                                         @RequestParam("admsTypeCode") String admsTypeCode,
                                         Principal principal,
                                         HttpServletResponse response,
                                         ModelAndView mv) throws IOException {

        mv.setViewName("pdfSingleFormatBirtDownload");

        Map<String, Object> bigDataMap = null;
        String lang = "C".equals(admsTypeCode) || "D".equals(admsTypeCode) ? "en" : "kr";
        String reportName = "yonsei-adms-" + lang;
        mv.addObject("reportFormat", REPORT_FORMAT);
        mv.addObject("reportName", reportName);
        ExecutionContext ec = birtService.processBirt(applNo, reportName);
        bigDataMap = (Map<String, Object>)ec.getData();
        mv.addAllObjects(bigDataMap);

        return mv;
    }

//    /**
//     * 수험표 다운로드
//     * @return
//     * @throws java.io.IOException
//     */
//
//    @RequestMapping(value="/search/applFormDownload", produces = "application/pdf")
//    public ModelAndView applFormDownload(@RequestParam("applNo") int applNo,
//                                         @RequestParam("admsTypeCode") String admsTypeCode,
//                                         Principal principal,
//                                         HttpServletResponse response,
//                                         ModelAndView mv) throws IOException {
//
//        mv.setViewName("pdfSingleFormatBirtDownload");
//
//        Map<String, Object> bigDataMap = null;
//        String lang = "C".equals(admsTypeCode) || "D".equals(admsTypeCode) ? "en" : "kr";
//        String reportName = "yonsei-adms-" + lang;
//        mv.addObject("reportFormat", REPORT_FORMAT);
//        mv.addObject("reportName", reportName);
//        ExecutionContext ec = birtService.processBirt(applNo, reportName);
//        bigDataMap = (Map<String, Object>)ec.getData();
//        mv.addAllObjects(bigDataMap);
//
//        return mv;
//    }

    /**
     * 전체 파일 다운로드
     * @return
     * @throws java.io.IOException
     */

    @RequestMapping(value="/search/excelDownload", produces = "application/pdf")
    @ResponseBody
    public ModelAndView  ExcelfileDownload( @ModelAttribute CourseSearchPageForm courseSearchPageForm,
                                            BindingResult bindingResult,
                                            Principal principal,
                                            HttpServletResponse respons) throws Exception {
        ModelAndView mv =new ModelAndView();
        ExecutionContext ecRetrieve = adminService.retrieveEntireApplicantListByDept(courseSearchPageForm);
        Map<String, Object> map = (Map<String, Object>)ecRetrieve.getData();
        mv.setViewName("ApplicantListDownload");
        mv.addObject("applList", map.get("applList"));
        mv.addObject("searchForm", map.get("searchForm"));
        return mv;

    }

    private ModelAndView setSearchOptions(ExecutionContext ec, ModelAndView mv) {
        if (ec.isSuccessful()) {
            Map<String, Object> map = (Map<String, Object>)ec.getData();
            mv.addObject("selection", map.get("selection"));
            mv.addObject("searchPageForm", map.get("searchPageForm"));
            mv.addObject("applList", map.get("applList"));
        } else {
            mv = getErrorMV("common/error", ec);
        }
        return mv;
    }

}
