<% request.setAttribute("title", "Download"); %>
<%@ include file="/header.jsp" %>

<div id="reg-wrapper">

	<form name="dwnldform" class="login-form register-form" action="Download" method="post">
		
		<div class="header">
			<h1>Device synchronization</h1>
			<span>Now synchronize your device. Go to the section \"DEVICE SYNCHRONIZATION\" in application. (10 minutes duration)</span>
		</div>
		
        <script type="text/javascript">
			document.forms[0].submit();
		</script>
		
	 </form>
        
</div>
<div class="gradient"></div>
<%@ include file="/footer.jsp" %>