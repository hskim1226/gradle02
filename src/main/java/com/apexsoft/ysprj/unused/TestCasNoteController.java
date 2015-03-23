package com.apexsoft.ysprj.unused;

import com.apexsoft.framework.birt.spring.core.BirtEngineFactory;
import com.apexsoft.framework.birt.spring.core.CustomAbstractSingleFormatBirtProcessor;
import com.apexsoft.framework.birt.spring.core.CustomPdfSingleFormatBirtSaveToFile;
import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.exception.YSBizException;
import com.apexsoft.ysprj.applicants.application.domain.Application;
import com.apexsoft.ysprj.applicants.common.control.BirtController;
import com.apexsoft.ysprj.applicants.common.domain.BirtRequest;
import com.apexsoft.ysprj.applicants.common.service.BirtService;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Map;

/**
 * Created by hanmomhanda on 15. 3. 13.
 */
@Controller
@RequestMapping(value="/application")
public class TestCasNoteController {

    @Autowired
    private BirtController birtController;

    @Autowired
    private BirtService birtService;

    @Autowired
    private BirtEngineFactory birtEngineFactory;

    @Value("#{app['rpt.format']}")
    private String REPORT_FORMAT;

    private final String RPT_APPLICATION_KR = "yonsei-appl-kr";
    private final String RPT_APPLICATION_EN = "yonsei-appl-en";

    private final String RPT_ADMISSION_KR = "yonsei-adms-kr";
    private final String RPT_ADMISSION_EN = "yonsei-adms-en";

    @RequestMapping(value="/testCasNote/{applNo}")
    public ModelAndView testCasNote(@PathVariable("applNo") int applNo,
                                    Principal principal,
                                    ModelAndView mv,
                                    HttpServletRequest request) {

//        Application application = new Application();
//        application.setApplNo(applNo);
//        BirtRequest birtRequest = new BirtRequest();
//        birtRequest.setApplication(application);


        mv.setViewName("application/mylist");
        mv.addObject("reportFormat", REPORT_FORMAT);
        mv.addObject("reportName", RPT_ADMISSION_KR);
        String pathToRptdesignFile = "/reports/"+RPT_ADMISSION_KR+".rptdesign";
        String fullPathToRptdesignFile = request.getSession().getServletContext().getRealPath(pathToRptdesignFile);
        mv.addObject("rptdesignFullPath", fullPathToRptdesignFile);
        ExecutionContext ec = birtService.processBirt(applNo, RPT_ADMISSION_KR);
        mv.addAllObjects((Map<String, Object>)ec.getData());

        IReportEngine reportEngine = birtEngineFactory.getObject();
        CustomAbstractSingleFormatBirtProcessor birtProcessor = new CustomPdfSingleFormatBirtSaveToFile();
        birtProcessor.setBirtEngine(reportEngine);
        try {
            birtProcessor.createReport(mv.getModel());
        } catch ( Exception e ) {
            e.printStackTrace();
            throw new YSBizException();
        }

        return mv;
    }
}
