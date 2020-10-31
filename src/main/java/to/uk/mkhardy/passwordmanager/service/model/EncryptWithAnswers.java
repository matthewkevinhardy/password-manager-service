package to.uk.mkhardy.passwordmanager.service.model;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public final class EncryptWithAnswers {
	
	@NotBlank(message = "pText is mandatory")
	private final String pText;
	
	@Valid
	private final User user;
	
	@NotEmpty(message = "Answers are mandatory")
	private final List<String> answers;
	
	public EncryptWithAnswers(String pText, User user, List<String> answers) {
		this.pText = pText;
		this.user = user;
		this.answers = answers;
	}
	public String getpText() {
		return pText;
	}
	public User getUser() {
		return user;
	}
	public List<String> getAnswers() {
		return answers;
	}
	
	
}
