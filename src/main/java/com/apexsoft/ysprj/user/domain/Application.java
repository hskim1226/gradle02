package com.apexsoft.ysprj.user.domain;

import java.util.*;

/**
 * Created by Administrator on 2014-08-12.
 */
public class Application {




    private String applNo;
    private String admsNo;
    private String deptCode;
    private String username;
    private String korName;
    private String chnName;
    private String engSurName;
    private String engName;
    private String residentNumber;
    private String mltrServCode;
    private String mltrTypeCode;
    private String zipCode;
    private String address;
    private String detailAddr;
    private String telephone;
    private String mobile;
    private String email;
    private String creator;
    private Date   createdDate;
    private String modifier;
    private Date   modifiedDate;

    public String getApplNo() {
        return applNo;
    }

    public void setApplNo(String applNo) {
        this.applNo = applNo;
    }

    public String getAdmsNo() {
        return admsNo;
    }

    public void setAdmsNo(String admsNo) {
        this.admsNo = admsNo;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getKorName() {
        return korName;
    }

    public void setKorName(String korName) {
        this.korName = korName;
    }

    public String getChnName() {
        return chnName;
    }

    public void setChnName(String chnName) {
        this.chnName = chnName;
    }

    public String getEngSurName() {
        return engSurName;
    }

    public void setEngSurName(String engSurName) {
        this.engSurName = engSurName;
    }

    public String getEngName() {
        return engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }

    public String getResidentNumber() {
        return residentNumber;
    }

    public void setResidentNumber(String residentNumber) {
        this.residentNumber = residentNumber;
    }

    public String getMltrServCode() {
        return mltrServCode;
    }

    public void setMltrServCode(String mltrServCode) {
        this.mltrServCode = mltrServCode;
    }

    public String getMltrTypeCode() {
        return mltrTypeCode;
    }

    public void setMltrTypeCode(String mltrTypeCode) {
        this.mltrTypeCode = mltrTypeCode;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDetailAddr() {
        return detailAddr;
    }

    public void setDetailAddr(String detailAddr) {
        this.detailAddr = detailAddr;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }


    private String campus;
    private String course;
    private String institution;
    private Map<String, String> departments;
    private String department;
    private Map<String, String> detailMajors;
    private String detailMajor;
    private List<Academy> academies;
    private List<Career> careers;
    private Map<String, String> bankList;
    private String bank;
    private String accountNumber;
    private String accountOwner;

    public Application() {
        this.departments = new HashMap<String, String>();
        this.detailMajors = new HashMap<String, String>();
        this.academies = new ArrayList<Academy>();
        this.careers = new ArrayList<Career>();
        this.bankList = new HashMap<String, String>();
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public Map<String, String> getDepartments() {
        return departments;
    }

    public void setDepartments(Map<String, String> departments) {
        this.departments = departments;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Map<String, String> getDetailMajors() {
        return detailMajors;
    }

    public void setDetailMajors(Map<String, String> detailMajors) {
        this.detailMajors = detailMajors;
    }

    public String getDetailMajor() {
        return detailMajor;
    }

    public void setDetailMajor(String detailMajor) {
        this.detailMajor = detailMajor;
    }

    public List<Academy> getAcademies() {
        return academies;
    }

    public void setAcademies(List<Academy> academies) {
        this.academies = academies;
    }

    public List<Career> getCareers() {
        return careers;
    }

    public void setCareers(List<Career> careers) {
        this.careers = careers;
    }

    public Map<String, String> getBankList() {
        return bankList;
    }

    public void setBankList(Map<String, String> bankList) {
        this.bankList = bankList;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountOwner() {
        return accountOwner;
    }

    public void setAccountOwner(String accountOwner) {
        this.accountOwner = accountOwner;
    }
}
