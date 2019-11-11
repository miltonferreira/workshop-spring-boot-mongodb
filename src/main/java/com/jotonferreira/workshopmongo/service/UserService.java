package com.jotonferreira.workshopmongo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jotonferreira.workshopmongo.domain.User;
import com.jotonferreira.workshopmongo.dto.UserDTO;
import com.jotonferreira.workshopmongo.repository.UserRepository;
import com.jotonferreira.workshopmongo.service.exception.ObjectNotFoundException;

@Service // indica que é um serviço que pode ser injetado em outras classes
public class UserService {
	// camada de serviço para pegar infos no BD
	
	@Autowired //assim o spring procura a classe e instancia para ser usada, sendo injetação de dependecia automatica
	private UserRepository repo;
	
	// pega dos os users no BD
	public List<User> findAll(){
		return repo.findAll(); // retorna todos os usuarios
	}
	
	// procura por ID o user no BD
	public User findById(String id) {
		
		Optional<User> obj = repo.findById(id); // pega no BD o ID
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado")); // retorna o user ou uma exceção
		
	}
	
	// insere um user no MongoDB
	public User insert(User obj) {
		return repo.insert(obj);
	}
	
	public void delete(String id) {
		findById(id); //se não encontrar o ID lança uma exceção
		repo.deleteById(id);
	}
	
	public User update(User obj) {
		
		User newObj = findById(obj.getId()); // procurar o user pelo ID
		
		updateData(newObj, obj); // colocar as infos do user no novo user
		
		return repo.save(newObj); // salvar as novas infos no BD
		
	}
	
	private void updateData(User newObj, User obj) {
		
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
		
	}

	// cria uma novo user com as informações do objDTO
	public User fromDTO(UserDTO objDTO) {
		return new User(objDTO.getId(), objDTO.getName(), objDTO.getEmail());
	}
	
}
