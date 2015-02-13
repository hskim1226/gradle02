package com.apexsoft.ysprj.applicants.application.domain;

import java.util.ArrayList;
import java.util.List;

public class TotalApplicationLanguageContainer extends TotalApplicationLanguage{
    List<TotalApplicationLanguageContainer> subContainer = new ArrayList<TotalApplicationLanguageContainer>();

    public List<TotalApplicationLanguageContainer> getSubContainer() {
        return subContainer;
    }

    public void setSubContainer(List<TotalApplicationLanguageContainer> subContainer) {
        this.subContainer = subContainer;
    }
}