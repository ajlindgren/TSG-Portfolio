<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Edit Sighting</title>
    </head>
    <body>
        <sf:form class="form-horizontal" role="form" modelAttribute="sighting"
                 method="POST" action="edit/${sighting.sightingId}">
            <div class="form-group">
                <div class="col-md-offset-4 col-md-8">
                    <input type="submit" class="btn btn-default pull-right" value="Update Sighting"/>
                </div>
            </div>
            <div class="form-group">
                <label for="add-sighting-datetime" class="col-md-4 control-label">Date/Time:</label>
                <div class="col-md-8">
                    <input type="datetime-local" class="form-control" id="add-sighting-datetime"
                           name="dateTime" path="dateTime" value="${sighting.dateTime}"/>
                </div>
            </div>
            <div class="form-group">
                <label for="add-location" class="col-md-4 control-label">Location:</label>
                <select class="form-control" id="add-location" name="locationId">
                    <c:forEach var="currentLoc" items="${locations}">
                        <option value="${currentLoc.locationId}">${currentLoc.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label for="add-supers" class="col-md-4 control-label">Supers:</label>
                <div class="col-md-8">
                    <select multiple class="form-control" id="add-supers" name="superIds" required>
                        <c:forEach var="sightingSuper" items="${sighting.supers}">
                            <c:forEach var="currentSuper" items="${supers}">
                                <c:choose>
                                    <c:when test="${sightingSuper.superId == currentSuper.superId}">
                                        <option value="${currentSuper.superId}" selected>${currentSuper.name}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${currentSuper.superId}">${currentSuper.name}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <sf:hidden path="sightingId"/>
            </div>
        </sf:form>
    </body>
</html>