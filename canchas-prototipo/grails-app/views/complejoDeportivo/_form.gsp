<%@ page import="com.widaz.canchas.ComplejoDeportivo" %>



<div class="fieldcontain ${hasErrors(bean: complejoDeportivoInstance, field: 'direccion', 'error')} ">
	<label for="direccion">
		<g:message code="complejoDeportivo.direccion.label" default="Direccion" />
		
	</label>
	<g:textField name="direccion" value="${complejoDeportivoInstance?.direccion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: complejoDeportivoInstance, field: 'duenio', 'error')} ">
	<label for="duenio">
		<g:message code="complejoDeportivo.duenio.label" default="Duenio" />
		
	</label>
	<g:textField name="duenio" value="${complejoDeportivoInstance?.duenio}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: complejoDeportivoInstance, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="complejoDeportivo.nombre.label" default="Nombre" />
		
	</label>
	<g:textField name="nombre" value="${complejoDeportivoInstance?.nombre}"/>
</div>

