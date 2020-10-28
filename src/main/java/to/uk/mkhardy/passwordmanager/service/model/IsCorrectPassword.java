package to.uk.mkhardy.passwordmanager.service.model;

import to.uk.mkhardy.passwordmanager.core.beans.impl.Password;

public final class IsCorrectPassword {
	private final String pTextPassword;
	private final Password password;
	
	public IsCorrectPassword(String pTextPassword, Password password) {
		super();
		this.pTextPassword = pTextPassword;
		this.password = password;
	}
	public String getpTextPassword() {
		return pTextPassword;
	}
	public Password getPassword() {
		return password;
	}
	
	
	
	
}
