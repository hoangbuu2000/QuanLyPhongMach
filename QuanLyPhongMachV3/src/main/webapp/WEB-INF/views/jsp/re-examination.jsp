<%--
  Created by IntelliJ IDEA.
  User: Buu
  Date: 29/04/2021
  Time: 8:32 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="page-wrapper" style="padding-top: 155px">
    <div class="content">
        <form:form method="post" role="form" cssClass="lead" modelAttribute="patient">
            <c:if test="${message != null}">
                <p class="alert alert-danger">${message}</p>
            </c:if>
            <div class="row">
                <div class="col-xs-6 col-sm-6 col-md-6">
                    <div class="form-group">
                        <label>First Name</label>
                        <form:input path="ten" cssClass="form-control input-md" />
                        <form:errors path="ten" cssClass="text-danger" />
                            <%--                                                    <input type="text" name="first_name" id="first_name" class="form-control input-md">--%>
                    </div>
                </div>
                <div class="col-xs-6 col-sm-6 col-md-6">
                    <div class="form-group">
                        <label>Last Name</label>
                        <form:input path="ho" cssClass="form-control input-md" />
                        <form:errors path="ho" cssClass="text-danger" />
                            <%--                                                    <input type="text" name="last_name" id="last_name" class="form-control input-md">--%>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-xs-6 col-sm-6 col-md-6">
                    <div class="form-group">
                        <label>Tuoi</label>
                        <form:input type="number" path="tuoi" cssClass="form-control input-md" />
                        <form:errors path="tuoi" cssClass="text-danger" />
                            <%--                                                    <input type="email" name="email" id="email" class="form-control input-md">--%>
                    </div>
                </div>
                <div class="col-xs-6 col-sm-6 col-md-6">
                    <div class="form-group">
                        <label>Email</label>
                        <form:input type="email" path="email" cssClass="form-control input-md" />
                        <form:errors path="email" cssClass="text-danger" />
                            <%--                                                    <input type="text" name="phone" id="phone" class="form-control input-md">--%>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-xs-6 col-sm-6 col-md-6">
                    <div class="form-group">
                        <label>DOB</label>
                        <div class="cal-icon">
                            <form:input path="ngaySinh" cssClass="form-control datetimepicker" />
                        </div>
                        <form:errors path="ngaySinh" cssClass="text-danger" />
                            <%--                                                    <input type="email" name="email" id="email" class="form-control input-md">--%>
                    </div>
                </div>
                <div class="col-xs-6 col-sm-6 col-md-6">
                    <div class="form-group">
                        <label>Gender</label>
                        <div class="form-check-inline">
                            <label class="form-check-label">
                                <form:radiobutton path="gioiTinh" value="Nam"
                                                  name="gender" cssClass="form-check-input" checked="true" />
                                Male
                            </label>
                        </div>
                        <div class="form-check-inline">
                            <label class="form-check-label">
                                <form:radiobutton path="gioiTinh" value="Nữ"
                                                  name="gender" cssClass="form-check-input" />
                                Female
                            </label>
                        </div>
                        <form:errors path="gioiTinh" cssClass="text-danger" />
                            <%--                                                    <input type="text" name="phone" id="phone" class="form-control input-md">--%>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-xs-6 col-sm-6 col-md-6">
                    <div class="form-group">
                        <label>Phone number</label>
                        <form:input path="dienThoai" cssClass="form-control input-md" />
                        <form:errors path="dienThoai" cssClass="text-danger" />
                            <%--                                                    <input type="email" name="email" id="email" class="form-control input-md">--%>
                    </div>
                </div>
                <div class="col-xs-6 col-sm-6 col-md-6">
                    <div class="form-group">
                        <label>Date</label>
                        <div class="cal-icon">
                            <form:input onchange="pickDate(this)" onmouseout="pickDate(this)" path="ngayKham" cssClass="form-control datetimepicker" />
                        </div>
                        <form:errors path="ngayKham" cssClass="text-danger" />
                            <%--                                                    <input type="text" name="phone" id="phone" class="form-control input-md">--%>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-xs-6 col-sm-6 col-md-6">
                    <div class="form-group">
                        <label>Shift</label>
                        <form:select onchange="getJson()" path="caKham" cssClass="form-control select">
                            <form:option value="" label="Choose your shift" />
                        </form:select>
                        <form:errors path="caKham" cssClass="text-danger" />
                            <%--                                                    <input type="email" name="email" id="email" class="form-control input-md">--%>
                    </div>
                </div>
                <div class="col-xs-6 col-sm-6 col-md-6">
                    <div class="form-group">
                        <label>Doctor</label>
                        <form:select path="bacSi" cssClass="form-control select">
                            <form:option value="" label="Choose your doctor" />
                        </form:select>
                        <form:errors path="bacSi" cssClass="text-danger" />
                            <%--                                                    <input type="text" name="phone" id="phone" class="form-control input-md">--%>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-xs-6 col-sm-6 col-md-6">
                    <div class="form-group">
                        <label>Disease</label>
                        <form:select multiple="true" path="loaiBenhList" cssClass="form-control select">
                            <c:forEach items="${danhSachLoaiBenh}" var="benh">
                                <form:option value="${benh.id}" label="${benh.tenBenh}" title="${benh.moTa}" />
                            </c:forEach>
                        </form:select>
                        <form:errors path="loaiBenhList" cssClass="text-danger" />
                            <%--                                                    <input type="text" name="phone" id="phone" class="form-control input-md">--%>
                    </div>
                </div>
                <div class="col-xs-6 col-sm-6 col-md-6">
                    <div class="form-group">
                        <label>Payment</label>
                        <div class="form-check form-check-inline">
                            <form:radiobutton path="thanhToan" cssClass="form-check-input" value="true"
                                              id="patient_paid" name="payment" checked="true"/>
                            <label class="form-check-label" for="patient_paid">
                                Pay now
                            </label>
                        </div>
                        <div class="form-check form-check-inline">
                            <form:radiobutton path="thanhToan" cssClass="form-check-input" value="false"
                                              id="patient_notpaid" name="payment"/>
                            <label class="form-check-label" for="patient_notpaid">
                                Pay later
                            </label>
                        </div>
                        <form:errors path="thanhToan" cssClass="text-danger" />
                            <%--                                                    <input type="email" name="email" id="email" class="form-control input-md">--%>
                    </div>
                </div>
            </div>

            <input type="submit" value="Submit" class="btn btn-skin btn-block btn-lg">

            <p class="lead-footer">* We'll contact you by phone & email later</p>

        </form:form>
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

        bacSi.append(new Option("Choose your doctor", ""));
        $.getJSON("/api/ajax?date=" + ngayKham + "&shift=" + shift).done(function (task) {
            console.log("DONE: ", JSON.stringify(task));
            let ds = task;
            for (let i = 0; i < ds.length; i++) {
                console.log(ds[i].id);
                bacSi.append(new Option(ds[i].ten, ds[i].id));
            }
        });
    }

    function getJson1(caKham, ngayKham) {
        caKham.append(new Option("Choose your shift", ""));
        $.getJSON("/api/ajax1?date=" + ngayKham).done(function (task) {
            console.log("DONE: ", JSON.stringify(task));
            let ds = task;
            for (let i = 0; i < ds.length; i++) {
                console.log(ds[i].id);
                caKham.append(new Option(ds[i].tenCa, ds[i].id));
            }
        });

        if (caKham.options.length > 0) {
            getJson();
        }
    }

    function reExamination() {
        let e = document.getElementsByTagName("form");
        e[1].style.display = "block";
        e[2].style.display = "none";
    }

    //
    // function checkDate(obj) {
    //     if (obj.value == "" || !moment(obj.value, "DD/MM/YYYY").isValid())
    //     {
    //         let caKham = document.querySelector("select[name=caKham]");
    //
    //         caKham.disabled = true;
    //     }
    // }

</script>
