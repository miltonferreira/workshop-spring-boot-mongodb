package com.jotonferreira.workshopmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.jotonferreira.workshopmongo.domain.User;

@Repository // indica que é um repositorio para conectar com banco de dados MongoDB
// extendendo do MongoRepository indica que vai controlar a classe User com tipo ID String
public interface UserRepository extends MongoRepository<User, String>{

}
