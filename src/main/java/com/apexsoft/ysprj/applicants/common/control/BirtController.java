package com.apexsoft.ysprj.applicants.common.control;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.ysprj.applicants.application.domain.*;
import com.apexsoft.ysprj.applicants.application.service.*;
import com.apexsoft.ysprj.applicants.common.domain.CommonCode;
import com.apexsoft.ysprj.applicants.common.service.BirtService;
import com.apexsoft.ysprj.applicants.common.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    private final String RPT_APPLICATION_KR = "application_kr";
    private final String RPT_APPLICATION_EN = "application_en";

    @RequestMapping(value = "/print")
    public ModelAndView previewApplicationByParam(@RequestParam(value = "applNo", required = false) Integer applNo,
                                                  @RequestParam(value = "reportFormat", required = false) String reportFormat,
                                                  @RequestParam(value = "reportName", required = false) String reportName,
                                                  ModelAndView mv) {
        mv.setViewName("pdfSingleFormatBirtView");
        mv.addObject("reportFormat", reportFormat);
        mv.addObject("reportName", reportName);
        ExecutionContext ec = birtService.processBirt(applNo);
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
        ExecutionContext ec = birtService.processBirt(applNo);
        mv.addAllObjects((Map<String, Object>)ec.getData());
        return mv;
    }

    @RequestMapping(value = "/preview")
    public ModelAndView previewAppInfo(@RequestParam(value = "application.applNo", required = false) Integer applNo,
                                       ModelAndView mv) {
        mv.setViewName("pdfSingleFormatBirtView");
        mv.addObject("reportFormat", REPORT_FORMAT);
        mv.addObject("reportName", RPT_APPLICATION_KR);
        ExecutionContext ec = birtService.processBirt(applNo);
        mv.addAllObjects((Map<String, Object>)ec.getData());
        return mv;
    }

    @RequestMapping(value = "/generate")
    public ModelAndView generateApplicationFile(@RequestParam(value = "application.applNo", required = false) Integer applNo,
                                                ModelAndView mv) {
        mv.setViewName("pdfSingleFormatBirtSaveToFile");
        mv.addObject("reportFormat", REPORT_FORMAT);
        mv.addObject("reportName", RPT_APPLICATION_KR);
        ExecutionContext ec = birtService.processBirt(applNo);
        mv.addAllObjects((Map<String, Object>)ec.getData());
        return mv;
    }
}
