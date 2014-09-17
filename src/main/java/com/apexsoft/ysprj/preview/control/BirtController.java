package com.apexsoft.ysprj.preview.control;

import com.apexsoft.ysprj.applicants.application.domain.CampusCollege;
import com.apexsoft.ysprj.applicants.application.domain.EntireApplication;
import com.apexsoft.ysprj.applicants.application.service.ApplicationService;
import com.apexsoft.ysprj.applicants.common.domain.CommonCode;
import com.apexsoft.ysprj.applicants.common.service.CommonService;
import org.eclipse.birt.report.engine.api.IRenderOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Administrator on 2014-08-01.
 */
@Controller
@RequestMapping("/application")
public class BirtController {
    @Autowired
    ApplicationService applicationService;

    @Autowired
    CommonService commonService;

    @RequestMapping(value = "/preview")
    public ModelAndView previewAppInfo(@RequestParam(value = "applNo", required = false) Integer applNo,
                                 Model model) {
        return previewApplication(applNo, "pdf", "application_kr", model);
    }

    @RequestMapping(value = "/print")
    public ModelAndView previewApplicationByParam(@RequestParam(value = "applNo", required = false) Integer applNo,
                                                  @RequestParam(value = "reportFormat", required = false) String reportFormat,
                                                  @RequestParam(value = "reportName", required = false) String reportName,
                                                  Model model) {
        return previewApplication(applNo, reportFormat, reportName, model);
    }

    @RequestMapping(value = "/print/{applNo}/{reportFormat}/{reportName}")
    public ModelAndView previewApplicationByRESTful(@PathVariable("applNo") Integer applNo,
                                           @PathVariable("reportFormat") String reportFormat,
                                           @PathVariable("reportName") String reportName,
                                           Model model) {
        return previewApplication(applNo, reportFormat, reportName, model);
    }

    private ModelAndView previewApplication(Integer applNo, String reportFormat, String reportName, Model model) {
        model.addAttribute( "reportFormat", reportFormat );
        model.addAttribute( "reportName", reportName );
        EntireApplication entireApplication = applicationService.retrieveEntireApplication(applNo);

        /* TODO APPL DB 변경되면 수정되어야 할 부분 */
        CampusCollege campusCollege = applicationService.retrieveInfoByApplNo(applNo,
                "EntireApplicationMapper.selectCampusCollegeCode",
                CampusCollege.class);
        entireApplication.setCampCode( campusCollege.getCampCode() );
        entireApplication.setCollCode( campusCollege.getCollCode() );

        String campName = commonService.retrieveCampNameByCode(entireApplication.getCampCode());
        String collName = commonService.retrieveCollNameByCode(entireApplication.getCollCode());
        /* TODO APPL DB 변경되면 수정되어야 할 부분 */
        String ariInstName = commonService.retrieveAriInstNameByCode(entireApplication.getApplication().getAriInstCode());
        String deptName = commonService.retrieveDeptNameByCode(entireApplication.getApplication().getDeptCode());
        String corsTypeName = commonService.retrieveCorsTypeNameByCode(entireApplication.getApplication().getCorsTypeCode());
        String detlMajName = commonService.retrieveDetlMajNameByCode(entireApplication.getApplication().getDetlMajCode());
        CommonCode commonCode = null;
        commonCode = commonService.retrieveCommonCodeValueByCodeGroupCode("MLTR_SERV", entireApplication.getApplicationGeneral().getMltrServCode());
        String mltrServName = commonCode != null ? commonCode.getCodeVal() : null;
        commonCode = commonService.retrieveCommonCodeValueByCodeGroupCode("MLTR_RANK", entireApplication.getApplicationGeneral().getMltrRankCode());
        String mltrRankName = commonCode != null ? commonCode.getCodeVal() : null;

        model.addAttribute("campName", campName);
        model.addAttribute("collName", collName);
        model.addAttribute("ariInstName", ariInstName);
        model.addAttribute("deptName", deptName);
        model.addAttribute("corsTypeName", corsTypeName);
        model.addAttribute("detlMajName", detlMajName);
        model.addAttribute("mltrServName", mltrServName);
        model.addAttribute("mltrRankName", mltrRankName);

        /* TODO ENTR_YEAR, ADMS_TYPE_CODE 구하는 부분 수정 필요 */
        commonCode = commonService.retrieveCommonCodeValueByCodeGroupCode("ADMS_TYPE", "A");
        String admsTypeName = commonCode != null ? commonCode.getCodeVal() : null;
        String[] admsTypeNames = admsTypeName.split(" ");
        model.addAttribute("entrYear", "2015");
        if( admsTypeNames.length > 1) {
            model.addAttribute("admsTypeName1", admsTypeNames[0]);
            model.addAttribute("admsTypeName2", admsTypeNames[1]);
        }
        /* TODO ENTR_YEAR, ADMS_TYPE_CODE 구하는 부분 수정 필요 */

        model.addAttribute("entireApplication", entireApplication);
        if(IRenderOption.OUTPUT_FORMAT_HTML.equalsIgnoreCase(reportFormat) ) {
            return new ModelAndView("htmlSingleFormatBirtView");
        }
        return new ModelAndView("pdfSingleFormatBirtView");
    }

}
