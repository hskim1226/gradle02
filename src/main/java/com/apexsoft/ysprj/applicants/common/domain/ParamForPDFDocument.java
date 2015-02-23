package com.apexsoft.ysprj.applicants.common.domain;

/**
 * Created by hanmomhanda on 15. 2. 22.
 */
public class ParamForPDFDocument {

    private int applNo;
    private String fileExt;

    public ParamForPDFDocument(int applNo) {
        this(applNo, "pdf");
    }

    public ParamForPDFDocument(int applNo, String fileExt) {
        this.applNo = applNo;
        this.fileExt = fileExt;
    }

    public int getApplNo() {
        return applNo;
    }

    public void setApplNo(int applNo) {
        this.applNo = applNo;
    }

    public String getFileExt() {
        return fileExt;
    }

    public void setFileExt(String fileExt) {
        this.fileExt = fileExt;
    }
}
