package to.uk.mkhardy.passwordmanager.service.model;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class Password {
	
	@NotBlank(message = "passwordHash is mandatory")
	private final String passwordHash;
	
	@Valid
	private final User user;

	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public Password(@JsonProperty("passwordHash") String passwordHash, @JsonProperty("user") User user) {
		this.passwordHash = passwordHash;
		this.user = user;
	}

	public String getPasswordHash() {
		return this.passwordHash;
	}

	public User getUser() {
		return user;
	}

}
