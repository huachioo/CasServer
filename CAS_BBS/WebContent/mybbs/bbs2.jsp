<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="org.jasig.cas.client.util.AssertionHolder"%>
<%@page import="org.jasig.cas.client.validation.Assertion"%>
<%@page import="java.security.Principal"%>
<%@page import="org.jasig.cas.client.util.AbstractCasFilter"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="cn.itcast.cas.domin.User" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 1.通过request.getRemoteUser()获取登录名：
    <%=
    	request.getRemoteUser()
     %><br>
     2.通过request.getUserPrincipal()获取登录名：
     <%=request.getUserPrincipal() %><br>
     3.通过Assertion对象获取登录名：
     <%
     	Assertion as = AssertionHolder.getAssertion();
     	Principal p = as.getPrincipal();
     	out.print(p.getName());
      %><br>
      4.通过session对象获取登录名：
      <%
      	as = (Assertion)session.getAttribute(AbstractCasFilter.CONST_CAS_ASSERTION);
      	p = as.getPrincipal();
     	out.print(p.getName());
       %>
       
        <hr>
       <h3>获取更多的用户信息：</h3>
       <%
       	Map m = as.getPrincipal().getAttributes();
       	out.print(m);
       	
       	String bbsName = (String)m.get("bbsName");
       	bbsName = URLDecoder.decode(bbsName,"UTF-8");
    	String newsName = (String)m.get("newsName");
    	newsName = URLDecoder.decode(newsName,"UTF-8");
        out.print("<br>"+bbsName+"<br>"+newsName);
        %>
        <br>
</body>
</html>