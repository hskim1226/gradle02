package com.apexsoft.ysprj.applicants.application.domain;

import org.apache.pdfbox.pdmodel.PDDocument;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hanmomhanda on 16. 1. 18.
 */
public class GradnetPDDocument extends PDDocument {
    PDDocument pdDocument;
    Map<String, String> metaData = new HashMap<>();

    public PDDocument getPdDocument() {
        return pdDocument;
    }

    public void setPdDocument(PDDocument pdDocument) {
        this.pdDocument = pdDocument;
    }

    public Map<String, String> getMetaDataMap() {
        return metaData;
    }
}
