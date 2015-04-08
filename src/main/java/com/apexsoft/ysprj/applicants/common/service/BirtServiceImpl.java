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
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;
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

    @Value("#{app['s3.midPath']}")
    private String s3MidPath;

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
            e.printStackTrace();
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

        rptInfoMap.put("pdfDirectoryFullPath", FileUtil.getUploadDirectoryFullPath(BASE_DIR, s3MidPath, admsNo, userId, applNo));
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
        if(application.getCampCode() !=null && !StringUtil.EMPTY_STRING.equals(application.getCampCode())) {
//            String campNameArr[] = commonService.retrieveCampNameByCode(application.getCampCode());
//            campName = campNameArr[0] + "N" + campNameArr[1];
            campName = commonService.retrieveCampNameByCode(application.getCampCode()).getCampName();
        }
        String corsTypeName = commonService.retrieveCorsTypeNameByCode(application.getCorsTypeCode()).getCodeVal();

        String ariInstName= "-- 해당사항 없음 -- ";
        if(application.getAriInstCode() !=null && !StringUtil.EMPTY_STRING.equals(application.getAriInstCode())) {
            ariInstName = commonService.retrieveAriInstNameByCode(application.getAriInstCode());
        }

        String deptName = commonService.retrieveDeptNameByCode(application.getDeptCode()).getDeptName();
        String deptCode = application.getDeptCode();
        String detlMajName = commonService.retrieveDetlMajNameByCode(application.getDetlMajCode()).getDetlMajName();

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
        rptInfoMap.put("engFullName", StringUtil.getEmptyIfNull(engName) + " " + StringUtil.getEmptyIfNull(engSur));

        String generalAdmsGender = StringUtil.EMPTY_STRING;
        if ( !"C".equals(admsTypeCode) && !"D".equals(admsTypeCode) ) {
            generalAdmsGender = "m".equals(StringUtil.getEmptyIfNull(gend).toLowerCase()) ? "(1******)" :
                    "f".equals(StringUtil.getEmptyIfNull(gend).toLowerCase()) ? "(2******)" : StringUtil.EMPTY_STRING;
        }
        rptInfoMap.put("rgstBornDate", StringUtil.getEmptyIfNull(rgstBornDate) + " " + generalAdmsGender);
//        rptInfoMap.put("rgstNo", StringUtil.insertHyphenAt(rgstNo, 6));






//        Country tmpCountry = commonService.retrieveCountryByCode(StringUtil.getEmptyIfNull(applicationForeigner.getBornCntrCode()));
//        rptInfoMap.put("bornCntrName", tmpCountry == null ? StringUtil.EMPTY_STRING : tmpCountry.getEngCntrName());
        Country tmpCountry = commonService.retrieveCountryByCode(StringUtil.getEmptyIfNull(application.getCitzCntrCode()));
        rptInfoMap.put("citzCntrName", tmpCountry == null ? StringUtil.EMPTY_STRING : tmpCountry.getEngCntrName());
