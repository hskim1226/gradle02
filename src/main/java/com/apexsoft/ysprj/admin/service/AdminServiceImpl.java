package com.apexsoft.ysprj.admin.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.framework.persistence.dao.page.PagenateInfo;
import com.apexsoft.ysprj.admin.control.form.*;
import com.apexsoft.ysprj.applicants.admission.domain.Admission;
import com.apexsoft.ysprj.applicants.admission.domain.AdmissionName;
import com.apexsoft.ysprj.applicants.application.domain.*;
import com.apexsoft.ysprj.applicants.common.domain.*;

import com.apexsoft.ysprj.applicants.common.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.framework.persistence.dao.page.PageInfo;
import com.apexsoft.framework.persistence.dao.page.PageStatement;
import com.apexsoft.ysprj.admin.domain.*;

import javax.annotation.Resource;


@Service
public class AdminServiceImpl implements AdminService{


    private final static String NAME_SPACE = "admin.applicant.";
    private final static String CANCEL_NAME_SPACE = "admin.cancel.";
    private final static String ADMIN_NAME_SPACE = "com.apexsoft.ysprj.admin.sqlmap.";
    private final static String APPL_NAME_SPACE = "com.apexsoft.ysprj.applicants.application.sqlmap.";
    private final static String ADMS_NAME_SPACE = "com.apexsoft.ysprj.applicants.admission.sqlmap.";
    private final static String COMMON_NAME_SPACE = "com.apexsoft.ysprj.applicants.common.sqlmap.";

    @Autowired
    private CommonDAO commonDAO;

    @Autowired
    private CommonService commonService;

    @Autowired
    private AdmsNo admsNo;

    @Value("#{app['adms.enterYear']}")
    private String enterYear;

    @Override
    public ExecutionContext retrieveApplicantPaginatedListByApplicantInfo(CourseSearchPageForm courseSearchPageForm){
        ExecutionContext ec = new ExecutionContext();
        Map<String, Object> ecDataMap = new HashMap<String, Object>();
        Map<String, Object> selectionMap = new HashMap<String, Object>();

        PageInfo<ApplicantInfo>  tempPageInfo =null;
        try{

            if( courseSearchPageForm.getKorName()!= null || courseSearchPageForm.getApplId()!= null) {
                PageStatement tempStst = new PageStatement(NAME_SPACE+"retrieveApplicantCountByName", NAME_SPACE+"retrieveApplicantListByName");

                tempPageInfo = commonDAO.queryForPagenatedList( tempStst, courseSearchPageForm, courseSearchPageForm.getPage().getNo(), courseSearchPageForm.getPage().getRows() );
                List<ApplicantInfo> tempInfoList = tempPageInfo.getData();
                for( int idx=0; idx< tempInfoList.size(); idx++) {
                    ApplicantInfo tempInfo = tempInfoList.get(idx);
                    int applNo = tempInfo.getApplNo();
                    tempInfo.setAdmsName(abridgeAdmsCodeName(tempInfo.getAdmsNo()));
                    tempInfo.setDocList(commonDAO.queryForList(NAME_SPACE + "selectByApplNo", applNo, ApplicationDocument.class));
                }

                ecDataMap.put("applList", tempPageInfo.getData());
                ecDataMap.put("totalCnt", tempPageInfo.getTotalRowCount());

            }else{

                ecDataMap.put("applList", new ArrayList<ApplicantInfo>());
                ecDataMap.put("applTotal", 0);
            }



            ecDataMap.put("searchPageForm", courseSearchPageForm);

            ec.setData(ecDataMap);

        }catch(Exception e){
            e.printStackTrace();

        }
        return ec;
    }

