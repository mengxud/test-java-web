<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <body>
   	 <h1>登陆界面</h1>
    <form action="/shopping/GoHallUI" method="post">
    	<table border="1">
    		<tr><td>请输入用户名：</td><td><input type="text" name="username" /></td></tr>
    		<tr><td>请输入密码：</td><td><input type="password" name="passwd" /></td></tr>
    		<tr><td><input type="submit" name="submit" value="登录" /></td><td><input type="reset" name="重置" /></td></tr>
    	</table>
    </form>
  </body>
</html>
