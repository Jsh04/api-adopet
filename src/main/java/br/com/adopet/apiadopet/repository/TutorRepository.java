package br.com.adopet.apiadopet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import br.com.adopet.apiadopet.domain.tutor.Tutor;

@Repository
public interface TutorRepository extends JpaRepository<Tutor, Long> {

	UserDetails findByEmail(String username);

	@Query("SELECT t FROM Tutor t Where t.nome = :nome")
	Tutor encontrarPorNome(@Param(value = "nome") String nome);

}