    @Override
    public ExecutionContext retrieveApplicantPaginatedListByDept(CourseSearchPageForm courseSearchPageForm){
        ExecutionContext ec = new ExecutionContext();
        Map<String, Object> ecDataMap = new HashMap<String, Object>();
        List<Admission> admsList = null;
        List<Campus> campList = null;
        List<College> collList = null;
        List<CodeNameDepartment> deptList = null;

        PageInfo<ApplicantInfo>  tempPageInfo =null;
        try{

            ParamForSetupCourses param = new ParamForSetupCourses();
            param.setAdmsNo(courseSearchPageForm.getAdmsNo());
            param.setCollCode(courseSearchPageForm.getCollCode());
            param.setDeptCode(courseSearchPageForm.getDeptCode());
            campList = commonService.retrieveCampus();
            List<ApplicationCheck> applChkList= null;
            admsList = commonDAO.queryForList(ADMS_NAME_SPACE +"CustomAdmissionMapper.selectByYear", enterYear, Admission.class);
            // 2016-01에는 조기 전형 없으므로 아래 행 주석 처리
//            admsList.addAll(commonDAO.queryForList(ADMS_NAME_SPACE +"CustomAdmissionMapper.selectByYear","2016", Admission.class));
            if( courseSearchPageForm.getAdmsNo()!= null) {
                PageStatement tempStst = new PageStatement(NAME_SPACE+"retrieveApplicantCountByDept", NAME_SPACE+"retrieveApplicantListByDept");

                tempPageInfo = commonDAO.queryForPagenatedList( tempStst, courseSearchPageForm, courseSearchPageForm.getPage().getNo(), courseSearchPageForm.getPage().getRows() );
                List<ApplicantInfo> tempInfoList = tempPageInfo.getData();
                for( int idx=0; idx< tempInfoList.size(); idx++) {
                    ApplicantInfo tempInfo = tempInfoList.get(idx);
                    int applNo = tempInfo.getApplNo();
                    tempInfo.setAdmsName(abridgeAdmsCodeName(tempInfo.getAdmsNo()));
                    tempInfo.setDocList(commonDAO.queryForList(NAME_SPACE + "selectByApplNo", applNo, ApplicationDocument.class));

                    applChkList =commonDAO.queryForList(ADMIN_NAME_SPACE + "CustomApplicationCheckMapper.selectByApplNo",tempInfo.getApplNo(), ApplicationCheck.class );
                    for( ApplicationCheck aChk :applChkList){
                        if("Y".equals(aChk.getChkYn())){
                            tempInfo.setCheckYn("Y");
                            break;
                        }
                    }
                }

                collList = commonService.retrieveCollegeByCampus( courseSearchPageForm.getCampCode() );
                deptList = commonService.retrieveGeneralDepartmentByAdmsColl(param);
                ecDataMap.put("applList", tempPageInfo.getData());
                ecDataMap.put("totalCnt", tempPageInfo.getTotalRowCount());

            }else{

                ecDataMap.put("applList", new ArrayList<ApplicantInfo>());
                ecDataMap.put("applTotal", 0);
            }
            ecDataMap.put("searchPageForm", courseSearchPageForm);
            ecDataMap.put("selection", getCouurseSelectionMap(courseSearchPageForm ));

            ec.setData(ecDataMap);

        }catch(Exception e){
            e.printStackTrace();

        }
        return ec;
    }
    @Override
	  public PageInfo<ApplicantInfo> retrieveApplicantPaginatedList(ApplicantSearchPageForm applicantSearchForm){
        return commonDAO.queryForPagenatedList(new PageStatement(){
            /**
             * @return the totalCountStatementId
             */
            public String getTotalCountStatementId() {
                return NAME_SPACE+"retrieveApplicantCountByCondition";
            }

            /**
             * @return the dataStatementId
             */
            public String getDataStatementId() {
                return NAME_SPACE+"retrieveApplicantListByCondition";
            }
        }, new ApplicantSearchForm(), applicantSearchForm.getPage().getNo(), applicantSearchForm.getPage().getRows() );
	  }

    @Override
    public ExecutionContext getApplicantDetail(int applNo, String applId){
        ExecutionContext ec;

        if( applNo > 0){
            ec = getApplicantDetailByApplNo(applNo);
        }else if(applId != null && applId != "") {
            ec = getApplicantDetailByApplId(applId);
        }else{
            ec = new ExecutionContext();
            Map<String, Object> ecDataMap = new HashMap<String, Object>();
            ec.setData(ecDataMap);
        }
        return ec;
    }

