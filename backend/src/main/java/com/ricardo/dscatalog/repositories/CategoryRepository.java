package com.ricardo.dscatalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ricardo.dscatalog.entities.Category;

/*
* JpaRepository 
*Por utilizar a JpaRepository (import org.springframework.data.jpa.repository.JpaRepository) 
*como uma interface que extends JpaRepository<T, TI> faz com que tenhamos diversos operações prontas para 
*auxiliar o acesso ao bco de dados relacional que tenha implementação da JPA com Spring (H2, MySQL, Postgress e etc).
*/


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
