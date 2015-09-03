package com.apexsoft.ysprj.applicants.application.service;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.exception.ErrorInfo;
import com.apexsoft.framework.exception.YSBizException;
import com.apexsoft.framework.mail.Mail;
import com.apexsoft.framework.mail.MailType;
import com.apexsoft.framework.mail.SESMailService;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.ysprj.applicants.application.domain.Application;
import com.apexsoft.ysprj.applicants.application.domain.ParamForApplicationRecommendation;
import com.apexsoft.ysprj.applicants.application.domain.Recommendation;
import com.apexsoft.ysprj.applicants.application.domain.RecommendationApplicationInfo;
import com.apexsoft.ysprj.applicants.common.domain.MailContentsParamKey;
import com.apexsoft.ysprj.applicants.common.util.MailFactory;
import com.apexsoft.ysprj.applicants.common.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        boolean isUpdate = recSeq > 0 || recNo > 0;

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
    public ExecutionContext retrieveRecommendationByProfessor(Recommendation param) {
        ExecutionContext ec = new ExecutionContext();

        RecommendationApplicationInfo recApplInfo =
                commonDAO.queryForObject(NAME_SPACE + "CustomRecommendationMapper.selectRecommendationByApplNoKey",
                        param, RecommendationApplicationInfo.class);
        ec.setData(recApplInfo);

//        if (recApplInfo.equals(RecommendStatus.SENT.codeVal())) { // 상태가 Sent이면
//            // EC에 Success 담아 리턴
//            ec.setData(recApplInfo);
//        } else {
//            ec.setResult(ExecutionContext.FAIL);
//        }
        ec.setData(recApplInfo);

        return ec;
    }

    @Override
    public ExecutionContext registerRecommendationByProfessor(Recommendation recommendation) {
        ExecutionContext ec = new ExecutionContext();
        int recNo = recommendation.getRecNo();
        recommendation.setRecStsCode("00003");  // 추천서 접수 완료

        int r1 = commonDAO.updateItem(recommendation, NAME_SPACE, "CustomRecommendationMapper", ".updateSelective");
        Recommendation result = commonDAO.queryForObject(NAME_SPACE + "CustomRecommendationMapper.selectByRecNo", recNo, Recommendation.class);
        result.setNotifyApplicantYn("Y");
        if (r1 == 1) {
            ec.setResult(ExecutionContext.SUCCESS);
            if ("Y".equals(result.getNotifyApplicantYn())) {
                if (sendCompletedMail(result)) {
                    ec.setMessage(MessageResolver.getMessage("U06737")); // 추천서 등록을 완료하고 지원자에게 알림 메일을 보냈습니다.
                } else {
                    ec.setResult(ExecutionContext.FAIL);
                    ec.setMessage(MessageResolver.getMessage("U06736")); // 지원자에게 추천서 등록 완료 알림 메일을 보내는데 실패했습니다.
                    ec.setErrCode("ERR0105");
                    Map<String, String> errorInfo = new HashMap<String, String>();
                    errorInfo.put("recNo", String.valueOf(recNo));
                    ec.setErrorInfo(new ErrorInfo(errorInfo));

                    throw new YSBizException(ec);
                }
            } else {
                ec.setMessage(MessageResolver.getMessage("U06734")); // 추천서 등록이 완료되었습니다.
            }

        } else {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(MessageResolver.getMessage("U06735")); // 추천서 등록에 실패했습니다.
            ec.setErrCode("ERR0083");
            Map<String, String> errorInfo = new HashMap<String, String>();
            errorInfo.put("recNo", String.valueOf(recNo));
            ec.setErrorInfo(new ErrorInfo(errorInfo));

            throw new YSBizException(ec);
        }

        return ec;
    }

    /**
     * 추천서 요청 저장
     *
     * @param recommendation
     * @param recStsCode      추천서 요청 상태 코드
     * @return
     */
    private int saveRecommendation(Recommendation recommendation, String recStsCode) {
        int recNo = recommendation.getRecNo();
        int applNo = recommendation.getApplNo();
        int recSeq = recommendation.getRecSeq();
        int r1 = 0;
        boolean isUpdate = recSeq > 0 || recNo > 0;
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
//        ParamForApplication param = new ParamForApplication();
//        param.setApplNo(recommendation.getApplNo());

        int recNo = recommendation.getApplNo();
        RecommendationApplicationInfo recApplInfo = commonDAO.queryForObject(NAME_SPACE + "CustomRecommendationMapper.selectRecommendApplInfo",
                recNo, RecommendationApplicationInfo.class);
        recommendation.setApplicantName(recApplInfo.getEngName());
        recommendation.setApplicantNationality(recApplInfo.getNationality());
        recommendation.setDegree(recApplInfo.getDegree());
        recommendation.setMajor(recApplInfo.getDept());
        return recommendation;
    }

    /**
     * 추천서 등록 화면 링크 암호화 해쉬 문자열을 생성하고 메일 발송
     * @param recommendation
     * @return
     */
    private boolean sendRequestMail(Recommendation recommendation) {
        boolean isSent = false;
        Mail mail = mailFactory.create(MailType.RECOMMENDATION_REQUEST);
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

    /**
     * 추천서 등록 후 교수가 지원자에게 확인 메일 발송
     *
     * @param recommendation
     * @return
     */
    private boolean sendCompletedMail(Recommendation recommendation) {
        int recNo = recommendation.getRecNo();
        boolean isSent = false;
        Mail mail = mailFactory.create(MailType.RECOMMENDATION_COMPLETED);
        mail.setInfo(recommendation);
        mail.setInfoType(Recommendation.class);
        // TODO - DB 조회해서 지원자 메일주소 세팅
        Application application =
                commonDAO.queryForObject(NAME_SPACE + "CustomRecommendationMapper.selectApplicantMailByRecNo",
                        recNo, Application.class);
        String applicantName = StringUtil.getEmptyIfNull(application.getKorName()).length() > 0 ?
                application.getKorName() :
                application.getEngName();
        mail.setTo(new String[]{application.getMailAddr()});
        mail.setSubject(MessageResolver.getMessage("MAIL_COMPLETED_RECOMMENDATION_SUBJECT"));
        Map<Object, String> contentsParam = mail.getContentsParam();
        contentsParam.put(MailContentsParamKey.USER_NAME, applicantName);
        contentsParam.put(MailContentsParamKey.PROF_NAME, recommendation.getProfName());
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
