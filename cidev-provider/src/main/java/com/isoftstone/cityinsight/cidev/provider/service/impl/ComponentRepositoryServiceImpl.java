package com.isoftstone.cityinsight.cidev.provider.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.isoftstone.cityinsight.cidev.api.domain.Application;
import com.isoftstone.cityinsight.cidev.api.domain.File;
import com.isoftstone.cityinsight.cidev.api.service.IComponentRepositoryService;
import com.isoftstone.cityinsight.cidev.provider.dao.mapper.ApplicationMapper;

@Service
public class ComponentRepositoryServiceImpl implements IComponentRepositoryService {

	@Autowired
	private ApplicationMapper applicationMapper;
	
	@Override
	public List<Application> getRecommendComponents(String userId, Integer pageNum, Integer pageSize) {
		Page<Application> page = PageHelper.startPage(pageNum, pageSize);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("appType", "co");
		applicationMapper.selectRecommendServices(params);
		return page.getResult();
	}

	@Override
	public List<Application> getAllComponents(Integer pageNum, Integer pageSize) {
		Page<Application> page = PageHelper.startPage(pageNum, pageSize);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("appType", "co");
		applicationMapper.selectAllServices(params);
		return page.getResult();
	}

	@Override
	public Map<String, Object> getComponentsDetail(String appId, String versionId) {
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, String> appIdAndVerId = new HashMap<String, String>();
		appIdAndVerId.put("appId", appId);
		appIdAndVerId.put("versionId", versionId);
		Application app = applicationMapper.selectServiceAndVersionDetail(appIdAndVerId);
		if (app != null) {
			List<File> files = applicationMapper.selectServiceFileInfosByVersionId(versionId);
			if (CollectionUtils.isNotEmpty(files)) {
				List<File> shotcuts = new ArrayList<File>();
				for (File file : files) {
					if (file.getFileType() == 0) {
						result.put("icon", file);
					} else if (file.getFileType() == 1) {
						shotcuts.add(file);
					} else if (file.getFileType() == 2) {
						result.put("source", file);
					}
				}
				result.put("shotcuts", shotcuts);
			}
			result.put("app", app);
			return result;
		}			
		return null;
	}

}
