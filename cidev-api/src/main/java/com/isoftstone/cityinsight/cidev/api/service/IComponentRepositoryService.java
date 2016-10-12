package com.isoftstone.cityinsight.cidev.api.service;

import java.util.List;
import java.util.Map;

import com.isoftstone.cityinsight.cidev.api.domain.Application;

public interface IComponentRepositoryService {
	
	public List<Application> getRecommendComponents(String userId, Integer pageNum, Integer pageSize);
	
	public List<Application> getAllComponents(Integer pageNum, Integer pageSize);
	
	public Map<String, Object> getComponentsDetail(String appId);
}
