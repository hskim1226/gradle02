package com.apexsoft.ysprj.admin.control;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.exception.ErrorInfo;
import com.apexsoft.framework.exception.YSBizException;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.ysprj.admin.control.form.CourseSearchGridForm;
import com.apexsoft.ysprj.admin.control.form.CourseSearchPageForm;
import com.apexsoft.ysprj.admin.domain.ApplicantCnt;
import com.apexsoft.ysprj.admin.domain.ApplicantInfo;
import com.apexsoft.ysprj.admin.domain.ApplicantInfoEntire;
import com.apexsoft.ysprj.admin.service.AdminService;
import com.apexsoft.ysprj.admin.service.ChangeService;
import com.apexsoft.ysprj.admin.service.PostApplicationService;
import com.apexsoft.ysprj.applicants.application.domain.ApplicationDocument;
import com.apexsoft.ysprj.applicants.application.service.DocumentService;
import com.apexsoft.ysprj.applicants.common.service.PDFService;
import com.apexsoft.ysprj.user.domain.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import com.apexsoft.ysprj.applicants.common.util.FileUtil;
import org.springframework.web.bind.ServletRequestUtils;


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
    private PostApplicationService postApplicationService;

    @Autowired
    private ObjectMapper jacksonObjectMapper;    
    
    @SuppressWarnings("restriction")
	@Resource(name = "messageResolver")
    private MessageResolver messageResolver;


    @Value("#{app['file.baseDir']}")
    private String fileBaseDir;

    @Value("#{app['s3.bucketName']}")
    private String s3BucketName;

    @Value("#{app['s3.midPath']}")
    private String s3MidPath;


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
        if (ecRetrieve.getResult().equals(ExecutionContext.SUCCESS)) {
            Map<String, Object> map = (Map<String, Object>)ecRetrieve.getData();
            mv.addObject("selection", map.get("selection"));
            mv.addObject("searchPageForm", map.get("searchPageForm"));
            mv.addObject("applList", map.get("applList"));

        } else {
            mv = getErrorMV("common/error", ecRetrieve);
        }
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
        if (ecRetrieve.getResult().equals(ExecutionContext.SUCCESS)) {
            Map<String, Object> map = (Map<String, Object>)ecRetrieve.getData();
            mv.addObject("selection", map.get("selection"));
            mv.addObject("searchPageForm", map.get("searchPageForm"));
            mv.addObject("applList", map.get("applList"));

        } else {
            mv = getErrorMV("common/error", ecRetrieve);
        }
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
        if (ecRetrieve.getResult().equals(ExecutionContext.SUCCESS)) {
            Map<String, Object> map = (Map<String, Object>)ecRetrieve.getData();
            mv.addObject("selection", map.get("selection"));
            mv.addObject("searchPageForm", map.get("searchPageForm"));
            mv.addObject("applList", map.get("applList"));
        } else {
            mv = getErrorMV("common/error", ecRetrieve);
        }
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
        if (ecRetrieve.getResult().equals(ExecutionContext.SUCCESS)) {
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
     * 전체 파일 다운로드
     * @return
     * @throws java.io.IOException
     */

    @RequestMapping(value="/search/pdfDownload", produces = "application/pdf")
    @ResponseBody
    public byte[] fileDownload(@RequestParam("applNo") int applNo, Principal principal, HttpServletResponse response)
            throws IOException {

        List<ApplicationDocument> applPaperInfosList =
                documentService.retrieveApplicationPaperInfo(applNo); // DB에서 filePath가져온다

        String applPaperLocalFilePath = applPaperInfosList.get(0).getFilePath();
        String s3FilePath = FileUtil.getS3PathFromLocalFullPath(applPaperLocalFilePath, fileBaseDir);
        String filePath = FileUtil.getFinalMergedFileFullPath(s3FilePath, applNo);

        AmazonS3 s3 = new AmazonS3Client();
        S3Object object = s3.getObject(new GetObjectRequest(s3BucketName, filePath));
        InputStream inputStream = object.getObjectContent();
        byte[] bytes = IOUtils.toByteArray(inputStream);

        String userId = principal.getName();
        ExecutionContext ecRetrieve = null;
        ecRetrieve = postApplicationService.checkDocumentRead(applNo, userId);
        Map<String, Object> map = (Map<String, Object>)ecRetrieve.getData();
        ApplicantInfo appInfo = (ApplicantInfo) map.get("applInfo");
        // 파일명 처리하세요.
        String fileName;
        if( "15B".equals(appInfo.getAdmsNo())){
            fileName = appInfo.getApplId() + "-" + appInfo.getKorName()+ "-"+ appInfo.getApplAttrName().replaceAll("\\p{Space}", "" )+ "-" + appInfo.getDeptName()+ "-" + appInfo.getCorsTypeName()  + ".pdf";
        }else if ( "15D".equals(appInfo.getAdmsNo())){
            fileName = appInfo.getApplId()+ "-" + appInfo.getEngName()+"-" + "외국인전형" + "-" + appInfo.getDeptName()+"-" + appInfo.getCorsTypeName()  + ".pdf";
        }else{
            fileName = appInfo.getApplId() + "-" + appInfo.getKorName()+"-" + "조기전형" + "-" + appInfo.getDeptName()+"-" + appInfo.getCorsTypeName()  + ".pdf";
        }
        response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode("test.pdf", "UTF-8") + "\"");
        response.setHeader("Content-Transfer-Encoding", "binary;");
        response.setHeader("Pragma", "no-cache;");
        response.setHeader("Expires", "-1;");
        response.setHeader("Content-Type", "application/octet-stream");
        response.setContentLength(bytes.length);

        return bytes;
    }

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

}
