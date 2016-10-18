package com.isoftstone.cityinsight.cidev.provider.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.isoftstone.cityinsight.cidev.api.domain.Application;
import com.isoftstone.cityinsight.cidev.api.domain.File;
import com.isoftstone.cityinsight.cidev.api.domain.FilesAssoc;
import com.isoftstone.cityinsight.cidev.api.service.IServiceRepositoryService;
import com.isoftstone.cityinsight.cidev.provider.dao.mapper.ApplicationMapper;

@Service
public class ServiceRepositoryServiceImpl implements IServiceRepositoryService {

	@Autowired
	private ApplicationMapper applicationMapper;

	@Override
	public List<Application> getRecommendServices(String userId, Integer pageNum, Integer pageSize) {
		Page<Application> page = PageHelper.startPage(pageNum, pageSize);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("appType", "cso");
		applicationMapper.selectRecommendServices(params);
		return page.getResult();
	}

	@Override
	public List<Application> getAllServices(String serviceCategory, Integer pageNum, Integer pageSize) {
		Page<Application> page = PageHelper.startPage(pageNum, pageSize);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("appType", "cso");
		params.put("serviceCategory", serviceCategory);
		applicationMapper.selectAllServices(params);
		return page.getResult();
	}

	@Override
	public Map<String, Object> getServiceDetail(String appId, String versionId) {
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

	
	/******************self service begin *********************/
	@Override
	public List<Application> selectSelfOnlinedServices(String userId, String appType, Integer pageNum,
			Integer pageSize) {
		Page<Application> page = PageHelper.startPage(pageNum, pageSize);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("appType", appType);
		params.put("userId", userId);
		applicationMapper.selectSelfOnlinedServices(params);
		return page.getResult();
	}

	@Override
	public List<Application> selectSelfNewestServices(String userId, String appType, Integer pageNum,
			Integer pageSize) {
		Page<Application> page = PageHelper.startPage(pageNum, pageSize);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("appType", appType);
		params.put("userId", userId);
		applicationMapper.selectSelfNewestServices(params);
		return page.getResult();
	}

	@Override
	public List<Application> selectSelfAppVersionList(String appId, Integer pageNum, Integer pageSize) {
		Page<Application> page = PageHelper.startPage(pageNum, pageSize);
		applicationMapper.selectSelfAppVersionList(appId);
		return page.getResult();
	}

	public void save(Application application, String iconId, String srcId, String shotcutIds) {
		if (application.getAppId() == null) {
			//save application
			application.setAppId(UUID.randomUUID().toString());
			applicationMapper.saveApplication(application);
		}
		application.setVersionId(UUID.randomUUID().toString());
		applicationMapper.saveApplicationVersion(application);
		
		FilesAssoc fassoc = new FilesAssoc();
		fassoc.setVersionId(application.getVersionId());
		//icon file file_type=0
		if (StringUtils.isNotEmpty(iconId)) {
			fassoc.setFileAssocId(UUID.randomUUID().toString());		
			fassoc.setFileId(iconId);
			fassoc.setFileType(0);
			fassoc.setFileSeq(1);
			applicationMapper.saveFileAssoc(fassoc);
		}
		
		if (StringUtils.isNotEmpty(srcId)) {
		// source file file_type=2
			fassoc.setFileAssocId(UUID.randomUUID().toString());
			fassoc.setFileId(srcId);
			fassoc.setFileType(2);
			fassoc.setFileSeq(1);
			applicationMapper.saveFileAssoc(fassoc);
		}
		
		if (StringUtils.isNotEmpty(shotcutIds)) {
		//shotcut files file_type=1
			String[] ids = StringUtils.split(shotcutIds, ',');
			List<FilesAssoc> stf = new ArrayList<FilesAssoc>(ids.length);
			for (int i = 0; i < ids.length; i++) {
				FilesAssoc filesAssoc = new FilesAssoc();
				filesAssoc.setVersionId(application.getVersionId());
				filesAssoc.setFileAssocId(UUID.randomUUID().toString());
				filesAssoc.setFileId(ids[i]);
				filesAssoc.setFileType(1);
				filesAssoc.setFileSeq(i + 1);
				stf.add(filesAssoc);
			}
			applicationMapper.saveFileAssocList(stf);
		}
		
		Map<String, Object> auditInfo = new HashMap<String, Object>();
		auditInfo.put("auditId", UUID.randomUUID().toString());
		auditInfo.put("versionId", application.getVersionId());
		auditInfo.put("auditStatus", 0);
		applicationMapper.appendAuditInfo(auditInfo);
	}

}
