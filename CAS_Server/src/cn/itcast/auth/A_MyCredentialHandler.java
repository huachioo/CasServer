package cn.itcast.auth;

import javax.sql.DataSource;
import javax.validation.constraints.NotNull;

import org.jasig.cas.authentication.handler.AuthenticationException;
import org.jasig.cas.authentication.handler.AuthenticationHandler;
import org.jasig.cas.authentication.principal.Credentials;

public class A_MyCredentialHandler implements AuthenticationHandler {
	
	private static final Class<MyCredentials> DEFAULT_CLASS = MyCredentials.class;

	/** Class that this instance will support. */
	@NotNull
	private Class<MyCredentials> classToSupport = DEFAULT_CLASS;

	private boolean supportSubClasses = true;

	private DataSource ds;
	
	public void MyCredentialHandler() {
	}

	@Override
	public boolean authenticate(Credentials credentials) throws AuthenticationException {
		final MyCredentials usernamePasswordCredentials = (MyCredentials) credentials;
		String username = usernamePasswordCredentials.getUsername();
		String password = usernamePasswordCredentials.getPassword();
		System.out.println("username"+username+" password"+password);
		usernamePasswordCredentials.setWebName("BBS");
		if(username.equals("员工1") && password.equals("123")){
			System.out.println("检测成功");
			return true;
		}
		System.out.println("检测失败");
		return true;
	}

	@Override
	public boolean supports(Credentials credentials) {
		return credentials != null
			     && (this.classToSupport.equals(credentials.getClass()) || (this.classToSupport
			       .isAssignableFrom(credentials.getClass()))
			       && this.supportSubClasses);
	}

	public DataSource getDs() {
		return ds;
	}

	public void setDs(DataSource ds) {
		this.ds = ds;
	}

}
