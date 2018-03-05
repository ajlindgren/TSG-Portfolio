<%-- 
    Document   : result
    Created on : Feb 26, 2018, 10:07:42 PM
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
        <h1>Result</h1>
        <p>
            You asked to factor: ${numberToFactor}
        </p>
        
        <p>
            <c:forEach var="currentFactor" items="${factors}">
                <c:out value="${currentFactor}"/>
            </c:forEach>
        </p>
        
        <p>
            <c:choose>
                <c:when test="${isPrime}">
                    ${numberToFactor} is Prime!
                </c:when>
                <c:otherwise>
                    Not Prime!
                </c:otherwise>
            </c:choose>
        </p>
        
        <p>
            <c:choose>
                <c:when test="${isPerfect}">
                    ${numberToFactor} is Perfect!
                </c:when>
                <c:otherwise>
                    Not Perfect!
                </c:otherwise>
            </c:choose>
        </p>
        
        <p>
            <a href="index.jsp">Factor Another</a>
        </p>
    </body>
</html>
