package to.uk.mkhardy.passwordmanager.service.model;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class User implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "userName is mandatory")
	private final String userName;
	
	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public User(@JsonProperty("userName") String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}
}
