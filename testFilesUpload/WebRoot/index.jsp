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
    
    <form action="NSM/mapEntityUpload" method="post" enctype="multipart/form-data"> 
    	Name1:<input id="bigpic" type="file" name="files['bigpic'].upload"/><br>
    	Name2:<input id="smallpic" type="file" name="files['smallpic'].upload"/><br>
    	<input type="submit" value="map 实体类确定">
    </form>
    
    <form action="NSL/listUpload" method="post" enctype="multipart/form-data"><!-- target="tarForm"> --> 
    	Name1:<input id="list1" type="file" name="list"/><br>
    	Name2:<input id="list2" type="file" name="list"/><br>
    	<input type="submit" value="list确定">
    </form>
    
    <iframe id="tarForm"></iframe>
  </body>
</html>
