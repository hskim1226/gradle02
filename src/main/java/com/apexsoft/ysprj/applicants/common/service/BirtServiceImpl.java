package com.apexsoft.ysprj.applicants.common.service;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.ysprj.applicants.application.domain.*;
import com.apexsoft.ysprj.applicants.application.service.AcademyService;
import com.apexsoft.ysprj.applicants.application.service.BasisService;
import com.apexsoft.ysprj.applicants.application.service.DocumentService;
import com.apexsoft.ysprj.applicants.application.service.LangCareerService;
import com.apexsoft.ysprj.applicants.common.domain.CommonCode;
import com.apexsoft.ysprj.applicants.common.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * Created by hanmomhanda on 15. 2. 24.
 */
@Service
public class BirtServiceImpl implements BirtService {

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

    @Value("#{app['file.baseDir']}")
    private String BASE_DIR;

    @Override
    public ExecutionContext processBirt(int applNo) {
        Map<String, Object> rptInfoMap = new HashMap<String, Object>();
        ExecutionContext ecResult = new ExecutionContext();
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
        List<TotalApplicationDocumentContainer> documentContainerList = document.getDocumentContainerList();

        String admsNo = application.getAdmsNo();
        String userId = application.getUserId();
        String rptFileName = FileUtil.getApplicationFileName(userId);

        rptInfoMap.put("rptDirectoryFullPath", FileUtil.getUploadDirectoryFullPath(BASE_DIR, admsNo, userId, String.valueOf(applNo)));
        rptInfoMap.put("rptFileName", rptFileName);

        CommonCode commonCode;
        commonCode = commonService.retrieveCommonCodeValueByCodeGroupCode("ADMS_TYPE", application.getAdmsTypeCode());
        String admsTypeName = commonCode != null ? commonCode.getCodeVal() : null;
        String[] admsTypeNames = admsTypeName.split(" ");
        rptInfoMap.put("entrYear", application.getEntrYear());
        if( admsTypeNames.length > 1) {
            rptInfoMap.put("admsTypeName1", admsTypeNames[0]);
            rptInfoMap.put("admsTypeName2", admsTypeNames[1]);
        }

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

        rptInfoMap.put("campName", campName);
        rptInfoMap.put("corsTypeName", corsTypeName);
        rptInfoMap.put("ariInstName", ariInstName);
        rptInfoMap.put("deptName", deptName);
        rptInfoMap.put("deptCode", deptCode);
        rptInfoMap.put("detlMajName", detlMajName);

        String korName = application.getKorName();
        String engName = application.getEngName();
        String engSur = application.getEngSur();
        String rgstNo = application.getRgstNo();
        String mailAddr = application.getMailAddr();
        String telNum = application.getTelNum();
        String mobiNum = application.getMobiNum();
        String addr = application.getAddr();
        String detlAddr = application.getDetlAddr();

        rptInfoMap.put("korName", korName);
        rptInfoMap.put("engName", engName);
        rptInfoMap.put("engSur", engSur);
        rptInfoMap.put("rgstNo", rgstNo);
        rptInfoMap.put("mailAddr", mailAddr);
        rptInfoMap.put("telNum", telNum);
        rptInfoMap.put("mobiNum", mobiNum);
        rptInfoMap.put("addr", addr);
        rptInfoMap.put("detlAddr", detlAddr);

        rptInfoMap.put("photoUri", documentService.retrievePhotoUri(applNo));

        String currWrkpName = applicationGeneral.getCurrWrkpName();
        String currWrkpTel = applicationGeneral.getCurrWrkpTel();

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


        rptInfoMap.put("academy0", academy0);
        rptInfoMap.put("academy1", academy1);
        rptInfoMap.put("academy2", academy2);
        rptInfoMap.put("academy3", academy3);
        rptInfoMap.put("academy4", academy4);


        rptInfoMap.put("currWrkpName", currWrkpName);
        rptInfoMap.put("currWrkpTel", currWrkpTel);


        String hndcGrad = applicationGeneral.getHndcGrad();
        String hndcType = applicationGeneral.getHndcType();

        rptInfoMap.put("hndcGrad", hndcGrad);
        rptInfoMap.put("hndcType", hndcType);

        // TODO


        rptInfoMap.put("lastCollegeScore", lastCollegeScore);
        rptInfoMap.put("lastGraduateScore", lastGraduateScore);

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


        rptInfoMap.put("toeflScore", toeflScore);
        rptInfoMap.put("toeicScore", toeicScore);
        rptInfoMap.put("tepsScore", tepsScore);
        rptInfoMap.put("ieltsScore", ieltsScore);
        rptInfoMap.put("greScore", greScore);



        String forlExmp = "";
        if(applicationGeneral.getForlExmpCode() !=null) {
            forlExmp = applicationGeneral.getForlExmpCode();
        }

        rptInfoMap.put("forlExmp", forlExmp);

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

        rptInfoMap.put("range0", range0);
        rptInfoMap.put("corpName0", corpName0);
        rptInfoMap.put("exprDesc0", exprDesc0);
        rptInfoMap.put("range1", range1);
        rptInfoMap.put("corpName1", corpName1);
        rptInfoMap.put("exprDesc1", exprDesc1);
        rptInfoMap.put("range2", range2);
        rptInfoMap.put("corpName2", corpName2);
        rptInfoMap.put("exprDesc2", exprDesc2);
        rptInfoMap.put("range3", range3);
        rptInfoMap.put("corpName3", corpName3);
        rptInfoMap.put("exprDesc3", exprDesc3);
        rptInfoMap.put("range4", range4);
        rptInfoMap.put("corpName4", corpName4);
        rptInfoMap.put("exprDesc4", exprDesc4);

        // TODO
        rptInfoMap.put("mltrServName", "");
        rptInfoMap.put("mltrJoinDay", "");
        rptInfoMap.put("mltrDschDay", "");
        rptInfoMap.put("mltrRankName", "");

        // TODO

        List<TotalApplicationDocument> docList = new ArrayList<TotalApplicationDocument>();
        getDocList(documentContainerList, docList);
        rptInfoMap.put("documents", docList);

        String appId = "지원 미완료";

        if( application.getApplId() != null && !application.getApplId().equals("")){
            appId = application.getApplId();
        }
        rptInfoMap.put("applId", appId);

        ecResult.setData(rptInfoMap);

        return ecResult;
    }

    private void getDocList(List<TotalApplicationDocumentContainer> list,
                            List<TotalApplicationDocument> docList) {

        for (TotalApplicationDocumentContainer item : list) {
            if ("Y".equals(item.getLastYn())) {
                docList.add(item);
            } else {
                getDocList(item.getSubContainer(), docList);
            }
        }
    }
}
