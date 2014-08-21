package com.apexsoft.ysprj.user.domain;

import java.util.Date;

/**
 * Created by go2zo on 2014. 8. 17.
 * TABEL: ADMS
 */
public class Admission {

    private String admsNo;          // 입학전형번호
    private String entrYear;        // 입학년도
    private String admsTypeCode;    // 전형구분코드
    private String admsDesc;        // 입학전형설명
    private String admsStsCode;     // 전형상태코드
    private String creId;           // 생성자
    private Date   creDate;         // 생성일자
    private String modId;           // 수정자
    private Date modDate;        // 수정일자

    public String getAdmsNo() {
        return admsNo;
    }

    public void setAdmsNo(String admsNo) {
        this.admsNo = admsNo;
    }

    public String getEntrYear() {
        return entrYear;
    }

    public void setEntrYear(String entrYear) {
        this.entrYear = entrYear;
    }

    public String getAdmsTypeCode() {
        return admsTypeCode;
    }

    public void setAdmsTypeCode(String admsTypeCode) {
        this.admsTypeCode = admsTypeCode;
    }

    public String getAdmsDesc() {
        return admsDesc;
    }

    public void setAdmsDesc(String admsDesc) {
        this.admsDesc = admsDesc;
    }

    public String getAdmsStsCode() {
        return admsStsCode;
    }

    public void setAdmsStsCode(String admsStsCode) {
        this.admsStsCode = admsStsCode;
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
