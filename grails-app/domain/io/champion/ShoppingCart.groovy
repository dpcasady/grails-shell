package io.champion

class ShoppingCart {
	
	Store store 

	static hasMany = [ items : ShoppingCartItem ]

	static constraints = {
		id generator: 'sequence', params:[sequence:'ID_CART_PK_SEQ']
    }

}