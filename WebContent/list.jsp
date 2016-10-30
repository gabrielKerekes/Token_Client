<% request.setAttribute("title", "List"); 
	response.setHeader("Cache-Control","no-cache");
	response.setHeader("Cache-Control","no-store");
	response.setHeader("Pragma","no-cache");
	response.setDateHeader ("Expires", 0);
                   %>
<%@ include file="/header.jsp" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="reg-wrapper">

	<form name="logoutform" class="login-form register-form" action="List" method="post">
	
		<div class="header">
			<h1><%= request.getAttribute("message")%></h1>
		</div>
		
		<div class="content">
			<style type="text/css">
				.tg  {border-collapse:collapse;border-spacing:0;}
				.tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;color: gray;}
				.tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:2px;overflow:hidden;word-break:normal;font-weight: bold;color: gray;}
			</style>
			<table class="tg">
				<tr>
					<th class="tg-031e">Text</th>
					<th class="tg-031e">DateTime</th>
					<th class="tg-031e">From</th>
					<th class="tg-031e">State</th>
				</tr>	
				<c:forEach var="item" items="${list}" varStatus="status">
					<tr>
						<td class="tg-031e">${item.transText}</td>
						<td class="tg-031e">${item.datetime}</td>
						<td class="tg-031e">${item.from}</td>
						<td class="tg-031e">${item.state}</td>
					</tr>		
				</c:forEach>
			</table>		
		</div>

		<div class="footer">
			<div class="content">
			<input name="push_message" type="text" placeholder="Write your message" />
			<div class="user-icon"></div>
			</div>
				<p><input type="submit" name="button" value=<%= request.getAttribute("button_text")%>  class="button"></p>
				<p><input type=<%= request.getAttribute("button_type")%> name="button" value="Message" class="button"></p>   
				<p><input type="submit" name="button" value="Account Settings" class="button"></p>             
		</div>
		
	</form>
        
</div>
<div class="gradient"></div>

<%@ include file="/footer.jsp" %>
