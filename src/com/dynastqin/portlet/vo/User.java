package com.dynastqin.portlet.vo;

import java.util.Date;

import com.dynastqin.portletcore.util.DateUtils;


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
	private String birthFmt;

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
//设置日期格式
	public String getBirthFmt() {
		if (birth == null)
			return "";
		else
			return DateUtils.format(birth);
	}

	public void setBirthFmt(String birthFmt) {
		this.birthFmt = birthFmt;
	}

}
