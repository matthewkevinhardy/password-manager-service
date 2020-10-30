package to.uk.mkhardy.passwordmanager.service.component;

import java.util.List;

import to.uk.mkhardy.passwordmanager.core.beans.impl.Answer;
import to.uk.mkhardy.passwordmanager.core.beans.impl.Password;
import to.uk.mkhardy.passwordmanager.core.beans.impl.Question;
import to.uk.mkhardy.passwordmanager.service.model.DecryptWithAnswers;
import to.uk.mkhardy.passwordmanager.service.model.EncryptWithAnswers;
import to.uk.mkhardy.passwordmanager.service.model.ExtractDataKey;
import to.uk.mkhardy.passwordmanager.service.model.GenerateDataKey;
import to.uk.mkhardy.passwordmanager.service.model.GetAnswer;
import to.uk.mkhardy.passwordmanager.service.model.GetPassword;
import to.uk.mkhardy.passwordmanager.service.model.IsCorrectAnswer;
import to.uk.mkhardy.passwordmanager.service.model.IsCorrectPassword;

public interface PasswordManagerComponent {
	
	public boolean isValidPassword( String password);

	public boolean isCorrectAnswer(IsCorrectAnswer isCorrectAnswer);

	public boolean isCorrectPassword(IsCorrectPassword isCorrectPassword);

	public Password getPassword(GetPassword getPassword);

	public List<Question> getQuestions();

	public Answer getAnswer(GetAnswer getAnswer);

	public String generateDataKey(GenerateDataKey generateDataKey);

	public String extractDataKey(ExtractDataKey extractDataKey);

	public String encryptWithAnswers(EncryptWithAnswers encryptWithAnswers);
	
	public String decryptWithAnswers(DecryptWithAnswers decryptWithAnswers);
}
