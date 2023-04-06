package br.com.adopet.apiadopet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.adopet.apiadopet.domain.abrigo.Abrigo;
import br.com.adopet.apiadopet.dto.DadosCadastroAbrigo;
import br.com.adopet.apiadopet.dto.DadosDetalhamentoAbrigo;
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
	
	@PostMapping
	@Transactional 
	public ResponseEntity<DadosDetalhamentoAbrigo> cadastrar(@RequestBody @Valid DadosCadastroAbrigo dados, UriComponentsBuilder uriBuilder) {
		var abrigo = new Abrigo(dados);
		abrigoRepository.save(abrigo);
		var uri = uriBuilder.path("/pet/{id}").buildAndExpand(abrigo.getId()).toUri();
		return ResponseEntity.created(uri).body(new DadosDetalhamentoAbrigo(abrigo));
	}
	
}