//        rptInfoMap.put("bornDay", StringUtil.getEmptyIfNull(application.getBornDay()));  // rgstBornDate로 대체
//        rptInfoMap.put("paspNo", StringUtil.getEmptyIfNull(applicationForeigner.getPaspNo()));

        if ( "C".equals(admsTypeCode) || "D".equals(admsTypeCode)) {
            try {
                String fornRgstNoEncr = applicationForeigner.getFornRgstNoEncr();
                String fornRgstNo = fornRgstNoEncr != null && !StringUtil.EMPTY_STRING.equals(fornRgstNoEncr) ? getEncryptedString(fornRgstNoEncr, false) : StringUtil.EMPTY_STRING;
                if (fornRgstNo != null && fornRgstNo.length() > 0) {
                    rptInfoMap.put("fornRgstNo", StringUtil.insertHyphenAt(fornRgstNo, 6));
                } else {
                    rptInfoMap.put("fornRgstNo", "미등록"+"\n"+"(Not Registered)");
                }
                String paspNoEncr = applicationForeigner.getPaspNoEncr();
                String paspNo = paspNoEncr != null && !StringUtil.EMPTY_STRING.equals(paspNoEncr) ? getEncryptedString(paspNoEncr, false) : StringUtil.EMPTY_STRING;
                rptInfoMap.put("paspNo", paspNo);
//                String visaNoEncr = applicationForeigner.getVisaNoEncr();
//                String visaNo = visaNoEncr != null && !StringUtil.EMPTY_STRING.equals(visaNoEncr) ? getEncryptedString(visaNoEncr, false) : StringUtil.EMPTY_STRING;
            } catch (IOException e) {
                ExecutionContext ecEncr = new ExecutionContext(ExecutionContext.FAIL);
                ecEncr.setMessage(messageResolver.getMessage("U347"));
                ecEncr.setErrCode("ERR0043");
                Map<String, Object> errMap = new HashMap<String, Object>();
                errMap.put("applNo", basis.getApplication().getApplNo());
                errMap.put("userId", userId);
                errMap.put("situation", "Error while loading props for En/Decryption");
                ecEncr.setErrorInfo(new ErrorInfo(errMap));
                throw new YSBizException(ecEncr);
            }
        }

        rptInfoMap.put("visaTypeName", StringUtil.getEmptyIfNull(applicationForeigner.getVisaTypeCode()) + StringUtil.getEmptyIfNull(applicationForeigner.getVisaTypeEtc()));
//        rptInfoMap.put("fornRgstNo", StringUtil.getEmptyIfNull(applicationForeigner.getFornRgstNo()).length() > 0 ? "등록"+"\n"+"(Registered)" : "미등록"+"\n"+"(Not Registered)");
        rptInfoMap.put("homeAddr", StringUtil.getEmptyIfNull(applicationForeigner.getHomeAddr()));
        rptInfoMap.put("korAddr", StringUtil.getEmptyIfNull(addr) + " " + StringUtil.getEmptyIfNull(detlAddr));
        rptInfoMap.put("mailAddr", StringUtil.getEmptyIfNull(mailAddr));
        rptInfoMap.put("homeTel", StringUtil.getEmptyIfNull(applicationForeigner.getHomeTel()));
        rptInfoMap.put("telNum", StringUtil.getEmptyIfNull(telNum));
        rptInfoMap.put("mobiNum", StringUtil.getEmptyIfNull(mobiNum));
        rptInfoMap.put("addr", StringUtil.getEmptyIfNull(addr));
        rptInfoMap.put("detlAddr", StringUtil.getEmptyIfNull(detlAddr));
        rptInfoMap.put("homeEmrgName", StringUtil.getEmptyIfNull(applicationForeigner.getHomeEmrgName()));
        rptInfoMap.put("homeEmrgTel", StringUtil.getEmptyIfNull(applicationForeigner.getHomeEmrgTel()));
        CommonCode homeEmrgRela = commonService.retrieveCommonCodeByCodeGroupCode("EMER_CONT", StringUtil.getEmptyIfNull(applicationForeigner.getHomeEmrgRela()));
        String homeEmrgRelaVal = homeEmrgRela != null ? homeEmrgRela.getCodeVal() : StringUtil.EMPTY_STRING;
        rptInfoMap.put("homeEmrgRela", homeEmrgRelaVal);
        rptInfoMap.put("korEmrgName", StringUtil.getEmptyIfNull(applicationForeigner.getKorEmrgName()));
        rptInfoMap.put("korEmrgTel", StringUtil.getEmptyIfNull(applicationForeigner.getKorEmrgTel()));
        CommonCode korEmrgRela = commonService.retrieveCommonCodeByCodeGroupCode("EMER_CONT", StringUtil.getEmptyIfNull(applicationForeigner.getKorEmrgRela()));
        String korEmrgRelaVal = korEmrgRela != null ? korEmrgRela.getCodeVal() : StringUtil.EMPTY_STRING;
        rptInfoMap.put("korEmrgRela", korEmrgRelaVal);

        rptInfoMap.put("photoUri", documentService.retrievePhotoUri(applNo));

        String currWrkpName = StringUtil.getEmptyIfNull(applicationGeneral.getCurrWrkpName());
        String currWrkpTel = StringUtil.getEmptyIfNull(applicationGeneral.getCurrWrkpTel());

        String lastCollegeScore = StringUtil.EMPTY_STRING;
        String lastGraduateScore = StringUtil.EMPTY_STRING;
        String toeflScore = StringUtil.EMPTY_STRING;
        String toeicScore = StringUtil.EMPTY_STRING;
        String tepsScore = StringUtil.EMPTY_STRING;
        String ieltsScore = StringUtil.EMPTY_STRING;
        String greScore = StringUtil.EMPTY_STRING;
        String topikScore = StringUtil.EMPTY_STRING;

