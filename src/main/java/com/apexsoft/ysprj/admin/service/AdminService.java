package com.apexsoft.ysprj.admin.service;

import com.apexsoft.framework.persistence.dao.page.PageInfo;
import com.apexsoft.ysprj.admin.domain.*;
import com.apexsoft.ysprj.admin.web.form.ApplicantSearchForm;


public interface AdminService {
	
	 PageInfo<ApplicantInfo> retrieveApplicantPaginatedListByPersionalInfo(ApplicantSearchForm applicantSearchForm);
	 PageInfo<ApplicantInfo> retrieveApplicantPaginatedList(ApplicantSearchForm applicantSearchForm);	 

}
