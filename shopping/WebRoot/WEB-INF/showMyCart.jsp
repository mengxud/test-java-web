<%@ page language="java" import="java.util.*,com.mxd.domain.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'showMyCart.jsp' starting page</title>
    <link rel="stylesheet" type="text/css" href="css/comm.css"/>
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
    <h1>我的购物车</h1>
    <a href="/shopping/GoHallUI">点击返回购物界面</a>
    <form action="/shopping/ShoppingClServlet?type=update" method="post">
    <table border="1" style="border-collapse: collapse;width:600px;">
    	<tr><td>BookID</td><td>书名</td><td>价格</td><td>出版社</td><td>数量</td><td>删除</td></tr>
    	<%
    		String totalPrice = request.getAttribute("totalPrice").toString();
    		String totalSum = request.getAttribute("totalSum").toString();
    		ArrayList al = (ArrayList)request.getAttribute("bookList");
    		for(int i = 0;i < al.size(); i++){
    			Book book = (Book)al.get(i);
    	%>	
    	<tr><td><%=book.getId() %><input type="hidden" name=bid value=<%=book.getId() %> /></td>
    	<td><%=book.getName() %></td>
    	<td><%=book.getPrice() %></td>
    	<td><%=book.getPublishHouse() %></td>
    	<td><input type="text" name="booknum" value="<%=book.getShoppingNum() %>" />本</td>
    	<td><a href="/shopping/ShoppingClServlet?type=delete&bid=<%=book.getId() %>">删除</a></td></tr>
    	
    	<%
    		}
    	%>
    	<tr><td colspan="6"><input  type="submit" value="更新"/></td></tr>
    	<tr><td colspan="6">购物车物品总数：<%=totalSum %>个        购物车总价格：<%=totalPrice %>元 </td></tr>
    	<br />
    	<a href="/shopping/GoMyOrderServlet">提交订单</a>
    </table>
    </form>
  </body>
</html>
