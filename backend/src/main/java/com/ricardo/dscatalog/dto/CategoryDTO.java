package com.ricardo.dscatalog.dto;

import java.io.Serializable;

import com.ricardo.dscatalog.entities.Category;

/*DTO (Data transfer Object)
 * É um objeto que serve para a transferencia de dados pois o controlador Rest não tem acesso as entidades do 
 * sistema (função exercida pela camada de serviço) por sua vez é transferido para o Rest apenas esse DTO.
 * 
 */


public class CategoryDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	
	public CategoryDTO() {
	}

	public CategoryDTO(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	//Construtor que recebe a própria classe como argumento, serve apenas para facilitar em dado momento 
	public CategoryDTO (Category entity) {
		this.id = entity.getId();
		this.name = entity.getName();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
