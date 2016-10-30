<% request.setAttribute("title", "Verify");
	response.setHeader("Cache-Control","no-cache");
	response.setHeader("Cache-Control","no-store");
	response.setHeader("Pragma","no-cache");
	response.setDateHeader ("Expires", 0);
                   %>
<%@ include file="/header.jsp" %>

<div id="reg-wrapper">

	<form name="verifyform" class="login-form register-form" action="Verify" method="post">
	
		<div class="header">
			<h1>Confirm transaction...</h1>
			<span>Please confirm/reject transaction to continue (5 minutes duration)</span>
		</div>
		
			<script type="text/javascript">
			document.forms[0].submit();
			</script>
	</form>
        
</div>
<div class="gradient"></div>

<%@ include file="/footer.jsp" %>
