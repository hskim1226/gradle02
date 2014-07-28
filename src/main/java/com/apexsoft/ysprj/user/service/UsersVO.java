package com.apexsoft.ysprj.user.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.Collection;


public class UsersVO implements UserDetails {

    @NotNull
    @Size(min=5, max=50)
    private String username;

    @NotNull
    @Size(min=5, max=50)
    private String password;
	
	private boolean enabled;
	
	private String nickname;
	
	private String koreanName;
	
	private String engName;
	
	private String birthDate;
	
	private int postNumber;

	private int postSeq;

	private String address;

	private String addressDetail;

    private String engAddress;

	private String phoneNumber;

	private String phoneNumber1;
	
	private String phoneNumber2;
	
	private String phoneNumber3;

    @NotNull
    @Size(min=5, max=50)
    private String email;
	
	private String passportNumber;
	
	private String license;

    private int grade;
	
	private int diveLog;
	
	private boolean mailingService;
	
	private boolean smsService;

    private Date regDate;

    private Collection<? extends GrantedAuthority> authorities;

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isAccountNonLocked() {

        return enabled;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getKoreanName() {
        return koreanName;
    }

    public void setKoreanName(String koreanName) {
        this.koreanName = koreanName;
    }

    public String getEngName() {
        return engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public int getPostNumber() {
        return postNumber;
    }

    public void setPostNumber(int postNumber) {
        this.postNumber = postNumber;
    }

    public int getPostSeq() {
        return postSeq;
    }

    public void setPostSeq(int postSeq) {
        this.postSeq = postSeq;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public String getEngAddress() {
        return engAddress;
    }

    public void setEngAddress(String engAddress) {
        this.engAddress = engAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber1() {
        return phoneNumber1;
    }

    public void setPhoneNumber1(String phoneNumber1) {
        this.phoneNumber1 = phoneNumber1;
    }

    public String getPhoneNumber2() {
        return phoneNumber2;
    }

    public void setPhoneNumber2(String phoneNumber2) {
        this.phoneNumber2 = phoneNumber2;
    }

    public String getPhoneNumber3() {
        return phoneNumber3;
    }

    public void setPhoneNumber3(String phoneNumber3) {
        this.phoneNumber3 = phoneNumber3;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getDiveLog() {
        return diveLog;
    }

    public void setDiveLog(int diveLog) {
        this.diveLog = diveLog;
    }

    public boolean isMailingService() {
        return mailingService;
    }

    public void setMailingService(boolean mailingService) {
        this.mailingService = mailingService;
    }

    public boolean isSmsService() {
        return smsService;
    }

    public void setSmsService(boolean smsService) {
        this.smsService = smsService;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    @Override
    public String toString() {
        return "UsersVO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", nickname='" + nickname + '\'' +
                ", koreanName='" + koreanName + '\'' +
                ", engName='" + engName + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", postNumber=" + postNumber +
                ", postSeq=" + postSeq +
                ", address='" + address + '\'' +
                ", addressDetail='" + addressDetail + '\'' +
                ", engAddress='" + engAddress + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", phoneNumber1='" + phoneNumber1 + '\'' +
                ", phoneNumber2='" + phoneNumber2 + '\'' +
                ", phoneNumber3='" + phoneNumber3 + '\'' +
                ", email='" + email + '\'' +
                ", passportNumber='" + passportNumber + '\'' +
                ", license='" + license + '\'' +
                ", grade=" + grade +
                ", diveLog=" + diveLog +
                ", mailingService=" + mailingService +
                ", smsService=" + smsService +
                '}';
    }
}
