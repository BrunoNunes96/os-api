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

import com.bruno.os.domain.Tecnico;
import com.bruno.os.dtos.TecnicoDTO;
import com.bruno.os.services.TecnicoService;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

@CrossOrigin("*")
@RestController // informando que é uma classe controladora
@RequestMapping(value = "/tecnicos")  // setando que é o end point inicial para o tecnico
public class TecnicoController {

	@Autowired
	private TecnicoService service;
	
	@GetMapping(value = "/{id}") // informando que vai receber uma variavel path
	public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id){
		
		Tecnico obj = service.findById(id);
		TecnicoDTO objDTO = new TecnicoDTO(obj);  // passando um OBJ tecnico para converter em DTO
		return ResponseEntity.ok().body(objDTO);   // recebendo no corpo da requisição um tecnico ou null
	}
	@GetMapping	  // metodo para listar todos tecnicos
public ResponseEntity<List<TecnicoDTO>> findAll(){
		
		List<TecnicoDTO> listDTO = service.findAll()		// mapeando cada objeto na lista	
				.stream().map(obj -> new TecnicoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO); // resposta com a lista DTO
 }
	
	
	
	@PostMapping       // controller do metodo de cadastro de tecnico                              
public ResponseEntity<TecnicoDTO> create(@Valid @RequestBody TecnicoDTO objDTO){ // passando no body um obj de tecnico
		
	Tecnico newObj = service.create(objDTO);
	// quando cadastramos um novo obj por boas patricas precisamos passar a URI de acesso aquele novo obj
	URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(newObj.getId()).toUri(); // pega o  novo id e coloca no URI
	return ResponseEntity.created(uri).build();
	
	}	
	
	// Atualizando tecnico
	
	@PutMapping(value = "/{id}")			// passando uma variavel path e validando os campos no corpo da requisição
	public ResponseEntity<TecnicoDTO> update(@PathVariable Integer id, @Valid @RequestBody TecnicoDTO objDTO){
		TecnicoDTO newObj = new TecnicoDTO(service.update(id, objDTO));
		return ResponseEntity.ok().body(newObj);
	}
	
	// Deletando tecnico
	
	@DeleteMapping (value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
	
	
	
