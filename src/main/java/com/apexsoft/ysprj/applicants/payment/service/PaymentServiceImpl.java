package com.apexsoft.ysprj.applicants.payment.service;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.exception.ErrorInfo;
import com.apexsoft.framework.exception.YSBizException;
import com.apexsoft.framework.mail.Mail;
import com.apexsoft.framework.mail.MailType;
import com.apexsoft.framework.mail.SESMailService;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.ysprj.applicants.application.domain.Application;
import com.apexsoft.ysprj.applicants.application.domain.ApplicationStatus;
import com.apexsoft.ysprj.applicants.application.domain.CustomNewSeq;
import com.apexsoft.ysprj.applicants.application.service.DocumentService;
import com.apexsoft.ysprj.applicants.common.domain.Department;
import com.apexsoft.ysprj.applicants.common.domain.MailContentsParamKey;
import com.apexsoft.ysprj.applicants.common.domain.MailInfo;
import com.apexsoft.ysprj.applicants.common.service.BirtService;
import com.apexsoft.ysprj.applicants.common.service.PDFService;
import com.apexsoft.ysprj.applicants.common.util.MailFactory;
import com.apexsoft.ysprj.applicants.payment.domain.*;
import lgdacom.XPayClient.XPayClient;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by cosb071 on 15. 1. 22.
 *
 * 결제 처리 서비스
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    private final static String NAME_SPACE = "com.apexsoft.ysprj.applicants.payment.sqlmap.";

    private static final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);

    @Autowired
    private CommonDAO commonDAO;

    @Autowired
    private DocumentService documentService;

    @Autowired
    MailFactory mailFactory;

    @Autowired
    SESMailService sesMailService;

    @Autowired
    BirtService birtService;

    @Autowired
    PDFService pdfService;

    @Value("#{app['file.baseDir']}")
    private String BASE_DIR;

    @Value("#{app['pay.lgdacom']}")
    private String configPath;

    private final String RSLT = "00000";      // 성공/에러일 때 반환값

    @Override
    public ExecutionContext saveApplicationPayment(Application application) {

        ExecutionContext ec = new ExecutionContext();
        int rInsert = 0, rUpdate = 0;

        int applNo = application.getApplNo();

        ApplicationPaymentCurStat applPayCS = commonDAO.queryForObject(NAME_SPACE + "CustomApplicationPaymentCurStatMapper.selectPayInfoByApplNo",
                                                                       applNo, ApplicationPaymentCurStat.class);
        String payStsCode = applPayCS.getPayStsCode();
        if( payStsCode == null || payStsCode.equals("") ) {

            applPayCS.setPayStsCode("00001");
            applPayCS.setCreId(application.getUserId());
            applPayCS.setCreDate(new Date());
            rInsert = commonDAO.insertItem(applPayCS, NAME_SPACE, "ApplicationPaymentCurStatMapper");

        } else {

            applPayCS.setModId(application.getUserId());
            applPayCS.setModDate(new Date());
            rUpdate = commonDAO.updateItem(applPayCS, NAME_SPACE, "ApplicationPaymentCurStatMapper");
        }

        if (rInsert == 1 || rUpdate == 1) {
            ec.setResult(ExecutionContext.SUCCESS);
            ec.setMessage(MessageResolver.getMessage("U335"));
        } else {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(MessageResolver.getMessage("U336"));
            String errCode = null;
            if ( rInsert != 1 ) errCode = "ERR0101";
            else if ( rUpdate != 1 ) errCode = "ERR0103";
            ec.setErrCode(errCode);
            throw new YSBizException(ec);
        }

        return ec;
    }

