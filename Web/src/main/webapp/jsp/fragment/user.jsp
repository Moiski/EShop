<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="userMenu">
<c:if test="${(sessionScope.userType eq 'GUEST') or (sessionScope.userType eq 'CLIENT')}">
	<div class="panel panel-info">
		<div class="panel-heading">
			<c:if test="${sessionScope.userType eq 'GUEST'}">
				<i><b>Hello, guest!</b></i>
			</c:if>
			<c:if test="${sessionScope.userType eq 'CLIENT'}">
				<b>Hello, ${sessionScope.userLogin}!</b>
			</c:if>
	  	</div>
	  	<div class="panel-body">
	  		<c:if test="${sessionScope.userType eq 'GUEST'}">
				<c:import url="/jsp/fragment/login.jsp" />
			</c:if>
		  	<c:if test="${sessionScope.userType eq 'CLIENT'}">
				<a href="controller?command=showuserorders">All orders</a><br/>
				<a href="controller?command=showcart">Cart</a><br/>
			</c:if>
			<c:if test="${sessionScope.userType eq 'CLIENT'}">
				<form action="controller" method="POST">
					<input type="hidden" name="command" value="logout" /><br/>
					<button class="btn btn-primary" type="submit">Log out</button>
				</form>
			</c:if>
	  	</div>
	</div>
</c:if>
</div>
