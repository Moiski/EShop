<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<div id="userMenu">
	<c:if test="${(sessionScope.userType eq 'GUEST') or (sessionScope.userType eq 'CLIENT')}">
		<div class="panel panel-info">
			<div class="panel-heading">
				<c:if test="${sessionScope.userType eq 'GUEST'}">
					<i><b><s:message code="message.welcome"/> GUEST!</b></i>
				</c:if>
				<c:if test="${sessionScope.userType eq 'CLIENT'}">
					<i><b><s:message code="message.welcome"/> ${sessionScope.userLogin}!</b></i>
				</c:if>
	  		</div>
	  		<div class="panel-body">
	  			<c:if test="${sessionScope.userType eq 'GUEST'}">
					<c:import url="/WEB-INF/jsp/fragment/login.jsp" />
				</c:if>
		  		<c:if test="${sessionScope.userType eq 'CLIENT'}">
					<a href="${pageContext.request.contextPath}/orders"><s:message code="message.user.navbar.order"/></a><br/>
					<a href="${pageContext.request.contextPath}/cart"><s:message code="message.user.navbar.cart"/></a><br/>
				</c:if>
				<c:if test="${sessionScope.userType eq 'CLIENT'}">
					<form action="${pageContext.request.contextPath}/logout" method="GET"><br>
						<button class="btn btn-info" type="submit"><s:message code="message.logout"/></button>
					</form>
				</c:if>
	  		</div>
		</div>
	</c:if>
</div>
