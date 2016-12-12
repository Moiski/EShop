<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<jsp:useBean id="product" class="by.moiski.dao.entities.Product" scope="request"></jsp:useBean>

<div>
	<c:import url="/WEB-INF/jsp/fragment/clientfilter.jsp"></c:import>
</div>
<c:forEach var="product" items="${productslist}" varStatus="status">
 		<div class="row">
  			<div class="col-xs-6"><img  src="${pageContext.request.contextPath}/resources/images/${product.image}"></div>
  			<div class="col-xs-6">
  				<c:out value="${product.name}"/><br>
  				<c:out value="${product.description}"/><br>
  				<div class="panel-info class"><h1><span class="label label-default"><s:message code="message.product.cost"/> ${product.price}</span></h1></div>
  			</div>
		</div>
		<div class="row">
  			<div class="col-xs-6">
  				<c:if test="${sessionScope.userType eq 'CLIENT'}">
  					<sf:form id="addProductButton" action="${pageContext.request.contextPath}/add/cart" 
  							method="POST" modelAttribute="product">
  						<sf:input type="hidden" path="productID" value="${product.productID}"/>
  						<button class="btn btn-default" type="submit"><s:message code="message.buy"/></button>
  					</sf:form>
				</c:if>
  			</div>
		</div>
		<br>
</c:forEach>
<c:if test="${productslist ne null}">
	<c:import url="/WEB-INF/jsp/fragment/pagination.jsp"></c:import>
</c:if>



