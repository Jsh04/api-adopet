package br.com.adopet.apiadopet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.adopet.apiadopet.domain.abrigo.Abrigo;

public interface AbrigoRepository extends JpaRepository<Abrigo, Long>{

	UserDetails findByEmail(String subject);

}
