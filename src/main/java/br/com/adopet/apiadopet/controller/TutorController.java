package br.com.adopet.apiadopet.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.adopet.apiadopet.domain.Tutor;
import br.com.adopet.apiadopet.dto.DadosAtualizacaoTutor;
import br.com.adopet.apiadopet.dto.DadosCadastroTutor;
import br.com.adopet.apiadopet.dto.DadosDetalhamentoTutor;
import br.com.adopet.apiadopet.dto.DadosListagemTutor;
import br.com.adopet.apiadopet.exception.DomainAdoPetException;
import br.com.adopet.apiadopet.repository.TutorRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("tutor")
public class TutorController {
	
	@Autowired
	private TutorRepository tutorRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping
	@Transactional
	public ResponseEntity<DadosDetalhamentoTutor> cadastrar(@RequestBody @Valid DadosCadastroTutor dados, UriComponentsBuilder uriBuilder){
		var senhaCriptografada = passwordEncoder.encode(dados.senha());
		var tutor = new Tutor(dados, senhaCriptografada);		
		tutorRepository.save(tutor);
		var uri = uriBuilder.path("/tutor/{id}").buildAndExpand(tutor.getId()).toUri();
		return ResponseEntity.created(uri).body(new DadosDetalhamentoTutor(tutor));
	}
	
	@GetMapping
	public ResponseEntity<Page<DadosListagemTutor>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
		if(tutorRepository.findAll().isEmpty()) {
			throw new DomainAdoPetException("Nao encontrado");
		}
		var page = tutorRepository.findAll(paginacao).map(DadosListagemTutor::new);
		return ResponseEntity.ok(page);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DadosDetalhamentoTutor> listarPorid(@PathVariable Long id){
		var tutorOpcional = tutorRepository.findById(id);
		if(tutorOpcional.isEmpty()) {
			throw new DomainAdoPetException("Nao encontrado");
		}
		var tutor = tutorOpcional.get();
		return ResponseEntity.ok().body(new DadosDetalhamentoTutor(tutor));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity deletar(@PathVariable Long id) {
		tutorRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PatchMapping("/{id}")
	@Transactional
	public ResponseEntity<DadosDetalhamentoTutor> atualizar(@RequestBody @Valid DadosAtualizacaoTutor dados, @PathVariable Long id){
		var tutorOpcional = tutorRepository.findById(id);
		if(tutorOpcional.isEmpty()) {
			throw new DomainAdoPetException("Não existe esse tutor");
		}
		
		var tutor = tutorOpcional.get();
		
		if(dados.nome() != null) {
			tutor.setNome(dados.nome());
		}
		
		if(dados.email() != null ){
			tutor.setEmail(dados.email());
		}
		
		if(dados.senha() != null) {
			var senhaCriptografada = passwordEncoder.encode(dados.senha());
			tutor.setSenha(senhaCriptografada);
		}
		
		tutorRepository.save(tutor);
		return ResponseEntity.ok(new DadosDetalhamentoTutor(tutor));
		
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity<DadosDetalhamentoTutor> autalizarTudo(@RequestBody @Valid DadosAtualizacaoTutor dados, @PathVariable Long id){
		var tutorOpcional = tutorRepository.findById(id);
		if(tutorOpcional.isEmpty()) {
			throw new DomainAdoPetException("Não existe esse tutor");
		}
		var tutor = tutorOpcional.get();
		
		tutor.setNome(dados.nome());
		tutor.setEmail(dados.email());
		tutor.setSenha(passwordEncoder.encode(dados.senha()));
		tutorRepository.save(tutor);
		
		return ResponseEntity.ok(new DadosDetalhamentoTutor(tutor));
	}

}
