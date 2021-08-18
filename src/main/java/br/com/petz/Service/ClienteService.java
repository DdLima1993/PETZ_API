package br.com.petz.Service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;


import br.com.petz.model.Cliente;
import br.com.petz.model.Endereco;
import br.com.petz.repository.ClienteRepository;


@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente atualizar(Long codigo, Cliente cliente) {


		Cliente clienteSalvo = buscarClientePeloCodigo(codigo);
		
		BeanUtils.copyProperties(cliente, clienteSalvo, "codigo");
		
		return this.clienteRepository.save(clienteSalvo);
	}
	
	public void atualizarEndereco(Long codigo, Endereco ender) {
		
		Cliente clienteSalvo = buscarClientePeloCodigo(codigo);
		
		clienteSalvo.setEndereco(ender);
		
		clienteRepository.save(clienteSalvo);
	}

	private Cliente buscarClientePeloCodigo(Long codigo) {

		Cliente pessoaSalva = this.clienteRepository.findById(codigo)
				.orElseThrow(()-> new EmptyResultDataAccessException(1));
		
		return pessoaSalva;
	}

}