    private ExecutionContext getApplicantDetailByApplNo(int applNo){
        ExecutionContext ec = new ExecutionContext();
        Map<String, Object> ecDataMap = new HashMap<String, Object>();
        ApplicantInfo applInfo = new ApplicantInfo();
        try{

            applInfo = commonDAO.queryForObject(NAME_SPACE+"retrieveApplicantInfoByKey", applNo, ApplicantInfo.class);

            ApplicationForeigner applForn = commonDAO.queryForObject(APPL_NAME_SPACE + "ApplicationForeignerMapper.selectByPrimaryKey",
                    applNo, ApplicationForeigner.class);
            applForn = applForn == null ? new ApplicationForeigner() : applForn;
            ApplicationGeneral applGene = commonDAO.queryForObject(APPL_NAME_SPACE + "ApplicationGeneralMapper.selectByPrimaryKey",
                    applNo, ApplicationGeneral.class);
            applGene = applGene == null ? new ApplicationGeneral() : applGene;
            Country  cntr = commonDAO.queryForObject( COMMON_NAME_SPACE+"CountryMapper.selectByPrimaryKey",applInfo.getCitzCntrCode(), Country.class);
            cntr = cntr == null ? new Country() : cntr;
            applInfo.setAdmsName(abridgeAdmsCodeName(applInfo.getAdmsNo()));
            ecDataMap.put("cntr",cntr);
            ecDataMap.put("applForn",applForn);
            ecDataMap.put("applGene",applGene);
            ecDataMap.put("applInfo",applInfo);
            ecDataMap.put("selection", getCouurseSelectionBasicMap());

            ec.setData(ecDataMap);

        }catch(Exception e){
            e.printStackTrace();

        }
        return ec;
    }


    private ExecutionContext getApplicantDetailByApplId(String applId){
        ExecutionContext ec = new ExecutionContext();
        Map<String, Object> ecDataMap = new HashMap<String, Object>();
        ApplicantInfo applInfo = new ApplicantInfo();
        try{
            applInfo = commonDAO.queryForObject(NAME_SPACE+"retrieveApplicantInfoByApplId", applId, ApplicantInfo.class);
            applInfo.setAdmsName(abridgeAdmsCodeName(applInfo.getAdmsNo()));



            ecDataMap.put("applInfo",applInfo);
            ecDataMap.put("selection", getCouurseSelectionBasicMap());

            ec.setData(ecDataMap);

        }catch(Exception e){
            e.printStackTrace();

        }
        return ec;
    }
	  




    @Override
	  public ApplicantInfo getApplicantInfo(ApplicantSearchForm searchForm ){
		  ApplicantInfo applInfo = null;
		  int applCnt = 0;
		  if( searchForm.getKorName()==null || searchForm.getKorName().equals(""))
			  if( searchForm.getApplId()==null || searchForm.getApplId().equals(""))	
				  if( searchForm.getEngName()==null || searchForm.getEngName().equals(""))
					  if( searchForm.getEngSur()==null || searchForm.getEngSur().equals(""))
						  return applInfo;

		  applCnt= commonDAO.queryForInt(CANCEL_NAME_SPACE+"retrieveApplicantCountByName", searchForm);
		  if( applCnt == 1){
			  applInfo = commonDAO.queryForObject(NAME_SPACE+"retrieveApplicantListByName", searchForm, ApplicantInfo.class);
              applInfo.setAdmsName(abridgeAdmsCodeName(applInfo.getAdmsNo()));
		  }
		  return applInfo;
	  }


    public ExecutionContext getApplicantDetail(ApplicantSearchForm searchForm ){
        ApplicantInfo applInfo = new ApplicantInfo();
        applInfo = commonDAO.queryForObject(NAME_SPACE+"retrieveApplicantInfoByKey", searchForm.getApplNo(), ApplicantInfo.class);
        return null;
    }

