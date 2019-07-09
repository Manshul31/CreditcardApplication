package com.mifinity.CreditCardApplication.service;

import java.io.Serializable;
import java.util.List;

import com.mifinity.CreditCardApplication.model.CreditCard;
import com.mifinity.CreditCardApplication.model.User;

/**
 * ICreditCardService is an interface in which all the method declaration are defined
 * @author Manshul
 * @version 1.0
 */
public interface ICreditCardService extends Serializable {

	public boolean updateCardDetails(String userName, CreditCard creditCard);

	public List<CreditCard> searchCards(String userName, String cardNumber);
	
	public boolean registerUser(User user);

	public boolean addCreditCard(String userName, CreditCard creditCard);

	public User findByUserName(String userName);
	
}
