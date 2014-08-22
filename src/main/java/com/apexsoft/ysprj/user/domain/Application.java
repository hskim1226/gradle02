package com.apexsoft.ysprj.user.domain;

import java.util.*;

/**
 * Created by go2zo on 2014. 8. 12.
 * TABLE: APPL
 */
public class Application {

    private String applNo;              // 입학지원번호
    private String userId;              // 회원아이디
    private String admsNo;              // 입학전형번호
    private String deptCode;            // 학과코드
    private String ariInsstCode;        // 학연산연구기관코드
    private String corsTypeCode;        // 지원과정구분코드
    private String detlMajCode;         // 세부전공코드
    private String inpDetlMaj;          // 입력세부전공
    private String partTimeYn;          // 파트타임여부
    private String korName;             // 한글이름
    private String chnName;             // 한자이름
    private String engSur;              // 영문성
    private String engName;             // 영문이름
    private String rgstNo;              // 주민등록번호
    private String zipCode;             // 우편번호
    private String addr;                // 주소
    private String detlAddr;            // 상세주소
    private String telNum;              // 전화번호
    private String mobiNum;             // 휴대폰번호
    private String faxNum;              // 팩스번호
    private String mailAddr;            // 이메일
    private String applId;              // 수험번호
    private String applStsCode;         // 지원상태코드
    private String privInfoYn;          // 개인정보3자이용동의
    private String creId;               // 생성자
    private Date   creDate;             // 생성일자
    private String modId;               // 수정자
    private Date   modDate;             // 수정일자

    public String getApplNo() {
        return applNo;
    }

    public void setApplNo(String applNo) {
        this.applNo = applNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getAdmsNo() {
        return admsNo;
    }

    public void setAdmsNo(String admsNo) {
        this.admsNo = admsNo;
    }

    public String getAriInsstCode() {
        return ariInsstCode;
    }

    public void setAriInsstCode(String ariInsstCode) {
        this.ariInsstCode = ariInsstCode;
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

    public String getInpDetlMaj() {
        return inpDetlMaj;
    }

    public void setInpDetlMaj(String inpDetlMaj) {
        this.inpDetlMaj = inpDetlMaj;
    }

    public String getPartTimeYn() {
        return partTimeYn;
    }

    public void setPartTimeYn(String partTimeYn) {
        this.partTimeYn = partTimeYn;
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

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getRgstNo() {
        return rgstNo;
    }

    public void setRgstNo(String rgstNo) {
        this.rgstNo = rgstNo;
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

    public String getFaxNum() {
        return faxNum;
    }

    public void setFaxNum(String faxNum) {
        this.faxNum = faxNum;
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

    public String getPrivInfoYn() {
        return privInfoYn;
    }

    public void setPrivInfoYn(String privInfoYn) {
        this.privInfoYn = privInfoYn;
    }

    public String getCreId() {
        return creId;
    }

    public void setCreId(String creId) {
        this.creId = creId;
    }

    public Date getCreDate() {
        return creDate;
    }

    public void setCreDate(Date creDate) {
        this.creDate = creDate;
    }

    public String getModId() {
        return modId;
    }

    public void setModId(String modId) {
        this.modId = modId;
    }

    public Date getModDate() {
        return modDate;
    }

    public void setModDate(Date modDate) {
        this.modDate = modDate;
    }

}
