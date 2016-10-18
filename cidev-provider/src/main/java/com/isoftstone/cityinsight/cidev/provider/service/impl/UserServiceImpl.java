package com.isoftstone.cityinsight.cidev.provider.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.isoftstone.cityinsight.cidev.api.domain.User;
import com.isoftstone.cityinsight.cidev.api.service.IUserService;
import com.isoftstone.cityinsight.cidev.provider.dao.mapper.UserMapper;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User createUser(User user) {
		this.userMapper.create(user);
		return this.getUserByName(user.getName());
	}

	@Override
	public User getUserById(String id) {
		if(StringUtils.isBlank(id)) {
			throw new RuntimeException("id 不能为空");
		}
		return this.userMapper.getById(id.trim());
	}

	@Override
	public User getUserByName(String name) {
		if (StringUtils.isBlank(name)) {
			throw new RuntimeException("名称不能为空");
		}
		return this.userMapper.getByName(name);
	}
	
	
	

}
