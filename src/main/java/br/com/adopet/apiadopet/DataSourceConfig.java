package br.com.adopet.apiadopet;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {

	@Value("${DATASOURCE_URL}")
	private String urlDataSource;

	@Value("${DATASOURCE_PASSWORD}")
	private String passawordDataSource;
	
	@Bean
	public DataSource configureDataSource() {
		return DataSourceBuilder.create().driverClassName("com.mysql.cj.jdbc.Driver")
				.url("jdbc:mysql://localhost:3306/adopet").username("root").password("1234").build();
	}

}
