package com.ricardo.dscatalog.services.exceptions;

/*
* Tratamento de exceções para criar um tratamento personalizado a classe que vai ser responsável por esse tratamento deve
* herdar de uma das classes de exception: 
* Exception essa classe obriga o programador a tratar a exceção por meio de 'try' e 'catch' o compilador não permite que o 
* programa prossiga; 
* RuntimeException essa classe é mais flexivel pois não para o compilador deixando o programador tratar ou não a exceção;
* Dentro da classe criamos um construtor que recebe uma mensagem que apenas repassa para o "super(msg);" -> super classe. 
*/

public class DatabaseException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public DatabaseException(String msg) {
		super(msg);
	}

}
