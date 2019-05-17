package io.champion

import grails.converters.*
import grails.transaction.Transactional

class ShoppingCartController {
	
	def allowedMethods = [ add : "POST" ]


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

		def store = item.store

		def shoppingCart

		if(!session.shoppingCart){
			shoppingCart = new ShoppingCart()
			shoppingCart.store = store
			shoppingCart.save(flush:true)
			session.shoppingCart = shoppingCart
		}else{
			shoppingCart = ShoppingCart.get(session.shoppingCart.id)
			session.shoppingCart = shoppingCart
		}

		def shoppingCartItem = ShoppingCartItem.findByItemAndShoppingCart(item, shoppingCart)

		if(!shoppingCartItem){

			shoppingCartItem = new ShoppingCartItem()
			shoppingCartItem.item = item
			shoppingCartItem.quantity = 1
			shoppingCartItem.shoppingCart = shoppingCart
			shoppingCartItem.store = store

			shoppingCartItem.save(flush:true)

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
		if(shoppingCartItem?.quantity == 1){
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


    def checkout(){
    	
        def shoppingCart = ShoppingCart.get(params.id)
        def customer = Customer.get(params.customer.id)

        if(!customer){
        	flash.message = "Please select a customer...."
        	redirect(action: "index")
        	return
        }

        def transaction = new Transaction()
        transaction.shoppingCart = shoppingCart
        transaction.customer = customer
        transaction.store = shoppingCart.store
        transaction.save(flush:true)

        session.shoppingCart = null

        render(view: "success", model: [ transaction: transaction, shoppingCart: shoppingCart ])
    }   

}