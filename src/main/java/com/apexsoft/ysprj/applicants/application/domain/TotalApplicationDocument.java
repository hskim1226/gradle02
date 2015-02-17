package com.apexsoft.ysprj.applicants.application.domain;

public class TotalApplicationDocument extends ApplicationDocument{

    //화면 표시용 그룹정보
    private String grpLabel;
    private boolean fileUploadFg = false;
    private boolean displayGrpFg = false;
    private boolean checkedFg;

    //필수요건 조회용 key 정보
    private String admsNo;
    private int admsCorsNo;
    private String detlMajCode;
    private String admsCodeGrp;
    private String admsCode;


    //문서요건 정보
    private String grpLevel;
    private String docItemGrp;
//    private String docTypeCode; // ApplicationDocument에 있으므로 제외
//    private String docItemCode; // ApplicationDocument에 있으므로 제외
    private String upCodeGrp;
    private String upCode;
    private String selCodeGrp;
    private String lastYn;
    private String mdtYn;
    private String multiYn;
    private String uploadYn;
    private int sendCnt;
    private String orgnSendYn;
    private String chkCnd;
    private String tmpltYn;
    private String msgNo;
    private String notInYn;



    public boolean isCheckedFg() {
        return checkedFg;
    }

    public void setCheckedFg(boolean checkedFg) {
        this.checkedFg = checkedFg;
    }

    public boolean isFileUploadFg() {
        return fileUploadFg;
    }

    public void setFileUploadFg(boolean fileUploadFg) {
        this.fileUploadFg = fileUploadFg;
    }

    public String getGrpLevel() {
        return grpLevel;
    }

    public void setGrpLevel(String grpLevel) {
        this.grpLevel = grpLevel;
    }

    public String getUpCodeGrp() {
        return upCodeGrp;
    }

    public void setUpCodeGrp(String upCodeGrp) {
        this.upCodeGrp = upCodeGrp;
    }

    public String getUpCode() {
        return upCode;
    }

    public void setUpCode(String upCode) {
        this.upCode = upCode;
    }

    public String getSelCodeGrp() {
        return selCodeGrp;
    }

    public void setSelCodeGrp(String selCodeGrp) {
        this.selCodeGrp = selCodeGrp;
    }

    public String getLastYn() {
        return lastYn;
    }

    public void setLastYn(String lastYn) {
        this.lastYn = lastYn;
    }

    public String getMdtYn() {
        return mdtYn;
    }

    public void setMdtYn(String mdtYn) {
        this.mdtYn = mdtYn;
    }

    public String getUploadYn() {
        return uploadYn;
    }

    public void setUploadYn(String uploadYn) {
        this.uploadYn = uploadYn;
    }

    public int getSendCnt() {
        return sendCnt;
    }

    public void setSendCnt(int sendCnt) {
        this.sendCnt = sendCnt;
    }

    public String getOrgnSendYn() {
        return orgnSendYn;
    }

    public void setOrgnSendYn(String orgnSendYn) {
        this.orgnSendYn = orgnSendYn;
    }

    public String getChkCnd() {
        return chkCnd;
    }

    public void setChkCnd(String chkCnd) {
        this.chkCnd = chkCnd;
    }

    public String getTmpltYn() {
        return tmpltYn;
    }

    public void setTmpltYn(String tmpltYn) {
        this.tmpltYn = tmpltYn;
    }

    public String getMsgNo() {
        return msgNo;
    }

    public void setMsgNo(String msgNo) {
        this.msgNo = msgNo;
    }

    public String getGrpLabel() {
        return grpLabel;
    }

    public void setGrpLabel(String grpLabel) {
        this.grpLabel = grpLabel;
    }

    public boolean isDisplayGrpFg() {       return displayGrpFg;   }

    public void setDisplayGrpFg(boolean displayGrpFg) {
        this.displayGrpFg = displayGrpFg;
    }

    public String getDocItemGrp() {
        return docItemGrp;
    }

    public void setDocItemGrp(String docItemGrp) {
        this.docItemGrp = docItemGrp;
    }


    public String getNotInYn() {       return notInYn;   }

    public void setNotInYn(String notInYn) {       this.notInYn = notInYn;   }

    public TotalApplicationDocument(){}


    public String getAdmsNo() {
        return admsNo;
    }

    public void setAdmsNo(String admsNo) {
        this.admsNo = admsNo;
    }

    public int getAdmsCorsNo() {
        return admsCorsNo;
    }

    public void setAdmsCorsNo(int admsCorsNo) {
        this.admsCorsNo = admsCorsNo;
    }

    public String getDetlMajCode() {
        return detlMajCode;
    }

    public void setDetlMajCode(String detlMajCode) {
        this.detlMajCode = detlMajCode;
    }

    public String getAdmsCodeGrp() {
        return admsCodeGrp;
    }

    public void setAdmsCodeGrp(String admsCodeGrp) {
        this.admsCodeGrp = admsCodeGrp;
    }

    public String getAdmsCode() {
        return admsCode;
    }

    public void setAdmsCode(String admsCode) {
        this.admsCode = admsCode;
    }


    public String getMultiYn() {
        return multiYn;
    }

    public void setMultiYn(String multiYn) {
        this.multiYn = multiYn;
    }

//    @Override
//    public String getDocTypeCode() {
//        return docTypeCode;
//    }
//
//    @Override
//    public void setDocTypeCode(String docTypeCode) {
//        this.docTypeCode = docTypeCode;
//    }
//
//    @Override
//    public String getDocItemCode() {
//        return docItemCode;
//    }
//
//    @Override
//    public void setDocItemCode(String docItemCode) {
//        this.docItemCode = docItemCode;
//    }
}