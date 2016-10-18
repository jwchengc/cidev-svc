package com.isoftstone.cityinsight.cidev.provider.dao.mapper;

import org.apache.ibatis.annotations.Param;

import com.isoftstone.cityinsight.cidev.api.domain.User;

public interface UserMapper {
	int create(User user);
	User getById(@Param("id") String id);
	User getByName(@Param("name") String name);
}
