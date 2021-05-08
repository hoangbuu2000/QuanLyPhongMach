<%@page contentType="text/html" pageEncoding="utf-8" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">


<!-- index22:59-->
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
    <link rel="shortcut icon" type="image/x-icon" href="<c:url value="/resources/assets/img/favicon.ico" />">
    <title><tiles:insertAttribute name="title" /></title>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/assets/css/bootstrap.min.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/assets/css/font-awesome.min.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/assets/css/select2.min.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/assets/css/dataTables.bootstrap4.min.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/assets/css/bootstrap-datetimepicker.min.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/assets/css/fullcalendar.min.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/assets/css/tagsinput.css" />">
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

<script>
    let e = document.getElementsByName("queQuan")[0];
    e.options.length = 0;
    let provinces = [
        'An Giang', 'Bà Rịa - Vũng Tàu', 'Bắc Giang',
        'Bắc Kạn', 'Bạc Liêu', 'Bắc Ninh', 'Bến Tre',
        'Bình Định', 'Bình Dương', 'Bình Phước', 'Bình Thuận',
        'Cà Mau', 'Cần Thơ', 'Cao Bằng', 'Đà Nẵng', 'Đắk Lắk',
        'Đắk Nông', 'Điện Biên', 'Đồng Nai', 'Đồng Tháp',
        'Gia Lai', 'Hà Giang', 'Hà Nam', 'Hà Nội', 'Hà Tĩnh',
        'Hải Dương', 'Hải Phòng', 'Hậu Giang', 'Hòa Bình',
        'Hưng Yên', 'Khánh Hòa', 'Kiên Giang', 'Kon Tum',
        'Lai Châu', 'Lâm Đồng', 'Lạng Sơn', 'Lào Cai',
        'Long An', 'Nam Định', 'Nghệ An', 'Ninh Bình',
        'Ninh Thuận', 'Phú Thọ', 'Phú Yên', 'Quảng Bình',
        'Quảng Nam', 'Quảng Ngãi', 'Quảng Ninh', 'Quảng Trị',
        'Sóc Trăng', 'Sơn La', 'Tây Ninh', 'Thái Bình',
        'Thái Nguyên', 'Thanh Hóa', 'Thừa Thiên Huế', 'Tiền Giang',
        'Thành phố Hồ Chí Minh', 'Trà Vinh', 'Tuyên Quang',
        'Vĩnh Long', 'Vĩnh Phúc', 'Yên Bái'
    ]
    for(let i = 0; i < provinces.length; i++) {
        e.append(new Option(provinces[i], provinces[i]));
    }
</script>

<script src="<c:url value="/resources/assets/js/jquery-3.2.1.min.js" />"></script>
<script src="<c:url value="/resources/assets/js/popper.min.js" />"></script>
<script src="<c:url value="/resources/assets/js/bootstrap.min.js" />"></script>
<script src="<c:url value="/resources/assets/js/jquery.slimscroll.js" />"></script>
<script src="<c:url value="/resources/assets/js/select2.min.js" />"></script>
<script src="<c:url value="/resources/assets/js/jquery.dataTables.min.js" />"></script>
<script src="<c:url value="/resources/assets/js/dataTables.bootstrap4.min.js" />"></script>
<script src="<c:url value="/resources/assets/js/moment.min.js" />"></script>
<script src="<c:url value="/resources/assets/js/bootstrap-datetimepicker.min.js" />"></script>
<script src="<c:url value="/resources/assets/js/app.js" />"></script>
<script src="<c:url value="/resources/assets/js/Chart.bundle.js" />"></script>
<script src="<c:url value="/resources/assets/js/chart.js" />"></script>
<script src="<c:url value="/resources/assets/js/fullcalendar.min.js" />"></script>
<script src="<c:url value="/resources/assets/js/jquery.fullcalendar.js" />"></script>
<script src="<c:url value="/resources/assets/js/tagsinput.js" />"></script>

</body>


</html>