/*
    @Override
    public ExecutionContext saveApplicationPayment2(Application application) {

        ExecutionContext ec = new ExecutionContext();

        int applNo = application.getApplNo();

        CustomPayInfo customPayInfo = commonDAO.queryForObject(NAME_SPACE + "CustomApplicationPaymentMapper.selectPayInfoByApplNo",
                applNo, CustomPayInfo.class);
        int paySeq = customPayInfo.getPaySeq();
        int admsFee = customPayInfo.getAdmsFee();
        int rInsert = 0, rUpdate = 0;
        ApplicationPayment ap = new ApplicationPayment();

        if (paySeq == 0) {
            int seqFromDB = commonDAO.queryForInt(NAME_SPACE + "CustomApplicationPaymentMapper.getSeq", applNo);
            ap.setApplNo(applNo);
            ap.setPaySeq(seqFromDB + 1);
            ap.setExpPayAmt(admsFee);
            ap.setPayStsCode("00001");
            ap.setCreId(application.getUserId());
            ap.setCreDate(new Date());
            rInsert = commonDAO.insertItem(ap, NAME_SPACE, "ApplicationPaymentMapper");
        } else {
            ap.setApplNo(applNo);
            ap.setPaySeq(paySeq);
            ap.setExpPayAmt(admsFee);
            ap.setModId(application.getUserId());
            ap.setModDate(new Date());
            rUpdate = commonDAO.updateItem(ap, NAME_SPACE, "ApplicationPaymentMapper");
        }

        if (rInsert == 1 || rUpdate == 1) {
            ec.setResult(ExecutionContext.SUCCESS);
            ec.setMessage(MessageResolver.getMessage("U335"));
        } else {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(MessageResolver.getMessage("U336"));
            String errCode = null;
            if ( rInsert != 1 ) errCode = "ERR0101";
            else if ( rUpdate != 1 ) errCode = "ERR0103";
            ec.setErrCode(errCode);
            throw new YSBizException(ec);
        }

        return ec;
    }
*/

    @Override
    public ExecutionContext retrieveConfirmInfo( Payment payment ) {

        ExecutionContext ec = new ExecutionContext();

        Payment newPayInfo = commonDAO.queryForObject(NAME_SPACE + "CustomApplicationPaymentMapper.selectConfirmInfo",
                payment.getApplNo(), Payment.class);

        payment.setApplStsCode(newPayInfo.getApplStsCode());
        payment.setLGD_AMOUNT(newPayInfo.getLGD_AMOUNT());
        payment.setLGD_FINANCENAME(newPayInfo.getLGD_FINANCENAME());
        payment.setLGD_ACCOUNTNUM(newPayInfo.getLGD_ACCOUNTNUM());
        payment.setAdmsSts(newPayInfo.getAdmsSts());
        payment.setKorName(newPayInfo.getKorName());
        payment.setEngName(newPayInfo.getEngName());
        payment.setEngSur(newPayInfo.getEngSur());

        return ec;
    }

    @Override
    public ExecutionContext registerPaymentCertifyLog( Payment payment ) {

        ExecutionContext ec = new ExecutionContext();
        int r1 = 0, r2 = 0;
        Date date = new Date();

        XPayCertRequest xPayCertRequest = setXPayCertRequest(payment);
        xPayCertRequest.setCreDate(date);
        r1 = commonDAO.insertItem(xPayCertRequest, NAME_SPACE, "XPayCertRequestMapper");

        XPayCertResult xPayCertResult = setXPayCertResult(payment);
        xPayCertResult.setCreDate(date);
        r2 = commonDAO.insertItem(xPayCertResult, NAME_SPACE, "XPayCertResultMapper");

        if( r1>0 && r2>0 ) {
            ec.setResult(ExecutionContext.SUCCESS);
        } else {
            ec.setResult(ExecutionContext.FAIL);
        }

        return ec;
    }

    @Override
    public ExecutionContext<PaymentResult> executePayment( Payment payment, TransactionVO transactionVO ) {

        String rtnStr;

        // added by gradnet
        ExecutionContext<PaymentResult> ec = new ExecutionContext<>();
        PaymentResult paymentResult = new PaymentResult();

        /*
         * [최종결제요청 페이지(STEP2-2)]
         *
         * LG유플러스으로 부터 내려받은 LGD_PAYKEY(인증Key)를 가지고 최종 결제요청.(파라미터 전달시 POST를 사용하세요)
         */

        //String configPath = PaymentConfig.CONFIG_PATH;  //LG유플러스에서 제공한 환경파일("/conf/lgdacom.conf,/conf/mall.conf") 위치 지정.

        /*
         *************************************************
         * 1.최종결제 요청 - BEGIN
         *  (단, 최종 금액체크를 원하시는 경우 금액체크 부분 주석을 제거 하시면 됩니다.)
         *************************************************
         */

        String CST_PLATFORM = payment.getCST_PLATFORM();
        String LGD_MID = payment.getLGD_MID();
        String LGD_PAYKEY = payment.getLGD_PAYKEY();

        //해당 API를 사용하기 위해 WEB-INF/lib/XPayClient.jar 를 Classpath 로 등록하셔야 합니다.
        XPayClient xpay = new XPayClient();
        boolean isInitOK = xpay.Init(configPath, CST_PLATFORM);

        if( !isInitOK ) {
            //API 초기화 실패 화면 처리
            transactionVO.setSysMsg(MessageResolver.getMessage("A000"));
            transactionVO.setUserMsg(MessageResolver.getMessage("U000"));

            // added by gradnet
            paymentResult.setPayType("InitFail");

            // removed by gradnet
//            return "InitFail";
        } else {
            try {

                xpay.Init_TX(LGD_MID);
                xpay.Set("LGD_TXNAME", PaymentConfig.LGD_TXNAME);
                xpay.Set("LGD_PAYKEY", LGD_PAYKEY);

                //결제 요청 로그 DB 처리
                registerPaymentRequestLog(payment);

            } catch(Exception e) {
                transactionVO.setSysMsg(MessageResolver.getMessage("A001") + e.getMessage());
                transactionVO.setUserMsg(MessageResolver.getMessage("U001"));

                // added by gradnet
                paymentResult.setPayType("InitFail");

                // removed by gradnet
//                return "InitFail";
            }
        }

        /*
         * 2. 최종결제 요청 결과처리
         * 최종 결제요청 결과 리턴 파라미터는 연동메뉴얼을 참고하시기 바랍니다.
         */
        if( xpay.TX() ) {

            //1)결제결과 처리(성공,실패 결과 처리를 하시기 바랍니다.)
            transactionVO.setSysMsg("결제요청이 완료되었습니다.  <br>" +
                            "TX 결제요청 Response_code = " + xpay.m_szResCode + "<br>" +
                            "TX 결제요청 Response_msg = " + xpay.m_szResMsg + "<p>" +
                            "거래번호 : " + xpay.Response("LGD_TID", 0) + "<br>" +
                            "상점아이디 : " + xpay.Response("LGD_MID", 0) + "<br>" +
                            "상점주문번호 : " + xpay.Response("LGD_OID", 0) + "<br>" +
                            "결제금액 : " + xpay.Response("LGD_AMOUNT", 0) + "<br>" +
                            "결과코드 : " + xpay.Response("LGD_RESPCODE", 0) + "<br>" +
                            "결과메세지 : " + xpay.Response("LGD_RESPMSG", 0) + "<p>"
            );

            //결제 결과 로그 DB 처리
            registerPaymentResultLog(xpay);

            String tmp = "";
            for( int i=0; i<xpay.ResponseNameCount(); i++ ) {
                for ( int j=0; j<xpay.ResponseCount(); j++ ) {
                    tmp += (j>1) ? "|" : "" + xpay.Response(xpay.ResponseName(i), j);
                }
                transactionVO.getTxMap().put(xpay.ResponseName(i), tmp);
                tmp = "";
            }

            if( "0000".equals( xpay.m_szResCode ) ) {

                //결제 성공에 따른 application 정보 처리
                String payType = xpay.Response("LGD_PAYTYPE", 0);
                paymentResult.setPayType(payType);

                //결제 정보 처리 (APPL_PAY_CS)
                ApplicationPaymentCurStat applPay = new ApplicationPaymentCurStat();
                applPay.setApplNo(payment.getApplNo());
                applPay.setPayTypeCode(xpay.Response("LGD_PAYTYPE", 0));
                String payAmt = xpay.Response("LGD_AMOUNT", 0);
                if( payAmt != null && !payAmt.equals("") )
                    applPay.setPayAmt(Integer.valueOf(payAmt));
                applPay.setPayDate(xpay.Response("LGD_PAYDATE", 0));
                applPay.setLgdOid(xpay.Response("LGD_OID", 0));
                applPay.setLgdTid(xpay.Response("LGD_TID", 0));
                paymentResult.setPaymentCurStat(applPay);

                transactionVO.setApplNo(payment.getApplNo());
                if( "SC0010".equals(payType) || "SC0030".equals(payType) ) {

                    //카드 또는 계좌이체에 대한 DB 처리
//                    registerPaymentSuccess(payment, xpay);

                    //결제 성공에 대한 화면 처리
                    transactionVO.setSysMsg(transactionVO.getSysMsg() + "최종결제요청 결과 성공 DB처리하시기 바랍니다.<br>");
                    transactionVO.setUserMsg(MessageResolver.getMessage("U002"));

                } else if( "SC0040".equals(payType) ) {

                    //가상계좌 입금대기에 대한 DB 처리
                    registerPaymentWait(payment, xpay);

                    payment.setLGD_FINANCENAME( xpay.Response("LGD_FINANCENAME", 0) );
                    payment.setLGD_ACCOUNTNUM( xpay.Response("LGD_ACCOUNTNUM", 0) );

                    //결제 성공에 대한 화면 처리
                    String msg = MessageResolver.getMessage("U003");
//                    msg = msg + "<br><br> 가상계좌정보";
//                    msg = msg + "<br> 은행 : " + xpay.Response("LGD_FINANCENAME", 0);
//                    msg = msg + "<br> 계좌 : " + xpay.Response("LGD_ACCOUNTNUM", 0);
                    transactionVO.setSysMsg(transactionVO.getSysMsg() + "최종결제요청 결과 성공 DB처리하시기 바랍니다.<br>");
                    transactionVO.setUserMsg(msg);

                } else {
                    //TODO 예외 처리
                }

                rtnStr = payType;
                paymentResult.setPayType(rtnStr);

            } else {

                //결제 실패에 대한 화면 처리
                transactionVO.setSysMsg(transactionVO.getSysMsg() + "최종결제요청 결과 실패 DB처리하시기 바랍니다.<br>");
                transactionVO.setUserMsg(MessageResolver.getMessage("U05203"));
                rtnStr = "PayFail";
                paymentResult.setPayType(rtnStr);
                //TODO 실패 코드 필요
            }

        } else {
            //2)API 요청실패 처리
            transactionVO.setSysMsg("결제요청이 실패하였습니다.  <br>" +
                            "TX 결제요청 Response_code = " + xpay.m_szResCode + "<br>" +
                            "TX 결제요청 Response_msg = " + xpay.m_szResMsg + "<p>"
            );
            transactionVO.setSysMsg(transactionVO.getSysMsg() + "최종결제요청 결과 실패 DB처리하시기 바랍니다.<br>");
            rtnStr = "ReqFail";
            paymentResult.setPayType(rtnStr);
        }

        ec.setData(paymentResult);
        return ec;
    }

    @Override
    public void updateStatus(Payment payment, PaymentResult paymentResult) {
        Application application = commonDAO.queryForObject("com.apexsoft.ysprj.applicants.application.sqlmap.ApplicationMapper.selectByPrimaryKey",
                payment.getApplNo(), Application.class);

        //수헙번호 순번 조회
        CustomNewSeq customNewSeq = updateApplId(payment);

        //수험번호, 결제완료 상태 적용
        updateApplSts(application, customNewSeq);

        //결제 정보 처리 (APPL_PAY_CS)
        ApplicationPaymentCurStat applPay = updatePayCs(application, paymentResult);

        //결제 트랜젝션 정보 처리 (APPL_PAY_TR)
        registerPaymentTransaction(applPay);

        //APPL_DOC에 수험번호가 채번된 원서, 수험표 정보 저장
        documentService.saveApplicationPaperInfo(application);
        documentService.saveAdmissionSlipPaperInfo(application);
    }

    private CustomNewSeq updateApplId(Payment payment) {
        //수헙번호 순번 조회
        CustomNewSeq customNewSeq = commonDAO.queryForObject("com.apexsoft.ysprj.applicants.application.sqlmap.CustomApplicationMapper.selectNewSeq",
                payment.getApplNo(), CustomNewSeq.class);
        //수험번호 순번 갱신
        int r1 = commonDAO.updateItem(customNewSeq, "com.apexsoft.ysprj.applicants.application.sqlmap.", "CustomApplicationMapper.", "updateApplIdSeqIdByNewSeq");

        if (r1 != 1) {
            exceptionThrower("U05204", "ERR0303", payment.getApplNo());
        }
        return customNewSeq;
    }

    private void updateApplSts(Application application, CustomNewSeq customNewSeq) {
        application.setApplId(getApplId(application, customNewSeq.getNewSeq()));
        application.setApplDate(new Date());
        application.setApplStsCode(ApplicationStatus.COMPLETED.codeVal());
        int r2 = commonDAO.updateItem(application, "com.apexsoft.ysprj.applicants.application.sqlmap.", "ApplicationMapper");
        if (r2 != 1) {
            exceptionThrower("U05204", "ERR0304", application.getApplNo());
        }
    }

    private ApplicationPaymentCurStat updatePayCs(Application application, PaymentResult paymentResult) {
        ApplicationPaymentCurStat applPay = paymentResult.getPaymentCurStat();
        applPay.setPayStsCode("00002");
        applPay.setModId(application.getUserId());
        applPay.setModDate(new Date());
        int r3 = commonDAO.updateItem(applPay, NAME_SPACE, "ApplicationPaymentCurStatMapper");
        if (r3 != 1) {
            exceptionThrower("U05204", "ERR0305", application.getApplNo());
        }
        return applPay;
    }


    private void registerPaymentTransaction(ApplicationPaymentCurStat applPayCS) {
        int r1 = 0;

        ApplicationPaymentTransaction applPayTr = new ApplicationPaymentTransaction();
        applPayTr.setApplNo(applPayCS.getApplNo());
        applPayTr.setPayTypeCode(applPayCS.getPayTypeCode());
        applPayTr.setPayAmt(applPayCS.getPayAmt());
        applPayTr.setPayDate(applPayCS.getPayDate());
        applPayTr.setPayStsCode(applPayCS.getPayStsCode());
        applPayTr.setCreId(applPayCS.getModId());
        applPayTr.setCreDate(new Date());

        int lastSeq = 0;
        lastSeq = commonDAO.queryForInt(NAME_SPACE + "CustomApplicationPaymentTransactionMapper.selectMaxSeqByApplNo", applPayCS.getApplNo());
        applPayTr.setPaySeq(lastSeq+1);

        r1 = commonDAO.insertItem(applPayTr, NAME_SPACE, "ApplicationPaymentTransactionMapper");

        if( r1 == 0 ) {
            exceptionThrower("U05204", "ERR0306", applPayCS.getApplNo());
        }
    }

    private void exceptionThrower(String userMsgCode, String errCode, int applNo) {
        ExecutionContext ec = new ExecutionContext(ExecutionContext.FAIL);
        ec.setMessage(MessageResolver.getMessage(userMsgCode));
        ec.setErrCode(errCode);
        Map<String, String> errorInfo = new HashMap<String, String>();
        errorInfo.put("applNo", String.valueOf(applNo));
        ec.setErrorInfo(new ErrorInfo(errorInfo));
        throw new YSBizException(ec);
    }

    @Override
    // 원서 수험표, 생성, S3 업로드
    public void processFiles(Application application) {
        // 예외 발생 시 throw 하지 않고 정상 흐름으로 복귀시켜야 함
        genAndUploadApplicationFormAndSlipFile(application);
    }

    @Override
    // 결제 완료 메일 발송
    public void sendNotification(Application application) {
        // 예외 발생 시 throw 하지 않고 정상 흐름으로 복귀시켜야 함
        sendMail(application);
    }

