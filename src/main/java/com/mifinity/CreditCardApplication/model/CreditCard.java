package com.mifinity.CreditCardApplication.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * CreditCard Model is used to define structure of credit card table with validations
 * @author Manshul
 * @version 1.0
 *
 */
@Entity
public class CreditCard implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3685665146595978033L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotNull
	@Size(min = 2,max = 30)
	private String cardHolderName;
	
	@NotNull
	@Size(min=16,max=16)
	private String cardNumber;
	
	@NotNull
	private String expiryDate;

	
	@ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "USER_ID", referencedColumnName = "username", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}


	/**
	 * @return the cardHolderName
	 */
	public String getCardHolderName() {
		return cardHolderName;
	}


	/**
	 * @param cardHolderName the cardHolderName to set
	 */
	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}


	/**
	 * @return the cardNumber
	 */
	public String getCardNumber() {
		return cardNumber;
	}


	/**
	 * @param cardNumber the cardNumber to set
	 */
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}


	/**
	 * @return the expiryDate
	 */
	public String getExpiryDate() {
		return expiryDate;
	}


	/**
	 * @param expiryDate the expiryDate to set
	 */
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}


	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}


	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CreditCard [id=" + id + ", cardHolderName=" + cardHolderName + ", cardNumber=" + cardNumber
				+ ", expiryDate=" + expiryDate + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	/*@Override
	public String toString() {
		return "CreditCard [id=" + id + ", cardHolderName=" + cardHolderName + ", cardNumber=" + cardNumber
				+ ", expiryDate=" + expiryDate + ", user=" + user + "]";
	}*/

}
