package com.jotonferreira.workshopmongo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jotonferreira.workshopmongo.domain.User;
import com.jotonferreira.workshopmongo.repository.UserRepository;
import com.jotonferreira.workshopmongo.service.exception.ObjectNotFoundException;

@Service // indica que é um serviço que pode ser injetado em outras classes
public class UserService {
	// camada de repositorio para pegar infos no BD
	
	@Autowired //assim o spring procura a classe e instancia para ser usada, sendo injetação de dependecia automatica
	private UserRepository repo;
	
	public List<User> findAll(){
		return repo.findAll(); // retorna todos os usuarios
	}
	
	public User findById(String id) {
		
		Optional<User> obj = repo.findById(id); // pega no BD o ID
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado")); // retorna o user ou uma exceção
		
	}
	
}
