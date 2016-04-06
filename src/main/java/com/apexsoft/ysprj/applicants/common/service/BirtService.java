package com.apexsoft.ysprj.applicants.common.service;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.ysprj.applicants.application.domain.Application;

import java.util.Map;

/**
 * Created by hanmomhanda on 15. 2. 24.
 */
public interface BirtService {

//    ExecutionContext processBirt(int applNo, String birtRptFileName);
    ExecutionContext processBirt(Application application, String birtRptFileName);

//    ExecutionContext generateBirtFile(int applNo, String birtRptFileName);
    ExecutionContext generateBirtFile(Application application, String birtRptFileName);

}
