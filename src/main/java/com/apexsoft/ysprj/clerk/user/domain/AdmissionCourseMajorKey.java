package com.apexsoft.ysprj.clerk.user.domain;

public class AdmissionCourseMajorKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ADMS_CORS_MAJ.ADMS_CORS_NO
     *
     * @mbggenerated Fri Sep 12 19:41:34 KST 2014
     */
    private Integer admsCorsNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ADMS_CORS_MAJ.DETL_MAJ_CODE
     *
     * @mbggenerated Fri Sep 12 19:41:34 KST 2014
     */
    private String detlMajCode;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ADMS_CORS_MAJ.ADMS_CORS_NO
     *
     * @return the value of ADMS_CORS_MAJ.ADMS_CORS_NO
     *
     * @mbggenerated Fri Sep 12 19:41:34 KST 2014
     */
    public Integer getAdmsCorsNo() {
        return admsCorsNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ADMS_CORS_MAJ.ADMS_CORS_NO
     *
     * @param admsCorsNo the value for ADMS_CORS_MAJ.ADMS_CORS_NO
     *
     * @mbggenerated Fri Sep 12 19:41:34 KST 2014
     */
    public void setAdmsCorsNo(Integer admsCorsNo) {
        this.admsCorsNo = admsCorsNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ADMS_CORS_MAJ.DETL_MAJ_CODE
     *
     * @return the value of ADMS_CORS_MAJ.DETL_MAJ_CODE
     *
     * @mbggenerated Fri Sep 12 19:41:34 KST 2014
     */
    public String getDetlMajCode() {
        return detlMajCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ADMS_CORS_MAJ.DETL_MAJ_CODE
     *
     * @param detlMajCode the value for ADMS_CORS_MAJ.DETL_MAJ_CODE
     *
     * @mbggenerated Fri Sep 12 19:41:34 KST 2014
     */
    public void setDetlMajCode(String detlMajCode) {
        this.detlMajCode = detlMajCode == null ? null : detlMajCode.trim();
    }
}