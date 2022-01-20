package com.bruno.os.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bruno.os.domain.Cliente;
import com.bruno.os.dtos.ClienteDTO;
import com.bruno.os.services.ClienteService;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
@CrossOrigin("*")
@RestController // informando que é uma classe controladora
@RequestMapping(value = "/clientes")  // setando que é o end point inicial para o cliente
public class ClienteController {

	@Autowired
	private ClienteService service;
	
	@GetMapping(value = "/{id}") // informando que vai receber uma variavel path
	public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id){
		
		Cliente obj = service.findById(id);
		ClienteDTO objDTO = new ClienteDTO(obj);  // passando um OBJ cliente para converter em DTO
		return ResponseEntity.ok().body(objDTO);   // recebendo no corpo da requisição um cliente ou null
	}
	@GetMapping	  // metodo para listar todos clientes
public ResponseEntity<List<ClienteDTO>> findAll(){
		
		List<ClienteDTO> listDTO = service.findAll()		// mapeando cada objeto na lista	
				.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO); // resposta com a lista DTO
 }
	
	
	
	@PostMapping       // controller do metodo de cadastro de cliente                              
public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteDTO objDTO){ // passando no body um obj de cliente
		
	Cliente newObj = service.create(objDTO);
	// quando cadastramos um novo obj por boas patricas precisamos passar a URI de acesso aquele novo obj
	URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(newObj.getId()).toUri(); // pega o  novo id e coloca no URI
	return ResponseEntity.created(uri).build();
	
	}	
	
	// Atualizando cliente
	
	@PutMapping(value = "/{id}")			// passando uma variavel path e validando os campos no corpo da requisição
	public ResponseEntity<ClienteDTO> update(@PathVariable Integer id, @Valid @RequestBody ClienteDTO objDTO){
		ClienteDTO newObj = new ClienteDTO(service.update(id, objDTO));
		return ResponseEntity.ok().body(newObj);
	}
	
	// Deletando cliente
	
	@DeleteMapping (value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
	
	
	
