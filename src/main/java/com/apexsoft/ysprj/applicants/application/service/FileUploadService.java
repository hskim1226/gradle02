package com.apexsoft.ysprj.applicants.application.service;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.ysprj.applicants.application.domain.*;

import java.util.List;

/**
 * Created by hanmomhanda on 15. 1. 23.
 *
 * 원서 기본 정보 서비스
 */
public interface FileUploadService {

    ExecutionContext createFileUpload(Application application,
                                      List<DocGroupFile> docGroupFileList);

    List<TotalApplicationDocumentContainer> retrieveManatoryApplicatoinlDocListByApplNo(int applNo);

    ExecutionContext updateFileUpload(Application application,
                                      List<DocGroupFile> docGroupFileList);

    ExecutionContext deleteFileUpload(Application application,
                                      List<DocGroupFile> docGroupFileList);
}
