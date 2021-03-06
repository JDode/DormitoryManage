package me.codegc.manage.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import me.codegc.manage.dao.AdminDao;
import me.codegc.manage.model.Admin;
import me.codegc.manage.utils.JDBCUtil;


public class AdminDaoimplement implements AdminDao {

	// 获取conn
	private Connection conn = JDBCUtil.init(JDBCUtil.CONFIG).getConnection();
	/**
	 *	查询所有管理员用户
	 */
	@Override
	public List<Admin> findAllAdmin() {
		
		String sql = "SELECT * FROM t_user WHERE  typeID = 2";
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Admin> lists = new ArrayList<Admin>();
		try {
			ps = conn.prepareStatement(sql);
			// 执行
			rs = ps.executeQuery();
			if (rs.next()) {
				lists.add(new Admin(
						rs.getString("account"), 
						rs.getString("password"),
						rs.getString("userName"),
						rs.getString("phoneNumber"),
						rs.getString("picURL"),
						rs.getByte("typeID")
						));
			}
			// 保存数据
			return lists;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			JDBCUtil.closeConnection(conn);
		}
		return null;
	}
	/**
	 * 根据账号查询 用户
	 */
	@Override
	public Admin findByAccount(String account) {
		String sql = "SELECT * FROM t_user WHERE account = ? AND typeID = 2";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Admin admin = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, account);
			// 执行
			rs = ps.executeQuery();
			if (rs.next()) {
				// 保存数据
				admin = new Admin(
						rs.getString("account"), 
						rs.getString("password"),
						rs.getString("userName"),
						rs.getString("phoneNumber"),
						rs.getString("picURL"),
						rs.getByte("typeID")
						);
				return admin;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			JDBCUtil.closeConnection(conn);
		}
		return admin;
	}
	
	/**
	 * 根据账号和密码查询管理员
	 */
	@Override
	public Admin findByAccountAndPassword(String account, String password) {
		String sql = "SELECT * FROM t_user WHERE account = ? AND password = ? AND typeID = 2";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Admin admin = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, account);
			ps.setString(2, password);
			// 执行
			rs = ps.executeQuery();
			if (rs.next()) {
				// 保存数据
				admin = new Admin(
						rs.getString("account"), 
						rs.getString("password"),
						rs.getString("userName"),
						rs.getString("phoneNumber"),
						rs.getString("picURL"),
						rs.getByte("typeID")
						);
				return admin;
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			JDBCUtil.closeConnection(conn);
		}
		return admin;
	}

}
