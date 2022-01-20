package com.bruno.os.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruno.os.domain.Cliente;
import com.bruno.os.domain.Os;
import com.bruno.os.domain.Prioridade;
import com.bruno.os.domain.Status;
import com.bruno.os.domain.Tecnico;
import com.bruno.os.dtos.OsDTO;
import com.bruno.os.repositories.OsRepository;
import com.bruno.os.services.exceptions.ObjectNotFoundException;

@Service
public class OsService {

	@Autowired
	private OsRepository repository;
	
	@Autowired
	private TecnicoService tecnicoService;
	
	@Autowired
	private ClienteService clienteService;
	
	public Os findById(Integer id) {
		Optional<Os> obj = repository.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! id: " + id + ", tipo: " + Os.class.getName() ));
	}
	
	public List<Os> findAll(){
		return repository.findAll();
	}

	public Os create(@Valid OsDTO obj) {
		return fromDTO(obj);

	}
	
	public Os update(@Valid OsDTO obj) {
		findById(obj.getId());
		return fromDTO(obj);
	}
	
	private Os fromDTO(OsDTO obj) {
		Os newObj = new Os();
		newObj.setId(obj.getId());
		newObj.setObservacoes(obj.getObservacoes());
		newObj.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
		newObj.setStatus(Status.toEnum(obj.getStatus()));
		
		Tecnico tec = tecnicoService.findById(obj.getTecnico());
		Cliente cli = clienteService.findById(obj.getCliente());
		
		newObj.setTecnico(tec);
		newObj.setCliente(cli);
		
		if(newObj.getStatus().getCod() ==(2)) {
			newObj.setDataFechamento(LocalDateTime.now());
		}
		return repository.save(newObj);
	}


}
