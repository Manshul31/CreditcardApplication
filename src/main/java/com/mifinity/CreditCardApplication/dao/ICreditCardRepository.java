package com.mifinity.CreditCardApplication.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.mifinity.CreditCardApplication.model.CreditCard;


/**
 * The ICreditCardRepository interface is the repository that extends the JPA repository.
 * It is connected to credit card table in the database 
 * 
 * @author Manshul Khattar
 * @version 1.0
 */
public interface ICreditCardRepository extends JpaRepository<CreditCard, String> {

	@Modifying
	@Transactional
	@Query(value = "update CREDIT_CARD set EXPIRY_DATE = ?3 where USER_ID = ?1 AND CARD_NUMBER = ?2", nativeQuery = true)
	int updateCardDetails(String username, String cardNumber, String expiryDate);

	@Query(value = "select * from CREDIT_CARD cc WHERE cc.USER_ID = ?1 AND cc.CARD_NUMBER like %?2%", nativeQuery = true)
	List<CreditCard> findCards(String username, String cardNumber);
	
	@Query(value = "select * from CREDIT_CARD cc WHERE cc.CARD_NUMBER like %?1%", nativeQuery = true)
	List<CreditCard> findCardsForAdmin(String cardNumber);

}
