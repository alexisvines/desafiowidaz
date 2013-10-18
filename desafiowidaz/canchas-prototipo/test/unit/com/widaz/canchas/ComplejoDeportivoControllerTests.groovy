package com.widaz.canchas



import org.junit.*
import grails.test.mixin.*

@TestFor(ComplejoDeportivoController)
@Mock(ComplejoDeportivo)
class ComplejoDeportivoControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/complejoDeportivo/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.complejoDeportivoInstanceList.size() == 0
        assert model.complejoDeportivoInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.complejoDeportivoInstance != null
    }

    void testSave() {
        controller.save()

        assert model.complejoDeportivoInstance != null
        assert view == '/complejoDeportivo/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/complejoDeportivo/show/1'
        assert controller.flash.message != null
        assert ComplejoDeportivo.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/complejoDeportivo/list'

        populateValidParams(params)
        def complejoDeportivo = new ComplejoDeportivo(params)

        assert complejoDeportivo.save() != null

        params.id = complejoDeportivo.id

        def model = controller.show()

        assert model.complejoDeportivoInstance == complejoDeportivo
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/complejoDeportivo/list'

        populateValidParams(params)
        def complejoDeportivo = new ComplejoDeportivo(params)

        assert complejoDeportivo.save() != null

        params.id = complejoDeportivo.id

        def model = controller.edit()

        assert model.complejoDeportivoInstance == complejoDeportivo
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/complejoDeportivo/list'

        response.reset()

        populateValidParams(params)
        def complejoDeportivo = new ComplejoDeportivo(params)

        assert complejoDeportivo.save() != null

        // test invalid parameters in update
        params.id = complejoDeportivo.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/complejoDeportivo/edit"
        assert model.complejoDeportivoInstance != null

        complejoDeportivo.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/complejoDeportivo/show/$complejoDeportivo.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        complejoDeportivo.clearErrors()

        populateValidParams(params)
        params.id = complejoDeportivo.id
        params.version = -1
        controller.update()

        assert view == "/complejoDeportivo/edit"
        assert model.complejoDeportivoInstance != null
        assert model.complejoDeportivoInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/complejoDeportivo/list'

        response.reset()

        populateValidParams(params)
        def complejoDeportivo = new ComplejoDeportivo(params)

        assert complejoDeportivo.save() != null
        assert ComplejoDeportivo.count() == 1

        params.id = complejoDeportivo.id

        controller.delete()

        assert ComplejoDeportivo.count() == 0
        assert ComplejoDeportivo.get(complejoDeportivo.id) == null
        assert response.redirectedUrl == '/complejoDeportivo/list'
    }
}
