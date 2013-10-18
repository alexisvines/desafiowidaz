package com.widaz.canchas

import org.springframework.dao.DataIntegrityViolationException

class ArriendoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [arriendoInstanceList: Arriendo.list(params), arriendoInstanceTotal: Arriendo.count()]
    }

    def create() {
        [arriendoInstance: new Arriendo(params)]
    }

    def save() {
        def arriendoInstance = new Arriendo(params)
        if (!arriendoInstance.save(flush: true)) {
            render(view: "create", model: [arriendoInstance: arriendoInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'arriendo.label', default: 'Arriendo'), arriendoInstance.id])
        redirect(action: "show", id: arriendoInstance.id)
    }

    def show(Long id) {
        def arriendoInstance = Arriendo.get(id)
        if (!arriendoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'arriendo.label', default: 'Arriendo'), id])
            redirect(action: "list")
            return
        }

        [arriendoInstance: arriendoInstance]
    }

    def edit(Long id) {
        def arriendoInstance = Arriendo.get(id)
        if (!arriendoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'arriendo.label', default: 'Arriendo'), id])
            redirect(action: "list")
            return
        }

        [arriendoInstance: arriendoInstance]
    }

    def update(Long id, Long version) {
        def arriendoInstance = Arriendo.get(id)
        if (!arriendoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'arriendo.label', default: 'Arriendo'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (arriendoInstance.version > version) {
                arriendoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'arriendo.label', default: 'Arriendo')] as Object[],
                          "Another user has updated this Arriendo while you were editing")
                render(view: "edit", model: [arriendoInstance: arriendoInstance])
                return
            }
        }

        arriendoInstance.properties = params

        if (!arriendoInstance.save(flush: true)) {
            render(view: "edit", model: [arriendoInstance: arriendoInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'arriendo.label', default: 'Arriendo'), arriendoInstance.id])
        redirect(action: "show", id: arriendoInstance.id)
    }

    def delete(Long id) {
        def arriendoInstance = Arriendo.get(id)
        if (!arriendoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'arriendo.label', default: 'Arriendo'), id])
            redirect(action: "list")
            return
        }

        try {
            arriendoInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'arriendo.label', default: 'Arriendo'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'arriendo.label', default: 'Arriendo'), id])
            redirect(action: "show", id: id)
        }
    }
}
