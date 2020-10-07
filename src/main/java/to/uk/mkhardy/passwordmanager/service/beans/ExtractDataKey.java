package to.uk.mkhardy.passwordmanager.service.beans;

import to.uk.mkhardy.passwordmanager.core.beans.impl.User;

public final class ExtractDataKey {
	private final String encryptedDataKey;
	private final String password;
	private final User user;
	
	public ExtractDataKey(String encryptedDataKey, String password, User user) {
		this.password = password;
		this.user = user;
		this.encryptedDataKey=encryptedDataKey;
	}
	public String getPassword() {
		return password;
	}
	public User getUser() {
		return user;
	}
	public String getEncryptedDataKey() {
		return encryptedDataKey;
	}
	
	
}
