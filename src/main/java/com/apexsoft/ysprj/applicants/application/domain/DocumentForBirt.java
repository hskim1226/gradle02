package com.apexsoft.ysprj.applicants.application.domain;

/**
 * Created by hanmomhanda on 15. 2. 22.
 */
public class DocumentForBirt {

    private String docItemName;
    private int pages;

    public DocumentForBirt() {

    }

    public DocumentForBirt(String docItemName, int pages) {
        this.docItemName = docItemName;
        this.pages = pages;
    }

    public String getDocItemName() {
        return docItemName;
    }

    public void setDocItemName(String docItemName) {
        this.docItemName = docItemName;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}
