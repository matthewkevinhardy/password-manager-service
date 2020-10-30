package to.uk.mkhardy.passwordmanager.service.component;

import java.util.List;

import to.uk.mkhardy.passwordmanager.core.PasswordManager;
import to.uk.mkhardy.passwordmanager.core.beans.impl.Answer;
import to.uk.mkhardy.passwordmanager.core.beans.impl.Password;
import to.uk.mkhardy.passwordmanager.core.beans.impl.Question;
import to.uk.mkhardy.passwordmanager.core.impl.PasswordRuleException;
import to.uk.mkhardy.passwordmanager.service.exception.PassManException;
import to.uk.mkhardy.passwordmanager.service.exception.PassRuleException;
import to.uk.mkhardy.passwordmanager.service.model.DecryptWithAnswers;
import to.uk.mkhardy.passwordmanager.service.model.EncryptWithAnswers;
import to.uk.mkhardy.passwordmanager.service.model.ExtractDataKey;
import to.uk.mkhardy.passwordmanager.service.model.GenerateDataKey;
import to.uk.mkhardy.passwordmanager.service.model.GetAnswer;
import to.uk.mkhardy.passwordmanager.service.model.GetPassword;
import to.uk.mkhardy.passwordmanager.service.model.IsCorrectAnswer;
import to.uk.mkhardy.passwordmanager.service.model.IsCorrectPassword;

public class PasswordManagerComponentImpl implements PasswordManagerComponent {
	
	private PasswordManager passwordManager;
	
	public PasswordManagerComponentImpl(PasswordManager passwordManager) {
		this.passwordManager=passwordManager;
	}
	
	public boolean isValidPassword(String password) {

		try {
			return passwordManager.isValidPassword(password);
		} catch (PasswordRuleException e) {
			throw new PassRuleException(e);
		}
	}

	public boolean isCorrectAnswer(IsCorrectAnswer isCorrectAnswer) {

		return passwordManager.isCorrectAnswer(isCorrectAnswer.getpText(), isCorrectAnswer.getAnswer());

	}

	public boolean isCorrectPassword(IsCorrectPassword isCorrectPassword) {

		return passwordManager.isCorrectPassword(isCorrectPassword.getpTextPassword(),
				isCorrectPassword.getPassword());
	}

	public Password getPassword(GetPassword getPassword) {
		Password pass = passwordManager.getPassword(getPassword.getPassword(), getPassword.getUser());
		return pass;
	}

	public List<Question> getQuestions() {
		return passwordManager.getQuestions();
	}

	public Answer getAnswer(GetAnswer getAnswer) {
		return passwordManager.getAnswer(getAnswer.getAnswer(), getAnswer.getUser(), getAnswer.getQuestion());
	}

	public String generateDataKey(GenerateDataKey generateDataKey)  {
		try {
			return passwordManager.generateDataKey(generateDataKey.getPassword(), generateDataKey.getUser());
		} catch (Exception e) {
			throw new PassManException(e);
		}
	}

	public String extractDataKey(ExtractDataKey extractDataKey)  {
		try {
			return passwordManager.extractDataKey(extractDataKey.getEncryptedDataKey(), extractDataKey.getPassword(),
					extractDataKey.getUser());
		} catch (Exception e) {
			throw new PassManException(e);
		}
	}

	public String encryptWithAnswers(EncryptWithAnswers encryptWithAnswers)  {
		try {
			return passwordManager.encrypt(encryptWithAnswers.getpText().getBytes(), encryptWithAnswers.getAnswers(),
					encryptWithAnswers.getUser());
		} catch (Exception e) {
			throw new PassManException(e);
		}
	}
	
	public String decryptWithAnswers(DecryptWithAnswers decryptWithAnswers)  {
		try {
			return passwordManager.decrypt(decryptWithAnswers.getcText(), decryptWithAnswers.getAnswers(),
					decryptWithAnswers.getUser());
		} catch (Exception e) {
			throw new PassManException(e);
		}
	}
}
