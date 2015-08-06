/*
 * @(#)EmailAttachmentBean.java 
 * 
 * Copyright (c)2005 Flipswap, Inc.
 * U.S.A. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Flipswap, Inc.
 * ("Confidential Information"). You shall not disclose such
 * Confidential Information and shall use it only in accordance with the terms
 * of the license agreement you entered into with Flipswap, Inc.
 */

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
