<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Edit Organization</title>
    </head>
    <body>
        <sf:form class="form-horizontal" role="form" modelAttribute="org"
                 action="${org.organizationId}" method="POST">
            <div class="form-group">
                <div class="col-md-offset-4 col-md-8">
                    <input type="submit" class="btn btn-default pull-right" value="Update Organization"/>
                </div>
            </div>
            <div class="form-group">
                <label for="add-org-name" class="col-md-4 control-label">Name:</label>
                <div class="col-md-8">
                    <sf:input type="text" class="form-control" id="add-org-name"
                              path="name" placeholder="Organization Name"/>
                    <sf:errors path="name" cssclass="error"></sf:errors>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-org-description" class="col-md-4 control-label">Description:</label>
                    <div class="col-md-8">
                    <sf:input type="text" class="form-control" id="add-org-description"
                              path="description" placeholder="Organization Description"/>
                    <sf:errors path="description" cssclass="error"></sf:errors>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-org-email" class="col-md-4 control-label">Email:</label>
                    <div class="col-md-8">
                    <sf:input type="text" class="form-control" id="add-org-email"
                              path="email" placeholder="Organization Email"/>
                    <sf:errors path="email" cssclass="error"></sf:errors>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-org-alignment" class="col-md-4 control-label">Alignment(Heroic):</label>
                    <div class="col-md-8">
                        <input type="checkbox" id="select-heroic" name="alignment"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-hq" class="col-md-4 control-label">Headquarters:</label>
                    <select class="form-control" id="add-hq" name="hqId">
                    <c:forEach var="currentHq" items="${hqs}">
                        <option value="${currentHq.headquartersId}">${currentHq.address}, ${currentHq.planet}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label for="add-supers" class="col-md-4 control-label">Supers:</label>
                <div class="col-md-8">
                    <select multiple class="form-control" id="add-supers" name="superIds" required>
                        <c:forEach var="orgSuper" items="${org.supers}">
                            <c:forEach var="currentSuper" items="${supers}">
                                <c:choose>
                                    <c:when test="${orgSuper.superId == currentSuper.superId}">
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
                <sf:hidden path="organizationId"/>
            </div>
        </sf:form>
    </body>
</html>