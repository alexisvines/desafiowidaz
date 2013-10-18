package com.widaz.canchas

import org.springframework.dao.DataIntegrityViolationException

class CanchaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [canchaInstanceList: Cancha.list(params), canchaInstanceTotal: Cancha.count()]
    }

    def create() {
        [canchaInstance: new Cancha(params)]
    }

    def save() {
        def canchaInstance = new Cancha(params)
        if (!canchaInstance.save(flush: true)) {
            render(view: "create", model: [canchaInstance: canchaInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'cancha.label', default: 'Cancha'), canchaInstance.id])
        redirect(action: "show", id: canchaInstance.id)
    }

    def show(Long id) {
        def canchaInstance = Cancha.get(id)
        if (!canchaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cancha.label', default: 'Cancha'), id])
            redirect(action: "list")
            return
        }

        [canchaInstance: canchaInstance]
    }

    def edit(Long id) {
        def canchaInstance = Cancha.get(id)
        if (!canchaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cancha.label', default: 'Cancha'), id])
            redirect(action: "list")
            return
        }

        [canchaInstance: canchaInstance]
    }

    def update(Long id, Long version) {
        def canchaInstance = Cancha.get(id)
        if (!canchaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cancha.label', default: 'Cancha'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (canchaInstance.version > version) {
                canchaInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'cancha.label', default: 'Cancha')] as Object[],
                          "Another user has updated this Cancha while you were editing")
                render(view: "edit", model: [canchaInstance: canchaInstance])
                return
            }
        }

        canchaInstance.properties = params

        if (!canchaInstance.save(flush: true)) {
            render(view: "edit", model: [canchaInstance: canchaInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'cancha.label', default: 'Cancha'), canchaInstance.id])
        redirect(action: "show", id: canchaInstance.id)
    }

    def delete(Long id) {
        def canchaInstance = Cancha.get(id)
        if (!canchaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cancha.label', default: 'Cancha'), id])
            redirect(action: "list")
            return
        }

        try {
            canchaInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'cancha.label', default: 'Cancha'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'cancha.label', default: 'Cancha'), id])
            redirect(action: "show", id: id)
        }
    }
}
