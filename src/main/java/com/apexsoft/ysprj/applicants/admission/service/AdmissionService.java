package com.apexsoft.ysprj.applicants.admission.service;

import com.apexsoft.ysprj.applicants.admission.domain.Admission;
import com.apexsoft.ysprj.applicants.admission.domain.AdmissionCourseMajor;
import com.apexsoft.ysprj.applicants.admission.domain.AdmissionCourseMajorLanguage;
import com.apexsoft.ysprj.applicants.admission.domain.ParamForAdmissionCourseMajor;

import java.util.List;

/**
 * Created by hanmomhanda on 14. 8. 28.
 */
public interface AdmissionService {

    AdmissionCourseMajor retrieveEngMdtYn(ParamForAdmissionCourseMajor param);
    List<AdmissionCourseMajorLanguage> retrieveAvailableEngExamList(ParamForAdmissionCourseMajor param);

    Admission retrieveAdmissionByAdmsNo(String admsNo);
}
