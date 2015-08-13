package com.apexsoft.ysprj.sysadmin.domain;

/**
 * Created by hanmomhanda on 15. 8. 13.
 */
public class StudentNumber {

    private String applId;
    private String studName;
    private String studNo;
    private String instName;
    private String deptName;
    private String corsName;
    private String s3FullPath;

    public String getApplId() {
        return applId;
    }

    public void setApplId(String applId) {
        this.applId = applId;
    }

    public String getStudName() {
        return studName;
    }

    public void setStudName(String studName) {
        this.studName = studName;
    }

    public String getStudNo() {
        return studNo;
    }

    public void setStudNo(String studNo) {
        this.studNo = studNo;
    }

    public String getInstName() {
        return instName;
    }

    public void setInstName(String instName) {
        this.instName = instName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getCorsName() {
        return corsName;
    }

    public void setCorsName(String corsName) {
        this.corsName = corsName;
    }

    public String getS3FullPath() {
        return s3FullPath;
    }

    public void setS3FullPath(String s3FullPath) {
        this.s3FullPath = s3FullPath;
    }
}
