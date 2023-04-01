package br.com.adopet.apiadopet.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ola")
public class OlaController {
	
	@GetMapping
	public String ola() {
		return "Ola Spring Boot";
	}
	

}
