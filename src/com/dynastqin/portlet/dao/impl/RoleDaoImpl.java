package com.dynastqin.portlet.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.dynastqin.portlet.dao.IRoleDao;
import com.dynastqin.portlet.vo.Role;
import com.dynastqin.portletcore.dao.SQLHelper;

public class RoleDaoImpl implements IRoleDao {
	private static final String GET_ALL_ROLE="SELECT id,name FROM role";

	@Override
	public Role findById(Object o) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> list() throws Exception {
		return getList(SQLHelper.executeQuery(GET_ALL_ROLE, null));
	}

	@Override
	public List<Role> list(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int add(Role t) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Object o) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Role t) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	private List<Role> getList(ResultSet rs) throws SQLException{
		List<Role> roleList=new ArrayList<Role>();
		Role role=null;
		while(rs.next()){
			role=new Role();
			role.setRoleId(rs.getInt(1));
			role.setRoleName(rs.getString(2));
			roleList.add(role);
		}
		//关闭连接
		SQLHelper.close(rs, SQLHelper.getStatement(), SQLHelper.getCon());
		return roleList;
	}
}
