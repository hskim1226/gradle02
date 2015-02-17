package com.apexsoft.ysprj.applicants.application.domain;

import java.util.List;

/**
 * Created by hanmomhanda on 15. 1. 12.
 */
public class Academy {

    private Application application;
    private List<CustomApplicationAcademy> collegeList;
    private List<CustomApplicationAcademy> graduateList;

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public List<CustomApplicationAcademy> getCollegeList() {
        return collegeList;
    }

    public void setCollegeList(List<CustomApplicationAcademy> collegeList) {
        this.collegeList = collegeList;
    }

    public List<CustomApplicationAcademy> getGraduateList() {
        return graduateList;
    }

    public void setGraduateList(List<CustomApplicationAcademy> graduateList) {
        this.graduateList = graduateList;
    }
}
