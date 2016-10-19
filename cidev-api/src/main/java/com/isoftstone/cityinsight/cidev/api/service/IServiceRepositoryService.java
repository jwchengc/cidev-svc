package com.isoftstone.cityinsight.cidev.api.service;

import java.util.List;
import java.util.Map;

import com.isoftstone.cityinsight.cidev.api.domain.Application;

public interface IServiceRepositoryService {

	List<Application> getRecommendServices(String userId, Integer pageNum, Integer pageSize);
	
	List<Application> getLatestServices(String userId, Integer pageNum, Integer pageSize);
	
	List<Application> getPopularServices(String userId, Integer pageNum, Integer pageSize);
	
	List<Application> getAllServices(String serviceCategory, Integer pageNum, Integer pageSize);
	
	Map<String, Object> getServiceDetail(String appId, String versionId);
	
	List<Map<String, Object>> getAllSvcCategory();
	
	//-----------my service begin -----------------//
	List<Application> selectSelfOnlinedServices(String userId, String appType, Integer pageNum, Integer pageSize);
	
	List<Application> selectSelfNewestServices(String userId, String appType, Integer pageNum, Integer pageSize);
	
	List<Application> selectSelfAppVersionList(String appId, Integer pageNum, Integer pageSize);
	
	void save(Application application, String iconId, String srcId, String shotcutIds);
}
