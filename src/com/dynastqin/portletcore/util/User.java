package com.dynastqin.portletcore.util;

import java.util.Date;


/**
 * 人员管理
 * 
 * @author
 */
public class User {

	private Integer userId; // 主键
	private String userName; // 登录用户
	private String password; // 登录密码
	private Integer roleId; // 角色ID
	private Date birth;// 生日

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}
}
