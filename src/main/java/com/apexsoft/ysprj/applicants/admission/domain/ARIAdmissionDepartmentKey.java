package com.apexsoft.ysprj.applicants.admission.domain;

public class ARIAdmissionDepartmentKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ARI_ADMS_DEPT.ARI_INST_CODE
     *
     * @mbggenerated Fri Sep 12 19:38:08 KST 2014
     */
    private String ariInstCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ARI_ADMS_DEPT.ADMS_NO
     *
     * @mbggenerated Fri Sep 12 19:38:08 KST 2014
     */
    private String admsNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ARI_ADMS_DEPT.DEPT_CODE
     *
     * @mbggenerated Fri Sep 12 19:38:08 KST 2014
     */
    private String deptCode;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ARI_ADMS_DEPT.ARI_INST_CODE
     *
     * @return the value of ARI_ADMS_DEPT.ARI_INST_CODE
     *
     * @mbggenerated Fri Sep 12 19:38:08 KST 2014
     */
    public String getAriInstCode() {
        return ariInstCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ARI_ADMS_DEPT.ARI_INST_CODE
     *
     * @param ariInstCode the value for ARI_ADMS_DEPT.ARI_INST_CODE
     *
     * @mbggenerated Fri Sep 12 19:38:08 KST 2014
     */
    public void setAriInstCode(String ariInstCode) {
        this.ariInstCode = ariInstCode == null ? null : ariInstCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ARI_ADMS_DEPT.ADMS_NO
     *
     * @return the value of ARI_ADMS_DEPT.ADMS_NO
     *
     * @mbggenerated Fri Sep 12 19:38:08 KST 2014
     */
    public String getAdmsNo() {
        return admsNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ARI_ADMS_DEPT.ADMS_NO
     *
     * @param admsNo the value for ARI_ADMS_DEPT.ADMS_NO
     *
     * @mbggenerated Fri Sep 12 19:38:08 KST 2014
     */
    public void setAdmsNo(String admsNo) {
        this.admsNo = admsNo == null ? null : admsNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ARI_ADMS_DEPT.DEPT_CODE
     *
     * @return the value of ARI_ADMS_DEPT.DEPT_CODE
     *
     * @mbggenerated Fri Sep 12 19:38:08 KST 2014
     */
    public String getDeptCode() {
        return deptCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ARI_ADMS_DEPT.DEPT_CODE
     *
     * @param deptCode the value for ARI_ADMS_DEPT.DEPT_CODE
     *
     * @mbggenerated Fri Sep 12 19:38:08 KST 2014
     */
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode == null ? null : deptCode.trim();
    }
}