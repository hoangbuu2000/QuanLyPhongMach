<%--
  Created by IntelliJ IDEA.
  User: Buu
  Date: 22/03/2021
  Time: 2:11 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<form:form action="/pojo/create" method="post" modelAttribute="bacsi">
    Ho: <form:input path="ho" />
    <br>Ten: <form:input path="ten" />
    <br>Gioi tinh: <form:input path="gioiTinh" />
    <br>Ngay sinh: <form:input path="ngaySinh" />
    <br>Dien thoai: <form:input path="dienThoai" />
    <br><input type="submit" value="Them" />
</form:form>
