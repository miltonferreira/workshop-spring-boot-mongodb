package com.jotonferreira.workshopmongo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jotonferreira.workshopmongo.domain.User;
import com.jotonferreira.workshopmongo.repository.UserRepository;

@Service // indica que é um serviço que pode ser injetado em outras classes
public class UserService {
	// camada de repositorio para pegar infos no BD
	
	@Autowired //assim o spring procura a classe e instancia para ser usada, sendo injetação de dependecia automatica
	UserRepository repo;
	
	public List<User> findAll(){
		return repo.findAll(); // retorna todos os usuarios
	}
	
}
