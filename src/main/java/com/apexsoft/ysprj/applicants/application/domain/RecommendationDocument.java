package com.apexsoft.ysprj.applicants.application.domain;

/**
 * Created by hanmomhanda on 15. 9. 17.
 */
public class RecommendationDocument {
    private String docTypeCode;
    private String docItemCode;
    private String docItemName;
    private String docItemNameXxen;

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

    public String getDocItemName() {
        return docItemName;
    }

    public void setDocItemName(String docItemName) {
        this.docItemName = docItemName;
    }

    public String getDocItemNameXxen() {
        return docItemNameXxen;
    }

    public void setDocItemNameXxen(String docItemNameXxen) {
        this.docItemNameXxen = docItemNameXxen;
    }
}
