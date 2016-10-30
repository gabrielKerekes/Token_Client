<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% request.setAttribute("title", "Code"); %>
<%@ include file="/header.jsp" %>

<div id="wrapper">

	<form name="loginform" class="login-form" action="Confirm" method="post">
	
		<div class="header">
		<h1> <% request.getAttribute("username"); %></h1>
		<span>Insert code from mail.</span>
		</div>
	
		<div class="content">
			<input name="code" type="text" class="input username" placeholder="Code" />
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
