<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% request.setAttribute("title", "Recovery"); %>
<%@ include file="/header.jsp" %>

<div id="wrapper">

	<form name="loginform" class="login-form" action="Recovery" method="post">
	
		<div class="header">
		<h1>Recovery</h1>
		<span>Fill out the username.</span>
		</div>
	
		<div class="content">
			<input name="username" type="text" class="input username" placeholder="Username" />
			<div class="user-icon"></div>
		</div>

		<div class="footer">
		<input type="submit" name="button" value="Next" class="button" />
		<input type="submit" name="button" value="Back" class="button" />
		<p class="warning-message">
        			<c:forEach var="singe_message" items="${messages}">
				        <c:out value="${singe_message}"/><br/>
				   </c:forEach>
        		</p>
		</div>
	
	</form>

</div>
<div class="gradient"></div>

<%@ include file="/footer.jsp" %>
