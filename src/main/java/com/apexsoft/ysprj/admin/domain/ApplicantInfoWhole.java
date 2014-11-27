package com.apexsoft.ysprj.admin.domain;

import java.util.Date;
import java.util.List;

import com.apexsoft.ysprj.applicants.application.domain.ApplicationAcademy;
import com.apexsoft.ysprj.applicants.application.domain.ApplicationDocument;
import com.apexsoft.ysprj.applicants.application.domain.ApplicationLanguage;

public class ApplicantInfoWhole extends ApplicantInfo{
	

    private List <ApplicationDocument> docList;
	private List <ApplicationAcademy> acadList;  
    private List <ApplicationLanguage> langList;   
    
    public List<ApplicationDocument> getDocList() {
		return docList;
	}
	public void setDocList(List<ApplicationDocument> docList) {
		this.docList = docList;
	}
	public List<ApplicationAcademy> getAcadList() {
		return acadList;
	}
	public void setAcadList(List<ApplicationAcademy> acadList) {
		this.acadList = acadList;
	}
	public List<ApplicationLanguage> getLangList() {
		return langList;
	}
	public void setLangList(List<ApplicationLanguage> langList) {
		this.langList = langList;
	}
     
    

}