//    private ExecutionContext registerPaymentSuccess(Payment payment, XPayClient xpay ) {
//
//        ExecutionContext ec = new ExecutionContext();
//        int r1 = 0, r2 = 0, r3 = 0;
//
//        //수헙번호 순번 조회
//        CustomNewSeq customNewSeq = commonDAO.queryForObject("com.apexsoft.ysprj.applicants.application.sqlmap.CustomApplicationMapper.selectNewSeq",
//                payment.getApplNo(), CustomNewSeq.class);
//        //수험번호 순번 갱신
//        r1 = commonDAO.updateItem(customNewSeq, "com.apexsoft.ysprj.applicants.application.sqlmap.", "CustomApplicationMapper.", "updateApplIdSeqIdByNewSeq");
//
//        //수험번호, 결제완료 상태 적용
//        Application application = commonDAO.queryForObject("com.apexsoft.ysprj.applicants.application.sqlmap.ApplicationMapper.selectByPrimaryKey",
//                payment.getApplNo(), Application.class);
//        application.setApplId(getApplId(application, customNewSeq.getNewSeq()));
//        application.setApplDate(new Date());
//        application.setApplStsCode(ApplicationStatus.COMPLETED.codeVal());
//        r2 = commonDAO.updateItem(application, "com.apexsoft.ysprj.applicants.application.sqlmap.", "ApplicationMapper");
//
//        //결제 정보 처리 (APPL_PAY_CS)
//        ApplicationPaymentCurStat applPay = new ApplicationPaymentCurStat();
//        applPay.setApplNo(payment.getApplNo());
//        applPay.setPayTypeCode(xpay.Response("LGD_PAYTYPE", 0));
//        String payAmt = xpay.Response("LGD_AMOUNT", 0);
//        if( payAmt != null && !payAmt.equals("") )
//            applPay.setPayAmt(Integer.valueOf(payAmt));
//        applPay.setPayDate(xpay.Response("LGD_PAYDATE", 0));
//        applPay.setLgdOid(xpay.Response("LGD_OID", 0));
//        applPay.setLgdTid(xpay.Response("LGD_TID", 0));
//        applPay.setPayStsCode("00002");
//        applPay.setModId(application.getUserId());
//        applPay.setModDate(new Date());
//        r3 = commonDAO.updateItem(applPay, NAME_SPACE, "ApplicationPaymentCurStatMapper");
//
//        //결제 트랜젝션 정보 처리 (APPL_PAY_TR)
//        registerPaymentTransaction(applPay);
//
//        //APPL_DOC에 수험번호가 채번된 원서, 수험표 정보 저장
//        documentService.saveApplicationPaperInfo(application);
//        documentService.saveAdmissionSlipPaperInfo(application);
//
//
//
//        // 원서 수험표, 생성, S3 업로드
//        genAndUploadApplicationFormAndSlipFile(application);
//
////        // 원서, 수험표 S3로 업로드
////        if (ExecutionContext.SUCCESS.equals(ecGen.getResult())) {
////            uploadApplicationFormAndSlipFileToS3(application);
////        } else {
////            // throw YSBizException으로 적당한 예외 정보 전송
////        }
//
//        /*
//        int paySeq = commonDAO.queryForInt(NAME_SPACE+"CustomApplicationPaymentMapper.getSeq", payment.getApplNo());
//
//        ApplicationPayment applPay = new ApplicationPayment();
//        applPay.setApplNo(payment.getApplNo());
//        applPay.setPaySeq(paySeq);
//        applPay.setPayTypeCode(xpay.Response("LGD_PAYTYPE", 0));
//        String payAmt = xpay.Response("LGD_AMOUNT", 0);
//        if( payAmt != null && !payAmt.equals("") )
//            applPay.setPayAmt(Integer.valueOf(payAmt));
//        applPay.setPayDate(xpay.Response("LGD_PAYDATE", 0));
//        applPay.setLgdOid(xpay.Response("LGD_OID", 0));
//        applPay.setLgdTid(xpay.Response("LGD_TID", 0));
//        applPay.setPayStsCode("00002");
//        r3 = commonDAO.updateItem(applPay, NAME_SPACE, "ApplicationPaymentMapper");
//        */
//
//        if( r1>0 && r2>0 && r3>0 ) {
//            ec.setResult(ExecutionContext.SUCCESS);
//            // 결제 완료 메일 발송
//            sendMail(application);
//        } else {
//            ec.setResult(ExecutionContext.FAIL);
//        }
//
//
//
//        return ec;
//    }


    @Override
    public int registerCasNote( ApplicationPaymentCurStat applPay ) {

        //LGD_OID로 해당 결제 조회
        ApplicationPaymentCurStatExample param = new ApplicationPaymentCurStatExample();
        param.createCriteria().andLgdOidEqualTo(applPay.getLgdOid());

        ApplicationPaymentCurStat orgApplPay = commonDAO.queryForObject(NAME_SPACE+"ApplicationPaymentCurStatMapper.selectByExample", param, ApplicationPaymentCurStat.class);
        applPay.setApplNo(orgApplPay.getApplNo());
        applPay.setPayStsCode("00002");
        applPay.setModId("cas_note");
        applPay.setModDate(new Date());
        commonDAO.updateItem(applPay, NAME_SPACE, "ApplicationPaymentCurStatMapper");

        //수헙번호 순번 조회
        CustomNewSeq customNewSeq = commonDAO.queryForObject("com.apexsoft.ysprj.applicants.application.sqlmap.CustomApplicationMapper.selectNewSeq",
                                                             applPay.getApplNo(), CustomNewSeq.class);
        //수험번호 순번 갱신
        commonDAO.updateItem(customNewSeq, "com.apexsoft.ysprj.applicants.application.sqlmap.", "CustomApplicationMapper.", "updateApplIdSeqIdByNewSeq");

        //수험번호, 결제완료 상태 적용
        Application application = commonDAO.queryForObject("com.apexsoft.ysprj.applicants.application.sqlmap.ApplicationMapper.selectByPrimaryKey",
                                                           applPay.getApplNo(), Application.class);
        application.setApplId(getApplId(application, customNewSeq.getNewSeq()));
        Date date = new Date();
        application.setApplDate(date);
        application.setApplStsCode(ApplicationStatus.COMPLETED.codeVal());
        commonDAO.updateItem(application, "com.apexsoft.ysprj.applicants.application.sqlmap.", "ApplicationMapper");

        //결제 트랜젝션 정보 처리 (APPL_PAY_TR)
        registerPaymentTransaction(applPay);

        //APPL_DOC에 수험번호가 채번된 원서, 수험표 정보 저장
        documentService.saveApplicationPaperInfo(application);
        documentService.saveAdmissionSlipPaperInfo(application);

        // 원서 수험표, 생성, S3 업로드
        genAndUploadApplicationFormAndSlipFile(application);

        // 입학 신청 완료 메일 발송
        sendMail(application);

        return applPay.getApplNo();

        //BirtController 호출해서 수험표, 수험원서를 물리적 PDF 파일로 저장은 xpay/result에서 ajax로 몰래 BirtController호출하는걸로

        /*
        ApplicationPaymentExample param = new ApplicationPaymentExample();
        param.createCriteria().andLgdOidEqualTo(applPay.getLgdOid());

        ApplicationPayment orgApplPay = commonDAO.queryForObject(NAME_SPACE+"ApplicationPaymentMapper.selectByExample", param, ApplicationPayment.class);

        applPay.setApplNo(orgApplPay.getApplNo());
        applPay.setPaySeq(orgApplPay.getPaySeq()+1);
        applPay.setExpPayAmt(orgApplPay.getExpPayAmt());
        applPay.setPayStsCode("00002");
        applPay.setCreDate(new Date());

        //결제 DB 처리
        commonDAO.insertItem(applPay, NAME_SPACE, "ApplicationPaymentMapper");

        //수헙번호 순번 조회
        CustomNewSeq customNewSeq = commonDAO.queryForObject("com.apexsoft.ysprj.applicants.application.sqlmap.CustomApplicationMapper.selectNewSeq",
                                                             applPay.getApplNo(), CustomNewSeq.class);
        //수험번호 순번 갱신
        commonDAO.updateItem(customNewSeq, "com.apexsoft.ysprj.applicants.application.sqlmap.", "CustomApplicationMapper.", "updateApplIdSeqIdByNewSeq");

        //수험번호, 결제완료 상태 적용
        Application application = commonDAO.queryForObject("com.apexsoft.ysprj.applicants.application.sqlmap.ApplicationMapper.selectByPrimaryKey",
                                                           applPay.getApplNo(), Application.class);
        application.setApplId(getApplId(application, customNewSeq.getNewSeq()));
        application.setApplStsCode("00020");
        commonDAO.updateItem(application, "com.apexsoft.ysprj.applicants.application.sqlmap.", "ApplicationMapper");
        */

    }

    @Override
    public ExecutionContext registerManualPay( ApplicationPaymentTransaction applPayTr ) {

        ExecutionContext ec = new ExecutionContext();
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");

        ApplicationPaymentCurStat applPay = new ApplicationPaymentCurStat();
        applPay.setApplNo(applPayTr.getApplNo());
        applPay.setPayStsCode("00002");
        applPay.setPayTypeCode(applPayTr.getPayTypeCode());
        applPay.setPayAmt(applPayTr.getPayAmt());
        applPay.setPayDate(df.format(date));
        applPay.setModId("admin_pay");
        applPay.setModDate(date);

        commonDAO.updateItem(applPay, NAME_SPACE, "ApplicationPaymentCurStatMapper");

        //수헙번호 순번 조회
        CustomNewSeq customNewSeq = commonDAO.queryForObject("com.apexsoft.ysprj.applicants.application.sqlmap.CustomApplicationMapper.selectNewSeq",
                                                             applPay.getApplNo(), CustomNewSeq.class);
        //수험번호 순번 갱신
        commonDAO.updateItem(customNewSeq, "com.apexsoft.ysprj.applicants.application.sqlmap.", "CustomApplicationMapper.", "updateApplIdSeqIdByNewSeq");

        //수험번호, 결제완료 상태 적용
        Application application = commonDAO.queryForObject("com.apexsoft.ysprj.applicants.application.sqlmap.ApplicationMapper.selectByPrimaryKey",
                                                           applPay.getApplNo(), Application.class);
        application.setApplId(getApplId(application, customNewSeq.getNewSeq()));
        application.setApplDate(date);
        application.setApplStsCode(ApplicationStatus.COMPLETED.codeVal());
        commonDAO.updateItem(application, "com.apexsoft.ysprj.applicants.application.sqlmap.", "ApplicationMapper");

        //결제 트랜젝션 정보 처리 (APPL_PAY_TR)
        applPayTr.setPayDate(applPay.getPayDate());
        applPayTr.setPayStsCode(applPay.getPayStsCode());
        applPayTr.setCreId(applPay.getModId());
        applPayTr.setCreDate(date);

        int lastSeq = 0;
        lastSeq = commonDAO.queryForInt(NAME_SPACE + "CustomApplicationPaymentTransactionMapper.selectMaxSeqByApplNo", applPayTr.getApplNo());
        applPayTr.setPaySeq(lastSeq + 1);

        commonDAO.insertItem(applPayTr, NAME_SPACE, "ApplicationPaymentTransactionMapper");

        //APPL_DOC에 수험번호가 채번된 원서, 수험표 정보 저장
        documentService.saveApplicationPaperInfo(application);
        documentService.saveAdmissionSlipPaperInfo(application);

        // 원서 수험표, 생성, S3 업로드
        genAndUploadApplicationFormAndSlipFile(application);

        // 입학 신청 완료 메일 발송
        sendMail(application);

        return ec;
    }

    private XPayCertRequest setXPayCertRequest( Payment payment ) {

        XPayCertRequest certReq = new XPayCertRequest();

        certReq.setLgdMid(payment.getLGD_MID());
        certReq.setLgdOid(payment.getLGD_OID());
        certReq.setLgdAmount(payment.getLGD_AMOUNT());
        certReq.setLgdTaxfreeamount(payment.getLGD_TAXFREEAMOUNT());
        certReq.setLgdBuyer(payment.getLGD_BUYER());
        certReq.setLgdProductinfo(payment.getLGD_PRODUCTINFO());
        certReq.setLgdTimestamp(payment.getLGD_TIMESTAMP());
        certReq.setLgdHashdata(payment.getLGD_HASHDATA());
        certReq.setLgdBuyerid(payment.getLGD_BUYERID());
        certReq.setLgdBuyerip(payment.getLGD_BUYERIP());
        certReq.setLgdBuyeremail(payment.getLGD_BUYEREMAIL());
        certReq.setLgdCustomSkin(payment.getLGD_CUSTOM_SKIN());
        certReq.setLgdWindowVer(payment.getLGD_WINDOW_VER());
        certReq.setLgdCustomProcesstype(payment.getLGD_CUSTOM_PROCESSTYPE());
        certReq.setLgdCasnoteurl(payment.getLGD_CASNOTEURL());

        return certReq;
    }

    private XPayCertResult setXPayCertResult( Payment payment ) {

        XPayCertResult certRslt = new XPayCertResult();

        certRslt.setLgdRespcode(payment.getLGD_RESPCODE());
        certRslt.setLgdRespmsg(payment.getLGD_RESPMSG());
        certRslt.setLgdMid(payment.getLGD_MID());
        certRslt.setLgdOid(payment.getLGD_OID());
        certRslt.setLgdAmount(payment.getLGD_AMOUNT());
        certRslt.setLgdPaykey(payment.getLGD_PAYKEY());

        return certRslt;
    }

    private ExecutionContext registerPaymentRequestLog( Payment payment ) {

        ExecutionContext ec = new ExecutionContext();

        XPayPayRequest xPayPayRequest = new XPayPayRequest();
        xPayPayRequest.setLgdTxname(PaymentConfig.LGD_TXNAME);
        xPayPayRequest.setLgdPaykey(payment.getLGD_PAYKEY());
        xPayPayRequest.setCreDate(new Date());

        int r1 = commonDAO.insertItem(xPayPayRequest, NAME_SPACE, "XPayPayRequestMapper");

        if( r1 > 0 ) {
            ec.setResult(ExecutionContext.SUCCESS);
        } else {
            ec.setResult(ExecutionContext.FAIL);
        }

        return ec;
    }

    private ExecutionContext registerPaymentResultLog( XPayClient xpay ) {

        ExecutionContext ec = new ExecutionContext();

        XPayPayResult xPayPayResult = new XPayPayResult();
        xPayPayResult.setLgdRespcode(xpay.Response("LGD_RESPCODE", 0));
        xPayPayResult.setLgdRespmsg(xpay.Response("LGD_RESPMSG", 0));
        xPayPayResult.setLgdMid(xpay.Response("LGD_MID", 0));
        xPayPayResult.setLgdOid(xpay.Response("LGD_OID", 0));
        xPayPayResult.setLgdAmount(xpay.Response("LGD_AMOUNT", 0));
        xPayPayResult.setLgdTid(xpay.Response("LGD_TID", 0));
        xPayPayResult.setLgdPaytype(xpay.Response("LGD_PAYTYPE", 0));
        xPayPayResult.setLgdPaydate(xpay.Response("LGD_PAYDATE", 0));
        xPayPayResult.setLgdHashdata(xpay.Response("LGD_HASHDATA", 0));
        xPayPayResult.setLgdFinancecode(xpay.Response("LGD_FINANCECODE", 0));
        xPayPayResult.setLgdFinancename(xpay.Response("LGD_FINANCENAME", 0));
        xPayPayResult.setLgdAccountnum(xpay.Response("LGD_ACCOUNTNUM", 0));
        xPayPayResult.setLgdCasflag(xpay.Response("LGD_CASFLAG", 0));
        xPayPayResult.setLgdCasseqno(xpay.Response("LGD_CASSEQNO", 0));

        //TODO 추가 속성 처리

        xPayPayResult.setCreDate(new Date());

        int r1 = commonDAO.insertItem(xPayPayResult, NAME_SPACE, "XPayPayResultMapper");

        if( r1 > 0 ) {
            ec.setResult(ExecutionContext.SUCCESS);
        } else {
            ec.setResult(ExecutionContext.FAIL);
        }

        return ec;
    }

    private ExecutionContext registerPaymentWait( Payment payment, XPayClient xpay ) {

        ExecutionContext ec = new ExecutionContext();
        int r1 = 0, r2 = 0;

        //결제완료 상태 적용
        Application application = commonDAO.queryForObject("com.apexsoft.ysprj.applicants.application.sqlmap.ApplicationMapper.selectByPrimaryKey",
                                                           payment.getApplNo(), Application.class);
        application.setApplStsCode("00021");
        r1 = commonDAO.updateItem(application, "com.apexsoft.ysprj.applicants.application.sqlmap.", "ApplicationMapper");

        //결제 정보 처리 (APPL_PAY_CS)
        ApplicationPaymentCurStat applPay = new ApplicationPaymentCurStat();
        applPay.setApplNo(payment.getApplNo());
        applPay.setPayTypeCode(xpay.Response("LGD_PAYTYPE", 0));
        String payAmt = xpay.Response("LGD_CASCAMOUNT", 0);
        if( payAmt != null && !payAmt.equals("") )
            applPay.setPayAmt(Integer.valueOf(payAmt));
        applPay.setLgdOid(xpay.Response("LGD_OID", 0));
        applPay.setLgdTid(xpay.Response("LGD_TID", 0));
        applPay.setPayStsCode("00005");
        applPay.setModId(application.getUserId());
        applPay.setModDate(new Date());
        r2 = commonDAO.updateItem(applPay, NAME_SPACE, "ApplicationPaymentCurStatMapper");

        //결제 트랜젝션 정보 처리 (APPL_PAY_TR)
        registerPaymentTransaction(applPay);

        /*
        int paySeq = commonDAO.queryForInt(NAME_SPACE+"CustomApplicationPaymentMapper.getSeq", payment.getApplNo());

        ApplicationPayment applPay = new ApplicationPayment();
        applPay.setApplNo(payment.getApplNo());
        applPay.setPaySeq(paySeq);
        applPay.setPayTypeCode(xpay.Response("LGD_PAYTYPE", 0));
        String payAmt = xpay.Response("LGD_AMOUNT", 0);
        if( payAmt != null && !payAmt.equals("") )
            applPay.setPayAmt(Integer.valueOf(payAmt));
        applPay.setPayDate(xpay.Response("LGD_PAYDATE", 0));
        applPay.setLgdOid(xpay.Response("LGD_OID", 0));
        applPay.setLgdTid(xpay.Response("LGD_TID", 0));
        applPay.setPayStsCode("00005");
        r2 = commonDAO.updateItem(applPay, NAME_SPACE, "ApplicationPaymentMapper");
        */

        if( r1>0 && r2>0 ) {
            ec.setResult(ExecutionContext.SUCCESS);
        } else {
            ec.setResult(ExecutionContext.FAIL);
        }

        return ec;
    }


    private String getApplId( Application application, int newSeq ) {

        String applId = null;
        String applNo3 = null;

        if( newSeq < 10 ) applNo3 = "00" + newSeq;
        else if( newSeq < 100 ) applNo3 = "0" + newSeq;
        else applNo3 = "" + newSeq;

        Department dept = commonDAO.queryForObject(NAME_SPACE + "CustomApplicationPaymentMapper.selectDeptSeq", application.getDeptCode(), Department.class);

        applId = application.getAdmsNo().substring(1) + dept.getDeptSeq() + application.getCorsTypeCode() + applNo3;

        return applId;
    }

    public List<Application> retrieveApplByApplStsCode(String applStsCode) {
        List<Application> applList = commonDAO.queryForList(
                "com.apexsoft.ysprj.applicants.application.sqlmap.CustomApplicationMapper.selectApplListByApplStsCode",
                applStsCode,
                Application.class);
        return applList;
    }

    private ExecutionContext genAndUploadApplicationFormAndSlipFile(Application application) {
        ExecutionContext ec = new ExecutionContext();
        String stage = null;
        try {
            String admsTypeCode = application.getAdmsTypeCode();
            String lang = "C".equals(admsTypeCode) || "D".equals(admsTypeCode) ? "en" : "kr";
            String reportName = "yonsei-appl-" + lang;
            stage = "before generate ApplForm";
            ExecutionContext ecGenAppl = birtService.generateBirtFile(application.getApplNo(), reportName);
            reportName = "yonsei-adms-" + lang;
            stage = "before generate ApplSlip";
            ExecutionContext ecGenAdms = birtService.generateBirtFile(application.getApplNo(), reportName);
            stage = "before generate PDF and Upload";
            ExecutionContext ecPdfMerge = pdfService.genAndUploadPDFByApplicants(application);

            if ( ExecutionContext.FAIL.equals(ecGenAppl.getResult()))
                throw new YSBizException(ecGenAppl);
            if ( ExecutionContext.FAIL.equals(ecGenAdms.getResult()))
                throw new YSBizException(ecGenAdms);
            if ( ExecutionContext.FAIL.equals(ecPdfMerge.getResult()))
                throw new YSBizException(ecPdfMerge);

        } catch (Exception e) {
            // TODO YSBizException을 던지지 말고, 로그나 DB에 남긴 후 정상 흐름 타서 사용자에게 결제 완료 알림가도록 처리 필요
            logger.error("Error in PaymentServiceImpl.genAndUploadApplicationFormAndSlipFile(), stage : " + stage +
             "applNo : " + application.getApplNo() + ", userId" + application.getUserId() );
            ExecutionContext ec1 = new ExecutionContext(ExecutionContext.FAIL);
            Map<String, String> errorMap = new HashMap<String, String>();
            errorMap.put("applNo", String.valueOf(application.getApplNo()));
            errorMap.put("userId", String.valueOf(application.getUserId()));
            ErrorInfo errorInfo = new ErrorInfo();
            errorInfo.setInfo(errorMap);
            ec1.setErrorInfo(errorInfo);
            throw new YSBizException(MessageResolver.getMessage("U05109"), new NullPointerException(), MessageResolver.getMessage("ERR0302"));
        }

        return ec;
    }

    private void sendMail(Application application) {
        MailInfo mailInfo = commonDAO.queryForObject("com.apexsoft.ysprj.applicants.common.sqlmap.CustomMailMapper.selectApplicationCompletedMailInfo",
                application.getApplNo(), MailInfo.class);
        String mailAddr = application.getMailAddr();
        String korName = application.getKorName();
        boolean hasKorName = korName != null && !StringUtils.isEmpty(korName);
        String userName = application.getEngName() + " " + application.getEngSur() + (hasKorName ? "(" + application.getKorName() + ")" : "");                    ;
        String userId = application.getUserId();
        String campName = mailInfo.getCampName();
        String major = mailInfo.getDeptName();
        String applId = mailInfo.getApplId();
        String admsNo = mailInfo.getAdmsNo();
        Locale locale = null;
        if (admsNo.endsWith("C") || admsNo.endsWith("D")) {
            locale = Locale.US;
            campName = mailInfo.getCampNameXxen();
            major = mailInfo.getDeptNameXxen();
        } else {
            locale = Locale.KOREAN;
        }

        Mail mail = mailFactory.create(MailType.COMPLETE_NOTI, locale);
        mail.setTo(new String[]{mailAddr});
        mail.withContentsParam(MailContentsParamKey.USER_NAME, userName)
                .withContentsParam(MailContentsParamKey.USER_ID, userId)
                .withContentsParam(MailContentsParamKey.INSTITUTE_NAME, campName)
                .withContentsParam(MailContentsParamKey.MAJOR, major)
                .withContentsParam(MailContentsParamKey.APPL_ID, applId)
                .makeContents();
        try {
            sesMailService.sendMail(mail);
        } catch (Exception e) {
            // TODO YSBizException을 던지지 말고, 로그나 DB에 남긴 후 정상 흐름 타서 사용자에게 결제 완료 알림가도록 처리 필요
            e.printStackTrace();
        }
    }
}
