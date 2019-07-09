package com.mifinity.CreditCardApplication.service.impl;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mifinity.CreditCardApplication.dao.ICreditCardRepository;
import com.mifinity.CreditCardApplication.dao.IRoleRepository;
import com.mifinity.CreditCardApplication.dao.IUserRepository;
import com.mifinity.CreditCardApplication.model.CreditCard;
import com.mifinity.CreditCardApplication.model.Role;
import com.mifinity.CreditCardApplication.model.User;
import com.mifinity.CreditCardApplication.service.ICreditCardService;
import com.mifinity.CreditCardApplication.util.Constants;

/**
 * CreditCardServiceImpl is a class that extends the ICreditCardService 
 * and overrides all the methods present in it. It is the class in which the business logic
 * is implemented
 * @author Manshul
 * @version 1.0
 */

@Service
public class CreditCardServiceImpl implements ICreditCardService {

	private static final long serialVersionUID = -2412131761198704032L;

	@Autowired
	private ICreditCardRepository creditCardRepository;

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private IRoleRepository roleRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public boolean registerUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRoles(new HashSet<Role>() {{add(roleRepository.findByName(Constants.ROLE_USER));}});
		if (userRepository.save(user) != null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method is used to find the user in database
	 * 
	 * @param username contains logged in user name
	 * @return user details 
	 * 
	 */
	@Override
	public User findByUserName(String username) {
		return userRepository.findByUsername(username);
	}

	/**
	 * This method adds the new Credit to the database
	 * 
	 * @param username contains logged in user name
	 * @param creditCard contains credit card details
	 * 
	 * @return true if saved else false
	 */
	@Override
	public boolean addCreditCard(String username, CreditCard creditCard) {
		User returnedUser = userRepository.findByUsername(username);
		if (returnedUser != null) {
			creditCard.setUser(returnedUser);
			if (creditCardRepository.save(creditCard) != null) {
				return true;
			}
		}
		return false;
	}

	/**
	 * This method search for card number with the help of user name
	 * 
	 * @param username contains logged in user name
	 * @param cardNumber contains credit card number
	 * 
	 * @return list of matched cards
	 */
	public List<CreditCard> searchCards(String username, String cardNumber) {
		User user = userRepository.findByUsername(username);
		
		List<CreditCard> cards = null;
			if ("admin".equals(username)) {
				cards = creditCardRepository.findCardsForAdmin(cardNumber);
			} else {
				cards = creditCardRepository.findCards(username, cardNumber);
			}
			return cards;
	}

	/**
	 * This method updates the credit card details
	 * 
	 * @param username contains name of user whose details need to be updated
	 * @param creditCard contains details of credit card of user 
	 * 
	 * returns true if updated else return false
	 * 
	 */
	@Override
	public boolean updateCardDetails(String username, CreditCard creditCard) {
		if (username != null && !"".equals(username) && creditCard != null && !("".equalsIgnoreCase(creditCard.getCardNumber()))) {
			if (creditCardRepository.updateCardDetails(username, creditCard.getCardNumber(),
					creditCard.getExpiryDate()) == 1) {
				return true;
			}
		}
		return false;
	}

}
