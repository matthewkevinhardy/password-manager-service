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
import to.uk.mkhardy.passwordmanager.core.PasswordManager;
import to.uk.mkhardy.passwordmanager.core.beans.impl.Answer;
import to.uk.mkhardy.passwordmanager.core.beans.impl.Password;
import to.uk.mkhardy.passwordmanager.core.beans.impl.Question;
import to.uk.mkhardy.passwordmanager.core.impl.PasswordRuleException;
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
	private PasswordManager passwordManager;
	
	@CrossOrigin
	@ApiOperation(value = "Check password against password rules")
	@PostMapping(path = "/isValidPassword", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> isValidPassword(@RequestParam(value = "password") String password) {

		boolean isValid = false;
		
		Map<String, Object> response = new HashMap<String, Object>();
		List<String> errors =  new LinkedList<String>();
		response.put("errors",errors);
		
		try {
			isValid = passwordManager.isValidPassword(password);
		} catch (PasswordRuleException e) {
			isValid = false;
			e.getPasswordRules().stream().forEach((r)->errors.add(r.getErrorMessageKey()));
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

		boolean isValid = passwordManager.isCorrectAnswer(isCorrectAnswer.getpText(), isCorrectAnswer.getAnswer());

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
		Password pass = passwordManager.getPassword(getPassword.getPassword(), getPassword.getUser());
		return pass;
	}

	@CrossOrigin
	@ApiOperation(value = "Gets the list of security questions")
	@GetMapping(path = "/getQuestions", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Question> getQuestions() {
		return passwordManager.getQuestions();
	}

	@CrossOrigin
	@ApiOperation(value = "Hashes an answer")
	@PostMapping(path = "/getAnswer", produces = MediaType.APPLICATION_JSON_VALUE)
	public Answer getAnswer(@RequestBody GetAnswer getAnswer) {
		return passwordManager.getAnswer(getAnswer.getAnswer(), getAnswer.getUser(), getAnswer.getQuestion());
	}

	@CrossOrigin
	@ApiOperation(value = "Generate a data key")
	@PostMapping(path = "/generateDataKey", produces = MediaType.APPLICATION_JSON_VALUE)
	public String generateDataKey(@RequestBody GenerateDataKey generateDataKey)  {
		try {
			return passwordManager.generateDataKey(generateDataKey.getPassword(), generateDataKey.getUser());
		} catch (Exception e) {
			throw new ResponseStatusException(
			           HttpStatus.INTERNAL_SERVER_ERROR, "Error", e);
		}
	}

	@CrossOrigin
	@ApiOperation(value = "Extract a data key")
	@PostMapping(path = "/extractDataKey", produces = MediaType.APPLICATION_JSON_VALUE)
	public String extractDataKey(@RequestBody ExtractDataKey extractDataKey)  {
		try {
			return passwordManager.extractDataKey(extractDataKey.getEncryptedDataKey(), extractDataKey.getPassword(),
					extractDataKey.getUser());
		} catch (Exception e) {
			throw new ResponseStatusException(
			           HttpStatus.INTERNAL_SERVER_ERROR, "Error", e);
		}
	}

	@CrossOrigin
	@ApiOperation(value = "Encrypt some plaintext with a list of answers")
	@PostMapping(path = "/encryptWithAnswers", produces = MediaType.APPLICATION_JSON_VALUE)
	public String encryptWithAnswers(@RequestBody EncryptWithAnswers encryptWithAnswers)  {
		try {
			return passwordManager.encrypt(encryptWithAnswers.getpText().getBytes(), encryptWithAnswers.getAnswers(),
					encryptWithAnswers.getUser());
		} catch (Exception e) {
			throw new ResponseStatusException(
			           HttpStatus.INTERNAL_SERVER_ERROR, "Error", e);
		}
	}
	
	@CrossOrigin
	@ApiOperation(value = "Decrypt some cypher text with a list of answers")
	@PostMapping(path = "/decryptWithAnswers", produces = MediaType.APPLICATION_JSON_VALUE)
	public String decryptWithAnswers(@RequestBody DecryptWithAnswers decryptWithAnswers)  {
		try {
			return passwordManager.decrypt(decryptWithAnswers.getcText(), decryptWithAnswers.getAnswers(),
					decryptWithAnswers.getUser());
		} catch (Exception e) {
			throw new ResponseStatusException(
			           HttpStatus.INTERNAL_SERVER_ERROR, "Error", e);
		}
	}
}
