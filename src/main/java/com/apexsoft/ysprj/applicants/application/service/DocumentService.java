package com.apexsoft.ysprj.applicants.application.service;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.ysprj.applicants.application.domain.*;

import java.util.List;

/**
 * Created by hanmomhanda on 15. 1. 23.
 *
 * 제출 문서 업로드 서비스
 */
public interface DocumentService {

    ExecutionContext retrieveDocument(int applNo);

    ExecutionContext retrieveDocument(Document document);

    ExecutionContext saveDocument(Document document);

    ExecutionContext saveOneDocument(TotalApplicationDocument document);

    ExecutionContext deleteOneDocument(TotalApplicationDocument document);
}
