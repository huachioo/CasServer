package cn.itcast.cas.listener;

import java.io.Console;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import org.jasig.cas.client.util.AbstractCasFilter;
import org.jasig.cas.client.validation.Assertion;

import cn.itcast.cas.domin.User;

public class MyListener implements HttpSessionAttributeListener {

	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		String key = event.getName();//keyֵ
		if(key.equals(AbstractCasFilter.CONST_CAS_ASSERTION)){
			Assertion as = (Assertion) event.getSession().getAttribute(AbstractCasFilter.CONST_CAS_ASSERTION);
		    Map<String,Object> map = as.getPrincipal().getAttributes();
		    System.out.println(map);
		    String bbsName = (String) map.get("bbsName");
		    String newsName = (String) map.get("newsName");
//		    User u = new User();
//		    u.setId((String)map.get("id"));
//		    //u.setUserName((String)map.get("username"));
//		    //u.setPassword((String)map.get("passsword"));
//		    try {
//		    	 u.setPassword(URLDecoder.decode((String) map.get("password"),"UTF-8")); 
//		    	u.setAddress(URLDecoder.decode((String) map.get("address"),"UTF-8"));
//		    	 u.setUserName(URLDecoder.decode((String) map.get("username"),"UTF-8"));
//			} catch (UnsupportedEncodingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		    System.out.println(u.getId()+u.getPassword()+"dasd");
//		    event.getSession().setAttribute("user", u);
		}
		 System.out.println("dasd1");

	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub

	}

}
