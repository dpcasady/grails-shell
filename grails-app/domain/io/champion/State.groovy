package io.champion

class State {
	
	String name
	
	Date dateCreated
	Date lastUpdated


	static constraints = {
		name(nullable:false, unique:true)
		id generator: 'sequence', params:[sequence:'ID_STATE_PK_SEQ']
    }

}
