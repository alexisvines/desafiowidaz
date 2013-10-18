<%@ page import="com.widaz.canchas.TipoCancha" %>



<div class="fieldcontain ${hasErrors(bean: tipoCanchaInstance, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="tipoCancha.nombre.label" default="Nombre" />
		
	</label>
	<g:textField name="nombre" value="${tipoCanchaInstance?.nombre}"/>
</div>

