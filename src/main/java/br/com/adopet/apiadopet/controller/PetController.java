package br.com.adopet.apiadopet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
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
import br.com.adopet.apiadopet.domain.pet.DadoCadastroPet;
import br.com.adopet.apiadopet.domain.pet.DadosAtualizacaoPet;
import br.com.adopet.apiadopet.domain.pet.DadosDetalhamentoPet;
import br.com.adopet.apiadopet.exception.DomainAdoPetException;
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
	public ResponseEntity<DadosDetalhamentoPet> cadastrar(@RequestBody @Valid DadoCadastroPet dados,
			UriComponentsBuilder uriBuilder) {
		var pet = petService.relacionarPetComAbrigoCadastro(dados);
		petRepository.save(pet);
		var uri = uriBuilder.path("/pet/{id}").buildAndExpand(pet.getId()).toUri();
		return ResponseEntity.created(uri).body(new DadosDetalhamentoPet(pet));
	}

	@GetMapping
	public ResponseEntity<Page<DadosDetalhamentoPet>> listar(
			@PageableDefault(size = 10, sort = { "nome" }) Pageable paginacao) {
		var page = petRepository.findAllByAdotadoFalse(paginacao).map(DadosDetalhamentoPet::new);
		return ResponseEntity.ok(page);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DadosDetalhamentoPet> listarPorId(@PathVariable Long id) {
		var petOptional = petRepository.findById(id);
		if(petOptional.isEmpty()) {
			throw new DomainAdoPetException("Não encontrado");
		}
		return ResponseEntity.ok(new DadosDetalhamentoPet(petOptional.get()));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletar(@PathVariable Long id){
		petRepository.deleteById(id);
		return ResponseEntity.ok("Pet foi deletado com sucesso!");
	}
	
	@PatchMapping("/{id}")
	@Transactional
	public ResponseEntity<DadosDetalhamentoPet> atualizar(@RequestBody @Valid DadosAtualizacaoPet dados, @PathVariable Long id){
		var petOpcional = petRepository.findById(id);
		if(petOpcional.isEmpty()) {
			throw new DomainAdoPetException("Não existe esse pet");
		}
		var pet = petOpcional.get();
		if(dados.abrigoId() != null) {
			var abrigo = petService.pegaAbrigoAtualizacao(dados.abrigoId());
			pet.setAbrigo(abrigo);
		}
		if(dados.nome() != null) {
			pet.setNome(dados.nome());
		}
		if(dados.imagem() != null) {
			pet.setNome(dados.imagem());
		}
		if(dados.adotado() != null) {
			pet.setAdotado(dados.adotado());
		}
		if(dados.idade() != null) {
			pet.setIdade(dados.idade());
		}
		if(dados.descricao() != null) {
			pet.setDescricao(dados.descricao());
		}	
		if(dados.endereco() != null ){
			var endereco = new Endereco(dados.endereco());
			pet.setEndereco(endereco);
		}
			
		petRepository.save(pet);
		return ResponseEntity.ok(new DadosDetalhamentoPet(pet));
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity<DadosDetalhamentoPet> autalizarTudo(@RequestBody @Valid DadosAtualizacaoPet dados, @PathVariable Long id){
		var petOpcional = petRepository.findById(id);
		if(petOpcional.isEmpty()) {
			throw new DomainAdoPetException("Não existe esse pet");
		}
		var abrigo = petService.pegaAbrigoAtualizacao(dados.abrigoId());
		var pet = petOpcional.get();
		pet.setNome(dados.nome());
		pet.setAbrigo(abrigo);
		pet.setAdotado(dados.adotado());
		pet.setDescricao(dados.descricao());
		var endereco = new Endereco(dados.endereco());
		pet.setEndereco(endereco);
		pet.setIdade(dados.idade());
		pet.setImg(dados.imagem());

		petRepository.save(pet);
		
		return ResponseEntity.ok(new DadosDetalhamentoPet(pet));
	}
}
