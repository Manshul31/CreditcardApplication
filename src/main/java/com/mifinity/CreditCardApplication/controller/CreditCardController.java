package com.mifinity.CreditCardApplication.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mifinity.CreditCardApplication.config.ISecurityService;
import com.mifinity.CreditCardApplication.model.CreditCard;
import com.mifinity.CreditCardApplication.model.User;
import com.mifinity.CreditCardApplication.service.ICreditCardService;

/**
 * The CreditCardController class contains methods 
 * which  handles the HTTP request and response.
 * @author Manshul Khattar
 * @version 1.0
 */

@Controller
public class CreditCardController {

	Logger logger = LoggerFactory.getLogger(CreditCardController.class);

	@Autowired
	ICreditCardService creditCardService;

	@Autowired
	private ISecurityService securityService;

	/**
	 * This method is for displaying the home page
	 * @return It returns login page
	 */
	@RequestMapping("/")
	public String home() {
		return "login";
	}
	
	/**
	 * This method is for displaying the login page
	 * @param model
	 * @return It returns the login page
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginUser(Model model) {
		return "login";
	}

	/**
	 * This method is for registering the new user
	 * @param model stores the details of card 
	 * @return It returns registration page
	 */
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String registration(Model model) {
		model.addAttribute("user", new User());
		return "registration";
	}

	/**
	 * This method takes user name and password to register a new user
	 *  and store it in a database
	 * @param user object contains user name and password
	 * @return Credit card options page
	 */
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registration(@ModelAttribute("user") User user) {
		String password = user.getPassword();
		if (user != null && !("".equalsIgnoreCase(user.getUsername()) && ("".equalsIgnoreCase(user.getPassword())))) {
			if (creditCardService.registerUser(user)) {
				securityService.autoLogin(user.getUsername(), password);
				return "creditCardOptions";
			}
		}
		return "error";
	}

	/**
	 * This method is for displaying options to admin
	 * @return Admin page
	 */
	@RequestMapping(value = { "/admin" }, method = RequestMethod.GET)
	public String adminOptions() {
		return "admin";
	}
	
	/**
	 * This method displays details to be entered for new credit card
	 * @return New Credit Card Page
	 */
	@RequestMapping(value = { "/createNewCard" }, method = RequestMethod.GET)
	public String addCreditCard() {
		return "createNewCard";
	}
	
	/**
	 * This method displays the credit card options
	 * 
	 * @return Credit Card Options page
	 */
	@RequestMapping(value = { "/creditCardOptions" }, method = RequestMethod.GET)
	public String creditCardOptions() {
		return "creditCardOptions";
	}

	/**
	 * This method verifies the details and store it to database
	 * @param username this parameter holds the name of user logged in
	 * @param creditCard this stores various details of credit card
	 * @return Displays credit card options page on successfully validating the input details
	 
	 */
	@RequestMapping(value = "/createNewCard", method = RequestMethod.POST)
	public String addCreditCard(@RequestParam("username") String username, CreditCard creditCard) {
		if (username != null && !"".equals(username) && creditCard != null) {
			if (creditCardService.addCreditCard(username, creditCard)) {
				return "creditCardOptions";
			} else {
				return "error";
			}
		}
		return "error";
	}

	/**
	 * This method display the existing credit card based on card number search
	 * @return Search Card page is returned
	 */
	@RequestMapping(value = { "/searchCards" }, method = RequestMethod.GET)
	public String searchCards() {
		return "searchCards";
	}

	/**
	 * This method validates username and card number and then displays the details
	 * of the card
	 * @param username of the current logged in user
	 * @param cardNumber of the user
	 * @param model
	 * @return update expiry date page
	 */
	@RequestMapping(value = "/searchCards", method = RequestMethod.POST)
	public String searchCards(@RequestParam("username") String username, String cardNumber, Model model) {
		if (username != null && !"".equals(username) && cardNumber != null && !("".equals(cardNumber))) {
			List<CreditCard> list = creditCardService.searchCards(username, cardNumber);
			if (list != null) {
				model.addAttribute("list", list);
				return "updateExpiryDate";
			}
		}
		return "error";
	}

	/**
	 * This method displays the update expiry date page when the update button is pressed
	 * @return Update Expiry Page is displayed
	 */
	@RequestMapping(value = { "/updateExpiryDate" }, method = RequestMethod.GET)
	public String updateExpiryDate() {
		return "updateExpiryDate";
	}

	/**
	 * This method inputs takes the card number and expiry date to update/modify the expiry date
	 * @param username contains the logged in user name
	 * @param creditCard contains credit card details
	 * @return the credit card options page
	 */
	@RequestMapping(value = "/updateExpiryDate", method = RequestMethod.POST)
	public String updateExpiryDate(@RequestParam("username") String username, CreditCard creditCard) {
		if (username != null && !"".equals(username) && creditCard != null) {
			if (creditCardService.updateCardDetails(username, creditCard)) {
				return "creditCardOptions";
			}
		}
		return "error";
	}

}
