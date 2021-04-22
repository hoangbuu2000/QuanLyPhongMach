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
                <h4 class="page-title">
                    <c:if test="${prescription.id != null}">
                        <spring:message code="prescription.edit.page.title" />
                    </c:if>
                    <c:if test="${prescription.id == null}">
                        <spring:message code="prescription.add.page.title" />
                    </c:if>
                </h4>
            </div>
        </div>
        <form:form onsubmit="return false;" modelAttribute="prescription" method="post">
            <form:errors path="*" element="div" cssClass="text-danger" />
            <div class="row">
                <div class="col-lg-8 offset-lg-2">
                    <div class="row">
                        <div class="col-sm-6">
                            <div class="form-group">
                                <label><spring:message code="prescription.add.label.doctor" /> <span class="text-danger">*</span></label>
                                <c:if test="${prescription.id != null}">
                                    <form:select path="doctor" cssClass="form-control select" >
                                        <c:forEach items="${doctors}" var="d">
                                            <c:if test="${d.id == prescription.doctor.id}">
                                                <form:option value="${d.id}" label="${d.ten}" selected="true" />
                                            </c:if>
                                            <c:if test="${d.id != prescription.doctor.id}">
                                                <form:option value="${d.id}" label="${d.ten}" />
                                            </c:if>
                                        </c:forEach>
                                    </form:select>
                                </c:if>
                                <c:if test="${prescription.id == null}">
                                    <form:select path="doctor" items="${doctors}" itemLabel="ten" itemValue="id"
                                                 cssClass="form-control select" />
                                </c:if>
                                <form:errors path="doctor" cssClass="text-danger" />
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div class="form-group">
                                <label><spring:message code="prescription.add.label.patient" /></label>
                                <c:if test="${prescription.id != null}">
                                    <form:select path="patient" cssClass="form-control select" >
                                        <c:forEach items="${patients}" var="d">
                                            <c:if test="${d.id == prescription.patient.id}">
                                                <form:option value="${d.id}" label="${d.ten}" selected="true" />
                                            </c:if>
                                            <c:if test="${d.id != prescription.patient.id}">
                                                <form:option value="${d.id}" label="${d.ten}" />
                                            </c:if>
                                        </c:forEach>
                                    </form:select>
                                </c:if>
                                <c:if test="${prescription.id == null}">
                                    <form:select path="patient" items="${patients}" itemLabel="ten" itemValue="id"
                                                 cssClass="form-control select" />
                                </c:if>
                                <form:errors path="patient" cssClass="text-danger" />
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div class="form-group">
                                <label><spring:message code="prescription.add.label.disease" /></label>
                                <c:if test="${prescription.id != null}">
                                    <form:select path="disease" cssClass="form-control select" >
                                        <c:forEach items="${diseases}" var="d">
                                            <c:if test="${d.id == prescription.disease.id}">
                                                <form:option value="${d.id}" label="${d.tenBenh}" selected="true" />
                                            </c:if>
                                            <c:if test="${d.id != prescription.disease.id}">
                                                <form:option value="${d.id}" label="${d.tenBenh}" />
                                            </c:if>
                                        </c:forEach>
                                    </form:select>
                                </c:if>
                                <c:if test="${prescription.id == null}">
                                    <form:select path="disease" items="${diseases}" itemLabel="tenBenh" itemValue="id"
                                                 cssClass="form-control select" />
                                </c:if>
                                <form:errors path="disease" cssClass="text-danger" />
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div class="form-group">
                                <label><spring:message code="prescription.add.label.date" /></label>
                                <div class="cal-icon">
                                    <c:if test="${prescription.id != null}">
                                        <form:input path="date" cssClass="form-control datetimepicker" value="${prescription.date}" />
                                    </c:if>
                                    <c:if test="${prescription.id == null}">
                                        <form:input path="date" cssClass="form-control datetimepicker" />
                                    </c:if>
                                </div>
                                <form:errors path="date" cssClass="text-danger" />
                            </div>
                        </div>

                        <table id="table_medicine" class="table table-responsive-md">
                            <thead>
                            <tr>
                                <td><spring:message code="prescription.add.table.medicine.num" /></td>
                                <td><spring:message code="prescription.add.table.medicine.name" /></td>
                                <td><spring:message code="prescription.add.table.medicine.quantity" /></td>
                                <td><spring:message code="prescription.add.table.medicine.unit" /></td>
                                <td><spring:message code="prescription.add.table.medicine.price" /></td>
                                <td><spring:message code="prescription.add.table.medicine.total" /></td>
                            </tr>
                            </thead>
                            <tbody>
                            <c:if test="${prescription.id != null}">
                                <c:set var="i" value="0" />
                                <c:forEach items="${prescription.medicines.entrySet()}" var="set">
                                    <tr>
                                        <td class="stt">
                                            ${i = i + 1}
                                        </td>
                                        <td id="select${i}">
                                            <select id="${i}" name="medicine" onchange="getUnitAndPrice(this)" class="medicine" class="form-control select">
                                                <option value="0"><spring:message code="prescription.add.table.medicine.select.label" /></option>
                                                <c:forEach items="${medicines}" var="m">
                                                    <c:if test="${set.getKey().id == m.id}">
                                                        <option value="${m.id}" selected>${m.tenThuoc}</option>
                                                    </c:if>
                                                    <c:if test="${set.getKey().id != m.id}">
                                                        <option value="${m.id}">${m.tenThuoc}</option>
                                                    </c:if>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <td id="td_quantity${i}">
                                            <input id="quantity${i}" name="quantity" onchange="tinhTien(this)" class="quantity"
                                                   type="number" class="form-control" value="${set.getValue()}" />
                                        </td>
                                        <td class="unit">${set.getKey().donVi}</td>
                                        <td class="price">${set.getKey().donGia}</td>
                                        <td class="total">${set.getKey().donGia * set.getValue()}</td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            <c:if test="${prescription.id == null}">
                                <tr>
                                    <td class="stt">
                                        <spring:message code="prescription.add.table.medicine.num.first" />
                                    </td>
                                    <td id="select1">
                                        <select id="1" name="medicine" onchange="getUnitAndPrice(this)" class="medicine" class="form-control select">
                                            <option value="0" selected><spring:message code="prescription.add.table.medicine.select.label" /></option>
                                            <c:forEach items="${medicines}" var="m">
                                                <option value="${m.id}">${m.tenThuoc}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td id="td_quantity1">
                                        <input id="quantity1" name="quantity" onchange="tinhTien(this)" class="quantity" type="number" class="form-control" />
                                    </td>
                                    <td class="unit"></td>
                                    <td class="price"></td>
                                    <td class="total"></td>
                                </tr>
                            </c:if>
                            </tbody>
                            <tfoot>
                            <tr>
                                <td colspan="5"><spring:message code="prescription.add.table.medicine.total" />:</td>
                                <td colspan="1" id="total" class="right"></td>
                            </tr>
                            <tr>
                                <td colspan="5"></td>
                                <td colspan="1">
                                    <a href="javascript:;" onclick="addRow()" class="btn btn-primary">
                                        <spring:message code="prescription.add.table.medicine.add.more" />
                                    </a>
                                </td>
                            </tr>
                            </tfoot>
                        </table>
                    </div>
                    <div class="m-t-20 text-center">
                        <form:hidden path="medicines" />
                        <c:if test="${prescription.id != null}">
                            <button onclick="getDataBeforeSubmit()" class="btn btn-primary submit-btn"><spring:message code="prescription.edit.form.submit" /></button>
                        </c:if>
                        <c:if test="${prescription.id == null}">
                            <button onclick="getDataBeforeSubmit()" class="btn btn-primary submit-btn"><spring:message code="prescription.add.form.submit" /></button>
                        </c:if>
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
    function getMedicines(obj) {
        obj.options.length = 0;
        $.getJSON("/medicine/api/getAll").done(function (task) {
            console.log("List Medicines: ", JSON.stringify(task));
            let ds = task;
            obj.append(new Option("<spring:message code="prescription.add.table.medicine.select.label" />", "0"));
            for(let i = 0; i < ds.length; i++) {
                console.log(ds[i].id);
                obj.append(new Option(ds[i].tenThuoc, ds[i].id));
            }
        })
    }

    function getUnitAndPrice(obj) {
        let thuocId = obj.value;
        let quantity = document.querySelector('#quantity' + obj.id).value;

        if (thuocId != '0') {
            $.getJSON("/prescription/api/getunit?id=" + thuocId).done(function (task) {
                console.log("Unit: ", JSON.stringify(task));
                document.querySelector('#select' + obj.id + ' ~ td.unit').innerHTML = task;
            });
            $.getJSON("/prescription/api/getprice?id=" + thuocId).done(function (task) {
                document.querySelector('#select' + obj.id + ' ~ td.price').innerHTML = task;
                if (quantity == null)
                    document.querySelector('#select' + obj.id + ' ~ td.total').innerHTML = '0';
                else {
                    document.querySelector('#select' + obj.id + ' ~ td.total').innerHTML = (parseInt(task) * quantity).toString();

                    let listPrice;
                    listPrice = document.querySelectorAll(".total");
                    let total = 0;
                    for(let i = 0; i < listPrice.length; i++) {
                        if (!isNaN(parseInt(listPrice[i].innerHTML)))
                            total += parseInt(listPrice[i].innerText);
                    }
                    document.querySelector("#total").innerHTML = total.toString() + " VND";
                }
            })
        }
        else {
            let currentPrice = document.querySelector('#select' + obj.id + ' ~ td.total').innerHTML;
            let totalPrice = document.querySelector("#total").innerHTML
            document.querySelector("#total").innerHTML = (parseInt(totalPrice) - parseInt(currentPrice)).toString() + " VND";
            document.querySelector('#select' + obj.id + ' ~ td.total').innerHTML = '0';
        }


    }

    function tinhTien(obj) {
        let quantity = obj.value;
        let unitprice = document.querySelector('#td_' + obj.id + ' ~ td.price').innerHTML;
        document.querySelector('#td_' + obj.id + ' ~ td.total').innerHTML = (quantity * parseInt(unitprice)).toString();

        let listPrice = document.querySelectorAll(".total");
        let total = 0;
        for(let i = 0; i < listPrice.length; i++) {
            if (!isNaN(parseInt(listPrice[i].innerHTML)))
                total += parseInt(listPrice[i].innerText);
        }
        document.querySelector("#total").innerHTML = total.toString() + " VND";

    }

    function addRow() {
        let e = $("#table_medicine > tbody");
        let stt = document.querySelector("#table_medicine > tbody > tr:last-child > td.stt").innerText;
        let newStt = parseInt(stt) + 1;
        e.append(
            '<tr>' +
            '<td class="stt">' + newStt +
            '</td>' +
            '<td id="select'+ newStt +'">' +
            '<select id="'+ newStt +'" name="medicine" onchange="getUnitAndPrice(this)" class="medicine" class="form-control select">' +
            '<option value="0" selected><spring:message code="prescription.add.table.medicine.select.label" /></option>' +
            <c:forEach items="${medicines}" var="m">
            '<option value="${m.id}">${m.tenThuoc}</option>'+
            </c:forEach>
            '</select>' +
            '</td>' +
            '<td id="td_quantity'+ newStt +'">' +
            '<input id="quantity'+ newStt +'" name="quantity" onchange="tinhTien(this)" class="quantity" type="number" class="form-control" />' +
            '</td>' +
            '<td class="unit"></td>' +
            '<td class="price"></td>' +
            '<td class="total"></td>' +
            '</tr>'
        );
    }

    function getDataBeforeSubmit() {

        let medicines = document.getElementsByName("medicine");
        let quantities = document.getElementsByName("quantity");

        let hiddenInput = document.querySelector("input[type=hidden]");
        let str = "";
        for(let i = 0; i < medicines.length; i++) {
            let medicine = medicines[i].value;
            let quantity = quantities[i].value;
            console.log("MEDICINE ID: ", medicine);
            console.log("QUANTITY: ", quantity);
            if (medicine !== "" && quantity !== "")
                str += medicine+"-"+quantity+";";
        }
        hiddenInput.value = str;
        console.log("VALUE: ", str);

        document.querySelector("form").submit();
    }

</script>