    private Map<String, Object> getCouurseSelectionMap( CourseSearchForm searchForm){
        Map<String, Object> selectionMap = new HashMap<String, Object>();
        List<College> collList = null;
        List<CodeNameDepartment> deptList = null;

        ParamForSetupCourses param = new ParamForSetupCourses();
        param.setAdmsNo(searchForm.getAdmsNo());
        param.setCollCode(searchForm.getCollCode());
        param.setDeptCode(searchForm.getDeptCode());

        selectionMap = getCouurseSelectionBasicMap(searchForm);
        if (collList != null)      selectionMap.put("collList", collList);
        if (deptList != null)      selectionMap.put("deptList", deptList);

        return selectionMap;
    }
    private Map<String, Object> getCouurseSelectionBasicMap(CourseSearchForm searchForm){
        Map<String, Object> selectionMap = new HashMap<String, Object>();
        List<AdmissionName> admsList = null;
        List<Campus> campList = new ArrayList<Campus>();
        List<CommonCode> applAttrList = new ArrayList<CommonCode>();
        List<College> collList = new ArrayList<College>();
        List<Department> deptList = new ArrayList<Department>();
        campList = commonService.retrieveCampus();
        admsList = commonDAO.queryForList(ADMS_NAME_SPACE +"CustomAdmissionMapper.selectAdmsNameByYear", enterYear, AdmissionName.class);
        // 2016-1에는 조기전형 없으므로 아래 행 주석처리
//        admsList.addAll( commonDAO.queryForList(ADMS_NAME_SPACE +"CustomAdmissionMapper.selectAdmsNameByYear","2016", AdmissionName.class));
        addShortAdmissionName(admsList);
        if(searchForm.getCampCode()!= null ) {
            collList = commonService.retrieveCollegeByCampus(searchForm.getCampCode());
        }
        if(searchForm.getDeptCode()!=null){
            deptList = commonDAO.queryForList(NAME_SPACE+"selectDepartmentListByCollege", searchForm, Department.class);
        }
        applAttrList= commonService.retrieveCommonCodeByCodeGroup("APPL_ATTR");


        if (admsList != null)      selectionMap.put("admsList", admsList);
        if (applAttrList != null)  selectionMap.put("applAttrList", applAttrList);
        if (campList != null)      selectionMap.put("campList", campList);
        if (collList != null)      selectionMap.put("collList", collList);
        if (deptList != null)      selectionMap.put("deptList", deptList);

        return selectionMap;
    }
    public  Map<String, Object> getCouurseSelectionBasicMap(){
        CourseSearchForm aForm = new ChangeSearchForm();
        return getCouurseSelectionBasicMap( aForm);
    }



    @Override
    public ExecutionContext retrieveEntireApplicantListByDept(CourseSearchPageForm courseSearchPageForm){
        ExecutionContext ec = new ExecutionContext();
        Map<String, Object> ecDataMap = new HashMap<String, Object>();

        List<ApplicantInfoEntire>  tempInfoList =null;
        List<ApplicationCheck> applChkList= null;
        try{

            ParamForSetupCourses param = new ParamForSetupCourses();
            param.setAdmsNo(courseSearchPageForm.getAdmsNo());
            //param.setCorsTypeCode(courseSearchPageForm.getCorsTypeCode());
            param.setCollCode(courseSearchPageForm.getCollCode());
            param.setDeptCode(courseSearchPageForm.getDeptCode());
            //향후 학교나 연도별로 데이터가 섞여 있는 경우
            //admsList = commonDAO.queryForList(ADMS_NAME_SPACE +"CustomAdmissionMapper.selectByYear","2015", Admission.class);
            if( courseSearchPageForm.getAdmsNo()!= null) {

                tempInfoList = commonDAO.queryForList(NAME_SPACE + "retrieveApplicantEntireListByDept", courseSearchPageForm, ApplicantInfoEntire.class);

                for( ApplicantInfoEntire aInfo : tempInfoList ) {
                    aInfo.setAcadList(commonDAO.queryForList(APPL_NAME_SPACE + "CustomApplicationAcademyMapper.selectByApplNo", aInfo.getApplNo(), CustomApplicationAcademy.class));
                    aInfo.setLangList(commonDAO.queryForList(APPL_NAME_SPACE + "CustomApplicationLanguageMapper.selectByApplNo", aInfo.getApplNo(), ApplicationLanguage.class));
                    aInfo.setExprList(commonDAO.queryForList(APPL_NAME_SPACE + "CustomApplicationExperienceMapper.selectByApplNo", aInfo.getApplNo(), CustomApplicationExperience.class));
                    aInfo.setAdmsName(abridgeAdmsCodeName(aInfo.getAdmsNo()));
                }
                ecDataMap.put("applList",tempInfoList);


            }else{

                ecDataMap.put("applList", new ArrayList<ApplicantInfo>());

            }
            ecDataMap.put("searchForm", courseSearchPageForm);
            ec.setData(ecDataMap);

        }catch(Exception e){
            e.printStackTrace();

        }
        return ec;
    }
    public CommonAdminInfo retrieveCommonAdminInfo(){
        CommonAdminInfo info= null;
                info =commonDAO.queryForObject(NAME_SPACE+"retrieveStatusCount", admsNo, CommonAdminInfo.class);
        return info;
    }


