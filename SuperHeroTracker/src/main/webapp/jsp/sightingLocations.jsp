<%-- 
    Document   : sightingLocations
    Created on : Mar 20, 2018, 6:51:57 PM
    Author     : Alex
--%>

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
                </ul>    
                </div>
            </div>
            <div class="container row">
                <div class="col-md-3" id="searchLocations">
                    Locations
                </div>
                <div class="col-md-6" id="displayLocations">
                    Location Name/Super Icon
                </div>
                <div class="col-md-3" id="searchSightings">
                    Sightings
                    Super Icons
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
