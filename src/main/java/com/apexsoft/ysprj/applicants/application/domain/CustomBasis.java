package com.apexsoft.ysprj.applicants.application.domain;

/**
 * Created by hanmomhanda on 15. 1. 9.
 *
 * 원서 기본 정보
 */
public class CustomBasis {

    private Application application;
    private ApplicationGeneral applicationGeneral;
    private ApplicationForeigner applicationForeigner;

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public ApplicationGeneral getApplicationGeneral() {
        return applicationGeneral;
    }

    public void setApplicationGeneral(ApplicationGeneral applicationGeneral) {
        this.applicationGeneral = applicationGeneral;
    }

    public ApplicationForeigner getApplicationForeigner() {
        return applicationForeigner;
    }

    public void setApplicationForeigner(ApplicationForeigner applicationForeigner) {
        this.applicationForeigner = applicationForeigner;
    }
}
