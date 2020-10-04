package com.ricardo.dscatalog.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ricardo.dscatalog.services.exceptions.DatabaseException;
import com.ricardo.dscatalog.services.exceptions.ResourceNotFoundException;



//Annotation que permite que a classe intercepte a exceção para receber o tratamento adequado
@ControllerAdvice
public class ResourceExceptionHandler {
	
	
	//HttpServletRequest é quem vai trazer as informações da pág com o erro
	@ExceptionHandler(ResourceNotFoundException.class	)
	public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException e, HttpServletRequest request){
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(); 
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Resource not found");
		err.setMensagem(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	
	//HttpServletRequest é quem vai trazer as informações da pág com o erro
		@ExceptionHandler(DatabaseException.class	)
		public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request){
			HttpStatus status = HttpStatus.BAD_REQUEST;
			StandardError err = new StandardError(); 
			err.setTimestamp(Instant.now());
			err.setStatus(status.value());
			err.setError("Database exception");
			err.setMensagem(e.getMessage());
			err.setPath(request.getRequestURI());
			return ResponseEntity.status(status).body(err);
		}

}
