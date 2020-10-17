package to.uk.mkhardy.passwordmanager.service;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class PasswordManagerAppTest {
	
	@Autowired
	private MockMvc mockMvc;


	@Test
	public void getQuestionsTest() throws Exception {
		this.mockMvc.perform(get("/password-manager/getQuestions"))
				.andExpect(status().isOk());
	}
}
