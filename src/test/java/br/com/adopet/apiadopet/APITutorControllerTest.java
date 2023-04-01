package br.com.adopet.apiadopet;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class APITutorControllerTest {
	
	
	@Autowired
	private MockMvc mock;
	
	
	@Test
	@DisplayName("Deverá cadastrar um tutor vinda de uma requisicao com o metodo post")
	public void cadastrarTutor() throws Exception {
		var response = mock.perform(MockMvcRequestBuilders.post("tutor")).andExpect(result -> result.getResponse().getStatus());

	}
	
}
