<%--
  Created by IntelliJ IDEA.
  User: Buu
  Date: 26/04/2021
  Time: 7:34 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Login V1</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--===============================================================================================-->
    <link rel="icon" type="image/png" href="<c:url value="/resources/login/images/icons/favicon.ico" />"/>
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/login/vendor/bootstrap/css/bootstrap.min.css" />"/>
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/login/fonts/font-awesome-4.7.0/css/font-awesome.min.css" />"/>
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/login/vendor/animate/animate.css" />"/>
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/login/vendor/css-hamburgers/hamburgers.min.css" />"/>
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/login/vendor/select2/select2.min.css" />"/>
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/login/css/util.css" />"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/login/css/main.css" />"/>
    <!--===============================================================================================-->
</head>
<body>


<tiles:insertAttribute name="body" />


<!--===============================================================================================-->
<script src="<c:url value="/resources/login/vendor/jquery/jquery-3.2.1.min.js" />" ></script>
<!--===============================================================================================-->
<script src="<c:url value="/resources/login/vendor/bootstrap/js/popper.js" />" ></script>
<script src="<c:url value="/resources/login/vendor/bootstrap/js/bootstrap.min.js" />" ></script>
<!--===============================================================================================-->
<script src="<c:url value="/resources/login/vendor/select2/select2.min.js" />" ></script>
<!--===============================================================================================-->
<script src="<c:url value="/resources/login/vendor/tilt/tilt.jquery.min.js" />" ></script>
<script >
    $('.js-tilt').tilt({
        scale: 1.1
    })
</script>
<!--===============================================================================================-->
<script src="<c:url value="/resources/login/js/main.js" />" ></script>

</body>
</html>
