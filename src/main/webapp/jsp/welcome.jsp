<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<c:set var="fbflag" value='${requestScope["FBFlag"]}' />
	<c:set var="alexaflag" value='${requestScope["AlexaFlag"]}' />
	<c:set var="tranList" value='${userDetails.transactionDetails}' />
	<c:if test="${fbflag eq 'Y'}">
		<c:redirect url='https://www.facebook.com/messenger_platform/account_linking?account_linking_token=${LinkToken}&authorization_code=${authCode}'/>	
     </c:if>
     <c:out value="${alexaflag}"/>
     <c:if test="${alexaflag eq 'Y'}">
		<c:redirect url='${url}'/>	
     </c:if>
      
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Account Details</title>
	<link rel="stylesheet" href="resources/css/democss.css" type="text/css" />
	<link rel="icon" href="resources/images/favicon.ico"/>
	<!-- chatjs requirements -->
    <script src="resources/ChatJs/js/jquery-1.9.1.min.js"></script>
    <script src="resources/ChatJs/js/jquery.autosize.js"></script>
    <link rel="stylesheet" href="resources/Styles/styles.css"/>

    <!-- chatjs files-->
    <script src="resources/ChatJs/js/jquery.chatjs.utils.js"></script>
    <script src="resources/ChatJs/js/jquery.chatjs.adapter.servertypes.js"></script>
    <script src="resources/ChatJs/js/jquery.chatjs.adapter.js"></script>
    <script src="resources/ChatJs/js/jquery.chatjs.adapter.demo.js"></script>
    <script src="resources/ChatJs/js/jquery.chatjs.window.js"></script>
    <script src="resources/ChatJs/js/jquery.chatjs.messageboard.js"></script>
    <script src="resources/ChatJs/js/jquery.chatjs.userlist.js"></script>
    <script src="resources/ChatJs/js/jquery.chatjs.pmwindow.js"></script>
    <script src="resources/ChatJs/js/jquery.chatjs.friendswindow.js"></script>
    <script src="resources/ChatJs/js/jquery.chatjs.controller.js"></script>
    <link rel="stylesheet" href="resources/ChatJs/css/jquery.chatjs.css"/>

    <script type="text/javascript">
        $(function () {
            $.chat({
                // your user information
                userId: 1,
                // id of the room. The friends list is based on the room Id
                roomId: 1,
                // text displayed when the other user is typing
                typingText: ' is typing...',
                // title for the rooms window
                roomsTitleText: 'Rooms',
                // title for the 'available rooms' rab
                availableRoomsText: 'Available rooms',
                // text displayed when there's no other users in the room
                emptyRoomText: "There's no one around here. You can still open a session in another browser and chat with yourself :)",
                // the adapter you are using
                chatJsContentPath: '/basics/chatjs/',
                adapter: new DemoAdapter()
            });
        });
    </script>
</head>
<body bgcolor="gainsboro" style="font-family: verdana;">
	<img src="<c:url value="/resources/images/TopHeader.jpg" />"
		style="width: 100%;">
	<center>Welcome ${loggedInUser}</center>
	Checking Account Balance: $<c:out value="${userDetails.accountBal}"/><br>
	
	<b>Transaction Details</b>
	<table>
		<tr>
			<th>Date</th>
			<th>Amount</th>
			<th>Desc</th>
		</tr>
		<c:forEach items="${tranList}" var="current">
	        <tr>
	          <td><c:out value="${current.tranDate}" /><td>
	          <td>$<c:out value="${current.tranAmount}" /><td>
	          <td><c:out value="${current.tranDesc}" /><td>
	        </tr>
      	</c:forEach>
	</table>
	<div id="footer">
			<img src="<c:url value="/resources/images/Footer.jpg" />"
				style="width: 100%;">
	</div>
</body>
</html>