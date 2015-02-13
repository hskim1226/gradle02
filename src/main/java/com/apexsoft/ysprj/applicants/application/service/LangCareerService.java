package com.apexsoft.ysprj.applicants.application.service;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.ysprj.applicants.application.domain.*;

import java.util.List;

/**
 * Created by hanmomhanda on 15. 1. 13.
 *
 * 원서 기본 정보 서비스
 */
public interface LangCareerService {

    ExecutionContext retrieveLangCareer(int applNo);

    ExecutionContext retrieveLangCareer(LangCareer langCareer);

    ExecutionContext saveLangCareer(LangCareer langCareer);
}
