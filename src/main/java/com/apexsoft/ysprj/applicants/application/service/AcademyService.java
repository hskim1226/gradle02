package com.apexsoft.ysprj.applicants.application.service;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.ysprj.applicants.application.domain.Application;
import com.apexsoft.ysprj.applicants.application.domain.ApplicationGeneral;
import com.apexsoft.ysprj.applicants.application.domain.CustomApplicationAcademy;

import java.util.List;

/**
 * Created by hanmomhanda on 15. 1. 12.
 *
 * 원서 기본 정보 서비스
 */
public interface AcademyService {

    ExecutionContext createAcademy(Application application,
                                   List<CustomApplicationAcademy> collegeList,
                                   List<CustomApplicationAcademy> graduateList);

    ExecutionContext retrieveAcademy(int applNo);

    ExecutionContext updateAcademy(Application application,
                                   List<CustomApplicationAcademy> collegeList,
                                   List<CustomApplicationAcademy> graduateList);

    ExecutionContext deleteAcademy(Application application,
                                   List<CustomApplicationAcademy> collegeList,
                                   List<CustomApplicationAcademy> graduateList);
}
