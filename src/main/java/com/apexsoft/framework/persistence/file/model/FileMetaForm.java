package com.apexsoft.framework.persistence.file.model;

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
    private String targetFileLinkId;
    private String applNo;
    private String admsNo;

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

    public String getTargetFileLinkId() {
        return targetFileLinkId;
    }

    public void setTargetFileLinkId(String targetFileLinkId) {
        this.targetFileLinkId = targetFileLinkId;
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
