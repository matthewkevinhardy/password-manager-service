package to.uk.mkhardy.passwordmanager.service.model;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class Answer {
	@NotBlank(message = "hashValue is mandatory")
	private final String hashValue;
	
	@Valid
	private final Question question;
	
	@Valid
	private final User user;

	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public Answer(@JsonProperty("hashValue") String hashValue, @JsonProperty("question") Question question,
			@JsonProperty("user") User user) {
		this.question = question;
		this.user = user;
		this.hashValue = hashValue;
	}

	public String getHashValue() {
		return hashValue;
	}

	public Question getQuestion() {
		return question;
	}

	public User getUser() {
		return user;
	}

}
