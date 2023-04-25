package br.com.adopet.apiadopet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class ApiAdopetApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiAdopetApplication.class, args);
	}

}
