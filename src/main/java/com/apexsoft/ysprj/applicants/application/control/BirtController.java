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
        List<LanguageGroup> langGrpList = langCareer.getLanguageGroupList();
        List<CustomApplicationExperience> expList = langCareer.getApplicationExperienceList();
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

        String campName = "-- 해당사항 없음 -- ";
        if(basis.getApplication().getCampCode() !=null && !"".equals(basis.getApplication().getCampCode())) {
            campName = commonService.retrieveCampNameByCode(basis.getApplication().getCampCode());
        }
        String corsTypeName = commonService.retrieveCorsTypeNameByCode(basis.getApplication().getCorsTypeCode());

        String ariInstName= "-- 해당사항 없음 -- ";
        if(basis.getApplication().getAriInstCode() !=null && !"".equals(basis.getApplication().getAriInstCode())) {
            ariInstName = commonService.retrieveAriInstNameByCode(basis.getApplication().getAriInstCode());
        }

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




        String academy0 = "";
        String academy1 = "";
        String academy2 = "";
        String academy3 = "";
        String academy4 = "";
        String lastCollegeScore = "";
        String lastGraduateScore = "";
        String toeflScore = "";
        String toeicScore = "";
        String tepsScore = "";
        String ieltsScore = "";
        String greScore = "";

        int collCnt = 0;
        boolean collLastFg = false;
        boolean gradLastFg = false;

        for(CustomApplicationAcademy aColl : collegeList ){
            collCnt++;
            if( collCnt ==1){
                academy0 = "(대학) "+aColl.getSchlName()+" "+aColl.getCollName() + " "+aColl.getMajName();
            }
            else if( collCnt ==2){
                academy1 =  "(대학) "+aColl.getSchlName()+" "+aColl.getCollName() + " "+aColl.getMajName();
            }
            else if( collCnt ==3){
                academy2 =  "(대학) "+aColl.getSchlName()+" "+aColl.getCollName() + " "+aColl.getMajName();
            }
            else if( collCnt ==4){
                academy3 =  "(대학) "+aColl.getSchlName()+" "+aColl.getCollName() + " "+aColl.getMajName();
            }
            else if( collCnt ==5){
                academy4 =  "(대학) "+aColl.getSchlName()+" "+aColl.getCollName() + " "+aColl.getMajName();
            }
            if( !collLastFg ) {
                lastCollegeScore = aColl.getGradAvr() + "/" + aColl.getGradFull();
            }
            if( "Y".equals(aColl.getLastSchlYn())){
                collLastFg = true;
            }

        }


        for(CustomApplicationAcademy aColl : graduateList ){
            collCnt++;
            if( collCnt ==1){
                academy0 = "(대학원) "+aColl.getSchlName()+" "+aColl.getCollName() + " "+aColl.getMajName();
            }
            else if( collCnt ==2){
                academy1 =  "(대학원) "+aColl.getSchlName()+" "+aColl.getCollName() + " "+aColl.getMajName();
            }
            else if( collCnt ==3){
                academy2 =  "(대학원) "+aColl.getSchlName()+" "+aColl.getCollName() + " "+aColl.getMajName();
            }
            else if( collCnt ==4){
                academy3 =  "(대학원) "+aColl.getSchlName()+" "+aColl.getCollName() + " "+aColl.getMajName();
            }
            else if( collCnt ==5){
                academy4 =  "(대학원) "+aColl.getSchlName()+" "+aColl.getCollName() + " "+aColl.getMajName();
            }

            if( !gradLastFg ) {
                lastGraduateScore = aColl.getGradAvr() + "/" + aColl.getGradFull();
            }
            if( "Y".equals(aColl.getLastSchlYn())){
                gradLastFg = true;
            }
        }


        mv.addObject("academy0",academy0 );
        mv.addObject("academy1",academy1 );
        mv.addObject("academy2",academy2 );
        mv.addObject("academy3",academy3 );
        mv.addObject("academy4",academy4 );


        mv.addObject("currWrkpName", currWrkpName);
        mv.addObject("currWrkpTel", currWrkpTel);


        String hndcGrad = applicationGeneral.getHndcGrad();
        String hndcType = applicationGeneral.getHndcType();

        mv.addObject("hndcGrad", hndcGrad);
        mv.addObject("hndcType", hndcType);

        // TODO


        mv.addObject("lastCollegeScore", lastCollegeScore);
        mv.addObject("lastGraduateScore", lastGraduateScore);

        for( LanguageGroup aLangGrp : langGrpList){
            //영어인경우
            if( "00001".equals(aLangGrp.getExamCode())){
                for( CustomApplicationLanguage aLang  : aLangGrp.getLangList() ){
                    if( aLang.isLangInfoSaveFg()) {
                        if ("00001".equals(aLang.getItemCode())) {
                            String toflType ="";
                            if( "00001".equals(aLang.getToflTypeCode()))
                                toflType ="IBT";
                            if( "00002".equals(aLang.getToflTypeCode()))
                                toflType ="CBT";
                            if( "00003".equals(aLang.getToflTypeCode()))
                                toflType ="PBT";

                            toeflScore = toflType + "  " + aLang.getLangGrad();
                        } else if ("00002".equals(aLang.getItemCode())) {
                            toeicScore = aLang.getLangGrad();
                        } else if ("00003".equals(aLang.getItemCode())) {
                            tepsScore = aLang.getLangGrad();

                        } else if ("00004".equals(aLang.getItemCode())) {
                            ieltsScore = aLang.getLangGrad();

                        } else if ("00005".equals(aLang.getItemCode())) {
                            greScore = aLang.getLangGrad();
                        }
                    }
                }
            }
        }


        mv.addObject("toeflScore", toeflScore);
        mv.addObject("toeicScore", toeicScore);
        mv.addObject("tepsScore", tepsScore);
        mv.addObject("ieltsScore", ieltsScore);
        mv.addObject("greScore", greScore);



        String forlExmp = "";
        if(applicationGeneral.getForlExmpCode() !=null) {
            forlExmp = applicationGeneral.getForlExmpCode();
        }

        mv.addObject("forlExmp", forlExmp);

        // TODO
        String range0 = "";
        String corpName0 = "";
        String exprDesc0 = "";
        String range1 = "";
        String corpName1 = "";
        String exprDesc1 = "";
        String range2 = "";
        String corpName2 = "";
        String exprDesc2 = "";
        String range3 = "";
        String corpName3 = "";
        String exprDesc3 = "";
        String range4 = "";
        String corpName4 = "";
        String exprDesc4 = "";

        int exprCnt =0;
        for(CustomApplicationExperience aExpr : expList ){
            exprCnt++;
            if( exprCnt ==1){
                range0 =aExpr.getJoinDay() +"~"+aExpr.getRetrDay();
                corpName0 =aExpr.getCorpName();
                exprDesc0 =aExpr.getExprDesc();
            }
            else if( exprCnt ==2){
                range1 =aExpr.getJoinDay() +"~"+aExpr.getRetrDay();
                corpName1 =aExpr.getCorpName();
                exprDesc1 =aExpr.getExprDesc();
            }
            else if( exprCnt ==3){
                range2 =aExpr.getJoinDay() +"~"+aExpr.getRetrDay();
                corpName2 =aExpr.getCorpName();
                exprDesc2=aExpr.getExprDesc();
            }
            else if( exprCnt ==4){
                range3 =aExpr.getJoinDay() +"~"+aExpr.getRetrDay();
                corpName3 =aExpr.getCorpName();
                exprDesc3 =aExpr.getExprDesc();
            }
            else if( exprCnt ==5){
                range4 =aExpr.getJoinDay() +"~"+aExpr.getRetrDay();
                corpName4 =aExpr.getCorpName();
                exprDesc4 =aExpr.getExprDesc();
            }

        }

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
        mv.addObject("mltrServName", "");
        mv.addObject("mltrJoinDay", "");
        mv.addObject("mltrDschDay", "");
        mv.addObject("mltrRankName", "");

        // TODO

        String appId = "지원 미완료";

        if( application.getApplId() != null && !application.getApplId().equals("")){
            appId = application.getApplId();
        }
        mv.addObject("applId", appId);

        return mv;
    }
}
