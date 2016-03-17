package com.apexsoft.ysprj.applicants.common.domain;

import java.util.Date;

/**
 * Created by hanmomhanda on 16. 3. 17.
 */
public class ApplicationError {

    private Integer id;
    private Integer applNo;
    private String methodName;
    private String remark;
    private String isResolved;
    private Date creDate;
    private Date modDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getApplNo() {
        return applNo;
    }

    public void setApplNo(Integer applNo) {
        this.applNo = applNo;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIsResolved() {
        return isResolved;
    }

    public void setIsResolved(String isResolved) {
        this.isResolved = isResolved;
    }

    public Date getCreDate() {
        return creDate;
    }

    public void setCreDate(Date creDate) {
        this.creDate = creDate;
    }

    public Date getModDate() {
        return modDate;
    }

    public void setModDate(Date modDate) {
        this.modDate = modDate;
    }
}
