package br.com.adopet.apiadopet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.adopet.apiadopet.domain.abrigo.Abrigo;
import br.com.adopet.apiadopet.domain.pet.DadoCadastroPet;
import br.com.adopet.apiadopet.domain.pet.Pet;
import br.com.adopet.apiadopet.exception.DomainAdoPetException;
import br.com.adopet.apiadopet.repository.AbrigoRepository;

@Service
public class PetService {
	
	@Autowired
	private AbrigoRepository abrigoRepository;
	

	public Pet relacionarPetComAbrigoCadastro(DadoCadastroPet dados) {
		var abrigoOptional = abrigoRepository.findById(dados.abrigoId());
		if(abrigoOptional.isEmpty()) {
			throw new DomainAdoPetException("Não encontrado");
		}
		var pet = new Pet(dados, abrigoOptional.get());
		
		return pet;
	}
	
	public Abrigo pegaAbrigoAtualizacao(Long id) {
		var abrigoOptional = abrigoRepository.findById(id);
		if(abrigoOptional.isEmpty()) {
			throw new DomainAdoPetException("Não encontrado");
		}
		
		return abrigoOptional.get();
	}
	
}
