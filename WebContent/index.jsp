<% request.setAttribute("title", "Token Application"); %>
<%@ include file="/header.jsp" %>

<div id="reg-wrapper">

	<form name="initform" class="login-form register-form" action="init" method="post">
        
        <div class="header">
			<h1>GRID card</h1>
			<span>Please login to continue.</span>
		</div>
		
		<div class="footer">
             <input type="submit" name="button" value="Login" class="button" />
        </div>
        
	</form>
      
</div>
<div class="gradient"></div>

<%@ include file="/footer.jsp" %>