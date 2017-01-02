package com.dynastqin.portlet.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dynastqin.portlet.service.UserService;
import com.dynastqin.portlet.vo.User;
import com.dynastqin.portletcore.util.Constants;
import com.dynastqin.portletcore.util.HTTPUtil;

/**
 * 用户登录Action
 */
public class LoginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static UserService userService;

	static {
		userService = new UserService();
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		try {
			User user = new User();
			user.setUserName(request.getParameter("userName"));
			user.setPassword(request.getParameter("password"));
			if (user.getUserName() != null) {
				User userInfo = null;
				if ((userInfo = userService.loginCheck(user)) != null) {
					request.getSession().removeAttribute(Constants.WEB_USER_SESSION);
					request.getSession().setAttribute(Constants.WEB_USER_SESSION, userInfo);

					response.sendRedirect("UserAction");// 登录成功进入用户列表
				} else {
					HTTPUtil.redirectURLAndAlert(request, response, request.getContextPath()+"/login.jsp", "用户名或密码错误！");
				}
			} else {
				HTTPUtil.redirectURL(request, response, request.getContextPath()+"/login.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
