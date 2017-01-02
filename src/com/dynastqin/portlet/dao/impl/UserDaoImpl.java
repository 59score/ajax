package com.dynastqin.portlet.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.dynastqin.portlet.dao.IUserDao;
import com.dynastqin.portlet.vo.User;
import com.dynastqin.portlet.vo.User_Role;
import com.dynastqin.portletcore.dao.SQLHelper;

public class UserDaoImpl implements IUserDao {
	
	private static final String GET_ALL_USER="SELECT id,username,password,role_id,birth FROM user";
	
	private static final String GET_ALL_USER_ROLE="SELECT U.id,username,password,R.id,R.name,birth FROM user U LEFT JOIN role R ON U.role_id=R.id";
	
	private static final String GET_USER_SIZE="SELECT count(*) FROM user";
	
	private static final String DEL_USER_BY_ID="DELETE FROM user WHERE id=?";

	@Override
	public User findById(Object o) throws Exception {
		String strSQL=GET_ALL_USER;
		if(o!=null && o.toString()!=""){
			strSQL+=MessageFormat.format(" WHERE id=''{0}''",o.toString());
			return getUser(SQLHelper.executeQuery(strSQL,null));
		}else
			return null;
	}
	
	@Override
	public List<User_Role> _list() throws Exception {
		return getList(SQLHelper.executeQuery(GET_ALL_USER_ROLE, null));
	}

	/**
	 * 根据查询条件获取用户列表，并分页
	 */
	@Override
	public List<User_Role> _list(Map<String, Object> map) throws Exception {
		String strSQL=GET_ALL_USER_ROLE;
			if(map.get("userName")!=null && map.get("userName").toString()!=""){
				strSQL+=MessageFormat.format(" WHERE username LIKE ''%{0}%''",map.get("userName"));
			}
			if(map.get("skip")!=null && map.get("limit")!=null){
				strSQL+=MessageFormat.format(" limit {0},{1}",map.get("skip"),map.get("limit"));
			}
		return getList(SQLHelper.executeQuery(strSQL,null));
	}
	
	/**
	 * 获取用户记录数
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public int getSize(Map<String, Object> map) throws Exception{
		String strSQL=GET_USER_SIZE;
		if(map.get("userName")!=null && map.get("userName").toString()!=""){
			strSQL+=MessageFormat.format(" WHERE userName LIKE ''%{0}%''",map.get("userName"));
		}
		ResultSet rs=SQLHelper.executeQuery(strSQL,null);
		int returnVal=-1;
		if(rs.next())
			returnVal=rs.getInt(1);
		//关闭连接
		SQLHelper.close(rs, SQLHelper.getStatement(), SQLHelper.getCon());
		return returnVal;

	}

	@Override
	public int add(User user) throws Exception {
		StringBuilder strAdd=new StringBuilder("INSERT INTO user(");
		StringBuilder strVal=new StringBuilder(") VALUES(");
		if(user.getUserName()!=null){
			strAdd.append("username,");
			strVal.append("'"+user.getUserName()+"',");
		}
		if(user.getPassword()!=null){
			strAdd.append("password,");
			strVal.append("'"+user.getPassword()+"',");
		}
		if(user.getRoleId()!=null){			
			strAdd.append("role_id,");
			strVal.append(user.getRoleId()+",");
		}
		if(user.getBirth()!=null){		
			strAdd.append("birth,");
			strVal.append("'"+new java.sql.Date(user.getBirth().getTime())+"',");
		}
		
		String strSQL=strAdd.toString();
		String _strVal=strVal.toString();
		int returnVal=-1;
		if(!strSQL.toLowerCase().endsWith("user(")){
			strSQL=strSQL.substring(0,strSQL.length()-1)+_strVal.substring(0,_strVal.length()-1)+")";
			returnVal=SQLHelper.executeDML(strSQL, null);
		}
		return returnVal;
	}

	@Override
	public int delete(Object o) throws Exception {
		return SQLHelper.executeDML(DEL_USER_BY_ID, new String[]{o.toString()});
	}

	@Override
	public int update(User user) throws Exception {
		StringBuilder strSQL=new StringBuilder("UPDATE user SET ");
		if(user.getUserName()!=null)
			strSQL.append("username='"+user.getUserName()+"',");
		if(user.getPassword()!=null)
			strSQL.append("password='"+user.getPassword()+"',");
		if(user.getRoleId()!=null)
			strSQL.append("role_id="+user.getRoleId()+",");
		if(user.getBirth()!=null)
			strSQL.append("birth='"+new java.sql.Date(user.getBirth().getTime())+"',");
		
		String strUpdate=strSQL.toString();
		int returnVal=-1;
		if(!strUpdate.toLowerCase().endsWith("set ")&&user.getUserId()!=null){
			strUpdate=strUpdate.substring(0, strUpdate.length()-1)
					+" where id='"+user.getUserId()+"'";
			returnVal=SQLHelper.executeDML(strUpdate, null);
		}
		return returnVal;
	}

	/**
	 * 读取数据库数据,获取用户列表
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private List<User_Role> getList(ResultSet rs) throws SQLException{
		List<User_Role> userList=new ArrayList<User_Role>();
		User_Role user=null;
		while(rs.next()){
			user=new User_Role();
			user.setUserId(rs.getInt(1));
			user.setUserName(rs.getString(2));
			user.setPassword(rs.getString(3));
			user.setRoleId(rs.getInt(4));
			user.setRoleName(rs.getString(5));
			if(rs.getDate(6)!=null)
				user.setBirth(new Date(rs.getDate(6).getTime()));
			userList.add(user);
		}
		//关闭连接
		SQLHelper.close(rs, SQLHelper.getStatement(), SQLHelper.getCon());
		return userList;
	}
	
	/**
	 * 读取数据库数据,获取单个用户
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private User getUser(ResultSet rs) throws SQLException{
		User user=null;
		if(rs.next()){
			user=new User();
			user.setUserId(rs.getInt(1));
			user.setUserName(rs.getString(2));
			user.setPassword(rs.getString(3));
			user.setRoleId(rs.getInt(4));
			if(rs.getDate(5)!=null)
				user.setBirth(new Date(rs.getDate(5).getTime()));
		}
		//关闭连接
		SQLHelper.close(rs, SQLHelper.getStatement(), SQLHelper.getCon());
		return user;
	}

	@Override
	public List<User> list() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> list(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 根据用户名，密码获取用户
	 * @param user 保存用户名，密码
	 */
	@Override
	public User getUserByNamePwd(User user) throws Exception {
		String strSQL=GET_ALL_USER;
		if(user.getUserName()!=null && user.getPassword()!=null){
			strSQL+=MessageFormat.format(" WHERE username=''{0}'' AND password=''{1}''", user.getUserName(),user.getPassword());
			return getUser(SQLHelper.executeQuery(strSQL,null));
		}
		else
			return null;
	}
}
