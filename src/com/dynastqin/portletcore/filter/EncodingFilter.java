package com.dynastqin.portletcore.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Servlet implementation class EncodingFilter
 */
public class EncodingFilter implements Filter {
	private static final long serialVersionUID = 1L;
	private String charSet; //设置字符编码
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.charSet=filterConfig.getInitParameter("charset");//取得初始化参数
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding(this.charSet);
		response.setCharacterEncoding(this.charSet);
		chain.doFilter(request, response);//必须写,否则会出现页面为空白的错误！
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
