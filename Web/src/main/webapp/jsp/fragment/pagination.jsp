<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<input id="activeCurrentPage" type="hidden" value="${activeCurrentPage}">

<nav aria-label="Page navigation">
	<ul class="pagination pagination-sm">
		<li class="page-item">
			<a href="controller?command=showproductsclientfilter&currentPage=${pageNumberList[0]}"aria-label="First page">
				<span aria-hidden="true">First page</span>
			</a>
		</li>
		<c:forEach var="i" items="${pageNumberList}" varStatus="status">
			<li class="page-item"><a href="controller?command=showproductsclientfilter&currentPage=${i}">${i}</a></li>
		</c:forEach>
		<li class="page-item">
			<a class="page-link" href="controller?command=showproductsclientfilter&currentPage=${pageNumberList.size()}" aria-label="Last page">
				<span aria-hidden="true">Last Page</span>
			</a>
		</li>
	</ul>
</nav>