package to.uk.mkhardy.passwordmanager.service.beans;

import to.uk.mkhardy.passwordmanager.core.beans.impl.Answer;

public final class IsValidAnswer {
	private final String pText;
	private final Answer answer;
	
	public IsValidAnswer(String pText, Answer answer) {
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
