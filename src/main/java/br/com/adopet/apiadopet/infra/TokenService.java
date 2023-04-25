package br.com.adopet.apiadopet.infra;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.adopet.apiadopet.domain.abrigo.Abrigo;
import br.com.adopet.apiadopet.domain.tutor.Tutor;

@Service
public class TokenService {
	
	public String gerarTokenTutor(Tutor tutor){
		try {
		    Algorithm algorithm = Algorithm.HMAC256("123456789");
		    String token = JWT.create()
		        .withIssuer("API Adopet")
		        .withSubject(tutor.getUsername())
		        .withExpiresAt(dataExpiracao())
		        .sign(algorithm);
		    return token;
		} catch (JWTCreationException exception){
		   throw new RuntimeException(exception);
		}
		
	}
	
	public String gerarTokenAbrigo(Abrigo abrigo){
		try {
		    Algorithm algorithm = Algorithm.HMAC256("123456789");
		    String token = JWT.create()
		        .withIssuer("API Adopet")
		        .withSubject(abrigo.getUsername())
		        .withExpiresAt(dataExpiracao())
		        .sign(algorithm);
		    return token;
		} catch (JWTCreationException exception){
		   throw new RuntimeException(exception);
		}
	}
	
	
	public String pegaSubject(String tokenJWT) {
		try {
		    var algoritimo = Algorithm.HMAC256("123456789");
		    return JWT
		    		.require(algoritimo)
		    		.withIssuer("API Adopet")
		    		.build()
		    		.verify(tokenJWT)
		    		.getSubject();
		} catch (JWTVerificationException exception){
			throw new RuntimeException(exception.getMessage());
		}
	}

	private Instant dataExpiracao() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}
}
