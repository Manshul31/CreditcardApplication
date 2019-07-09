package com.mifinity.CreditCardApplication.config;
/**
 * ISecurityService is an interface  which consists of method declarations
 * @author Manshul
 * @version 1.0
 */
public interface ISecurityService {

	String findUserName();
	
	void autoLogin(String username, String password);
	
}
