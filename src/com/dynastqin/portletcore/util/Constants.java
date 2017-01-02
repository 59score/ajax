package com.dynastqin.portletcore.util;

public class Constants {
	public static final String WEB_USER_SESSION = "sessionUser";//保存到session中的登录用户
	
	//返回的json中的结果代码key
	public static final String RESULT_CODE = "result_code";
	
	//RESULT_CODE对应的值，失败
	public static final String RESULT_CODE_FAILURE = "0";
		
	//RESULT_CODE对应的值，成功
	public static final String RESULT_CODE_SUCCESS = "1";
	
	//RESULT_CODE对应的值，异常
	public static final String RESULT_CODE_ERROR = "2";
	
	//返回json中的提示信息
	public static final String RESULT_MSG="result_msg";
	
	//返回json中的异常信息
	public static final String ERROR_MSG="error_msg";
	
	public static final String[] USERLOGIN_URL=new String[]{"/login.jsp","/LoginAction"};
}
