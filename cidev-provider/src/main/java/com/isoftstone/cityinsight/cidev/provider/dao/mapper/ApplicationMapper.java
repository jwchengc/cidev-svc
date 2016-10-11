package com.isoftstone.cityinsight.cidev.provider.dao.mapper;

import java.util.List;
import java.util.Map;

import com.isoftstone.cityinsight.cidev.api.domain.Application;

public interface ApplicationMapper {
	
	public List<Application> selectRecommendServices(Map<String, Object> params);

}
