package br.com.adopet.apiadopet.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.adopet.apiadopet.domain.Tutor;

@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DataJpaTest(showSql = true)
class TutorRepositoryTest {
	
	@Autowired(required = true)
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
	
	private void cadastrarTutor() {
		var tutor = new Tutor(null, "Silvio", "josesilvio@email.com", "1234");
		em.persist(tutor);
	}
	
	

}
