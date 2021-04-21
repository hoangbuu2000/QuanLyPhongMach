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
                <h4 class="page-title"><spring:message code="patient.add.page.title" /></h4>
            </div>
        </div>
        <form:form modelAttribute="patient" action="/appointment/add" method="post">
            <div class="row">
                <div class="col-lg-8 offset-lg-2">
                    <div class="row">
                        <div class="col-sm-6">
                            <div class="form-group">
                                <label><spring:message code="patient.add.label.first" /> <span class="text-danger">*</span></label>
                                <form:input path="ten" cssClass="form-control" />
                                <form:errors path="ten" cssClass="text-danger" />
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div class="form-group">
                                <label><spring:message code="patient.add.label.last" /></label>
                                <form:input path="ho" cssClass="form-control" />
                                <form:errors path="ho" cssClass="text-danger" />
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div class="form-group">
                                <label><spring:message code="patient.add.label.age" /></label>
                                <form:input path="tuoi" cssClass="form-control" type="number" />
                                <form:errors path="tuoi" cssClass="text-danger" />
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div class="form-group">
                                <label><spring:message code="patient.add.label.email" /> <span class="text-danger">*</span></label>
                                <form:input path="email" type="email" cssClass="form-control" />
                                <form:errors path="email" cssClass="text-danger" />
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div class="form-group">
                                <label><spring:message code="patient.add.label.dob" /></label>
                                <div class="cal-icon">
                                    <form:input path="ngaySinh" cssClass="form-control datetimepicker" />
                                </div>
                                <form:errors path="ngaySinh" cssClass="text-danger" />
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div class="form-group gender-select">
                                <label class="gen-label"><spring:message code="patient.add.label.gender" /></label>
                                <div class="form-check-inline">
                                    <label class="form-check-label">
                                        <form:radiobutton path="gioiTinh" value="Nam"
                                                          name="gender" cssClass="form-check-input" checked="true" />
                                        <spring:message code="patient.add.label.gender.male" />
                                    </label>
                                </div>
                                <div class="form-check-inline">
                                    <label class="form-check-label">
                                        <form:radiobutton path="gioiTinh" value="Nữ"
                                                          name="gender" cssClass="form-check-input" />
                                        <spring:message code="patient.add.label.gender.female" />
                                    </label>
                                </div>
                                <form:errors path="gioiTinh" cssClass="text-danger" />
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div class="form-group">
                                <label><spring:message code="patient.add.label.phone" /> </label>
                                <form:input path="dienThoai" cssClass="form-control" />
                                <form:errors path="dienThoai" cssClass="text-danger" />
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div class="form-group">
                                <label><spring:message code="patient.add.label.appointment" /></label>
                                <div class="cal-icon">
                                    <form:input onchange="pickDate(this)" onmouseout="pickDate(this)" path="ngayKham" cssClass="form-control datetimepicker" />
                                </div>
                                <form:errors path="ngayKham" cssClass="text-danger" />
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div class="form-group">
                                <label><spring:message code="patient.add.label.shift" /></label>
                                <form:select onchange="getJson()" path="caKham" cssClass="form-control select">
                                    <form:option value="" label="Choose your shift" />
                                </form:select>
                                <form:errors path="caKham" cssClass="text-danger" />
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div class="form-group">
                                <label><spring:message code="patient.add.label.doctor" /></label>
                                <form:select path="bacSi" cssClass="form-control select">

                                </form:select>
                                <form:errors path="bacSi" cssClass="text-danger" />
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div class="form-group">
                                <label><spring:message code="patient.add.label.disease" /></label>
                                <form:select multiple="true" path="loaiBenhList" cssClass="form-control select">
                                    <c:forEach items="${danhSachLoaiBenh}" var="benh">
                                        <form:option value="${benh.id}" label="${benh.tenBenh}" title="${benh.moTa}" />
                                    </c:forEach>
                                </form:select>
                                <form:errors path="loaiBenhList" cssClass="text-danger" />
                            </div>
                        </div>
                        <div class="col-sm-12 form-group">
                            <label class="display-block"><spring:message code="patient.add.label.payment" /></label>
                            <div class="form-check form-check-inline">
                                <form:radiobutton path="thanhToan" cssClass="form-check-input" value="true"
                                                  id="patient_paid" name="payment" checked="true"/>
                                <label class="form-check-label" for="patient_paid">
                                    <spring:message code="patient.add.label.payment.now" />
                                </label>
                            </div>
                            <div class="form-check form-check-inline">
                                <form:radiobutton path="thanhToan" cssClass="form-check-input" value="false"
                                                  id="patient_notpaid" name="payment"/>
                                <label class="form-check-label" for="patient_notpaid">
                                    <spring:message code="patient.add.label.payment.later" />
                                </label>
                            </div>
                            <form:errors path="thanhToan" cssClass="text-danger" />
                        </div>
                    </div>
                    <div class="m-t-20 text-center">
                        <button class="btn btn-primary submit-btn"><spring:message code="patient.add.form.submit" /></button>
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
    function pickDate(obj) {
        if (obj.value != "" && moment(obj.value, "DD/MM/YYYY").isValid()) {
            let caKham = document.querySelector("select[name=caKham]");
            caKham.options.length = 0;

            let ngayKham = $("#ngayKham").val();

            getJson1(caKham, ngayKham);
        }
    }

    function getJson() {
        let ngayKham = $("#ngayKham").val();
        let shift = $("#caKham").val();
        let bacSi = document.querySelector("select[name=bacSi]");
        bacSi.options.length = 0;

        $.getJSON("/patient/ajax?date=" + ngayKham + "&shift=" + shift).done(function (task) {
            console.log("DONE: ", JSON.stringify(task));
            let ds = task;
            for(let i = 0; i < ds.length; i++) {
                console.log(ds[i].id);
                bacSi.append(new Option(ds[i].ten, ds[i].id));
            }
        });
    }

    function getJson1(caKham, ngayKham) {
        caKham.append(new Option("Choose your shift", ""));
        $.getJSON("/patient/ajax1?date=" + ngayKham).done(function (task) {
            console.log("DONE: ", JSON.stringify(task));
            let ds = task;
            for(let i = 0; i < ds.length; i++) {
                console.log(ds[i].id);
                caKham.append(new Option(ds[i].tenCa, ds[i].id));
            }
        });

        if (caKham.options.length > 0) {
            getJson();
        }
    }

</script>
