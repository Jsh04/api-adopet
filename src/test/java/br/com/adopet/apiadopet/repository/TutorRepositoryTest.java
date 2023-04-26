package br.com.adopet.apiadopet.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import br.com.adopet.apiadopet.domain.tutor.DadosCadastroTutor;
import br.com.adopet.apiadopet.domain.tutor.Tutor;

@AutoConfigureTestDatabase(replace = Replace.NONE)
@DataJpaTest(showSql = true)
@ActiveProfiles("test")
class TutorRepositoryTest {
	
	@Autowired
	private TutorRepository tutorRepository;

	@Autowired
	private TestEntityManager em;

	@Test
	@DisplayName("Deve retorna todos os tutores")
	void retornaTododsOsTutores() {
		cadastrarTutor();
		var tutores = tutorRepository.findAll();
		assertThat(tutores).isNotNull();
	}
	
	private Tutor cadastrarTutor() {
		var tutor = new Tutor(new DadosCadastroTutor("Silvio Henrique", "jose@gmail.com", null, null, null, null), "1234");
		var tutorSalvo = em.persist(tutor);
		return tutorSalvo;
	}
	
	@Test
	@DisplayName("Deve retorna apenas o tutor informado pelo id")
	void retornaTutorPeloId() {
		var tutorSalvo = cadastrarTutor();
		var tutor = tutorRepository.findById(tutorSalvo.getId());
		assertThat(tutor.get().getNome()).isEqualTo(tutor.get().getNome());
	}
	
	

}

