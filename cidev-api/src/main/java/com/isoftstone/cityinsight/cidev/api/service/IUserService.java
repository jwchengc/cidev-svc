package com.isoftstone.cityinsight.cidev.api.service;

import com.isoftstone.cityinsight.cidev.api.domain.User;

public interface IUserService {
	public User createUser(User user);
	public User getUserById(String id);
	public User getUserByName(String name);
}
