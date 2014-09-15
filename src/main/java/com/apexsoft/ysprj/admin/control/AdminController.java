package com.apexsoft.ysprj.admin.control;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.apexsoft.ysprj.admin.control.form.*;
import com.apexsoft.ysprj.admin.domain.ChangeInfo;
import com.apexsoft.ysprj.admin.service.ChangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.framework.persistence.dao.page.PageInfo;
import com.apexsoft.ysprj.admin.domain.ApplicantCnt;
import com.apexsoft.ysprj.admin.domain.ApplicantInfo;
import com.apexsoft.ysprj.admin.service.AdminService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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

    
    @RequestMapping(value="/stats/daily")
    public String statsDaily() {
        return "admin/stats/daily";
    }
    
    @RequestMapping(value="/stats/daily/search")

    public List<ApplicantCnt> statsDailySearch(CourseSearchGridForm searchForm)
    		throws NoSuchAlgorithmException, JsonProcessingException, UnsupportedEncodingException {
    	
   		List<ApplicantCnt> pStsList =null;
   		pStsList = adminService.retrieveApplicantCntByDept(searchForm);	
   		return pStsList;
	}
  
    
    @RequestMapping(value="/stats/category")
    public String statsCategory() {
        return "admin/stats/category";
    }
       
	@RequestMapping(value="/stats/category/search")   
	@ResponseBody	
    public String statsCategorySearch(CourseSearchGridForm searchForm)
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
    public String searchApplicantByName( ApplicantSearchPageForm searchForm, Model model)
    		throws NoSuchAlgorithmException, JsonProcessingException, UnsupportedEncodingException { 
   		PageInfo<ApplicantInfo> pInfo =null;

    	pInfo = adminService.retrieveApplicantPaginatedListByName(searchForm);	

        model.addAttribute("applList", pInfo.getData());
        model.addAttribute("applTotal", pInfo.getTotalRowCount());
        return "admin/search/applicantsName";
    }
    
    @RequestMapping(value="/search/applicants/idSearch")
    public String searchApplicantById(ApplicantSearchPageForm searchForm, Model model)
    		throws NoSuchAlgorithmException, JsonProcessingException, UnsupportedEncodingException {
        PageInfo<ApplicantInfo> pInfo =null;

        pInfo = adminService.retrieveApplicantPaginatedListByName(searchForm);

        model.addAttribute("applList", pInfo.getData());
        model.addAttribute("applTotal", pInfo.getTotalRowCount());

        return "admin/search/applicantsId";
    }
    
    @RequestMapping(value="/search/applicants/deptSearch")
    public String searchApplicantByDept( CourseSearchPageForm searchForm, Model model)
    		throws NoSuchAlgorithmException, JsonProcessingException, UnsupportedEncodingException { 
   		PageInfo<ApplicantInfo> pInfo =null;

    	pInfo = adminService.retrieveApplicantPaginatedListByDept(searchForm);	

        model.addAttribute("applList", pInfo.getData());
        model.addAttribute("applTotal", pInfo.getTotalRowCount());

        return "admin/search/applicantsDept";
    }    
    
    @RequestMapping(value="/search/applicant/applInfoDetail")
    public String displayQnaDetail(@RequestParam("applNo") int applNo, Model model){
    	ApplicantInfo applicantInfo = adminService.getApplicantDetail(applNo);

        model.addAttribute("applInfo", applicantInfo);

        return "admin/search/applInfoDetail";
    }    

    @RequestMapping(value="/search/unpaid")
    public String statsUnpaid() {
        return "admin/search/unpaid";
    }
    
    @RequestMapping(value="/modification/searchAdms")
    public String searchAdms(ApplicantSearchForm searchForm, Model model) {
   		ApplicantInfo applInfo =null;
   		applInfo = adminService.getApplicantInfo(searchForm);	
        model.addAttribute("applInfo", applInfo);    	
        return "admin/modification/changeInfo";
    }

    @RequestMapping(value="/modification/changeList")
    public String modificationList() {
        return "admin/modification/changeList";
    }

    @RequestMapping(value="/modification/changeInfo")
    public String callChangeInfo(@RequestParam("applNo") int applNo, Model model) {
        ApplicantInfo applicantInfo = adminService.getApplicantDetail(applNo);
        model.addAttribute("applInfo", applicantInfo);
        return "admin/modification/changeInfo";
    }

    @RequestMapping(value="/modification/changeInfoInit")
    public String modificationRequestInit(  ChangeSearchPageForm searchForm, Model model)
            throws NoSuchAlgorithmException, JsonProcessingException, UnsupportedEncodingException {
        PageInfo<ChangeInfo> pInfo =null;

        pInfo = changeService.retrieveChangePaginatedList(searchForm);

        model.addAttribute("chgList", pInfo.getData());
        model.addAttribute("chgTotal", pInfo.getTotalRowCount());
        return "admin/modification/changeList";
    }
    @RequestMapping(value="/modification/requestChangeInfo")
    public String requestItemChange (ChangeInfoForm changeInfoForm, Model model)
            throws NoSuchAlgorithmException, JsonProcessingException, UnsupportedEncodingException {

        changeService.createInfoChange(changeInfoForm);

        PageInfo<ChangeInfo> pInfo =null;
        ChangeSearchPageForm searchForm = new ChangeSearchPageForm();
        pInfo = changeService.retrieveChangePaginatedList(searchForm);

        model.addAttribute("chgList", pInfo.getData());
        model.addAttribute("chgTotal", pInfo.getTotalRowCount());
        return "admin/modification/changeList";

    }

    @RequestMapping(value="/modification/changeUnit")
    public String modificationUnit() {
        return "admin/modification/changeUnit";
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
    
    

}
