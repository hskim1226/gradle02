package com.apexsoft.ysprj.admin.control.form;
public class CourseSearchGridForm extends CourseSearchForm {

    public boolean is_search() {
        return _search;
    }

    public void set_search(boolean _search) {
        this._search = _search;
    }

    public long getNd() {
        return nd;
    }

    public void setNd(long nd) {
        this.nd = nd;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getSidx() {
        return sidx;
    }

    public void setSidx(String sidx) {
        this.sidx = sidx;
    }

    public String getSord() {
        return sord;
    }

    public void setSord(String sord) {
        this.sord = sord;
    }

    private boolean _search;
	private long    nd;
    private int rows;
    private int  page;
    private String sidx;
    private String sord;


}
