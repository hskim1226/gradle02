package com.apexsoft.ysprj.user.service;

import com.apexsoft.framework.security.UserSessionVO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

public class UsersVO extends UserSessionVO {

//    private String username;
    private String userType;
//    private String name;
    private String birth;
    private String gender;
    private String mobile;
//    private String password;
//    private String email;
    private String smsReceive;
    private String emailReceive;
    private String divisionCode;
    private String termsAgree;
    private String privacyAgree;
    private String creator;
    private Date createDate;
    private String modifier;
    private Date modifyDate;

//    private Collection<? extends GrantedAuthority> authorities;

//    private boolean enabled;

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return authorities;
//    }
//
//    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
//        this.authorities = authorities;
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    @Override
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return enabled;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return enabled;
//    }
//
//    public void setEnabled(boolean enabled) {
//        this.enabled = enabled;
//    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }

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

//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }

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
//                "userId=" + username +
                ", userType=" + userType +
//                ", name=" + name +
                ", birth=" + birth +
                ", gender=" + gender +
                ", mobile=" + mobile +
//                ", password=" + password +
//                ", email=" + email +
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
