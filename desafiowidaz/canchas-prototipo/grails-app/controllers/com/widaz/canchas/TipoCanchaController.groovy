package com.widaz.canchas

import org.springframework.dao.DataIntegrityViolationException

class TipoCanchaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [tipoCanchaInstanceList: TipoCancha.list(params), tipoCanchaInstanceTotal: TipoCancha.count()]
    }

    def create() {
        [tipoCanchaInstance: new TipoCancha(params)]
    }

    def save() {
        def tipoCanchaInstance = new TipoCancha(params)
        if (!tipoCanchaInstance.save(flush: true)) {
            render(view: "create", model: [tipoCanchaInstance: tipoCanchaInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'tipoCancha.label', default: 'TipoCancha'), tipoCanchaInstance.id])
        redirect(action: "show", id: tipoCanchaInstance.id)
    }

    def show(Long id) {
        def tipoCanchaInstance = TipoCancha.get(id)
        if (!tipoCanchaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoCancha.label', default: 'TipoCancha'), id])
            redirect(action: "list")
            return
        }

        [tipoCanchaInstance: tipoCanchaInstance]
    }

    def edit(Long id) {
        def tipoCanchaInstance = TipoCancha.get(id)
        if (!tipoCanchaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoCancha.label', default: 'TipoCancha'), id])
            redirect(action: "list")
            return
        }

        [tipoCanchaInstance: tipoCanchaInstance]
    }

    def update(Long id, Long version) {
        def tipoCanchaInstance = TipoCancha.get(id)
        if (!tipoCanchaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoCancha.label', default: 'TipoCancha'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (tipoCanchaInstance.version > version) {
                tipoCanchaInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'tipoCancha.label', default: 'TipoCancha')] as Object[],
                          "Another user has updated this TipoCancha while you were editing")
                render(view: "edit", model: [tipoCanchaInstance: tipoCanchaInstance])
                return
            }
        }

        tipoCanchaInstance.properties = params

        if (!tipoCanchaInstance.save(flush: true)) {
            render(view: "edit", model: [tipoCanchaInstance: tipoCanchaInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'tipoCancha.label', default: 'TipoCancha'), tipoCanchaInstance.id])
        redirect(action: "show", id: tipoCanchaInstance.id)
    }

    def delete(Long id) {
        def tipoCanchaInstance = TipoCancha.get(id)
        if (!tipoCanchaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoCancha.label', default: 'TipoCancha'), id])
            redirect(action: "list")
            return
        }

        try {
            tipoCanchaInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'tipoCancha.label', default: 'TipoCancha'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'tipoCancha.label', default: 'TipoCancha'), id])
            redirect(action: "show", id: id)
        }
    }
}
