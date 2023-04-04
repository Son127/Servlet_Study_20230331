package com.study.servlet.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.study.servlet.entity.Role;
import com.study.servlet.util.DBConnectionMgr;

public class RoleRepositoryImpl implements RoleRepository {
	
	//Repository 싱글톤
	private static RoleRepository instance;
	public static RoleRepository getInstance() {
		if(instance == null) {
			instance = new RoleRepositoryImpl(); 
		}
		return instance;
	}
	
	//DBConnectionMgr DI
	private DBConnectionMgr pool;
	
	private RoleRepositoryImpl() {
		pool = DBConnectionMgr.getInstance();
	}
	
	@Override
	public Role findRoleByRoleName(String roleName) {
		Connection  con = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		Role role = new Role();
		
		try {
			con = pool.getConnection();
			String sql = "select\r\n"
					+ "	rm.role_id\r\n"
					+ " rm.role_name\\r\\n"
					+ "from\r\n"
					+ "	role_mst rm\r\n"
					+ "where\r\n"
					+ "	rm.role_name = ?";
			ptmt = con.prepareStatement(sql);
			ptmt.setString(1, sql);
			rs = ptmt.executeQuery();
			
			if(rs.next()) {
				role = Role.builder()
						.roleId(rs.getInt(1))
						.roleName(rs.getString(2))
						.build();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, ptmt, rs); // 객체 소멸시켜주는 작업
		}
		
		
		return role;
	}

}
