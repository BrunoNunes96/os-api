package com.bruno.os.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruno.os.domain.Pessoa;
import com.bruno.os.domain.Cliente;
import com.bruno.os.dtos.ClienteDTO;
import com.bruno.os.repositories.PessoaRepository;
import com.bruno.os.repositories.ClienteRepository;
import com.bruno.os.services.exceptions.DataIntegratyViolationException;
import com.bruno.os.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired // injetando o tecnicorepository
	private ClienteRepository repository;
	@Autowired
	private PessoaRepository pessoaRepository;
	
	
	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repository.findById(id); // buscando pelo id
		return obj.orElseThrow(() -> new ObjectNotFoundException( // retorna objeto ou uma excessão
				"Objeto não encontrado! id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

	// metodo que lista todos tecnicos em uma lista
	public List<Cliente> findAll() {
		return repository.findAll();
	}

	// metodo que cadastra novos tecnicos
	public Cliente create(ClienteDTO objDTO) {
		if (findByCPF(objDTO) != null) { // criando a excessao
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}
		Cliente newObj = new Cliente(0, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone());
		return repository.save(newObj);
	}

	// metodo que atualiza um tecnico
	public Cliente update(Integer id, @Valid ClienteDTO objDTO) {

		Cliente oldObj = findById(id);

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
		Cliente obj = findById(id);
		
		if(obj.getList().size() > 0) {
			throw new DataIntegratyViolationException("Pessoa possui orndens de serviço, não pode ser deletado!");
		}
		findById(id);
		repository.deleteById(id);
		
	}

	// metodo para verificar se o cpf ja existe ou nao
	private Pessoa findByCPF(ClienteDTO objDTO) {
		Pessoa obj = pessoaRepository.findByCPF(objDTO.getCpf());
		if (obj != null) {
			return obj;
		}
		return null;

	}

}
