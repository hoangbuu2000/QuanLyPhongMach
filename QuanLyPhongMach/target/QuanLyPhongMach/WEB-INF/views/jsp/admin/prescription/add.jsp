<%--
  Created by IntelliJ IDEA.
  User: Buu
  Date: 30/03/2021
  Time: 11:55 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="page-wrapper">
    <div class="content">
        <div class="row">
            <div class="col-lg-8 offset-lg-2">
                <h4 class="page-title"><spring:message code="prescription.add.page.title" /></h4>
            </div>
        </div>
        <form:form modelAttribute="prescription" action="/prescription/add" method="post">
            <div class="row">
                <div class="col-lg-8 offset-lg-2">
                    <div class="row">
                        <div class="col-sm-6">
                            <div class="form-group">
                                <label><spring:message code="prescription.add.label.first" /> <span class="text-danger">*</span></label>
                                <form:select path="doctor" items="${doctors}" itemLabel="ten" itemValue="id" cssClass="form-control select" />
                                <form:errors path="doctor" cssClass="text-danger" />
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div class="form-group">
                                <label><spring:message code="prescription.add.label.last" /></label>
                                <form:select path="patient" items="${patients}" itemLabel="ten" itemValue="id" cssClass="form-control select" />
                                <form:errors path="patient" cssClass="text-danger" />
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div class="form-group">
                                <label><spring:message code="prescription.add.label.age" /></label>
                                <form:select path="disease" items="${diseases}" itemValue="id" itemLabel="tenBenh" cssClass="form-control select" />
                                <form:errors path="disease" cssClass="text-danger" />
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div class="form-group">
                                <label><spring:message code="prescription.add.label.dob" /></label>
                                <div class="cal-icon">
                                    <form:input path="date" cssClass="form-control datetimepicker" />
                                </div>
                                <form:errors path="date" cssClass="text-danger" />
                            </div>
                        </div>

                        <table class="table">
                            <thead>
                            <tr>
                                <td>STT</td>
                                <td>Ten thuoc</td>
                                <td>So luong</td>
                                <td>Don vi</td>
                                <td>Don gia</td>
                                <td>Thanh tien</td>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td id="stt">

                                </td>
                                <td>
                                    <select onchange="getUnitAndPrice(this)" class="medicine" class="form-control select">
                                        <option value="0" selected>Chon thuoc</option>
                                        <c:forEach items="${medicines}" var="m">
                                            <option value="${m.id}">${m.tenThuoc}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td id="td_quantity1">
                                    <input onchange="tinhTien(this)" class="quantity" type="number" class="form-control" />
                                </td>
                                <td class="unit"></td>
                                <td class="price"></td>
                                <td class="total"></td>
                            </tr>
                            <tr>
                                <td class="stt">

                                </td>
                                <td>
                                    <select onchange="getUnitAndPrice(this)" class="medicine" class="form-control select">
                                        <option value="0" selected>Chon thuoc</option>
                                        <c:forEach items="${medicines}" var="m">
                                            <option value="${m.id}">${m.tenThuoc}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td id="td_quantity2">
                                    <input onchange="tinhTien(this)" class="quantity" type="number" class="form-control" />
                                </td>
                                <td class="unit"></td>
                                <td class="price"></td>
                                <td class="total"></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="m-t-20 text-center">
                        <button class="btn btn-primary submit-btn"><spring:message code="prescription.add.form.submit" /></button>
                    </div>
                </div>
            </div>
        </form:form>
    </div>
    <div class="notification-box">
        <div class="msg-sidebar notifications msg-noti">
            <div class="topnav-dropdown-header">
                <span>Messages</span>
            </div>
            <div class="drop-scroll msg-list-scroll" id="msg_list">
                <ul class="list-box">
                    <li>
                        <a href="chat.html">
                            <div class="list-item">
                                <div class="list-left">
                                    <span class="avatar">R</span>
                                </div>
                                <div class="list-body">
                                    <span class="message-author">Richard Miles </span>
                                    <span class="message-time">12:28 AM</span>
                                    <div class="clearfix"></div>
                                    <span class="message-content">Lorem ipsum dolor sit amet, consectetur adipiscing</span>
                                </div>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a href="chat.html">
                            <div class="list-item new-message">
                                <div class="list-left">
                                    <span class="avatar">J</span>
                                </div>
                                <div class="list-body">
                                    <span class="message-author">John Doe</span>
                                    <span class="message-time">1 Aug</span>
                                    <div class="clearfix"></div>
                                    <span class="message-content">Lorem ipsum dolor sit amet, consectetur adipiscing</span>
                                </div>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a href="chat.html">
                            <div class="list-item">
                                <div class="list-left">
                                    <span class="avatar">T</span>
                                </div>
                                <div class="list-body">
                                    <span class="message-author"> Tarah Shropshire </span>
                                    <span class="message-time">12:28 AM</span>
                                    <div class="clearfix"></div>
                                    <span class="message-content">Lorem ipsum dolor sit amet, consectetur adipiscing</span>
                                </div>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a href="chat.html">
                            <div class="list-item">
                                <div class="list-left">
                                    <span class="avatar">M</span>
                                </div>
                                <div class="list-body">
                                    <span class="message-author">Mike Litorus</span>
                                    <span class="message-time">12:28 AM</span>
                                    <div class="clearfix"></div>
                                    <span class="message-content">Lorem ipsum dolor sit amet, consectetur adipiscing</span>
                                </div>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a href="chat.html">
                            <div class="list-item">
                                <div class="list-left">
                                    <span class="avatar">C</span>
                                </div>
                                <div class="list-body">
                                    <span class="message-author"> Catherine Manseau </span>
                                    <span class="message-time">12:28 AM</span>
                                    <div class="clearfix"></div>
                                    <span class="message-content">Lorem ipsum dolor sit amet, consectetur adipiscing</span>
                                </div>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a href="chat.html">
                            <div class="list-item">
                                <div class="list-left">
                                    <span class="avatar">D</span>
                                </div>
                                <div class="list-body">
                                    <span class="message-author"> Domenic Houston </span>
                                    <span class="message-time">12:28 AM</span>
                                    <div class="clearfix"></div>
                                    <span class="message-content">Lorem ipsum dolor sit amet, consectetur adipiscing</span>
                                </div>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a href="chat.html">
                            <div class="list-item">
                                <div class="list-left">
                                    <span class="avatar">B</span>
                                </div>
                                <div class="list-body">
                                    <span class="message-author"> Buster Wigton </span>
                                    <span class="message-time">12:28 AM</span>
                                    <div class="clearfix"></div>
                                    <span class="message-content">Lorem ipsum dolor sit amet, consectetur adipiscing</span>
                                </div>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a href="chat.html">
                            <div class="list-item">
                                <div class="list-left">
                                    <span class="avatar">R</span>
                                </div>
                                <div class="list-body">
                                    <span class="message-author"> Rolland Webber </span>
                                    <span class="message-time">12:28 AM</span>
                                    <div class="clearfix"></div>
                                    <span class="message-content">Lorem ipsum dolor sit amet, consectetur adipiscing</span>
                                </div>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a href="chat.html">
                            <div class="list-item">
                                <div class="list-left">
                                    <span class="avatar">C</span>
                                </div>
                                <div class="list-body">
                                    <span class="message-author"> Claire Mapes </span>
                                    <span class="message-time">12:28 AM</span>
                                    <div class="clearfix"></div>
                                    <span class="message-content">Lorem ipsum dolor sit amet, consectetur adipiscing</span>
                                </div>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a href="chat.html">
                            <div class="list-item">
                                <div class="list-left">
                                    <span class="avatar">M</span>
                                </div>
                                <div class="list-body">
                                    <span class="message-author">Melita Faucher</span>
                                    <span class="message-time">12:28 AM</span>
                                    <div class="clearfix"></div>
                                    <span class="message-content">Lorem ipsum dolor sit amet, consectetur adipiscing</span>
                                </div>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a href="chat.html">
                            <div class="list-item">
                                <div class="list-left">
                                    <span class="avatar">J</span>
                                </div>
                                <div class="list-body">
                                    <span class="message-author">Jeffery Lalor</span>
                                    <span class="message-time">12:28 AM</span>
                                    <div class="clearfix"></div>
                                    <span class="message-content">Lorem ipsum dolor sit amet, consectetur adipiscing</span>
                                </div>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a href="chat.html">
                            <div class="list-item">
                                <div class="list-left">
                                    <span class="avatar">L</span>
                                </div>
                                <div class="list-body">
                                    <span class="message-author">Loren Gatlin</span>
                                    <span class="message-time">12:28 AM</span>
                                    <div class="clearfix"></div>
                                    <span class="message-content">Lorem ipsum dolor sit amet, consectetur adipiscing</span>
                                </div>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a href="chat.html">
                            <div class="list-item">
                                <div class="list-left">
                                    <span class="avatar">T</span>
                                </div>
                                <div class="list-body">
                                    <span class="message-author">Tarah Shropshire</span>
                                    <span class="message-time">12:28 AM</span>
                                    <div class="clearfix"></div>
                                    <span class="message-content">Lorem ipsum dolor sit amet, consectetur adipiscing</span>
                                </div>
                            </div>
                        </a>
                    </li>
                </ul>
            </div>
            <div class="topnav-dropdown-footer">
                <a href="chat.html">See all messages</a>
            </div>
        </div>
    </div>
</div>

<script>
    function getUnitAndPrice(obj) {
        let thuocId = obj.value;

        $.getJSON("/prescription/api/getunit?id=" + thuocId).done(function (task) {
            console.log("Unit: ", JSON.stringify(task));
            $('.' + obj.parent().id + ' + td.total').innerHTML = task;
        });

        $.getJSON("/prescription/api/getprice?id=" + thuocId).done(function (task) {
            console.log("Price: ", JSON.stringify(task));
            $('.' + obj.parent().id + ' + td.total').innerHTML = task;
        })
    }

    function tinhTien(obj) {
        let quantity = obj.value;
        let unitprice = document.querySelector('.' + obj.parent().id + ' + td.price');

        $('.' + obj.parent().id + ' + td.total').innerHTML = parseInt(quantity) * parseInt(unitprice);

    }

</script>
