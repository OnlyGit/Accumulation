<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
out.println(basePath);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>"><!-- 为页面上的所有链接定义默认地址或默认目标。在html中不用关闭，在xhtml中必须关闭 -->
    
    <title>My JSP 'index.jsp' starting page</title>
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
    <form action="NSM/mapUpload" method="post" enctype="multipart/form-data"> 
    	Name1:<input id="bigpic" type="file" name="uploads.bigpic"/><br>
    	Name2:<input id="smallpic" type="file" name="uploads.smallpic"/><br>
    	<input type="submit" value="map确定">
    </form>
    
    
  </body>
</html>
