
<%@ page import="com.widaz.canchas.Cancha" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'cancha.label', default: 'Cancha')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-cancha" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-cancha" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list cancha">
			
				<g:if test="${canchaInstance?.complejoDeportivo}">
				<li class="fieldcontain">
					<span id="complejoDeportivo-label" class="property-label"><g:message code="cancha.complejoDeportivo.label" default="Complejo Deportivo" /></span>
					
						<span class="property-value" aria-labelledby="complejoDeportivo-label"><g:link controller="complejoDeportivo" action="show" id="${canchaInstance?.complejoDeportivo?.id}">${canchaInstance?.complejoDeportivo?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${canchaInstance?.numeroCancha}">
				<li class="fieldcontain">
					<span id="numeroCancha-label" class="property-label"><g:message code="cancha.numeroCancha.label" default="Numero Cancha" /></span>
					
						<span class="property-value" aria-labelledby="numeroCancha-label"><g:fieldValue bean="${canchaInstance}" field="numeroCancha"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${canchaInstance?.tipoCancha}">
				<li class="fieldcontain">
					<span id="tipoCancha-label" class="property-label"><g:message code="cancha.tipoCancha.label" default="Tipo Cancha" /></span>
					
						<span class="property-value" aria-labelledby="tipoCancha-label"><g:link controller="tipoCancha" action="show" id="${canchaInstance?.tipoCancha?.id}">${canchaInstance?.tipoCancha?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${canchaInstance?.id}" />
					<g:link class="edit" action="edit" id="${canchaInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
