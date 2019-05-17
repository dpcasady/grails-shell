package io.champion

class Item {

	BigDecimal price
	String catalogNumber
	String description

	Store store

	Date dateCreated
	Date lastUpdated

	static constraints = {
		price(nullable:false)
		catalogNumber(nullable:false)
		description(nullable:false)
		id generator: 'sequence', params:[sequence:'ID_ITEM_PK_SEQ']
    }

}