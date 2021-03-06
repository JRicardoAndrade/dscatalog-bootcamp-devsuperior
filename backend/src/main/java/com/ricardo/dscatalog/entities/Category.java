package com.ricardo.dscatalog.entities;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name = "tb_category") //define o nome da tabela
public class Category implements Serializable {
	
	/*  SERIALIZABLE
	 *Padrão da linguagem Java que serve para um objeto seja convertido em sequências de bytes que serve para o objeto ser gravado
	 *em arquivos passar pelas redes, essa é uma prática muito utilizada pois evita problemas dessa natureza. 
	 */

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//Annotation que auto incrementa o id quando você inserir o 1º o sistema automaticamente coloca os próx números
	private Long id;
	private String name;
	
	
	//UTC sem fuso horário pegando Greenwich 
	@Column (columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant createdAt;

	@Column (columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	Instant updatedAt;
	
	public Category() {
	}

	public Category(Long id, String name) {
		this.id = id;
		this.name = name;
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
	
	public Instant getCreatedAt() {
		return createdAt;
	}


	public Instant getUpdateAt() {
		return updatedAt;
	}
	
	@PrePersist
	public void prePersist() {
		createdAt = Instant.now();
	}
	
	@PreUpdate
	public void preUpdate() {
		updatedAt = Instant.now();
	}


/*
 *  hashCode() e equals() realizam a comparação se is ibjetos são igauis a diferença entre eles é que o hashCade() é mais rápido
 *  porém existe uma margem muito pequena mas existe de dois objetos terem o mesmo número (que é gerado automaticamente NÃO É O 
 *  DESENVOLVEDOR QUE ATRIBUI ESSE NÚMERO e esses números que são comparados pelo hashCode()), já o "equals()" realiza uma 
 *  comparação mais LENTA porém mais precisa de um objeto, desta forma um objeto CATEGORY pode ser comparado com outro objeto
 *  CATEGORY pela variável "id". A melhor forma de utilizar é fazer o hashCoder() correr uma coleção e caso ele retorne dois objetos
 *  iguais utilizamos o equals() pois ele confirmara de forma mais precisa.  
 */
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
