package com.study.servlet.service;

import com.study.servlet.entity.Role;
import com.study.servlet.repository.RoleRepository;
import com.study.servlet.repository.RoleRepositoryImpl;
import com.study.servlet.util.DBConnectionMgr;

public class RoleServiceImp implements RoleService{

	private static RoleService instance;
	public static RoleService getInstance() {
		if(instance == null) {
			instance = new RoleServiceImp();
		}
		return instance;
	}
	
	private RoleRepository roleRepository;
	
	private RoleServiceImp() {
		roleRepository = RoleRepositoryImpl.getInstance();
	}
	
	@Override
	public Role getRole(String roleName) {
		return roleRepository.findRoleByRoleName(roleName) ;
	}
	
	

}
