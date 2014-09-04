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

    @RequestMapping(value = "/application/{applNo}/{reportFormat}/{reportName}")
    public ModelAndView displayApplication(@PathVariable("applNo") Integer applNo,
                                           @PathVariable("reportFormat") String reportFormat,
                                           @PathVariable("reportName") String reportName,
                                           Model model, HttpServletRequest request) {
//        String reportName = request.getParameter("reportName");
//        String reportFormat = request.getParameter("reportFormat");
        EntireApplication entireApplication = applicationService.retrieveEntireApplication(applNo);
        model.addAttribute("entireApplication", entireApplication);
//        model.addAttribute("reportName", reportName);
        if(IRenderOption.OUTPUT_FORMAT_HTML.equalsIgnoreCase(reportFormat) ) {
            return new ModelAndView("htmlSingleFormatBirtView");
        }
        return new ModelAndView("pdfSingleFormatBirtView");
    }

}
