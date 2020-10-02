/*
 * Classe que será utilizada como recurso para a classe Category 
 */

package com.ricardo.dscatalog.resources;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}