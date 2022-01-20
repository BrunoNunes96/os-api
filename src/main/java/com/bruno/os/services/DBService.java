package com.bruno.os.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruno.os.domain.Cliente;
import com.bruno.os.domain.Os;
import com.bruno.os.domain.Prioridade;
import com.bruno.os.domain.Status;
import com.bruno.os.domain.Tecnico;
import com.bruno.os.repositories.ClienteRepository;
import com.bruno.os.repositories.OsRepository;
import com.bruno.os.repositories.TecnicoRepository;

@Service
public class DBService {

	@Autowired // injetando um repository
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private OsRepository osRepository;

	
	public void instanciaDB() {

		// estanciando os objetos
		Tecnico t1 = new Tecnico(0, "Bruno Nunes", "258.683.400-35", "(41)99235-3867");
		Tecnico t2 = new Tecnico(0, "Bruno Nunes", "258.683.400-35", "(41)99235-3867");
		Cliente c1 = new Cliente(0, "Betina Campos", "275.261.640-65", "(41)99235-3867");
		Os os1 = new Os(null, Prioridade.ALTA, "Teste create OD", Status.ANDAMENTO, t1, c1);

		// adicionando cliente e tecnico na lista
		t1.getList().add(os1);
		c1.getList().add(os1);

		// salvando tecnicos, cliente e os
		tecnicoRepository.saveAll(Arrays.asList(t1, t2));
		clienteRepository.saveAll(Arrays.asList(c1));
		osRepository.saveAll(Arrays.asList(os1));
	}
}
