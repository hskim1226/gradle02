package com.apexsoft.ysprj.applicants.application.domain;

import java.util.Date;

public class ApplicationETC {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column APPL_ETC.APPL_NO
     *
     * @mbggenerated Fri Sep 12 20:12:04 KST 2014
     */
    private Integer applNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column APPL_ETC.CRE_ID
     *
     * @mbggenerated Fri Sep 12 20:12:04 KST 2014
     */
    private String creId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column APPL_ETC.CRE_DATE
     *
     * @mbggenerated Fri Sep 12 20:12:04 KST 2014
     */
    private Date creDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column APPL_ETC.MOD_ID
     *
     * @mbggenerated Fri Sep 12 20:12:04 KST 2014
     */
    private String modId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column APPL_ETC.MOD_DATE
     *
     * @mbggenerated Fri Sep 12 20:12:04 KST 2014
     */
    private Date modDate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column APPL_ETC.APPL_NO
     *
     * @return the value of APPL_ETC.APPL_NO
     *
     * @mbggenerated Fri Sep 12 20:12:04 KST 2014
     */
    public Integer getApplNo() {
        return applNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column APPL_ETC.APPL_NO
     *
     * @param applNo the value for APPL_ETC.APPL_NO
     *
     * @mbggenerated Fri Sep 12 20:12:04 KST 2014
     */
    public void setApplNo(Integer applNo) {
        this.applNo = applNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column APPL_ETC.CRE_ID
     *
     * @return the value of APPL_ETC.CRE_ID
     *
     * @mbggenerated Fri Sep 12 20:12:04 KST 2014
     */
    public String getCreId() {
        return creId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column APPL_ETC.CRE_ID
     *
     * @param creId the value for APPL_ETC.CRE_ID
     *
     * @mbggenerated Fri Sep 12 20:12:04 KST 2014
     */
    public void setCreId(String creId) {
        this.creId = creId == null ? null : creId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column APPL_ETC.CRE_DATE
     *
     * @return the value of APPL_ETC.CRE_DATE
     *
     * @mbggenerated Fri Sep 12 20:12:04 KST 2014
     */
    public Date getCreDate() {
        return creDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column APPL_ETC.CRE_DATE
     *
     * @param creDate the value for APPL_ETC.CRE_DATE
     *
     * @mbggenerated Fri Sep 12 20:12:04 KST 2014
     */
    public void setCreDate(Date creDate) {
        this.creDate = creDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column APPL_ETC.MOD_ID
     *
     * @return the value of APPL_ETC.MOD_ID
     *
     * @mbggenerated Fri Sep 12 20:12:04 KST 2014
     */
    public String getModId() {
        return modId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column APPL_ETC.MOD_ID
     *
     * @param modId the value for APPL_ETC.MOD_ID
     *
     * @mbggenerated Fri Sep 12 20:12:04 KST 2014
     */
    public void setModId(String modId) {
        this.modId = modId == null ? null : modId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column APPL_ETC.MOD_DATE
     *
     * @return the value of APPL_ETC.MOD_DATE
     *
     * @mbggenerated Fri Sep 12 20:12:04 KST 2014
     */
    public Date getModDate() {
        return modDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column APPL_ETC.MOD_DATE
     *
     * @param modDate the value for APPL_ETC.MOD_DATE
     *
     * @mbggenerated Fri Sep 12 20:12:04 KST 2014
     */
    public void setModDate(Date modDate) {
        this.modDate = modDate;
    }
}