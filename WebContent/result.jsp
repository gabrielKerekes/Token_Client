<% request.setAttribute("title", "Verify"); %>
<%@ include file="/header.jsp" %>

<div id="reg-wrapper">

	<form name="resultform" class="login-form register-form" action="init" method="post">
	
		<div class="header">
			<h1><%= request.getAttribute("message")%></h1>
		</div>
		
		<div class="footer">
             <input type="submit" name="button" value="Login" class="button" />
        </div>
		
	</form>
        
</div>
<div class="gradient"></div>

<%@ include file="/footer.jsp" %>
