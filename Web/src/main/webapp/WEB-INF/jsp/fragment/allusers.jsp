<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<jsp:useBean id="user" class="by.moiski.dao.entities.User" scope="request"></jsp:useBean>

<div class="table-responsive">
	<table class ="table table-bordered table-striped ">
	<caption align="top"><b><s:message code="message.admin.user.all"/></b></caption>
		<tr class="info" align="left">
			<th><s:message code="message.user.reg.date"/></th>
			<th><s:message code="message.user.login"/></th>
			<th><s:message code="message.user.email"/></th>
			<th><s:message code="message.user.name"/></th>
			<th><s:message code="message.user.surname"/></th>
			<th><s:message code="message.user.address"/></th>
			<th><s:message code="message.user.type"/></th>
			<th><s:message code="message.user.black.list"/></th>
			<th><s:message code="message.admin.user.all.action"/></th>
		</tr>
	<c:forEach var="user" items="${usersList}" varStatus="status">
		<tr>
			<td><c:out value="${user.registrDate}"/></td>
			<td><c:out value="${user.login}"/></td>
			<td><c:out value="${user.email}"/></td>
			<td><c:out value="${user.firstname}"/></td>
			<td><c:out value="${user.lastname}"/></td>
			<td><c:out value="${user.shippingAddress}"/></td>
			<td><c:out value="${user.role}"/></td>
			<td><c:out value="${user.blackList}"/></td>
			<td>
				<sf:form id="addAndRemoveInBlackList" action="${pageContext.request.contextPath}/users/add/remove/blacklist"
						method="GET" modelAttribute="user">
				 	<sf:input type="hidden" path="userId" value="${user.userId}"/>
					<button class="btn btn-default btn-block btn-info" type="submit"><s:message code="message.admin.user.all.black.list"/></button>
				</sf:form>
			</td>
		</tr>
	</c:forEach>
	</table>
</div>