package com.apexsoft.ysprj.admin.service;

import com.apexsoft.framework.persistence.dao.page.PageInfo;

import com.apexsoft.ysprj.admin.control.form.ApplicantSearchPageForm;

import com.apexsoft.ysprj.admin.domain.ApplicantInfo;


public interface GuidelineService {
	
	 PageInfo<ApplicantInfo> retreiveDetManageInfo(ApplicantSearchPageForm applicantSearchForm);




}
