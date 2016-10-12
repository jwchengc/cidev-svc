package com.isoftstone.cityinsight.cidev.provider.dao.mapper;

import java.util.List;

import com.isoftstone.cityinsight.cidev.api.domain.File;
import com.isoftstone.cityinsight.cidev.api.domain.FilesAssoc;
import com.isoftstone.cityinsight.cidev.api.domain.Project;

public interface ProjectMapper {
	
	List<Project> selectProjectsByOwnerId(String ownerId);
	
	void save(Project project);
	
	void saveProjectFile(File file);
	
	void saveProjectFileAssoc(FilesAssoc filesAssoc);
}
