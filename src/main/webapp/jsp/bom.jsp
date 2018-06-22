<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Tapco</title>
<script src='https://unpkg.com/@watson-virtual-agent/chat-widget@1.6.0/dist/chat.min.js'>
</script>
<script>
  IBMChat.init({
    el: 'ibm_chat_root',
    baseURL: 'https://api.ibm.com/virtualagent/run/api/v1',
    botID: 'YOUR_BOT_ID',
    XIBMClientID: 'YOUR_IBM_CLIENT_ID',
    XIBMClientSecret: 'YOUR_IBM_CLIENT_SECRET'
  });
</script>
</head>
<body background="<c:url value="resources/images/NewGenBank-chatbot.jpg" />">
 
      
  

    
</body>
</html>
