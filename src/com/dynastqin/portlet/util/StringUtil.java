package com.dynastqin.portlet.util;

import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	/**
	 * 大小写转换
	 * 
	 * @param obj 要转换的值
	 * @param type 0:转小写;	>0:转大写
	 * @return
	 */
	public static String toLowerUpperCase(Object obj, int type) {
		String returnValue = "";
		if (obj == null || obj.toString() == "")
			returnValue = "";
		else {
			if (type == 0)
				returnValue = obj.toString().toLowerCase();
			else
				returnValue = obj.toString().toUpperCase();
		}
		return returnValue;
	}
	
	/**
	 * object转换成Integer对象
	 * @param obj
	 * @return obj=null或者obj.toString()=""返回null;<br/>obj="123"返回123
	 */
	public static Integer toInteger(Object obj){
		if(obj==null||obj.toString()==""){
			return null;
		}else{
			return Integer.parseInt(obj.toString());
		}
	}
	
	public static void main(String args[]){
		//System.out.println(toLowerCase(null, 6));
		//System.out.println(toInteger(""));
		//System.out.println(MessageFormat.format(" WHERE username=''{0}'' and password={1}","admin","123456"));

		String url="www/hello/ab";
		String url1=url.substring("www/hello/".length(),url.length());
		Pattern pat = Pattern.compile("(ab|bc)$");//字符串以ab或bc结尾
		Matcher mat=pat.matcher(url.substring("www/hello/".length(),url.length()));
		System.out.println(mat.find());
	}

}
