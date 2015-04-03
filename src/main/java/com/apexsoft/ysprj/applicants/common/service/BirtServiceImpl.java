package com.apexsoft.ysprj.applicants.common.service;

import com.apexsoft.framework.birt.spring.core.BirtEngineFactory;
import com.apexsoft.framework.birt.spring.core.CustomAbstractSingleFormatBirtProcessor;
import com.apexsoft.framework.birt.spring.core.CustomPdfSingleFormatBirtSaveToFile;
import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.exception.ErrorInfo;
import com.apexsoft.framework.exception.YSBizException;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.ysprj.applicants.application.domain.*;
import com.apexsoft.ysprj.applicants.application.service.AcademyService;
import com.apexsoft.ysprj.applicants.application.service.BasisService;
import com.apexsoft.ysprj.applicants.application.service.DocumentService;
import com.apexsoft.ysprj.applicants.application.service.LangCareerService;
import com.apexsoft.ysprj.applicants.common.domain.CommonCode;
import com.apexsoft.ysprj.applicants.common.domain.Country;
import com.apexsoft.ysprj.applicants.common.util.FileUtil;
import com.apexsoft.ysprj.applicants.common.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    MessageResolver messageResolver;

    @Autowired
    BirtEngineFactory birtEngineFactory;

    @Autowired
    ServletContext servletContext;

    @Value("#{app['file.baseDir']}")
    private String BASE_DIR;

    @Value("#{app['rpt.format']}")
    private String REPORT_FORMAT;

    @Value("#{app['path.static']}")
    private String STATIC_PATH;

    private String ADMS_FORN_1 = "C";
    private String ADMS_FORN_2 = "D";

    @Override
    public ExecutionContext generateBirtFile(int applNo, String birtRptFileName) {

        ExecutionContext ec = processBirt(applNo, birtRptFileName);
        Map<String, Object> map = (Map<String, Object>)ec.getData();
        map.put("reportFormat", REPORT_FORMAT);
        map.put("reportName", birtRptFileName);
        String pathToRptdesignFile = "/reports/" +  birtRptFileName + ".rptdesign";
//        String fullPathToRptdesignFile = STATIC_PATH + pathToRptdesignFile;
        String fullPathToRptdesignFile = servletContext.getRealPath(pathToRptdesignFile);
        map.put("rptdesignFullPath", fullPathToRptdesignFile);

        IReportEngine reportEngine = birtEngineFactory.getObject();
        CustomAbstractSingleFormatBirtProcessor birtProcessor = new CustomPdfSingleFormatBirtSaveToFile();
        birtProcessor.setBirtEngine(reportEngine);
        try {
            birtProcessor.createReport(map);
        } catch ( Exception e ) {
            ExecutionContext ecError = new ExecutionContext(ExecutionContext.FAIL);

            ecError.setMessage(messageResolver.getMessage("U803"));
            ecError.setErrCode("ERR0072");

            Map<String, String> errorInfo = new HashMap<String, String>();
            errorInfo.put("applNo", String.valueOf(applNo));

            ecError.setErrorInfo(new ErrorInfo(errorInfo));
            throw new YSBizException(ecError);
        }
        return ec;
    }


    //원서 정보 수험표 정보 모두 여기서 추출
    @Override
    public ExecutionContext processBirt(int applNo, String birtRptFileName) {
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
        ApplicationForeigner applicationForeigner = basis.getApplicationForeigner();
        List<CustomApplicationAcademy> collegeList = academy.getCollegeList();
        List<CustomApplicationAcademy> graduateList = academy.getGraduateList();
        List<CustomApplicationAcademy> academyList = new ArrayList<CustomApplicationAcademy>();
        academyList.addAll(collegeList);
        academyList.addAll(graduateList);
        List<TotalApplicationDocumentContainer> documentContainerList = document.getDocumentContainerList();

        String admsNo = application.getAdmsNo();
        String userId = application.getUserId();
        String admsTypeCode = application.getAdmsTypeCode();
        String pdfFileName = StringUtils.indexOf(birtRptFileName, "appl") > 0 ?
                FileUtil.getApplicationFileName(userId) :
                FileUtil.getSlipFileName(userId);

        rptInfoMap.put("pdfDirectoryFullPath", FileUtil.getUploadDirectoryFullPath(BASE_DIR, admsNo, userId, applNo));
        rptInfoMap.put("pdfFileName", pdfFileName);

        CommonCode commonCode;
        commonCode = commonService.retrieveCommonCodeByCodeGroupCode("ADMS_TYPE", application.getAdmsTypeCode());
        String admsTypeName = commonCode != null ? commonCode.getCodeVal() : null;
        String[] admsTypeNames = admsTypeName.split(" ");
        rptInfoMap.put("entrYear", application.getEntrYear());
        if( admsTypeNames.length > 1) {
            rptInfoMap.put("admsTypeName1", admsTypeNames[0]);
            rptInfoMap.put("admsTypeName2", admsTypeNames[1]);
        }
        rptInfoMap.put("title", application.getEntrYear() + " 학년도 " + admsTypeNames[0] + " " + admsTypeNames[1] + " 대학원 입학원서");

        String campName = "-- 해당사항 없음 -- ";
        if(application.getCampCode() !=null && !"".equals(application.getCampCode())) {
//            String campNameArr[] = commonService.retrieveCampNameByCode(application.getCampCode());
//            campName = campNameArr[0] + "N" + campNameArr[1];
            campName = commonService.retrieveCampNameByCode(application.getCampCode());
        }
        String corsTypeName = commonService.retrieveCorsTypeNameByCode(application.getCorsTypeCode());

        String ariInstName= "-- 해당사항 없음 -- ";
        if(application.getAriInstCode() !=null && !"".equals(application.getAriInstCode())) {
            ariInstName = commonService.retrieveAriInstNameByCode(application.getAriInstCode());
        }

        String deptName = commonService.retrieveDeptNameByCode(application.getDeptCode());
        String deptCode = application.getDeptCode();
        String detlMajName = commonService.retrieveDetlMajNameByCode(application.getDetlMajCode());

        rptInfoMap.put("campName", campName);
        rptInfoMap.put("semester", application.getEntrYear() + "-" + admsTypeNames[0]);
        rptInfoMap.put("corsTypeName", corsTypeName);
        rptInfoMap.put("ariInstName", ariInstName);
        rptInfoMap.put("deptName", deptName);
        rptInfoMap.put("deptCode", deptCode);
        rptInfoMap.put("detlMajName", detlMajName);

        String korName = application.getKorName();
        String engName = application.getEngName();
        String engSur = application.getEngSur();
        String gend = application.getGend();
        String rgstNo = application.getRgstNo();
        String rgstBornDate = application.getRgstBornDate();
        String mailAddr = application.getMailAddr();
        String telNum = application.getTelNum();
        String mobiNum = application.getMobiNum();
        String addr = application.getAddr();
        String detlAddr = application.getDetlAddr();

        rptInfoMap.put("korName", StringUtil.getEmptyIfNull(korName));
        rptInfoMap.put("engName", StringUtil.getEmptyIfNull(engName));
        rptInfoMap.put("engSur", StringUtil.getEmptyIfNull(engSur));
        rptInfoMap.put("gend", StringUtil.getEmptyIfNull(gend));
        rptInfoMap.put("rgstBornDate", StringUtil.getEmptyIfNull(rgstBornDate));
//        rptInfoMap.put("rgstNo", StringUtil.insertHyphenAt(rgstNo, 6));
        String fornRgstNo = applicationForeigner.getFornRgstNo();
        if (fornRgstNo != null && fornRgstNo.length() > 0) {
            rptInfoMap.put("fornRgstNo", StringUtil.insertHyphenAt(fornRgstNo, 6));
        }
        Country tmpCountry = commonService.retrieveCountryByCode(StringUtil.getEmptyIfNull(applicationForeigner.getBornCntrCode()));
        rptInfoMap.put("bornCntrName", tmpCountry == null ? "" : tmpCountry.getEngCntrName());
        tmpCountry = commonService.retrieveCountryByCode(StringUtil.getEmptyIfNull(application.getCitzCntrCode()));
        rptInfoMap.put("citzCntrName", tmpCountry == null ? "" : tmpCountry.getEngCntrName());
        rptInfoMap.put("bornDay", StringUtil.getEmptyIfNull(application.getBornDay()));
        rptInfoMap.put("paspNo", StringUtil.getEmptyIfNull(applicationForeigner.getPaspNo()));
        rptInfoMap.put("visaTypeName", StringUtil.getEmptyIfNull(applicationForeigner.getVisaTypeCode()) + StringUtil.getEmptyIfNull(applicationForeigner.getVisaTypeEtc()));
        rptInfoMap.put("fornRgstYn", StringUtil.getEmptyIfNull(applicationForeigner.getFornRgstNo()).length() > 0 ? "등록"+"\n"+"(Registered)" : "미등록"+"\n"+"(Not Registered)");
        rptInfoMap.put("homeAdddr", StringUtil.getEmptyIfNull(applicationForeigner.getHomeAddr()));
        rptInfoMap.put("korAddr", StringUtil.getEmptyIfNull(addr) + " " + StringUtil.getEmptyIfNull(detlAddr));
        rptInfoMap.put("mailAddr", StringUtil.getEmptyIfNull(mailAddr));
        rptInfoMap.put("homeTel", StringUtil.getEmptyIfNull(applicationForeigner.getHomeTel()));
        rptInfoMap.put("telNum", StringUtil.getEmptyIfNull(telNum));
        rptInfoMap.put("mobiNum", StringUtil.getEmptyIfNull(mobiNum));
        rptInfoMap.put("addr", StringUtil.getEmptyIfNull(addr));
        rptInfoMap.put("detlAddr", StringUtil.getEmptyIfNull(detlAddr));
        rptInfoMap.put("homeEmrgName", StringUtil.getEmptyIfNull(applicationForeigner.getHomeEmrgName()));
        rptInfoMap.put("homeEmrgTel", StringUtil.getEmptyIfNull(applicationForeigner.getHomeEmrgTel()));
        rptInfoMap.put("homeEmrgRela", StringUtil.getEmptyIfNull(applicationForeigner.getHomeEmrgRela()));
        rptInfoMap.put("korEmrgName", StringUtil.getEmptyIfNull(applicationForeigner.getKorEmrgName()));
        rptInfoMap.put("korEmrgTel", StringUtil.getEmptyIfNull(applicationForeigner.getKorEmrgTel()));
        rptInfoMap.put("korEmrgRela", StringUtil.getEmptyIfNull(applicationForeigner.getKorEmrgRela()));

        List<String> oneAcad = new ArrayList<String>();


        rptInfoMap.put("photoUri", documentService.retrievePhotoUri(applNo));

        String currWrkpName = StringUtil.getEmptyIfNull(applicationGeneral.getCurrWrkpName());
        String currWrkpTel = StringUtil.getEmptyIfNull(applicationGeneral.getCurrWrkpTel());

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
        String topikScore = "";

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


        String hndcGrad = StringUtil.getEmptyIfNull(applicationGeneral.getHndcGrad());
        String hndcType = StringUtil.getEmptyIfNull(applicationGeneral.getHndcType());

        rptInfoMap.put("hndcGrad", hndcGrad);
        rptInfoMap.put("hndcType", hndcType);

        // TODO


        rptInfoMap.put("lastCollegeScore", lastCollegeScore);
        rptInfoMap.put("lastGraduateScore", lastGraduateScore);

        String forlExmp = "";

        for( LanguageGroup aLangGrp : langGrpList){
            //영어인경우
            if( "00001".equals(aLangGrp.getExamCode())){
                for( TotalApplicationLanguageContainer aLangSend  : aLangGrp.getLangList() ){
                    for ( CustomApplicationLanguage aLang : aLangSend.getSubContainer() ) {
                        if ("00001".equals(aLangSend.getItemCode())) { // 제출
                            if (aLang.isLangInfoSaveFg()) {
                                if ("00001".equals(aLang.getItemCode())) {
                                    String toflType = "";
                                    if ("00001".equals(aLang.getSubCode()))
                                        toflType = "IBT";
                                    if ("00002".equals(aLang.getSubCode()))
                                        toflType = "CBT";
                                    if ("00003".equals(aLang.getSubCode()))
                                        toflType = "PBT";

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
                        } else { // 면제
                            if (aLang.isLangInfoSaveFg()) {
                                if ("00001".equals(aLang.getItemCode())) { // 영어권 외국인
                                    forlExmp = "영어권 외국인";
                                } else if ("00002".equals(aLang.getItemCode())) {
                                    forlExmp = "영어권 졸업자";
                                } else if ("00003".equals(aLang.getItemCode())) {
                                    forlExmp = "본교 석사 출신";
                                } else if ("00005".equals(aLang.getItemCode())) {
                                    forlExmp = "학과 면제";
                                } else if ("00006".equals(aLang.getItemCode())) {
                                    forlExmp = "본교 의예,치의예 졸업";
                                } else if ("00007".equals(aLang.getItemCode())) {
                                    forlExmp = "본교 의학,의전원 졸업";
                                } else if ("00008".equals(aLang.getItemCode())) {
                                    forlExmp = "구사 능력 증빙";
                                }
                            }
                        }
                    }
                }
            }
            // 국어인 경우
            else if ("00002".equals(aLangGrp.getExamCode())) {
                for( TotalApplicationLanguageContainer aLangSend  : aLangGrp.getLangList() ){
                    for( CustomApplicationLanguage aLang  : aLangSend.getSubContainer() ) {
                        if ("00001".equals(aLangSend.getItemCode())) { // 제출
                            if (aLang.isLangInfoSaveFg()) {
                                if ("00001".equals(aLang.getItemCode())) {
                                    topikScore = aLang.getLangGrad();
                                }
                            }
                        } else {
                            if (aLang.isLangInfoSaveFg()) {
                                if ("00001".equals(aLang.getItemCode())) { // 영어권 외국인
                                    forlExmp = "영어권 외국인";
                                } else if ("00002".equals(aLang.getItemCode())) {
                                    forlExmp = "영어권 졸업자";
                                } else if ("00003".equals(aLang.getItemCode())) {
                                    forlExmp = "본교 석사 출신";
                                } else if ("00005".equals(aLang.getItemCode())) {
                                    forlExmp = "학과 면제";
                                } else if ("00006".equals(aLang.getItemCode())) {
                                    forlExmp = "본교 의예,치의예 졸업";
                                } else if ("00007".equals(aLang.getItemCode())) {
                                    forlExmp = "본교 의학,의전원 졸업";
                                } else if ("00008".equals(aLang.getItemCode())) {
                                    forlExmp = "구사 능력 증빙";
                                }
                            }

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
        rptInfoMap.put("forlExmp", forlExmp.length() > 0 ? "O" : "");
        rptInfoMap.put("topik", topikScore);

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
