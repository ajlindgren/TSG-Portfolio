<%-- 
    Document   : manager
    Created on : Mar 29, 2018, 7:08:02 PM
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
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Super Hero Tracker</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"> 
    </head>
    <body>
        <div class="container">

            <!-- Navigation/Header -->
            <div class="headerDiv">
                <h1>Manager</h1>
                <div class="navbar">
                    <ul class="nav nav-tabs">
                        <li role="presentation"><a href="${pageContext.request.contextPath}/">Home</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/displaySupers">Supers</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/displayOrganizations">Organizations</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/displaySightingLocations">Sighting Locations</a></li>
                        <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/displayManager">Manager</a></li>
                    </ul>  
                </div>
            </div>

            <!-- Manager Options -->
            <div class="row">
                <div class="col-sm-2">
                    <button class="btn btn-default" id="power">Manage Powers</button>
                </div>
                <div class="col-sm-2">
                    <button class="btn btn-default" id="super">Manage Supers</button>
                </div>
                <div class="col-sm-2">
                    <button class="btn btn-default" id="hq">Manage HQs</button>
                </div>
                <div class="col-sm-2">
                    <button class="btn btn-default" id="org">Manage Orgs</button>
                </div>
                <div class="col-sm-2">
                    <button class="btn btn-default" id="location">Manage Locations</button>
                </div>
                <div class="col-sm-2">
                    <button class="btn btn-default" id="sighting">Manage Sightings</button>                    
                </div>
            </div>

            <!-- Hide/Show Manager Divs -->
            <div id="managePower" class="pull-left" hidden>
                <form class="form-horizontal" 
                      role="form" method="POST" 
                      action="addPower">
                    <div class="form-group">
                        <label for="add-power-description" class="col-md-4 control-label">Power:</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" name="powerDescription" placeholder="Power" maxlength="50" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-offset-4 col-md-8">
                            <input type="submit" class="btn btn-default" value="Create Power"/>
                        </div>
                    </div>
                </form>
                <form class="form-horizontal"
                      role="form" method="POST"
                      action="deletePower">
                    <div class="form-group">
                        <select class="form-control" id="select-power" name="powerId">
                            <c:forEach var="currentPower" items="${powers}">
                                <option value="${currentPower.powerId}">${currentPower.description}</option>
                            </c:forEach>
                        </select>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" class="btn btn-default" value="Delete Power"/>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div id="manageSuper" class="pull-left" hidden>
                <form class="form-horizontal" role="form" method="POST" action="addSuper" modelAttribute="upSuper">
                    <div class="form-group">
                        <label for="add-super-name" class="col-md-4 control-label">Name:</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" id="add-super-name" name="name" placeholder="Name" maxlength="50" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-super-description" class="col-md-4 control-label">Description:</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" id="add-super-description" 
                                   name="description" placeholder="Description" maxlength="1200" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="select-power" class="col-md-4 control-label">Power:</label>
                        <div class="col-md-8">
                            <select class="form-control" id="select-power" name="powerId">
                                <c:forEach var="currentPower" items="${powers}">
                                    <option value="${currentPower.powerId}">${currentPower.description}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-offset-4 col-md-8">
                            <input type="submit" class="btn btn-default" value="Create Super"/>
                        </div>
                    </div>
                </form>
                <form class="form-horizontal"
                      role="form" method="POST"
                      action="deleteSuper">
                    <div class="form-group">
                        <select class="form-control" id="select-super" name="superId">
                            <c:forEach var="currentSuper" items="${supers}">
                                <option value="${currentSuper.superId}">${currentSuper.name}</option>
                            </c:forEach>
                        </select>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" class="btn btn-default" value="Delete Super"/>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div id="manageHq" class="pull-left" hidden>
                <form class="form-horizontal" 
                      role="form" method="POST" 
                      action="addHq">
                    <div class="form-group">
                        <label for="add-hq-address" class="col-md-4 control-label">Address:</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" name="hqAddress" placeholder="Address" maxlength="75" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-hq-planet" class="col-md-4 control-label">Planet:</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" name="hqPlanet" placeholder="Planet" maxlength="30" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-offset-4 col-md-8">
                            <input type="submit" class="btn btn-default" value="Create HQ"/>
                        </div>
                    </div>
                </form>
                <form class="form-horizontal"
                      role="form" method="POST"
                      action="deleteHq">
                    <div class="form-group">
                        <select class="form-control" id="select-hq" name="hqId">
                            <c:forEach var="currentHq" items="${hqs}">
                                <option value="${currentHq.headquartersId}">${currentHq.address}, ${currentHq.planet}</option>
                            </c:forEach>
                        </select>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" class="btn btn-default" value="Delete HQ"/>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div id="manageOrg" class="pull-left" hidden>
                <form class="form-horizontal" 
                      role="form" method="POST" 
                      action="addOrg">
                    <div class="form-group">
                        <label for="add-org-name" class="col-md-4 control-label">Name:</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" name="orgName" placeholder="Name" maxlength="50" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-org-description" class="col-md-4 control-label">Description:</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" name="orgDescription" placeholder="Description" maxlength="1200" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-org-email" class="col-md-4 control-label">Email:</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" name="orgEmail" placeholder="Email" maxlength="50" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="select-hq" class="col-md-4 control-label">HQ:</label>
                        <div class="col-md-8">
                            <select class="form-control" id="select-hq" name="hqId" required>
                                <c:forEach var="currentHq" items="${hqs}">
                                    <option value="${currentHq.headquartersId}">${currentHq.address}, ${currentHq.planet}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="select-supers" class="col-md-4 control-label">Supers:</label>
                        <div class="col-md-8">
                            <select multiple class="form-control" id="select-location" name="superIds" required>
                                <c:forEach var="currentSuper" items="${supers}">
                                    <option value="${currentSuper.superId}">${currentSuper.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="select-alignment" class="col-md-4 control-label">Alignment:</label>
                        <div class="col-md-8">
                            <select class="form-control" id="select-alignment" name="alignment" required>
                                <option value="heroic">Heroic</option>
                                <option value="villainous">Villainous</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-offset-4 col-md-8">
                            <input type="submit" class="btn btn-default" value="Create Organization"/>
                        </div>
                    </div>
                </form>
                <form class="form-horizontal"
                      role="form" method="POST"
                      action="deleteOrg">
                    <div class="form-group">
                        <select class="form-control" id="select-org" name="orgId">
                            <c:forEach var="currentOrg" items="${orgs}">
                                <option value="${currentOrg.organizationId}">${currentOrg.name}</option>
                            </c:forEach>
                        </select>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" class="btn btn-default" value="Delete Org"/>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div id="manageLocation" class="pull-left" hidden>
                <form class="form-horizontal" 
                      role="form" method="POST" 
                      action="addLocation">
                    <div class="form-group">
                        <label for="add-location-name" class="col-md-4 control-label">Name:</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" name="locName" placeholder="Name" maxlength="60" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-location-description" class="col-md-4 control-label">Description:</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" name="locDescription" placeholder="Description" maxlength="1000" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-location-address" class="col-md-4 control-label">Address:</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" name="locAddress" placeholder="Address" maxlength="75" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-location-latitude" class="col-md-4 control-label">Latitude:</label>
                        <div class="col-md-8">
                            <input type="number" max="180" min="-180" step=".000001" class="form-control" name="locLatitude" placeholder="Latitude" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-location-longitude" class="col-md-4 control-label">Longitude:</label>
                        <div class="col-md-8">
                            <input type="number" max="180" min="-180" step=".000001" class="form-control" name="locLongitude" placeholder="Longitude" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-offset-4 col-md-8">
                            <input type="submit" class="btn btn-default" value="Create Location"/>
                        </div>
                    </div>
                </form>
                <form class="form-horizontal"
                      role="form" method="POST"
                      action="deleteLocation">
                    <div class="form-group">
                        <select class="form-control" id="select-location" name="locId">
                            <c:forEach var="currentLoc" items="${locations}">
                                <option value="${currentLoc.locationId}">${currentLoc.name}</option>
                            </c:forEach>
                        </select>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" class="btn btn-default" value="Delete Location"/>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div id="manageSighting" class="pull-left" hidden>
                <form class="form-horizontal" 
                      role="form" method="POST" 
                      action="addSighting">
                    <div class="form-group">
                        <label for="add-sighting-datetime" class="col-md-4 control-label">Date/Time:</label>
                        <div class="col-md-8">
                            <input type="datetime-local" class="form-control" id="add-sighting-datetime"
                                   name="dateTime" path="dateTime" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="select-location" class="col-md-4 control-label">Location:</label>
                        <div class="col-md-8">
                            <select class="form-control" id="select-hq" name="locId" required>
                                <c:forEach var="currentLoc" items="${locations}">
                                    <option value="${currentLoc.locationId}">${currentLoc.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="select-supers" class="col-md-4 control-label">Supers:</label>
                        <div class="col-md-8">
                            <select multiple class="form-control" id="select-location" name="superIds" required>
                                <c:forEach var="currentSuper" items="${supers}">
                                    <option value="${currentSuper.superId}">${currentSuper.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-offset-4 col-md-8">
                            <input type="submit" class="btn btn-default" value="Create Sighting"/>
                        </div>
                    </div>
                </form>
                <form class="form-horizontal"
                      role="form" method="POST"
                      action="deleteSighting">
                    <div class="form-group">
                        <select class="form-control" id="select-sighting" name="sightingId">
                            <c:forEach var="currentSighting" items="${sightings}">
                                <option value="${currentSighting.sightingId}">${currentSighting.location.name}, ${currentSighting.dateTime}</option>
                            </c:forEach>
                        </select>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" class="btn btn-default" value="Delete Sighting"/>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <!-- container endpoint -->
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/manager.js"></script>
    </body>
</html>
