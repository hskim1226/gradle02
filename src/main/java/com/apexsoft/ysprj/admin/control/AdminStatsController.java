package com.apexsoft.ysprj.admin.control;


import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.ysprj.admin.control.form.CourseSearchGridForm;
import com.apexsoft.ysprj.admin.domain.ApplicantCnt;
import com.apexsoft.ysprj.admin.domain.ApplicantDetailCnt;
import com.apexsoft.ysprj.admin.service.AdminService;
import com.apexsoft.ysprj.admin.service.AdminStatsService;
import com.apexsoft.ysprj.admin.service.PostApplicationService;
import com.apexsoft.ysprj.applicants.application.service.DocumentService;
import com.apexsoft.ysprj.applicants.common.service.PDFService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by hanmomhanda on 14. 8. 6.
 */
@Controller
@RequestMapping(value="/admin")
public class AdminStatsController {

    @Autowired
    PDFService pdfService;

    @Autowired
    DocumentService documentService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private AdminStatsService adminStatsService;

    @Autowired
    private PostApplicationService postApplicationService;

    @Autowired
    private ObjectMapper jacksonObjectMapper;

    @Value("#{app['file.baseDir']}")
    private String fileBaseDir;

    @Value("#{app['s3.bucketName']}")
    private String s3BucketName;

    @Value("#{app['file.midPath']}")
    private String s3MidPath;

    private ModelAndView getErrorMV(String errorViewName, ExecutionContext ec) {
        ModelAndView mv = new ModelAndView(errorViewName);
        mv.addObject("ec", ec);
        return mv;
    }

    // 최근 1주일간 지원자 인원수 통계 초기화면
    @RequestMapping(value="/stats/basicCntByWeekly")
    public String statsWeekly(Model model) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap = adminService.getCouurseSelectionBasicMap();
        ExecutionContext ec = new ExecutionContext();
        model.addAttribute("admsList", modelMap.get("admsList"));
        return "admin/stats/basicCntByWeekly";
    }

    // 최근 1주일간 지원자 인원수 통계 화면
    @RequestMapping(value="/stats/basicCntByWeekly/search")
    @ResponseBody
    public String searchStatsWeekly(CourseSearchGridForm searchForm)
            throws NoSuchAlgorithmException, JsonProcessingException, UnsupportedEncodingException {
        List<ApplicantCnt> pStsList = null;
        List<String> headList = null;
        pStsList = adminStatsService.retrieveApplicantCntByRecent(searchForm);

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("total", pStsList.size());
        modelMap.put("records", pStsList.size());
        modelMap.put("rows", pStsList);
        modelMap.put("header", headList);
        modelMap.put("page", 1);

        String value = mapper.writeValueAsString(modelMap);

        return value;
    }

    //학과별 지원자 인원수 통계 첫화면
    @RequestMapping(value="/stats/basicCntByDept")
    public String statsByDept(Model model) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap = adminService.getCouurseSelectionBasicMap();
        ExecutionContext ec = new ExecutionContext();
        model.addAttribute("admsList", modelMap.get("admsList"));
        return "admin/stats/basicCntByDept";
    }

    //학과별 지원자 인원수 통계 화면
	@RequestMapping(value="/stats/basicCntByDept/search")
         @ResponseBody
         public String searchStatsByDept(CourseSearchGridForm searchForm)
            throws NoSuchAlgorithmException, JsonProcessingException, UnsupportedEncodingException {
        List<ApplicantCnt> pStsList =null;
        List<String> headList = null;
        pStsList = adminStatsService.retrieveApplicantCntByDept(searchForm);


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

    //학과별 지원자 인원수 상세 통계 초기화면
    @RequestMapping(value="/stats/detailCntByDept")
    public String detailStatsByDept(Model model) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap = adminService.getCouurseSelectionBasicMap();
        ExecutionContext ec = new ExecutionContext();
        model.addAttribute("admsList", modelMap.get("admsList"));
        return "admin/stats/detailCntByDept";
    }

    //학과별 지원자 인원수 상세 통계 화면
    @RequestMapping(value="/stats/detailCntByDept/search")
    @ResponseBody
    public String searchDetailStatsByDept(CourseSearchGridForm searchForm)
            throws NoSuchAlgorithmException, JsonProcessingException, UnsupportedEncodingException {
        List<ApplicantCnt> pStsList =null;
        List<String> headList = null;
        pStsList = adminStatsService.retrieveApplicantDetailCntByDept(searchForm);

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

    //학과별 미결제 인원수 통계 초기화면
    @RequestMapping(value="/stats/unpaidCnt")
    public String statsUnpaid(Model model) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap = adminService.getCouurseSelectionBasicMap();
        ExecutionContext ec = new ExecutionContext();
        model.addAttribute("admsList", modelMap.get("admsList"));
        return "admin/stats/unpaidCnt";
    }

    //학과별 미결제 인원수 통계 화면
    @RequestMapping(value="/search/unpaidCnt/search")
    @ResponseBody
    public String searchStatsUnpaid(CourseSearchGridForm searchForm)
            throws NoSuchAlgorithmException, JsonProcessingException, UnsupportedEncodingException {
        List<ApplicantCnt> pStsList =null;
        pStsList = adminStatsService.retrieveUnpaidApplicantCntByDept(searchForm);

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("total",pStsList.size());
        modelMap.put("records", pStsList.size());
        modelMap.put("rows",pStsList);
        modelMap.put("page", 1);

        String value = mapper.writeValueAsString(modelMap);

        return value;
    }


    //과정별 인원수 통계 초기화면
    @RequestMapping(value="/stats/basicCntByAdms")
    public String statsCourse(Model model) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap = adminService.getCouurseSelectionBasicMap();
        ExecutionContext ec = new ExecutionContext();
        model.addAttribute("admsList", modelMap.get("admsList"));
        return "admin/stats/basicCntByAdms";
    }

    //과졍별 인원수 통계 화면
    @RequestMapping(value="/stats/basicCntByAdms/search")
    @ResponseBody
    public String searchStatsCourse(CourseSearchGridForm searchForm)
            throws NoSuchAlgorithmException, JsonProcessingException, UnsupportedEncodingException {
        List<ApplicantDetailCnt> pStsList =null;
        pStsList = adminStatsService.selectApplicantCntByAdmsDept(searchForm);

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("total",pStsList.size());
        modelMap.put("records", pStsList.size());
        modelMap.put("rows",pStsList);
        modelMap.put("page", 1);

        String value = mapper.writeValueAsString(modelMap);

        return value;
    }

}
