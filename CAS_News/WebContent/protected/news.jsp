<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="org.jasig.cas.client.util.AssertionHolder"%>
<%@page import="org.jasig.cas.client.validation.Assertion"%>
<%@page import="java.security.Principal"%>
<%@page import="org.jasig.cas.client.util.AbstractCasFilter"%>
<%@page import="java.net.URLDecoder"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'bbs.jsp' starting page</title>
    
  </head>
  
  <body>
    <%=request.getClass().getName() %><br>
    <h3>获取登录名：</h3>
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
       	
       	String address = (String)m.get("address");
        address = URLDecoder.decode(address,"UTF-8");
        out.print("<br>"+address);
        %>
        <br>
  </body>
</html>
