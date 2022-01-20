package com.bruno.os.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.bruno.os.services.DBService;



@Configuration
@Profile("dev")  // informando que a classe é um perfil de teste
public class TestConfig {

	
	@Autowired
	private DBService dbService;
	
	@Bean // chamando o metodo altomaticamente
	public void estanciaBD() {
		
		this.dbService.instanciaDB(); // estanciando o bd no metodo de testes
	}

	
}
