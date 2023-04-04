package com.study.servlet.repository;

import com.study.servlet.entity.User;

public interface UserRepository {
	public int save(User user);
	public User findUserByUsername(String username);
	
}
