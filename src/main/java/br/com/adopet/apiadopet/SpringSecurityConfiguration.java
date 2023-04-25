package br.com.adopet.apiadopet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.adopet.apiadopet.infra.SecurityFilter;

@Configuration
public class SpringSecurityConfiguration {


	@Autowired
	private SecurityFilter securityFilter;

	@Bean
	public SecurityFilterChain configuration(HttpSecurity http) throws Exception {
		return http
				.csrf(crsf -> crsf.disable())
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.authorizeHttpRequests()
				.requestMatchers(HttpMethod.POST, "/login")
				.permitAll()
				.and()
				.authorizeHttpRequests()
				.requestMatchers(HttpMethod.POST, "/tutor")
				.permitAll()
				.and()
				.authorizeHttpRequests()
				.requestMatchers(HttpMethod.POST, "/abrigo")
				.permitAll()
				.and()
				.authorizeHttpRequests()
				.requestMatchers(HttpMethod.DELETE, "/tutor")
				.hasRole("TUTOR")
				.requestMatchers(HttpMethod.PUT, "/tutor")
				.hasRole("TUTOR")
				.requestMatchers(HttpMethod.GET, "/tutor")
				.permitAll()
				.and()
				.authorizeHttpRequests()
				.requestMatchers(HttpMethod.DELETE, "/abrigo/adocao/{id}")
				.hasRole("ROLE_ABRIGO")
				.and()
				.authorizeHttpRequests()
				.requestMatchers(HttpMethod.POST, "/tutor/adocao")
				.hasAnyRole("ROLE_TUTOR")
				.and()
				.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
				.authorizeHttpRequests()
				.anyRequest()
				.authenticated()
				.and()
				.build();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder encriptarSenha() {
		return new BCryptPasswordEncoder();
	}

}

