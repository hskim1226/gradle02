package com.apexsoft.ysprj.applicants.admission.service;

import com.apexsoft.ysprj.applicants.admission.domain.AdmissionCourseMajor;
import com.apexsoft.ysprj.applicants.admission.domain.AdmissionCourseMajorKey;

/**
 * Created by hanmomhanda on 14. 8. 28.
 */
public interface AdmissionService {

    AdmissionCourseMajor retrieveEngMdtYn(AdmissionCourseMajorKey admissionCourseMajorKey);
}
