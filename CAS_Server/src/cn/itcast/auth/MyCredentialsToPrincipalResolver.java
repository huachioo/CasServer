package cn.itcast.auth;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.jasig.cas.authentication.principal.AbstractPersonDirectoryCredentialsToPrincipalResolver;
import org.jasig.cas.authentication.principal.Credentials;

public class MyCredentialsToPrincipalResolver extends AbstractPersonDirectoryCredentialsToPrincipalResolver {

	private DataSource ds;
	@Override
	public boolean supports(Credentials credentials) {
		// TODO Auto-generated method stub
		return credentials != null
				&& MyCredentials.class
						.isAssignableFrom(credentials.getClass());
	}

	public DataSource getDs() {
		return ds;
	}

	public void setDs(DataSource ds) {
		this.ds = ds;
	}

	@Override
	protected String extractPrincipalId(Credentials credentials) {
		// TODO Auto-generated method stub
		final MyCredentials usernamePasswordCredentials = (MyCredentials) credentials;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null ;
		String id = null;
		try {
		    conn = ds.getConnection();
		    String userName = usernamePasswordCredentials.getUsername();
		    String passWord = usernamePasswordCredentials.getPassword();
		    usernamePasswordCredentials.setWebName("BBS");
		    String sql = "select id from a_employee where name = ? and password = ? ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, userName);
			ps.setString(2, passWord);
			rs = ps.executeQuery();
			
			if(rs!=null){
				if(rs.next()){
					id = rs.getString("id");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(rs!= null){
				    rs.close();
				}
				if(ps!= null){
					ps.close();
				}
				if(conn!= null){
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return id;
	} 

}
