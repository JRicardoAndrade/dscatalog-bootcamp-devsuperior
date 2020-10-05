package com.ricardo.dscatalog.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ricardo.dscatalog.dto.CategoryDTO;
import com.ricardo.dscatalog.entities.Category;
import com.ricardo.dscatalog.repositories.CategoryRepository;
import com.ricardo.dscatalog.services.exceptions.DatabaseException;
import com.ricardo.dscatalog.services.exceptions.ResourceNotFoundException;


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
	public Page<CategoryDTO> findAllPaged(PageRequest pageRequest){
		Page<Category> list = repository.findAll(pageRequest);
		return list.map(x -> new CategoryDTO(x));
		
		
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
		Category entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not Found"));
		return new CategoryDTO(entity);
		
		/*Objeto Optinal é uma abordagem desde o Java 8 para evitar que você trabalhei com um objeto NULO, o Spring-Data-JPA 
		 * implementou a busca pelo ID utilizando um objeto optinal ele nunca retorna um objeto NULO. 
		 */
	}

	//método de Criação no bco de dados
	@Transactional 
	public CategoryDTO insert(CategoryDTO dto) {
		Category entity = new Category();
		entity.setName(dto.getName());
		entity = repository.save(entity);
		return new CategoryDTO(entity);
	}

	//método de atualização no bco de dados
	@Transactional 
	public CategoryDTO update(Long id, CategoryDTO dto) {
		try{
			Category entity = repository.getOne(id);
			entity.setName(dto.getName());
			entity = repository.save(entity);
			return new CategoryDTO(entity);
		}
		catch(EntityNotFoundException e){
			throw new ResourceNotFoundException("Id not found "+ id);
		}
	}

	/*o métodp delete não terá a annotation @Transactional pois se fizer isso não será possivel capturar um 
	 * exceção do bco de dados neste método trataremos de dois tipos de exceção 1º de um id inválido e o 
	 * 2ª para garantir a integridade do bco de dados pois caso alguém queira deletar uma categoria de produtos não será
	 * permitido, ex: caso tenhamos a categoria computador e dentro dela existe notebook, pc gamer e etc, só será possivel 
	 * deletar um item interno e não a categoria raiz (computador) pois se este for deletado afetará outras sub categorias 
	 * do sistema
	 */
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}
		catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found "+ id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}
}
