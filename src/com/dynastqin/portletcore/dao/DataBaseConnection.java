package com.dynastqin.portletcore.dao;

import java.sql.Connection;
import java.sql.DriverManager;

/*
 *创建数据库操作类DataBaseConneciton,主要负责数据库连接与关闭
 * */
public class DataBaseConnection {
	//private static final String DBDRIVER="com.microsoft.sqlserver.jdbc.SQLServerDriver";//SQL数据库引擎
	private static final String DBDRIVER="com.mysql.jdbc.Driver";
	//private static final String DBURL="jdbc:sqlserver://localhost:1433; databaseName=Northwind";//数据源
	private static final String DBURL="jdbc:mysql://localhost:3306/pims";//数据源
	private static final String DBUSER="root";
	private static final String DBPASSWORD="888888";
	
	private Connection con=null;
	
	public DataBaseConnection() throws Exception{
		try{
			Class.forName(DBDRIVER);//加载驱动程序
			this.con=DriverManager.getConnection(DBURL,DBUSER,DBPASSWORD);//连接数据库
		}catch(Exception ex){
			throw ex;
		}
	}
	
	public Connection getConnection(){
		return this.con;
	}
	
	public void Close() throws Exception{
		if(this.con!=null){
			try{
				this.con.close();
			}catch(Exception ex){
				throw ex;
			}
		}
	}
	
	
	

}
