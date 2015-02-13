package com.apexsoft.ysprj.template.service;

/**
 * Created with IntelliJ IDEA.
 * User: zbum
 * Date: 2014. 7. 26.
 * Time: 오후 7:11
 * To change this template use File | Settings | File Templates.
 */
public class TempFileVO {
    private int seq;
    private String path;
    private String fileName;

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

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
        return "TempFileVO{" +
                "seq=" + seq +
                ", path='" + path + '\'' +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}
