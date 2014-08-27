package com.apexsoft.ysprj.admin.web.form;

import java.util.List;

public class ApplicantSearchForm {


	    private int pageNum = 1;

	    private int pageRows = 30;

	    private String applId;

	    private String korName;
	    
	    private String engSur;

	    private String engName;	

	    private String rgstNo;
	    
		public String getApplId() {
			return applId;
		}

		public void setApplId(String applId) {
			this.applId = applId;
		}

		public int getPageNum() {
			return pageNum;
		}

		public void setPageNum(int pageNum) {
			this.pageNum = pageNum;
		}

		public int getPageRows() {
			return pageRows;
		}

		public void setPageRows(int pageRows) {
			this.pageRows = pageRows;
		}

		public String getApplID() {
			return applId;
		}

		public void setApplID(String applID) {
			this.applId = applID;
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
	    

}
