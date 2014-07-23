package com.apexsoft.ysprj.user.web.form;

/**
 * Created with IntelliJ IDEA.
 * User: zbum
 * Date: 2014. 7. 22.
 * Time: 오후 6:03
 * To change this template use File | Settings | File Templates.
 */
public class UserSearchForm {

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
}
