package com.apexsoft.ysprj.admin.service;

import com.apexsoft.ysprj.admin.control.form.ChangeInfoForm;
import com.apexsoft.ysprj.admin.control.form.ChangeSearchPageForm;
import com.apexsoft.ysprj.admin.domain.ChangeInfo;
import com.apexsoft.framework.persistence.dao.page.PageInfo;



public interface ChangeService {

    void createInfoChange( ChangeInfoForm changeInfoForm );

    PageInfo<ChangeInfo> retrieveChangePaginatedList(ChangeSearchPageForm searchForm);

}
