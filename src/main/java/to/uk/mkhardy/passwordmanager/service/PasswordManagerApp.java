package to.uk.mkhardy.passwordmanager.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import to.uk.mkhardy.passwordmanager.core.PasswordManager;
import to.uk.mkhardy.passwordmanager.core.PasswordManagerBuilder;
import to.uk.mkhardy.passwordmanager.core.beans.PasswordRule;
import to.uk.mkhardy.passwordmanager.core.beans.impl.Answer;
import to.uk.mkhardy.passwordmanager.core.beans.impl.Password;
import to.uk.mkhardy.passwordmanager.core.beans.impl.PasswordLengthRule;
import to.uk.mkhardy.passwordmanager.core.beans.impl.PasswordLowercaseCharRule;
import to.uk.mkhardy.passwordmanager.core.beans.impl.PasswordUppercaseCharRule;
import to.uk.mkhardy.passwordmanager.core.beans.impl.Question;
import to.uk.mkhardy.passwordmanager.core.impl.PasswordRuleException;
import to.uk.mkhardy.passwordmanager.service.beans.DecryptWithAnswers;
import to.uk.mkhardy.passwordmanager.service.beans.EncryptWithAnswers;
import to.uk.mkhardy.passwordmanager.service.beans.ExtractDataKey;
import to.uk.mkhardy.passwordmanager.service.beans.GenerateDataKey;
import to.uk.mkhardy.passwordmanager.service.beans.GetAnswer;
import to.uk.mkhardy.passwordmanager.service.beans.GetPassword;
import to.uk.mkhardy.passwordmanager.service.beans.IsCorrectAnswer;
import to.uk.mkhardy.passwordmanager.service.beans.IsCorrectPassword;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class PasswordManagerApp {

	public static void main(String[] args) {
		SpringApplication.run(PasswordManagerApp.class, args);
	}

	private Logger logger = LoggerFactory.getLogger(PasswordManagerApp.class);
	private PasswordManager passwordManager;

	public PasswordManagerApp() {

		PasswordRule lengthRule = new PasswordLengthRule(8, "passwordLengthRule.description",
				"passwordLengthRule.errorMessage");
		PasswordRule uppercaseCharRule = new PasswordUppercaseCharRule("passwordUppercaseCharRule.description",
				"passwordUppercaseCharRule.errorMessage");
		PasswordRule lowercaseCharRule = new PasswordLowercaseCharRule("passwordLowercaseCharRule.description",
				"passwordLowercaseCharRule.errorMessage");
		PasswordRule numberRule = new PasswordNumberRule("passwordNumberRule.description",
				"passwordNumberRule.errorMessage");
		PasswordRule spCharRule = new PasswordSpecialCharRule("passwordSpCharRule.description",
				"passwordSpCharRule.errorMessage");

		Question question1 = new Question("question1");
		Question question2 = new Question("question2");
		Question question3 = new Question("question3");
		Question question4 = new Question("question4");
		Question question5 = new Question("question5");
		Question question6 = new Question("question6");
		Question question7 = new Question("question7");
		Question question8 = new Question("question8");
		Question question9 = new Question("question9");
		Question question10 = new Question("question10");

		passwordManager = new PasswordManagerBuilder().addPasswordRule(uppercaseCharRule)
				.addPasswordRule(lowercaseCharRule).addPasswordRule(lengthRule).addPasswordRule(numberRule)
				.addPasswordRule(spCharRule).addQuestion(question1).addQuestion(question2).addQuestion(question3)
				.addQuestion(question4).addQuestion(question5).addQuestion(question6).addQuestion(question7)
				.addQuestion(question8).addQuestion(question9).addQuestion(question10).build();
	}

	@RestController
	@RequestMapping("password-manager")
	public class PasswordManagerController {

		@PostMapping(path = "/isValidPassword", produces = MediaType.APPLICATION_JSON_VALUE)
		public Map<String, String> isValidPassword(@RequestParam(value = "password") String password) {

			boolean isValid = false;
			String error = null;

			try {
				isValid = passwordManager.isValidPassword(password);
			} catch (PasswordRuleException e) {
				isValid = false;
				error = e.getPasswordRule().getErrorMessageKey();
			}

			Map<String, String> response = new HashMap<String, String>();
			if (isValid) {
				response.put("isValid", "true");
			} else {
				response.put("isValid", "false");
				response.put("error", error);
			}
			return response;
		}

		@PostMapping(path = "/isCorrectAnswer", produces = MediaType.APPLICATION_JSON_VALUE)
		public Map<String, Object> isCorrectAnswer(@RequestBody IsCorrectAnswer isCorrectAnswer) {

			boolean isValid = passwordManager.isCorrectAnswer(isCorrectAnswer.getpText(), isCorrectAnswer.getAnswer());

			Map<String, Object> response = new HashMap<String, Object>();
			if (isValid) {
				response.put("isValid", Boolean.TRUE);
			} else {
				response.put("isValid", Boolean.FALSE);
			}

			return response;
		}

		@PostMapping(path = "/isCorrectPassword", produces = MediaType.APPLICATION_JSON_VALUE)
		public Map<String, Object> isCorrectPassword(@RequestBody IsCorrectPassword isCorrectPassword) {

			boolean isValid = passwordManager.isCorrectPassword(isCorrectPassword.getpTextPassword(),
					isCorrectPassword.getPassword());

			Map<String, Object> response = new HashMap<String, Object>();
			if (isValid) {
				response.put("isValid", Boolean.TRUE);
			} else {
				response.put("isValid", Boolean.FALSE);
			}

			return response;
		}

		@PostMapping(path = "/getPassword", produces = MediaType.APPLICATION_JSON_VALUE)
		public Password getPassword(@RequestBody GetPassword getPassword) {
			Password pass = passwordManager.getPassword(getPassword.getPassword(), getPassword.getUser());
			return pass;
		}

		@GetMapping(path = "/getQuestions", produces = MediaType.APPLICATION_JSON_VALUE)
		public List<Question> getQuestions() {
			return passwordManager.getQuestions();
		}

		@PostMapping(path = "/getAnswer", produces = MediaType.APPLICATION_JSON_VALUE)
		public Answer getAnswer(@RequestBody GetAnswer getAnswer) {
			return passwordManager.getAnswer(getAnswer.getAnswer(), getAnswer.getUser(), getAnswer.getQuestion());
		}

		@PostMapping(path = "/generateDataKey", produces = MediaType.APPLICATION_JSON_VALUE)
		public String generateDataKey(@RequestBody GenerateDataKey generateDataKey) throws Exception {
			return passwordManager.generateDataKey(generateDataKey.getPassword(), generateDataKey.getUser());
		}

		@PostMapping(path = "/extractDataKey", produces = MediaType.APPLICATION_JSON_VALUE)
		public String extractDataKey(@RequestBody ExtractDataKey extractDataKey) throws Exception {
			return passwordManager.extractDataKey(extractDataKey.getEncryptedDataKey(), extractDataKey.getPassword(),
					extractDataKey.getUser());
		}

		@PostMapping(path = "/encryptWithAnswers", produces = MediaType.APPLICATION_JSON_VALUE)
		public String encryptWithAnswers(@RequestBody EncryptWithAnswers encryptWithAnswers) throws Exception {
			return passwordManager.encrypt(encryptWithAnswers.getpText().getBytes(), encryptWithAnswers.getAnswers(),
					encryptWithAnswers.getUser());
		}
		
		@PostMapping(path = "/decryptWithAnswers", produces = MediaType.APPLICATION_JSON_VALUE)
		public String decryptWithAnswers(@RequestBody DecryptWithAnswers decryptWithAnswers) throws Exception {
			return passwordManager.decrypt(decryptWithAnswers.getcText(), decryptWithAnswers.getAnswers(),
					decryptWithAnswers.getUser());
		}
	}

}
