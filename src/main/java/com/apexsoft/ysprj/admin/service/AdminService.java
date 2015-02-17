package com.apexsoft.ysprj.admin.service;

import java.util.List;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.persistence.dao.page.PageInfo;
import com.apexsoft.ysprj.admin.control.form.*;
import com.apexsoft.ysprj.admin.domain.*;


public interface AdminService {

    ExecutionContext retrieveApplicantPaginatedListByApplicantInfo(CourseSearchPageForm courseSearchForm);

    ExecutionContext retrieveApplicantPaginatedListByDept(CourseSearchPageForm courseSearchForm);

    PageInfo<ApplicantInfo> retrieveApplicantPaginatedList(ApplicantSearchPageForm applicantSearchForm);

    ExecutionContext getApplicantDetail(int id);
	 
	List<ApplicantCnt> retrieveApplicantCntByDept(CourseSearchGridForm searchForm);
	 
	List<ApplicantInfo> getApplicantListForSelect(ApplicantSearchForm searchForm);
	 
	ApplicantInfo getApplicantInfo(ApplicantSearchForm applicantSearchForm);




}
