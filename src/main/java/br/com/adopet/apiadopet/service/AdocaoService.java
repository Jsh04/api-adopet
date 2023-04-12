package br.com.adopet.apiadopet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.adopet.apiadopet.domain.adocao.Adocao;
import br.com.adopet.apiadopet.domain.adocao.DadosCadastroAdocao;
import br.com.adopet.apiadopet.exception.DomainAdoPetException;
import br.com.adopet.apiadopet.repository.AdocaoRepository;
import br.com.adopet.apiadopet.repository.PetRepository;
import br.com.adopet.apiadopet.repository.TutorRepository;

@Service
public class AdocaoService {

	
	@Autowired
	private AdocaoRepository adocaoRepository;
	
	@Autowired
	private PetRepository petRepository;
	
	@Autowired
	private TutorRepository tutorRepository;

	public Adocao cadastrar(DadosCadastroAdocao dados) {
		var pet = petRepository.encontrarPorNome(dados.nomeAnimal().toUpperCase());
		var tutor = tutorRepository.encontrarPorNome(dados.nome());
		if(pet == null ) {
			throw new DomainAdoPetException("Pet não encontrado");
		}
		if(tutor == null) {
			throw new DomainAdoPetException("Tutor não encontrado");
		}
		var adocao = new Adocao(dados);
		pet.excluir();
		adocao.setPet(pet);
		adocao.setTutor(tutor);
		
		adocaoRepository.save(adocao);
		return adocao;
	}
	
}
