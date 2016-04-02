package com.apexsoft.ysprj.applicants.payment.domain;

/**
 * Created by hanmomhanda on 16. 1. 14.
 */
public class PaymentResult {
    String payType;
    ApplicationPaymentCurStat paymentCurStat;

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public ApplicationPaymentCurStat getPaymentCurStat() {
        return paymentCurStat;
    }

    public void setPaymentCurStat(ApplicationPaymentCurStat paymentCurStat) {
        this.paymentCurStat = paymentCurStat;
    }

    public boolean isCasNote() {
        return "SC0040".equals(payType);
    }

    public boolean isCardOrRealtimeTransfer() {
        return "SC0010".equals(payType) || "SC0030".equals(payType);
    }
}
