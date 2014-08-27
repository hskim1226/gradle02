package com.apexsoft.ysprj.admin.domain;

import java.util.Date;
import java.util.List;

public class ApplicantInfo {
	
    private String USER_ID;    // 생성자
	private String ADMS_NO;    // 생성자
	private String applId;    // 생성자	
    private String ADMS_TYPE;  // 생성일자
    private String CAMP_NAME;  // 수정자
    private String DEPT_NAME; 
    private String CORS_TYPE_CODE;  // 
    private String ARI_INST_NAME;  // 
    private String DETL_MAJ_CODE;  // 
    private String KOR_NAME;  //
    private String engSur;  // 
    private String engName;  //     
    private String RGST_NO;  // 
    private String MOBI_NUM;  // 
    private String MAIL_ADDR;  // 
    private String temp1;  // 
    private String temp2;  // 
    private String temp3;  //
    private List <Docu> docuList;
    
	public String getUSER_ID() {
		return USER_ID;
	}
	public void setUSER_ID(String uSER_ID) {
		USER_ID = uSER_ID;
	}
	public String getADMS_NO() {
		return ADMS_NO;
	}
	public void setADMS_NO(String aDMS_NO) {
		ADMS_NO = aDMS_NO;
	}
	public String getApplId() {
		return applId;
	}
	public void setApplId(String applId) {
		this.applId = applId;
	}
	public String getADMS_TYPE() {
		return ADMS_TYPE;
	}
	public void setADMS_TYPE(String aDMS_TYPE) {
		ADMS_TYPE = aDMS_TYPE;
	}
	public String getCAMP_NAME() {
		return CAMP_NAME;
	}
	public void setCAMP_NAME(String cAMP_NAME) {
		CAMP_NAME = cAMP_NAME;
	}
	public String getDEPT_NAME() {
		return DEPT_NAME;
	}
	public void setDEPT_NAME(String dEPT_NAME) {
		DEPT_NAME = dEPT_NAME;
	}
	public String getCORS_TYPE_CODE() {
		return CORS_TYPE_CODE;
	}
	public void setCORS_TYPE_CODE(String cORS_TYPE_CODE) {
		CORS_TYPE_CODE = cORS_TYPE_CODE;
	}
	public String getARI_INST_NAME() {
		return ARI_INST_NAME;
	}
	public void setARI_INST_NAME(String aRI_INST_NAME) {
		ARI_INST_NAME = aRI_INST_NAME;
	}
	public String getDETL_MAJ_CODE() {
		return DETL_MAJ_CODE;
	}
	public void setDETL_MAJ_CODE(String dETL_MAJ_CODE) {
		DETL_MAJ_CODE = dETL_MAJ_CODE;
	}
	public String getKOR_NAME() {
		return KOR_NAME;
	}
	public void setKOR_NAME(String kOR_NAME) {
		KOR_NAME = kOR_NAME;
	}
	public String getEngSur() {
		return engSur;
	}
	public void setEngSur(String engSur) {
		this.engSur = engSur;
	}
	public String getEngName() {
		return engName;
	}
	public void setEngName(String engName) {
		this.engName = engName;
	}
	public String getRGST_NO() {
		return RGST_NO;
	}
	public void setRGST_NO(String rGST_NO) {
		RGST_NO = rGST_NO;
	}
	public String getMOBI_NUM() {
		return MOBI_NUM;
	}
	public void setMOBI_NUM(String mOBI_NUM) {
		MOBI_NUM = mOBI_NUM;
	}
	public String getMAIL_ADDR() {
		return MAIL_ADDR;
	}
	public void setMAIL_ADDR(String mAIL_ADDR) {
		MAIL_ADDR = mAIL_ADDR;
	}
	public String getTemp1() {
		return temp1;
	}
	public void setTemp1(String temp1) {
		this.temp1 = temp1;
	}
	public String getTemp2() {
		return temp2;
	}
	public void setTemp2(String temp2) {
		this.temp2 = temp2;
	}
	public String getTemp3() {
		return temp3;
	}
	public void setTemp3(String temp3) {
		this.temp3 = temp3;
	}
	public List<Docu> getDocuList() {
		return docuList;
	}
	public void setDocuList(List<Docu> docuList) {
		this.docuList = docuList;
	}


}
