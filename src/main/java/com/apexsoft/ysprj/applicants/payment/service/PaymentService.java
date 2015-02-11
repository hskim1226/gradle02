package com.apexsoft.ysprj.applicants.payment.service;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.xpay.service.TransactionVO;
import com.apexsoft.ysprj.applicants.payment.domain.ApplicationPayment;
import com.apexsoft.ysprj.applicants.payment.domain.Payment;

/**
 * Created by cosb071 on 15. 1. 22.
 *
 * 결제 처리 서비스
 */
public interface PaymentService {

    ExecutionContext registerPaymentCertifyLog( Payment payment );

    void executePayment( Payment payment, TransactionVO transactionVO );

    void registerCasNote( ApplicationPayment applPay );

    //ExecutionContext registerPaymentRequestLog( Payment payment );

    //ExecutionContext registerPaymentResultLog( XPayClient xpay );

    //ExecutionContext registerPaymentSuccess( Payment payment );

}
