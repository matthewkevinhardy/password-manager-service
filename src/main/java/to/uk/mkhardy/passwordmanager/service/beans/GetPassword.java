package to.uk.mkhardy.passwordmanager.service.beans;

import to.uk.mkhardy.passwordmanager.core.beans.impl.User;

public class GetPassword {
	private User user;
	private String password;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
