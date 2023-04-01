package br.com.adopet.apiadopet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SpringSecurityConfiguration {
	
	@Bean
	public PasswordEncoder encriptarSenha() {
		return new BCryptPasswordEncoder();
	}

}
