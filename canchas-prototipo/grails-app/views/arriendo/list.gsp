
<%@ page import="com.widaz.canchas.Arriendo" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'arriendo.label', default: 'Arriendo')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-arriendo" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-arriendo" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="arriendo.cancha.label" default="Cancha" /></th>
					
						<g:sortableColumn property="horaDesde" title="${message(code: 'arriendo.horaDesde.label', default: 'Hora Desde')}" />
					
						<g:sortableColumn property="horaHasta" title="${message(code: 'arriendo.horaHasta.label', default: 'Hora Hasta')}" />
					
						<g:sortableColumn property="nombreCliente" title="${message(code: 'arriendo.nombreCliente.label', default: 'Nombre Cliente')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${arriendoInstanceList}" status="i" var="arriendoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${arriendoInstance.id}">${fieldValue(bean: arriendoInstance, field: "cancha")}</g:link></td>
					
						<td><g:formatDate date="${arriendoInstance.horaDesde}" /></td>
					
						<td><g:formatDate date="${arriendoInstance.horaHasta}" /></td>
					
						<td>${fieldValue(bean: arriendoInstance, field: "nombreCliente")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${arriendoInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
