package com.mifinity.CreditCardApplication.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mifinity.CreditCardApplication.model.User;

/**
 * The IUserRepository interface is the repository that extends the JPA repository.
 * It is connected to User table in the database 
 * 
 * @author Manshul Khattar
 * @version 1.0
 */
public interface IUserRepository extends JpaRepository<User, String>{
	
	User findByUsername(String username);
}
