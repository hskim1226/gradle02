package com.apexsoft.ysprj.sysadmin.service;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.ysprj.applicants.application.domain.Application;
import com.apexsoft.ysprj.sysadmin.domain.StudentNumber;

/**
 * Created by hanmomhanda on 15. 4. 16.
 */
public interface SysAdminService {

    ExecutionContext processReGenMergeUpload(Application application);

    ExecutionContext downloadAllPdf();

    ExecutionContext downloadAllSlipPdf();

    ExecutionContext downaloadRenamedPictures();
}
