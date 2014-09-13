package com.apexsoft.ysprj.admin.service;

import java.util.List;

import com.apexsoft.framework.persistence.dao.page.PageInfo;
import com.apexsoft.ysprj.admin.control.form.ApplicantSearchForm;
import com.apexsoft.ysprj.admin.control.form.CourseSearchForm;
import com.apexsoft.ysprj.admin.domain.*;


public interface AdminService {
	
	 PageInfo<ApplicantInfo> retrieveApplicantPaginatedListByName(ApplicantSearchForm applicantSearchForm);
	 
	 PageInfo<ApplicantInfo> retrieveApplicantPaginatedListByDept(CourseSearchForm courseSearchForm);
	 
	 PageInfo<ApplicantInfo> retrieveApplicantPaginatedList(ApplicantSearchForm applicantSearchForm);	
	 
	 ApplicantInfo getApplicantDetail(int id);
	 
	 List<ApplicantCnt> retrieveApplicantCntByDept(CourseSearchForm searchForm);
	 
	 List<ApplicantInfo> getApplicantListForSelect(ApplicantSearchForm searchForm);	 
	 
	 ApplicantInfo getApplicantInfo(ApplicantSearchForm applicantSearchForm);
	 
}
