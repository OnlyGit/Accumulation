<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  </head>
  <body>
      <s:fielderror></s:fielderror><!-- 获取validation中的message信息 -->
      <form action="validation/simpleAnnotationLogin" method="post">
          <input type="text" name="userName" /> <br>
          <input type="password" name="password" /> <br>
          <input type="submit" value="提交" />
      </form>
  </body>
</html>