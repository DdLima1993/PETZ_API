package br.com.petz.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.petz.Service.PetService;
import br.com.petz.model.Cliente;
import br.com.petz.model.Pet;
import br.com.petz.repository.ClienteRepository;
import br.com.petz.repository.PetRepository;

@RestController
@RequestMapping("/pets")
public class PetController {
	
	@Autowired
	private PetRepository petRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private PetService petService;
	
	@GetMapping
	public  List<Pet>  listar(){
		return petRepository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Pet> criar(@Valid @RequestBody Pet pet) {
		
		Optional<Cliente> c = clienteRepository.findById(pet.getCliente());
		
		if(c.isPresent()) {
			return ResponseEntity.ok(petRepository.save(pet));		
		}else {
			return ResponseEntity.badRequest().build();
		}
		
	}
	
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Pet> buscarPeloCodigo(@PathVariable Long codigo){
		
		Optional<Pet> pet = petRepository.findById(codigo);
		
		if(pet.isPresent()) {
			return ResponseEntity.ok(pet.get());
		}else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@DeleteMapping("/{codigo}")
	public void remover(@PathVariable Long codigo) {
		Optional<Pet> pet = petRepository.findById(codigo);
		
		if(pet.isPresent()) {
			petRepository.deleteById(codigo);
		}else {
			 ResponseEntity.noContent().build();
		}
		
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Pet> atualizar(@PathVariable Long  codigo, @Valid @RequestBody Pet pet){
		Pet petSalvo = petService.atualizar(codigo, pet);
		return ResponseEntity.ok(petSalvo);
	}
	
	
	
	

}
