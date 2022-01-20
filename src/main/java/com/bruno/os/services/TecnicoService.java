package com.bruno.os.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruno.os.domain.Pessoa;
import com.bruno.os.domain.Tecnico;
import com.bruno.os.dtos.TecnicoDTO;
import com.bruno.os.repositories.PessoaRepository;
import com.bruno.os.repositories.TecnicoRepository;
import com.bruno.os.services.exceptions.DataIntegratyViolationException;
import com.bruno.os.services.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {

	@Autowired // injetando o tecnicorepository
	private TecnicoRepository repository;
	@Autowired
	private PessoaRepository pessoaRepository;
	
	
	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = repository.findById(id); // buscando pelo id
		return obj.orElseThrow(() -> new ObjectNotFoundException( // retorna objeto ou uma excessão
				"Objeto não encontrado! id: " + id + ", Tipo: " + Tecnico.class.getName()));
	}

	// metodo que lista todos tecnicos em uma lista
	public List<Tecnico> findAll() {
		return repository.findAll();
	}

	// metodo que cadastra novos tecnicos
	public Tecnico create(TecnicoDTO objDTO) {
		if (findByCPF(objDTO) != null) { // criando a excessao
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}
		Tecnico newObj = new Tecnico(0, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone());
		return repository.save(newObj);
	}

	// metodo que atualiza um tecnico
	public Tecnico update(Integer id, @Valid TecnicoDTO objDTO) {

		Tecnico oldObj = findById(id);

		if (findByCPF(objDTO) != null && findByCPF(objDTO).getId() != id) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}

		oldObj.setNome(objDTO.getNome());
		oldObj.setCpf(objDTO.getCpf());
		oldObj.setTelefone(objDTO.getTelefone());

		return repository.save(oldObj);

	}

// metodo para deletar um tecnico

	public void delete(Integer id) {
		Tecnico obj = findById(id);
		
		if(obj.getList().size() > 0) {
			throw new DataIntegratyViolationException("Técnico possui orndens de serviço, não pode ser deletado!");
		}
		findById(id);
		repository.deleteById(id);
		
	}

	// metodo para verificar se o cpf ja existe ou nao
	private Pessoa findByCPF(TecnicoDTO objDTO) {
		Pessoa obj = pessoaRepository.findByCPF(objDTO.getCpf());
		if (obj != null) {
			return obj;
		}
		return null;

	}

}
