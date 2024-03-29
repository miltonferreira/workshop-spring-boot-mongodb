package com.jotonferreira.workshopmongo.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jotonferreira.workshopmongo.domain.Post;
import com.jotonferreira.workshopmongo.repository.PostRepository;
import com.jotonferreira.workshopmongo.service.exception.ObjectNotFoundException;

@Service // indica que é um serviço que pode ser injetado em outras classes
public class PostService {
	// camada de serviço para pegar infos no BD
	
	@Autowired //assim o spring procura a classe e instancia para ser usada, sendo injetação de dependecia automatica
	private PostRepository repo;
	
	// pega dos os users no BD
	public List<Post> findAll(){
		return repo.findAll(); // retorna todos os usuarios
	}
	
	// procura por ID o user no BD
	public Post findById(String id) {
		Optional<Post> obj = repo.findById(id); // pega no BD o ID
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado")); // retorna o user ou uma exceção
	}
	
	// retorna uma lista de post's na procura
	public List<Post> findByTitle(String text){
		//return repo.findByTitleContainingIgnoreCase(text);
		return repo.findbyTitleQuery(text); // pesquisa mais simples usando @Query na interface PostRepository
	}
	
	// retorna pesquisa no titulo, no corpo do post e comentarios com data minima e maxima
	public List<Post> fullSearch(String text, Date minDate, Date maxDate){
		maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000); // acrescenta mais 1 dia ao maxDate
		return repo.fullSearch(text, minDate, maxDate); // retorna a pesquisa no MongoDB
	}
	
}
