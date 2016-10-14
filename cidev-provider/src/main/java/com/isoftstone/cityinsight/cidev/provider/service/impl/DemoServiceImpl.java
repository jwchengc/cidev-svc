package com.isoftstone.cityinsight.cidev.provider.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.isoftstone.cityinsight.cidev.api.domain.Demo;
import com.isoftstone.cityinsight.cidev.api.service.DemoService;
import com.isoftstone.cityinsight.cidev.provider.dao.mapper.DemoMapper;

@Service
public class DemoServiceImpl implements DemoService {

	@Autowired
	private DemoMapper demoMapper;
	
	@Override
	public List<Demo> list() {
		Page<Demo> page = PageHelper.startPage(1, 10);
		this.demoMapper.list();
		return page.getResult();
	}
	
}
