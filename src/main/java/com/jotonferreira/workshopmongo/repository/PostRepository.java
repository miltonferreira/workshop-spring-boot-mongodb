package com.jotonferreira.workshopmongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.jotonferreira.workshopmongo.domain.Post;

@Repository // indica que é um repositorio para conectar com banco de dados MongoDB
// extendendo do MongoRepository indica que vai controlar a classe Post com tipo ID String
public interface PostRepository extends MongoRepository<Post, String>{
	
	@Query("{ 'title': { $regex: ?0, $options: 'i' } }") // ?0 indica que é o primeiro parametro "text" - 'i' indica para ignorar case sensitive
	List<Post> findbyTitleQuery(String text);
	
	// query method do MongoDB
	List<Post> findByTitleContainingIgnoreCase(String text);
	
}
