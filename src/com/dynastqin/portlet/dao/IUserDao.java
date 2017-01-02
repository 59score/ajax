package com.dynastqin.portlet.dao;

import java.util.List;
import java.util.Map;

import com.dynastqin.portlet.vo.User;
import com.dynastqin.portlet.vo.User_Role;
import com.dynastqin.portletcore.dao.IBaseDao;

public interface IUserDao extends IBaseDao<User> {
	
	List<User_Role> _list() throws Exception;
	
	List<User_Role> _list(Map<String, Object> map) throws Exception;

	int getSize(Map<String, Object> map) throws Exception;
	
	User getUserByNamePwd(User user) throws Exception;

}
