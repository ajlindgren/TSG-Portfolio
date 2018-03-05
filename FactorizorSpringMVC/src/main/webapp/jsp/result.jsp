<%-- 
    Document   : result
    Created on : Feb 27, 2018, 7:25:45 PM
    Author     : Alex
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
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
            Factors are:
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
