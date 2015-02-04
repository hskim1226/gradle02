package com.apexsoft.ysprj.applicants.application.domain;

/**
 * Created by hanmomhanda on 14. 9. 12.
 */
public class ParamForApplicationDocument {

    private int applNo;
    private String fileName;
    private String docTypeCode;
    private int docGrp =0;

    public int getApplNo() {
        return applNo;
    }

    public void setApplNo(int applNo) {
        this.applNo = applNo;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDocTypeCode() {
        return docTypeCode;
    }

    public void setDocTypeCode(String docTypeCode) {
        this.docTypeCode = docTypeCode;
    }

    public int getDocGrp() {
        return docGrp;
    }

    public void setDocGrp(int docGrp) {
        this.docGrp = docGrp;
    }
}
