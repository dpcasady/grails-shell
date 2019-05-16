package io.champion

class Store {
	
	String name
	String address
	String city

	State state
	String zip
	String phone

	String type


	Date dateCreated
	Date lastUpdated

	
	static hasMany = [ transactions: Transaction ]


	static constraints = {
		name(size:4..255, nullable:false, blank:false, unique:true)
		address(nullable:false)
		city(nullable:false)
		state(nullable:false)
		zip(nullable:false)
		phone(nullable:true)
		type(nullable:false)
		id generator: 'sequence', params:[sequence:'ID_STORE_PK_SEQ']
    }

}