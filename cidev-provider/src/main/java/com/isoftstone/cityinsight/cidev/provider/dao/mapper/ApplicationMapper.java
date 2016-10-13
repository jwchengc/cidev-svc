package com.isoftstone.cityinsight.cidev.provider.dao.mapper;

import java.util.List;
import java.util.Map;

import com.isoftstone.cityinsight.cidev.api.domain.Application;
import com.isoftstone.cityinsight.cidev.api.domain.File;
import com.isoftstone.cityinsight.cidev.api.domain.FilesAssoc;

public interface ApplicationMapper {
	
	List<Application> selectRecommendServices(Map<String, Object> params);
	
	List<Application> selectAllServices(Map<String, Object> params);
	
	Application selectServiceAndVersionDetail(Map<String, String> appIdAndVerId);
	
	List<File> selectServiceFileInfosByVersionId(String versionId);
	
	//-----------my service begin -----------------//
	
	List<Application> selectSelfOnlinedServices(Map<String, Object> params);
	
	List<Application> selectSelfNewestServices(Map<String, Object> params);
	
	List<Application> selectSelfAppVersionList(String appId);
	
	void saveApplication(Application application);
	
	void saveApplicationVersion(Application application);
	
	void saveFileAssoc(FilesAssoc filesAssoc);
	
	void saveFileAssocList(List<FilesAssoc> list);

}
