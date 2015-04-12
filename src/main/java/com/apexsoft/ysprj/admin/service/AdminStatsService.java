package com.apexsoft.ysprj.admin.service;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.persistence.dao.page.PageInfo;
import com.apexsoft.ysprj.admin.control.form.ApplicantSearchForm;
import com.apexsoft.ysprj.admin.control.form.ApplicantSearchPageForm;
import com.apexsoft.ysprj.admin.control.form.CourseSearchGridForm;
import com.apexsoft.ysprj.admin.control.form.CourseSearchPageForm;
import com.apexsoft.ysprj.admin.domain.*;

import java.util.List;
import java.util.Map;


public interface AdminStatsService {


    List<ApplicantCnt> retrieveApplicantCntByDept(CourseSearchGridForm searchForm);

    List<ApplicantCnt> retrieveApplicantCntByRecent(CourseSearchGridForm searchForm);

    List<ApplicantCnt> retrieveApplicantDetailCntByDept(CourseSearchGridForm searchForm);

    List<ApplicantCnt> retrieveUnpaidApplicantCntByDept(CourseSearchGridForm searchForm);

    List<ApplicantDetailCnt> selectApplicantCntByAdmsDept(CourseSearchGridForm searchForm);
}
