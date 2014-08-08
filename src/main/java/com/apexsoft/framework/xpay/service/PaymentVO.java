package com.apexsoft.framework.xpay.service;

import javax.validation.constraints.NotNull;

/**
 * Created by hanmomhanda on 14. 8. 8.
 */
public class PaymentVO {

    @NotNull
    private String LGD_AMOUNT;

    @NotNull
    private String LGD_PRODUCTINFO;

    @NotNull
    private String LGD_TIMESTAMP;

    public String getLGD_AMOUNT() {
        return LGD_AMOUNT;
    }

    public void setLGD_AMOUNT(String LGD_AMOUNT) {
        this.LGD_AMOUNT = LGD_AMOUNT;
    }

    public String getLGD_PRODUCTINFO() {
        return LGD_PRODUCTINFO;
    }

    public void setLGD_PRODUCTINFO(String LGD_PRODUCTINFO) {
        this.LGD_PRODUCTINFO = LGD_PRODUCTINFO;
    }

    public String getLGD_TIMESTAMP() {
        return LGD_TIMESTAMP;
    }

    public void setLGD_TIMESTAMP(String LGD_TIMESTAMP) {
        this.LGD_TIMESTAMP = LGD_TIMESTAMP;
    }
}
