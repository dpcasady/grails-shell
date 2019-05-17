package io.champion

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class StoreController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Store.list(params), model:[storeCount: Store.count()]
    }

    def show(Store store) {
        respond store
    }

    def create() {
        respond new Store(params)
    }

    @Transactional
    def save(Store store) {
        if (store == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (store.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond store.errors, view:'create'
            return
        }

        store.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'store.label', default: 'Store'), store.id])
                redirect store
            }
            '*' { respond store, [status: CREATED] }
        }
    }

    def edit(Store store) {
        respond store
    }

    @Transactional
    def update(Store store) {
        if (store == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (store.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond store.errors, view:'edit'
            return
        }

        store.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'store.label', default: 'Store'), store.id])
                redirect store
            }
            '*'{ respond store, [status: OK] }
        }
    }

    @Transactional
    def delete(Store store) {

        if (store == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        store.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'store.label', default: 'Store'), store.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'store.label', default: 'Store'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }


    def items(Store store){
        [ store : store ]
    }
}
