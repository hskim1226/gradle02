package com.apexsoft.ysprj.preview.control;

import com.apexsoft.ysprj.applicants.application.domain.Application;
import com.apexsoft.ysprj.applicants.application.domain.EntireApplication;
import com.apexsoft.ysprj.applicants.application.service.ApplicationService;
import com.apexsoft.ysprj.preview.service.BirtService;
import org.eclipse.birt.report.engine.api.IRenderOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2014-08-01.
 */
@Controller
@RequestMapping("/preview")
public class BirtController {
    @Autowired
    ApplicationService applicationService;

    @Autowired
    BirtService birtService;

    @RequestMapping(value = "/application")
    public ModelAndView getApplications(Model model) {
        return new ModelAndView("birtView");
    }

//    @RequestMapping(value = "/application/{id}")
//    public String getApplication(@PathVariable("id") String id) {
//        return "birt/preview";
//    }

    @RequestMapping(value = "/application/{appNo}")
    public ModelAndView displayApplication(@PathVariable("appNo") String appNo, Model model, HttpServletRequest request) {
        Application application = birtService.getApplication(appNo);
        model.addAttribute("applicationVO", application);
        if( "pdf".equalsIgnoreCase(request.getParameter("reportFormat")) ) {
            return new ModelAndView("pdfSingleFormatBirtView");
        }
        return new ModelAndView("htmlSingleFormatBirtView");
    }

    @RequestMapping(value = "/application/{appNo}/{reportFormat}/{reportName}")
    public ModelAndView displayApplication(@PathVariable("appNo") Integer appNo,
                                           @PathVariable("reportFormat") String reportFormat,
                                           @PathVariable("reportName") String reportName,
                                           Model model, HttpServletRequest request) {
//        String reportName = request.getParameter("reportName");
//        String reportFormat = request.getParameter("reportFormat");
        EntireApplication entireApplication = applicationService.retrieveEntireApplication(appNo);
        model.addAttribute("entireApplication", entireApplication);
//        model.addAttribute("reportName", reportName);
        if(IRenderOption.OUTPUT_FORMAT_HTML.equalsIgnoreCase(reportFormat) ) {
            return new ModelAndView("htmlSingleFormatBirtView");
        }
        return new ModelAndView("pdfSingleFormatBirtView");
    }

}
