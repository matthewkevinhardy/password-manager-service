package to.uk.mkhardy.passwordmanager.service.model;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

public final class IsCorrectAnswer {
	
	@NotBlank(message = "pText is mandatory")
	private final String pText;
	
	@Valid
	private final Answer answer;
	
	public IsCorrectAnswer(String pText, Answer answer) {
		this.pText = pText;
		this.answer = answer;
	}
	public String getpText() {
		return pText;
	}
	public Answer getAnswer() {
		return answer;
	}
	
	
}
