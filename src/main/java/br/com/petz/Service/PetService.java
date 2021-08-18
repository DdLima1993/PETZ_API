package br.com.petz.Service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;


import br.com.petz.model.Pet;
import br.com.petz.repository.PetRepository;

@Service
public class PetService {
	
	@Autowired
	private PetRepository petRepository;
	
	
	public Pet atualizar(Long codigo, Pet pet) {


		Pet petSalvo = buscarPetPeloCodigo(codigo);
		
		BeanUtils.copyProperties(pet, petSalvo, "codigo");
		
		return this.petRepository.save(petSalvo);
	}
	
	
	private Pet buscarPetPeloCodigo(Long codigo) {

		Pet petSalvo = this.petRepository.findById(codigo)
				.orElseThrow(()-> new EmptyResultDataAccessException(1));
		
		return petSalvo;
	}

}
