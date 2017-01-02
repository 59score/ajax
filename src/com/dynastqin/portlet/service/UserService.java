package com.dynastqin.portlet.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dynastqin.portlet.dao.IUserDao;
import com.dynastqin.portlet.dao.impl.UserDaoImpl;
import com.dynastqin.portlet.vo.User;
import com.dynastqin.portlet.vo.User_Role;
import com.dynastqin.portletcore.util.PageUtil;

public class UserService {
	private IUserDao userDao;

	public UserService() {
		userDao = new UserDaoImpl();
	}

	public User findUserById(String id) throws Exception {
		return userDao.findById(id);

	}

	public List<User_Role> getUsers() throws Exception {
		return userDao._list();
	}

	/**
	 * 根据条件查询
	 * 
	 * @throws Exception
	 */
	public List<User_Role> getUsers(User user, PageUtil page) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if (user != null)
			map.put("userName", user.getUserName());

		map.put("skip", page.getSkip());
		map.put("limit", page.getPageSize());
		return userDao._list(map);
	}

	/**
	 * 获取用户记录数
	 * 
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public int getUserSize(String userName) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if (userName != null)
			map.put("userName", userName);
		return userDao.getSize(map);
	}

	/**
	 * 删除用户
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean deleteUser(String id) throws Exception {
		return userDao.delete(id) > 0 ? true : false;

	}

	public boolean updateUser(User user) throws Exception {
		return userDao.update(user) > 0 ? true : false;
	}

	public boolean addUser(User user) throws Exception {
		return userDao.add(user) > 0 ? true : false;
	}
	
	public User loginCheck(User user) throws Exception{
		return userDao.getUserByNamePwd(user);
	}

	// test
	public static void main(String[] args) throws Exception {
		List<User_Role> userList = new UserService().getUsers();
		if (userList.size() > 0) {
			for (User_Role user : userList) {
				System.out.println("id:" + user.getUserId());
				System.out.println("username:" + user.getUserName());
			}
		}
	}

}
