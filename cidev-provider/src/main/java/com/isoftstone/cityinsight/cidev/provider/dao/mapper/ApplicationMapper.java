package com.isoftstone.cityinsight.cidev.provider.dao.mapper;

import java.util.List;
import java.util.Map;

import com.isoftstone.cityinsight.cidev.api.domain.Application;
import com.isoftstone.cityinsight.cidev.api.domain.File;

public interface ApplicationMapper {
	
	List<Application> selectRecommendServices(Map<String, Object> params);
	
	List<Application> selectAllServices(Map<String, Object> params);
	
	Application selectServiceOnlineVersionDetail(String appId);
	
	List<File> selectServiceFileInfosByVersionId(String versionId);

}
