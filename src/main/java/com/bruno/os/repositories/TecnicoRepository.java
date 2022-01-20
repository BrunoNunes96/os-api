package com.bruno.os.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bruno.os.domain.Tecnico;

@Repository  // informando que é um repositorio
public interface TecnicoRepository  extends JpaRepository<Tecnico, Integer>{ // classe e o tipo primitivo

	@Query("SELECT obj FROM Tecnico obj WHERE obj.cpf =:cpf")
	Tecnico findByCPF(@Param("cpf") String cpf);
}
