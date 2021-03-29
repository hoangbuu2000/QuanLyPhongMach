<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">


<!-- index22:59-->
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
    <link rel="shortcut icon" type="image/x-icon" href="<c:url value="/resources/assets/img/favicon.ico" />">
    <title>DHB</title>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/assets/css/bootstrap.min.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/assets/css/font-awesome.min.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/assets/css/style.css" />">
    <!--[if lt IE 9]>
<%--    <script src="<c:url value="/resources/assets/js/html5shiv.min.js" />"></script>--%>
<%--    <script src="<c:url value="/resources/assets/js/respond.min.js" />"></script>--%>
    <![endif]-->
</head>

<body>
<div class="main-wrapper">
    <tiles:insertAttribute name="navbar" />
    <tiles:insertAttribute name="sidebar" />
    <tiles:insertAttribute name="body" />
</div>
<div class="sidebar-overlay" data-reff=""></div>
<script src="<c:url value="/resources/assets/js/jquery-3.2.1.min.js" />"></script>
<script src="<c:url value="/resources/assets/js/popper.min.js" />"></script>
<script src="<c:url value="/resources/assets/js/bootstrap.min.js" />"></script>
<script src="<c:url value="/resources/assets/js/jquery.slimscroll.js" />"></script>
<script src="<c:url value="/resources/assets/js/Chart.bundle.js" />"></script>
<script src="<c:url value="/resources/assets/js/chart.js" />"></script>
<script src="<c:url value="/resources/assets/js/app.js" />"></script>

</body>


<!-- index22:59-->
</html>