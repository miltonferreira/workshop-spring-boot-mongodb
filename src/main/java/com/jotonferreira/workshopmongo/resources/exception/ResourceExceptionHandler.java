package com.jotonferreira.workshopmongo.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.jotonferreira.workshopmongo.service.exception.ObjectNotFoundException;

@ControllerAdvice // indica que a classe trata possiveis erros nas requisições
public class ResourceExceptionHandler {
	// manipulador de exceções
	
	@ExceptionHandler(ObjectNotFoundException.class) // indica qual é a classe que manipula a exceção
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		
		HttpStatus status = HttpStatus.NOT_FOUND; // indica que é um erro 404
		
		StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Não encontrado", e.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);
		
	}

}
