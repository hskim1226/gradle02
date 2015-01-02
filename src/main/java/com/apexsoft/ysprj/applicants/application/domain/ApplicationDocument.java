package com.apexsoft.ysprj.applicants.application.domain;

import java.util.Date;

public class ApplicationDocument extends ApplicationDocumentKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column APPL_DOC.DOC_TYPE_CODE
     *
     * @mbggenerated Thu Sep 18 18:37:48 KST 2014
     */
    private String docTypeCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column APPL_DOC.DOC_GRP
     *
     * @mbggenerated Thu Sep 18 18:37:48 KST 2014
     */
    private Integer docGrp;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column APPL_DOC.DOC_ITEM_CODE
     *
     * @mbggenerated Thu Sep 18 18:37:48 KST 2014
     */
    private String docItemCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column APPL_DOC.DOC_ITEM_NAME
     *
     * @mbggenerated Thu Sep 18 18:37:48 KST 2014
     */
    private String docItemName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column APPL_DOC.DOC_NAME
     *
     * @mbggenerated Thu Sep 18 18:37:48 KST 2014
     */
    private String docName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column APPL_DOC.FILE_EXT
     *
     * @mbggenerated Thu Sep 18 18:37:48 KST 2014
     */
    private String fileExt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column APPL_DOC.IMG_YN
     *
     * @mbggenerated Thu Sep 18 18:37:48 KST 2014
     */
    private String imgYn;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column APPL_DOC.FILE_PATH
     *
     * @mbggenerated Thu Sep 18 18:37:48 KST 2014
     */
    private String filePath;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column APPL_DOC.FILE_NAME
     *
     * @mbggenerated Thu Sep 18 18:37:48 KST 2014
     */
    private String fileName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column APPL_DOC.ORG_FILE_NAME
     *
     * @mbggenerated Thu Sep 18 18:37:48 KST 2014
     */
    private String orgFileName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column APPL_DOC.CRE_ID
     *
     * @mbggenerated Thu Sep 18 18:37:48 KST 2014
     */
    private String creId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column APPL_DOC.CRE_DATE
     *
     * @mbggenerated Thu Sep 18 18:37:48 KST 2014
     */
    private Date creDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column APPL_DOC.MOD_ID
     *
     * @mbggenerated Thu Sep 18 18:37:48 KST 2014
     */
    private String modId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column APPL_DOC.MOD_DATE
     *
     * @mbggenerated Thu Sep 18 18:37:48 KST 2014
     */
    private Date modDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column APPL_DOC.DOC_ITEM_NAME_XXEN
     *
     * @mbggenerated Thu Sep 18 18:37:48 KST 2014
     */
    private String docItemNameXxen;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column APPL_DOC.DOC_GRP_NAME
     *
     * @mbggenerated Thu Sep 18 18:37:48 KST 2014
     */
    private String docGrpName;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column APPL_DOC.DOC_TYPE_CODE
     *
     * @return the value of APPL_DOC.DOC_TYPE_CODE
     *
     * @mbggenerated Thu Sep 18 18:37:48 KST 2014
     */
    public String getDocTypeCode() {
        return docTypeCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column APPL_DOC.DOC_TYPE_CODE
     *
     * @param docTypeCode the value for APPL_DOC.DOC_TYPE_CODE
     *
     * @mbggenerated Thu Sep 18 18:37:48 KST 2014
     */
    public void setDocTypeCode(String docTypeCode) {
        this.docTypeCode = docTypeCode == null ? null : docTypeCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column APPL_DOC.DOC_GRP
     *
     * @return the value of APPL_DOC.DOC_GRP
     *
     * @mbggenerated Thu Sep 18 18:37:48 KST 2014
     */
    public Integer getDocGrp() {
        return docGrp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column APPL_DOC.DOC_GRP
     *
     * @param docGrp the value for APPL_DOC.DOC_GRP
     *
     * @mbggenerated Thu Sep 18 18:37:48 KST 2014
     */
    public void setDocGrp(Integer docGrp) {
        this.docGrp = docGrp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column APPL_DOC.DOC_ITEM_CODE
     *
     * @return the value of APPL_DOC.DOC_ITEM_CODE
     *
     * @mbggenerated Thu Sep 18 18:37:48 KST 2014
     */
    public String getDocItemCode() {
        return docItemCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column APPL_DOC.DOC_ITEM_CODE
     *
     * @param docItemCode the value for APPL_DOC.DOC_ITEM_CODE
     *
     * @mbggenerated Thu Sep 18 18:37:48 KST 2014
     */
    public void setDocItemCode(String docItemCode) {
        this.docItemCode = docItemCode == null ? null : docItemCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column APPL_DOC.DOC_ITEM_NAME
     *
     * @return the value of APPL_DOC.DOC_ITEM_NAME
     *
     * @mbggenerated Thu Sep 18 18:37:48 KST 2014
     */
    public String getDocItemName() {
        return docItemName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column APPL_DOC.DOC_ITEM_NAME
     *
     * @param docItemName the value for APPL_DOC.DOC_ITEM_NAME
     *
     * @mbggenerated Thu Sep 18 18:37:48 KST 2014
     */
    public void setDocItemName(String docItemName) {
        this.docItemName = docItemName == null ? null : docItemName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column APPL_DOC.DOC_NAME
     *
     * @return the value of APPL_DOC.DOC_NAME
     *
     * @mbggenerated Thu Sep 18 18:37:48 KST 2014
     */
    public String getDocName() {
        return docName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column APPL_DOC.DOC_NAME
     *
     * @param docName the value for APPL_DOC.DOC_NAME
     *
     * @mbggenerated Thu Sep 18 18:37:48 KST 2014
     */
    public void setDocName(String docName) {
        this.docName = docName == null ? null : docName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column APPL_DOC.FILE_EXT
     *
     * @return the value of APPL_DOC.FILE_EXT
     *
     * @mbggenerated Thu Sep 18 18:37:48 KST 2014
     */
    public String getFileExt() {
        return fileExt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column APPL_DOC.FILE_EXT
     *
     * @param fileExt the value for APPL_DOC.FILE_EXT
     *
     * @mbggenerated Thu Sep 18 18:37:48 KST 2014
     */
    public void setFileExt(String fileExt) {
        this.fileExt = fileExt == null ? null : fileExt.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column APPL_DOC.IMG_YN
     *
     * @return the value of APPL_DOC.IMG_YN
     *
     * @mbggenerated Thu Sep 18 18:37:48 KST 2014
     */
    public String getImgYn() {
        return imgYn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column APPL_DOC.IMG_YN
     *
     * @param imgYn the value for APPL_DOC.IMG_YN
     *
     * @mbggenerated Thu Sep 18 18:37:48 KST 2014
     */
    public void setImgYn(String imgYn) {
        this.imgYn = imgYn == null ? null : imgYn.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column APPL_DOC.FILE_PATH
     *
     * @return the value of APPL_DOC.FILE_PATH
     *
     * @mbggenerated Thu Sep 18 18:37:48 KST 2014
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column APPL_DOC.FILE_PATH
     *
     * @param filePath the value for APPL_DOC.FILE_PATH
     *
     * @mbggenerated Thu Sep 18 18:37:48 KST 2014
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column APPL_DOC.FILE_NAME
     *
     * @return the value of APPL_DOC.FILE_NAME
     *
     * @mbggenerated Thu Sep 18 18:37:48 KST 2014
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column APPL_DOC.FILE_NAME
     *
     * @param fileName the value for APPL_DOC.FILE_NAME
     *
     * @mbggenerated Thu Sep 18 18:37:48 KST 2014
     */
    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column APPL_DOC.ORG_FILE_NAME
     *
     * @return the value of APPL_DOC.ORG_FILE_NAME
     *
     * @mbggenerated Thu Sep 18 18:37:48 KST 2014
     */
    public String getOrgFileName() {
        return orgFileName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column APPL_DOC.ORG_FILE_NAME
     *
     * @param orgFileName the value for APPL_DOC.ORG_FILE_NAME
     *
     * @mbggenerated Thu Sep 18 18:37:48 KST 2014
     */
    public void setOrgFileName(String orgFileName) {
        this.orgFileName = orgFileName == null ? null : orgFileName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column APPL_DOC.CRE_ID
     *
     * @return the value of APPL_DOC.CRE_ID
     *
     * @mbggenerated Thu Sep 18 18:37:48 KST 2014
     */
    public String getCreId() {
        return creId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column APPL_DOC.CRE_ID
     *
     * @param creId the value for APPL_DOC.CRE_ID
     *
     * @mbggenerated Thu Sep 18 18:37:48 KST 2014
     */
    public void setCreId(String creId) {
        this.creId = creId == null ? null : creId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column APPL_DOC.CRE_DATE
     *
     * @return the value of APPL_DOC.CRE_DATE
     *
     * @mbggenerated Thu Sep 18 18:37:48 KST 2014
     */
    public Date getCreDate() {
        return creDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column APPL_DOC.CRE_DATE
     *
     * @param creDate the value for APPL_DOC.CRE_DATE
     *
     * @mbggenerated Thu Sep 18 18:37:48 KST 2014
     */
    public void setCreDate(Date creDate) {
        this.creDate = creDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column APPL_DOC.MOD_ID
     *
     * @return the value of APPL_DOC.MOD_ID
     *
     * @mbggenerated Thu Sep 18 18:37:48 KST 2014
     */
    public String getModId() {
        return modId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column APPL_DOC.MOD_ID
     *
     * @param modId the value for APPL_DOC.MOD_ID
     *
     * @mbggenerated Thu Sep 18 18:37:48 KST 2014
     */
    public void setModId(String modId) {
        this.modId = modId == null ? null : modId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column APPL_DOC.MOD_DATE
     *
     * @return the value of APPL_DOC.MOD_DATE
     *
     * @mbggenerated Thu Sep 18 18:37:48 KST 2014
     */
    public Date getModDate() {
        return modDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column APPL_DOC.MOD_DATE
     *
     * @param modDate the value for APPL_DOC.MOD_DATE
     *
     * @mbggenerated Thu Sep 18 18:37:48 KST 2014
     */
    public void setModDate(Date modDate) {
        this.modDate = modDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column APPL_DOC.DOC_ITEM_NAME_XXEN
     *
     * @return the value of APPL_DOC.DOC_ITEM_NAME_XXEN
     *
     * @mbggenerated Thu Sep 18 18:37:48 KST 2014
     */
    public String getDocItemNameXxen() {
        return docItemNameXxen;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column APPL_DOC.DOC_ITEM_NAME_XXEN
     *
     * @param docItemNameXxen the value for APPL_DOC.DOC_ITEM_NAME_XXEN
     *
     * @mbggenerated Thu Sep 18 18:37:48 KST 2014
     */
    public void setDocItemNameXxen(String docItemNameXxen) {
        this.docItemNameXxen = docItemNameXxen == null ? null : docItemNameXxen.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column APPL_DOC.DOC_GRP_NAME
     *
     * @return the value of APPL_DOC.DOC_GRP_NAME
     *
     * @mbggenerated Thu Sep 18 18:37:48 KST 2014
     */
    public String getDocGrpName() {
        return docGrpName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column APPL_DOC.DOC_GRP_NAME
     *
     * @param docGrpName the value for APPL_DOC.DOC_GRP_NAME
     *
     * @mbggenerated Thu Sep 18 18:37:48 KST 2014
     */
    public void setDocGrpName(String docGrpName) {
        this.docGrpName = docGrpName == null ? null : docGrpName.trim();
    }
}