package com.apexsoft.ysprj.applicants.application.domain;

import java.util.List;

/**
 * Created by hanmomhanda on 15. 1. 26.
 */
public class Document {

    private Application application;
    private List<TotalApplicationDocumentContainer> documentContainerList;

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public List<TotalApplicationDocumentContainer> getDocumentContainerList() {
        return documentContainerList;
    }

    public void setDocumentContainerList(List<TotalApplicationDocumentContainer> documentContainerList) {
        this.documentContainerList = documentContainerList;
    }
}
