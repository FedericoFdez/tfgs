<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<p>Perfil: profesor.</p>
<c:if test="${fn:length(tfgsAsTutor)>0}">
	<p>Está tutorizando los siguientes TFGs.</p>
	<table>
		<tr>
			<th>Estado</th>
			<th>Autor</th>
			<th>Título</th>
			<th>Resumen</th>
			<th>Secretario</th>
			<th>Acciones</th>
		</tr>
		<c:forEach items="${tfgsAsTutor}" var="tfg">
			<tr>
				<td><c:out value="${tfg.status}" /><c:if test="${tfg.rejected == true}"> (R)</c:if></td>
				<td><c:out value="${tfg.author}" /></td>
				<td><c:out value="${tfg.title}" /></td>
				<td><c:out value="${tfg.summary}" /></td>

				<c:choose>
					<c:when test="${tfg.status == 1}">
						<c:if test="${tfg.rejected == false }">
							<form action="/accept?role=tutor" method="post" acceptcharset="utf-8">
								<td><input type="text" name="secretary" id="secretary"
									maxLength="255" size="20" required placeholder="secretary" /></td>
								<td><input type="hidden" name="author" value="${tfg.author}" />
									<input type="submit" value="Aceptar como tutor" />
							</form>
							<form action="/reject?role=tutor" method="post" acceptcharset="utf-8">
									<input type="hidden" name="author" value="${tfg.author}" />
									<input type="submit" value="Rechazar como tutor" />
							</form>
								</td>
							</form>
						</c:if>
					</c:when>
					<c:when test="${tfg.status == 2}">
						<td><c:out value="${tfg.secretary }" /></td>
						<td></td>
					</c:when>
					<c:when test="${tfg.status == 3 }">
						<td><c:out value="${tfg.secretary }" /></td>
						<td>
						<form action="/file" method="get">
							<input id="author" name="author" type="hidden"
								value="${tfg.author}" /> <input type="submit"
								value="Mostrar memoria" />
						</form>
						<c:if test="${tfg.rejected == false}">
						<form action="/accept?role=tutor" method="post">
							<input id="author" name="author" type="hidden"
								value="${tfg.author}" /> <input type="submit"
								value="Aceptar memoria" />
						</form>
						<form action="/reject?role=tutor" method="post">
							<input id="author" name="author" type="hidden"
								value="${tfg.author}" /> <input type="submit"
								value="Rechazar memoria" />
						</form>
						</c:if>
						</td>
					</c:when>
					<c:when test="${tfg.status ge 4}">
						<td><c:out value="${tfg.secretary }" /></td>
						<td>
						<form action="/file" method="get">
							<input id="author" name="author" type="hidden"
								value="${tfg.author}" /> <input type="submit"
								value="Mostrar memoria" />
						</form>
						</td>
					</c:when>
				</c:choose>
			</tr>
		</c:forEach>
	</table>
</c:if>
<c:if test="${fn:length(tfgsAsSecretary) > 0}">
	<p>Es el secretario de los siguientes TFGs.</p>
	<table>
		<tr>
			<th>Estado</th>
			<th>Autor</th>
			<th>Título</th>
			<th>Resumen</th>
			<th>Tutor</th>
			<th>Acciones</th>
		</tr>
		<c:forEach items="${tfgsAsSecretary}" var="tfg">
			<tr>
				<td><c:out value="${tfg.status}" /></td>
				<td><c:out value="${tfg.author}" /></td>
				<td><c:out value="${tfg.title}" /></td>
				<td><c:out value="${tfg.summary}" /></td>
				<td><c:out value="${tfg.tutor}" /></td>
				<td><c:choose>
						<c:when test="${tfg.status == 4}">
							<form action="/file" method="get">
								<input id="author" name="author" type="hidden"
									value="${tfg.author}" /> <input type="submit"
									value="Mostrar memoria" />
							</form>
							<c:if test="${tfg.rejected == false}">
							<form action="/accept?role=secretary" method="post" acceptcharset="utf-8">
								<input id="author" name="author" type="hidden"
									value="${tfg.author}" /> <input type="submit"
									value="Aprobar" />
							</form>
							<form action="/reject?role=secretary" method="post" acceptcharset="utf-8">
								<input id="author" name="author" type="hidden"
									value="${tfg.author}" /> <input type="submit"
									value="Suspender" />
							</form>
							</c:if>
						</c:when>
						<c:when test="${tfg.status == 4}">
							<form action="/file" method="get">
								<input id="author" name="author" type="hidden"
									value="${tfg.author}" /> <input type="submit"
									value="Mostrar memoria" />
							</form>
						</c:when>
					</c:choose></td>
			</tr>
		</c:forEach>
	</table>
</c:if>