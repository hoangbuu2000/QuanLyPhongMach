<%--
  Created by IntelliJ IDEA.
  User: Buu
  Date: 26/04/2021
  Time: 3:26 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="page-wrapper">
    <div class="content">
        <form action="/login" method="post">
            <form:errors path="*" element="div" cssClass="alert alert-danger" />
            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" id="username" name="username" class="from-control">
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" class="from-control">
            </div>
            <div class="form-group">
                <input type="submit" value="Login" class="btn btn-primary">
            </div>
        </form>
    </div>
</div>
