<%@ page language="java" import="java.util.*,com.mxd.domain.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'myOrder.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">
	function goSubmitOrder(){
		window.location.href="/shopping/SubmitOrderServlet";
	}
</script>
  </head>
  
  <body>
    <h1>我的订单</h1>
    <form action="" method="post">
    	<table style="border-collapse: collapse;" border="1">
    		<tr>
    			<td>用户名：</td>
    			<td><%=((Users)request.getSession().getAttribute("user")).getName() %></td>
    		</tr>
    		<tr>
    			<td>Email：</td>
    			<td><%=((Users)request.getSession().getAttribute("user")).getEmail() %></td>
    		</tr>
    		<tr>
    			<td>用户级别：</td>
    			<td><%=((Users)request.getSession().getAttribute("user")).getGrade() %></td>
    		</tr>
    	</table>
    	<br />
    	<table style="border-collapse: collapse;" border="1">
    		<tr><td>书名</td><td>价格</td><td>出版社</td><td>数量</td></tr>
    		<%
    		String totalPrice = request.getAttribute("totalPrice").toString();
    		String totalSum = request.getAttribute("totalSum").toString();
    			ArrayList al = (ArrayList)request.getAttribute("bookList");
    		    for(int i = 0;i<al.size();i++){
    		    	Book book = (Book)al.get(i);
    		 %>
    		<tr>
    		<td><%=book.getName()%></td>
    		<td><%=book.getPrice()%></td>
    		<td><%=book.getPublishHouse()%></td>
    		<td><%=book.getShoppingNum()%> 本</td>
    		</tr>
    		 
    		 <%
    		    }
    		%>
    		<tr><td colspan="4">购物车物品总数：<%=totalSum %>个        购物车总价格：<%=totalPrice %>元 </td></tr>
    	</table>
    	<br />
    	<a href="/shopping/ShoppingClServlet?type=return">返回购物车</a>
    	<input type="button" onclick="goSubmitOrder()" value="确认订单"/>
    </form>
  </body>
</html>
