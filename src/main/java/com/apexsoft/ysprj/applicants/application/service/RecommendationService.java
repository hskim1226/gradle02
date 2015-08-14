package com.apexsoft.ysprj.applicants.application.service;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.ysprj.applicants.application.domain.Recommendation;

/**
 * Created by hanmomhanda on 15. 8. 14.
 */
public interface RecommendationService {

    ExecutionContext retrieveRecommendation(int recNo);

    ExecutionContext retrieveRecommendationList(int applNo);

    ExecutionContext saveRecommendationRequest(Recommendation recommendation);

    ExecutionContext deleteRecommendationRequest(Recommendation recommendation);

    ExecutionContext registerRecommendationByProfessor(Recommendation recommendation);
}
