package com.jotonferreira.workshopmongo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jotonferreira.workshopmongo.domain.User;
import com.jotonferreira.workshopmongo.service.UserService;

@RestController // recursos rest para o endpoint
@RequestMapping(value = "/users") // caminho para o endpoint
public class UserResource {
	// camada que fornece o EndPoint de acesso as infos
	
	@Autowired //assim o spring procura a classe e instancia para ser usada, sendo injetação de dependecia automatica
	private UserService service;
	
	@RequestMapping(method = RequestMethod.GET) // obtem informações com o get no padrão Rest >>> @GetMapping é a mesma coisa <<<
	// ResponseEntity encapsula com reposta HTTP com cabeçalhos e possiveis erros
	public ResponseEntity<List<User>> findAll(){
		
		List<User> list = service.findAll(); // pega todos os usuarios no BD
		
		// ok instancia o ResponseEntity com codigo de resposta HTTP, que a resposta ocorreu com sucesso
		return ResponseEntity.ok().body(list); // body é o corpo da resposta com a lista
		
	}

//	@RequestMapping(method = RequestMethod.GET) // obtem informações com o get no padrão Rest >>> @GetMapping é a mesma coisa <<<
//	// ResponseEntity encapsula com reposta HTTP com cabeçalhos e possiveis erros
//	public ResponseEntity<List<User>> findAll(){
//		// >>>>>>>>>>>>> forma manual em banco de dados para incluir usuarios <<<<<<<<<<<<<<<<<
//		
//		User maria = new User("1", "Maria Brown", "maria@gmail.com"); 
//		User alex = new User("2", "Alex Green", "alex@gmail.com");
//		
//		List<User> list = new ArrayList<User>();
//		list.addAll(Arrays.asList(maria, alex)); // adiciona os objs a lista
//		
//		// ok instancia o ResponseEntity com codigo de resposta HTTP, que a resposta ocorreu com sucesso
//		return ResponseEntity.ok().body(list); // body é o corpo da resposta com a lista de objs
//		
//	}
	
}
