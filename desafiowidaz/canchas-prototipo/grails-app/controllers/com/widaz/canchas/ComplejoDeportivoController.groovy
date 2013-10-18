package com.widaz.canchas

import org.springframework.dao.DataIntegrityViolationException

class ComplejoDeportivoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [complejoDeportivoInstanceList: ComplejoDeportivo.list(params), complejoDeportivoInstanceTotal: ComplejoDeportivo.count()]
    }

    def create() {
        [complejoDeportivoInstance: new ComplejoDeportivo(params)]
    }

    def save() {
        def complejoDeportivoInstance = new ComplejoDeportivo(params)
        if (!complejoDeportivoInstance.save(flush: true)) {
            render(view: "create", model: [complejoDeportivoInstance: complejoDeportivoInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'complejoDeportivo.label', default: 'ComplejoDeportivo'), complejoDeportivoInstance.id])
        redirect(action: "show", id: complejoDeportivoInstance.id)
    }

    def show(Long id) {
        def complejoDeportivoInstance = ComplejoDeportivo.get(id)
        if (!complejoDeportivoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'complejoDeportivo.label', default: 'ComplejoDeportivo'), id])
            redirect(action: "list")
            return
        }

        [complejoDeportivoInstance: complejoDeportivoInstance]
    }

    def edit(Long id) {
        def complejoDeportivoInstance = ComplejoDeportivo.get(id)
        if (!complejoDeportivoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'complejoDeportivo.label', default: 'ComplejoDeportivo'), id])
            redirect(action: "list")
            return
        }

        [complejoDeportivoInstance: complejoDeportivoInstance]
    }

    def update(Long id, Long version) {
        def complejoDeportivoInstance = ComplejoDeportivo.get(id)
        if (!complejoDeportivoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'complejoDeportivo.label', default: 'ComplejoDeportivo'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (complejoDeportivoInstance.version > version) {
                complejoDeportivoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'complejoDeportivo.label', default: 'ComplejoDeportivo')] as Object[],
                          "Another user has updated this ComplejoDeportivo while you were editing")
                render(view: "edit", model: [complejoDeportivoInstance: complejoDeportivoInstance])
                return
            }
        }

        complejoDeportivoInstance.properties = params

        if (!complejoDeportivoInstance.save(flush: true)) {
            render(view: "edit", model: [complejoDeportivoInstance: complejoDeportivoInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'complejoDeportivo.label', default: 'ComplejoDeportivo'), complejoDeportivoInstance.id])
        redirect(action: "show", id: complejoDeportivoInstance.id)
    }

    def delete(Long id) {
        def complejoDeportivoInstance = ComplejoDeportivo.get(id)
        if (!complejoDeportivoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'complejoDeportivo.label', default: 'ComplejoDeportivo'), id])
            redirect(action: "list")
            return
        }

        try {
            complejoDeportivoInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'complejoDeportivo.label', default: 'ComplejoDeportivo'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'complejoDeportivo.label', default: 'ComplejoDeportivo'), id])
            redirect(action: "show", id: id)
        }
    }
}
