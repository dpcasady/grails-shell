package io.champion


class ShoppingCartItem {
	
	int quantity
    
	static belongsTo = [ item: Item, store: Store, shoppingCart: ShoppingCart ]

    static mapping = {
        item lazy: false
        store lazy: false
    }
	

	static constraints = {
		id generator: 'sequence', params:[sequence:'ID_SHOPPING_CART_ITEM_PK_SEQ']
    }
}