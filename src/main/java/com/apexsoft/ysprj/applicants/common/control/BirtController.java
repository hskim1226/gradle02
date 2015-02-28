package com.apexsoft.ysprj.applicants.common.control;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.ysprj.applicants.application.domain.*;
import com.apexsoft.ysprj.applicants.application.service.*;
import com.apexsoft.ysprj.applicants.common.domain.BirtRequest;
import com.apexsoft.ysprj.applicants.common.domain.CommonCode;
import com.apexsoft.ysprj.applicants.common.service.BirtService;
import com.apexsoft.ysprj.applicants.common.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2014-08-01.
 */
@Controller
@RequestMapping("/application")
public class BirtController {

    @Autowired
    BirtService birtService;

    @Value("#{app['rpt.format']}")
    private String REPORT_FORMAT;

    private final String RPT_APPLICATION_KR = "yonsei-appl-kr";
    private final String RPT_APPLICATION_EN = "yonsei-appl-en";

    private final String RPT_ADMISSION_KR = "yonsei-adms-kr";
    private final String RPT_ADMISSION_EN = "yonsei-adms-en";

    @RequestMapping(value = "/print")
    public ModelAndView previewApplicationByParam(BirtRequest birtRequest,
                                                  ModelAndView mv) {
        mv.setViewName("pdfSingleFormatBirtView");
        Application application = birtRequest.getApplication();
        String admsTypeCode = application.getAdmsTypeCode();
        String reqType = birtRequest.getReqType();
        String lang = "C".equals(admsTypeCode) ? "en" : "kr";
        String reportName = "yonsei-" + reqType + "-" + lang;
        int applNo = application.getApplNo();

        mv.addObject("reportFormat", REPORT_FORMAT);
        mv.addObject("reportName", reportName);
        ExecutionContext ec = birtService.processBirt(applNo, reportName);
        mv.addAllObjects((Map<String, Object>)ec.getData());
        return mv;
    }

    @RequestMapping(value = "/print/{applNo}/{reportFormat}/{reportName}")
    public ModelAndView previewApplicationByRESTful(@PathVariable("applNo") Integer applNo,
                                                    @PathVariable("reportFormat") String reportFormat,
                                                    @PathVariable("reportName") String reportName,
                                                    ModelAndView mv) {
        mv.setViewName("pdfSingleFormatBirtView");
        mv.addObject("reportFormat", reportFormat);
        mv.addObject("reportName", reportName);
        ExecutionContext ec = birtService.processBirt(applNo, reportName);
        mv.addAllObjects((Map<String, Object>)ec.getData());
        return mv;
    }

    @RequestMapping(value = "/preview")
    public ModelAndView previewAppInfo(BirtRequest birtRequest,
                                       ModelAndView mv) {

        mv.setViewName("pdfSingleFormatBirtView");
        Application application = birtRequest.getApplication();
        String admsTypeCode = application.getAdmsTypeCode();
        String reqType = birtRequest.getReqType();
        String lang = "C".equals(admsTypeCode) ? "en" : "kr";
        String reportName = "yonsei-" + reqType + "-" + lang;
        int applNo = application.getApplNo();
        mv.addObject("reportFormat", REPORT_FORMAT);
        mv.addObject("reportName", reportName);
        ExecutionContext ec = birtService.processBirt(applNo, reportName);
        mv.addAllObjects((Map<String, Object>)ec.getData());
        return mv;
    }

    @RequestMapping(value = "/generate/application")
    public ModelAndView generateApplicationFile(BirtRequest birtRequest,
                                                ModelAndView mv) {
        Application application = birtRequest.getApplication();
        int applNo = application.getApplNo();
        mv.setViewName("pdfSingleFormatBirtSaveToFile");
        mv.addObject("reportFormat", REPORT_FORMAT);
        mv.addObject("reportName", RPT_APPLICATION_KR);
        ExecutionContext ec = birtService.processBirt(applNo, RPT_APPLICATION_KR);
        mv.addAllObjects((Map<String, Object>)ec.getData());
        return mv;
    }

    @RequestMapping(value = "/generate/slip")
    public ModelAndView generateSlipFile(BirtRequest birtRequest,
                                         ModelAndView mv) {
        Application application = birtRequest.getApplication();
        int applNo = application.getApplNo();
        mv.setViewName("pdfSingleFormatBirtSaveToFile");
        mv.addObject("reportFormat", REPORT_FORMAT);
        mv.addObject("reportName", RPT_ADMISSION_KR);
        ExecutionContext ec = birtService.processBirt(applNo, RPT_ADMISSION_KR);
        mv.addAllObjects((Map<String, Object>)ec.getData());
        return mv;
    }
}
