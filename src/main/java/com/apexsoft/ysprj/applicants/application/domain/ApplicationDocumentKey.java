package com.apexsoft.ysprj.applicants.application.domain;

public class ApplicationDocumentKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column APPL_DOC.APPL_NO
     *
     * @mbggenerated Wed Jan 28 17:22:47 KST 2015
     */
    private Integer applNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column APPL_DOC.DOC_SEQ
     *
     * @mbggenerated Wed Jan 28 17:22:47 KST 2015
     */
    private Integer docSeq;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column APPL_DOC.APPL_NO
     *
     * @return the value of APPL_DOC.APPL_NO
     *
     * @mbggenerated Wed Jan 28 17:22:47 KST 2015
     */
    public Integer getApplNo() {
        return applNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column APPL_DOC.APPL_NO
     *
     * @param applNo the value for APPL_DOC.APPL_NO
     *
     * @mbggenerated Wed Jan 28 17:22:47 KST 2015
     */
    public void setApplNo(Integer applNo) {
        this.applNo = applNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column APPL_DOC.DOC_SEQ
     *
     * @return the value of APPL_DOC.DOC_SEQ
     *
     * @mbggenerated Wed Jan 28 17:22:47 KST 2015
     */
    public Integer getDocSeq() {
        return docSeq;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column APPL_DOC.DOC_SEQ
     *
     * @param docSeq the value for APPL_DOC.DOC_SEQ
     *
     * @mbggenerated Wed Jan 28 17:22:47 KST 2015
     */
    public void setDocSeq(Integer docSeq) {
        this.docSeq = docSeq;
    }
}