package to.uk.mkhardy.passwordmanager.service.model;

import java.util.List;

import to.uk.mkhardy.passwordmanager.core.beans.impl.User;

public final class EncryptWithAnswers {
	private final String pText;
	private final User user;
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
