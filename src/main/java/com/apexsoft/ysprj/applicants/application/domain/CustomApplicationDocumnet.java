package com.apexsoft.ysprj.applicants.application.domain;

/**
 * Created by hanmomhanda on 14. 9. 14.
 */
public class CustomApplicationDocumnet extends ApplicationDocument{


    private String docItemNameXxen;

    @Override
    public String getDocItemNameXxen() {
        return docItemNameXxen;
    }

    @Override
    public void setDocItemNameXxen(String docItemNameXxen) {
        this.docItemNameXxen = docItemNameXxen;
    }
}
