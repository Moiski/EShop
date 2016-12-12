<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<input id="activeCurrentPage" type="hidden" value="${activeCurrentPage}">

<nav aria-label="Page navigation">
	<ul class="pagination pagination-sm">
		<li class="page-item">
			<a href="${pageContext.request.contextPath}/products/clientfilter?currentPage=${pageNumberList[0]}"aria-label="First page">
				<span aria-hidden="true"><s:message code="message.pagination.first"/></span>
			</a>
		</li>
		<c:forEach var="i" items="${pageNumberList}" varStatus="status">
			<li class="page-item"><a href="${pageContext.request.contextPath}/products/clientfilter?currentPage=${i}">${i}</a></li>
		</c:forEach>
		<li class="page-item">
			<a class="page-link" href="${pageContext.request.contextPath}/products/clientfilter?currentPage=${pageNumberList.size()}" aria-label="Last page">
				<span aria-hidden="true"><s:message code="message.pagination.last"/></span>
			</a>
		</li>
	</ul>
</nav>