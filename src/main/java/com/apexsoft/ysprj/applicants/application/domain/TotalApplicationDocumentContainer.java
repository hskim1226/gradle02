package com.apexsoft.ysprj.applicants.application.domain;

import java.util.ArrayList;
import java.util.List;

public class TotalApplicationDocumentContainer extends TotalApplicationDocument{


    List<TotalApplicationDocumentContainer> subContainer = new ArrayList<TotalApplicationDocumentContainer>();

    public List<TotalApplicationDocumentContainer> getSubContainer() {
        return subContainer;
    }

    public void setSubContainer(List<TotalApplicationDocumentContainer> subContainer) {
        this.subContainer = subContainer;
    }
}