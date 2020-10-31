package to.uk.mkhardy.passwordmanager.service.model;

import javax.validation.constraints.NotBlank;

public final class IsCorrectPassword {
	
	@NotBlank(message = "pTextPassword is mandatory")
	private final String pTextPassword;
	
	@NotBlank(message = "password is mandatory")
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
