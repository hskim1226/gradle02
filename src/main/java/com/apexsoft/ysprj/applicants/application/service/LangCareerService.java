package com.apexsoft.ysprj.applicants.application.service;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.ysprj.applicants.application.domain.LangCareer;

/**
 * Created by hanmomhanda on 15. 1. 13.
 *
 * 원서 기본 정보 서비스
 */
public interface LangCareerService {

    ExecutionContext retrieveLangCareer(int applNo);

    ExecutionContext retrieveCurrentLangCareer(int applNo);

    ExecutionContext retrieveLangCareer(LangCareer langCareer);

    ExecutionContext saveLangCareer(LangCareer langCareer);

    void  retrieveLangSubCode(LangCareer langCareer);
}
