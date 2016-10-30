<% request.setAttribute("title", "Logout"); %>
<% request.setAttribute("title", "Failed"); %>
<%@ include file="/header.jsp" %>

<div id="reg-wrapper">

	<form name="logoutform" class="login-form register-form" action="init" method="post">
	
		<div class="header">
			<h1><%= request.getAttribute("message")%></h1>
		</div>

		<div class="footer">
			<input type="submit" name="button" value="Back" class="button">           
		</div>
		
	</form>
        
</div>
<div class="gradient"></div>

<%@ include file="/footer.jsp" %>
