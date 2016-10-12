package com.isoftstone.cityinsight.cidev.api.service;

import java.util.List;

import com.isoftstone.cityinsight.cidev.api.domain.File;
import com.isoftstone.cityinsight.cidev.api.domain.Project;

public interface IEditorService {
	List<Project> selectProjectsByUserId(String userId, Integer pageNum, Integer pageSize);
	
	void saveProjectInfo(Project project, File file);
}
