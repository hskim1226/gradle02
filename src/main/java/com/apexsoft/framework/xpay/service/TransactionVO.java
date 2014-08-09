package com.apexsoft.framework.xpay.service;

import java.util.HashMap;
import java.util.Map;

public class TransactionVO {

    String msg;
    Map<String, String> txMap = new HashMap<String, String>();

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, String> getTxMap() {
        return txMap;
    }

    public void setTxMap(Map<String, String> txMap) {
        this.txMap = txMap;
    }
}
