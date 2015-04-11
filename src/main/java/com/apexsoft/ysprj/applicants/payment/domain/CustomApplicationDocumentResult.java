package com.apexsoft.ysprj.applicants.payment.domain;

/**
 * Created by hanmomhanda on 15. 4. 11.
 */
public class CustomApplicationDocumentResult {

    private int applNo;
    private String fileYn;
    private String applId;

    public int getApplNo() {
        return applNo;
    }

    public void setApplNo( int applNo ) {
        this.applNo = applNo;
    }

    public String getFileYn() {
        return fileYn;
    }

    public void setFileYn( String fileYn ) {
        this.fileYn = fileYn;
    }

    public String getApplId() {
        return applId;
    }

    public void setApplId( String applId ) {
        this.applId = applId;
    }
}
