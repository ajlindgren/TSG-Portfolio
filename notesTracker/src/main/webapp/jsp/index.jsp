<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Index Page</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body>
        
        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <table class="table table-striped">
                        <tr>
                            <th>NotebookID</th>
                            <th>Subject</th>
                            <th>Color</th>
                        </tr>
                        
                        <c:forEach var="notebook" items="${notebooks}">
                            <tr>
                                <td>${notebook.notebookID}</td>
                                <td>${notebook.subject}</td>
                                <td>${notebook.color}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                
                <div class="col-md-6">
                    
                </div>
            </div>
            
            
            
            
            
            
            
        </div>
        
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>

