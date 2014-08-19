package com.apexsoft.ysprj.user.domain;

/**
 * TABLE : APPL_EXPR
 */
public class Career {

    private String applNo;        // 입학지원번호
    private String exprSeq;       // 경력순번
    private String joinYmd;       // 입사일자
    private String retrYmd;       // 퇴사일자
    private String corpName;      // 기관명
    private String exprDesc;      // 경력내용
    private String creId;         // 생성자
    private String creDate;       // 생성일자
    private String modId;         // 수정자
    private String modDate;       // 수정일자

    public String getApplNo() {
        return applNo;
    }

    public void setApplNo(String applNo) {
        this.applNo = applNo;
    }

    public String getExprSeq() {
        return exprSeq;
    }

    public void setExprSeq(String exprSeq) {
        this.exprSeq = exprSeq;
    }

    public String getJoinYmd() {
        return joinYmd;
    }

    public void setJoinYmd(String joinYmd) {
        this.joinYmd = joinYmd;
    }

    public String getRetrYmd() {
        return retrYmd;
    }

    public void setRetrYmd(String retrYmd) {
        this.retrYmd = retrYmd;
    }

    public String getCorpName() {
        return corpName;
    }

    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }

    public String getExprDesc() {
        return exprDesc;
    }

    public void setExprDesc(String exprDesc) {
        this.exprDesc = exprDesc;
    }

    public String getCreId() {
        return creId;
    }

    public void setCreId(String creId) {
        this.creId = creId;
    }

    public String getCreDate() {
        return creDate;
    }

    public void setCreDate(String creDate) {
        this.creDate = creDate;
    }

    public String getModId() {
        return modId;
    }

    public void setModId(String modId) {
        this.modId = modId;
    }

    public String getModDate() {
        return modDate;
    }

    public void setModDate(String modDate) {
        this.modDate = modDate;
    }
}
