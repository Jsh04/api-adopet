package br.com.adopet.apiadopet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.adopet.apiadopet.domain.abrigo.Abrigo;
import br.com.adopet.apiadopet.domain.tutor.Tutor;
import br.com.adopet.apiadopet.dto.DadosAutenticacao;
import br.com.adopet.apiadopet.dto.DadosToken;
import br.com.adopet.apiadopet.infra.TokenService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("login")
public class AutenticacaoController {
	
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<DadosToken> efetuarLogin(@RequestBody @Valid DadosAutenticacao dados){
		var token = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
		var authentication = manager.authenticate(token);
		String tokenJWT = null;
		var roleAuthority = authentication.getAuthorities().stream().toList().get(0);
		String role = roleAuthority.getAuthority();
		if(role.equals("ROLE_TUTOR")) {
			tokenJWT = tokenService.gerarTokenTutor((Tutor) authentication.getPrincipal());
		}else {
			tokenJWT = tokenService.gerarTokenAbrigo((Abrigo) authentication.getPrincipal());
		}
		return ResponseEntity.ok(new DadosToken(tokenJWT));
	}
}
