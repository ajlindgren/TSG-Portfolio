<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                <h1>Super Hero Tracker Home</h1>
                <div class="navbar">
                <ul class="nav nav-tabs">
                	<li role="presentation" class="active"><a href="${pageContext.request.contextPath}/">Home</a></li>
                	<li role="presentation"><a href="${pageContext.request.contextPath}/displaySupers">Supers</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/displayOrganizations">Organizations</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/displaySightingLocations">Sighting Locations</a></li>
                </ul>    
            </div>
            </div>
            <div class="container row" id="mainDiv">
                <!-- div containing google map info and page description -->
                <div class="col-md-9" id="leftDiv">
                    <h2>A BIG OL' MAP</h2>
                </div>
                <!-- div containing list of recent sightings and addSighting -->
                <div class="col-md-3" id="rightDiv">
                    <div id="recentSightings">
                        <h2>10 most recent sightings</h2>
                        <c:forEach var="currentSighting" items="${sightingList}">
                            <div>
                                <p>
                                    ${currentSighting.getDateTime()}
                                </p>
                                <p>
                                    ${currentSighting.location.getName()}
                                </p>
                            </div>
                        </c:forEach>
                    </div>
                    <div id="addSighting">
                        <h2>add a new Super Hero Sighting</h2>
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

