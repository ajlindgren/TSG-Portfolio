<%-- 
    Document   : editSuper
    Created on : Mar 28, 2018, 7:06:02 PM
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
        <title>Edit Super</title>
    </head>
    <body>
        <sf:form class="form-horizontal" role="form" modelAttribute="upSuper"
                 action="editSuper" method="POST">
            <div class="form-group">
                <label for="add-super-name" class="col-md-4 control-label">Super Name:</label>
                <div class="col-md-8">
                    <sf:input type="text" class="form-control" id="add-super-name"
                              path="name" placeholder="Super Name"/>
                    <sf:errors path="name" cssclass="error"></sf:errors>
                </div>
            </div>
            <div class="form-group">
                <label for="add-super-description" class="col-md-4 control-label">Description:</label>
                <div class="col-md-8">
                    <sf:input type="text" class="form-control" id="add-super-description"
                              path="description" placeholder="Super Description"/>
                    <sf:errors path="description" cssclass="error"></sf:errors>
                </div>
            </div>
            <div class="form-group">
                Power
                <select class="form-control" name="powerId">
                    <c:forEach var="currentPower" items="${powerList}">
                        <option value="${currentPower.powerId}">${currentPower.description}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <sf:hidden path="superId"/>
            </div>
            <div class="form-group">
                <div class="col-md-offset-4 col-md-8">
                    <input type="submit" class="btn btn-default" value="Update Super"/>
                </div>
            </div>
        </sf:form>
    </body>
</html>
