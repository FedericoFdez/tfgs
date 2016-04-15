<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<h1>Sistema de gestión de TFGs</h1>
<c:if test="${username!=null}">
	<p>Hola, ${username} - <a href="<c:url value="${logoutURL}" />">SALIR</a>
	</p>
</c:if>