//        int collCnt = 0;
        boolean collLastFg = false;


//        for(CustomApplicationAcademy aColl : collegeList ){
//            collCnt++;
//            if( collCnt ==1){
//                academy0 = "(대학) "+aColl.getSchlName()+" "+aColl.getCollName() + " "+aColl.getMajName();
//            }
//            else if( collCnt ==2){
//                academy1 =  "(대학) "+aColl.getSchlName()+" "+aColl.getCollName() + " "+aColl.getMajName();
//            }
//            else if( collCnt ==3){
//                academy2 =  "(대학) "+aColl.getSchlName()+" "+aColl.getCollName() + " "+aColl.getMajName();
//            }
//            else if( collCnt ==4){
//                academy3 =  "(대학) "+aColl.getSchlName()+" "+aColl.getCollName() + " "+aColl.getMajName();
//            }
//            else if( collCnt ==5){
//                academy4 =  "(대학) "+aColl.getSchlName()+" "+aColl.getCollName() + " "+aColl.getMajName();
//            }
//            if( !collLastFg ) {
//                lastCollegeScore = aColl.getGradAvr() + "/" + aColl.getGradFull();
//            }
//            if( "Y".equals(aColl.getLastSchlYn())){
//                collLastFg = true;
//            }
//
//        }

        int collegeListL = collegeList.size();
        int i;
        for(i = 0 ; i < collegeListL ; i++) {
            CustomApplicationAcademy aColl = collegeList.get(i);

            if ("C".equals(admsTypeCode) || "D".equals(admsTypeCode)) {
                rptInfoMap.put("acadPeriod" + i, aColl.getEntrDay() + "~" + aColl.getGrdaDay());
                rptInfoMap.put("academy" + i, "(대학) "+ aColl.getSchlName() + " " + aColl.getCollName());
                rptInfoMap.put("majName" + i, aColl.getMajName());
                rptInfoMap.put("gpaFull" + i, aColl.getGradAvr() + " / " + aColl.getGradFull());
            } else {
                rptInfoMap.put("academy" + i, "(대학) "+ aColl.getSchlName() + " " + aColl.getCollName() + " " + aColl.getMajName());
            }
            if( !collLastFg ) {
                lastCollegeScore = aColl.getGradAvr() + " / " + aColl.getGradFull();
            }
            if( "Y".equals(aColl.getLastSchlYn())) {
                collLastFg = true;
            }
        }

        boolean gradLastFg = false;
        int graduateListL = graduateList.size();
        for(int j = 0 ; j < graduateListL ; j++) {
            CustomApplicationAcademy aGrad = graduateList.get(j);

            if ("C".equals(admsTypeCode) || "D".equals(admsTypeCode)) {
                rptInfoMap.put("acadPeriod" + (i+j), aGrad.getEntrDay() + "~" + aGrad.getGrdaDay());
                rptInfoMap.put("academy" + (i+j), "(대학원) " + aGrad.getSchlName() + " " + aGrad.getCollName());
                rptInfoMap.put("majName" + (i+j), aGrad.getMajName());
                rptInfoMap.put("gpaFull" + (i+j), aGrad.getGradAvr() + " / " + aGrad.getGradFull());
            } else {
                rptInfoMap.put("academy" + (i+j), "(대학원) "+ aGrad.getSchlName() + " " + aGrad.getCollName() + " " + aGrad.getMajName());
            }
            if( !gradLastFg ) {
                lastGraduateScore = aGrad.getGradAvr() + " / " + aGrad.getGradFull();
            }
            if( "Y".equals(aGrad.getLastSchlYn())) {
                gradLastFg = true;
            }
        }


