package br.com.adopet.apiadopet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.adopet.apiadopet.domain.Tutor;

@Repository
public interface TutorRepository extends JpaRepository<Tutor, Long> {

}
