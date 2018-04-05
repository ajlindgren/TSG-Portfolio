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
                        <li role="presentation"><a href="${pageContext.request.contextPath}/displayManager">Manager</a></li>
                </ul>    
            </div>
            </div>
            <div class="container" id="mainDiv">
                <!-- div containing google map info and page description -->
                <div id="topDiv">
                    <h1>Welcome</h1>
                    
                    <p>
                        Heroes and Villains have arisen from their secret lairs and now do battle
                        all across the country. It is our civic (and fanboy/fangirl) duty to keep 
                        track of any instances of Super Sightings!
                    </p>
                    <p>
                        Therefore, we at The Software Guild have created this application
                        to help the citizens of the United States keep track of any Super
                        activity they witness.
                    </p>
                    <p>
                        Feel free to mark and describe any such instances, and remember!
                        Don't interfere!
                    </p>
                </div>
                <!-- div containing list of recent sightings and addSighting -->
                <div id="bottomDiv">
                    <div class="row-md-12" id="recentSightings">
                        <h2>10 most recent sightings</h2>
                        <c:forEach var="currentSighting" items="${sightingList}">
                            <div class="col-md-3">
                                <p>
                                    ${currentSighting.dateTime}
                                </p>
                                <p>
                                    ${currentSighting.location.name}
                                </p>
                                <c:forEach var="innerSuper" items="${currentSighting.supers}">
                                <img src="${pageContext.request.contextPath}/${innerSuper.iconFile}"
                                     class="img-thumbnail" alt="Super Icon" width="50" height="50">
                                </c:forEach>
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

