<%@include file="include.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Login</title>
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
        
            $('.chat-window').each(function(){
            	if($('.chat-window').size() > 1){
            		var chatTitle = $(this).find('.text').text();
                    if(chatTitle == 'Friends'){
                    	$(this).hide();
                    }else{
                    	$(this).css('right','10px');
                    }
            	}else{
            		$('.user-list-item').click();
            		var chatTitle = $(this).find('.text').text();
                    if(chatTitle == 'Friends'){
                    	$(this).hide();
                    }else{
                    	$(this).css('right','10px');
                    }
            	}
            	
            });
        });
    </script>
	</head>
	<body bgcolor="gainsboro" style="font-family: verdana;">
		<img src="<c:url value="/resources/images/chatbot-image.png" />"
		style="width: 100%;">
		
		
	</body>
</html>