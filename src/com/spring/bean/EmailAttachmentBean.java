package com.spring.bean;

import java.io.Serializable;

import javax.activation.DataSource;

public class EmailAttachmentBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private byte[] attachment;
    private String fileName;
    private String mimeType;
    private DataSource dataSource;

    public byte[] getAttachment() {
        return attachment;
    }

    public void setAttachment(byte[] attachment) {
        this.attachment = attachment;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

}
