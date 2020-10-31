package to.uk.mkhardy.passwordmanager.service.model;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

public final class GenerateDataKey {
	
	@NotBlank(message = "password is mandatory")
	private final String password;
	
	@Valid
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
