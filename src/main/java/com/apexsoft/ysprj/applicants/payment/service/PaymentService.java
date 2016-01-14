package com.apexsoft.ysprj.applicants.payment.service;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.ysprj.applicants.application.domain.Application;
import com.apexsoft.ysprj.applicants.payment.domain.*;
import lgdacom.XPayClient.XPayClient;

import java.util.List;

/**
 * Created by cosb071 on 15. 1. 22.
 *
 * 결제 처리 서비스
 */
public interface PaymentService {

    ExecutionContext retrieveConfirmInfo( Payment payment );

    ExecutionContext registerPaymentCertifyLog( Payment payment );

    <T> ExecutionContext<T> executePayment( Payment payment, TransactionVO transactionVO );

    void updateStatus(Payment payment, PaymentResult paymentResult);

    void processFiles(Application application);

    void sendNotification(Application application);

    int registerCasNote( ApplicationPaymentCurStat applPay );

    ExecutionContext saveApplicationPayment(Application application);

    ExecutionContext registerManualPay( ApplicationPaymentTransaction applPayTr );

    //ExecutionContext saveApplicationPayment2(Application application);

    //ExecutionContext registerPaymentRequestLog( Payment payment );

    //ExecutionContext registerPaymentResultLog( XPayClient xpay );

    //ExecutionContext registerPaymentSuccess( Payment payment );

    List<Application> retrieveApplByApplStsCode(String applStsCode);

}
