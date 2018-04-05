<%-- 
    Document   : sightingLocations
    Created on : Mar 20, 2018, 6:51:57 PM
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
                <h1>Super Sightings and Locations</h1>
                <div class="navbar">
                    <ul class="nav nav-tabs">
                        <li role="presentation"><a href="${pageContext.request.contextPath}/">Home</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/displaySupers">Supers</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/displayOrganizations">Organizations</a></li>
                        <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/displaySightingLocations">Sighting Locations</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/displayManager">Manager</a></li>

                    </ul>  
                </div>
            </div>
            <div class="container row">
                <!-- Left-side Locations Div -->
                <div class="col-md-3" id="searchLocations">
                    Locations
                    <c:forEach var="currentLocation" items="${locations}">
                        <div>
                            <a href="${pageContext.request.contextPath}/displaySightingLocations/locations/${currentLocation.locationId}">${currentLocation.name}</a>
                        </div>
                    </c:forEach>
                </div>

                <!-- Center display Div -->
                <div class="col-md-6" id="displayLocations">
                    <c:choose>
                        <c:when test="${config==1}">
                            <h2>${clickedLocation.name}</h2>
                            <div class="row-md-3">
                                <c:forEach var="currentSighting" items="${sightByLocId}">
                                    <c:forEach var="currentSuper" items="${currentSighting.supers}">
                                        <div class="col-md-4" style="height:125px">
                                            <form role="form" method="GET" action="editSighting/${currentSighting.sightingId}">
                                                <input type="submit" value="Edit" class="pull-right btn btn-default" id="edit${currentSighting.sightingId}"/>
                                            </form>
                                            <img src="${pageContext.request.contextPath}/${currentSuper.iconFile}"
                                                 class="img-thumbnail" alt="Super Icon" width="40" height="40">
                                            <p>${currentSuper.name}</p>
                                            <p>${currentSighting.dateTime}</p>
                                        </div>
                                    </c:forEach>
                                </c:forEach>
                            </div>
                        </c:when>
                        <c:when test="${config==2}">
                            <h2>${clickedSuper.name} Sightings</h2>
                            <div class="row-md-3">
                                <c:forEach var="currentSighting" items="${sightingsForSuper}">
                                    <div class="col-md-4" style="height:125px">
                                        <form role="form" method="GET" action="editSighting/${currentSighting.sightingId}">
                                            <input type="submit" value="Edit" class="pull-right btn btn-default" id="edit${currentSighting.sightingId}"/>
                                        </form>
                                        <p>
                                            ${currentSighting.dateTime}
                                        </p>
                                        <p>
                                            ${currentSighting.location.name}
                                        </p>
                                    </div>
                                </c:forEach>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <h2>${defaultLocation.name}</h2>
                            <div class="row-md-3">
                                <c:forEach var="currentSighting" items="${sightByLocId}">
                                    <c:forEach var="currentSuper" items="${currentSighting.supers}">
                                        <div class="col-md-4" style="height:125px">
                                            <form role="form" method="GET" action="editSighting/${currentSighting.sightingId}">
                                                <input type="submit" value="Edit" class="pull-right btn btn-default" id="edit${currentSighting.sightingId}"/>
                                            </form>
                                            <img src="${pageContext.request.contextPath}/${currentSuper.iconFile}"
                                                 class="img-thumbnail" alt="Super Icon" width="40" height="40">
                                            <p>${currentSuper.name}</p>
                                            <p>${currentSighting.dateTime}</p>
                                        </div>
                                    </c:forEach>
                                </c:forEach>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>

                <!-- Right-side sightings Div -->
                <div class="col-md-3" id="searchSupers">
                    Sightings
                    <c:forEach var="currentSuper" items="${supers}">
                        <div>
                            <a href="${pageContext.request.contextPath}/displaySightingLocations/sightings/${currentSuper.superId}">
                                <img src="${pageContext.request.contextPath}/${currentSuper.iconFile}"
                                     class="img-thumbnail" alt="Super Icon" width="50" height="50"></a>
                                ${currentSuper.name}
                        </div>
                    </c:forEach>
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
