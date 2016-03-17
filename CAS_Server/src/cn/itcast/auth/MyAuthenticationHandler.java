package cn.itcast.auth;

import org.jasig.cas.authentication.handler.AuthenticationException;
import org.jasig.cas.authentication.handler.support.AbstractUsernamePasswordAuthenticationHandler;
import org.jasig.cas.authentication.principal.UsernamePasswordCredentials;

public class MyAuthenticationHandler extends AbstractUsernamePasswordAuthenticationHandler {

	@Override
	protected boolean authenticateUsernamePasswordInternal(UsernamePasswordCredentials credentials)
			throws AuthenticationException {
		// TODO Auto-generated method stub
		
		final String userName = credentials.getUsername();
		final String passWord = credentials.getPassword();
		
		if(userName != null && passWord != null){
			StringBuffer sb = new StringBuffer(userName);
			if(sb.reverse().toString().equals(passWord)){
				return true;
			}
		}
		return false;
	}
}
