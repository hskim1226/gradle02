package com.apexsoft.ysprj.applicants.application.service;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.ysprj.applicants.application.domain.Academy;

/**
 * Created by hanmomhanda on 15. 1. 12.
 *
 * 원서 기본 정보 서비스
 */
public interface AcademyService {

    ExecutionContext retrieveSelectionMap(Academy academy);

    ExecutionContext retrieveAcademy(int applNo);

    ExecutionContext retrieveAcademy(Academy academy);

    ExecutionContext saveAcademy(Academy academy);

}
