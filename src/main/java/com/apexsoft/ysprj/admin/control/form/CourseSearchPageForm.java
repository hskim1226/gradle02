package com.apexsoft.ysprj.admin.control.form;

import com.apexsoft.framework.persistence.dao.page.PagenateInfo;

public class CourseSearchPageForm extends CourseSearchForm {

    private int searchType = 0;

    private PagenateInfo page = new PagenateInfo(15);

    public PagenateInfo getPage() {
        return page;
    }

    public void setPage(PagenateInfo page) {
        this.page = page;
    }

    public int getSearchType() {
        return searchType;
    }

    public void setSearchType(int searchType) {
        this.searchType = searchType;
    }
}
