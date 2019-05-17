package io.champion

import grails.converters.*

class ShoppingCartController {
	
	def allowedMethods = [ add : "POST", remove: "POST" ]


	def index(){
		def shoppingCart
		if(session.shoppingCart){
			shoppingCart = ShoppingCart.get(session.shoppingCart.id)
		}

		if(!shoppingCart){
			redirect(controller: "store", action: "index")
			return
		}
		[ shoppingCart: shoppingCart ]
	}


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
			shoppingCart = ShoppingCart.get(session.shoppingCart.id)
			session.shoppingCart = shoppingCart
		}

		def shoppingCartItem = ShoppingCartItem.findByItemAndStore(item, store)

		if(!shoppingCartItem){

			shoppingCartItem = new ShoppingCartItem()
			shoppingCartItem.item = item
			shoppingCartItem.quantity = 1
			shoppingCartItem.shoppingCart = shoppingCart
			shoppingCartItem.store = store

			shoppingCartItem.save(flush:true)

			if (!shoppingCartItem.isAttached()) {
			    shoppingCartItem.attach()
			}

			shoppingCartItem.errors.allErrors.each { 
				println it
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


	def remove(ShoppingCartItem shoppingCartItem){

		if(shoppingCartItem.quantity == 1){
			def shoppingCart = shoppingCartItem.shoppingCart
			shoppingCart.removeFromShoppingCartItems(shoppingCartItem)
			shoppingCartItem.delete(flush:true)

			if(shoppingCart.shoppingCartItems.size() == 0){
				shoppingCart.delete(flush:true)
				session.shoppingCart = null
			}
		}else{
			shoppingCartItem.quantity--
			shoppingCartItem.save(flush:true)
		}



		redirect(action: "index")

	}

}