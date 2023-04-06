package br.com.adopet.apiadopet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.adopet.apiadopet.dto.DadoCadastroPet;
import br.com.adopet.apiadopet.dto.DadosDetalhamentoPet;
import br.com.adopet.apiadopet.repository.PetRepository;
import br.com.adopet.apiadopet.service.PetService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("pet")
public class PetController {

	@Autowired
	private PetRepository petRepository;
	
	@Autowired 
	private PetService petService;
	
	@PostMapping
	@Transactional
	public ResponseEntity<DadosDetalhamentoPet> cadastrar(@RequestBody @Valid DadoCadastroPet dados, UriComponentsBuilder uriBuilder){
		System.out.println(dados.abrigoId());
		var pet = petService.cadastrarPet(dados);
		petRepository.save(pet);
		var uri = uriBuilder.path("/pet/{id}").buildAndExpand(pet.getId()).toUri();
		return ResponseEntity.created(uri).body(new DadosDetalhamentoPet(pet));
	}
	
	@GetMapping
	public ResponseEntity<Page<DadosDetalhamentoPet>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
		var page = petRepository.findAll(paginacao).map(DadosDetalhamentoPet::new);
		return ResponseEntity.ok(page);
	}
	
		
	}

