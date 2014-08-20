package com.apexsoft.ysprj.user.test;

import java.util.List;

/**
 * Created by hanmomhanda on 14. 8. 20.
 */
public class EntireApplication extends Application {

    private Department department;
    private List<Academy> academyList;
    private List<Career> careerList;

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Academy> getAcademyList() {
        return academyList;
    }

    public void setAcademyList(List<Academy> academyList) {
        this.academyList = academyList;
    }

    public List<Career> getCareerList() {
        return careerList;
    }

    public void setCareerList(List<Career> career) {
        this.careerList = career;
    }
}
