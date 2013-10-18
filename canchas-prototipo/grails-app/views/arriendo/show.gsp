
<%@ page import="com.widaz.canchas.Arriendo" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'arriendo.label', default: 'Arriendo')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-arriendo" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-arriendo" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list arriendo">
			
				<g:if test="${arriendoInstance?.cancha}">
				<li class="fieldcontain">
					<span id="cancha-label" class="property-label"><g:message code="arriendo.cancha.label" default="Cancha" /></span>
					
						<span class="property-value" aria-labelledby="cancha-label"><g:link controller="cancha" action="show" id="${arriendoInstance?.cancha?.id}">${arriendoInstance?.cancha?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${arriendoInstance?.horaDesde}">
				<li class="fieldcontain">
					<span id="horaDesde-label" class="property-label"><g:message code="arriendo.horaDesde.label" default="Hora Desde" /></span>
					
						<span class="property-value" aria-labelledby="horaDesde-label"><g:formatDate date="${arriendoInstance?.horaDesde}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${arriendoInstance?.horaHasta}">
				<li class="fieldcontain">
					<span id="horaHasta-label" class="property-label"><g:message code="arriendo.horaHasta.label" default="Hora Hasta" /></span>
					
						<span class="property-value" aria-labelledby="horaHasta-label"><g:formatDate date="${arriendoInstance?.horaHasta}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${arriendoInstance?.nombreCliente}">
				<li class="fieldcontain">
					<span id="nombreCliente-label" class="property-label"><g:message code="arriendo.nombreCliente.label" default="Nombre Cliente" /></span>
					
						<span class="property-value" aria-labelledby="nombreCliente-label"><g:fieldValue bean="${arriendoInstance}" field="nombreCliente"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${arriendoInstance?.id}" />
					<g:link class="edit" action="edit" id="${arriendoInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
