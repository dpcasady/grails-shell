package io.champion

class Transaction {

	Store store
	Customer customer 

	static hasMany = [ products: Product ]

	Date dateCreated
	Date lastUpdated

	static constraints = {
		store(nullable:false)
		customer(nullable:false)
		id generator: 'sequence', params:[sequence:'ID_TRANSACTION_PK_SEQ']
    }

}