package io.champion


class Customer {
	
	String name
	String address
	String city
	State state
	String zip
	String phone
	Date dateOfBirth


	Date dateCreated
	Date lastUpdated

	static hasMany = [ transactions : Transaction ]

	static constraints = {
		name(size:4..255, nullable:false, blank:false, unique:true)
		address(nullable:false)
		city(nullable:false)
		state(nullable:false)
		zip(nullable:false)
		phone(nullable:true)
		dateOfBirth(nullable:true)
		id generator: 'sequence', params:[sequence:'ID_CUSTOMER_PK_SEQ']
    }

}
