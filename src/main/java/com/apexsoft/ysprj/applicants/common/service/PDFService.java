package com.apexsoft.ysprj.applicants.common.service;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.ysprj.applicants.application.domain.Application;

/**
 * Created by hanmomhanda on 15. 2. 22.
 */
public interface PDFService {

    ExecutionContext genAndUploadPDFByApplicants(Application application);
}
