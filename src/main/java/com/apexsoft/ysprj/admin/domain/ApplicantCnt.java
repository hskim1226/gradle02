package com.apexsoft.ysprj.admin.domain;

public class ApplicantCnt {

	private String deptSeq;
	private String deptCode;
	private String deptName;	
	private String campName;
	private String collName;
	private int cnt1 = 0;
	private int cnt2 = 0;
	private int cnt3 = 0;
	private int cnt4 = 0;	
	private int totalCnt = 0;
	

	public int getTotalCnt() {
		return totalCnt;
	}
	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}
	public String getDeptSeq() {
		return deptSeq;
	}
	public void setDeptSeq(String deptSeq) {
		this.deptSeq = deptSeq;
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
	public String getCampName() {
		return campName;
	}
	public void setCampName(String campName) {
		this.campName = campName;
	}
	public String getCollName() {
		return collName;
	}
	public void setCollName(String collName) {
		this.collName = collName;
	}
	public int getCnt1() {
		return cnt1;
	}
	public void setCnt1(int cnt1) {
		this.cnt1 = cnt1;
	}
	public int getCnt2() {
		return cnt2;
	}
	public void setCnt2(int cnt2) {
		this.cnt2 = cnt2;
	}
	public int getCnt3() {
		return cnt3;
	}
	public void setCnt3(int cnt3) {
		this.cnt3 = cnt3;
	}
	public int getCnt4() {
		return cnt4;
	}
	public void setCnt4(int cnt4) {
		this.cnt4 = cnt4;
	}	

	
}
