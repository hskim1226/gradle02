package com.apexsoft.ysprj.applicants.common.service;

import com.apexsoft.framework.common.vo.ExecutionContext;

/**
 * Created by hanmomhanda on 15. 2. 22.
 */
public interface PDFService {

    ExecutionContext getMergedPDFByApplicants(int applNo);
}
