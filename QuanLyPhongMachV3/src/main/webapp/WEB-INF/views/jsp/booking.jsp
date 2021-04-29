<%--
  Created by IntelliJ IDEA.
  User: Buu
  Date: 28/04/2021
  Time: 7:35 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test='${result != ""}'>
    <div style="margin-top: 200px">
        <h3 class="text-center text-danger">Thank you for booking</h3>
    </div>
</c:if>
