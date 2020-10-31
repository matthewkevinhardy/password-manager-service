package to.uk.mkhardy.passwordmanager.service.model;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

public final class DecryptWithAnswers {
	
	@NotBlank(message = "cText is mandatory")
	private final String cText;
	
	@Valid
	private final User user;
	
	@NotBlank
	private final List<String> answers;
	
	public DecryptWithAnswers(String cText, User user, List<String> answers) {
		this.cText = cText;
		this.user = user;
		this.answers = answers;
	}
	public String getcText() {
		return cText;
	}
	public User getUser() {
		return user;
	}
	public List<String> getAnswers() {
		return answers;
	}
	
	
}
