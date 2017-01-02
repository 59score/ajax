package com.dynastqin.portletcore.util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HTTPUtil {
	/**
	 * 重定位RUL
	 * @param req
	 * @param resp
	 * @param URL
	 * @throws IOException
	 */
	public static void redirectURL(HttpServletRequest req, HttpServletResponse resp,String URL) throws IOException{
		resp.setContentType("text/html;charset=UTF-8");
		resp.getWriter().write("<script language=\"javascript\">top.location.href='"
				+URL+"';</script>");
	}
	
	/**
	 * 提示消息并重定位RUL
	 * @param req
	 * @param resp
	 * @param Message 消息
	 * @param URL
	 * @throws IOException
	 */
	public static void redirectURLAndAlert(HttpServletRequest req, HttpServletResponse resp,String URL,String Message) throws IOException{
		resp.setContentType("text/html;charset=UTF-8");
		resp.getWriter().write(
				"<script language=\"javascript\">alert('"+Message+"');" +
 	     "top.location.href='"+URL+"';</script>");
	}
	
	/**
	 * 设置session值，若存在，先删除
	 * @param request
	 * @param key
	 * @param value
	 */
	public static void setSessionAttribute(HttpServletRequest request,
			String key, Object value) {
		removeSessionAttribute(request,key);
		request.getSession().setAttribute(key, value);		
	}
	/**
	 * 删除session值
	 * @param request
	 * @param key
	 */
	public static void removeSessionAttribute(HttpServletRequest request,
			String key) {
		HttpSession session = request.getSession();
		if (session.getAttribute(key) != null)
			session.removeAttribute(key);
	}
}
