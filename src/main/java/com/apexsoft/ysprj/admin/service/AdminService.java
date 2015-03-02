package com.apexsoft.ysprj.admin.service;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.persistence.dao.page.PageInfo;
import com.apexsoft.ysprj.admin.control.form.ApplicantSearchForm;
import com.apexsoft.ysprj.admin.control.form.ApplicantSearchPageForm;
import com.apexsoft.ysprj.admin.control.form.CourseSearchGridForm;
import com.apexsoft.ysprj.admin.control.form.CourseSearchPageForm;
import com.apexsoft.ysprj.admin.domain.ApplicantCnt;
import com.apexsoft.ysprj.admin.domain.ApplicantInfo;

import java.util.List;


public interface AdminService {

    ExecutionContext retrieveApplicantPaginatedListByApplicantInfo(CourseSearchPageForm courseSearchForm);

    ExecutionContext retrieveApplicantPaginatedListByDept(CourseSearchPageForm courseSearchForm);

    PageInfo<ApplicantInfo> retrieveApplicantPaginatedList(ApplicantSearchPageForm applicantSearchForm);

    ExecutionContext getApplicantDetail(int applNo, String applId);
	 
	List<ApplicantCnt> retrieveApplicantCntByDept(CourseSearchGridForm searchForm);

    List<ApplicantCnt> retrieveApplicantCntByRecent(CourseSearchGridForm searchForm);
	 
	ApplicantInfo getApplicantInfo(ApplicantSearchForm applicantSearchForm);

    List<ApplicantCnt> retrieveUnpaidApplicantCntByDept(CourseSearchGridForm searchForm);




}
