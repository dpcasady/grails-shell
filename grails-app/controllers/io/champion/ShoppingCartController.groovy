package io.champion

import grails.converters.*

class ShoppingCartController {
	
	def allowedMethods = [ add : "POST" ]

	def add(Item item){
		
		println "item : ${item}"

		def store = item.store

		def shoppingCart

		if(!session.shoppingCart){
			println "no session...."
			shoppingCart = new ShoppingCart()
			shoppingCart.store = store
			shoppingCart.save(flush:true)
			session.shoppingCart = shoppingCart
		}else{
			println "session...."
			shoppingCart = session.shoppingCart
		}

		println "here.... ${shoppingCart} : ${store}"
		def shoppingCartItem = ShoppingCartItem.findByItemAndStore(item, store)

		if(!shoppingCartItem){
			println "no shopping cart item ..."
			shoppingCartItem = new ShoppingCartItem()
			shoppingCartItem.item = item
			shoppingCartItem.quantity = 1
			shoppingCartItem.shoppingCart = shoppingCart
			shoppingCartItem.store = store

			shoppingCartItem.save(flush:true)

			if (!shoppingCartItem.isAttached()) {
			    shoppingCartItem.attach()
			}
			println "shoppingCartItem : ${shoppingCartItem}"

			shoppingCartItem.errors.allErrors.each { 
				println it
			}

			if(!shoppingCart.isAttached()) {
			    shoppingCart.attach()
			}

			shoppingCart.addToShoppingCartItems(shoppingCartItem)
			shoppingCart.save(flush:true)

		}else{
			if (!shoppingCartItem.isAttached()) {
			    shoppingCartItem.attach()
			}
			shoppingCartItem.quantity++
			shoppingCartItem.save(flush:true)
		}

		render shoppingCart as JSON
	}

}