package com.apexsoft.ysprj.applicants.common.service;

import com.apexsoft.framework.birt.spring.core.BirtEngineFactory;
import com.apexsoft.framework.birt.spring.core.CustomAbstractSingleFormatBirtProcessor;
import com.apexsoft.framework.birt.spring.core.CustomPdfSingleFormatBirtSaveToFile;
import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.exception.ErrorInfo;
import com.apexsoft.framework.exception.YSBizException;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.framework.persistence.file.exception.PhotoDownloadException;
import com.apexsoft.ysprj.applicants.application.domain.*;
import com.apexsoft.ysprj.applicants.application.service.AcademyService;
import com.apexsoft.ysprj.applicants.application.service.BasisService;
import com.apexsoft.ysprj.applicants.application.service.DocumentService;
import com.apexsoft.ysprj.applicants.application.service.LangCareerService;
import com.apexsoft.ysprj.applicants.common.domain.CommonCode;
import com.apexsoft.ysprj.applicants.common.domain.Country;
import com.apexsoft.ysprj.applicants.common.util.FilePathUtil;
import com.apexsoft.ysprj.applicants.common.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by hanmomhanda on 15. 2. 24.
 */
@Service
public class BirtServiceImpl implements BirtService {

    @Autowired
    private CommonService commonService;

    @Autowired
    private BasisService basisService;

    @Autowired
    private AcademyService academyService;

    @Autowired
    private LangCareerService langCareerService;

    @Autowired
    private DocumentService documentService;

    @Autowired
    private FilePersistenceService filePersistenceService;

    @Autowired
    private BirtEngineFactory birtEngineFactory;

    @Autowired
    private ServletContext servletContext;

    private static final Logger logger = LoggerFactory.getLogger(BirtServiceImpl.class);

    @Value("#{app['file.baseDir']}")
    private String BASE_DIR;

    @Value("#{app['rpt.format']}")
    private String REPORT_FORMAT;

    @Value("#{app['path.static']}")
    private String STATIC_PATH;

    @Value("#{app['file.midPath']}")
    private String midPath;

    @Value("#{app['site.tel']}")
    private String siteTel;

    @Value("#{app['site.helpdesk']}")
    private String helpdeskMail;

//    private Application application;

    /**
     * Birt로 파일 생성 및 서버 로컬에 저장
     *
     * @param application
     * @param birtRptFileName
     * @return
     */
    @Override
//    public ExecutionContext generateBirtFile(int applNo, String birtRptFileName) {
    public ExecutionContext generateBirtFile(Application application, String birtRptFileName) {

        ExecutionContext ec = processBirt(application, birtRptFileName);
        Map<String, Object> map = (Map<String, Object>)ec.getData();
        map.put("reportFormat", REPORT_FORMAT);
        map.put("reportName", birtRptFileName);
        String pathToRptdesignFile = "/reports/" +  birtRptFileName + ".rptdesign";
        String fullPathToRptdesignFile = servletContext.getRealPath(pathToRptdesignFile);
        map.put("rptdesignFullPath", fullPathToRptdesignFile);

        File photoFile = getPhotoFromFileRepo(application.getApplNo());
        map.put("photoUri", photoFile.getAbsolutePath());

        IReportEngine reportEngine = birtEngineFactory.getObject();
        CustomAbstractSingleFormatBirtProcessor birtProcessor = new CustomPdfSingleFormatBirtSaveToFile();
        birtProcessor.setBirtEngine(reportEngine);
        try {
            birtProcessor.createReport(map);
        } catch ( Exception e ) {
            e.printStackTrace();
            ExecutionContext ecError = new ExecutionContext(ExecutionContext.FAIL);

            ecError.setMessage(MessageResolver.getMessage("U803"));
            ecError.setErrCode("ERR0072");

            Map<String, String> errorInfo = new HashMap<String, String>();
            errorInfo.put("applNo", String.valueOf(application.getApplNo()));

            ecError.setErrorInfo(new ErrorInfo(errorInfo));
            throw new YSBizException(ecError);
        } finally {
            if (photoFile.exists()) {
                photoFile.delete();
            }
        }
        return ec;
    }

