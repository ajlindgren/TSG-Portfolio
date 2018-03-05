<%-- 
    Document   : index
    Created on : Feb 26, 2018, 10:06:11 PM
    Author     : Alex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Factorizor</title>
    </head>
    <body>
        <h1>Factorizor</h1>
        <p>
            Please enter number to factor:
        </p>
        <p>
        <form method="POST" action="FactorizorServlet">
            <input type="text" name="numberToFactor">
            <input type="submit" value="Find Factors">
        </form>
        
        <form method="GET" action="Servlet2">
            <button type="submit">Are you a stranger to love?</button>
        </form>
        </p>
    </body>
</html>
