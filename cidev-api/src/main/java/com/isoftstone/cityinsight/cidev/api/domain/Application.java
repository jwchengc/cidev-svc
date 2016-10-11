package com.isoftstone.cityinsight.cidev.api.domain;

import java.util.Date;

/**
 * Created by jeff on 10/11/16.
 */
public class Application {
    private String appId;
    private String appName;
    private String appType;
    private String svcCategoryId;
    private Date createdAt;
    private String createdBy;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
