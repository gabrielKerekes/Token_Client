<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% request.setAttribute("title", "Registration"); %>
<%@ include file="/header.jsp" %>

<div id="reg-wrapper">

    <form name="registerform" class="login-form register-form" action="Registration" method="post">

		<div class="header">
			<h1>Register Form</h1>
			<span>Fill out your personal information.</span>
		</div>

		<div class="content">
                <div class="row">
                	<label for="username">Username:</label>
                	<input type="text" id="username" name="username" class="input" 
                		<c:if test="${not empty username}">value='<c:out value="${username}"/>'</c:if> 
                	/>
                </div>
                <div class="row">
                	<label for="password">Password:</label>
                	<input name="password" id="password" type="password" class="input" 
                		<c:if test="${not empty password}">value='<c:out value="${password}"/>'</c:if> 
                	/>
                </div>
                <div class="row">
                	<label for="repeat_password">Repeat Password:</label>
                	<input name="repeat_password" id="repeat_password" type="password" class="input" 
                		<c:if test="${not empty repeat_password}">value='<c:out value="${repeat_password}"/>'</c:if> 
                	/>
                </div>
                <div class="row">
                	<label for="mail">Mail:</label>
                	<input type="text" name="mail" id="mail" class="input" 
                		<c:if test="${not empty f_name}">value='<c:out value="${f_name}"/>'</c:if> 
                	/>
                </div>
                <div class="row">
                	<label for="repeat_mail">Repeat Mail:</label>
                	<input type="text" name="repeat_mail" id="repeat_mail" class="input" 
                		<c:if test="${not empty l_name}">value='<c:out value="${l_name}"/>'</c:if> 
                	/>
                </div>
                <div class="row">
                	<label for="pin">PIN:</label>
                	<input type="number" name="pin" id="pin" class="input" 
                		<c:if test="${not empty pin}">value='<c:out value="${pin}"/>'</c:if> 
                	/>
                </div>
		</div>
		
		<div class="footer">
            <input type="submit" name="button" value="OK" class="button" />
            <input type="submit" name="button" value="Back" class="alternative" />
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
