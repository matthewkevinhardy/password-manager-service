package to.uk.mkhardy.passwordmanager.service.model;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

public final class GetAnswer {
	
	@Valid
	private final User user;
	
	@Valid
	private final Question question;
	
	@NotBlank(message = "answer is mandatory")
	private final String answer;
	
	public GetAnswer(@Valid User user,@Valid Question question,String answer) {
		this.user = user;
		this.question = question;
		this.answer=answer;
	}
	public User getUser() {
		return user;
	}
	public Question getQuestion() {
		return question;
	}
	public String getAnswer() {
		return answer;
	}
	
	
}
