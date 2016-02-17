package com.apexsoft.ysprj.applicants.common.domain;

import com.amazonaws.services.s3.model.ObjectMetadata;

/**
 * Created by hanmomhanda on 16. 2. 17.
 */
public class FileMeta {

    private String
        contentType,
        contentEncoding,
        ETag,
        lastModified;

    private long contentLength;

    public FileMeta() {}

    public FileMeta(ObjectMetadata metadata) {
        this.contentType = metadata.getContentType();
        this.contentEncoding = metadata.getContentEncoding();
        this.ETag = metadata.getETag();
        this.lastModified = metadata.getLastModified().toString();
        this.contentLength = metadata.getContentLength();
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public long getContentLength() {
        return contentLength;
    }

    public void setContentLength(long contentLength) {
        this.contentLength = contentLength;
    }

    public String getContentEncoding() {
        return contentEncoding;
    }

    public void setContentEncoding(String contentEncoding) {
        this.contentEncoding = contentEncoding;
    }

    public String getETag() {
        return ETag;
    }

    public void setETag(String ETag) {
        this.ETag = ETag;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

}
