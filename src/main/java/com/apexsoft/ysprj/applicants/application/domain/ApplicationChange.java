package com.apexsoft.ysprj.applicants.application.domain;

import java.util.Date;

public class ApplicationChange extends ApplicationChangeKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column APPL_CHG.APPL_NO
     *
     * @mbggenerated Fri Sep 12 20:12:04 KST 2014
     */
    private Integer applNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column APPL_CHG.APPL_CHG_CODE
     *
     * @mbggenerated Fri Sep 12 20:12:04 KST 2014
     */
    private String applChgCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column APPL_CHG.REQ_DAY
     *
     * @mbggenerated Fri Sep 12 20:12:04 KST 2014
     */
    private String reqDay;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column APPL_CHG.REQ_USER_ID
     *
     * @mbggenerated Fri Sep 12 20:12:04 KST 2014
     */
    private String reqUserId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column APPL_CHG.REQ_NAME
     *
     * @mbggenerated Fri Sep 12 20:12:04 KST 2014
     */
    private String reqName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column APPL_CHG.CHG_STS_CODE
     *
     * @mbggenerated Fri Sep 12 20:12:04 KST 2014
     */
    private String chgStsCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column APPL_CHG.ACT_DAY
     *
     * @mbggenerated Fri Sep 12 20:12:04 KST 2014
     */
    private String actDay;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column APPL_CHG.ACT_USER_ID
     *
     * @mbggenerated Fri Sep 12 20:12:04 KST 2014
     */
    private String actUserId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column APPL_CHG.CRE_ID
     *
     * @mbggenerated Fri Sep 12 20:12:04 KST 2014
     */
    private String creId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column APPL_CHG.CRE_DATE
     *
     * @mbggenerated Fri Sep 12 20:12:04 KST 2014
     */
    private Date creDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column APPL_CHG.MOD_ID
     *
     * @mbggenerated Fri Sep 12 20:12:04 KST 2014
     */
    private String modId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column APPL_CHG.MOD_DATE
     *
     * @mbggenerated Fri Sep 12 20:12:04 KST 2014
     */
    private Date modDate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column APPL_CHG.APPL_NO
     *
     * @return the value of APPL_CHG.APPL_NO
     *
     * @mbggenerated Fri Sep 12 20:12:04 KST 2014
     */
    public Integer getApplNo() {
        return applNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column APPL_CHG.APPL_NO
     *
     * @param applNo the value for APPL_CHG.APPL_NO
     *
     * @mbggenerated Fri Sep 12 20:12:04 KST 2014
     */
    public void setApplNo(Integer applNo) {
        this.applNo = applNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column APPL_CHG.APPL_CHG_CODE
     *
     * @return the value of APPL_CHG.APPL_CHG_CODE
     *
     * @mbggenerated Fri Sep 12 20:12:04 KST 2014
     */
    public String getApplChgCode() {
        return applChgCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column APPL_CHG.APPL_CHG_CODE
     *
     * @param applChgCode the value for APPL_CHG.APPL_CHG_CODE
     *
     * @mbggenerated Fri Sep 12 20:12:04 KST 2014
     */
    public void setApplChgCode(String applChgCode) {
        this.applChgCode = applChgCode == null ? null : applChgCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column APPL_CHG.REQ_DAY
     *
     * @return the value of APPL_CHG.REQ_DAY
     *
     * @mbggenerated Fri Sep 12 20:12:04 KST 2014
     */
    public String getReqDay() {
        return reqDay;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column APPL_CHG.REQ_DAY
     *
     * @param reqDay the value for APPL_CHG.REQ_DAY
     *
     * @mbggenerated Fri Sep 12 20:12:04 KST 2014
     */
    public void setReqDay(String reqDay) {
        this.reqDay = reqDay == null ? null : reqDay.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column APPL_CHG.REQ_USER_ID
     *
     * @return the value of APPL_CHG.REQ_USER_ID
     *
     * @mbggenerated Fri Sep 12 20:12:04 KST 2014
     */
    public String getReqUserId() {
        return reqUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column APPL_CHG.REQ_USER_ID
     *
     * @param reqUserId the value for APPL_CHG.REQ_USER_ID
     *
     * @mbggenerated Fri Sep 12 20:12:04 KST 2014
     */
    public void setReqUserId(String reqUserId) {
        this.reqUserId = reqUserId == null ? null : reqUserId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column APPL_CHG.REQ_NAME
     *
     * @return the value of APPL_CHG.REQ_NAME
     *
     * @mbggenerated Fri Sep 12 20:12:04 KST 2014
     */
    public String getReqName() {
        return reqName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column APPL_CHG.REQ_NAME
     *
     * @param reqName the value for APPL_CHG.REQ_NAME
     *
     * @mbggenerated Fri Sep 12 20:12:04 KST 2014
     */
    public void setReqName(String reqName) {
        this.reqName = reqName == null ? null : reqName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column APPL_CHG.CHG_STS_CODE
     *
     * @return the value of APPL_CHG.CHG_STS_CODE
     *
     * @mbggenerated Fri Sep 12 20:12:04 KST 2014
     */
    public String getChgStsCode() {
        return chgStsCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column APPL_CHG.CHG_STS_CODE
     *
     * @param chgStsCode the value for APPL_CHG.CHG_STS_CODE
     *
     * @mbggenerated Fri Sep 12 20:12:04 KST 2014
     */
    public void setChgStsCode(String chgStsCode) {
        this.chgStsCode = chgStsCode == null ? null : chgStsCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column APPL_CHG.ACT_DAY
     *
     * @return the value of APPL_CHG.ACT_DAY
     *
     * @mbggenerated Fri Sep 12 20:12:04 KST 2014
     */
    public String getActDay() {
        return actDay;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column APPL_CHG.ACT_DAY
     *
     * @param actDay the value for APPL_CHG.ACT_DAY
     *
     * @mbggenerated Fri Sep 12 20:12:04 KST 2014
     */
    public void setActDay(String actDay) {
        this.actDay = actDay == null ? null : actDay.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column APPL_CHG.ACT_USER_ID
     *
     * @return the value of APPL_CHG.ACT_USER_ID
     *
     * @mbggenerated Fri Sep 12 20:12:04 KST 2014
     */
    public String getActUserId() {
        return actUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column APPL_CHG.ACT_USER_ID
     *
     * @param actUserId the value for APPL_CHG.ACT_USER_ID
     *
     * @mbggenerated Fri Sep 12 20:12:04 KST 2014
     */
    public void setActUserId(String actUserId) {
        this.actUserId = actUserId == null ? null : actUserId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column APPL_CHG.CRE_ID
     *
     * @return the value of APPL_CHG.CRE_ID
     *
     * @mbggenerated Fri Sep 12 20:12:04 KST 2014
     */
    public String getCreId() {
        return creId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column APPL_CHG.CRE_ID
     *
     * @param creId the value for APPL_CHG.CRE_ID
     *
     * @mbggenerated Fri Sep 12 20:12:04 KST 2014
     */
    public void setCreId(String creId) {
        this.creId = creId == null ? null : creId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column APPL_CHG.CRE_DATE
     *
     * @return the value of APPL_CHG.CRE_DATE
     *
     * @mbggenerated Fri Sep 12 20:12:04 KST 2014
     */
    public Date getCreDate() {
        return creDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column APPL_CHG.CRE_DATE
     *
     * @param creDate the value for APPL_CHG.CRE_DATE
     *
     * @mbggenerated Fri Sep 12 20:12:04 KST 2014
     */
    public void setCreDate(Date creDate) {
        this.creDate = creDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column APPL_CHG.MOD_ID
     *
     * @return the value of APPL_CHG.MOD_ID
     *
     * @mbggenerated Fri Sep 12 20:12:04 KST 2014
     */
    public String getModId() {
        return modId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column APPL_CHG.MOD_ID
     *
     * @param modId the value for APPL_CHG.MOD_ID
     *
     * @mbggenerated Fri Sep 12 20:12:04 KST 2014
     */
    public void setModId(String modId) {
        this.modId = modId == null ? null : modId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column APPL_CHG.MOD_DATE
     *
     * @return the value of APPL_CHG.MOD_DATE
     *
     * @mbggenerated Fri Sep 12 20:12:04 KST 2014
     */
    public Date getModDate() {
        return modDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column APPL_CHG.MOD_DATE
     *
     * @param modDate the value for APPL_CHG.MOD_DATE
     *
     * @mbggenerated Fri Sep 12 20:12:04 KST 2014
     */
    public void setModDate(Date modDate) {
        this.modDate = modDate;
    }
}