//        for(CustomApplicationAcademy aColl : graduateList ){
//            collCnt++;
//            if( collCnt ==1){
//                academy0 = "(대학원) "+aColl.getSchlName()+" "+aColl.getCollName() + " "+aColl.getMajName();
//            }
//            else if( collCnt ==2){
//                academy1 =  "(대학원) "+aColl.getSchlName()+" "+aColl.getCollName() + " "+aColl.getMajName();
//            }
//            else if( collCnt ==3){
//                academy2 =  "(대학원) "+aColl.getSchlName()+" "+aColl.getCollName() + " "+aColl.getMajName();
//            }
//            else if( collCnt ==4){
//                academy3 =  "(대학원) "+aColl.getSchlName()+" "+aColl.getCollName() + " "+aColl.getMajName();
//            }
//            else if( collCnt ==5){
//                academy4 =  "(대학원) "+aColl.getSchlName()+" "+aColl.getCollName() + " "+aColl.getMajName();
//            }
//
//            if( !gradLastFg ) {
//                lastGraduateScore = aColl.getGradAvr() + "/" + aColl.getGradFull();
//            }
//            if( "Y".equals(aColl.getLastSchlYn())){
//                gradLastFg = true;
//            }
//        }

        rptInfoMap.put("currWrkpName", currWrkpName);
        rptInfoMap.put("currWrkpTel", currWrkpTel);


        String hndcGrad = StringUtil.getEmptyIfNull(applicationGeneral.getHndcGrad());
        String hndcType = StringUtil.getEmptyIfNull(applicationGeneral.getHndcType());

        rptInfoMap.put("hndcGrad", hndcGrad);
        rptInfoMap.put("hndcType", hndcType);

        // TODO


        rptInfoMap.put("lastCollegeScore", lastCollegeScore);
        rptInfoMap.put("lastGraduateScore", lastGraduateScore);

        String forlExmp = StringUtil.EMPTY_STRING;

        for( LanguageGroup aLangGrp : langGrpList){
            //영어인경우
            if( "00001".equals(aLangGrp.getExamCode())){
                for( TotalApplicationLanguageContainer aLangSend  : aLangGrp.getLangList() ){
                    for ( CustomApplicationLanguage aLang : aLangSend.getSubContainer() ) {
                        if ("00001".equals(aLangSend.getItemCode())) { // 제출
                            if (aLang.isLangInfoSaveFg()) {
                                if ("00001".equals(aLang.getItemCode())) {
                                    String toflType = StringUtil.EMPTY_STRING;
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
                                    String ieltsCode = aLang.getLangGrad();
                                    ieltsScore = commonService.retrieveCommonCodeByCodeGroupCode("IELT_LEVL", ieltsCode).getCodeVal();
                                } else if ("00005".equals(aLang.getItemCode())) {
                                    greScore = aLang.getLangGrad();
                                }
                            }
                        } else { // 면제
                            if (aLang.isLangInfoSaveFg()) {
//                                if ("00001".equals(aLang.getItemCode())) { // 영어권 외국인
//                                    forlExmp = "영어권 외국인";
//                                } else if ("00002".equals(aLang.getItemCode())) {
//                                    forlExmp = "영어권 졸업자";
//                                } else if ("00003".equals(aLang.getItemCode())) {
//                                    forlExmp = "본교 석사 출신";
//                                } else if ("00005".equals(aLang.getItemCode())) {
//                                    forlExmp = "학과 면제";
//                                } else if ("00006".equals(aLang.getItemCode())) {
//                                    forlExmp = "본교 의예,치의예 졸업";
//                                } else if ("00007".equals(aLang.getItemCode())) {
//                                    forlExmp = "본교 의학,의전원 졸업";
//                                } else if ("00008".equals(aLang.getItemCode())) {
//                                    forlExmp = "구사 능력 증빙";
//                                }
                                forlExmp = "O";
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
//                                if ("00001".equals(aLang.getItemCode())) { // 영어권 외국인
//                                    forlExmp = "영어권 외국인";
//                                } else if ("00002".equals(aLang.getItemCode())) {
//                                    forlExmp = "영어권 졸업자";
//                                } else if ("00003".equals(aLang.getItemCode())) {
//                                    forlExmp = "본교 석사 출신";
//                                } else if ("00005".equals(aLang.getItemCode())) {
//                                    forlExmp = "학과 면제";
//                                } else if ("00006".equals(aLang.getItemCode())) {
//                                    forlExmp = "본교 의예,치의예 졸업";
//                                } else if ("00007".equals(aLang.getItemCode())) {
//                                    forlExmp = "본교 의학,의전원 졸업";
//                                } else if ("00008".equals(aLang.getItemCode())) {
//                                    forlExmp = "구사 능력 증빙";
//                                }
                                forlExmp = "O";
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
        rptInfoMap.put("forlExmp", forlExmp.length() > 0 ? "O" : StringUtil.EMPTY_STRING);
        rptInfoMap.put("topikScore", commonService.retrieveCommonCodeByCodeGroupCode("TOPK_LEVL", topikScore).getCodeVal());

        // TODO
        String range0 = StringUtil.EMPTY_STRING;
        String corpName0 = StringUtil.EMPTY_STRING;
        String exprDesc0 = StringUtil.EMPTY_STRING;
        String range1 = StringUtil.EMPTY_STRING;
        String corpName1 = StringUtil.EMPTY_STRING;
        String exprDesc1 = StringUtil.EMPTY_STRING;
        String range2 = StringUtil.EMPTY_STRING;
        String corpName2 = StringUtil.EMPTY_STRING;
        String exprDesc2 = StringUtil.EMPTY_STRING;
        String range3 = StringUtil.EMPTY_STRING;
        String corpName3 = StringUtil.EMPTY_STRING;
        String exprDesc3 = StringUtil.EMPTY_STRING;
        String range4 = StringUtil.EMPTY_STRING;
        String corpName4 = StringUtil.EMPTY_STRING;
        String exprDesc4 = StringUtil.EMPTY_STRING;

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
        rptInfoMap.put("mltrServName", StringUtil.EMPTY_STRING);
        rptInfoMap.put("mltrJoinDay", StringUtil.EMPTY_STRING);
        rptInfoMap.put("mltrDschDay", StringUtil.EMPTY_STRING);
        rptInfoMap.put("mltrRankName", StringUtil.EMPTY_STRING);

        // TODO

        List<TotalApplicationDocument> docList = new ArrayList<TotalApplicationDocument>();
        getDocList(documentContainerList, docList);
        rptInfoMap.put("documents", docList);

        String appId = "지원 미완료";

        if( application.getApplId() != null && !application.getApplId().equals(StringUtil.EMPTY_STRING)){
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

    private String getEncryptedString(String input, boolean isEncrypt) throws IOException {
        Properties prop = new Properties();
        InputStream is = servletContext.getResourceAsStream("WEB-INF/grad-ks");
        String result = null;

        try {
            prop.load(is);
            TextEncryptor textEncryptor = Encryptors.queryableText(prop.getProperty("ENC_PSWD"), prop.getProperty("ENC_SALT"));
            result = isEncrypt ? textEncryptor.encrypt(input) : textEncryptor.decrypt(input);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                throw new YSBizException(e);
            }
        }
        return result;
    }
}
