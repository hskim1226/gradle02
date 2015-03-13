package com.apexsoft.ysprj.admin.control;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.ysprj.admin.control.form.CourseSearchGridForm;
import com.apexsoft.ysprj.admin.control.form.CourseSearchPageForm;
import com.apexsoft.ysprj.admin.domain.ApplicantCnt;
import com.apexsoft.ysprj.admin.service.AdminService;
import com.apexsoft.ysprj.admin.service.ChangeService;
import com.apexsoft.ysprj.user.domain.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.io.File;
import java.io.IOException;
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
    private AdminService adminService;

    @Autowired
    private ChangeService changeService;

    @Autowired
    private ObjectMapper jacksonObjectMapper;    
    
    @SuppressWarnings("restriction")
	@Resource(name = "messageResolver")
    private MessageResolver messageResolver;

    @Value("#{app['file.baseDir']}")
    private String fileBaseDir;


    @RequestMapping(value="/stats/daily")
    public String statsDaily(Model model) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap = adminService.getCouurseSelectionBasicMap();
        ExecutionContext ec = new ExecutionContext();
        model.addAttribute("admsList", modelMap.get("admsList"));
        return "admin/stats/recentDay";
    }
    
    @RequestMapping(value="/stats/daily/search")

    public String statsDailySearch(CourseSearchGridForm searchForm)
    		throws NoSuchAlgorithmException, JsonProcessingException, UnsupportedEncodingException {
        List<ApplicantCnt> pStsList =null;
        pStsList = adminService.retrieveApplicantCntByDept(searchForm);

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("total",pStsList.size());
        modelMap.put("records", pStsList.size());
        modelMap.put("rows",pStsList);
        modelMap.put("page", 1);

        String value = mapper.writeValueAsString(modelMap);

        return value;
    }

    @RequestMapping(value="/stats")
    public String initAdmin() {
        return "admin/stats/category";
    }
    
    @RequestMapping(value="/stats/category")
    public String statsCategory(Model model) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap = adminService.getCouurseSelectionBasicMap();
        ExecutionContext ec = new ExecutionContext();
        model.addAttribute("admsList", modelMap.get("admsList"));
        return "admin/stats/category";
    }
       
	@RequestMapping(value="/stats/category/search")   
	@ResponseBody	
    public String statsCategorySearch(CourseSearchGridForm searchForm)
    		throws NoSuchAlgorithmException, JsonProcessingException, UnsupportedEncodingException {    	
   		List<ApplicantCnt> pStsList =null;
        List<String> headList = null;
   		pStsList = adminService.retrieveApplicantCntByRecent(searchForm);

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("total",pStsList.size());
        modelMap.put("records", pStsList.size());
        modelMap.put("rows",pStsList);
        modelMap.put("header",headList);
        modelMap.put("page", 1);

        String value = mapper.writeValueAsString(modelMap);

        return value;
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
        model.addAttribute("applInfo", map.get("applInfo"));

        return "admin/search/applInfoDetail";
    }    

    @RequestMapping(value="/search/unpaid")
    public String statsUnpaid(Model model) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap = adminService.getCouurseSelectionBasicMap();
        ExecutionContext ec = new ExecutionContext();
        model.addAttribute("admsList", modelMap.get("admsList"));
        return "admin/stats/unpaid";
    }


    @RequestMapping(value="/search/unpaid/search")
    @ResponseBody
    public String statsUnpaidSearch(CourseSearchGridForm searchForm)
            throws NoSuchAlgorithmException, JsonProcessingException, UnsupportedEncodingException {
        List<ApplicantCnt> pStsList =null;
        pStsList = adminService.retrieveUnpaidApplicantCntByDept(searchForm);

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("total",pStsList.size());
        modelMap.put("records", pStsList.size());
        modelMap.put("rows",pStsList);
        modelMap.put("page", 1);

        String value = mapper.writeValueAsString(modelMap);

        return value;
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


    private ModelAndView getErrorMV(String errorViewName, ExecutionContext ec) {
        ModelAndView mv = new ModelAndView(errorViewName);
        mv.addObject("ec", ec);
        return mv;
    }

    /**
     * 전체 파일 다운로드
     * @return
     * @throws java.io.IOException
     */

    @RequestMapping(value="/search/download", produces = "application/pdf")
    @ResponseBody
    public byte[] fileDownload( @ModelAttribute CourseSearchPageForm courseSearchPageForm,
                                BindingResult bindingResult,
                               Principal principal,
                               HttpServletResponse response)
            throws IOException {


        String admsNo = courseSearchPageForm.getAdmsNo();
        int applNo = courseSearchPageForm.getApplNo();
        String userId = courseSearchPageForm.getUserId();

        String uploadDirectoryFullPath = FileUtil.getUploadDirectoryFullPath(fileBaseDir, admsNo, userId, applNo);
        //TODO 파일명 FileUtil 통해 해결하도록 수정 필요

        String fileFileFullPath = FileUtil.getFinalMergedFileFullPath(uploadDirectoryFullPath, applNo);
        String fileName = applNo + "-merged-numbered.pdf";
        String downLoadFileName = userId + "_all.pdf";
        File file =  new File(fileFileFullPath, fileName);
        byte[] bytes = org.springframework.util.FileCopyUtils.copyToByteArray(file);

        response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(downLoadFileName, "UTF-8") + "\"");
        response.setHeader("Content-Transfer-Encoding", "binary;");
        response.setHeader("Pragma", "no-cache;");
        response.setHeader("Expires", "-1;");
        response.setHeader("Content-Type", "application/pdf");
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

       // ExecutionContext ecRetrieve = adminService.getApplicantDetail();
        //Map<String, Object> map = (Map<String, Object>)ecRetrieve.getData();

        Map<String,String> revenueData = new HashMap<String,String>();
        revenueData.put("Jan-2010", "$100,000,000");
        revenueData.put("Feb-2010", "$110,000,000");
        revenueData.put("Mar-2010", "$130,000,000");
        revenueData.put("Apr-2010", "$140,000,000");
        revenueData.put("May-2010", "$200,000,000");

        return new ModelAndView("ExcelRevenueSummary","revenueData",revenueData);

    }

}
