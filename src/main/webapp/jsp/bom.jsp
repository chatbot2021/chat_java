<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Tapco</title>
<style>

.innerdiv {
 position: fixed;
    bottom: 0px;
    right: 0px; 
}
    body {    
    background-repeat: no-repeat;
}
    </style>  
</head>
<body background="<c:url value="resources/images/NewGenBank-chatbot.jpg" />">
 
     <div id="ibm-chat-root"></div>
  

    
</body>
    <script src='https://unpkg.com/@watson-virtual-agent/chat-widget@1.6.0/dist/chat.min.js'>
</script>
<script>
  IBMChat.init({
    el: 'ibm_chat_root',
    baseURL: 'https://gateway.watsonplatform.net/conversation/api',
    botID: '04c1f7c2-c655-46f8-9527-809809fc4282',
    XIBMClientID: 'fae8a157-a382-43e2-bddb-59ef82946481',
    XIBMClientSecret: 'mdqboUrRRbyO'
  });
</script>
</html>
