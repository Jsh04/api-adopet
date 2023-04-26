package br.com.adopet.apiadopet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.adopet.apiadopet.domain.abrigo.Abrigo;

public interface AbrigoRepository extends JpaRepository<Abrigo, Long>{

	Abrigo findByEmail(String subject);

}
