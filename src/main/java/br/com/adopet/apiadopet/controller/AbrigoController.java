package br.com.adopet.apiadopet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
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

import br.com.adopet.apiadopet.domain.Endereco;
import br.com.adopet.apiadopet.domain.abrigo.Abrigo;
import br.com.adopet.apiadopet.domain.abrigo.DadosAtualizacaoAbrigo;
import br.com.adopet.apiadopet.domain.abrigo.DadosCadastroAbrigo;
import br.com.adopet.apiadopet.domain.abrigo.DadosDetalhamentoAbrigo;
import br.com.adopet.apiadopet.exception.DomainAdoPetException;
import br.com.adopet.apiadopet.repository.AbrigoRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("abrigo")
public class AbrigoController {

	
	@Autowired
	private AbrigoRepository abrigoRepository;
	
	@GetMapping
	public ResponseEntity<Page<DadosDetalhamentoAbrigo>> listar(@PageableDefault(size = 10) Pageable pageable){
		var page = abrigoRepository.findAll(pageable);
		
		return ResponseEntity.ok(page.map(DadosDetalhamentoAbrigo::new));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DadosDetalhamentoAbrigo> listarPorId(@PathVariable Long id) {
		var abrigoOptional = abrigoRepository.findById(id);
		if(abrigoOptional.isEmpty()) {
			throw new DomainAdoPetException("Não encontrado");
		}
		return ResponseEntity.ok(new DadosDetalhamentoAbrigo(abrigoOptional.get()));
	}
	
	@PostMapping
	@Transactional 
	public ResponseEntity<DadosDetalhamentoAbrigo> cadastrar(@RequestBody @Valid DadosCadastroAbrigo dados, UriComponentsBuilder uriBuilder) {
		var abrigo = new Abrigo(dados);
		abrigoRepository.save(abrigo);
		var uri = uriBuilder.path("/pet/{id}").buildAndExpand(abrigo.getId()).toUri();
		return ResponseEntity.created(uri).body(new DadosDetalhamentoAbrigo(abrigo));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletar(@PathVariable Long id){
		abrigoRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PatchMapping("/{id}")
	@Transactional
	public ResponseEntity<DadosDetalhamentoAbrigo> atualizar(@RequestBody @Valid DadosAtualizacaoAbrigo dados, @PathVariable Long id){
		var abrigoOpcional = abrigoRepository.findById(id);
		if(abrigoOpcional.isEmpty()) {
			throw new DomainAdoPetException("Não existe esse abrigo");
		}
		
		var abrigo = abrigoOpcional.get();
		
		if(dados.nome() != null) {
			abrigo.setNome(dados.nome());
		}
		
		if(dados.endereco() != null ){
			var endereco = new Endereco(dados.endereco());
			abrigo.setEndereco(endereco);
		}
			
		abrigoRepository.save(abrigo);
		return ResponseEntity.ok(new DadosDetalhamentoAbrigo(abrigo));
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity<DadosDetalhamentoAbrigo> autalizarTudo(@RequestBody @Valid DadosAtualizacaoAbrigo dados, @PathVariable Long id){
		var abrigoOpcional = abrigoRepository.findById(id);
		if(abrigoOpcional.isEmpty()) {
			throw new DomainAdoPetException("Não existe esse abrigo");
		}
		var abrigo = abrigoOpcional.get();
		
		var endereco = new Endereco(dados.endereco());
		
		abrigo.setNome(dados.nome());
		abrigo.setEndereco(endereco);
		abrigoRepository.save(abrigo);
		
		return ResponseEntity.ok(new DadosDetalhamentoAbrigo(abrigo));
	}
	
}
