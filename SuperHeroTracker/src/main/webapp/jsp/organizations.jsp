<%-- 
    Document   : organizations
    Created on : Mar 20, 2018, 6:51:31 PM
    Author     : Alex
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
                <h1>Super Organizations</h1>
                <div class="navbar">
                    <ul class="nav nav-tabs">
                        <li role="presentation"><a href="${pageContext.request.contextPath}/">Home</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/displaySupers">Supers</a></li>
                        <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/displayOrganizations">Organizations</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/displaySightingLocations">Sighting Locations</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/displayManager">Manager</a></li>
                    </ul>  
                </div>
            </div>
            <div class="container row">
                <div class="col-md-3" id="searchOrganizations">
                    <div>
                        <h3>Heroic</h3>
                        <ul>
                            <c:forEach var="currentOrg" items="${heroicOrgs}">
                                <li><a href="${pageContext.request.contextPath}/displayOrganizations/${currentOrg.organizationId}">${currentOrg.name}</a></li>
                                </c:forEach>
                        </ul>
                    </div>
                    <div>
                        <h3>Villainous</h3>
                        <ul>
                            <c:forEach var="currentOrg" items="${villainousOrgs}">
                                <li><a href="${pageContext.request.contextPath}/displayOrganizations/${currentOrg.organizationId}">${currentOrg.name}</a></li>
                                </c:forEach>
                        </ul>
                    </div>
                </div>
                <div class="col-md-9" id="organizationInfo">
                    <form role="form" method="GET" action="${pageContext.request.contextPath}/editOrganization/${org.organizationId}">
                        <input type="submit" value="Edit" class="pull-right btn btn-default" id="edit${org.organizationId}"/>
                    </form>
                    <div> 
                        <h2><c:out value="${org.name}"/></h2>
                        </br>
                        <p><c:out value="${org.description}"/></p>
                        <p><c:out value="${org.headquarters.address}, ${org.headquarters.planet}"/></p>
                        <p><c:out value="${org.email}"/></p>
                    </div>
                    <div>
                        <h4>Members:</h4>
                        <c:forEach var="currentSuper" items="${org.supers}">
                            <div class="col-md-2" style="height:75px">
                                <img src="${pageContext.request.contextPath}/${currentSuper.iconFile}"
                                     class="img-thumbnail" alt="Super Icon" width="50" height="50">
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
            <!-- container endpoint -->
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/main.js"></script>
    </body>
</html>
