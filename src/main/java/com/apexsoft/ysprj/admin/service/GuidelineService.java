package com.apexsoft.ysprj.admin.service;

import com.apexsoft.framework.persistence.dao.page.PageInfo;

import com.apexsoft.ysprj.admin.control.form.ApplicantSearchPageForm;

import com.apexsoft.ysprj.admin.domain.ApplicantInfo;
import com.apexsoft.ysprj.admin.domain.MandatoryContainer;
import com.apexsoft.ysprj.applicants.application.domain.ParamForApplicationMandatoryDoc;

import java.util.List;


public interface GuidelineService {

    public List<MandatoryContainer> getWholeDeptMajMandTree( ParamForApplicationMandatoryDoc param );




}
