package com.mifinity.CreditCardApplication.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mifinity.CreditCardApplication.model.Role;
/**
 * The IRoleRepository interface is the repository that extends the JPA repository.
 * It is connected to Role table in the database 
 * 
 * @author Manshul Khattar
 * @version 1.0
 */
public interface IRoleRepository extends JpaRepository<Role, Long>{
	
	Role findByName(String name);

}
