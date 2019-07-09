package com.mifinity.CreditCardApplication.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.mifinity.CreditCardApplication.dao.ICreditCardRepository;
import com.mifinity.CreditCardApplication.dao.IRoleRepository;
import com.mifinity.CreditCardApplication.dao.IUserRepository;
import com.mifinity.CreditCardApplication.model.CreditCard;
import com.mifinity.CreditCardApplication.model.User;
import com.mifinity.CreditCardApplication.service.impl.CreditCardServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CreditCardServiceImplTest {
	
	@Autowired
	private CreditCardServiceImpl creditCardServiceImpl;
	
	@MockBean
	private IUserRepository userRepository;
	
	@MockBean
	private ICreditCardRepository creditCardRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	

	@Test
	public void testFindByUserName() {
		User user = new User();
		
		user.setUsername("Manshul");
		user.setPassword("123");
		Mockito.when(userRepository.findByUsername(user.getUsername())).thenReturn(user);
		assertThat(creditCardServiceImpl.findByUserName(user.getUsername())).isEqualTo(user);
	
	}
	
		
	@Test
	public void testRegisterUser() {
		User user = new User();
		
		user.setUsername("Manshul");
		user.setPassword(bCryptPasswordEncoder.encode("123"));
		Mockito.when(userRepository.save(user)).thenReturn(user);
		assertThat(creditCardServiceImpl.registerUser(user)).isEqualTo(true);
	
	}
	

	
	@Test
	public void testAddCreditCard() {
		User user = new User();
		CreditCard creditCard = new CreditCard();
		
		user.setUsername("Manshul");
		user.setPassword("123");
		creditCard.setUser(user);
		creditCard.setCardHolderName("Manshul Khattar");
		creditCard.setCardNumber("4756213909876432");
		creditCard.setExpiryDate("20/12");
		
		Mockito.when(userRepository.findByUsername(user.getUsername())).thenReturn(user);
		Mockito.when(creditCardRepository.save(creditCard)).thenReturn(creditCard);
		assertThat(creditCardServiceImpl.addCreditCard(user.getUsername(), creditCard)).isEqualTo(true);
	
	}
	
	@Test
	public void testSearchCard() {
		User user = new User();
		CreditCard creditCard = new CreditCard();
		
		
		user.setUsername("Manshul");
		user.setPassword("123");
		creditCard.setUser(user);
		creditCard.setCardHolderName("Manshul Khattar");
		creditCard.setCardNumber("4756213909876432");
		creditCard.setExpiryDate("20/12");
		
	
		
		List<CreditCard> creditCardList = new ArrayList<>();
		creditCardList.add(creditCard);
		
			
		
		Mockito.when(creditCardRepository.findCards(user.getUsername(),
				creditCard.getCardNumber())).thenReturn(creditCardList);
		assertThat(creditCardServiceImpl.searchCards(user.getUsername(),
				creditCard.getCardNumber())).isEqualTo(creditCardList);
	
	}
	
	@Test
	public void testUpdateCardDetails() {
		User user = new User();
		CreditCard creditCard = new CreditCard();
		
		
		user.setUsername("Manshul");
		user.setPassword("123");
		creditCard.setUser(user);
		creditCard.setCardHolderName("Manshul Khattar");
		creditCard.setCardNumber("4756213909876432");
		creditCard.setExpiryDate("20/12");
			
		
		Mockito.when(creditCardRepository.updateCardDetails(user.getUsername(),
				creditCard.getCardNumber(),creditCard.getExpiryDate())).thenReturn(1);
		assertThat(creditCardServiceImpl.updateCardDetails(user.getUsername(),
				creditCard)).isEqualTo(true);
	
	}
	
}