    private File getPhotoFromFileRepo(Integer applNo) {
        File photo = null;
        String filePath = documentService.retrievePhotoUri(applNo);
        try {
            photo = filePersistenceService.getFileFromFileRepo(BASE_DIR, filePath);
        } catch (Exception e) {
            ExecutionContext ec = new ExecutionContext(ExecutionContext.FAIL);
            ec.setMessage(MessageResolver.getMessage("U06107", new Object[]{siteTel, helpdeskMail}));
            ec.setErrCode("ERR1109");
            throw new PhotoDownloadException(ec);
        }
        return photo;
    }

    //원서 정보 수험표 정보 모두 여기서 추출
    @Override
//    public ExecutionContext processBirt(int applNo, String birtRptFileName) {
    public ExecutionContext processBirt(Application application, String birtRptFileName) {
        Map<String, Object> rptInfoMap = new HashMap<String, Object>();

        int applNo = application.getApplNo();
        ExecutionContext ecBasis = basisService.retrieveBasis(applNo);
        Basis basis = ((Map<String, Basis>)ecBasis.getData()).get("basis");
        application = basis.getApplication();
        String userId = application.getUserId();

        Map<String, Object> basisInfo = getBasisInfo(basis);
        rptInfoMap.putAll(basisInfo);

        Map<String, Object> academyInfo = getAcademyInfo(application);
        rptInfoMap.putAll(academyInfo);

        Map<String, Object> langCareerInfo = getLangCareerInfo(applNo);
        rptInfoMap.putAll(langCareerInfo);

        Map<String, Object> documentInfo = getDocumentInfo(applNo);
        rptInfoMap.putAll(documentInfo);

        ExecutionContext ecResult = new ExecutionContext();

        String pdfFileName = StringUtils.indexOf(birtRptFileName, "appl") > 0 ?
                FilePathUtil.getApplicationFormFileName(userId) :
                FilePathUtil.getApplicationSlipFileName(userId);

        rptInfoMap.put("pdfDirectoryFullPath", FilePathUtil.getUploadDirectoryFullPath(BASE_DIR, midPath, application.getAdmsNo(), userId, applNo));
        rptInfoMap.put("pdfFileName", pdfFileName);

        String applId = application.isApplIdIssued() ? application.getApplId() : "지원 미완료";
        rptInfoMap.put("applId", applId);

        ecResult.setData(rptInfoMap);

        return ecResult;
    }

