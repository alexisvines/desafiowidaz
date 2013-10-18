package com.widaz.canchas



import org.junit.*
import grails.test.mixin.*

@TestFor(ArriendoController)
@Mock(Arriendo)
class ArriendoControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/arriendo/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.arriendoInstanceList.size() == 0
        assert model.arriendoInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.arriendoInstance != null
    }

    void testSave() {
        controller.save()

        assert model.arriendoInstance != null
        assert view == '/arriendo/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/arriendo/show/1'
        assert controller.flash.message != null
        assert Arriendo.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/arriendo/list'

        populateValidParams(params)
        def arriendo = new Arriendo(params)

        assert arriendo.save() != null

        params.id = arriendo.id

        def model = controller.show()

        assert model.arriendoInstance == arriendo
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/arriendo/list'

        populateValidParams(params)
        def arriendo = new Arriendo(params)

        assert arriendo.save() != null

        params.id = arriendo.id

        def model = controller.edit()

        assert model.arriendoInstance == arriendo
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/arriendo/list'

        response.reset()

        populateValidParams(params)
        def arriendo = new Arriendo(params)

        assert arriendo.save() != null

        // test invalid parameters in update
        params.id = arriendo.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/arriendo/edit"
        assert model.arriendoInstance != null

        arriendo.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/arriendo/show/$arriendo.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        arriendo.clearErrors()

        populateValidParams(params)
        params.id = arriendo.id
        params.version = -1
        controller.update()

        assert view == "/arriendo/edit"
        assert model.arriendoInstance != null
        assert model.arriendoInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/arriendo/list'

        response.reset()

        populateValidParams(params)
        def arriendo = new Arriendo(params)

        assert arriendo.save() != null
        assert Arriendo.count() == 1

        params.id = arriendo.id

        controller.delete()

        assert Arriendo.count() == 0
        assert Arriendo.get(arriendo.id) == null
        assert response.redirectedUrl == '/arriendo/list'
    }
}
