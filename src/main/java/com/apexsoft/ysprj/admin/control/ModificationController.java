package com.apexsoft.ysprj.admin.control;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.ysprj.admin.control.form.ApplicantSearchForm;
import com.apexsoft.ysprj.admin.control.form.ChangeInfoForm;
import com.apexsoft.ysprj.admin.control.form.ChangeSearchPageForm;
import com.apexsoft.ysprj.admin.domain.ApplicantInfo;
import com.apexsoft.ysprj.admin.domain.CustomApplicationChange;
import com.apexsoft.ysprj.admin.service.AdminService;
import com.apexsoft.ysprj.admin.service.ChangeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.Map;

//import com.apexsoft.ysprj.user.domain.Users;

/**
 * Created by hanmomhanda on 14. 8. 6.
 */
@Controller
@RequestMapping(value="/admin")
public class ModificationController {


    @Autowired
    private AdminService adminService;

    @Autowired
    private ChangeService changeService;

    @Autowired
    private ObjectMapper jacksonObjectMapper;    
    
    @SuppressWarnings("restriction")
	@Resource(name = "messageResolver")
    private MessageResolver messageResolver;


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
    @RequestMapping(value="/modification/changeEtc")
    public ModelAndView callChangeEtc( @ModelAttribute ApplicantSearchForm applicantSearchForm,
                                        BindingResult bindingResult,
                                        ModelAndView mv) {

        mv.setViewName("admin/modification/changeEtc");

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
        String userId = principal.getName();
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


    @RequestMapping(value="/modification/requestChangeEtc")
    public ModelAndView modificationEtcInfo(@ModelAttribute ChangeInfoForm changeInfoForm,
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
        ExecutionContext ecRetrieve = changeService.createEtcInfoChange(changeInfoForm,userId);
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




    private ModelAndView getErrorMV(String errorViewName, ExecutionContext ec) {
        ModelAndView mv = new ModelAndView(errorViewName);
        mv.addObject("ec", ec);
        return mv;
    }
}
