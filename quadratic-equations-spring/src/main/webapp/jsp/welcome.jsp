<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>
        lab
    </title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width"/>
</head>
<body>

<h2>Put coefficients in cells. Press button. Profit. Enjoy.</h2>

<header class="header">
    <div class="overlay"></div>
    <div class="container offset-1">
        <%--action="${pageContext.request.contextPath}/"--%>
        <form id="report-submit-ajax-form" class="" method="POST" action="${pageContext.request.contextPath}/calculate">

            <div id="ajax-succeed"></div>

            <div class="form-group">
                <div class="">
                    <input maxlength="45" minlength="1" name="paramA" id="paramA" type="text"
                           placeholder="a" required>
                    <span>
                        x <sup>2</sup> +
                    </span>

                    <input maxlength="45" minlength="1" name="paramB" id="paramB" type="text"
                           placeholder="b" required>
                    <span>
                        x +
                    </span>

                    <input maxlength="45" minlength="1" name="paramC" id="paramC" type="text"
                           placeholder="c" required>

                </div>
            </div>

            <div class="form-group">
                <div class="">
                    <button type="submit" class="btn btn-primary btn-sm" id="ajax-submit-btn">
                        calculate
                    </button>
                </div>
            </div>
        </form>
    </div>
</header>

<c:if test="${discriminantIsNegative == true}">
    <h3>Negative discriminant. Sorry bro</h3>
</c:if>

<c:if test="${calculation.discriminant != null}">
    <h3> D = <c:out value="${calculation.discriminant}"/></h3>
</c:if>
<c:if test="${calculation.x1 != null}">
    <h3> x1 = <c:out value="${calculation.x1}"/></h3>
</c:if>
<c:if test="${calculation.x2 != null}">
    <h3> x2 = <c:out value="${calculation.x2}"/></h3>
</c:if>


<%--<script src="${pageContext.request.contextPath}/resources/js/ajax-submit-report.js"></script>--%>
</body>

</html>


