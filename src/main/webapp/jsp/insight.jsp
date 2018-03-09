<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insite</title>
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
 
       <div class="innerdiv">
          <iframe
                width="350"
                height="430"
                src="https://console.dialogflow.com/api-client/demo/embedded/a639c596-cd0a-42d9-8685-e43ac6586e41">
            </iframe>
       </div>
</body>
</html>
