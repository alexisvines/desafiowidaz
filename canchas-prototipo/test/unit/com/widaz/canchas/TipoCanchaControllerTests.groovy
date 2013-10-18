package com.widaz.canchas



import org.junit.*
import grails.test.mixin.*

@TestFor(TipoCanchaController)
@Mock(TipoCancha)
class TipoCanchaControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/tipoCancha/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.tipoCanchaInstanceList.size() == 0
        assert model.tipoCanchaInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.tipoCanchaInstance != null
    }

    void testSave() {
        controller.save()

        assert model.tipoCanchaInstance != null
        assert view == '/tipoCancha/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/tipoCancha/show/1'
        assert controller.flash.message != null
        assert TipoCancha.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/tipoCancha/list'

        populateValidParams(params)
        def tipoCancha = new TipoCancha(params)

        assert tipoCancha.save() != null

        params.id = tipoCancha.id

        def model = controller.show()

        assert model.tipoCanchaInstance == tipoCancha
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/tipoCancha/list'

        populateValidParams(params)
        def tipoCancha = new TipoCancha(params)

        assert tipoCancha.save() != null

        params.id = tipoCancha.id

        def model = controller.edit()

        assert model.tipoCanchaInstance == tipoCancha
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/tipoCancha/list'

        response.reset()

        populateValidParams(params)
        def tipoCancha = new TipoCancha(params)

        assert tipoCancha.save() != null

        // test invalid parameters in update
        params.id = tipoCancha.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/tipoCancha/edit"
        assert model.tipoCanchaInstance != null

        tipoCancha.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/tipoCancha/show/$tipoCancha.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        tipoCancha.clearErrors()

        populateValidParams(params)
        params.id = tipoCancha.id
        params.version = -1
        controller.update()

        assert view == "/tipoCancha/edit"
        assert model.tipoCanchaInstance != null
        assert model.tipoCanchaInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/tipoCancha/list'

        response.reset()

        populateValidParams(params)
        def tipoCancha = new TipoCancha(params)

        assert tipoCancha.save() != null
        assert TipoCancha.count() == 1

        params.id = tipoCancha.id

        controller.delete()

        assert TipoCancha.count() == 0
        assert TipoCancha.get(tipoCancha.id) == null
        assert response.redirectedUrl == '/tipoCancha/list'
    }
}
