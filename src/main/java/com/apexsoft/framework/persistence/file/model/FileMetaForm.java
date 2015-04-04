package com.apexsoft.framework.persistence.file.model;

import com.apexsoft.ysprj.applicants.application.domain.TotalApplicationDocument;

/**
 * Created with IntelliJ IDEA.
 * User: zbum
 * Date: 2014. 7. 23.
 * Time: 오후 10:42
 * To change this template use File | Settings | File Templates.
 */
public class FileMetaForm {

    private String path;
    private String fileName;
    private String originalFileName;
    private String fieldName;
    private String targetButton;
    private String targetFileDownloadLinkId;
    private String targetFileDeleteLinkId;
    private String applNo;
    private String admsNo;
    private String resultMessage;
    private TotalApplicationDocument totalApplicationDocument;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getTargetButton() {
        return targetButton;
    }

    public void setTargetButton(String targetButton) {
        this.targetButton = targetButton;
    }

    public String getTargetFileDownloadLinkId() {
        return targetFileDownloadLinkId;
    }

    public void setTargetFileDownloadLinkId( String targetFileDownloadLinkId ) {
        this.targetFileDownloadLinkId = targetFileDownloadLinkId;
    }

    public String getTargetFileDeleteLinkId() {
        return targetFileDeleteLinkId;
    }

    public void setTargetFileDeleteLinkId( String targetFileDeleteLinkId ) {
        this.targetFileDeleteLinkId = targetFileDeleteLinkId;
    }

    public String getApplNo() {
        return applNo;
    }

    public void setApplNo(String applNo) {
        this.applNo = applNo;
    }

    public String getAdmsNo() {
        return admsNo;
    }

    public void setAdmsNo(String admsNo) {
        this.admsNo = admsNo;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public TotalApplicationDocument getOneDocument() {
        return totalApplicationDocument;
    }

    public void setTotalApplicationDocument(TotalApplicationDocument totalApplicationDocument) {
        this.totalApplicationDocument = totalApplicationDocument;
    }

    @Override
    public String toString() {
        return "FileMetaForm{" +
                "path='" + path + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fieldName='" + fieldName+ '\'' +
                ", targetButton='" + targetButton+ '\'' +
                '}';
    }
}
