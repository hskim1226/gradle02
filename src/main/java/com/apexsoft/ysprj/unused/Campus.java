package com.apexsoft.ysprj.unused;

import java.util.Date;

/**
 * Created by hanmomhanda on 14. 8. 19.
 * TABLE : CAMP
 *
 * Modified by
 *      go2zo on 2014. 8. 20.
 */
@Deprecated
public class Campus {

    private String campCode;        // 캠퍼스코드
    private String campName;        // 캠퍼스명
    private String useYn;           // 사용여부
    private String creId;           // 생성자
    private Date   creDate;         // 생성일자
    private String modId;           // 수정자
    private Date   modDate;         // 수정일자

    public String getCampCode() {
        return campCode;
    }

    public void setCampCode(String campCode) {
        this.campCode = campCode;
    }

    public String getCampName() {
        return campName;
    }

    public void setCampName(String campName) {
        this.campName = campName;
    }

    public String getUseYn() {
        return useYn;
    }

    public void setUseYn(String useYn) {
        this.useYn = useYn;
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
