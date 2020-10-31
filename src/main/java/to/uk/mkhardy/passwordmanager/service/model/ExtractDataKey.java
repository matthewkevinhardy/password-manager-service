package to.uk.mkhardy.passwordmanager.service.model;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

public final class ExtractDataKey {
	
	@NotBlank(message = "encDataKey is mandatory")
	private final String encryptedDataKey;
	
	@NotBlank(message = "password is mandatory")
	private final String password;
	
	@Valid
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
