<%-- 
    Document   : astley
    Created on : Feb 27, 2018, 2:28:09 PM
    Author     : Alex
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Result</title>
    </head>
    <body>
        <h1>You're no stranger to Love.</h1>
        
        <c:out value="${youKnowTheRules}"/>
        
        <p>
            <a href="index.jsp">Back Around Again</a>
        </p>
    </body>
</html>
