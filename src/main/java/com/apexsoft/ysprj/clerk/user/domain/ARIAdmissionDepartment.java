package com.apexsoft.ysprj.clerk.user.domain;

import java.util.Date;

public class ARIAdmissionDepartment extends ARIAdmissionDepartmentKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ARI_ADMS_DEPT.CRE_ID
     *
     * @mbggenerated Mon Aug 25 21:02:55 KST 2014
     */
    private String creId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ARI_ADMS_DEPT.CRE_DATE
     *
     * @mbggenerated Mon Aug 25 21:02:55 KST 2014
     */
    private Date creDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ARI_ADMS_DEPT.MOD_ID
     *
     * @mbggenerated Mon Aug 25 21:02:55 KST 2014
     */
    private String modId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ARI_ADMS_DEPT.MOD_DATE
     *
     * @mbggenerated Mon Aug 25 21:02:55 KST 2014
     */
    private Date modDate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ARI_ADMS_DEPT.CRE_ID
     *
     * @return the value of ARI_ADMS_DEPT.CRE_ID
     *
     * @mbggenerated Mon Aug 25 21:02:55 KST 2014
     */
    public String getCreId() {
        return creId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ARI_ADMS_DEPT.CRE_ID
     *
     * @param creId the value for ARI_ADMS_DEPT.CRE_ID
     *
     * @mbggenerated Mon Aug 25 21:02:55 KST 2014
     */
    public void setCreId(String creId) {
        this.creId = creId == null ? null : creId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ARI_ADMS_DEPT.CRE_DATE
     *
     * @return the value of ARI_ADMS_DEPT.CRE_DATE
     *
     * @mbggenerated Mon Aug 25 21:02:55 KST 2014
     */
    public Date getCreDate() {
        return creDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ARI_ADMS_DEPT.CRE_DATE
     *
     * @param creDate the value for ARI_ADMS_DEPT.CRE_DATE
     *
     * @mbggenerated Mon Aug 25 21:02:55 KST 2014
     */
    public void setCreDate(Date creDate) {
        this.creDate = creDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ARI_ADMS_DEPT.MOD_ID
     *
     * @return the value of ARI_ADMS_DEPT.MOD_ID
     *
     * @mbggenerated Mon Aug 25 21:02:55 KST 2014
     */
    public String getModId() {
        return modId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ARI_ADMS_DEPT.MOD_ID
     *
     * @param modId the value for ARI_ADMS_DEPT.MOD_ID
     *
     * @mbggenerated Mon Aug 25 21:02:55 KST 2014
     */
    public void setModId(String modId) {
        this.modId = modId == null ? null : modId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ARI_ADMS_DEPT.MOD_DATE
     *
     * @return the value of ARI_ADMS_DEPT.MOD_DATE
     *
     * @mbggenerated Mon Aug 25 21:02:55 KST 2014
     */
    public Date getModDate() {
        return modDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ARI_ADMS_DEPT.MOD_DATE
     *
     * @param modDate the value for ARI_ADMS_DEPT.MOD_DATE
     *
     * @mbggenerated Mon Aug 25 21:02:55 KST 2014
     */
    public void setModDate(Date modDate) {
        this.modDate = modDate;
    }
}