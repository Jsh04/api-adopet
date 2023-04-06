package br.com.adopet.apiadopet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.adopet.apiadopet.domain.Pet;
import br.com.adopet.apiadopet.dto.DadoCadastroPet;
import br.com.adopet.apiadopet.exception.DomainAdoPetException;
import br.com.adopet.apiadopet.repository.AbrigoRepository;

@Service
public class PetService {
	
	@Autowired
	private AbrigoRepository abrigoRepository;
	

	public Pet relacionarPetComAbrigo(DadoCadastroPet dados) {
		var abrigoOptional = abrigoRepository.findById(dados.abrigoId());
		if(abrigoOptional.isEmpty()) {
			throw new DomainAdoPetException("Não encontrado");
		}
		var pet = new Pet(dados, abrigoOptional.get());
		
		return pet;
	}
	
}
