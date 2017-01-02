package com.dynastqin.portlet.action;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.dynastqin.portlet.service.RoleService;
import com.dynastqin.portlet.service.UserService;
import com.dynastqin.portlet.util.StringUtil;
import com.dynastqin.portlet.vo.User;
import com.dynastqin.portlet.vo.User_Role;
import com.dynastqin.portletcore.util.Constants;
import com.dynastqin.portletcore.util.DateUtils;
import com.dynastqin.portletcore.util.HTTPUtil;
import com.dynastqin.portletcore.util.JSONUtil;
import com.dynastqin.portletcore.util.PageUtil;

/**
 * 用户管理Action
 * @author tantao
 *
 */
public class UserAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static UserService userService;
	static RoleService roleService;
	
	static{
		userService = new UserService();
		roleService=new RoleService();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String method = StringUtil.toLowerUpperCase(request.getParameter("method"),0);
		if("toadd".equals(method)){
			toAddUser(request, response);
		}
		else if("add".equals(method)) {
			addUser(request,response);
		}
		else if("toupdate".equals(method)){
			toUpdateUser(request,response);
		}
		else if ("update".equals(method)) {
			 updateUser(request,response);
		}
		else if ("delete".equals(method)) {
			 deleteUser(request,response);
		}
		else if("list".equals(method)){
			getJsonData(request, response);
		}
		else if("logout".equals(method)){
			logout(request,response);
		}
		else {
			listUser(request, response);
		}
	}

	/**
	 * 返回用户列表
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void listUser(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			req.getRequestDispatcher("UserManage/user-list.jsp").forward(req, resp);
		} catch (Exception e) {
			//e.printStackTrace();
			req.setAttribute("errorMsg", e.getMessage());
			req.getRequestDispatcher("error.jsp").forward(req, resp);
		}
	}
	/**
	 * 获取用户列表数据
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void getJsonData(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			String pageIndex = req.getParameter("pageIndex");
			String userName = req.getParameter("searchUser");
			if (pageIndex == null||pageIndex=="")
				pageIndex = "1";
			PageUtil page = new PageUtil(3, Integer.parseInt(pageIndex));
			page.setTotalRecords(userService.getUserSize(userName));// 设置总记录数

			//查询条件
			User user=new User();
			user.setUserName(userName);
			List<User_Role> userList = userService.getUsers(user, page);
			// Map
			Map<String, Object> map = new HashMap<String, Object>(3);
			map.put("page",JSONObject.fromObject(page));
			map.put("data", JSONUtil.listToJson(userList));
			map.put(Constants.RESULT_CODE, Constants.RESULT_CODE_SUCCESS);
			String strMapJson = JSONUtil.mapToJson(map).toString();

			resp.getWriter().print(strMapJson);
		} catch (Exception e) {
			//e.printStackTrace();
			req.setAttribute("errorMsg", e.getMessage());
			req.getRequestDispatcher("error.jsp").forward(req, resp);
		}
	}
	
	/**
	 * 去增加用户页面
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void toAddUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		try {
			req.setAttribute("roles", roleService.getRoles());
			req.setAttribute("user", null);
			req.getRequestDispatcher("UserManage/user-edit.jsp").forward(req, resp);
		} catch (Exception e) {
			//e.printStackTrace();
			req.setAttribute("errorMsg", e.getMessage());
			req.getRequestDispatcher("error.jsp").forward(req, resp);
		}
	}
	
	/**
	 * 增加用户
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void addUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		Map<String,Object> map=new HashMap<String,Object>();
		try {
			User user=new User();
			user.setUserName(req.getParameter("userName"));
			user.setRoleId(StringUtil.toInteger(req.getParameter("roleId")));
			user.setBirth(DateUtils.strToDate(req.getParameter("birth")));
		
			if(userService.addUser(user))
				map.put(Constants.RESULT_CODE, Constants.RESULT_CODE_SUCCESS);
			else
				map.put(Constants.RESULT_CODE, Constants.RESULT_CODE_FAILURE);
		} catch (Exception e) {
			//e.printStackTrace();
			map.put(Constants.RESULT_CODE, Constants.RESULT_CODE_ERROR);
			HTTPUtil.setSessionAttribute(req, Constants.ERROR_MSG, e.getMessage());
		}
		resp.getWriter().print(JSONUtil.mapToJson(map));
	}
	
	/**
	 * 去修改用户页面
	 * 
	 * @param request
	 *            httpRequest对象
	 * @return
	 */
	private void toUpdateUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		try {
			String strId = req.getParameter("id");
			req.setAttribute("user", userService.findUserById(strId));
			req.setAttribute("pageIndex", req.getParameter("pageIndex"));//得到当前页
			req.setAttribute("roles", roleService.getRoles());
			req.getRequestDispatcher("UserManage/user-edit.jsp").forward(req, resp);
		} catch (Exception e) {
			//e.printStackTrace();
			req.setAttribute("errorMsg", e.getMessage());
			req.getRequestDispatcher("error.jsp").forward(req, resp);
		}
	}
	
	/**
	 * 修改用户
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void updateUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		Map<String,Object> map=new HashMap<String,Object>();
		try {
			User user=new User();
			user.setUserId(StringUtil.toInteger(req.getParameter("userId")));
			user.setUserName(req.getParameter("userName"));
			user.setRoleId(StringUtil.toInteger(req.getParameter("roleId")));
			user.setBirth(DateUtils.strToDate(req.getParameter("birth")));
		
			if(userService.updateUser(user))
				map.put(Constants.RESULT_CODE, Constants.RESULT_CODE_SUCCESS);
			else
				map.put(Constants.RESULT_CODE, Constants.RESULT_CODE_FAILURE);
		} catch (Exception e) {
			//e.printStackTrace();
			map.put(Constants.RESULT_CODE, Constants.RESULT_CODE_ERROR);
			HTTPUtil.setSessionAttribute(req, Constants.ERROR_MSG, e.getMessage());
		}
		resp.getWriter().print(JSONUtil.mapToJson(map));
	}
	
	/**
	 * 删除用户
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		try {
			String strId = req.getParameter("id");
			Map<String,Object> map=new HashMap<String,Object>();
			if(userService.deleteUser(strId))
				map.put(Constants.RESULT_CODE, Constants.RESULT_CODE_SUCCESS);
			else
				map.put(Constants.RESULT_CODE, Constants.RESULT_CODE_FAILURE);
			resp.getWriter().print(JSONUtil.mapToJson(map));
		} catch (Exception e) {
			//e.printStackTrace();
			req.setAttribute("errorMsg", e.getMessage());
			req.getRequestDispatcher("error.jsp").forward(req, resp);
		}
	}

	/**
	 * 退出
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		try{
		if(req.getAttribute(Constants.WEB_USER_SESSION)!=null){
			req.removeAttribute(Constants.WEB_USER_SESSION);
		}
		HTTPUtil.redirectURL(req, resp, req.getContextPath()+"/login.jsp");
		} catch (Exception e) {
			//e.printStackTrace();
			req.setAttribute("errorMsg", e.getMessage());
			req.getRequestDispatcher("error.jsp").forward(req, resp);
		}
	}
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
