/*
 * Classe que será utilizada como recurso para a classe Category 
 */

package com.ricardo.dscatalog.resources;


import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ricardo.dscatalog.dto.CategoryDTO;
import com.ricardo.dscatalog.services.CategoryService;

// annotations de REST que servem para controlar e mapear as requisições 
// dentro da annotation @RequestMapping é necessário incluir um valor que será a repassado para onde vai buscar

@RestController
@RequestMapping(value ="/categories")    
public class CategoryResource {

	@Autowired
	private CategoryService service;	
	
	@GetMapping 
	public ResponseEntity<List<CategoryDTO>> findAll(){
		List<CategoryDTO> list = service.findAll();	
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping (value = "/{id}") //este argumento na frente da annotation vai incluir o id na frente da @RequestMapping
	public ResponseEntity<CategoryDTO> findById(@PathVariable Long id){
		CategoryDTO dto = service.findById(id);	
		return ResponseEntity.ok().body(dto);
	}
	
	@PostMapping  
	public ResponseEntity<CategoryDTO> insert(@RequestBody CategoryDTO dto){
		dto = service.insert(dto);
		
		//estrutura para inserir um objeto conforme padrão com cabeçalho da resposta do recurso 
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(dto.getId()).toUri();
		
		return ResponseEntity.created(uri).body(dto);
		
	}
}