package com.isoftstone.cityinsight.cidev.api.service;

import java.util.List;
import java.util.Map;

import com.isoftstone.cityinsight.cidev.api.domain.Application;

public interface IServiceRepositoryService {

	public List<Application> getRecommendServices(String userId, Integer pageNum, Integer pageSize);
	
	public List<Application> getAllServices(Integer pageNum, Integer pageSize);
	
	public Map<String, Object> getServiceDetail(String appId, String versionId);
}
