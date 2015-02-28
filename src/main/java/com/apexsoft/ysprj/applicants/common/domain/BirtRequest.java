package com.apexsoft.ysprj.applicants.common.domain;

import com.apexsoft.ysprj.applicants.application.domain.Application;

/**
 * Created by hanmomhanda on 15. 2. 28.
 */
public class BirtRequest {

    private Application application;
    private String reqType;

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public String getReqType() {
        return reqType;
    }

    public void setReqType(String reqType) {
        this.reqType = reqType;
    }
}
