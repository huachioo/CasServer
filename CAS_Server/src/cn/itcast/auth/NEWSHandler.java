package cn.itcast.auth;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.jasig.cas.authentication.handler.AuthenticationException;

public class NEWSHandler  extends AbstractWebHandler{
	
	private DataSource newsds;
	public DataSource getNewsds() {
		return newsds;
	}

	public void setNewsds(DataSource newsds) {
		this.newsds = newsds;
	}

	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null ;
	String id = null;

	@Override
	protected boolean authenticateUsernamePasswordInternal(CredentialsTest credentials) throws AuthenticationException {
		// TODO Auto-generated method stub
		try {
			final String userName = credentials.getUsername();
			final String passWord = credentials.getPassword();
			conn = newsds.getConnection();
			String sql = "select id from a_employee where name = ? and password = ? ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, userName);
			ps.setString(2, passWord);
			rs = ps.executeQuery();
			if(rs!=null){
				credentials.setWebName("news");
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
