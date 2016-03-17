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

public class MyReturn implements CredentialsToPrincipalResolver {
	private DataSource ds;
	@Override
	public Principal resolvePrincipal(Credentials credentials) {
		final CredentialsTest credentialsTest = (CredentialsTest) credentials;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null ;
		String id = null;
		String webName = credentialsTest.getWebName();
		String username = credentialsTest.getUsername();
		String password = credentialsTest.getPassword();
		String sql = "select * from user where " + webName + "Name = ? and " + webName + "Password = ?";
		Map<String, Object> map = new HashMap<String, Object>();
		String bbsName = null;
		String bbsPassword = null;
		String newsName = null;
		String newsPassword = null;
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();
			while(rs.next()) {
				id = rs.getString("id");
				bbsName = rs.getString("bbsName");
				bbsPassword = rs.getString("bbsPassword");
				newsName = rs.getString("newsName");
				newsPassword = rs.getString("newsPassword");
		    }
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
		
		try {
			map.put("bbsName", URLEncoder.encode(bbsName, "UTF-8"));
			map.put("newsName", URLEncoder.encode(newsName, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		map.put("id", id);
		map.put("bbsPassword", bbsPassword);
		map.put("newsPassword", newsPassword);
		return new SimplePrincipal(id,map);
	}

	@Override
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
