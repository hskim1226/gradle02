package com.apexsoft.ysprj.admin.domain;

/**
 * Created by Dhkim on 2015-04-06.
 */
public class CommonAdminInfo {
    String admissionList;
    int currCnt =0;
    int unpaidCnt =0;
    int changeCnt=0;
    int cancelCnt=0;
    int uncmplCnt=0;

    String adminName;

    public String getAdmissionList() {
        return admissionList;
    }

    public void setAdmissionList(String admissionList) {
        this.admissionList = admissionList;
    }
        public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public int getCurrCnt() {
        return currCnt;
    }

    public void setCurrCnt(int currCnt) {
        this.currCnt = currCnt;
    }

    public int getUnpaidCnt() {
        return unpaidCnt;
    }

    public void setUnpaidCnt(int unpaidCnt) {
        this.unpaidCnt = unpaidCnt;
    }

    public int getChangeCnt() {
        return changeCnt;
    }

    public void setChangeCnt(int changeCnt) {
        this.changeCnt = changeCnt;
    }

    public int getCancelCnt() {
        return cancelCnt;
    }

    public void setCancelCnt(int cancelCnt) {
        this.cancelCnt = cancelCnt;
    }



    public int getUncmplCnt() {
        return uncmplCnt;
    }

    public void setUncmplCnt(int uncmplCnt) {
        this.uncmplCnt = uncmplCnt;
    }
}
