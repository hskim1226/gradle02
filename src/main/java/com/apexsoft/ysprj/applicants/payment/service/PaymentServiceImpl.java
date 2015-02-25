package com.apexsoft.ysprj.applicants.payment.service;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.exception.YSBizException;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.framework.unused.xpay.service.TransactionVO;
import com.apexsoft.ysprj.applicants.application.domain.Application;
import com.apexsoft.ysprj.applicants.application.domain.CustomNewSeq;
import com.apexsoft.ysprj.applicants.application.domain.CustomPayInfo;
import com.apexsoft.ysprj.applicants.application.domain.TotalApplicationDocument;
import com.apexsoft.ysprj.applicants.application.service.DocumentService;
import com.apexsoft.ysprj.applicants.common.util.FileUtil;
import com.apexsoft.ysprj.applicants.payment.domain.*;
import lgdacom.XPayClient.XPayClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by cosb071 on 15. 1. 22.
 *
 * 결제 처리 서비스
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    private final static String NAME_SPACE = "com.apexsoft.ysprj.applicants.payment.sqlmap.";

    @Autowired
    private CommonDAO commonDAO;

    @Autowired
    private DocumentService documentService;

    @Value("#{app['file.baseDir']}")
    private String BASE_DIR;

    @Resource(name = "messageResolver")
    MessageResolver messageResolver;

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
            ec.setMessage(messageResolver.getMessage("U335"));
        } else {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(messageResolver.getMessage("U336"));
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
            ec.setMessage(messageResolver.getMessage("U335"));
        } else {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(messageResolver.getMessage("U336"));
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

        Payment newPayInfo = commonDAO.queryForObject("com.apexsoft.ysprj.applicants.payment.sqlmap.CustomApplicationPaymentMapper.selectConfirmInfo",
                                                      payment.getApplNo(), Payment.class);

        payment.setApplStsCode(newPayInfo.getApplStsCode());
        payment.setLGD_AMOUNT(newPayInfo.getLGD_AMOUNT());
        payment.setLGD_FINANCENAME(newPayInfo.getLGD_FINANCENAME());
        payment.setLGD_ACCOUNTNUM(newPayInfo.getLGD_ACCOUNTNUM());

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
    public void executePayment( Payment payment, TransactionVO transactionVO ) {

        /*
         * [최종결제요청 페이지(STEP2-2)]
         *
         * LG유플러스으로 부터 내려받은 LGD_PAYKEY(인증Key)를 가지고 최종 결제요청.(파라미터 전달시 POST를 사용하세요)
         */

        String configPath = PaymentConfig.CONFIG_PATH;  //LG유플러스에서 제공한 환경파일("/conf/lgdacom.conf,/conf/mall.conf") 위치 지정.

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
            transactionVO.setSysMsg(messageResolver.getMessage("A000"));
            transactionVO.setUserMsg(messageResolver.getMessage("U000"));
            return;
        } else {
            try {

                xpay.Init_TX(LGD_MID);
                xpay.Set("LGD_TXNAME", PaymentConfig.LGD_TXNAME);
                xpay.Set("LGD_PAYKEY", LGD_PAYKEY);

                //결제 요청 로그 DB 처리
                registerPaymentRequestLog(payment);

            } catch(Exception e) {
                transactionVO.setSysMsg(messageResolver.getMessage("A001") + e.getMessage());
                transactionVO.setUserMsg(messageResolver.getMessage("U001"));
                return;
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
                transactionVO.setApplNo(payment.getApplNo());
                if( "SC0010".equals(payType) || "SC0030".equals(payType) ) {

                    //카드 또는 계좌이체에 대한 DB 처리
                    registerPaymentSuccess(payment, xpay);

                    //결제 성공에 대한 화면 처리
                    transactionVO.setSysMsg(transactionVO.getSysMsg() + "최종결제요청 결과 성공 DB처리하시기 바랍니다.<br>");
                    transactionVO.setUserMsg(messageResolver.getMessage("U002"));

                } else if( "SC0040".equals(payType) ) {

                    //가상계좌 입금대기에 대한 DB 처리
                    registerPaymentWait(payment, xpay);

                    //결제 성공에 대한 화면 처리
                    String msg = messageResolver.getMessage("U003");
                    msg = msg + "<br><br> 가상계좌정보";
                    msg = msg + "<br> 은행 : " + xpay.Response("LGD_FINANCENAME", 0);
                    msg = msg + "<br> 계좌 : " + xpay.Response("LGD_ACCOUNTNUM", 0);
                    transactionVO.setSysMsg(transactionVO.getSysMsg() + "최종결제요청 결과 성공 DB처리하시기 바랍니다.<br>");
                    transactionVO.setUserMsg(msg);

                } else {
                    //TODO 예외 처리
                }

            } else {

                //결제 실패에 대한 화면 처리
                transactionVO.setSysMsg(transactionVO.getSysMsg() + "최종결제요청 결과 실패 DB처리하시기 바랍니다.<br>");
                transactionVO.setUserMsg(messageResolver.getMessage(""));
                //TODO 실패 코드 필요
            }

        } else {
            //2)API 요청실패 처리
            transactionVO.setSysMsg("결제요청이 실패하였습니다.  <br>" +
                            "TX 결제요청 Response_code = " + xpay.m_szResCode + "<br>" +
                            "TX 결제요청 Response_msg = " + xpay.m_szResMsg + "<p>"
            );
            transactionVO.setSysMsg(transactionVO.getSysMsg() + "최종결제요청 결과 실패 DB처리하시기 바랍니다.<br>");
        }

    }

    @Override
    public void registerCasNote( ApplicationPaymentCurStat applPay ) {

        //LGD_OID로 해당 결제 조회
        ApplicationPaymentCurStatExample param = new ApplicationPaymentCurStatExample();
        param.createCriteria().andLgdOidEqualTo(applPay.getLgdOid());

        ApplicationPaymentCurStat orgApplPay = commonDAO.queryForObject(NAME_SPACE+"ApplicationPaymentCurStatMapper.selectByExample", param, ApplicationPaymentCurStat.class);
        applPay.setApplNo(orgApplPay.getApplNo());
        applPay.setPayStsCode("00002");
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
        application.setApplStsCode("00020");
        commonDAO.updateItem(application, "com.apexsoft.ysprj.applicants.application.sqlmap.", "ApplicationMapper");

        //APPL_DOC에 수험표, 원서 정보 저장
        //TODO 예외처리
        saveApplDocInfo(application);

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

    private XPayCertRequest setXPayCertRequest( Payment payment ) {

        XPayCertRequest certReq = new XPayCertRequest();

        certReq.setLgdMid(payment.getLGD_MID());
        certReq.setLgdOid(payment.getLGD_OID());
        certReq.setLgdAmount(payment.getLGD_AMOUNT());
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

    private ExecutionContext registerPaymentSuccess( Payment payment, XPayClient xpay ) {

        ExecutionContext ec = new ExecutionContext();
        int r1 = 0, r2 = 0, r3 = 0;

        //수헙번호 순번 조회
        CustomNewSeq customNewSeq = commonDAO.queryForObject("com.apexsoft.ysprj.applicants.application.sqlmap.CustomApplicationMapper.selectNewSeq",
                                                             payment.getApplNo(), CustomNewSeq.class);
        //수험번호 순번 갱신
        r1 = commonDAO.updateItem(customNewSeq, "com.apexsoft.ysprj.applicants.application.sqlmap.", "CustomApplicationMapper.", "updateApplIdSeqIdByNewSeq");

        //수험번호, 결제완료 상태 적용
        Application application = commonDAO.queryForObject("com.apexsoft.ysprj.applicants.application.sqlmap.ApplicationMapper.selectByPrimaryKey",
                                                           payment.getApplNo(), Application.class);
        application.setApplId(getApplId(application, customNewSeq.getNewSeq()));
        application.setApplDate(new Date());
        application.setApplStsCode("00020");
        r2 = commonDAO.updateItem(application, "com.apexsoft.ysprj.applicants.application.sqlmap.", "ApplicationMapper");

        //결제 정보 처리
        ApplicationPaymentCurStat applPay = new ApplicationPaymentCurStat();
        applPay.setApplNo(payment.getApplNo());
        applPay.setPayTypeCode(xpay.Response("LGD_PAYTYPE", 0));
        String payAmt = xpay.Response("LGD_AMOUNT", 0);
        if( payAmt != null && !payAmt.equals("") )
            applPay.setPayAmt(Integer.valueOf(payAmt));
        applPay.setPayDate(xpay.Response("LGD_PAYDATE", 0));
        applPay.setLgdOid(xpay.Response("LGD_OID", 0));
        applPay.setLgdTid(xpay.Response("LGD_TID", 0));
        applPay.setPayStsCode("00002");
        r3 = commonDAO.updateItem(applPay, NAME_SPACE, "ApplicationPaymentCurStatMapper");

        //APPL_DOC에 수험표, 원서 정보 저장
        //TODO 예외 처리
        saveApplDocInfo(application);

        //BirtController 호출해서 수험표, 수험원서를 물리적 PDF 파일로 저장은 xpay/result에서 ajax로 몰래 BirtController호출하는걸로

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
        applPay.setPayStsCode("00002");
        r3 = commonDAO.updateItem(applPay, NAME_SPACE, "ApplicationPaymentMapper");
        */

        if( r1>0 && r2>0 && r3>0 ) {
            ec.setResult(ExecutionContext.SUCCESS);
        } else {
            ec.setResult(ExecutionContext.FAIL);
        }

        return ec;
    }

    private ExecutionContext registerPaymentWait( Payment payment, XPayClient xpay ) {

        ExecutionContext ec = new ExecutionContext();
        int r1 = 0, r2 = 0;

        //수험번호, 결제완료 상태 적용
        Application application = commonDAO.queryForObject("com.apexsoft.ysprj.applicants.application.sqlmap.ApplicationMapper.selectByPrimaryKey",
                                                           payment.getApplNo(), Application.class);
        application.setApplStsCode("00021");
        r1 = commonDAO.updateItem(application, "com.apexsoft.ysprj.applicants.application.sqlmap.", "ApplicationMapper");

        //결제 정보 처리
        ApplicationPaymentCurStat applPay = new ApplicationPaymentCurStat();
        applPay.setApplNo(payment.getApplNo());
        applPay.setPayTypeCode(xpay.Response("LGD_PAYTYPE", 0));
        String payAmt = xpay.Response("LGD_CASCAMOUNT", 0);
        if( payAmt != null && !payAmt.equals("") )
            applPay.setPayAmt(Integer.valueOf(payAmt));
        applPay.setLgdOid(xpay.Response("LGD_OID", 0));
        applPay.setLgdTid(xpay.Response("LGD_TID", 0));
        applPay.setPayStsCode("00005");
        r2 = commonDAO.updateItem(applPay, NAME_SPACE, "ApplicationPaymentCurStatMapper");

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

        applId = application.getAdmsNo() + application.getDeptCode() + application.getCorsTypeCode() + applNo3;

        return applId;
    }

    private void saveApplDocInfo(Application application) {
        String userId = application.getUserId();
        String admsNo = application.getAdmsNo();
        int applNo = application.getApplNo();

        TotalApplicationDocument aDoc = new TotalApplicationDocument();
        aDoc.setApplNo(applNo);
        //TODO 수험표 Birt 처리 후 아래 내용 활성화
//        aDoc.setDocSeq(-2);
//        aDoc.setDocItemName("수험표");
//        aDoc.setFileExt("pdf");
//        aDoc.setImgYn("N");
//        aDoc.setFilePath(FileUtil.getUploadDirectoryFullPath(BASE_DIR, admsNo, userId, String.valueOf(applNo)));
//        aDoc.setFileName(FileUtil.getSlipFileName(userId));
//        aDoc.setOrgFileName(FileUtil.getSlipFileName(userId));
//        aDoc.setPageCnt(1);
//        aDoc.setCreId(application.getUserId());
//        aDoc.setCreDate(new Date());
//        documentService.saveOneDocument(aDoc);
        aDoc.setDocSeq(-1);
        aDoc.setDocItemName("지원서");
        aDoc.setFileName(FileUtil.getApplicationFileName(userId));
        aDoc.setOrgFileName(FileUtil.getApplicationFileName(userId));
        aDoc.setPageCnt(2);
        aDoc.setFileUploadFg(false);
        aDoc.setDocSeq(0);
        documentService.saveOneDocument(aDoc);
    }
}
