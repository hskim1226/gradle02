package com.apexsoft.ysprj.admin.domain;

import java.util.Date;
import java.util.List;

import com.apexsoft.ysprj.applicants.application.domain.ApplicationDocument;

public class ApplicantInfo {
    
    private int applNo;
    private String userId;
	private String admsNo;
    private String admsType;
    private String admsTypeName;
    private String applAttrCode;
    private String applAttrName;
	private String campName;
    private String collName;
    private String deptCode;    
    private String deptName;        
    private String ariInstCode;
    private String ariInstName;  //  
    private String corsTypeCode;  //   
    private String corsTypeName;  //      
    private String detlMajCode;  //     
    private String detlMajName;  //   
    
	private String korName;  //
    private String engSur;  // 
    private String engName;  //     
    private String rgstNo;  // 
    private String telNum;  //  
    private String addr; 
    private String detlAddr;      

	private String mobiNum;
    private String mailAddr;
    private String emerContCode;
	private String emerContName; 
    private String emerContTel;      
    
	public String getEmerContCode() {
		return emerContCode;
	}
	public void setEmerContCode(String emerContCode) {
		this.emerContCode = emerContCode;
	}
	public String getEmerContName() {
		return emerContName;
	}
	public void setEmerContName(String emerContName) {
		this.emerContName = emerContName;
	}
	public String getEmerContTel() {
		return emerContTel;
	}
	public void setEmerContTel(String emerContTel) {
		this.emerContTel = emerContTel;
	}
	private String applId;    
    private String applStsCode; 
    private String applStsName;
    private String payTypeCode;
    private String payTypeName;
    private int admsFee;
    
    public int getAdmsFee() {
		return admsFee;
	}
	public void setAdmsFee(int admsFee) {
		this.admsFee = admsFee;
	}
	private Date applDate;

	public Date getApplDate() {
		return applDate;
	}
	public void setApplDate(Date applDate) {
		this.applDate = applDate;
	}
	private List <ApplicationDocument> docList;
    
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getApplNo() {
		return applNo;
	}
	public void setApplNo(int applNo) {
		this.applNo = applNo;
	}
	public String getAdmsNo() {
		return admsNo;
	}
	public void setAdmsNo(String admsNo) {
		this.admsNo = admsNo;
	}
	public String getAdmsType() {
		return admsType;
	}
	public void setAdmsType(String admsType) {
		this.admsType = admsType;
	}
	public String getCampName() {
		return campName;
	}
	public void setCampName(String campName) {
		this.campName = campName;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getAriInstCode() {
		return ariInstCode;
	}
	public void setAriInstCode(String ariInstCode) {
		this.ariInstCode = ariInstCode;
	}
	public String getAriInstName() {
		return ariInstName;
	}
	public void setAriInstName(String ariInstName) {
		this.ariInstName = ariInstName;
	}
	public String getCorsTypeCode() {
		return corsTypeCode;
	}
	public void setCorsTypeCode(String corsTypeCode) {
		this.corsTypeCode = corsTypeCode;
	}
	public String getDetlMajCode() {
		return detlMajCode;
	}
	public void setDetlMajCode(String detlMajCode) {
		this.detlMajCode = detlMajCode;
	}
	public String getKorName() {
		return korName;
	}
	public void setKorName(String korName) {
		this.korName = korName;
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
	public String getRgstNo() {
		return rgstNo;
	}
	public void setRgstNo(String rgstNo) {
		this.rgstNo = rgstNo;
	}
	public String getTelNum() {
		return telNum;
	}
	public void setTelNum(String telNum) {
		this.telNum = telNum;
	}
	public String getMobiNum() {
		return mobiNum;
	}
	public void setMobiNum(String mobiNum) {
		this.mobiNum = mobiNum;
	}
	public String getMailAddr() {
		return mailAddr;
	}
	public void setMailAddr(String mailAddr) {
		this.mailAddr = mailAddr;
	}
	public String getApplId() {
		return applId;
	}
	public void setApplId(String applId) {
		this.applId = applId;
	}
	public String getApplStsCode() {
		return applStsCode;
	}
	public void setApplStsCode(String applStsCode) {
		this.applStsCode = applStsCode;
	}

	public List<ApplicationDocument> getDocList() {
		return docList;
	}
	public void setDocList(List<ApplicationDocument> docuList) {
		this.docList = docuList;
	}
    
    public String getCollName() {
		return collName;
	}
	public void setCollName(String collName) {
		this.collName = collName;
	}


	public String getAdmsTypeName() {
		return admsTypeName;
	}
	public void setAdmsTypeName(String admsTypeName) {
		this.admsTypeName = admsTypeName;
	}
	public String getCorsTypeName() {
		return corsTypeName;
	}
	public void setCorsTypeName(String corsTypeName) {
		this.corsTypeName = corsTypeName;
	}
	public String getDetlMajName() {
		return detlMajName;
	}
	public void setDetlMajName(String detlMajName) {
		this.detlMajName = detlMajName;
	}
	public String getApplStsName() {
		return applStsName;
	}
	public void setApplStsName(String applStsName) {
		this.applStsName = applStsName;
	}
    public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getDetlAddr() {
		return detlAddr;
	}
	public void setDetlAddr(String detlAddr) {
		this.detlAddr = detlAddr;
	}

    public String getApplAttrCode() {
        return applAttrCode;
    }

    public void setApplAttrCode(String applAttrCode) {
        this.applAttrCode = applAttrCode;
    }

    public String getApplAttrName() {
        return applAttrName;
    }

    public void setApplAttrName(String applAttrName) {
        this.applAttrName = applAttrName;
    }

    public String getPayTypeCode() {
        return payTypeCode;
    }

    public void setPayTypeCode(String payTypeCode) {
        this.payTypeCode = payTypeCode;
    }

    public String getPayTypeName() {
        return payTypeName;
    }

    public void setPayTypeName(String payTypeName) {
        this.payTypeName = payTypeName;
    }
}
