package io.champion

class Transaction {

	Store store
	Customer customer 

	ShoppingCart shoppingCart

	Date dateCreated
	Date lastUpdated

	static constraints = {
		id generator: 'sequence', params:[sequence:'ID_TRANSACTION_PK_SEQ']
    }

}