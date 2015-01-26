package com.apexsoft.ysprj.applicants.application.domain;

/**
 * Created by hanmomhanda on 15. 1. 25.
 */
public class CustomApplicationExperience extends ApplicationExperience {

    private UserCUDType userCUDType;

    public UserCUDType getUserCUDType() {
        return userCUDType;
    }

    public void setUserCUDType(UserCUDType userCUDType) {
        this.userCUDType = userCUDType;
    }
}
