package com.jotonferreira.workshopmongo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.jotonferreira.workshopmongo.domain.User;
import com.jotonferreira.workshopmongo.repository.UserRepository;

@Configuration // indica ao MongoDB que é uma classe de configuração
public class Instantiation implements CommandLineRunner{
	// manipula as coleções diretamento no MongoDB
	
	
	@Autowired //assim o spring procura a classe e instancia para ser usada, sendo injetação de dependecia automatica
	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		// executa ações no MongoDB
		
		userRepository.deleteAll(); // deleta toda coleção no MongoDB
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob)); // salva usuarios no MongoDB
		
	}

}
