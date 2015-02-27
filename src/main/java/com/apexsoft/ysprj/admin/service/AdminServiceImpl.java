package com.apexsoft.ysprj.admin.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.ysprj.admin.control.form.*;
import com.apexsoft.ysprj.applicants.admission.domain.Admission;
import com.apexsoft.ysprj.applicants.common.domain.*;

import com.apexsoft.ysprj.applicants.common.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.framework.persistence.dao.page.PageInfo;
import com.apexsoft.framework.persistence.dao.page.PageStatement;
import com.apexsoft.ysprj.admin.domain.*;
import com.apexsoft.ysprj.applicants.application.domain.ApplicationDocument;

import javax.annotation.Resource;


@Service
public class AdminServiceImpl implements AdminService{
	

    private final static String NAME_SPACE = "admin.applicant.";
    private final static String CANCEL_NAME_SPACE = "admin.cancel.";    
    private final static String APPL_NAME_SPACE = "applicants.application.sqlmap.";
    private final static String ADMS_NAME_SPACE = "com.apexsoft.ysprj.applicants.admission.sqlmap.";

    @Autowired
    private CommonDAO commonDAO;

    @Autowired
    private CommonService commonService;

    @Resource(name = "messageResolver")
    MessageResolver messageResolver;

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

            admsList = commonDAO.queryForList(ADMS_NAME_SPACE +"CustomAdmissionMapper.selectByYear","2015", Admission.class);
            if( courseSearchPageForm.getAdmsNo()!= null) {
                PageStatement tempStst = new PageStatement(NAME_SPACE+"retrieveApplicantCountByDept", NAME_SPACE+"retrieveApplicantListByDept");

                tempPageInfo = commonDAO.queryForPagenatedList( tempStst, courseSearchPageForm, courseSearchPageForm.getPage().getNo(), courseSearchPageForm.getPage().getRows() );
                List<ApplicantInfo> tempInfoList = tempPageInfo.getData();
                for( int idx=0; idx< tempInfoList.size(); idx++) {
                    ApplicantInfo tempInfo = tempInfoList.get(idx);
                    int applNo = tempInfo.getApplNo();
                    tempInfo.setDocList(commonDAO.queryForList(NAME_SPACE + "selectByApplNo", applNo, ApplicationDocument.class));
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
            ecDataMap.put("applInfo",applInfo);
            ecDataMap.put("selection", getCouurseSelectionBasicMap());

            ec.setData(ecDataMap);

        }catch(Exception e){
            e.printStackTrace();

        }
        return ec;
    }
	  

    public List<ApplicantCnt> retrieveApplicantCntByDept(CourseSearchGridForm searchForm) {
    	List<ApplicantCnt> campusList = null;
        try {

           campusList = commonDAO.queryForList(NAME_SPACE+"selectApplicantCnt", searchForm, ApplicantCnt.class);
           
        } catch (Exception e) {
            e.printStackTrace();
        }
        return campusList;
    }

    public List<ApplicantCnt> retrieveApplicantCntByRecent(CourseSearchGridForm searchForm) {
        List<ApplicantCnt> campusList = null;
        try {

            campusList = commonDAO.queryForList(NAME_SPACE+"selectApplicantRecentCnt", searchForm, ApplicantCnt.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return campusList;
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
        List<Admission> admsList = null;
        List<Campus> campList = new ArrayList<Campus>();
        List<CommonCode> applAttrList = new ArrayList<CommonCode>();
        List<College> collList = new ArrayList<College>();
        List<Department> deptList = new ArrayList<Department>();
        campList = commonService.retrieveCampus();
        admsList = commonDAO.queryForList(ADMS_NAME_SPACE +"CustomAdmissionMapper.selectByYear","2015", Admission.class);
        if(searchForm.getCampCode()!= null ) {
            collList = commonService.retrieveCollegeByCampus(searchForm.getCampCode());
        }
        if(searchForm.getDeptCode()!=null){
            deptList = commonDAO.queryForList(NAME_SPACE+"selectDepartmentListByCollege", searchForm, Department.class);
        }
        applAttrList= commonService.retrieveCommonCodeValueByCodeGroup("APPL_ATTR");
        if (admsList != null)      selectionMap.put("admsList", admsList);
        if (applAttrList != null)  selectionMap.put("applAttrList", applAttrList);
        if (campList != null)      selectionMap.put("campList", campList);
        if (collList != null)      selectionMap.put("collList", collList);
        if (deptList != null)      selectionMap.put("deptList", deptList);

        return selectionMap;
    }
    private Map<String, Object> getCouurseSelectionBasicMap(){
        CourseSearchForm aForm = new ChangeSearchForm();
        return getCouurseSelectionBasicMap( aForm);
    }
    public List<ApplicantCnt> retrieveUnpaidApplicantCntByDept(CourseSearchGridForm searchForm) {
        List<ApplicantCnt> campusList = null;
        try {

            campusList = commonDAO.queryForList(NAME_SPACE+"selectUnpaidApplicantCnt", searchForm, ApplicantCnt.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return campusList;
    }

}
