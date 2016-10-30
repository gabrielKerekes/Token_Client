<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% request.setAttribute("title", "Login"); %>
<%@ include file="/header.jsp" %>

<div id="wrapper">

	<form name="loginform" class="login-form" action="login" method="post">
	
		<div class="header">
		<h1>Login Form</h1>
		<span>Fill out the form below to login.</span>
		</div>
	
		<div class="content">
			<input name="username" type="text" class="input username" placeholder="Username" />
			<div class="user-icon"></div>
			<input name="password" type="password" class="input password" placeholder="Password" />
			<div class="pass-icon"></div>	
			<input name="grid_code" type="password" class="input password" placeholder="GRID  <%= request.getAttribute("which_grid")%> " />
			<div class="grid-icon"></div>	
			<input name="otp_code" type="number" class="input password" placeholder="OTP" />
			<div class="grid-icon"></div>	
		</div>

		<div class="footer">
		<input type="submit" name="button" value="Login" class="button" />
		<input type="submit" name="button" value="Register" class="alternative" />
		<input type="submit" name="button" value="Restore" class="alternative" />
		<input type="submit" name="button" value="Token" class="alternative" />
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
