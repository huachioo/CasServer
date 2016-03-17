package cn.itcast.cas.domin;

public class User {

	private String id;
	private String userName;
	private String password;
	private String address;
	
    public void user(){
    	
    }
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "[id:"+id+",name:"+userName+",password:"+password+",address:"+address+"]";
	}
}