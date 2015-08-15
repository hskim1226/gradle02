package com.apexsoft.ysprj.applicants.application.service;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.exception.ErrorInfo;
import com.apexsoft.framework.exception.YSBizException;
import com.apexsoft.framework.mail.Mail;
import com.apexsoft.framework.mail.MailType;
import com.apexsoft.framework.mail.SESMailService;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.ysprj.applicants.application.domain.*;
import com.apexsoft.ysprj.applicants.common.util.MailFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by hanmomhanda on 15. 1. 13.
 */
@Service
public class RecommendationServiceImpl implements RecommendationService {

    private final static String NAME_SPACE = "com.apexsoft.ysprj.applicants.application.sqlmap.";

    @Autowired
    private CommonDAO commonDAO;

    @Autowired
    private MailFactory mailFactory;

    @Autowired
    private SESMailService sesMailService;

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
        boolean isUpdate = recSeq > 0;

        int r1 = saveRecommendation(recommendation, "00001");

        if (r1 == 1) {
            ec.setResult(ExecutionContext.SUCCESS);
            ParamForApplicationRecommendation param = new ParamForApplicationRecommendation();
            param.setApplNo(applNo);
            param.setRecSeq(recommendation.getRecSeq());
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
        ExecutionContext ec = new ExecutionContext();
        int applNo = recommendation.getApplNo();
        int recNo = recommendation.getRecNo();
        int r1 = 0;

        r1 = commonDAO.delete(NAME_SPACE + "CustomRecommendationMapper.deleteByPrimaryKey", recommendation);

        if (r1 == 1) {
            ec.setResult(ExecutionContext.SUCCESS);
            ec.setMessage(MessageResolver.getMessage("U06507")); // 추천서 요청을 취소했습니다.
        } else {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(MessageResolver.getMessage("U06508")); // 추천서 요청 취소에 실패했습니다.
            ec.setErrCode("ERR0084");
            Map<String, String> errorInfo = new HashMap<String, String>();
            errorInfo.put("applNo", String.valueOf(applNo));
            errorInfo.put("recNo", String.valueOf(recNo));
            ec.setErrorInfo(new ErrorInfo(errorInfo));

            throw new YSBizException(ec);
        }
        return ec;
    }

    @Override
    public ExecutionContext sendRecommendationRequest(Recommendation recommendation) {
        ExecutionContext ec = new ExecutionContext();
        int recNo = recommendation.getRecNo();
        int applNo = recommendation.getApplNo();
        int recSeq = recommendation.getRecSeq();
        boolean isUpdate = recSeq > 0;

        fillEtcInfo(recommendation);

        int r1 = saveRecommendation(recommendation, "00002"); // 요청 완료

        if (r1 == 1) {
            ec.setResult(ExecutionContext.SUCCESS);
            if (sendRequestMail(recommendation)) {
                ec.setMessage(MessageResolver.getMessage("U06509")); // 교수님께 추천서 요청을 발송했습니다.
            } else {
                ec.setResult(ExecutionContext.FAIL);
                ec.setMessage(MessageResolver.getMessage("U06510")); // 추천서 요청 메일 발송에 실패했습니다.
                ec.setErrCode("ERR0085");
                Map<String, String> errorInfo = new HashMap<String, String>();
                errorInfo.put("recNo", String.valueOf(recNo));
                errorInfo.put("applNo", String.valueOf(applNo));
                errorInfo.put("recSeq", String.valueOf(recSeq));
                ec.setErrorInfo(new ErrorInfo(errorInfo));

                throw new YSBizException(ec);
            }
        } else {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(MessageResolver.getMessage("U06506")); // 추천서 요청 임시 저장에 실패했습니다.
            String errCode = null;
            errCode = isUpdate ? "ERR0083" : "ERR0081";
            ec.setErrCode(errCode);

            Map<String, String> errorInfo = new HashMap<String, String>();
            errorInfo.put("recNo", String.valueOf(recNo));
            errorInfo.put("applNo", String.valueOf(applNo));
            errorInfo.put("recSeq", String.valueOf(recSeq));
            ec.setErrorInfo(new ErrorInfo(errorInfo));

            throw new YSBizException(ec);
        }

        return ec;
    }

    @Override
    public ExecutionContext registerRecommendationByProfessor(Recommendation recommendation) {
        return null;
    }

    private int saveRecommendation(Recommendation recommendation, String recStsCode) {
        int applNo = recommendation.getApplNo();
        int recSeq = recommendation.getRecSeq();
        int r1 = 0;
        boolean isUpdate = recSeq > 0;
        recommendation.setRecStsCode(recStsCode);
        if (isUpdate) {
            r1 = commonDAO.updateItem(recommendation, NAME_SPACE, "CustomRecommendationMapper", ".updateSelective");
        } else {
            recSeq = commonDAO.queryForInt(NAME_SPACE + "CustomRecommendationMapper.selectMaxRecSeqByApplNo", applNo);
            recommendation.setRecSeq(++recSeq);
            r1 = commonDAO.insertItem(recommendation, NAME_SPACE, "CustomRecommendationMapper");
        }
        return r1;
    }

    @Override
    public Recommendation fillEtcInfo(Recommendation recommendation) {
        ParamForApplication param = new ParamForApplication();
        param.setApplNo(recommendation.getApplNo());
        RecommendationApplicationInfo recApplInfo = commonDAO.queryForObject(NAME_SPACE + "CustomRecommendationMapper.selectRecommendApplInfo",
                param, RecommendationApplicationInfo.class);
        recommendation.setApplicantName(recApplInfo.getEngName());
        recommendation.setApplicantNationality(recApplInfo.getNationality());
        recommendation.setDegree(recApplInfo.getDegree());
        recommendation.setMajor(recApplInfo.getDept());
        return recommendation;
    }

    private boolean sendRequestMail(Recommendation recommendation) {
        boolean isSent = false;
        Mail mail = mailFactory.create(MailType.REQUEST_RECOMMENDATION);
        mail.setInfo(recommendation);
        mail.setInfoType(Recommendation.class);
        mail.setTo(new String[]{recommendation.getProfMailAddr()});
        mail.setSubject(recommendation.getReqSubject());
        mail.makeContents();
        try {
            sesMailService.sendMail(mail);
            isSent = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isSent;
    }
}
