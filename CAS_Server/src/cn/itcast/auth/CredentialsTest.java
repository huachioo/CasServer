package cn.itcast.auth;

import org.jasig.cas.authentication.principal.UsernamePasswordCredentials;

public class CredentialsTest extends UsernamePasswordCredentials{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String webName;

	public String getWebName() {
		return webName;
	}

	public void setWebName(String webName) {
		this.webName = webName;
	}
	
	

}
