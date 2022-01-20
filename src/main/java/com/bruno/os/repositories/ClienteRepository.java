package com.bruno.os.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bruno.os.domain.Cliente;

@Repository  // informando que é um repositorio
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

	
}
