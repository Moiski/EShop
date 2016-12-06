<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div>
	<c:import url="/jsp/fragment/clientfilter.jsp"></c:import>
</div>

<c:forEach var="product" items="${productslist}" varStatus="status">
 		<div class="row">
  			<div class="col-xs-6"><img  src="./images/${product.image}"></div>
  			<div class="col-xs-6">
  				<c:out value="${product.name}"/><br>
  				<c:out value="${product.description}"/><br>
  				<div class="panel-info class"><h1><span class="label label-default">Cost ${product.price}</span></h1></div>
  			</div>
		</div>
		<div class="row">
  			<div class="col-xs-6">
  				<c:if test="${sessionScope.userType eq 'CLIENT'}">
					<form id="addProductButton" action="controller" method="POST" >
						<input type="hidden" name="command" value="addtocart" />
						<input type="hidden" name="productid" value="${product.productID}" />
						<input type="hidden" name="productname" value="${product.name}" />
						<input type="hidden" name="productprice" value="${product.price}" />
						<input type="hidden" name="categoryid" value="${product.category.categoryID}" />
						<button class="btn btn-default" type="submit">Add to cart</button>
					</form>
				</c:if>
  			</div>
		</div>
		<br>
</c:forEach>
<c:if test="${productslist ne null}">
	<c:import url="/jsp/fragment/pagination.jsp"></c:import>
</c:if>



