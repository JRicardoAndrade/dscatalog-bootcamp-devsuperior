/*
 * Classe que será utilizada como recurso para a classe Product 
 */

package com.ricardo.dscatalog.resources;


import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ricardo.dscatalog.dto.ProductDTO;
import com.ricardo.dscatalog.services.ProductService;

// annotations de REST que servem para controlar e mapear as requisições 
// dentro da annotation @RequestMapping é necessário incluir um valor que será a repassado para onde vai buscar

@RestController
@RequestMapping(value ="/products")    
public class ProductResource {

	@Autowired
	private ProductService service;	
	
	
	
	@GetMapping 
	public ResponseEntity<Page<ProductDTO>> findAll(
			//			
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy			
			){
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		Page<ProductDTO> list = service.findAllPaged(pageRequest);	
		
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping (value = "/{id}") //este argumento na frente da annotation vai incluir o id na frente da @RequestMapping
	public ResponseEntity<ProductDTO> findById(@PathVariable Long id){
		ProductDTO dto = service.findById(id);	
		return ResponseEntity.ok().body(dto);
	}
	
	
	//CREATED uma categoria no bco de dados
	@PostMapping  
	public ResponseEntity<ProductDTO> insert(@RequestBody ProductDTO dto){
		dto = service.insert(dto);
		
		//estrutura para inserir um objeto conforme padrão com cabeçalho da resposta do recurso 
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(dto.getId()).toUri();
		
		return ResponseEntity.created(uri).body(dto);
		
	}
	
	
	//UPDATE de um item no bco de dados
	@PutMapping(value = "/{id}")  
	public ResponseEntity<ProductDTO> update(@PathVariable Long id, @RequestBody ProductDTO dto){
		dto = service.update(id, dto);
		return ResponseEntity.ok().body(dto);
	}
	
	//DELETE de um item no bco de dados
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ProductDTO> delete(@PathVariable Long id){
	service.delete(id);
	return ResponseEntity.noContent().build();
	}
}