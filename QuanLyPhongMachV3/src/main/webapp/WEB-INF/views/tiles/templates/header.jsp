<%--
  Created by IntelliJ IDEA.
  User: Buu
  Date: 02/03/2021
  Time: 8:11 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-custom navbar-fixed-top" role="navigation">
    <div class="top-area">
        <div class="container">
            <div class="row">
                <div class="col-sm-6 col-md-6">
                    <p class="bold text-left">Monday - Saturday, 8am to 10pm </p>
                </div>
                <div class="col-sm-6 col-md-6">
                    <p class="bold text-right">Call us now +62 008 65 001</p>
                </div>
            </div>
        </div>
    </div>
    <div class="container navigation">

        <div class="navbar-header page-scroll">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-main-collapse">
                <i class="fa fa-bars"></i>
            </button>
            <a class="navbar-brand" href="<c:url value="/" />">
                <img src="<c:url value="/resources/client/img/logo.png" />" alt="" width="150" height="40">
            </a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse navbar-right navbar-main-collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#intro">Home</a></li>
                <li class=""><a href="#service">Service</a></li>
                <li class=""><a href="#doctor">Doctors</a></li>
                <li class=""><a href="#facilities">Facilities</a></li>
                <li><a href="#pricing">Pricing</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="badge custom-badge red pull-right">Extra</span>Search <b class="caret"></b></a>
                    <ul class="dropdown-menu" style="width: 300px; margin-top: 16px;">
                        <form action="/search">
                            <li><input name="q" class="form-control" placeholder="Enter ID" required /></li>
                            <li class="text-right"><input type="submit" value="Submit" class="btn btn-primary" /></li>
                        </form>
<%--                        <li><a href="index-video.html">Home video</a></li>--%>
<%--                        <li><a href="index-cta.html">Home CTA</a></li>--%>
<%--                        <li><a href="https://bootstrapmade.com">Download</a></li>--%>
                    </ul>
                </li>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container -->
</nav>
