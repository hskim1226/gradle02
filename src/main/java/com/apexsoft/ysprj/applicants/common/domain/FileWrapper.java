package com.apexsoft.ysprj.applicants.common.domain;

import java.io.InputStream;

/**
 * Created by hanmomhanda on 16. 2. 17.
 */
public class FileWrapper {
    private InputStream inputStream;
    private FileMeta fileMeta;

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public FileMeta getFileMeta() {
        return fileMeta;
    }

    public void setFileMeta(FileMeta fileMeta) {
        this.fileMeta = fileMeta;
    }
}
