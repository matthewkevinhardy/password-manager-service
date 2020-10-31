package to.uk.mkhardy.passwordmanager.service.utils;

import to.uk.mkhardy.passwordmanager.core.beans.impl.Answer;
import to.uk.mkhardy.passwordmanager.core.beans.impl.Password;
import to.uk.mkhardy.passwordmanager.core.beans.impl.Question;
import to.uk.mkhardy.passwordmanager.core.beans.impl.User;

public class ModelMapper {
	public static User getCoreUser(to.uk.mkhardy.passwordmanager.service.model.User user) {
		return new User(user.getUserName());
	}
	
	public static Question getCoreQuestion(to.uk.mkhardy.passwordmanager.service.model.Question question) {
		return new Question(question.getQuestionId());
	}
	
	public static Answer getCoreAnswer(to.uk.mkhardy.passwordmanager.service.model.Answer answer) {
		return new Answer(answer.getHashValue(),getCoreQuestion(answer.getQuestion()),getCoreUser(answer.getUser()));
	}
	
	public static Password getCorePassword(to.uk.mkhardy.passwordmanager.service.model.Password password) {
		return new Password(password.getPasswordHash(),getCoreUser(password.getUser()));
	}
}
