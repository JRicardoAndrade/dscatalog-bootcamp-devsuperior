package com.ricardo.dscatalog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ricardo.dscatalog.entities.Category;
import com.ricardo.dscatalog.repositories.CategoryRepository;


/*A annotation @Service serve para registrar a classe como um componente que vai participar do sistema de 
 * INJEÇÃO DE DEPENDÊNCIA AUTOMATIZADO do Spring, isso quer dizer que o responsável pelo gerenciamento as ]
 * instancias dos objetos do tipo CategoryService será o próprio Spring.
 * 
 * Sempre é melhor colocar a annotation mais apropriada do que realmente "ele é" 
 */


@Service
public class CategoryService {
	
	/*Para utilizar é necessário que a classe onde será injetada a dependência tenha um obj da classse que 
	 *será chamada seguida da annotation de injeção de dependência (@Autorwired), a classe que será chamada 
	 *precisa está com a annotation de dependência (@Component, @Repository ou @Service) para que ela possa ser 
	 *chamada e utilizada, desta forma habilita os métodos que podem ser utilizados 
	 */
	
	@Autowired
	private CategoryRepository repository;
	
	public List<Category> findAll(){
		return repository.findAll();
	}

}
