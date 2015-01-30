package com.apexsoft.ysprj.applicants.application.domain;

public class TotalApplicationLanguage extends CustomApplicationLanguage{

    // 어학 항목 별 사용자 CUD 상태
    private boolean checkedFg;

    //첨부파일 정보
    private int docSeq;
    private String docTypeCode;
    private int docGrp;
    private String docItemCode;
    private String docItemName;
    private String docName;

    //이하는 파일 업로드 할때만 사용

    private String fileExt;
    private String imgYn;
    private String filePath;
    private String fileName;
    private String orgFileName;
    private String docItemNameXxen;

    //필요없는 필드 DB삭제예정
    private String docGrpName;

    //파일 업로드 여부
    private boolean fileUploadFg = false;

    public boolean isCheckedFg() {
        return checkedFg;
    }

    public void setCheckedFg(boolean checkedFg) {
        this.checkedFg = checkedFg;
    }

    public int getDocSeq() {
        return docSeq;
    }

    public void setDocSeq(int docSeq) {
        this.docSeq = docSeq;
    }

    public int getDocGrp() {
        return docGrp;
    }

    public void setDocGrp(int docGrp) {
        this.docGrp = docGrp;
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

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public boolean isFileUploadFg() {
        return fileUploadFg;
    }

    public void setFileUploadFg(boolean fileUploadFg) {
        this.fileUploadFg = fileUploadFg;
    }

    public String getDocTypeCode() {
        return docTypeCode;
    }

    public void setDocTypeCode(String docTypeCode) {
        this.docTypeCode = docTypeCode;
    }

    public String getFileExt() {
        return fileExt;
    }

    public void setFileExt(String fileExt) {
        this.fileExt = fileExt;
    }

    public String getImgYn() {
        return imgYn;
    }

    public void setImgYn(String imgYn) {
        this.imgYn = imgYn;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getOrgFileName() {
        return orgFileName;
    }

    public void setOrgFileName(String orgFileName) {
        this.orgFileName = orgFileName;
    }

    public String getDocItemNameXxen() {
        return docItemNameXxen;
    }

    public void setDocItemNameXxen(String docItemNameXxen) {
        this.docItemNameXxen = docItemNameXxen;
    }

    public String getDocGrpName() {
        return docGrpName;
    }

    public void setDocGrpName(String docGrpName) {
        this.docGrpName = docGrpName;
    }
}