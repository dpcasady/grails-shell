package io.champion

class ShoppingCart {
	
	Store store 

	static hasMany = [ shoppingCartItems: ShoppingCartItem ]
	
	static mapping = {
		sort id: "asc"
		shoppingCartItems sort: "id", order: "asc"
	}

	static constraints = {
		id generator: 'sequence', params:[sequence:'ID_CART_PK_SEQ']
    }

}