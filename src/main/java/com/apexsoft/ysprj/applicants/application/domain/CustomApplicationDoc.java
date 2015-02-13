package com.apexsoft.ysprj.applicants.application.domain;

/**
 * Created by hanmomhanda on 14. 9. 14.
 */
public class CustomApplicationDoc {

    private String docItemCode;
    private String itemName;
    private String itemNameXxen;
    private String docTypeCode;
    private String typeName;
    private String uploadYn;
    private String msgNo;
    private String chkCnd;

    public String getDocItemCode() {
        return docItemCode;
    }

    public void setDocItemCode(String docItemCode) {
        this.docItemCode = docItemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemNameXxen() {
        return itemNameXxen;
    }

    public void setItemNameXxen(String itemNameXxen) {
        this.itemNameXxen = itemNameXxen;
    }

    public String getDocTypeCode() {
        return docTypeCode;
    }

    public void setDocTypeCode(String docTypeCode) {
        this.docTypeCode = docTypeCode;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getUploadYn() {
        return uploadYn;
    }

    public void setUploadYn(String uploadYn) {
        this.uploadYn = uploadYn;
    }

    public String getMsgNo() {
        return msgNo;
    }

    public void setMsgNo(String msgNo) {
        this.msgNo = msgNo;
    }

    public String getChkCnd() {
        return chkCnd;
    }

    public void setChkCnd(String chkCnd) {
        this.chkCnd = chkCnd;
    }
}
