package com.apexsoft.ysprj.admin.service;

import java.util.List;

import com.apexsoft.ysprj.admin.control.form.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.framework.persistence.dao.page.PageInfo;
import com.apexsoft.framework.persistence.dao.page.PageStatement;
import com.apexsoft.ysprj.admin.domain.*;
import com.apexsoft.ysprj.applicants.application.domain.ApplicationDocument;


@Service
public class AdminServiceImpl implements AdminService{
	

    private final static String NAME_SPACE = "admin.applicant.";
    private final static String CANCEL_NAME_SPACE = "admin.cancel.";    
    private final static String APPL_NAME_SPACE = "applicants.application.sqlmap.CustomApplicationDocumentMapper.";
    
    @Autowired
    private CommonDAO commonDAO;


	  public PageInfo<ApplicantInfo> retrieveApplicantPaginatedListByName(ApplicantSearchPageForm applicantSearchForm){
		  
		   PageStatement tempStst = new PageStatement(NAME_SPACE+"retrieveApplicantCountByName", NAME_SPACE+"retrieveApplicantListByName");
		   PageInfo<ApplicantInfo>  tempPageInfo;
		   tempPageInfo = commonDAO.queryForPagenatedList( tempStst, applicantSearchForm, applicantSearchForm.getPage().getNo(), applicantSearchForm.getPage().getRows() );
		   List<ApplicantInfo> tempInfoList = tempPageInfo.getData();
		   for( int idx=0; idx< tempInfoList.size(); idx++){
			   ApplicantInfo tempInfo = tempInfoList.get(idx);
			   int applNo = tempInfo.getApplNo();
			try{   
			   tempInfo.setDocList(commonDAO.queryForList( NAME_SPACE+"selectByApplNo", applNo, ApplicationDocument.class));
			}catch(Exception e){
				e.printStackTrace();
			
			}
			   
		   }
		   return tempPageInfo;
		   
 	        //return commonDAO.queryForPagenatedList( tempStst, new ApplicantSearchForm(), applicantSearchForm.getPageNum(), applicantSearchForm.getPageRows() );
		 }
	  
	  public PageInfo<ApplicantInfo> retrieveApplicantPaginatedListByDept(CourseSearchPageForm courseSearchForm){
		  
		   PageStatement tempStst = new PageStatement(NAME_SPACE+"retrieveApplicantCountByDept", NAME_SPACE+"retrieveApplicantListByDept");
		   PageInfo<ApplicantInfo>  tempPageInfo;
		   tempPageInfo = commonDAO.queryForPagenatedList( tempStst, courseSearchForm, courseSearchForm.getPage().getNo(), courseSearchForm.getPage().getRows() );
		   List<ApplicantInfo> tempInfoList = tempPageInfo.getData();
		   for( int idx=0; idx< tempInfoList.size(); idx++){
			   ApplicantInfo tempInfo = tempInfoList.get(idx);
			   int applNo = tempInfo.getApplNo();
			try{   
			   tempInfo.setDocList(commonDAO.queryForList( NAME_SPACE+"selectByApplNo", applNo, ApplicationDocument.class));
			}catch(Exception e){
				e.printStackTrace();
			
			}
			   
		   }
		   return tempPageInfo;
		   

		 }	  

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
	  
	  public ApplicantInfo getApplicantDetail(int applNo){
		  ApplicantInfo applInfo = new ApplicantInfo();
		  applInfo = commonDAO.queryForObject(NAME_SPACE+"retrieveApplicantInfoByKey", applNo, ApplicantInfo.class);
		  applInfo.setDocList(commonDAO.queryForList( NAME_SPACE+"selectByApplNo", applNo, ApplicationDocument.class));
		  return applInfo;
	  }	  
  
	  
    @Override
    public List<ApplicantCnt> retrieveApplicantCntByDept(CourseSearchGridForm searchForm) {
    	List<ApplicantCnt> campusList = null;
        try {

           campusList = commonDAO.queryForList(NAME_SPACE+"selectApplicantCnt", searchForm, ApplicantCnt.class);
           
        } catch (Exception e) {
            e.printStackTrace();
        }
        return campusList;
    }
    
	  
	  public List<ApplicantInfo> getApplicantListForSelect(ApplicantSearchForm searchForm){
		  List<ApplicantInfo> applInfo = null;
		  applInfo = commonDAO.queryForList(CANCEL_NAME_SPACE+"retrieveApplicantListByNameForSelect", searchForm, ApplicantInfo.class);  
		  return applInfo;
	  }	  
	  
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


}
