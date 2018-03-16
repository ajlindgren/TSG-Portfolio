<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Vending Machine</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
        <link href="https://fonts.googleapis.com/css?family=Shrikhand" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Faustina" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <div id="headerDiv">
                <h1 class="text-center" style="padding-top: 10px">Vend-o-Tronic 2000 Deluxe</h1>
            </div>

            <div class="container row" id="mainDiv">
                <!-- left-side Div containing product info display -->
                <div class="col-md-9" id="left">
                    <div class="row-md-4" id="productDiv">
                        <c:forEach var="currentProduct" items="${productList}">
                            <form class="form-horizontal" name="select-${currentProduct.id}-form" action="selectProduct" method="POST">
                                <input hidden name="productName" value="${currentProduct.name}"/>
                            </form>
                            <div class="col-md-4" onClick="document.forms['select-${currentProduct.id}-form'].submit();">
                                <p class="pull-left id" style="font-size:15px">${currentProduct.id}</p>
                                <br>
                                <p class="text-center name" style="font-size:35px">${currentProduct.name}</p>
                                <br><br>
                                <p class="text-center price" style="font-size:20px">$ ${currentProduct.price}</p>
                                <br>
                                <p class="text-center quantity" style="font-size:20px"> Quantity Left: <span>${currentProduct.quantity}</span></p>
                            </div>
                        </c:forEach>
                    </div>
                </div>

                <!-- right-side Div containing user input/feedback -->
                <div class="col-md-3" align="center" id="right">
                    <div class="row" id="rTop">
                        <h3>Deposit</h3>
                        <div class="form-group form-group-sm">
                            <input class="form-control" id="disabledTotalInput" type="text" value="$${change.total}" placeholder="Total $ Deposited" readonly/>
                        </div>
                        <div class="form-group form-group-sm">
                            <form class="form-horizontal" action="deposit" method="POST">
                                <button class="btn btn-default" type="submit" name="amount" value="1.00">$1.00</button>
                                <button class="btn btn-default" type="submit" name="amount" value="0.25">$.25</button>
                                <br/>
                                <button class="btn btn-default" type="submit" name="amount" value="0.10">$.10</button>
                                <button class="btn btn-default" type="submit" name="amount" value="0.05">$.05</button> 
                            </form>                                    
                        </div>
                    </div>
                    <hr>
                    <div class="row" id="rMid">
                        <h3>Messages</h3>
                            <div class="form-group form-group-sm">
                                <input class="form-control" id="disable-messages" type="text" value="${errorMsg}" placeholder="Messages will display here" readonly />
                            </div>
                            <div class="form-group form-group-sm">
                                <div class="col-md-1"></div>
                                <label for="display-item" class="col-md-2 control-label">
                                    Item:
                                </label>
                                <div class="col-md-8">
                                    <input class="form-control" id="display-item" type="text" placeholder=""
                                           value="${selectedProduct.name} ${selectedProduct.price}" readonly />
                                </div>
                                <div class="col-md-1"></div>
                            </div>
                            <div class="form-group form-group-sm">
                                <form class="form-horizontal" action="purchaseSelection" method="GET">
                                    <button class="btn btn-default" type="submit">Make Purchase</button>
                                </form>
                            </div>
                    </div>
                    <hr>
                    <div class="row" id="rBot">
                        <h3>Change</h3>
                        <div>
                            <!-- rounding errors will likely be resolved by converting to int -->
                            <input class="form-control" id="display-change" type="text" placeholder="Change will be returned here" 
                                   value="${quarters} Q(s), ${dimes} D(s) and ${nickels} N(s)" readonly/>
                        </div>
                        <div class="form-group form-group-sm">
                            <form class="form-horizontal" action="refundDeposit" method="POST">
                                <button class="btn btn-default" type="submit">Return Change</button>
                            </form>
                        </div>
                    </div>
                </div>

                <!-- mainDiv endpoint -->
            </div>
            <!-- containerDiv endpoint -->
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>

