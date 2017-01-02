package com.dynastqin.portlet.vo;

/**
 * 角色管理
 * @author
 */
public class Role {

	private Integer roleId; //主键
	private String roleName; //角色名称

	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
