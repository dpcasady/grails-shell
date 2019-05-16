package io.champion.common

public enum StoreType {

	GROCERY('Grocery'),
	SPORTING_GOODS('Sporting Goods'),
	ELECTRONICS('Electronics')
	
	private final String type

	StoreType(type){
		this.type = type
	}

	def getT(){
		return this.type
	}

}