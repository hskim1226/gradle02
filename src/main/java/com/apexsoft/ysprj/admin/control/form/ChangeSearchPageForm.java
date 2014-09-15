package com.apexsoft.ysprj.admin.control.form;

import com.apexsoft.framework.persistence.dao.page.PagenateInfo;


public class ChangeSearchPageForm extends ChangeSearchForm {


    private PagenateInfo page = new PagenateInfo(15);


    public PagenateInfo getPage() {
        return page;
    }

    public void setPage(PagenateInfo page) {
        this.page = page;
    }


		    
	    

}
