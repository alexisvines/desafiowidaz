
<%@ page import="com.widaz.canchas.Cancha" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'cancha.label', default: 'Cancha')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-cancha" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-cancha" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="cancha.complejoDeportivo.label" default="Complejo Deportivo" /></th>
					
						<g:sortableColumn property="numeroCancha" title="${message(code: 'cancha.numeroCancha.label', default: 'Numero Cancha')}" />
					
						<th><g:message code="cancha.tipoCancha.label" default="Tipo Cancha" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${canchaInstanceList}" status="i" var="canchaInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${canchaInstance.id}">${fieldValue(bean: canchaInstance, field: "complejoDeportivo")}</g:link></td>
					
						<td>${fieldValue(bean: canchaInstance, field: "numeroCancha")}</td>
					
						<td>${fieldValue(bean: canchaInstance, field: "tipoCancha")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${canchaInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
