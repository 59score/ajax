package com.dynastqin.portletcore.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dynastqin.portletcore.util.Constants;
import com.dynastqin.portletcore.util.HTTPUtil;

/**
 * Servlet Filter implementation class LoginFilter
 */
public class LoginFilter implements Filter {
	private String validURL;

	/**
	 * Default constructor.
	 */
	public LoginFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * 用户登录过滤
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpServletResponse httpResp = (HttpServletResponse) response;
		boolean isLoginURL = false;//是否为登录地址
		for (String loginURL : Constants.USERLOGIN_URL) {
			if (httpReq.getRequestURI().equals(
					httpReq.getContextPath() + loginURL)) {
				isLoginURL = true;
				break;
			}
		}
		if (!isLoginURL && httpReq.getSession().getAttribute(Constants.WEB_USER_SESSION) == null) {//用户未登陆
				HTTPUtil.redirectURLAndAlert(httpReq, httpResp,
						httpReq.getContextPath() + "/login.jsp", "请先重新登录！");
		} else {
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		this.validURL=fConfig.getInitParameter("validURL");//取得初始化参数
	}

}
