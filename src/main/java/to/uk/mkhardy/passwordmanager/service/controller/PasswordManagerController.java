package to.uk.mkhardy.passwordmanager.service.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.swagger.annotations.ApiOperation;
import to.uk.mkhardy.passwordmanager.core.beans.impl.Answer;
import to.uk.mkhardy.passwordmanager.core.beans.impl.Password;
import to.uk.mkhardy.passwordmanager.core.beans.impl.Question;
import to.uk.mkhardy.passwordmanager.service.component.PasswordManagerComponent;
import to.uk.mkhardy.passwordmanager.service.exception.PassRuleException;
import to.uk.mkhardy.passwordmanager.service.model.DecryptWithAnswers;
import to.uk.mkhardy.passwordmanager.service.model.EncryptWithAnswers;
import to.uk.mkhardy.passwordmanager.service.model.ExtractDataKey;
import to.uk.mkhardy.passwordmanager.service.model.GenerateDataKey;
import to.uk.mkhardy.passwordmanager.service.model.GetAnswer;
import to.uk.mkhardy.passwordmanager.service.model.GetPassword;
import to.uk.mkhardy.passwordmanager.service.model.IsCorrectAnswer;
import to.uk.mkhardy.passwordmanager.service.model.IsCorrectPassword;

@RestController
@RequestMapping("password-manager")
public class PasswordManagerController {
	
	@Autowired
	private PasswordManagerComponent passwordManagerComponent;
	
	@CrossOrigin
	@ApiOperation(value = "Check password against password rules")
	@PostMapping(path = "/isValidPassword", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> isValidPassword(@RequestParam(value = "password") String password) {

		boolean isValid = false;
		
		Map<String, Object> response = new HashMap<String, Object>();
		List<String> errors =  new LinkedList<String>();
		response.put("errors",errors);
		
		try {
			isValid = passwordManagerComponent.isValidPassword(password);
		} catch (PassRuleException e) {
			isValid = false;
			e.getPasswordRuleException().getPasswordRules().stream().forEach((r)->errors.add(r.getErrorMessageKey()));
		}
		
		if (isValid) {
			response.put("isValid", Boolean.TRUE);
		} else {
			response.put("isValid", Boolean.FALSE);
		}
		return response;
	}

	@CrossOrigin
	@ApiOperation(value = "Checks an answer against a hash")
	@PostMapping(path = "/isCorrectAnswer", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> isCorrectAnswer(@RequestBody IsCorrectAnswer isCorrectAnswer) {

		boolean isValid = passwordManagerComponent.isCorrectAnswer(isCorrectAnswer);

		Map<String, Object> response = new HashMap<String, Object>();
		if (isValid) {
			response.put("isValid", Boolean.TRUE);
		} else {
			response.put("isValid", Boolean.FALSE);
		}

		return response;
	}

	@CrossOrigin
	@ApiOperation(value = "Checks an password against a hash")
	@PostMapping(path = "/isCorrectPassword", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> isCorrectPassword(@RequestBody IsCorrectPassword isCorrectPassword) {

		boolean isValid = passwordManagerComponent.isCorrectPassword(isCorrectPassword);

		Map<String, Object> response = new HashMap<String, Object>();
		if (isValid) {
			response.put("isValid", Boolean.TRUE);
		} else {
			response.put("isValid", Boolean.FALSE);
		}

		return response;
	}

	@CrossOrigin
	@ApiOperation(value = "Hashes a password")
	@PostMapping(path = "/getPassword", produces = MediaType.APPLICATION_JSON_VALUE)
	public Password getPassword(@RequestBody GetPassword getPassword) {
		if(getPassword.getPassword().trim().length()==0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "empty password");
		}
		if(getPassword.getUser().getUserName().trim().length()==0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "empty username");
		}
		Password pass = passwordManagerComponent.getPassword(getPassword);
		return pass;
	}

	@CrossOrigin
	@ApiOperation(value = "Gets the list of security questions")
	@GetMapping(path = "/getQuestions", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Question> getQuestions() {
		return passwordManagerComponent.getQuestions();
	}

	@CrossOrigin
	@ApiOperation(value = "Hashes an answer")
	@PostMapping(path = "/getAnswer", produces = MediaType.APPLICATION_JSON_VALUE)
	public Answer getAnswer(@RequestBody GetAnswer getAnswer) {
		return passwordManagerComponent.getAnswer(getAnswer);
	}

	@CrossOrigin
	@ApiOperation(value = "Generate a data key")
	@PostMapping(path = "/generateDataKey", produces = MediaType.APPLICATION_JSON_VALUE)
	public String generateDataKey(@RequestBody GenerateDataKey generateDataKey)  {
		return passwordManagerComponent.generateDataKey(generateDataKey);
	}

	@CrossOrigin
	@ApiOperation(value = "Extract a data key")
	@PostMapping(path = "/extractDataKey", produces = MediaType.APPLICATION_JSON_VALUE)
	public String extractDataKey(@RequestBody ExtractDataKey extractDataKey)  {
		return passwordManagerComponent.extractDataKey(extractDataKey);
	}

	@CrossOrigin
	@ApiOperation(value = "Encrypt some plaintext with a list of answers")
	@PostMapping(path = "/encryptWithAnswers", produces = MediaType.APPLICATION_JSON_VALUE)
	public String encryptWithAnswers(@RequestBody EncryptWithAnswers encryptWithAnswers)  {
		return passwordManagerComponent.encryptWithAnswers(encryptWithAnswers);
	}
	
	@CrossOrigin
	@ApiOperation(value = "Decrypt some cypher text with a list of answers")
	@PostMapping(path = "/decryptWithAnswers", produces = MediaType.APPLICATION_JSON_VALUE)
	public String decryptWithAnswers(@RequestBody DecryptWithAnswers decryptWithAnswers)  {
		return passwordManagerComponent.decryptWithAnswers(decryptWithAnswers);
	}
}
