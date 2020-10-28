package to.uk.mkhardy.passwordmanager.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class PasswordManagerApp {

	public static void main(String[] args) {
		SpringApplication.run(PasswordManagerApp.class, args);
	}
}
