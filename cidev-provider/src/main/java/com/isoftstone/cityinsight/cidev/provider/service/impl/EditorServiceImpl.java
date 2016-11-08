package com.isoftstone.cityinsight.cidev.provider.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.isoftstone.cityinsight.cidev.api.domain.File;
import com.isoftstone.cityinsight.cidev.api.domain.FilesAssoc;
import com.isoftstone.cityinsight.cidev.api.domain.Project;
import com.isoftstone.cityinsight.cidev.api.service.IEditorService;
import com.isoftstone.cityinsight.cidev.provider.dao.mapper.ProjectMapper;

@Service
public class EditorServiceImpl implements IEditorService {
	
	@Autowired
	private ProjectMapper projectMapper;

	@Override
	public List<Project> selectProjectsByUserId(String userId, String projectType, Integer pageNum, Integer pageSize) {
		Page<Project> page = PageHelper.startPage(pageNum, pageSize);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("ownerId", userId);
		params.put("projectType", StringUtils.trimToNull(projectType));
		projectMapper.selectProjectsByOwnerId(params);
		return page.getResult();
	}

	@Override
	public void saveProjectInfo(Project project) {
		projectMapper.save(project);
	}
	
	@Override
	public void syncProjectInfo(Boolean isUpdated, String projectId, File file) {
		if (isUpdated) {
			projectMapper.delFileByProjectId(projectId);
			projectMapper.saveProjectFile(file);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("fileId", file.getFileId());
			params.put("projectId", projectId);
			projectMapper.updateFileAssocInfo(params);
		} else {
			projectMapper.saveProjectFile(file);
			FilesAssoc filesAssoc = new FilesAssoc();
			filesAssoc.setFileAssocId(UUID.randomUUID().toString());
			filesAssoc.setVersionId(projectId);
			filesAssoc.setFileId(file.getFileId());
			filesAssoc.setFileSeq(1);
			filesAssoc.setFileType(2);
			projectMapper.saveProjectFileAssoc(filesAssoc);
		}
	}

}
