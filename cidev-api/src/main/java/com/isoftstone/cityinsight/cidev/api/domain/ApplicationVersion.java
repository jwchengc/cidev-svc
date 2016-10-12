package com.isoftstone.cityinsight.cidev.api.domain;

import java.util.Date;

public class ApplicationVersion {
	private String versionId;
	private String appId;
	private String appVersion;
	private String description;
	private String versionInfo;
	private String keyword;
	private String copyright;
	private String linkMan;
	private String mobile;
	private String address;
	private Integer currentAuditStatus;
	private Integer isOnlinedStatus;
	private String createdBy;
	private Date createdAt;
	
	public String getVersionId() {
		return versionId;
	}
	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getAppVersion() {
		return appVersion;
	}
	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getVersionInfo() {
		return versionInfo;
	}
	public void setVersionInfo(String versionInfo) {
		this.versionInfo = versionInfo;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getCopyright() {
		return copyright;
	}
	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}
	public String getLinkMan() {
		return linkMan;
	}
	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Integer getCurrentAuditStatus() {
		return currentAuditStatus;
	}
	public void setCurrentAuditStatus(Integer currentAuditStatus) {
		this.currentAuditStatus = currentAuditStatus;
	}
	public Integer getIsOnlinedStatus() {
		return isOnlinedStatus;
	}
	public void setIsOnlinedStatus(Integer isOnlinedStatus) {
		this.isOnlinedStatus = isOnlinedStatus;
	}

}
