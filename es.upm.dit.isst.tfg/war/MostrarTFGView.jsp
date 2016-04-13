<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sistema de gestión de TFGs</title>
</head>
<body>
	<p>Sistema de gestión de TFGs</p>
	<c:if test="${user}">
		<c:out value="${user}" />
	</c:if>
	<p>Puedes pulsar el siguiente enlace para salir
		<a href="<c:url value="${url}" />"><c:out value="${urlLinkText}" /></a>
	</p>
</body>
</html>