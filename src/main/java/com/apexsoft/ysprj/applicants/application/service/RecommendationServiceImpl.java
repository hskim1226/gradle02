package com.apexsoft.ysprj.applicants.application.service;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.exception.ErrorInfo;
import com.apexsoft.framework.exception.YSBizException;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.ysprj.applicants.application.domain.*;
import com.apexsoft.ysprj.applicants.common.service.CommonService;
import com.apexsoft.ysprj.applicants.common.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.*;

/**
 * Created by hanmomhanda on 15. 1. 13.
 */
@Service
public class RecommendationServiceImpl implements RecommendationService {

    private final static String NAME_SPACE = "com.apexsoft.ysprj.applicants.application.sqlmap.";

    @Autowired
    private CommonDAO commonDAO;

    @Override
    public ExecutionContext retrieveRecommendation(int recNo) {
        ExecutionContext ec = new ExecutionContext();

        Recommendation recommendation =
                commonDAO.queryForObject(NAME_SPACE + "CustomRecommendationMapper.selectByRecNo",
                        recNo, Recommendation.class);

        ec.setData(recommendation);
        return ec;
    }

    @Override
    public ExecutionContext retrieveRecommendationList(int applNo) {
        ExecutionContext ec = new ExecutionContext();

        List<Recommendation> recommendationList =
                commonDAO.queryForList(NAME_SPACE + "CustomRecommendationMapper.selectListByApplNo",
                        applNo, Recommendation.class);

        ec.setData(recommendationList);
        return ec;
    }

    @Override
    public ExecutionContext saveRecommendationRequest(Recommendation recommendation) {
        ExecutionContext ec = new ExecutionContext();
        int applNo = recommendation.getApplNo();
        int recSeq = recommendation.getRecSeq();
        int r1 = 0;
        boolean isUpdate = recSeq > 0;

        recommendation.setRecStsCode("00001");

        if (isUpdate) {
            r1 = commonDAO.updateItem(recommendation, NAME_SPACE, "CustomRecommendationMapper");
        } else {
            recommendation.setRecSeq(++recSeq);
            r1 = commonDAO.insertItem(recommendation, NAME_SPACE, "CustomRecommendationMapper");
        }

        if (r1 == 1) {
            ec.setResult(ExecutionContext.SUCCESS);
            ParamForApplicationRecommendation param = new ParamForApplicationRecommendation();
            param.setApplNo(applNo);
            param.setRecSeq(recSeq);
            Recommendation result = commonDAO.queryForObject(NAME_SPACE + "CustomRecommendationMapper.selectByApplNoRecSeq",
                    param, Recommendation.class);
            ec.setData(result);
            ec.setMessage(MessageResolver.getMessage("U06505")); // 추천서 요청을 임시 저장했습니다.
        } else {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(MessageResolver.getMessage("U06506")); // 추천서 요청 임시 저장에 실패했습니다.

            String errCode = null;
            errCode = isUpdate ? "ERR0083" : "ERR0081";
            ec.setErrCode(errCode);

            Map<String, String> errorInfo = new HashMap<String, String>();
            errorInfo.put("applNo", String.valueOf(applNo));
            ec.setErrorInfo(new ErrorInfo(errorInfo));

            throw new YSBizException(ec);
        }

        return ec;
    }

    @Override
    public ExecutionContext deleteRecommendationRequest(Recommendation recommendation) {
        return null;
    }

    @Override
    public ExecutionContext registerRecommendationByProfessor(Recommendation recommendation) {
        return null;
    }


}
