package com.apexsoft.ysprj.admin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.framework.persistence.dao.page.PageInfo;
import com.apexsoft.framework.persistence.dao.page.PageStatement;
import com.apexsoft.ysprj.admin.service.AdminService;
import com.apexsoft.ysprj.admin.web.form.ApplicantSearchForm;
import com.apexsoft.ysprj.admin.domain.*;

@Service
public class AdminServiceImpl implements AdminService{
	

    private final static String NAME_SPACE = "admin.applicant.";

    @Autowired
    private CommonDAO commonDAO;

	  public PageInfo<ApplicantInfo> retrieveApplicantPaginatedListByPersionalInfo(ApplicantSearchForm applicantSearchForm){
		  	if (applicantSearchForm.getApplId().equals(null))
		  	if (applicantSearchForm.getEngName().equals(null))
		  	if (applicantSearchForm.getKorName().equals(null))
		  	if (applicantSearchForm.getRgstNo().equals(null)){
		  		PageInfo tempPageInfo = new PageInfo();
		  	}
		  	  
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
	        }, new ApplicantSearchForm(), applicantSearchForm.getPageNum(), applicantSearchForm.getPageRows() );
		 }

	  public PageInfo<ApplicantInfo> retrieveApplicantPaginatedList(ApplicantSearchForm applicantSearchForm){
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
        }, new ApplicantSearchForm(), applicantSearchForm.getPageNum(), applicantSearchForm.getPageRows() );
	 }

}
