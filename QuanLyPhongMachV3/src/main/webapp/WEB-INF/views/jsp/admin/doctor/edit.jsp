<%--
  Created by IntelliJ IDEA.
  User: Buu
  Date: 31/03/2021
  Time: 2:45 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="page-wrapper" style="min-height: 754px;">
    <div class="content">
        <div class="row">
            <div class="col-lg-8 offset-lg-2">
                <h4 class="page-title"><spring:message code="edit.body.page.title" /></h4>
            </div>
        </div>
        <form:form method="post" action="/doctor/edit/${id}" modelAttribute="doctor" enctype="multipart/form-data">
            <p class="text-danger">${message}</p>
        <div class="row">
            <div class="col-lg-8 offset-lg-2">
                    <div class="row">
                        <div class="col-sm-6">
                            <div class="form-group">
                                <label><spring:message code="add.body.label.first" /> <span class="text-danger">*</span></label>
                                <form:input path="ten" cssClass="form-control" />
                                <form:errors path="ten" cssClass="text-danger" />
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div class="form-group">
                                <label><spring:message code="add.body.label.last" /></label>
                                <form:input path="ho" cssClass="form-control" />
                                <form:errors path="ho" cssClass="text-danger" />
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div class="form-group">
                                <label><spring:message code="add.body.label.user" /> <span class="text-danger">*</span></label>
                                <form:input path="username" cssClass="form-control" />
                                <form:errors path="username" cssClass="text-danger" />
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div class="form-group">
                                <label><spring:message code="add.body.label.email" /> <span class="text-danger">*</span></label>
                                <form:input path="email" type="email" cssClass="form-control" />
                                <form:errors path="email" cssClass="text-danger" />
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div class="form-group">
                                <label><spring:message code="add.body.label.pass" /></label>
                                <form:password path="password" cssClass="form-control" placeholder="" value="${doctor.password}" />
                                <form:errors path="password" cssClass="text-danger" />
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div class="form-group">
                                <label><spring:message code="add.body.label.confirm" /></label>
                                <form:password path="confirmPassword" cssClass="form-control" value="${doctor.confirmPassword}" />
                                <form:errors path="confirmPassword" cssClass="text-danger" />
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div class="form-group">
                                <label><spring:message code="add.body.label.dob" /></label>
                                <div class="cal-icon">
                                    <form:input path="ngaySinh" cssClass="form-control datetimepicker" />
                                    <form:errors path="ngaySinh" cssClass="text-danger" />
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div class="form-group gender-select">
                                <label class="gen-label"><spring:message code="add.body.label.gender" /></label>
                                <div class="form-check-inline">
                                    <label class="form-check-label">
                                        <form:radiobutton path="gioiTinh" value="Nam" cssClass="form-check-input" />
                                        <spring:message code="add.body.label.male" />
                                    </label>
                                </div>
                                <div class="form-check-inline">
                                    <label class="form-check-label">
                                        <form:radiobutton path="gioiTinh" value="Nữ" cssClass="form-check-input" />
                                        <spring:message code="add.body.label.female" />
                                    </label>
                                </div>
                                <form:errors path="gioiTinh" cssClass="text-danger" />
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div class="form-group">
                                <label><spring:message code="add.body.label.phone" /> </label>
                                <form:input path="dienThoai" cssClass="form-control" />
                                <form:errors path="dienThoai" cssClass="text-danger" />
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div class="form-group">
                                <label><spring:message code="add.body.label.avatar" /></label>
                                <div class="profile-upload">
                                    <div class="upload-img">
                                        <img alt="" src="<c:url value="${doctor.urlImg}" />" />
                                    </div>
                                    <div class="upload-input">
                                        <form:input path="image" cssClass="form-control" type="file" />
                                    </div>
                                </div>
                                <form:errors path="image" cssClass="text-danger" />
                            </div>
                        </div>
                        <div class="col-sm-12">
                            <div class="row">
                                <div class="col-sm-6 col-md-6 col-lg-3">
                                    <div class="form-group">
                                        <label><spring:message code="add.body.label.hometown" /></label>
                                        <form:select path="queQuan" cssClass="form-control select">
                                            <form:option value="Tien Giang" />
                                            <form:option value="Dong Thap" />
                                        </form:select>
                                        <form:errors path="queQuan" cssClass="text-danger" />
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="display-block"><spring:message code="add.body.label.status" /></label>
                        <div class="form-check form-check-inline">
                            <form:radiobutton path="active" cssClass="form-check-input" value="1"
                                              id="doctor_active" name="status" checked="true"/>
                            <label class="form-check-label" for="doctor_active">
                                <spring:message code="add.body.label.active" />
                            </label>
                        </div>
                        <div class="form-check form-check-inline">
                            <form:radiobutton path="active" cssClass="form-check-input" value="0"
                                              id="doctor_inactive" name="status"/>
                            <label class="form-check-label" for="doctor_inactive">
                                <spring:message code="add.body.label.inactive" />
                            </label>
                        </div>
                        <form:errors path="active" cssClass="text-danger" />
                    </div>
                    <div class="m-t-20 text-center">
                        <button class="btn btn-primary submit-btn"><spring:message code="edit.body.submit" /></button>
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
            <div class="slimScrollDiv" style="position: relative; overflow: hidden; width: auto; height: 630px;"><div class="drop-scroll msg-list-scroll" id="msg_list" style="overflow: hidden; width: auto; height: 630px;">
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
            </div><div class="slimScrollBar" style="background: rgb(135, 135, 135); width: 4px; position: absolute; top: 0px; opacity: 0.4; display: none; border-radius: 0px; z-index: 99; right: 1px; height: 809px;"></div><div class="slimScrollRail" style="width: 4px; height: 100%; position: absolute; top: 0px; display: none; border-radius: 7px; background: rgb(51, 51, 51); opacity: 0.2; z-index: 90; right: 1px;"></div></div>
            <div class="topnav-dropdown-footer">
                <a href="chat.html">See all messages</a>
            </div>
        </div>
    </div>
</div>

<script>
    let pass = document.querySelector("#password");
    pass.setAttribute("placeholder", "<spring:message code="add.body.pass.place" />" );
</script>