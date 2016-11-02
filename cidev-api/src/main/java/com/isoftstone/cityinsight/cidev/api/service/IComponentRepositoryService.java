package com.isoftstone.cityinsight.cidev.api.service;

import java.util.List;
import java.util.Map;

import com.isoftstone.cityinsight.cidev.api.domain.Application;

public interface IComponentRepositoryService {
	
	public List<Application> getRecommendComponents(String userId, Integer pageNum, Integer pageSize);
	
	List<Application> getPopularComponents(Integer pageNum, Integer pageSize);
	
	List<Application> getLatestComponents(Integer pageNum, Integer pageSize);
	
	public List<Application> getAllComponents(String serviceCategory, Integer pageNum, Integer pageSize);
	
	public Map<String, Object> getComponentsDetail(String appId, String versionId);
}
