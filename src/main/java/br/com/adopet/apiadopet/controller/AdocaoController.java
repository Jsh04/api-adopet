package br.com.adopet.apiadopet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.adopet.apiadopet.domain.adocao.DadosCadastroAdocao;
import br.com.adopet.apiadopet.domain.adocao.DadosDetalhamentoAdocao;
import br.com.adopet.apiadopet.service.AdocaoService;

@RestController
@RequestMapping("/adocao")
public class AdocaoController {
	
	@Autowired
	private AdocaoService adocaoService;
	
	@PostMapping
	public ResponseEntity<DadosDetalhamentoAdocao> novaAdocao(@RequestBody DadosCadastroAdocao dados, UriComponentsBuilder uriBuilder) {
		var adocao = adocaoService.cadastrar(dados);
		var uri = uriBuilder.path("/tutor/adocao/{id}").buildAndExpand(adocao.getId()).toUri();
		return ResponseEntity.created(uri).body(new DadosDetalhamentoAdocao(adocao));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletarAdocao(@PathVariable Long id){
		adocaoService.deletarAdocao(id);
		return ResponseEntity.noContent().build();
	
	}

}
