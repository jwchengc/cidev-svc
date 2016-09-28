package com.isoftstone.cityinsight.cidev.api.domain;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public class Demo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	@JsonFormat(shape=Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+08:00")
	@JsonProperty("created_at")
	private Date createdAt;
	@JsonFormat(shape=Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+08:00")
	@JsonProperty("updated_at")
	private Date updatedAt;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

}
