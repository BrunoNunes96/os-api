package com.bruno.os.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.bruno.os.services.DBService;



@Configuration
@Profile("test")  // informando que a classe é um perfil de teste
public class DevConfig {

	
	@Autowired
	private DBService dbService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String ddl;
	
	@Bean // chamando o metodo altomaticamente
	public boolean estanciaBD() {
		
		
	if(ddl.equals("create")){
		
		this.dbService.instanciaDB(); // estanciando o bd no metodo de testes
	}
	return false;
  }
}
