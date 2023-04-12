package br.com.adopet.apiadopet.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.adopet.apiadopet.domain.pet.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
	
	@Query("select p from Pet p where p.nome = :nome")
	Pet encontrarPorNome(@Param(value = "nome") String nome);

	@Query("SELECT p FROM Pet p where p.adotado = false")
	Page<Pet> findAllByAdotadoFalse(Pageable paginacao);

	


	
	
}
