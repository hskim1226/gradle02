package com.apexsoft.ysprj.user.web.form;

/**
 * Created with IntelliJ IDEA.
 * User: zbum
 * Date: 2014. 7. 22.
 * Time: 오후 6:03
 * To change this template use File | Settings | File Templates.
 */
public class UserSearchForm {

    private int pageNum = 1;

    private int pageRows = 30;

    private String searchKeyword;

    private String searchCondition;

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    public String getSearchCondition() {
        return searchCondition;
    }

    public void setSearchCondition(String searchCondition) {
        this.searchCondition = searchCondition;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageRows() {
        return pageRows;
    }

    public void setPageRows(int pageRows) {
        this.pageRows = pageRows;
    }
}
