package com.isoftstone.cityinsight.cidev.api.domain;

import java.io.Serializable;

/**
 * Created by jeff on 10/11/16.
 */
public class Application extends ApplicationVersion implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 5234497012764089877L;
//    private String appId;
    private String appName;
    private String appType;
    private String svcCategoryId;
    private Integer publishedChannel;
//    private Date createdAt;
//    private String createdBy;
    
    //非持久化属性
    private String fileUrl;

    public Integer getPublishedChannel() {
		return publishedChannel;
	}

	public void setPublishedChannel(Integer publishedChannel) {
		this.publishedChannel = publishedChannel;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public String getSvcCategoryId() {
        return svcCategoryId;
    }

    public void setSvcCategoryId(String svcCategoryId) {
        this.svcCategoryId = svcCategoryId;
    }

//    public Date getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(Date createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public String getCreatedBy() {
//        return createdBy;
//    }
//
//    public void setCreatedBy(String createdBy) {
//        this.createdBy = createdBy;
//    }
}
