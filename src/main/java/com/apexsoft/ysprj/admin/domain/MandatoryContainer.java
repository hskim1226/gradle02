package com.apexsoft.ysprj.admin.domain;

import java.util.List;

public class MandatoryContainer extends CommonMandatory {

    private List<MandatoryContainer> subContList;

    public List<MandatoryContainer> getSubContList() {
        return subContList;
    }

    public void setSubContList(List<MandatoryContainer> subContList) {
        this.subContList = subContList;
    }
}