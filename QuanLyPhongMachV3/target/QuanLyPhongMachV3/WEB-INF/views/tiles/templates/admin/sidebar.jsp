<%--
  Created by IntelliJ IDEA.
  User: Buu
  Date: 14/03/2021
  Time: 4:28 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="url" value="${requestScope['javax.servlet.forward.request_uri']}" />
<div class="sidebar" id="sidebar">
    <div class="sidebar-inner slimscroll">
        <div id="sidebar-menu" class="sidebar-menu">
            <ul>
                <c:if test='${url == "/admin"}'>
                    <c:set value="active" var="dashboard"/>
                </c:if>
                <c:if test='${url.contains("admin-management")}'>
                    <c:set value="active" var="admin"/>
                </c:if>
                <c:if test='${url.contains("doctor")}'>
                    <c:set value="active" var="doctor"/>
                </c:if>
                <c:if test='${url.contains("patient")}'>
                    <c:set value="active" var="patient"/>
                </c:if>
                <c:if test='${url.contains("appointment")}'>
                    <c:set value="active" var="appointment"/>
                </c:if>
                <c:if test='${url.contains("schedule")}'>
                    <c:set value="active" var="schedule"/>
                </c:if>
                <c:if test='${url.contains("disease")}'>
                    <c:set value="active" var="disease"/>
                </c:if>
                <c:if test='${url.contains("medicine")}'>
                    <c:set value="active" var="medicine"/>
                </c:if>
                <c:if test='${url.contains("prescription")}'>
                    <c:set value="active" var="prescription"/>
                </c:if>
                <c:if test='${url.contains("invoice")}'>
                    <c:set value="active" var="invoice"/>
                </c:if>
                <c:if test='${url.contains("employee")}'>
                    <c:set value="active" var="employee"/>
                </c:if>
                <c:choose>
                    <c:when test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
                        <li class="menu-title">Admin</li>
                        <li class="${dashboard}">
                            <a href="<c:url value="/admin" />"><i class="fa fa-dashboard"></i> <span>Dashboard</span></a>
                        </li>
                        <li class="${admin}">
                            <a href="<c:url value="/admin/admin-management" />"><i class="fa fa-star"></i> <span>Admin</span></a>
                        </li>
                        <li class="${doctor}">
                            <a href="<c:url value="/admin/doctor" />"><i class="fa fa-user-md"></i> <span>Doctors</span></a>
                        </li>
                        <li class="${patient}">
                            <a href="<c:url value="/admin/patient" />"><i class="fa fa-wheelchair"></i> <span>Patients</span></a>
                        </li>
                        <li class="${appointment}">
                            <a href="<c:url value="/admin/appointment" />"><i class="fa fa-calendar"></i> <span>Appointments</span></a>
                        </li>
                        <li class="${schedule}">
                            <a href="<c:url value="/admin/schedule" />"><i class="fa fa-calendar-check-o"></i> <span>Doctor Schedule</span></a>
                        </li>
                        <li class="${disease}">
                            <a href="<c:url value="/admin/disease" />"><i class="fa fa-deaf"></i> <span>Type of Disease</span></a>
                        </li>
                        <li class="${medicine}">
                            <a href="<c:url value="/admin/medicine" />"><i class="fa fa-eject"></i> <span>Medicines</span></a>
                        </li>
                        <li class="${prescription}">
                            <a href="<c:url value="/admin/prescription" />"><i class="fa fa-paypal"></i> <span>Prescription</span></a>
                        </li>
                        <li class="${invoice}">
                            <a href="<c:url value="/admin/invoice" />"><i class="fa fa-hospital-o"></i> <span>Invoice</span></a>
                        </li>
                        <li class="${employee}">
                            <a href="<c:url value="/admin/employee" />"><i class="fa fa-user-plus"></i> <span>Employees List</span></a>
                        </li>
                        <%--                <li class="submenu">--%>
                        <%--                    <a href="#"><i class="fa fa-user"></i> <span> Employees </span> <span class="menu-arrow"></span></a>--%>
                        <%--                    <ul style="display: none;">--%>
                        <%--                        <li class="${employeeAct}"><a href="<c:url value="/employee" />">Employees List</a></li>--%>
                        <%--                        <li><a href="leaves.html">Leaves</a></li>--%>
                        <%--                        <li><a href="holidays.html">Holidays</a></li>--%>
                        <%--                        <li><a href="attendance.html">Attendance</a></li>--%>
                        <%--                    </ul>--%>
                        <%--                </li>--%>
                        <li class="submenu">
                            <a href="#"><i class="fa fa-money"></i> <span> Accounts </span> <span class="menu-arrow"></span></a>
                            <ul style="display: none;">
                                <li><a href="invoices.html">Invoices</a></li>
                                <li><a href="payments.html">Payments</a></li>
                                <li><a href="expenses.html">Expenses</a></li>
                                <li><a href="taxes.html">Taxes</a></li>
                                <li><a href="provident-fund.html">Provident Fund</a></li>
                            </ul>
                        </li>
                        <li class="submenu">
                            <a href="#"><i class="fa fa-book"></i> <span> Payroll </span> <span class="menu-arrow"></span></a>
                            <ul style="display: none;">
                                <li><a href="salary.html"> Employee Salary </a></li>
                                <li><a href="salary-view.html"> Payslip </a></li>
                            </ul>
                        </li>
                        <li>
                            <a href="chat.html"><i class="fa fa-comments"></i> <span>Chat</span> <span class="badge badge-pill bg-primary float-right">5</span></a>
                        </li>
                        <li class="submenu">
                            <a href="#"><i class="fa fa-video-camera camera"></i> <span> Calls</span> <span class="menu-arrow"></span></a>
                            <ul style="display: none;">
                                <li><a href="voice-call.html">Voice Call</a></li>
                                <li><a href="video-call.html">Video Call</a></li>
                                <li><a href="incoming-call.html">Incoming Call</a></li>
                            </ul>
                        </li>
                        <li class="submenu">
                            <a href="#"><i class="fa fa-envelope"></i> <span> Email</span> <span class="menu-arrow"></span></a>
                            <ul style="display: none;">
                                <li><a href="compose.html">Compose Mail</a></li>
                                <li><a href="inbox.html">Inbox</a></li>
                                <li><a href="mail-view.html">Mail View</a></li>
                            </ul>
                        </li>
                        <li class="submenu">
                            <a href="#"><i class="fa fa-commenting-o"></i> <span> Blog</span> <span class="menu-arrow"></span></a>
                            <ul style="display: none;">
                                <li><a href="blog.html">Blog</a></li>
                                <li><a href="blog-details.html">Blog View</a></li>
                                <li><a href="add-blog.html">Add Blog</a></li>
                                <li><a href="edit-blog.html">Edit Blog</a></li>
                            </ul>
                        </li>
                        <li>
                            <a href="assets.html"><i class="fa fa-cube"></i> <span>Assets</span></a>
                        </li>
                        <li>
                            <a href="activities.html"><i class="fa fa-bell-o"></i> <span>Activities</span></a>
                        </li>
                        <li class="submenu">
                            <a href="#"><i class="fa fa-flag-o"></i> <span> Reports </span> <span class="menu-arrow"></span></a>
                            <ul style="display: none;">
                                <li><a href="expense-reports.html"> Expense Report </a></li>
                                <li><a href="invoice-reports.html"> Invoice Report </a></li>
                            </ul>
                        </li>
                        <li>
                            <a href="settings.html"><i class="fa fa-cog"></i> <span>Settings</span></a>
                        </li>
                        <li class="menu-title">UI Elements</li>
                        <li class="submenu">
                            <a href="#"><i class="fa fa-laptop"></i> <span> Components</span> <span class="menu-arrow"></span></a>
                            <ul style="display: none;">
                                <li><a href="uikit.html">UI Kit</a></li>
                                <li><a href="typography.html">Typography</a></li>
                                <li><a href="tabs.html">Tabs</a></li>
                            </ul>
                        </li>
                        <li class="submenu">
                            <a href="#"><i class="fa fa-edit"></i> <span> Forms</span> <span class="menu-arrow"></span></a>
                            <ul style="display: none;">
                                <li><a href="form-basic-inputs.html">Basic Inputs</a></li>
                                <li><a href="form-input-groups.html">Input Groups</a></li>
                                <li><a href="form-horizontal.html">Horizontal Form</a></li>
                                <li><a href="form-vertical.html">Vertical Form</a></li>
                            </ul>
                        </li>
                        <li class="submenu">
                            <a href="#"><i class="fa fa-table"></i> <span> Tables</span> <span class="menu-arrow"></span></a>
                            <ul style="display: none;">
                                <li><a href="tables-basic.html">Basic Tables</a></li>
                                <li><a href="tables-datatables.html">Data Table</a></li>
                            </ul>
                        </li>
                        <li>
                            <a href="calendar.html"><i class="fa fa-calendar"></i> <span>Calendar</span></a>
                        </li>
                        <li class="menu-title">Extras</li>
                        <li class="submenu">
                            <a href="#"><i class="fa fa-columns"></i> <span>Pages</span> <span class="menu-arrow"></span></a>
                            <ul style="display: none;">
                                <li><a href="login.html"> Login </a></li>
                                <li><a href="register.html"> Register </a></li>
                                <li><a href="forgot-password.html"> Forgot Password </a></li>
                                <li><a href="change-password2.html"> Change Password </a></li>
                                <li><a href="lock-screen.html"> Lock Screen </a></li>
                                <li><a href="profile.html"> Profile </a></li>
                                <li><a href="gallery.html"> Gallery </a></li>
                                <li><a href="error-404.html">404 Error </a></li>
                                <li><a href="error-500.html">500 Error </a></li>
                                <li><a href="blank-page.html"> Blank Page </a></li>
                            </ul>
                        </li>
                        <li class="submenu">
                            <a href="javascript:void(0);"><i class="fa fa-share-alt"></i> <span>Multi Level</span> <span class="menu-arrow"></span></a>
                            <ul style="display: none;">
                                <li class="submenu">
                                    <a href="javascript:void(0);"><span>Level 1</span> <span class="menu-arrow"></span></a>
                                    <ul style="display: none;">
                                        <li><a href="javascript:void(0);"><span>Level 2</span></a></li>
                                        <li class="submenu">
                                            <a href="javascript:void(0);"> <span> Level 2</span> <span class="menu-arrow"></span></a>
                                            <ul style="display: none;">
                                                <li><a href="javascript:void(0);">Level 3</a></li>
                                                <li><a href="javascript:void(0);">Level 3</a></li>
                                            </ul>
                                        </li>
                                        <li><a href="javascript:void(0);"><span>Level 2</span></a></li>
                                    </ul>
                                </li>
                                <li>
                                    <a href="javascript:void(0);"><span>Level 1</span></a>
                                </li>
                            </ul>
                        </li>
                    </c:when>
                    <c:when test="${pageContext.request.isUserInRole('ROLE_DOCTOR')}">
                        <li class="menu-title">Doctor</li>
                        <li class="${dashboard}">
                            <a href="<c:url value="/admin" />"><i class="fa fa-dashboard"></i> <span>Dashboard</span></a>
                        </li>
                        <li class="${patient}">
                            <a href="<c:url value="/admin/patient" />"><i class="fa fa-wheelchair"></i> <span>Patients</span></a>
                        </li>
                        <li class="${appointment}">
                            <a href="<c:url value="/admin/appointment" />"><i class="fa fa-calendar"></i> <span>Appointments</span></a>
                        </li>
                        <li class="${schedule}">
                            <a href="<c:url value="/admin/schedule" />"><i class="fa fa-calendar-check-o"></i> <span>Doctor Schedule</span></a>
                        </li>
                        <li class="${prescription}">
                            <a href="<c:url value="/admin/prescription" />"><i class="fa fa-paypal"></i> <span>Prescription</span></a>
                        </li>
                    </c:when>
                    <c:when test="${pageContext.request.isUserInRole('ROLE_EMPLOYEE')}">
                        <li class="menu-title">Employee</li>
                        <li class="${dashboard}">
                            <a href="<c:url value="/admin" />"><i class="fa fa-dashboard"></i> <span>Dashboard</span></a>
                        </li>
                        <li class="${patient}">
                            <a href="<c:url value="/admin/patient" />"><i class="fa fa-wheelchair"></i> <span>Patients</span></a>
                        </li>
                        <li class="${appointment}">
                            <a href="<c:url value="/admin/appointment" />"><i class="fa fa-calendar"></i> <span>Appointments</span></a>
                        </li>
                        <li class="${invoice}">
                            <a href="<c:url value="/admin/invoice" />"><i class="fa fa-hospital-o"></i> <span>Invoice</span></a>
                        </li>
                    </c:when>
                </c:choose>
                <li>
                    <a href="<c:url value="/logout" />"><i class="fa fa-sign-out"></i></a>
                </li>
            </ul>
        </div>
    </div>
</div>
