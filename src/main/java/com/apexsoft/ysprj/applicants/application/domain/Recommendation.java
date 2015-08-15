package com.apexsoft.ysprj.applicants.application.domain;

import java.util.Date;

/**
 * Created by hanmomhanda on 15. 8. 12.
 */
public class Recommendation {

    private int recNo;

    private int applNo;

    private int recSeq;

    private String profName;

    private String profMailAddr;

    private String reqText;

    private String recKey;

    private String recStsCode;

    private String recStsName;

    private String recStsNameXxen;

    private String creId;

    private Date creDate;

    private String modId;

    private Date modDate;

    public int getRecNo() {
        return recNo;
    }

    public void setRecNo(int recNo) {
        this.recNo = recNo;
    }

    public int getApplNo() {
        return applNo;
    }

    public void setApplNo(int applNo) {
        this.applNo = applNo;
    }

    public int getRecSeq() {
        return recSeq;
    }

    public void setRecSeq(int recSeq) {
        this.recSeq = recSeq;
    }

    public String getProfName() {
        return profName;
    }

    public void setProfName(String profName) {
        this.profName = profName;
    }

    public String getProfMailAddr() {
        return profMailAddr;
    }

    public void setProfMailAddr(String profMailAddr) {
        this.profMailAddr = profMailAddr;
    }

    public String getReqText() {
        return reqText;
    }

    public void setReqText(String reqText) {
        this.reqText = reqText;
    }

    public String getRecKey() {
        return recKey;
    }

    public void setRecKey(String recKey) {
        this.recKey = recKey;
    }

    public String getRecStsCode() {
        return recStsCode;
    }

    public void setRecStsCode(String recStsCode) {
        this.recStsCode = recStsCode;
    }

    public String getRecStsName() {
        return recStsName;
    }

    public void setRecStsName(String recStsName) {
        this.recStsName = recStsName;
    }

    public String getRecStsNameXxen() {
        return recStsNameXxen;
    }

    public void setRecStsNameXxen(String recStsNameXxen) {
        this.recStsNameXxen = recStsNameXxen;
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
