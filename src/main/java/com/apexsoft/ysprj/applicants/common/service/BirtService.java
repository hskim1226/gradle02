package com.apexsoft.ysprj.applicants.common.service;

import com.apexsoft.framework.common.vo.ExecutionContext;

/**
 * Created by hanmomhanda on 15. 2. 24.
 */
public interface BirtService {

    ExecutionContext processBirt(int applNo, String birtRptFileName);
}
