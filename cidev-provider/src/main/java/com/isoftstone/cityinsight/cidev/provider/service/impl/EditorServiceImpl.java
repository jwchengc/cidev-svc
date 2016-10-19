package com.isoftstone.cityinsight.cidev.provider.service.impl;

import java.util.List;
import java.util.UUID;

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
	public List<Project> selectProjectsByUserId(String userId, Integer pageNum, Integer pageSize) {
		Page<Project> page = PageHelper.startPage(pageNum, pageSize);
		projectMapper.selectProjectsByOwnerId(userId);
		return page.getResult();
	}

	@Override
	public void saveProjectInfo(Project project, File file) {
		projectMapper.save(project);
		projectMapper.saveProjectFile(file);
		FilesAssoc filesAssoc = new FilesAssoc();
		filesAssoc.setFileAssocId(UUID.randomUUID().toString());
		filesAssoc.setVersionId(project.getProjectId());
		filesAssoc.setFileId(file.getFileId());
		filesAssoc.setFileSeq(1);
		filesAssoc.setFileType(2);
		projectMapper.saveProjectFileAssoc(filesAssoc);
	}

}
