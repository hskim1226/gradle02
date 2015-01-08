package com.apexsoft.ysprj.applicants.evaluation.service;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.ysprj.applicants.application.domain.*;
import com.apexsoft.ysprj.applicants.evaluation.domain.DocGroup;

import java.util.List;

/**
 * Created by Administrator on 2014-08-12.
 */
public interface EvaluationService {

   DocGroup retrieveDocGroupByApplNo( int applNo);
}


