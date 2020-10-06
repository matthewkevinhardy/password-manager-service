package to.uk.mkhardy.passwordmanager.service.beans;

import to.uk.mkhardy.passwordmanager.core.beans.impl.Question;
import to.uk.mkhardy.passwordmanager.core.beans.impl.User;

public final class GetAnswer {
	
	private final User user;
	private final Question question;
	private final String answer;
	
	public GetAnswer(User user, Question question,String answer) {
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
