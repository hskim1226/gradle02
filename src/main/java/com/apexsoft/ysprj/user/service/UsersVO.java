package com.apexsoft.ysprj.user.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

public class UsersVO implements UserDetails {

    private String userId;
    private String userType;
    private String name;
    private String birth;
    private String gender;
    private String mobile;
    private String encryptedPassword;
    private String email;
    private String smsReceive;
    private String emailReceive;
    private String divisionCode;
    private String termsAgree;
    private String privacyAgree;
    private String creator;
    private Date createDate;
    private String modifier;
    private Date modifyDate;

    private Collection<? extends GrantedAuthority> authorities;

    private boolean enabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String getPassword() {
        return encryptedPassword;
    }

    public void setPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    @Override
    public String getUsername() {
        return getUserId();
    }

    public void setUsername(String username) {
        setUserId(username);
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSmsReceive() {
        return smsReceive;
    }

    public void setSmsReceive(String smsReceive) {
        this.smsReceive = smsReceive;
    }

    public String getEmailReceive() {
        return emailReceive;
    }

    public void setEmailReceive(String emailReceive) {
        this.emailReceive = emailReceive;
    }

    public String getDivisionCode() {
        return divisionCode;
    }

    public void setDivisionCode(String divisionCode) {
        this.divisionCode = divisionCode;
    }

    public String getTermsAgree() {
        return termsAgree;
    }

    public void setTermsAgree(String termsAgree) {
        this.termsAgree = termsAgree;
    }

    public String getPrivacyAgree() {
        return privacyAgree;
    }

    public void setPrivacyAgree(String privacyAgree) {
        this.privacyAgree = privacyAgree;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public java.util.Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(java.util.Date createDate) {
        this.createDate = createDate;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public java.util.Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(java.util.Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    @Override
    public String toString() {
        return "UsersVO{" +
                "userId=" + userId +
                ", userType=" + userType +
                ", name=" + name +
                ", birth=" + birth +
                ", gender=" + gender +
                ", mobile=" + mobile +
                ", encryptedPassword=" + encryptedPassword +
                ", email=" + email +
                ", smsReceive=" + smsReceive +
                ", emailReceive=" + emailReceive +
                ", divisionCode=" + divisionCode +
                ", termsAgree=" + termsAgree +
                ", privacyAgree=" + privacyAgree +
                ", creator=" + creator +
                ", createDate=" + createDate +
                ", modifier=" + modifier +
                ", modifyDate=" + modifyDate +
                "}";
    }
}
