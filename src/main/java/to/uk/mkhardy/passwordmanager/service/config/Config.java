package to.uk.mkhardy.passwordmanager.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import to.uk.mkhardy.passwordmanager.core.PasswordManager;
import to.uk.mkhardy.passwordmanager.core.PasswordManagerBuilder;
import to.uk.mkhardy.passwordmanager.core.beans.PasswordRule;
import to.uk.mkhardy.passwordmanager.core.beans.impl.PasswordLengthRule;
import to.uk.mkhardy.passwordmanager.core.beans.impl.PasswordLowercaseCharRule;
import to.uk.mkhardy.passwordmanager.core.beans.impl.PasswordUppercaseCharRule;
import to.uk.mkhardy.passwordmanager.core.beans.impl.Question;
import to.uk.mkhardy.passwordmanager.service.component.PasswordManagerComponent;
import to.uk.mkhardy.passwordmanager.service.component.PasswordManagerComponentImpl;
import to.uk.mkhardy.passwordmanager.service.utils.PasswordNumberRule;
import to.uk.mkhardy.passwordmanager.service.utils.PasswordSpecialCharRule;

@Configuration
@EnableSwagger2
public class Config {
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.regex("/password-manager/.*")).build();
	}
	
	@Bean
	public PasswordManagerComponent getPasswordManagerComponent() {
		return new PasswordManagerComponentImpl(getPasswordManager());
	}
	
	@Bean
	@Scope("singleton")
	public PasswordManager getPasswordManager() {
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

		PasswordManager passwordManager = new PasswordManagerBuilder().addPasswordRule(uppercaseCharRule)
				.addPasswordRule(lowercaseCharRule).addPasswordRule(lengthRule).addPasswordRule(numberRule)
				.addPasswordRule(spCharRule).addQuestion(question1).addQuestion(question2).addQuestion(question3)
				.addQuestion(question4).addQuestion(question5).addQuestion(question6).addQuestion(question7)
				.addQuestion(question8).addQuestion(question9).addQuestion(question10).build();
		
		return passwordManager;
	}
}