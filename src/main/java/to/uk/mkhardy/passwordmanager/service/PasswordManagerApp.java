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
import to.uk.mkhardy.passwordmanager.service.beans.GetAnswer;
import to.uk.mkhardy.passwordmanager.service.beans.GetPassword;
import to.uk.mkhardy.passwordmanager.service.beans.IsValidAnswer;

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
				.addPasswordRule(lowercaseCharRule).addPasswordRule(lengthRule).addQuestion(question1)
				.addQuestion(question2).addQuestion(question3).addQuestion(question4).addQuestion(question5)
				.addQuestion(question6).addQuestion(question7).addQuestion(question8).addQuestion(question9)
				.addQuestion(question10).build();
	}
	
	@RestController
	@RequestMapping("password-manager")
	public class PasswordManagerController {

		@GetMapping("/logging")
		public String logging() {
			logger.info("Log test");
			return "check your logs";
		}
		
		@PostMapping(path="/isValidPassword",produces = MediaType.APPLICATION_JSON_VALUE)
		public Map<String,String> isValidPassword(@RequestParam(value = "password") String password) {
			
			boolean isValid = false;
			String error=null;
			
			try {
				isValid = passwordManager.isValidPassword(password);
			} catch (PasswordRuleException e) {
				isValid=false;
				error = e.getPasswordRule().getErrorMessageKey();
			}
			
			Map<String, String> response = new HashMap<String, String>();
			if(isValid) {
				response.put("isValid","true");
			}
			else {
				response.put("isValid","false");
				response.put("error",error);
			}
			return response;
		}
		
		@GetMapping(path="/isValidAnswer",produces = MediaType.APPLICATION_JSON_VALUE)
		public Map<String,String> isValidAnswer(@RequestBody IsValidAnswer isValidAnswer) {
			
			boolean isValid = passwordManager.isValidAnswer(isValidAnswer.getpText(), isValidAnswer.getAnswer());
			
			Map<String, String> response = new HashMap<String, String>();
			if(isValid) {
				response.put("isValid","true");
			}
			else {
				response.put("isValid","false");
			}
			
			return response;
		}
		
		@PostMapping(path="/getPassword",produces = MediaType.APPLICATION_JSON_VALUE)
		public Password getPassword(@RequestBody GetPassword getPassword) {
			Password pass = passwordManager.getPassword(getPassword.getPassword(), getPassword.getUser());
			return pass;
		}
		
		@GetMapping(path="/getQuestions",produces = MediaType.APPLICATION_JSON_VALUE)
		public List<Question> getQuestions() {
			return passwordManager.getQuestions();
		}
		
		@GetMapping(path="/getAnswer",produces = MediaType.APPLICATION_JSON_VALUE)
		public Answer getAnswer(@RequestBody GetAnswer getAnswer) {
			return passwordManager.getAnswer(getAnswer.getAnswer(), getAnswer.getUser(), getAnswer.getQuestion());
		}
		
		
	}
	
	
}
