package com.apexsoft.ysprj.qna.service;

import java.sql.Date;

/**
 * Created with IntelliJ IDEA.
 * User: zbum
 * Date: 2014. 8. 24.
 * Time: 오후 6:24
 * To change this template use File | Settings | File Templates.
 */
public class QnaVO {

    private int id;
    private String title;
    private String contents;
    private int readCnt;
    private boolean useYn;
    private String creId;
    private Date creDate;
    private String modId;
    private Date modDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public int getReadCnt() {
        return readCnt;
    }

    public void setReadCnt(int readCnt) {
        this.readCnt = readCnt;
    }

    public boolean isUseYn() {
        return useYn;
    }

    public void setUseYn(boolean useYn) {
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

    @Override
    public String toString() {
        return "QnaVO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", readCnt=" + readCnt +
                ", useYn=" + useYn +
                ", creId='" + creId + '\'' +
                ", creDate=" + creDate +
                ", modId='" + modId + '\'' +
                ", modDate=" + modDate +
                '}';
    }
}
