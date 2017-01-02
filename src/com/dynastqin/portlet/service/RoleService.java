package com.dynastqin.portlet.service;

import java.util.List;

import com.dynastqin.portlet.dao.IRoleDao;
import com.dynastqin.portlet.dao.impl.RoleDaoImpl;
import com.dynastqin.portlet.vo.Role;

public class RoleService {
	private IRoleDao roleDao;
	
	public RoleService(){
		this.roleDao=new RoleDaoImpl();
	}
	public List<Role> getRoles() throws Exception {
		return roleDao.list();
	}
}
