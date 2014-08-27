package com.apexsoft.ysprj.admin.web;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apexsoft.framework.persistence.dao.page.PageInfo;
import com.apexsoft.ysprj.admin.domain.ApplicantInfo;
import com.apexsoft.ysprj.admin.service.AdminService;
import com.apexsoft.ysprj.admin.web.form.ApplicantSearchForm;
import com.apexsoft.ysprj.user.domain.Users;
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

    
    @RequestMapping(value="/stats/daily")
    public String statsDaily() {
        return "admin/stats/daily";
    }

    @RequestMapping(value="/stats/category")
    public String statsCategory() {
        return "admin/stats/category";
    }

    @RequestMapping(value="/search/applicants")
    @ResponseBody
    public String searchApplicants( ApplicantSearchForm applicantSearchForm)
    		throws NoSuchAlgorithmException, JsonProcessingException, UnsupportedEncodingException { 
    	PageInfo<ApplicantInfo> pInfo =adminService.retrieveApplicantPaginatedListByPersionalInfo(applicantSearchForm);
        String json = new ObjectMapper().writeValueAsString(pInfo);
        return json;    	
    }

    @RequestMapping(value="/stats/unpaid")
    public String statsUnpaid() {
        return "admin/stats/unpaid";
    }

    @RequestMapping(value="/modification/list")
    public String modificationList() {
        return "admin/modification/list";
    }

    @RequestMapping(value="/modification/request")
    public String modificationRequest() {
        return "admin/modification/request";
    }

    @RequestMapping(value="/modification/unit")
    public String modificationUnit() {
        return "admin/modification/unit";
    }

    @RequestMapping(value="/cancel/application")
    public String cancelApplication() {
        return "admin/cancel/application";
    }


}
