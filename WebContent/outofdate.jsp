<% request.setAttribute("title", "Token Application"); %>
<%@ include file="/header.jsp" %>

<div id="reg-wrapper">

	<form name="initform" class="login-form register-form" action="OutOfDate" method="post">
        
        <div class="header">
			<h1>Token out of Date</h1>
			<span>Your registration is older than 6 months. Please register your token. Reinstall your mobile application.</span>
		</div>
		
		<div class="footer">
             <input type="submit" name="button" value="Next" class="button" />
        </div>
        
	</form>
      
</div>
<div class="gradient"></div>

<%@ include file="/footer.jsp" %>