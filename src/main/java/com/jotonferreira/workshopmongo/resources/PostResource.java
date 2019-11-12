package com.jotonferreira.workshopmongo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jotonferreira.workshopmongo.domain.Post;
import com.jotonferreira.workshopmongo.resources.util.URL;
import com.jotonferreira.workshopmongo.service.PostService;

@RestController // recursos rest para o endpoint
@RequestMapping(value = "/posts") // caminho para o endpoint
public class PostResource {
	// camada que fornece o EndPoint de acesso as infos
	
	@Autowired //assim o spring procura a classe e instancia para ser usada, sendo injetação de dependecia automatica
	private PostService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET) // com value="/{id}" se cria um novo REST para pegar os posts
	public ResponseEntity<Post> findById(@PathVariable String id){ // com @PathVariable indica que tem que ser igual a info do ID
		
		Post obj = service.findById(id); // pega o id requisitado no MongoDB
		
		return ResponseEntity.ok().body(obj); // retorna as infos dos posts
		
	}
	
	@RequestMapping(value="/titlesearch", method=RequestMethod.GET) // cria um novo REST para pegar os posts pesquisados
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value="text", defaultValue = "") String text){ // 
		
		text = URL.decodeParam(text); // decodifica a string da pesquisa
		
		List<Post> list = service.findByTitle(text); // faz busca/query
		
		return ResponseEntity.ok().body(list); // retorna as infos da lista de post's
		
	}
	
}
