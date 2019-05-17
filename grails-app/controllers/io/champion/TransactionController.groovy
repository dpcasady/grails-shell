package io.champion

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class TransactionController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE", checkout: "POST"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Transaction.list(params), model:[transactionCount: Transaction.count()]
    }

    def show(Transaction transaction) {
        respond transaction
    }


    @Transactional
    def checkout(ShoppingCart shoppingCart){
        def customer = session.customer

        def transaction = new Transaction()
        transaction.shoppingCart = shoppingCart
        transasction.customer = Customer.get(1)
        transaction.store = shoppingCart.store
        transaction.save(flush:true)

        redirect(action: "show", id: transaction.id)
    }   


    @Transactional
    def save(Transaction transaction) {
        if (transaction == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (transaction.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond transaction.errors, view:'create'
            return
        }

        transaction.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'transaction.label', default: 'Transaction'), transaction.id])
                redirect transaction
            }
            '*' { respond transaction, [status: CREATED] }
        }
    }

    @Transactional
    def delete(Transaction transaction) {

        if (transaction == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        transaction.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'transaction.label', default: 'Transaction'), transaction.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'transaction.label', default: 'Transaction'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
