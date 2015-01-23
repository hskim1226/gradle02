package com.apexsoft.ysprj.applicants.application.domain;

public class TotalApplicationDocument extends ApplicationDocument{

    private String grpLevel;
    private String docTypeCode;
    private String docItemCode;
    private String upCodeGrp;
    private String upCode;
    private String selCodeGrp;
    private String lastYn;
    private String mdtYn;
    private String uploadYn;
    private int sendCnt;
    private String orgnSendYn;
    private String chkCnd;
    private String tmpltYn;
    private String msgNo;

    public String getGrpLevel() {
        return grpLevel;
    }

    public void setGrpLevel(String grpLevel) {
        this.grpLevel = grpLevel;
    }

    @Override
    public String getDocTypeCode() {
        return docTypeCode;
    }

    @Override
    public void setDocTypeCode(String docTypeCode) {
        this.docTypeCode = docTypeCode;
    }

    @Override
    public String getDocItemCode() {
        return docItemCode;
    }

    @Override
    public void setDocItemCode(String docItemCode) {
        this.docItemCode = docItemCode;
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

    public void setAppDocInfo( ApplicationDocument appDoc){
        setDocTypeCode(appDoc.getDocTypeCode());//그룹 코드
        setDocGrp(appDoc.getDocGrp());//세부 시퀀스
        setDocItemCode(appDoc.getDocItemCode());//문서코드
        setDocItemName(appDoc.getDocItemName());//문서명
        setDocName(appDoc.getDocName());//아마도 세부 시퀀스의 설명?
        setFileExt(appDoc.getFileExt());
        setImgYn(appDoc.getImgYn());
        setFilePath(appDoc.getFilePath());
        setFileName(appDoc.getFileName());
        setOrgFileName(appDoc.getOrgFileName());
        setDocItemNameXxen(appDoc.getDocItemNameXxen());
        setDocGrpName(appDoc.getDocGrpName());
    }

    public void setComMandInfo( CommonMandatory comMand){
        setDocTypeCode    (comMand.getGrpCode());//그룹코드
        setDocItemCode    (comMand.getItemCode());//문서코드
        setDocItemName    (comMand.getItemName());//문서명

        setGrpCode     (comMand.getGrpCode());//그룹코드
        setGrpName     (comMand.getGrpName());//그룹명 (필요없을 듯)
       // setItemGrpCode (comMand.getItemGrpCode());//문서 그룹 코드
        setItemGrpName (comMand.getItemGrpName());//문서 그룹 명 (필요없을 듯)
        setItemCode    (comMand.getItemCode());//문서코드
        setItemName    (comMand.getItemName());//문서명
        setChnYn       (comMand.getChnYn());
        setLastYn      (comMand.getLastYn());
        setMdtYn       (comMand.getMdtYn());
        setUploadYn    (comMand.getUploadYn());
        setSendCnt     (comMand.getSendCnt());
        setOrgnSendYn  (comMand.getOrgnSendYn());
        setTmpltYn     (comMand.getTmpltYn());
        setMsgNo       (comMand.getMsgNo());
    }

}