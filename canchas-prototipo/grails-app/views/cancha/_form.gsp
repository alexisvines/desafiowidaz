<%@ page import="com.widaz.canchas.Cancha" %>



<div class="fieldcontain ${hasErrors(bean: canchaInstance, field: 'complejoDeportivo', 'error')} required">
	<label for="complejoDeportivo">
		<g:message code="cancha.complejoDeportivo.label" default="Complejo Deportivo" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="complejoDeportivo" name="complejoDeportivo.id" from="${com.widaz.canchas.ComplejoDeportivo.list()}" optionKey="id" required="" value="${canchaInstance?.complejoDeportivo?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: canchaInstance, field: 'numeroCancha', 'error')} required">
	<label for="numeroCancha">
		<g:message code="cancha.numeroCancha.label" default="Numero Cancha" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="numeroCancha" type="number" value="${canchaInstance.numeroCancha}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: canchaInstance, field: 'tipoCancha', 'error')} required">
	<label for="tipoCancha">
		<g:message code="cancha.tipoCancha.label" default="Tipo Cancha" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="tipoCancha" name="tipoCancha.id" from="${com.widaz.canchas.TipoCancha.list()}" optionKey="id" required="" value="${canchaInstance?.tipoCancha?.id}" class="many-to-one"/>
</div>

