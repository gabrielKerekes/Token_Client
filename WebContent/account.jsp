<% request.setAttribute("title", "Settings"); 
	response.setHeader("Cache-Control","no-cache");
	response.setHeader("Cache-Control","no-store");
	response.setHeader("Pragma","no-cache");
	response.setDateHeader ("Expires", 0);
                   %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% request.setAttribute("title", "Account settings"); %>
<%@ include file="/header.jsp" %>

<div id="reg-wrapper">

    <form name="registerform" class="login-form register-form" action="Account" method="post">

		<div class="header">
			<h1>Account Settings</h1>
			<span>Change your account data</span>
		</div>

		<div class="content">
				<div class="row">
                	<label for="password">Password:</label>
                	<input name="password" id="password" type="password" class="input" 
                		<c:if test="${not empty password}">value='<c:out value="${password}"/>'</c:if> 
                	/>
                </div>
                <div class="row">
                	<label for="new_password">New Password:</label>
                	<input name="new_password" id="new_password" type="password" class="input" 
                		<c:if test="${not empty new_password}">value='<c:out value="${password}"/>'</c:if> 
                	/>
                </div>
                <div class="row">
                	<label for="repeat_password">Repeat New Password:</label>
                	<input name="repeat_password" id="repeat_password" type="password" class="input" 
                		<c:if test="${not empty password}">value='<c:out value="${password}"/>'</c:if> 
                	/>
                </div>
                <div class="row">
                	<p><input type="submit" name="button" value="Change password" class="alternative"></p> 
                </div>
                
                 <div class="row">
                	<label for="mail">Mail:</label>
                	<input type="text" name="mail" id="mail" class="input" 
                		<c:if test="${not empty mail}">value='<c:out value="${f_name}"/>'</c:if> 
                	/>
                </div>
                <div class="row">
                	<label for="repeat_mail">Repeat Mail:</label>
                	<input type="text" name="repeat_mail" id="repeat_mail" class="input" 
                		<c:if test="${not empty repeat_mail}">value='<c:out value="${l_name}"/>'</c:if> 
                	/>
                </div>
                <div class="row">
                	<p><input type="submit" name="button" value="Change mail" class="alternative"></p> 
                </div>
                
                <div class="row">
                	<label for="pin">PIN</label>
                	<input type="text" name="pin" id="pin" class="input" 
                		<c:if test="${not empty pin}">value='<c:out value="${l_name}"/>'</c:if> 
                	/>
                </div>
                <div class="row">
                	<p><input type="submit" name="button" value="Change PIN" class="alternative"></p> 
                </div>
		</div>
		
		<div class="footer">
            <input type="submit" name="button" value="Back" class="button" />
            <p class="warning-message">
        			<c:forEach var="singe_message" items="${messages_a}">
				        <c:out value="${singe_message}"/><br/>
				   </c:forEach>
        	</p>
		</div>
        		
   </form>
   
   </div>
<div class="gradient"></div>

<%@ include file="/footer.jsp" %>
