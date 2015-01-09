package com.apexsoft.ysprj.admin.service;

import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.framework.persistence.dao.page.PageInfo;
import com.apexsoft.framework.persistence.dao.page.PageStatement;
import com.apexsoft.ysprj.admin.control.form.ApplicantSearchForm;
import com.apexsoft.ysprj.admin.control.form.ApplicantSearchPageForm;
import com.apexsoft.ysprj.admin.control.form.CourseSearchGridForm;
import com.apexsoft.ysprj.admin.control.form.CourseSearchPageForm;
import com.apexsoft.ysprj.admin.domain.ApplicantCnt;
import com.apexsoft.ysprj.admin.domain.ApplicantInfo;
import com.apexsoft.ysprj.applicants.application.domain.ApplicationDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GuidelineServiceImpl implements GuidelineService{
	

    private final static String NAME_SPACE = "admin.applicant.";
    private final static String GUIDELINE_NAME_SPACE = "admin.guideline.";
    private final static String APPL_NAME_SPACE = "applicants.application.sqlmap.CustomApplicationDocumentMapper.";
    
    @Autowired
    private CommonDAO commonDAO;


	  public PageInfo<ApplicantInfo> retreiveDetManageInfo(ApplicantSearchPageForm applicantSearchForm){
		  
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


}
