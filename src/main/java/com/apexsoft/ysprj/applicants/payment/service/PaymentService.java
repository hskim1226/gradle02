package com.apexsoft.ysprj.applicants.payment.service;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.unused.xpay.service.TransactionVO;
import com.apexsoft.ysprj.applicants.application.domain.Application;
import com.apexsoft.ysprj.applicants.payment.domain.ApplicationPaymentCurStat;
import com.apexsoft.ysprj.applicants.payment.domain.Payment;

/**
 * Created by cosb071 on 15. 1. 22.
 *
 * 결제 처리 서비스
 */
public interface PaymentService {

    ExecutionContext retrieveConfirmInfo( Payment payment );

    ExecutionContext registerPaymentCertifyLog( Payment payment );

    String executePayment( Payment payment, TransactionVO transactionVO );

    void registerCasNote( ApplicationPaymentCurStat applPay );

    ExecutionContext saveApplicationPayment(Application application);

    //ExecutionContext saveApplicationPayment2(Application application);

    //ExecutionContext registerPaymentRequestLog( Payment payment );

    //ExecutionContext registerPaymentResultLog( XPayClient xpay );

    //ExecutionContext registerPaymentSuccess( Payment payment );

}
