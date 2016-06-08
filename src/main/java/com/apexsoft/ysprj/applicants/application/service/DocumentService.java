package com.apexsoft.ysprj.applicants.application.service;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.ysprj.applicants.application.domain.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by hanmomhanda on 15. 1. 23.
 *
 * 제출 문서 업로드 서비스
 */
public interface DocumentService {

    ExecutionContext retrieveDocument(int applNo);

    ExecutionContext retrieveDocument(Document document);

    Map<String, byte[]> getDownloadableFileAsBytes(Application application, String type) throws IOException, InterruptedException;

    String retrievePhotoUri(int applNo);

    ExecutionContext saveDocument(Document document);

    ExecutionContext saveOneDocument(TotalApplicationDocument document);

    ExecutionContext submit(Document document);

    ExecutionContext retrieveOneDocument(ApplicationDocumentKey docKey);

    ExecutionContext deleteOneDocument(TotalApplicationDocument document);

    <T> ExecutionContext retrieveInfoListByParamObj(Object parameter, String mapperNameSqlId, Class<T> clazz);

    ExecutionContext saveApplicationPaperInfo(Application application);

    List<ApplicationDocument> retrieveApplicationPaperInfo(int applNo);

    Application getApplication(int applNo);

    ExecutionContext saveAdmissionSlipPaperInfo(Application application);

    List<ApplicationDocument> retrieveApplicationDocuments(int applNo);

    List<ApplicationDocument> retrieveRecommendationDocuments(int applNo);
}
