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
import com.apexsoft.ysprj.admin.control.form.*;
import com.apexsoft.ysprj.admin.domain.ApplicationChange;
import com.apexsoft.ysprj.admin.domain.CustomApplicationChange;
import com.apexsoft.ysprj.admin.service.ChangeService;
import com.apexsoft.ysprj.user.domain.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.ysprj.admin.domain.ApplicantCnt;
import com.apexsoft.ysprj.admin.domain.ApplicantInfo;
import com.apexsoft.ysprj.admin.service.AdminService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.servlet.ModelAndView;

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


    @RequestMapping(value="/login", method= RequestMethod.GET)
    public ModelAndView displayLoginForm(Users users,
                                         BindingResult bindingResult,
                                         ModelAndView mv,
                                         HttpServletRequest request) {
        mv.setViewName("admin/login/adminLogin");
        if (bindingResult.hasErrors()) return mv;

        if (request.getAttribute("LOGIN_FAILURE") == Boolean.TRUE)
            mv.addObject("loginMessage", messageResolver.getMessage("U330"));

        return mv;
    }
    
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
    @RequestMapping(value="/stats")
    public String initAdmin() {
        return "admin/stats/category";
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
    public String statsUnpaid() {
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


    
    @RequestMapping(value="/modification/searchAdms")
    public String searchAdms(ApplicantSearchForm searchForm, Model model) {
   		ApplicantInfo applInfo =null;
   		applInfo = adminService.getApplicantInfo(searchForm);	
        model.addAttribute("applInfo", applInfo);    	
        return "admin/modification/changeInfo";
    }

    @RequestMapping(value="/modification/changeList")
    public ModelAndView retrieveModificationList(@ModelAttribute ChangeSearchPageForm searchPageForm,
                                   BindingResult bindingResult,
                                   ModelAndView mv) {
        mv.setViewName("admin/modification/changeList");
        ExecutionContext ec;
        if (bindingResult.hasErrors()) {
            mv.addObject("resultMsg", messageResolver.getMessage("U334"));

        }
        ExecutionContext ecRetrieve = changeService.retrieveChangePaginatedList(searchPageForm);
        if (ecRetrieve.getResult().equals(ExecutionContext.SUCCESS)) {
            Map<String, Object> map = (Map<String, Object>)ecRetrieve.getData();
            mv.addObject("selection", map.get("selection"));
            mv.addObject("changeSearchPageForm", map.get("changeSearchPageForm"));
            mv.addObject("chgList", map.get("chgList"));
        } else {
            mv = getErrorMV("common/error", ecRetrieve);
        }
        return mv;
    }


    @RequestMapping(value="/modification/changeInfo")
    public ModelAndView callChangeInfo( @ModelAttribute ApplicantSearchForm applicantSearchForm,
                                        BindingResult bindingResult,
                                        ModelAndView mv) {

        mv.setViewName("admin/modification/changeInfo");

        if (bindingResult.hasErrors()) {
            mv.addObject("resultMsg", messageResolver.getMessage("U334"));

        }
        ExecutionContext ecRetrieve = adminService.getApplicantDetail(applicantSearchForm.getApplNo(),applicantSearchForm.getApplId() );
        if (ecRetrieve.getResult().equals(ExecutionContext.SUCCESS)) {
            Map<String, Object> map = (Map<String, Object>)ecRetrieve.getData();
            mv.addObject("selection", map.get("selection"));
            mv.addObject("applicantSearchForm",applicantSearchForm);
            mv.addObject("applInfo", map.get("applInfo"));
            mv.addObject("customApplicationChange", new CustomApplicationChange());
        } else {
            mv = getErrorMV("common/error", ecRetrieve);
        }
        return mv;
    }

    @RequestMapping(value="/modification/changeUnit")
      public ModelAndView callChangeUnit( @ModelAttribute ApplicantSearchForm applicantSearchForm,
                                          BindingResult bindingResult,
                                          ModelAndView mv) {

        mv.setViewName("admin/modification/changeUnit");

        if (bindingResult.hasErrors()) {
            mv.addObject("resultMsg", messageResolver.getMessage("U334"));

        }
        ExecutionContext ecRetrieve = adminService.getApplicantDetail(applicantSearchForm.getApplNo(),applicantSearchForm.getApplId() );
        if (ecRetrieve.getResult().equals(ExecutionContext.SUCCESS)) {
            Map<String, Object> map = (Map<String, Object>)ecRetrieve.getData();
            mv.addObject("selection", map.get("selection"));
            mv.addObject("applicantSearchForm",applicantSearchForm);
            mv.addObject("applInfo", map.get("applInfo"));
            mv.addObject("customApplicationChange", new CustomApplicationChange());
        } else {
            mv = getErrorMV("common/error", ecRetrieve);
        }
        return mv;
    }

    @RequestMapping(value="/modification/cancelAppl")
    public ModelAndView callCancelInfo( @ModelAttribute ApplicantSearchForm applicantSearchForm,
                                        BindingResult bindingResult,
                                        ModelAndView mv) {

        mv.setViewName("admin/modification/cancelAppl");

        if (bindingResult.hasErrors()) {
            mv.addObject("resultMsg", messageResolver.getMessage("U334"));

        }
        ExecutionContext ecRetrieve = adminService.getApplicantDetail(applicantSearchForm.getApplNo(),applicantSearchForm.getApplId() );
        if (ecRetrieve.getResult().equals(ExecutionContext.SUCCESS)) {
            Map<String, Object> map = (Map<String, Object>)ecRetrieve.getData();
            mv.addObject("selection", map.get("selection"));
            mv.addObject("applicantSearchForm",applicantSearchForm);
            mv.addObject("applInfo", map.get("applInfo"));
            mv.addObject("customApplicationChange", new CustomApplicationChange());
        } else {
            mv = getErrorMV("common/error", ecRetrieve);
        }
        return mv;
    }

    @RequestMapping(value="/modification/requestChangeInfo")
    public ModelAndView changeApplicaitonInfo (@ModelAttribute ChangeInfoForm changeInfoForm,
                                               Principal principal,
                                               BindingResult bindingResult,
                                               ModelAndView mv) {


        mv.setViewName("admin/modification/changeList");

        if (bindingResult.hasErrors()) {
            mv.addObject("resultMsg", messageResolver.getMessage("U334"));

        }

        //TODO 로그인 정보로 변경
        String userId = "test";
        //String userId = principal.getName();
        ExecutionContext ecRetrieve = changeService.createInfoChange(changeInfoForm,userId);
        if (ecRetrieve.getResult().equals(ExecutionContext.SUCCESS)) {

            ChangeSearchPageForm searchForm = new ChangeSearchPageForm();
            searchForm.setAdmsNo(changeInfoForm.getAdmsNo());
            searchForm.setApplChgCode("");
            searchForm.setChgStsCode("");
            searchForm.setCampCode("");
            searchForm.setCollCode("");


            ecRetrieve = changeService.retrieveChangePaginatedList(searchForm);
            if (ecRetrieve.getResult().equals(ExecutionContext.SUCCESS)) {
                Map<String, Object> map = (Map<String, Object>) ecRetrieve.getData();
                mv.addObject("selection", map.get("selection"));
                mv.addObject("changeSearchPageForm", map.get("changeSearchPageForm"));
                mv.addObject("chgList", map.get("chgList"));
            }else{
                mv = getErrorMV("common/error", ecRetrieve);
            }
        } else {
            mv = getErrorMV("common/error", ecRetrieve);
        }
        return mv;

    }

    @RequestMapping(value="/modification/requestChangeUnit")
    public ModelAndView modificationUnit(@ModelAttribute CustomApplicationChange changeInfoForm,
                                   Principal principal,
                                   BindingResult bindingResult,
                                   ModelAndView mv) {

        mv.setViewName("admin/modification/changeList");

        if (bindingResult.hasErrors()) {
            mv.addObject("resultMsg", messageResolver.getMessage("U334"));
        }

        //TODO 로그인 정보로 변경
        String userId = "test";
        //String userId = principal.getName();
        ExecutionContext ecRetrieve = changeService.createUnitChange(changeInfoForm,userId);
        if (ecRetrieve.getResult().equals(ExecutionContext.SUCCESS)) {

            ChangeSearchPageForm searchForm = new ChangeSearchPageForm();
            searchForm.setAdmsNo(changeInfoForm.getAdmsNo());
            searchForm.setApplChgCode("");
            searchForm.setChgStsCode("");

            ecRetrieve = changeService.retrieveChangePaginatedList(searchForm);
            if (ecRetrieve.getResult().equals(ExecutionContext.SUCCESS)) {
                Map<String, Object> map = (Map<String, Object>) ecRetrieve.getData();
                mv.addObject("selection", map.get("selection"));
                mv.addObject("changeSearchPageForm", map.get("changeSearchPageForm"));
                mv.addObject("chgList", map.get("chgList"));
            }else{
                mv = getErrorMV("common/error", ecRetrieve);
            }
        } else {
            mv = getErrorMV("common/error", ecRetrieve);
        }
        return mv;
    }
    @RequestMapping(value="/modification/requestCancel")
    public ModelAndView cancelApplication(@ModelAttribute ChangeInfoForm changeInfoForm,
                                         Principal principal,
                                         BindingResult bindingResult,
                                         ModelAndView mv) {

        mv.setViewName("admin/modification/changeList");

        if (bindingResult.hasErrors()) {
            mv.addObject("resultMsg", messageResolver.getMessage("U334"));
        }

        //TODO 로그인 정보로 변경
        String userId = "test";
        //String userId = principal.getName();
        ExecutionContext ecRetrieve = changeService.createApplicationCancel(changeInfoForm,userId);
        if (ecRetrieve.getResult().equals(ExecutionContext.SUCCESS)) {

            ChangeSearchPageForm searchForm = new ChangeSearchPageForm();
            searchForm.setAdmsNo(changeInfoForm.getAdmsNo());
            searchForm.setApplChgCode("");
            searchForm.setChgStsCode("");

            ecRetrieve = changeService.retrieveChangePaginatedList(searchForm);
            if (ecRetrieve.getResult().equals(ExecutionContext.SUCCESS)) {
                Map<String, Object> map = (Map<String, Object>) ecRetrieve.getData();
                mv.addObject("selection", map.get("selection"));
                mv.addObject("changeSearchPageForm", map.get("changeSearchPageForm"));
                mv.addObject("chgList", map.get("chgList"));
            }else{
                mv = getErrorMV("common/error", ecRetrieve);
            }
        } else {
            mv = getErrorMV("common/error", ecRetrieve);
        }
        return mv;
    }
    @RequestMapping(value="/modification/changeInfoDetail")
    public ModelAndView retreiveChangeDetail(@ModelAttribute String chgId,
                                             Principal principal,
                                             BindingResult bindingResult,
                                             ModelAndView mv){
        mv.setViewName("admin/modification/changeInfoDetail");
        if (bindingResult.hasErrors()) {
            mv.addObject("resultMsg", messageResolver.getMessage("U334"));
        }
        ExecutionContext ecRetrieve =ecRetrieve = changeService.retrieveChangeDetail(chgId);
        if (ecRetrieve.getResult().equals(ExecutionContext.SUCCESS)) {
                Map<String, Object> map = (Map<String, Object>) ecRetrieve.getData();
                mv.addObject("chgInfo", map.get("chgInfo"));

        } else {
            mv = getErrorMV("common/error", ecRetrieve);
        }
        return mv;

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
}
