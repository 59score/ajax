<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>用户修改</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/calendar.css" />

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/cal.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery_dialog.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/userAjax.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function() {
		$('#userName').focus();//解决第一个文本输入框无法选中问题
		$("input[id='birth']").simpleDatepicker({startdate : 1950,enddate : 2012});
	});
		
	function Ok() {
		//模拟向服务端POST，并由服务端接管后继逻辑
		if ($('#userId').val() != null && $('#userId').val() != '')
			$('#userForm').attr("action", "UserAction?method=update");
		else
			$('#userForm').attr("action", "UserAction?method=add");

		if($('#userName').val()==""){
			$("input[id='userName']").after("<span style='font-size:11px;color:red'>请输入姓名！</span>");
			return false;
		}
		if($('#birth').val()==""){
			$("input[id='birth']").after("<span style='font-size:11px;color:red'>请输入生日！</span>");
			return false;
		}
	
		//使用ajax 提交数据
	$.ajax({
		type:'GET',
		contentType : 'application/json',
		url : $('#userForm').attr("action"),
		data:{userId:$('#userId').val(),userName:$('#userName').val(),birth:$('#birth').val(),roleId:$('#roleId').val(),pageIndex:$('#pageIndex').val()},
		dataType : 'html',
		success :function(data){
			var result=JSON.parse(data);
			if(result.result_code==1){
				alert('操作成功！');
				window.parent.showUserData($('#pageIndex').val());//调用父页面方法，无刷新更新数据
				window.parent.JqueryDialog.Close();
			}else if(result.result_code==0){
					alert('操作失败！');
			}else{
				  top.location.href="<%=request.getContextPath()%>/error.jsp";
			}
		},
		error : function(data) {
			alert("获取数据失败！");
		}
		});  

	/*
	//使用form提交
	 	document.getElementById("userForm").submit();
		window.parent.showUserData($('#pageIndex').val());//调用父页面方法，无刷新更新数据
		return true;
		*/
	}

</script>
</head>
<body>
	<form id="userForm">
		<input type="hidden" name="userId" id="userId" value="${user.userId}" />
		<input type="hidden" name="pageIndex" id="pageIndex"
			value="${pageIndex}" />
		<div>
			<table>
				<tr>
					<td width="50px">姓名</td>
					<td><input type="text" name="userName" id="userName"
						value="${user.userName}" /></td>
				</tr>
				<tr><td>生日</td>
					<td><input type="text" name="birth" id="birth"
						value="${user.birthFmt}" /></td>
				</tr>
				<tr>
					<td>角色</td>
					<td><select name="roleId" id="roleId">
							<c:forEach items="${roles}" var="role">
								<c:choose>
									<c:when test="${role.roleId == user.roleId}">
										<option value="${role.roleId}" selected="selected">${role.roleName}</option>
									</c:when>
									<c:otherwise>
										<option value="${role.roleId}">${role.roleName}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
					</select></td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>