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

import br.com.petz.Service.ClienteService;
import br.com.petz.model.Cliente;
import br.com.petz.model.Endereco;
import br.com.petz.repository.ClienteRepository;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository ;
	
	@Autowired
	private ClienteService clienteService;
	
	
	@GetMapping
	public  List<Cliente>  listar(){
		return clienteRepository.findAll();
		
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente criar(@Valid @RequestBody Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Cliente> buscarPeloCodigo(@PathVariable Long codigo){
		
		Optional<Cliente> cliente = clienteRepository.findById(codigo);
		
		if(cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get());
		}else {
			return ResponseEntity.noContent().build();
		}
	}
	
	
	@DeleteMapping("/{codigo}")
	public void remover(@PathVariable Long codigo) {
		Optional<Cliente> cliente = clienteRepository.findById(codigo);
		
		if(cliente.isPresent()) {
			clienteRepository.deleteById(codigo);
		}else {
			 ResponseEntity.noContent().build();
		}
		
	}
	
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Long  codigo, @Valid @RequestBody Cliente cliente){
		Cliente clienteSalvo = clienteService.atualizar(codigo, cliente);
		return ResponseEntity.ok(clienteSalvo);
	}
	

	@PutMapping("/{codigo}/endereco")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarEndereco(@PathVariable Long codigo, @RequestBody Endereco ender) {
		clienteService.atualizarEndereco(codigo, ender);
	}

}
