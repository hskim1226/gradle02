package com.apexsoft.ysprj.applicants.admission.domain;

public class AdmissionMandatoryKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ADMS_MDT.ADMS_NO
     *
     * @mbggenerated Fri Sep 12 19:38:08 KST 2014
     */
    private String admsNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ADMS_MDT.FORN_TYPE_CODE
     *
     * @mbggenerated Fri Sep 12 19:38:08 KST 2014
     */
    private String fornTypeCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ADMS_MDT.DOC_ITEM_CODE
     *
     * @mbggenerated Fri Sep 12 19:38:08 KST 2014
     */
    private String docItemCode;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ADMS_MDT.ADMS_NO
     *
     * @return the value of ADMS_MDT.ADMS_NO
     *
     * @mbggenerated Fri Sep 12 19:38:08 KST 2014
     */
    public String getAdmsNo() {
        return admsNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ADMS_MDT.ADMS_NO
     *
     * @param admsNo the value for ADMS_MDT.ADMS_NO
     *
     * @mbggenerated Fri Sep 12 19:38:08 KST 2014
     */
    public void setAdmsNo(String admsNo) {
        this.admsNo = admsNo == null ? null : admsNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ADMS_MDT.FORN_TYPE_CODE
     *
     * @return the value of ADMS_MDT.FORN_TYPE_CODE
     *
     * @mbggenerated Fri Sep 12 19:38:08 KST 2014
     */
    public String getFornTypeCode() {
        return fornTypeCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ADMS_MDT.FORN_TYPE_CODE
     *
     * @param fornTypeCode the value for ADMS_MDT.FORN_TYPE_CODE
     *
     * @mbggenerated Fri Sep 12 19:38:08 KST 2014
     */
    public void setFornTypeCode(String fornTypeCode) {
        this.fornTypeCode = fornTypeCode == null ? null : fornTypeCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ADMS_MDT.DOC_ITEM_CODE
     *
     * @return the value of ADMS_MDT.DOC_ITEM_CODE
     *
     * @mbggenerated Fri Sep 12 19:38:08 KST 2014
     */
    public String getDocItemCode() {
        return docItemCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ADMS_MDT.DOC_ITEM_CODE
     *
     * @param docItemCode the value for ADMS_MDT.DOC_ITEM_CODE
     *
     * @mbggenerated Fri Sep 12 19:38:08 KST 2014
     */
    public void setDocItemCode(String docItemCode) {
        this.docItemCode = docItemCode == null ? null : docItemCode.trim();
    }
}