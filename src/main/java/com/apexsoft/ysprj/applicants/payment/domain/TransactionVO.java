package com.apexsoft.ysprj.applicants.payment.domain;

import java.util.HashMap;
import java.util.Map;

public class TransactionVO {

    private int applNo;
    private String sysMsg;
    private String userMsg;
    Map<String, String> txMap = new HashMap<String, String>();

    public int getApplNo() {
        return applNo;
    }

    public void setApplNo(int applNo) {
        this.applNo = applNo;
    }

    public String getSysMsg() {
        return sysMsg;
    }

    public void setSysMsg(String msg) {
        this.sysMsg = msg;
    }

    public String getUserMsg() {
        return userMsg;
    }

    public void setUserMsg(String userMsg) {
        this.userMsg = userMsg;
    }

    public Map<String, String> getTxMap() {
        return txMap;
    }

    public void setTxMap(Map<String, String> txMap) {
        this.txMap = txMap;
    }
}
