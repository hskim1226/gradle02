package com.apexsoft.ysprj.applicants.application.service;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.ysprj.applicants.application.domain.Recommendation;

import java.util.List;

/**
 * Created by hanmomhanda on 15. 8. 14.
 */
public interface RecommendationService {

    Recommendation fillEtcInfo(Recommendation recommendation);

    ExecutionContext retrieveRecommendation(int recNo);

    ExecutionContext retrieveRecommendationList(int applNo);

    ExecutionContext previewRecommendation(Recommendation recommendation);

    ExecutionContext saveRecommendationRequest(Recommendation recommendation);

    ExecutionContext deleteRecommendationRequest(Recommendation recommendation);

    ExecutionContext sendRecommendationRequest(Recommendation recommendation);

    ExecutionContext openRecommendationRequestByProfessor(Recommendation recommendation);

    ExecutionContext retrieveRecommendationByProfessor(Recommendation recommendation);

    ExecutionContext registerRecommendationByProfessor(Recommendation recommendation);

    ExecutionContext retrieveUncompletedRecommendationList();

    ExecutionContext sendUrgeMail(List<Recommendation> recommendationList);
}
