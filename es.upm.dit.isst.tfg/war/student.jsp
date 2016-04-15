<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ page
	import="com.google.appengine.api.blobstore.BlobstoreServiceFactory"%>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService"%>
<%
	BlobstoreService blobstoreService = BlobstoreServiceFactory
			.getBlobstoreService();
%>

<p>Perfil: alumno.</p>
<c:choose>
	<c:when test="${tfg!=null}">
		<p>Estado de su solicitud: ${tfg.status}</p>
		<table>
			<tr>
				<th>Autor</th>
				<th>Título</th>
				<th>Resumen</th>
				<th>Tutor</th>
				<th>Secretario</th>
				<th>Estado</th>
				<th>Memoria</th>
			</tr>
			<tr>
				<td><c:out value="${tfg.author}" /></td>
				<td><c:out value="${tfg.title}" /></td>
				<td><c:out value="${tfg.summary}" /></td>
				<td><c:out value="${tfg.tutor}" /></td>
				<td><c:out value="${tfg.secretary}" /></td>
				<td><c:out value="${tfg.status}" /></td>
				<td><c:choose>
						<c:when test="${tfg.status lt 2}">Sin memoria</c:when>
						<c:when test="${tfg.status==2}">
							<form action="<%=blobstoreService.createUploadUrl("/file")%>"
								method="post" enctype="multipart/form-data">
								<input id="author" name="author" type="hidden"
									value="${tfg.author}" /> <input type="file" name="file" /> <input
									type="submit" value="Subir memoria" />
							</form>
						</c:when>
						<c:when test="${tfg.status ge 3}">
							<form action="/file" method="get">
								<input id="author" name="author" type="hidden"
									value="${tfg.author}" /> <input type="submit"
									value="Mostrar memoria" />
							</form>
						</c:when>
					</c:choose></td>
			</tr>
		</table>
	</c:when>
	<c:otherwise>
		<p>Nueva solicitud de TFG</p>
		<form action="/newTFG" method="post" acceptcharset="utf-8">
			<input type="text" name="title" id="title" maxLength="255" size="20"
				required placeholder="Titulo" /> <input type="text" name="summary"
				id="summary" maxLength="255" size="20" required
				placeholder="resumen" /> <input type="text" name="tutor" id="tutor"
				maxLength="255" size="20" required placeholder="tutor" /> <input
				type="submit" value="Solicitar" />
		</form>
	</c:otherwise>
</c:choose>