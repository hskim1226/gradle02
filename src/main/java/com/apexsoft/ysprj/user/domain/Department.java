package com.apexsoft.ysprj.user.domain;

import java.util.Date;

/**
 * Created by go2zo on 2014. 8. 17.
 */
public class Department {

    private String deptCode; // 학과코드
    private String deptName; // 학과명
    private String collCode; // 대학코드
    private String afflName; // 계열명
    private String useYn; // 사용여부
    private String creId; // 생성자
    private Date   creDate; // 생성일자
    private String modId; // 수정자
    private Date   modDate; // 수정일자

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getCollCode() {
        return collCode;
    }

    public void setCollCode(String collCode) {
        this.collCode = collCode;
    }

    public String getAfflName() {
        return afflName;
    }

    public void setAfflName(String afflName) {
        this.afflName = afflName;
    }

    public String getUseYn() {
        return useYn;
    }

    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }

    public String getCreId() {
        return creId;
    }

    public void setCreId(String creId) {
        this.creId = creId;
    }

    public Date getCreDate() {
        return creDate;
    }

    public void setCreDate(Date creDate) {
        this.creDate = creDate;
    }

    public String getModId() {
        return modId;
    }

    public void setModId(String modId) {
        this.modId = modId;
    }

    public Date getModDate() {
        return modDate;
    }

    public void setModDate(Date modDate) {
        this.modDate = modDate;
    }
}
