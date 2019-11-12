package com.jotonferreira.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.jotonferreira.workshopmongo.domain.Post;
import com.jotonferreira.workshopmongo.domain.User;
import com.jotonferreira.workshopmongo.dto.AuthorDTO;
import com.jotonferreira.workshopmongo.repository.PostRepository;
import com.jotonferreira.workshopmongo.repository.UserRepository;

@Configuration // indica ao MongoDB que é uma classe de configuração
public class Instantiation implements CommandLineRunner{
	// manipula as coleções diretamento no MongoDB
	
	
	@Autowired //assim o spring procura a classe e instancia para ser usada, sendo injetação de dependecia automatica
	private UserRepository userRepository;
	
	@Autowired //assim o spring procura a classe e instancia para ser usada, sendo injetação de dependecia automatica
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		// executa ações no MongoDB
		
		// User ------------------------------------------------------------------------------------
		userRepository.deleteAll(); // deleta toda coleção no MongoDB
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob)); // salva usuarios no MongoDB
		// User ------------------------------------------------------------------------------------
		
		// Post ------------------------------------------------------------------------------------
		postRepository.deleteAll(); // deleta toda coleção no MongoDB
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria)); // com new AuthorDTO se tem um obj para pegar as infos
		Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom Dia", "Acordei feliz hoje!",  new AuthorDTO(maria)); // com new AuthorDTO se tem um obj para pegar as infos
		
		postRepository.saveAll(Arrays.asList(post1, post2)); // salva post's no MongoDB
		// Post ------------------------------------------------------------------------------------
		
	}

}
