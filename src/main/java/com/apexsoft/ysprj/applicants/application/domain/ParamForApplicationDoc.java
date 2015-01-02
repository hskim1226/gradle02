package com.apexsoft.ysprj.applicants.application.domain;

import java.util.List;

/**
 * Created by hanmomhanda on 14. 9. 14.
 */
public class ParamForApplicationDoc {

    private String admsNo;
    private String docTypeCode;
    private List<String> docItemCodeList;

    public String getAdmsNo() {
        return admsNo;
    }

    public void setAdmsNo(String admsNo) {
        this.admsNo = admsNo;
    }

    public String getDocTypeCode() {
        return docTypeCode;
    }

    public void setDocTypeCode(String docTypeCode) {
        this.docTypeCode = docTypeCode;
    }

    public List<String> getDocItemCodeList() {
        return docItemCodeList;
    }

    public void setDocItemCodeList(List<String> docItemCodeList) {
        this.docItemCodeList = docItemCodeList;
    }
}
