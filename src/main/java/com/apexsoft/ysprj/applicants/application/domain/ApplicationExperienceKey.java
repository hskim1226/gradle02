package com.apexsoft.ysprj.applicants.application.domain;

public class ApplicationExperienceKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column APPL_EXPR.APPL_NO
     *
     * @mbggenerated Fri Sep 12 19:50:54 KST 2014
     */
    private Integer applNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column APPL_EXPR.EXPR_SEQ
     *
     * @mbggenerated Fri Sep 12 19:50:54 KST 2014
     */
    private Integer exprSeq;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column APPL_EXPR.APPL_NO
     *
     * @return the value of APPL_EXPR.APPL_NO
     *
     * @mbggenerated Fri Sep 12 19:50:54 KST 2014
     */
    public Integer getApplNo() {
        return applNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column APPL_EXPR.APPL_NO
     *
     * @param applNo the value for APPL_EXPR.APPL_NO
     *
     * @mbggenerated Fri Sep 12 19:50:54 KST 2014
     */
    public void setApplNo(Integer applNo) {
        this.applNo = applNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column APPL_EXPR.EXPR_SEQ
     *
     * @return the value of APPL_EXPR.EXPR_SEQ
     *
     * @mbggenerated Fri Sep 12 19:50:54 KST 2014
     */
    public Integer getExprSeq() {
        return exprSeq;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column APPL_EXPR.EXPR_SEQ
     *
     * @param exprSeq the value for APPL_EXPR.EXPR_SEQ
     *
     * @mbggenerated Fri Sep 12 19:50:54 KST 2014
     */
    public void setExprSeq(Integer exprSeq) {
        this.exprSeq = exprSeq;
    }
}