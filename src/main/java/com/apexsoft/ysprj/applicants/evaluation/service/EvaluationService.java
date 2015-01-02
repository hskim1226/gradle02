package com.apexsoft.ysprj.applicants.evaluation.service;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.ysprj.applicants.application.domain.*;

import java.util.List;

/**
 * Created by Administrator on 2014-08-12.
 */
public interface EvaluationService {

    List<DocGroupFile> retrieveManApplDocListByApplNo(int applNo);
}


