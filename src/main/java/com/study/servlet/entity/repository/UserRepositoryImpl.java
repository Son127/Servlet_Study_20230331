package com.study.servlet.entity.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mysql.cj.xdevapi.Statement;
import com.study.servlet.entity.User;
import com.study.servlet.util.DBConnectionMgr;

public class UserRepositoryImpl implements UserRepository {
	
	//Repository 싱글톤
	private static UserRepository instance;
	public static UserRepository getInstance() {
		if(instance == null) {
			instance = new UserRepositoryImpl(); 
		}
		return instance;
	}
	
	//DBConnectionMgr DI
	private DBConnectionMgr pool;
	
	private UserRepositoryImpl() {
		pool = DBConnectionMgr.getInstance();
	}
	
	@Override
	public int save(User user) {
		
		String sql = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int successCount = 0;
		
		try {
			con = pool.getConnection();
			String sql1 = "insert into user_mst values(0, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql1, pstmt.RETURN_GENERATED_KEYS);
			
			pstmt.setInt(1, user.getUserId());
			pstmt.setString(2, user.getUsername());
			pstmt.setString(3, user.getPassword());
			pstmt.setString(4, user.getName());
			pstmt.setString(5, user.getEmail());
			successCount = pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt);
		}
		
		return successCount;
	}
	
	@Override
	public User findUserByUsername(String username) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User user = new User();
		
		try {
			con = pool.getConnection();		// db연결
			String sql = "select\r\n"
					+ "	um.user_id,\r\n"
					+ "    um.username,\r\n"
					+ "    um.password,\r\n"
					+ "    um.name,\r\n"
					+ "    um.email,\r\n"
					+ "    ud.gender,\r\n"
					+ "    ud.birthday,\r\n"
					+ "    ud.address\r\n"
					+ "from \r\n"
					+ "	user_mst um\r\n"
					+ "    left outer join user_dtl ud on(ud.user_id = um.user_id)\r\n"
					+ "where\r\n"
					+ "	um.username = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				user = User.builder()
						.userId(rs.getInt(1))
						.username(rs.getString(2))
						.password(rs.getString(3))
						.name(rs.getString(4))
						.email(rs.getString(5))
						.build();
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		
		return user;
	}

}
