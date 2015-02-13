package com.apexsoft.ysprj.qna.web.form;

import com.apexsoft.framework.persistence.dao.page.PagenateInfo;

/**
 * Created with IntelliJ IDEA.
 * User: zbum
 * Date: 2014. 8. 24.
 * Time: 오후 6:40
 * To change this template use File | Settings | File Templates.
 */
public class QnaSearchForm {


    private String searchKeyword;

    private String searchCondition;

    private PagenateInfo page = new PagenateInfo(15);

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

    public PagenateInfo getPage() {
        return page;
    }

    public void setPage(PagenateInfo page) {
        this.page = page;
    }
}
