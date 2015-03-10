package com.apexsoft.ysprj.applicants.application.domain;

/**
 * Created by dhKim on 15. 2. 03.
 */
public class ParamForDocumentType {

    private int applNo;
    private int docGrp;
    private String docTypeCode;
    private String docItemCode;


    public int getApplNo() {
        return applNo;
    }

    public void setApplNo(int applNo) {
        this.applNo = applNo;
    }

    public int getDocGrp() {
        return docGrp;
    }

    public void setDocGrp(int docGrp) {
        this.docGrp = docGrp;
    }

    public String getDocTypeCode() {
        return docTypeCode;
    }

    public void setDocTypeCode(String docTypeCode) {
        this.docTypeCode = docTypeCode;
    }

    public String getDocItemCode() {
        return docItemCode;
    }

    public void setDocItemCode(String docItemCode) {
        this.docItemCode = docItemCode;
    }
}
