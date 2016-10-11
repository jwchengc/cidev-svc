package com.isoftstone.cityinsight.cidev.provider.service.impl;

import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.isoftstone.cityinsight.cidev.api.domain.Application;
import com.isoftstone.cityinsight.cidev.api.domain.Demo;
import com.isoftstone.cityinsight.cidev.api.service.IServiceRepositoryService;

public class ServiceRepositoryServiceImpl implements IServiceRepositoryService {

	@Override
	public List<Application> getRecommendServices(String userId, Integer pageNum, Integer pageSize) {
		Page<Demo> page = PageHelper.startPage(pageNum, pageSize);
		return null;
	}

	@Override
	public List<Application> getAllServices(Integer pageNum, Integer pageSize) {
		Page<Demo> page = PageHelper.startPage(pageNum, pageSize);
		return null;
	}

	@Override
	public Application getServiceDetail(String appId) {
		// TODO Auto-generated method stub
		return null;
	}

}
