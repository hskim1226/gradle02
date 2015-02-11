package com.apexsoft.ysprj.applicants.application.control;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.ysprj.applicants.application.domain.*;
import com.apexsoft.ysprj.applicants.application.service.*;
import com.apexsoft.ysprj.applicants.common.domain.CommonCode;
import com.apexsoft.ysprj.applicants.common.service.CommonService;
import org.eclipse.birt.report.engine.api.IRenderOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
//    @Autowired
//    ApplicationService applicationService;

    @Autowired
    CommonService commonService;

    @Autowired
    BasisService basisService;

    @Autowired
    AcademyService academyService;

    @Autowired
    LangCareerService langCareerService;

    @Autowired
    DocumentService documentService;

    @Value("#{app['rpt.format']}")
    private String REPORT_FORMAT;

    private final String RPT_APPLICATION_KR = "application_kr";
    private final String RPT_APPLICATION_EN = "application_en";

    @RequestMapping(value = "/print")
    public ModelAndView previewApplicationByParam(@RequestParam(value = "applNo", required = false) Integer applNo,
                                                  @RequestParam(value = "reportFormat", required = false) String reportFormat,
                                                  @RequestParam(value = "reportName", required = false) String reportName,
                                                  ModelAndView mv) {
        return previewApplication(applNo, reportFormat, reportName, mv);
    }

    @RequestMapping(value = "/print/{applNo}/{reportFormat}/{reportName}")
    public ModelAndView previewApplicationByRESTful(@PathVariable("applNo") Integer applNo,
                                           @PathVariable("reportFormat") String reportFormat,
                                           @PathVariable("reportName") String reportName,
                                           ModelAndView mv) {
        return previewApplication(applNo, reportFormat, reportName, mv);
    }

    @RequestMapping(value = "/preview")
    public ModelAndView previewAppInfo(@RequestParam(value = "application.applNo", required = false) Integer applNo,
                                       ModelAndView mv) {
        return previewApplication(applNo, REPORT_FORMAT, RPT_APPLICATION_KR, mv);
    }

    private ModelAndView previewApplication(Integer applNo, String reportFormat, String reportName, ModelAndView mv) {
        mv.setViewName("pdfSingleFormatBirtView");
        mv.addObject("reportFormat", reportFormat);
        mv.addObject("reportName", reportName);

        ExecutionContext ecBasis = basisService.retrieveBasis(applNo);
        ExecutionContext ecAcademy = academyService.retrieveAcademy(applNo);
        ExecutionContext ecLangCareer = langCareerService.retrieveLangCareer(applNo);
        ExecutionContext ecDocument = documentService.retrieveDocument(applNo);

        Basis basis = ((Map<String, Basis>)ecBasis.getData()).get("basis");
        Academy academy = ((Map<String, Academy>)ecAcademy.getData()).get("academy");
        LangCareer langCareer = ((Map<String, LangCareer>)ecLangCareer.getData()).get("langCareer");
        Document document = ((Map<String, Document>)ecDocument.getData()).get("document");

        Application application = basis.getApplication();
        ApplicationGeneral applicationGeneral = basis.getApplicationGeneral();
        List<CustomApplicationAcademy> collegeList = academy.getCollegeList();
        List<CustomApplicationAcademy> graduateList = academy.getGraduateList();
        List<CustomApplicationAcademy> academyList = new ArrayList<CustomApplicationAcademy>();
        academyList.addAll(collegeList);
        academyList.addAll(graduateList);

        /* TODO ENTR_YEAR, ADMS_TYPE_CODE 구하는 부분 수정 필요 */
        CommonCode commonCode;
        commonCode = commonService.retrieveCommonCodeValueByCodeGroupCode("ADMS_TYPE", "A");
        String admsTypeName = commonCode != null ? commonCode.getCodeVal() : null;
        String[] admsTypeNames = admsTypeName.split(" ");
        mv.addObject("entrYear", "2015");
        if( admsTypeNames.length > 1) {
            mv.addObject("admsTypeName1", admsTypeNames[0]);
            mv.addObject("admsTypeName2", admsTypeNames[1]);
        }
        /* TODO ENTR_YEAR, ADMS_TYPE_CODE 구하는 부분 수정 필요 */

        String campName = commonService.retrieveCampNameByCode(basis.getApplication().getCampCode());
        String corsTypeName = commonService.retrieveCorsTypeNameByCode(basis.getApplication().getCorsTypeCode());
        String ariInstName = commonService.retrieveAriInstNameByCode(basis.getApplication().getAriInstCode());
        String deptName = commonService.retrieveDeptNameByCode(basis.getApplication().getDeptCode());
        String deptCode = application.getDeptCode();
        String detlMajName = commonService.retrieveDetlMajNameByCode(basis.getApplication().getDetlMajCode());

        mv.addObject("campName", campName);
        mv.addObject("corsTypeName", corsTypeName);
        mv.addObject("ariInstName", ariInstName);
        mv.addObject("deptName", deptName);
        mv.addObject("deptCode", deptCode);
        mv.addObject("detlMajName", detlMajName);

        String korName = application.getKorName();
        String engName = application.getEngName();
        String engSur = application.getEngSur();
        String rgstNo = application.getRgstNo();
        String mailAddr = application.getMailAddr();
        String telNum = application.getTelNum();
        String mobiNum = application.getMobiNum();
        String addr = application.getAddr();
        String detlAddr = application.getDetlAddr();

        mv.addObject("korName", korName);
        mv.addObject("engName", engName);
        mv.addObject("engSur", engSur);
        mv.addObject("rgstNo", rgstNo);
        mv.addObject("mailAddr", mailAddr);
        mv.addObject("telNum", telNum);
        mv.addObject("mobiNum", mobiNum);
        mv.addObject("addr", addr);
        mv.addObject("detlAddr", detlAddr);

        // TODO : 사진 파일 추출
//        ApplicationDocument photoFile = document.retrieveApplicationDocumentPhoto(applNo);
//        String photoFilePath = photoFile.getFilePath() + "/" + photoFile.getFileName();
        String photoUri = "/opt/ysproject/upload/2015/15A/z/zz/357/하니.png";
        mv.addObject("photoUri", photoUri);

        String currWrkpName = applicationGeneral.getCurrWrkpName();
        String currWrkpTel = applicationGeneral.getCurrWrkpTel();
        // TODO
//        String academy0 = academyList.get(0);
//        res += entrDay.substr(0, 4) + "년" + entrDay.substr(4, 2) + "월" + entrDay.substr(6, 2) + "일";
        String academy0 = "학력0";
        String academy1 = "학력1";
        String academy2 = "학력2";
        String academy3 = "학력3";
        String academy4 = "학력4";

        mv.addObject("currWrkpName", currWrkpName);
        mv.addObject("currWrkpTel", currWrkpTel);
        mv.addObject("academy0", academy0);
        mv.addObject("academy1", academy1);
        mv.addObject("academy2", academy2);
        mv.addObject("academy3", academy3);
        mv.addObject("academy4", academy4);

        String hndcGrad = applicationGeneral.getHndcGrad();
        String hndcType = applicationGeneral.getHndcType();

        mv.addObject("hndcGrad", hndcGrad);
        mv.addObject("hndcType", hndcType);

        // TODO
        String lastCollegeScore = "3.55";
        String lastGraduateScore = "4.5";
        String toeflScore = "1";
        String toeicScore = "2";
        String tepsScore = "3";
        String ieltsScore = "4";
        String greScore = "5";
        String forlExmp = "6";

        mv.addObject("lastCollegeScore", lastCollegeScore);
        mv.addObject("lastGraduateScore", lastGraduateScore);
        mv.addObject("toeflScore", toeflScore);
        mv.addObject("toeicScore", toeicScore);
        mv.addObject("tepsScore", tepsScore);
        mv.addObject("ieltsScore", ieltsScore);
        mv.addObject("greScore", greScore);
        mv.addObject("forlExmp", forlExmp);

        // TODO
        String range0 = "00";
        String corpName0 = "01";
        String exprDesc0 = "02";
        String range1 = "10";
        String corpName1 = "11";
        String exprDesc1 = "12";
        String range2 = "20";
        String corpName2 = "21";
        String exprDesc2 = "22";
        String range3 = "30";
        String corpName3 = "31";
        String exprDesc3 = "32";
        String range4 = "40";
        String corpName4 = "41";
        String exprDesc4 = "42";

        mv.addObject("range0", range0);
        mv.addObject("corpName0", corpName0);
        mv.addObject("exprDesc0", exprDesc0);
        mv.addObject("range1", range1);
        mv.addObject("corpName1", corpName1);
        mv.addObject("exprDesc1", exprDesc1);
        mv.addObject("range2", range2);
        mv.addObject("corpName2", corpName2);
        mv.addObject("exprDesc2", exprDesc2);
        mv.addObject("range3", range3);
        mv.addObject("corpName3", corpName3);
        mv.addObject("exprDesc3", exprDesc3);
        mv.addObject("range4", range4);
        mv.addObject("corpName4", corpName4);
        mv.addObject("exprDesc4", exprDesc4);

        // TODO
        mv.addObject("mltrServName", "병역1");
        mv.addObject("mltrJoinDay", "병역2");
        mv.addObject("mltrDschDay", "병역3");
        mv.addObject("mltrRankName", "병역4");

        // TODO
        mv.addObject("applId", "TEMP_APPL_ID");

        return mv;
    }
}
