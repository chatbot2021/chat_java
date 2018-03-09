<%@include file="include.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="fbflag" value='${requestScope["FBFlag"]}' />
<c:if test="${fbflag eq 'Y'}">
	<c:redirect url='https://www.facebook.com/messenger_platform/account_linking?account_linking_token=${LinkToken}/>'/>	
</c:if>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Login</title>
			<link rel="stylesheet" href="resources/css/democss.css" type="text/css" />
			<link rel="icon" href="resources/images/favicon.ico"/>
			<style>
			.theDiv { position: absolute; top: 200px; bottom: 0; left: 950px; right: 145px; }
			
			body {    
    background-repeat: no-repeat;
}
			</style>
	</head>
	<!-- Start of LiveChat (www.livechatinc.com) code -->
	<script type="text/javascript">
	window.__lc = window.__lc || {};
	window.__lc.license = 8605094;
	(function() {
	  var lc = document.createElement('script'); lc.type = 'text/javascript'; lc.async = true;
	  lc.src = ('https:' == document.location.protocol ? 'https://' : 'http://') + 'cdn.livechatinc.com/tracking.js';
	  var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(lc, s);
	})();
	</script>
	<!-- End of LiveChat code -->
	<body background="<c:url value="resources/images/NewGenBank-chatbot.jpg" />" >
		
	
			<form:form id="loginForm" method="post" action="login" modelAttribute="loginBean">
			<input type="hidden" id="token" name="token" value="${loginBean.token}">
			<input type="hidden" id="state" name="state" value="${loginBean.state}">
			<input type="hidden" id="redirectURI" name="redirectURI" value="${loginBean.redirectURI}">
			<table align="" id="adminlogin" class="theDiv">
				<tr>
					
					<td><form:input id="username" name="username" class="login" style="height: 30px" path="" /></td>
				</tr>
				<tr>
				
					<td style="padding-top: 1em; padding-bottom: 1em;"><form:password id="password" name="password" class="login" style="height: 30px" path="" /></td>
				</tr>
				<tr>					
					<td colspan="3" align="center"><input type="image" alt="Submit"
						id="viewCaseDetailsAdmin"
						src="<c:url value="/resources/images/LoginBTN.jpg" />">
				</tr>
			</table>			
			
		</form:form>
		
		
		
	</body>	
</html>
