package cn.itcast.auth;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.jasig.cas.authentication.principal.Credentials;
import org.jasig.cas.authentication.principal.CredentialsToPrincipalResolver;
import org.jasig.cas.authentication.principal.Principal;
import org.jasig.cas.authentication.principal.SimplePrincipal;

public class MyReturnMoreInfoCredentialsToPrincipalResolver implements
CredentialsToPrincipalResolver {

private DataSource ds;

public Principal resolvePrincipal(Credentials credentials) {


final CredentialsTest usernamePasswordCredentials = (CredentialsTest) credentials;
Connection conn = null;
PreparedStatement ps = null;
ResultSet rs = null ;
String id = null;
Map<String, Object> map = new HashMap<String, Object>();
try {
	conn = ds.getConnection();
	String username = usernamePasswordCredentials.getUsername();
	String password = usernamePasswordCredentials.getPassword();
	String webName = usernamePasswordCredentials.getWebName();
	map.put("webName", webName);
	try {
		map.put("username", URLEncoder.encode(username, "UTF-8"));
	} catch (UnsupportedEncodingException e) {
		e.printStackTrace();
	}
	map.put("password", password);
	try {
		map.put("address", URLEncoder.encode("±±¾©", "UTF-8"));
	} catch (UnsupportedEncodingException e) {
		e.printStackTrace();
	}
	
	String sql = "select id from a_employee where name = ? and password = ? ";
	ps = conn.prepareStatement(sql);
	ps.setString(1, username);
	ps.setString(2, password);
	rs = ps.executeQuery();
	
	if(rs != null){
		if(rs.next()){
			id = rs.getString("id");
			map.put("id", id);
		}
	}
} catch (SQLException e) {
	e.printStackTrace();
}finally{
	try{
		if(rs != null){
			rs.close();
		}
		if(ps != null){
			ps.close();
		}
		if(conn != null){
			conn.close();
		}
	}catch(Exception e){
		e.printStackTrace();
	}
}
System.out.println(map+" return");
return new SimplePrincipal(id,map);
}

public boolean supports(Credentials credentials) {
return credentials != null
&& CredentialsTest.class
		.isAssignableFrom(credentials.getClass());
}

public DataSource getDs() {
return ds;
}

public void setDs(DataSource ds) {
this.ds = ds;
}

}
