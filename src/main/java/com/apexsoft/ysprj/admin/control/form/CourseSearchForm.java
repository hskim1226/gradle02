package com.apexsoft.ysprj.admin.control.form;
public class CourseSearchForm extends ApplicantSearchForm {

	private String admsNo;//ADMS_NO	
	private String campCode;//CAMP_CODE
	private String collCode;//COLL_CODE
	private String deptCode;//DEPT_CODE
	private String corsTypeCode;//CORS_TYPE_CODE
    private String applStscode;

    public String getAdmsNo() {
        return admsNo;
    }
    public void setAdmsNo(String admsNo) {
        this.admsNo = admsNo;
    }
    public String getCampCode() {
        return campCode;
    }
    public void setCampCode(String campCode) {
        this.campCode = campCode;
    }
    public String getCollCode() {
        return collCode;
    }
    public void setCollCode(String collCode) {
        this.collCode = collCode;
    }
    public String getDeptCode() {
        return deptCode;
    }
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }
    public String getCorsTypeCode() {
        return corsTypeCode;
    }
    public void setCorsTypeCode(String corsTypeCode) {
        this.corsTypeCode = corsTypeCode;
    }

    public String getApplStscode() {
        return applStscode;
    }

    public void setApplStscode(String applStscode) {
        this.applStscode = applStscode;
    }
}
