package br.com.adopet.apiadopet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.adopet.apiadopet.repository.TutorRepository;

@Service
public class AutenticacaoService implements UserDetailsService {
	
	@Autowired
	private TutorRepository tutorRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return tutorRepository.findByEmail(username);
	}
	
	

}
