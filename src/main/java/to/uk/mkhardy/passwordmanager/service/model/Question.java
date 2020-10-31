package to.uk.mkhardy.passwordmanager.service.model;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class Question {
	
	@NotBlank(message = "questionId is mandatory")
	private final String questionId;

	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public Question(@JsonProperty("questionId") String questionId) {
		this.questionId = questionId;
	}

	public String getQuestionId() {
		return questionId;
	}
	
	
}
