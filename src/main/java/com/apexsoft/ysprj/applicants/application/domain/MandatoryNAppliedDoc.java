package com.apexsoft.ysprj.applicants.application.domain;

public class MandatoryNAppliedDoc extends CommonMandatory{


    public void setAppDocInfo( ApplicationDocument appDoc){
        setDocTypeCode(appDoc.getDocTypeCode());
        setDocGrp(appDoc.getDocGrp());
        setDocItemCode(appDoc.getDocItemCode());
        setDocItemName(appDoc.getDocItemName());
        setDocName(appDoc.getDocName());
        setFileExt(appDoc.getFileExt());
        setImgYn(appDoc.getImgYn());
        setFilePath(appDoc.getFilePath());
        setFileName(appDoc.getFileName());
        setOrgFileName(appDoc.getOrgFileName());
        setDocItemNameXxen(appDoc.getDocItemNameXxen());
        setDocGrpName(appDoc.getDocGrpName());
    }

    public void setComMandInfo( CommonMandatory comMand){
        setGrpCode     (comMand.getGrpCode());
        setGrpName     (comMand.getGrpName());
        setItemGrpCode (comMand.getItemGrpCode());
        setItemGrpName (comMand.getItemGrpName());
        setItemCode    (comMand.getItemCode());
        setItemName    (comMand.getItemName());
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