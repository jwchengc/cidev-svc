package com.isoftstone.cityinsight.cidev.api.service;

import java.util.List;
import java.util.Map;

import com.isoftstone.cityinsight.cidev.api.domain.Application;

public interface IServiceRepositoryService {

	public List<Application> getRecommendServices(String userId, Integer pageNum, Integer pageSize);
	
	public List<Application> getAllServices(Integer pageNum, Integer pageSize);
	
	public Map<String, Object> getServiceDetail(String appId, String versionId);
	
	//-----------my service begin -----------------//
	public List<Application> selectSelfOnlinedServices(String userId, String appType, Integer pageNum, Integer pageSize);
	
	public List<Application> selectSelfNewestServices(String userId, String appType, Integer pageNum, Integer pageSize);
	
	List<Application> selectSelfAppVersionList(String appId, Integer pageNum, Integer pageSize);
	
	void save(Application application, String iconId, String srcId, String shotcutIds);
}
