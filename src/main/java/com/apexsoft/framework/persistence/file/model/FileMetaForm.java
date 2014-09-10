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

    @Override
    public String toString() {
        return "FileMetaForm{" +
                "path='" + path + '\'' +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}
