package to.uk.mkhardy.passwordmanager.service.model;

import java.util.List;

import to.uk.mkhardy.passwordmanager.core.beans.impl.User;

public final class DecryptWithAnswers {
	private final String cText;
	private final User user;
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