    private Map<String,Object> getBasisInfo(Basis basis) {
        Map<String, Object> basisInfo = new HashMap<String, Object>();

        Application application = basis.getApplication();
        ApplicationGeneral applicationGeneral = basis.getApplicationGeneral();
        ApplicationForeigner applicationForeigner = basis.getApplicationForeigner();

        // 지원 과정 정보
        CommonCode commonCode;
        commonCode = commonService.retrieveCommonCodeByCodeGroupCode("ADMS_TYPE", application.getAdmsTypeCode());
        String admsTypeName = commonCode != null ? commonCode.getCodeVal() : null;
        String[] admsTypeNames = admsTypeName.split(" ");
        basisInfo.put("entrYear", application.getEntrYear());
        if( admsTypeNames.length > 1) {
            basisInfo.put("admsTypeName1", admsTypeNames[0]);
            basisInfo.put("admsTypeName2", admsTypeNames[1]);
        }
        basisInfo.put("title", application.getEntrYear() + " 학년도 " + admsTypeNames[0] + " " + admsTypeNames[1] + " 대학원 입학원서");

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
        String detlMajName = "99999".equals(application.getDetlMajCode()) ? application.getInpDetlMaj()
                : commonService.retrieveDetlMajNameByCode(application.getDetlMajCode()).getDetlMajName();
        basisInfo.put("campName", campName);
        basisInfo.put("semester", application.getEntrYear() + "-" + admsTypeNames[0]);
        basisInfo.put("corsTypeName", corsTypeName);
        basisInfo.put("ariInstName", ariInstName);
        basisInfo.put("deptName", deptName);
        basisInfo.put("deptCode", deptCode);
        basisInfo.put("detlMajName", detlMajName);

        // 지원자 정보
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

        basisInfo.put("korName", StringUtil.getEmptyIfNull(korName));
        basisInfo.put("engName", StringUtil.getEmptyIfNull(engName));
        basisInfo.put("engSur", StringUtil.getEmptyIfNull(engSur));
        basisInfo.put("gend", StringUtil.getEmptyIfNull(gend));
        basisInfo.put("engFullName", StringUtil.getEmptyIfNull(engName) + " " + StringUtil.getEmptyIfNull(engSur));

        String generalAdmsGender = StringUtil.EMPTY_STRING;
        if ( !application.isForeignAppl() ) {
            generalAdmsGender = "m".equals(StringUtil.getEmptyIfNull(gend).toLowerCase()) ? "(1******)" :
                    "f".equals(StringUtil.getEmptyIfNull(gend).toLowerCase()) ? "(2******)" : StringUtil.EMPTY_STRING;
        }
        basisInfo.put("rgstBornDate", StringUtil.getEmptyIfNull(rgstBornDate) + " " + generalAdmsGender);

        Country tmpCountry = commonService.retrieveCountryByCode(StringUtil.getEmptyIfNull(application.getCitzCntrCode()));
        basisInfo.put("citzCntrName", tmpCountry == null ? StringUtil.EMPTY_STRING : tmpCountry.getEngCntrName());

        if ( application.isForeignAppl()) {
            try {
                String fornRgstNoEncr = applicationForeigner.getFornRgstNoEncr();
                String fornRgstNo = fornRgstNoEncr != null && !StringUtil.EMPTY_STRING.equals(fornRgstNoEncr) ? getEncryptedString(fornRgstNoEncr, false) : StringUtil.EMPTY_STRING;
                if (fornRgstNo != null && fornRgstNo.length() > 0) {
                    basisInfo.put("fornRgstNo", StringUtil.insertHyphenAt(fornRgstNo, 6));
                } else {
                    basisInfo.put("fornRgstNo", "미등록"+"\n"+"(Not Registered)");
                }
                String paspNoEncr = applicationForeigner.getPaspNoEncr();
                String paspNo = paspNoEncr != null && !StringUtil.EMPTY_STRING.equals(paspNoEncr) ? getEncryptedString(paspNoEncr, false) : StringUtil.EMPTY_STRING;
                basisInfo.put("paspNo", paspNo);
//                String visaNoEncr = applicationForeigner.getVisaNoEncr();
//                String visaNo = visaNoEncr != null && !StringUtil.EMPTY_STRING.equals(visaNoEncr) ? getEncryptedString(visaNoEncr, false) : StringUtil.EMPTY_STRING;
            } catch (IOException e) {
                ExecutionContext ecEncr = new ExecutionContext(ExecutionContext.FAIL);
                ecEncr.setMessage(MessageResolver.getMessage("U347"));
                ecEncr.setErrCode("ERR0043");
                Map<String, String> errMap = new HashMap<>();
                errMap.put("applNo", String.valueOf(application.getApplNo()));
                errMap.put("userId", application.getUserId());
                errMap.put("situation", "Error while loading props for En/Decryption");
                ecEncr.setErrorInfo(new ErrorInfo(errMap));
                throw new YSBizException(ecEncr);
            }
        }

        basisInfo.put("visaTypeName", StringUtil.getEmptyIfNull(applicationForeigner.getVisaTypeCode()) + StringUtil.getEmptyIfNull(applicationForeigner.getVisaTypeEtc()));
//        basisInfo.put("fornRgstNo", StringUtil.getEmptyIfNull(applicationForeigner.getFornRgstNo()).length() > 0 ? "등록"+"\n"+"(Registered)" : "미등록"+"\n"+"(Not Registered)");
        basisInfo.put("homeAddr", StringUtil.getEmptyIfNull(applicationForeigner.getHomeAddr()));
        basisInfo.put("korAddr", StringUtil.getEmptyIfNull(addr) + " " + StringUtil.getEmptyIfNull(detlAddr));
        basisInfo.put("mailAddr", StringUtil.getEmptyIfNull(mailAddr));
        basisInfo.put("homeTel", StringUtil.getEmptyIfNull(applicationForeigner.getHomeTel()));
        basisInfo.put("telNum", StringUtil.getEmptyIfNull(telNum));
        basisInfo.put("mobiNum", StringUtil.getEmptyIfNull(mobiNum));
        basisInfo.put("addr", StringUtil.getEmptyIfNull(addr));
        basisInfo.put("detlAddr", StringUtil.getEmptyIfNull(detlAddr));
        basisInfo.put("homeEmrgName", StringUtil.getEmptyIfNull(applicationForeigner.getHomeEmrgName()));
        basisInfo.put("homeEmrgTel", StringUtil.getEmptyIfNull(applicationForeigner.getHomeEmrgTel()));
        CommonCode homeEmrgRela = commonService.retrieveCommonCodeByCodeGroupCode("EMER_CONT", StringUtil.getEmptyIfNull(applicationForeigner.getHomeEmrgRela()));
        String homeEmrgRelaVal = homeEmrgRela != null ? homeEmrgRela.getCodeVal() : StringUtil.EMPTY_STRING;
        basisInfo.put("homeEmrgRela", homeEmrgRelaVal);
        basisInfo.put("korEmrgName", StringUtil.getEmptyIfNull(applicationForeigner.getKorEmrgName()));
        basisInfo.put("korEmrgTel", StringUtil.getEmptyIfNull(applicationForeigner.getKorEmrgTel()));
        CommonCode korEmrgRela = commonService.retrieveCommonCodeByCodeGroupCode("EMER_CONT", StringUtil.getEmptyIfNull(applicationForeigner.getKorEmrgRela()));
        String korEmrgRelaVal = korEmrgRela != null ? korEmrgRela.getCodeVal() : StringUtil.EMPTY_STRING;
        basisInfo.put("korEmrgRela", korEmrgRelaVal);

        String currWrkpName = StringUtil.getEmptyIfNull(applicationGeneral.getCurrWrkpName());
        String currWrkpTel = StringUtil.getEmptyIfNull(applicationGeneral.getCurrWrkpTel());

        basisInfo.put("currWrkpName", currWrkpName);
        basisInfo.put("currWrkpTel", currWrkpTel);

        String hndcGrad = StringUtil.getEmptyIfNull(applicationGeneral.getHndcGrad());
        String hndcType = StringUtil.getEmptyIfNull(applicationGeneral.getHndcType());

        basisInfo.put("hndcGrad", hndcGrad);
        basisInfo.put("hndcType", hndcType);

        return basisInfo;
    }

//    private Map<String,Object> getAcademyInfo(int applNo) {
    private Map<String,Object> getAcademyInfo(Application application) {
        Map<String, Object> academyInfo = new HashMap<String, Object>();
        ExecutionContext ecAcademy = academyService.retrieveAcademy(application.getApplNo());
        Academy academy = ((Map<String, Academy>)ecAcademy.getData()).get("academy");

        List<CustomApplicationAcademy> collegeList = academy.getCollegeList();
        List<CustomApplicationAcademy> graduateList = academy.getGraduateList();
        List<CustomApplicationAcademy> academyList = new ArrayList<CustomApplicationAcademy>();
        academyList.addAll(collegeList);
        academyList.addAll(graduateList);

        boolean collLastFg = false;
        String lastCollegeScore = StringUtil.EMPTY_STRING;
        String lastGraduateScore = StringUtil.EMPTY_STRING;

        int collegeListL = collegeList.size();
        int i;
        for(i = 0 ; i < collegeListL ; i++) {
            CustomApplicationAcademy aColl = collegeList.get(i);

            if (application.isForeignAppl()) {
                academyInfo.put("acadPeriod" + i, aColl.getEntrDay() + "~" + aColl.getGrdaDay());
                academyInfo.put("academy" + i, "(대학) "+ aColl.getSchlName() + " " + aColl.getCollName());
                academyInfo.put("majName" + i, aColl.getMajName());
                academyInfo.put("gpaFull" + i, aColl.getGradAvr() + " / " + aColl.getGradFull());
            } else {
                academyInfo.put("academy" + i, "(대학) "+ aColl.getSchlName() + " " + aColl.getCollName() + " " + aColl.getMajName());
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
        int j;
        for(j = 0 ; j < graduateListL ; j++) {
            CustomApplicationAcademy aGrad = graduateList.get(j);

            if (application.isForeignAppl()) {
                academyInfo.put("acadPeriod" + (i+j), aGrad.getEntrDay() + "~" + aGrad.getGrdaDay());
                academyInfo.put("academy" + (i+j), "(대학원) " + aGrad.getSchlName() + " " + aGrad.getCollName());
                academyInfo.put("majName" + (i+j), aGrad.getMajName());
                academyInfo.put("gpaFull" + (i+j), aGrad.getGradAvr() + " / " + aGrad.getGradFull());
            } else {
                academyInfo.put("academy" + (i+j), "(대학원) "+ aGrad.getSchlName() + " " + aGrad.getCollName() + " " + aGrad.getMajName());
            }
            if( !gradLastFg ) {
                lastGraduateScore = aGrad.getGradAvr() + " / " + aGrad.getGradFull();
            }
            if( "Y".equals(aGrad.getLastSchlYn())) {
                gradLastFg = true;
            }
        }

        for (int k = i+j ; k < 5 ; k++) {
            academyInfo.put("academy" + k, "");
            if (application.isForeignAppl()) {
                academyInfo.put("acadPeriod" + k, "");
                academyInfo.put("academy" + k, "");
                academyInfo.put("majName" + k, "");
                academyInfo.put("gpaFull" + k, "");
            } else {
                academyInfo.put("academy" + k, "");
            }
        }

        academyInfo.put("lastCollegeScore", lastCollegeScore);
        academyInfo.put("lastGraduateScore", lastGraduateScore);

        return academyInfo;
    }

    private Map<String,Object> getLangCareerInfo(int applNo) {
        Map<String, Object> langCareerInfo = new HashMap<String, Object>();
        ExecutionContext ecLangCareer = langCareerService.retrieveLangCareer(applNo);
        LangCareer langCareer = ((Map<String, LangCareer>)ecLangCareer.getData()).get("langCareer");
        List<LanguageGroup> langGrpList = langCareer.getLanguageGroupList();
        List<CustomApplicationExperience> expList = langCareer.getApplicationExperienceList();

        String toeflScore = StringUtil.EMPTY_STRING;
        String toeicScore = StringUtil.EMPTY_STRING;
        String tepsScore = StringUtil.EMPTY_STRING;
        String ieltsScore = StringUtil.EMPTY_STRING;
        String greScore = StringUtil.EMPTY_STRING;
        String topikScore = StringUtil.EMPTY_STRING;

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
                                    topikScore = commonService.retrieveCommonCodeByCodeGroupCode("TOPK_LEVL", topikScore).getCodeVal();
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

        langCareerInfo.put("toeflScore", toeflScore);
        langCareerInfo.put("toeicScore", toeicScore);
        langCareerInfo.put("tepsScore", tepsScore);
        langCareerInfo.put("ieltsScore", ieltsScore);
        langCareerInfo.put("greScore", greScore);
        langCareerInfo.put("forlExmp", forlExmp.length() > 0 ? "O" : StringUtil.EMPTY_STRING);
        langCareerInfo.put("topikScore", topikScore);

        // 경력
        for (int i = 0, len = expList.size() ; i < len ; i++) {
            CustomApplicationExperience aExpr = expList.get(i);
            langCareerInfo.put("range" + i, aExpr.getJoinDay() +"~"+aExpr.getRetrDay());
            langCareerInfo.put("corpName" + i, aExpr.getCorpName());
            langCareerInfo.put("exprDesc" + i, aExpr.getExprDesc());
        }

        // 군경력 - rpt 파일에서 사용하지 않고 있음
        langCareerInfo.put("mltrServName", StringUtil.EMPTY_STRING);
        langCareerInfo.put("mltrJoinDay", StringUtil.EMPTY_STRING);
        langCareerInfo.put("mltrDschDay", StringUtil.EMPTY_STRING);
        langCareerInfo.put("mltrRankName", StringUtil.EMPTY_STRING);


        return langCareerInfo;
    }

    private Map<String,Object> getDocumentInfo(int applNo) {
        Map<String, Object> documentInfo = new HashMap<String, Object>();
        ExecutionContext ecDocument = documentService.retrieveDocument(applNo);
        Document document = ((Map<String, Document>)ecDocument.getData()).get("document");
        List<TotalApplicationDocumentContainer> documentContainerList = document.getDocumentContainerList();
        List<TotalApplicationDocument> docList = new ArrayList<TotalApplicationDocument>();
        getDocList(documentContainerList, docList);
        documentInfo.put("documents", docList);
        return documentInfo;
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
