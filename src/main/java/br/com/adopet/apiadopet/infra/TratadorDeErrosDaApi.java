package br.com.adopet.apiadopet.infra;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.adopet.apiadopet.exception.DomainAdoPetException;
import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class TratadorDeErrosDaApi {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<EntityNotFoundException> tratarErro404() {
		return ResponseEntity.notFound().build();
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<List<DadosErroValidacao>> tratarErro400(MethodArgumentNotValidException ex){
		var erros = ex.getFieldErrors();
		
		return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList());
	}
	
		@ExceptionHandler(DomainAdoPetException.class)
		public ResponseEntity<DomainAdoPetException> tratarErroDomain() {
			return ResponseEntity.notFound().build();
		}
	
	private record DadosErroValidacao(String campo, String mensagem) {
		public DadosErroValidacao(FieldError erro) {
			this(erro.getField(), erro.getDefaultMessage());
		}
	}
}
