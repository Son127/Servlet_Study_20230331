package com.study.servlet.entity.repository;

import com.study.servlet.entity.User;

public interface UserRepository {
	public int save(User user);
	public User findUserByUsername(String username);
	
}
