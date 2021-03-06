package com.isoftstone.cityinsight.cidev.api.domain;

import java.io.Serializable;

public class FilesAssoc implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5831445000830384266L;
	private String fileAssocId;
	private String versionId;
	private String fileId;
	private Integer fileType;
	private Integer fileSeq;
	
	public String getFileAssocId() {
		return fileAssocId;
	}
	public void setFileAssocId(String fileAssocId) {
		this.fileAssocId = fileAssocId;
	}
	public String getVersionId() {
		return versionId;
	}
	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public Integer getFileType() {
		return fileType;
	}
	public void setFileType(Integer fileType) {
		this.fileType = fileType;
	}
	public Integer getFileSeq() {
		return fileSeq;
	}
	public void setFileSeq(Integer fileSeq) {
		this.fileSeq = fileSeq;
	}
}
