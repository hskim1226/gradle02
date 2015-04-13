package com.apexsoft.ysprj.admin.service;

import com.apexsoft.ysprj.admin.domain.MandatoryContainer;
import com.apexsoft.ysprj.applicants.application.domain.ParamForApplicationMandatoryDoc;

import java.util.List;


public interface MandatoryRuleService {

    public List<MandatoryContainer> getWholeDeptMajMandTree( ParamForApplicationMandatoryDoc param );




}
