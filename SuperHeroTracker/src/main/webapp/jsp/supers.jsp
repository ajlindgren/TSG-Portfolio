<%-- 
    Document   : supers
    Created on : Mar 20, 2018, 6:34:43 PM
    Author     : Alex
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Super Hero Tracker</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">   
    </head>
    <body>
        <div class="container">
            <div class="headerDiv">
                <h1>Supers</h1>
                <div class="navbar">
                    <ul class="nav nav-tabs">
                        <li role="presentation"><a href="${pageContext.request.contextPath}/">Home</a></li>
                        <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/displaySupers">Supers</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/displayOrganizations">Organizations</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/displaySightingLocations">Sighting Locations</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/displayManager">Manager</a></li>

                    </ul>  
                </div>
            </div>
            <div class="container" id="fullSupers">
                <c:forEach var="currentSuper" items="${supers}">
                    <div class="container row">
                        <div class="col-md-2">
                            <div>
                                <img src="${pageContext.request.contextPath}/${currentSuper.iconFile}"
                                     class="img-thumbnail" alt="Super Icon" width="200" height="200">
                            </div>
                            <form role="form" method="POST" 
                                  action="addIcon/${currentSuper.superId}" 
                                  enctype="multipart/form-data">
                                <div class="form-group">
                                    <label for="picture">Upload Icon:</label> 
                                    <input type="file" 
                                           id="picture" 
                                           name="picture"/>
                                </div>
                                <input type="submit" value="Upload Icon"/>
                            </form> 
                        </div>
                        <div class="col-md-7">
                            <form role="form" method="GET" action="editSuper/${currentSuper.superId}">
                                <input type="submit" value="Edit" class="pull-right btn btn-default" id="edit${currentSuper.superId}"/>
                            </form>
                            <p><h2 class="text-center">${currentSuper.name}</h2></p>
                            <p class="text-right">Description: ${currentSuper.description}</p>
                            <p class="text-right">Power: ${currentSuper.power.description}</p>
                            <p class="text-center">Organizations:</p>
                            <c:forEach var="currentOrg" items="${orgs}">
                                <c:forEach var="innerSuper" items="${currentOrg.supers}">
                                    <c:if test="${innerSuper.superId == currentSuper.superId}">
                                        <p class="text-center">${currentOrg.name}</p>
                                    </c:if>
                                </c:forEach>
                            </c:forEach>
                        </div>
                    </div>

                </c:forEach>
            </div>
            <!-- container endpoint -->
        </div>
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/main.js"></script>
</body>
</html>
