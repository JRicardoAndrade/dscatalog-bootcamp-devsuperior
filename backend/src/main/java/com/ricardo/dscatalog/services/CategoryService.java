package com.ricardo.dscatalog.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ricardo.dscatalog.dto.CategoryDTO;
import com.ricardo.dscatalog.entities.Category;
import com.ricardo.dscatalog.repositories.CategoryRepository;
import com.ricardo.dscatalog.services.exceptions.EntityNotFoundException;


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
	
	
	
	//Na camada de serviço não vai trafegar entidade apenas os DTOs 
	@Transactional (readOnly = true)
	public List<CategoryDTO> findAll(){
		List<Category> list = repository.findAll();
		return list.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());
		
		
		/*É necessário converter uma list de category em uma lista de categoryDTO pode ser feito de duas formas 
		 * por um laço for que vai percorrer toda a lista e busca o category e adiciona na lista de categoryDTO ao final 
		 * retorna a listDTO
		 * 
		 * List<CategoryDTO> listDTO = new ArrayList<>();
		 * for (Category cat : list){
		 * 		listDTO.add(new CategoryDTO(cat));
		 * }
		 * return listDTO 
		 * 
		 * A melhor forma de realizar essa conversão de Category para CategoryDTO é por uma expressão LAMBDA pois ficará 
		 * mais resumido......... a função stream().map() transforma a lista CategoryDTO em stream depois o processo é o inverso
		 * de stream() para list().
		 */
		
	}

	@Transactional (readOnly = true)
	public CategoryDTO findById(Long id) {
		Optional<Category> obj = repository.findById(id);
		// o sistema tentará acessar o obj<category> caso ele não encontre vai trazer a mensagem descrita na exceção
		Category entity = obj.orElseThrow(() -> new EntityNotFoundException("Entity not Found"));
		return new CategoryDTO(entity);
		
		/*Objeto Optinal é uma abordagem desde o Java 8 para evitar que você trabalhei com um objeto NULO, o Spring-Data-JPA 
		 * implementou a busca pelo ID utilizando um objeto optinal ele nunca retorna um objeto NULO. 
		 */
	}

}
