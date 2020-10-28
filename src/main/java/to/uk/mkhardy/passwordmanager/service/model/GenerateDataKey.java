package to.uk.mkhardy.passwordmanager.service.model;

import to.uk.mkhardy.passwordmanager.core.beans.impl.User;

public final class GenerateDataKey {
	private final String password;
	private final User user;
	public GenerateDataKey(String password, User user) {
		this.password = password;
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public User getUser() {
		return user;
	}
	
	
}
