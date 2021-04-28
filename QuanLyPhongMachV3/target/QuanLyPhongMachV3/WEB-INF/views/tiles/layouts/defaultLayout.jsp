<%--
  Created by IntelliJ IDEA.
  User: Buu
  Date: 02/03/2021
  Time: 8:10 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Medicio landing page template for Health niche</title>

    <!-- css -->
    <link href="<c:url value="/resources/client/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/resources/client/font-awesome/css/font-awesome.min.css"/>" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/client/plugins/cubeportfolio/css/cubeportfolio.min.css"/>"/>
    <link href="<c:url value="/resources/client/css/nivo-lightbox.css"/>" rel="stylesheet" />
    <link href="<c:url value="/resources/client/css/nivo-lightbox-theme/default/default.css"/>" rel="stylesheet" type="text/css" />
    <link href="<c:url value="/resources/client/css/owl.carousel.css"/>" rel="stylesheet" media="screen" />
    <link href="<c:url value="/resources/client/css/owl.theme.css"/>" rel="stylesheet" media="screen" />
    <link href="<c:url value="/resources/client/css/animate.css"/>" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/assets/css/select2.min.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/assets/css/bootstrap-datetimepicker.min.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/assets/css/style.css" />">
    <link href="<c:url value="/resources/client/css/style.css"/>" rel="stylesheet">

    <!-- boxed bg -->
    <link id="bodybg" href="<c:url value="/resources/client/bodybg/bg1.css" />" rel="stylesheet" type="text/css" />
    <!-- template skin -->
    <link id="t-colors" href="<c:url value="/resources/client/color/default.css" />" rel="stylesheet">


</head>

<body id="page-top" data-spy="scroll" data-target=".navbar-custom">

<div id="wrapper">

    <tiles:insertAttribute name="header" />

    <tiles:insertAttribute name="body" />

    <tiles:insertAttribute name="footer" />

</div>
<a href="#" class="scrollup"><i class="fa fa-angle-up active"></i></a>

<!-- Core JavaScript Files -->
<script src="<c:url value="/resources/client/js/jquery.min.js"/>"></script>
<script src="<c:url value="/resources/client/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/resources/client/js/jquery.easing.min.js"/>"></script>
<script src="<c:url value="/resources/client/js/wow.min.js"/>"></script>
<script src="<c:url value="/resources/client/js/jquery.scrollTo.js"/>"></script>
<script src="<c:url value="/resources/client/js/jquery.appear.js"/>"></script>
<script src="<c:url value="/resources/client/js/stellar.js"/>"></script>
<script src="<c:url value="/resources/client/plugins/cubeportfolio/js/jquery.cubeportfolio.min.js"/>"></script>
<script src="<c:url value="/resources/client/js/owl.carousel.min.js"/>"></script>
<script src="<c:url value="/resources/client/js/nivo-lightbox.min.js"/>"></script>
<script src="<c:url value="/resources/assets/js/select2.min.js" />"></script>
<script src="<c:url value="/resources/assets/js/moment.min.js" />"></script>
<script src="<c:url value="/resources/assets/js/bootstrap-datetimepicker.min.js" />"></script>
<script src="<c:url value="/resources/assets/js/app.js" />"></script>
<script src="<c:url value="/resources/client/js/custom.js"/>"></script>


</body>

</html>

