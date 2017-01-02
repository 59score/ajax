<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>用户列表</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/myCss.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery_dialog.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/userAjax.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery_dialog.js"></script>
</head>
<body onload="showUserData()" style="margin:0px;padding:0px;">
	<div id="content" style="padding:20px 20px">
	<h2>ajax+servlet页面无刷新增删改查</h2>
		<form method="post">
			<div style="width: 350px;">
				<div style="float: left">
					<a href="javascript:JqueryDialog.Open('添加用户', 'UserAction?method=toAdd', 300, 300);">添加</a>
				</div>
				<div style="text-align: right">
					<input type="text" name="userName" id="userName" value="${userName}" /><input
						type="button" value="搜索" onclick="showUserData()"/>
				</div>
			</div>
			<table id="userList">
				<thead>
					<tr align="center">
					<th><input type="checkbox" value="all" id="checkAll"/></th>
						<th>序号</th>
						<th>姓名</th>
						<th>生日</th>
						<th>角色</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody></tbody>
			</table>
			
			<a href="UserAction?method=logout">退出</a>
	</div>
</body>
</html>