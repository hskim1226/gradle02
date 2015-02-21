package com.apexsoft.framework.xpay.service;

import java.util.HashMap;
import java.util.Map;

@Deprecated
public class TransactionVO {

    private String sysMsg;
    private String userMsg;
    Map<String, String> txMap = new HashMap<String, String>();

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
