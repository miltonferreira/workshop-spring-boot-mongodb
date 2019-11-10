package com.jotonferreira.workshopmongo.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jotonferreira.workshopmongo.domain.User;

@RestController // recursos rest para o endpoint
@RequestMapping(value = "/users") // caminho para o endpoint
public class UserResource {

	@RequestMapping(method = RequestMethod.GET) // obtem informações com o get no padrão Rest >>> @GetMapping é a mesma coisa <<<
	// ResponseEntity encapsula com reposta HTTP com cabeçalhos e possiveis erros
	public ResponseEntity<List<User>> findAll(){
		
		User maria = new User("1", "Maria Brown", "maria@gmail.com"); 
		User alex = new User("2", "Alex Green", "alex@gmail.com");
		
		List<User> list = new ArrayList<User>();
		list.addAll(Arrays.asList(maria, alex)); // adiciona os objs a lista
		
		// ok instancia o ResponseEntity com codigo de resposta HTTP, que a resposta ocorreu com sucesso
		return ResponseEntity.ok().body(list); // body é o corpo da resposta com a lista de objs
		
	}
	
}
