package com.jotonferreira.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jotonferreira.workshopmongo.domain.User;
import com.jotonferreira.workshopmongo.dto.UserDTO;
import com.jotonferreira.workshopmongo.service.UserService;

@RestController // recursos rest para o endpoint
@RequestMapping(value = "/users") // caminho para o endpoint
public class UserResource {
	// camada que fornece o EndPoint de acesso as infos
	
	@Autowired //assim o spring procura a classe e instancia para ser usada, sendo injetação de dependecia automatica
	private UserService service;
	
	@RequestMapping(method = RequestMethod.GET) // obtem informações com o get no padrão Rest >>> @GetMapping é a mesma coisa <<<
	// ResponseEntity encapsula com reposta HTTP com cabeçalhos e possiveis erros
	public ResponseEntity<List<UserDTO>> findAll(){
		
		List<User> list = service.findAll(); // pega todos os usuarios no BD
		
		// converte User para UserDTO com lambda
		List<UserDTO> listDTO = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		
		// ok instancia o ResponseEntity com codigo de resposta HTTP, que a resposta ocorreu com sucesso
		return ResponseEntity.ok().body(listDTO); // body é o corpo da resposta com a lista
		
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET) // com value="/{id}" se cria um novo REST para pegar o id do usuario
	public ResponseEntity<UserDTO> findById(@PathVariable String id){ // com @PathVariable indica que tem que ser igual a info do ID
		
		User obj = service.findById(id); // pega o id requisitado no MongoDB
		
		return ResponseEntity.ok().body(new UserDTO(obj)); // retorna as infos do User como DTO
		
	}
	
	@RequestMapping(method=RequestMethod.POST) // ou PostMapping - com POST é possivel inserir novo user e infos ao BD
	public ResponseEntity<Void> insert(@RequestBody UserDTO objDto){ // @RequestBody = corpo da requisição que é o UserDTO
		
		User obj = service.fromDTO(objDto); // 
		obj = service.insert(obj); // insere no BD o user
		
		// pega o endereço com ID do novo obj que inseriu
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build(); // retorna resposta vazia com codigo 201 e cabeçalho com o novo recurso criado
		
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE) // com DELETE é possivel excluir um user do BD
	public ResponseEntity<Void> delete(@PathVariable String id){ // com @PathVariable indica que tem que ser igual a info do ID
		
		service.delete(id); // pega o id requisitado no MongoDB
		
		return ResponseEntity.noContent().build(); // retorna null com codigo 204 para o HTTP
		
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT) // ou PostMapping - com POST é possivel inserir novo user e infos ao BD
	public ResponseEntity<Void> update(@RequestBody UserDTO objDto, @PathVariable String id){ // @RequestBody = corpo da requisição que é o UserDTO
		
		User obj = service.fromDTO(objDto); // 
		obj.setId(id);
		obj = service.update(obj); // insere no BD o user
		
		return ResponseEntity.noContent().build(); // retorna null com codigo 204 para o HTTP
		
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