    public ExecutionContext retrieveInitInfo(){
        ExecutionContext ec = new ExecutionContext();
        Map<String, Object> ecDataMap = new HashMap<String, Object>();
        PageInfo<CustomApplicationChange>  tempPageInfo =null;
        List<CustomApplicationChange> tempInfoList = null;
        List<ApplicantDailyCnt> weekCntList = null;
        List<ApplicantDailyCnt> corsCntList = null;
        List<CommonCodeCnt> chgCntList = null;
        List<CommonCodeCnt> applCntList = null;
        ChangeSearchPageForm searchForm = new ChangeSearchPageForm();
        searchForm.setPage(new PagenateInfo(5));
        searchForm.setAdmsNo("");
        try{
            weekCntList = commonDAO.queryForList(NAME_SPACE+"selectApplicantRecentCnt", ApplicantDailyCnt.class);
            corsCntList = commonDAO.queryForList(NAME_SPACE+"selectApplicantCntByCorse", ApplicantDailyCnt.class);

            PageStatement tempStst = new PageStatement(ADMIN_NAME_SPACE+"CustomApplicationChangeMapper.retrieveChangeCount", ADMIN_NAME_SPACE+"CustomApplicationChangeMapper.retrieveChangeList");

            tempPageInfo = commonDAO.queryForPagenatedList( tempStst, searchForm, searchForm.getPage().getNo(), searchForm.getPage().getRows() );

            tempInfoList = tempPageInfo.getData();
            chgCntList = commonDAO.queryForList(ADMIN_NAME_SPACE+"CustomApplicationChangeMapper.selectChangeStatusCnt", CommonCodeCnt.class);
            applCntList = commonDAO.queryForList(NAME_SPACE+"selectApplicantCntByAdms", CommonCodeCnt.class);
            for( CommonCodeCnt aCode :applCntList){
                aCode.setCodeVal(abridgeAdmsCodeName(aCode.getCode()));
            }


            ecDataMap.put("chgCntList", chgCntList);
            ecDataMap.put("chgList", tempInfoList);
            ecDataMap.put("weekCntList",weekCntList);
            ecDataMap.put("corsCntList",corsCntList);
            ec.setData(ecDataMap);
        }catch(Exception e){
            e.printStackTrace();
        }
        return ec;
    }

    public Map<String, Object> retrieveInterceptorInfo() {
        Map<String, Object> ecDataMap = new HashMap<String, Object>();
        List<CommonCodeCnt> applCntList = null;
        applCntList = commonDAO.queryForList(NAME_SPACE+"selectApplicantCntByAdms", CommonCodeCnt.class);
        for( CommonCodeCnt aCode :applCntList){
            aCode.setCodeVal(abridgeAdmsCodeName(aCode.getCode()));
        }
        CommonAdminInfo info= null;
        info =commonDAO.queryForObject(NAME_SPACE+"retrieveStatusCount", admsNo, CommonAdminInfo.class);

        ecDataMap.put("applCntList", applCntList);
        ecDataMap.put("adminInfo", info);

        return ecDataMap;
    }

    private void addShortAdmissionName(List<AdmissionName> admsList){
        for( AdmissionName aAdms : admsList){
           if( admsNo.getGeneral().equals(aAdms.getAdmsNo())){
                aAdms.setAdmsName("16년 전기 일반");
           }else if( admsNo.getForeign() .equals(aAdms.getAdmsNo())){
               aAdms.setAdmsName("16년 전기 외국인");
           }else if( admsNo.getEarly().equals(aAdms.getAdmsNo())){
               aAdms.setAdmsName("16년 후기 조기");
           }
        }
    }
    private String abridgeAdmsCodeName(String admsCode){
        String admsName = "";

        if( admsNo.getGeneral().equals(admsCode)) {
            admsName = "16년 전기 일반";
        }else if( admsNo.getForeign().equals(admsCode)){
            admsName ="16년 전기 외국인";
        }else if( admsNo.getEarly().equals(admsCode)){
            admsName ="16년 후기 조기";
        }
        return admsName;
    }
    private String shortAdmsCodeName(String admsCode){
        String admsName = "";

        if( admsNo.getGeneral().equals(admsCode)) {
            admsName = "일반";
        }else if( admsNo.getForeign().equals(admsCode)){
            admsName ="외국인";
        }else if( admsNo.getEarly().equals(admsCode)){
            admsName ="조기";
        }
        return admsName;
    }
}
