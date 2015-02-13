package com.apexsoft.ysprj.admin.service;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.ysprj.admin.control.form.ChangeInfoForm;
import com.apexsoft.ysprj.admin.control.form.ChangeSearchPageForm;


public interface ChangeService {

    ExecutionContext createInfoChange( ChangeInfoForm changeInfoForm, String userId );

    ExecutionContext createUnitChange( ChangeInfoForm changeInfoForm, String userId );

    ExecutionContext retrieveChangePaginatedList(ChangeSearchPageForm searchForm);

}
