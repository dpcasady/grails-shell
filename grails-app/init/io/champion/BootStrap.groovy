package io.champion

import io.champion.State
import io.champion.Customer
import io.champion.Store
import io.champion.common.StoreType

import grails.util.Environment

class BootStrap {

	def storeNames = [ "Kaymbo", "Dabjam", "Muxo"]

	def storeTypes = [ StoreType.GROCERY.t, StoreType.SPORTING_GOODS.t, StoreType.ELECTRONICS.t ]

	def customerNames = ["Suellen Buttner", "Tobey Summerell", "Angelo Cranston", "Matteo Jennings", "Bernadine McClinton", "Terry Mulheron",
			"Romy Krale", "Buiron Lethardy", "Dniren Toke", "Archibold McGrudder"]

	def states = [ "Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut", "Delaware", "Florida", 
			"Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", 
			"Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", 
			"New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", 
			"Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", 
			"West Virginia", "Wisconsin", "Wyoming"]


	def cities = [ "Hezhi", "Kongwan", "Temizhbekskaya", "Henggang", "Karnobat", "Amiens", "Sakaidechō", "Bebekan", "Jibiya", "Bal’shavik" ]



    def init = { servletContext ->
    	createStates()

		if(Environment.current == Environment.DEVELOPMENT) {
			createData()
		}
	}


    def destroy = {}


    def createData(){
    	createStores()
    	createItems()
    	createCustomers()
    }


    def createStores(){
    	(0..3).each{ n ->
    		def state = State.findByName(states[n])
			def zip = Math.abs(new Random().nextInt() % [9901]) + 10001

    		def store = new Store()
    		store.name = storeNames[n]
    		store.address = n +  " Street "
    		store.city = cities[n]

    		store.state = state
    		store.zip = zip

    		store.phone = "(602)345-678" + n
    		
    		store.type = storeTypes[n]

    		store.save(flush:true)

    	}

    	println "Stores : ${Store.count()}"

    }


    def createItems(){

    	def stores = Store.list()
    	stores.each { store ->
    		def min = Math.abs(new Random().nextInt() % [10]) + 0
    		def max = Math.abs(new Random().nextInt() % [20]) + 11

    		def items = Math.abs(new Random().nextInt() % [max]) + min

    		println "items : ${items}"

    		(0..items).each { m ->

    			def item = new Item()
    			item.price = Math.abs(new Random().nextInt() % [3]) + 29
    			item.catalogNumber = Math.abs(new Random().nextInt() % [9901]) + 10001
    			item.description = "Item " + m

    			item.store = store
    			item.errors.allErrors.each {
  	 		        println it
 	  		    }

    			item.save(flush:true)

    			store.addToItems(item)
    			store.save(flush:true)

    		}
    	}
    }


    def createCustomers(){
    	
    	(0..10).each{ n ->
    		def state = State.findByName(states[n])
			def zip = Math.abs(new Random().nextInt() % [9901]) + 10001

    		def customer = new Customer()
    		customer.name = customerNames[n]
    		customer.address = n +  " Street "
    		customer.city = cities[n]

    		customer.state = state
    		customer.zip = zip

    		customer.phone = "(602)123-123" + n
    		customer.birthday = new Date() + n
    		customer.save(flush:true)
    	}

    	println "Customers : ${Customer.count()}"
    }



    def createStates(){
    	states.each(){ name ->
			def state = new State()
			state.name = name
			state.save(flush:true)
		}

		println "States : ${State.count()}"
    }
}
