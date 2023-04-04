package com.study.servlet.service;

import com.study.servlet.entity.User;

public interface UserService {
	public int addUser(User user);
	public User getUser(String username);
	public boolean duplicatedUsername(String username);
}
