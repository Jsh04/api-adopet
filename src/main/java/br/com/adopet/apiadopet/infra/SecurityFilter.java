package br.com.adopet.apiadopet.infra;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.adopet.apiadopet.repository.AbrigoRepository;
import br.com.adopet.apiadopet.repository.TutorRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

	@Autowired
	private TokenService tokenService;
	
	@Autowired 
	private TutorRepository tutorRepository;
	
	@Autowired
	private AbrigoRepository abrigoRepository;
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		var tokenJWT = recuperarToken(request);
		
		if(tokenJWT != null) {
			var subject = tokenService.pegaSubject(tokenJWT);
			var abrigo = abrigoRepository.findByEmail(subject);
			var tutor = tutorRepository.findByEmail(subject);
			if(abrigo != null) {
				var authentication = new UsernamePasswordAuthenticationToken(abrigo, null, abrigo.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}else {
				var authentication = new UsernamePasswordAuthenticationToken(tutor, null, tutor.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
			
		}
		
		filterChain.doFilter(request, response);
		
	}

	private String recuperarToken(HttpServletRequest request) {
		var authorizationHeader = request.getHeader("Authorization");
		if(authorizationHeader != null) {
			return authorizationHeader.replace("bearer ", "");
		}
		return null;
	}
}

