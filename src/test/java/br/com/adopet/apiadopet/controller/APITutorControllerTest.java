package br.com.adopet.apiadopet.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import br.com.adopet.apiadopet.domain.tutor.DadosCadastroTutor;
import br.com.adopet.apiadopet.domain.tutor.DadosDetalhamentoTutor;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@AutoConfigureJsonTesters
public class APITutorControllerTest {
	
	
	@Autowired
	private MockMvc mock;
	
	@Autowired
	private JacksonTester<DadosCadastroTutor> dadosCadastroTutorJacksonTester;
	
	@Autowired 
	private JacksonTester<DadosDetalhamentoTutor> dadosDetalhamentoTutorJacksonTester;
	
	
	
	@Test
	@DisplayName("Deverá cadastrar um tutor vinda de uma requisicao com o metodo post")
	public void cadastrarTutor() throws Exception {
		var cadastro = new DadosCadastroTutor("Jose Barros", "joseBarros@emai.com", "1234");
		var response = mock
				.perform(
						MockMvcRequestBuilders
						.post("/tutor")
						.contentType(MediaType.APPLICATION_JSON)
						.content(dadosCadastroTutorJacksonTester.write(
								cadastro)
								.getJson()))
				.andReturn()
				.getResponse();
		assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
		
		var jsonEsperado = dadosDetalhamentoTutorJacksonTester.write(
					new DadosDetalhamentoTutor(cadastro.nome().toUpperCase(), cadastro.email())
				).getJson();
						
		assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
	}
	
	@Test
	@DisplayName("Deverá cadastrar um tutor vinda de uma requisicao com o metodo post")
	public void cadastrarAbrigo() {
		
	}
	
	
	
}

