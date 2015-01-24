package com.apexsoft.ysprj.applicants.application.domain;

import java.util.List;

public class DocumentGroup {



    private List<TotalApplicationDocumentContainer> applContList;

    public List<TotalApplicationDocumentContainer> getApplContList() {
        return applContList;
    }

    public void setApplContList(List<TotalApplicationDocumentContainer> applContList) {
        this.applContList = applContList;
    }
}