<%--
  Created by IntelliJ IDEA.
  User: Buu
  Date: 09/04/2021
  Time: 8:20 CH
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
                    <c:if test="${invoice.id != null}">
                        <spring:message code="invoice.edit.page.title" />
                    </c:if>
                    <c:if test="${invoice.id == null}">
                        <spring:message code="invoice.add.page.title" />
                    </c:if>
                </h4>
            </div>
        </div>
        <form:form onsubmit="return false;" modelAttribute="invoice" method="post">
            <form:errors path="*" element="div" cssClass="text-danger" />
            <div class="row">
                <div class="col-lg-8 offset-lg-2">
                    <div class="row">
                        <div class="col-sm-6">
                            <div class="form-group">
                                <label><spring:message code="invoice.add.label.employee" /> <span class="text-danger">*</span></label>
                                <c:if test="${invoice.id != null}">
                                    <form:select path="employee" cssClass="form-control select" >
                                        <c:forEach items="${employees}" var="d">
                                            <c:if test="${d.id == invoice.employee.id}">
                                                <form:option value="${d.id}" label="${d.ten}" selected="true" />
                                            </c:if>
                                            <c:if test="${d.id != invoice.employee.id}">
                                                <form:option value="${d.id}" label="${d.ten}" />
                                            </c:if>
                                        </c:forEach>
                                    </form:select>
                                </c:if>
                                <c:if test="${invoice.id == null}">
                                    <form:select path="employee" items="${employees}" itemLabel="ten" itemValue="id"
                                                 cssClass="form-control select" />
                                </c:if>
                                <form:errors path="employee" cssClass="text-danger" />
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div class="form-group">
                                <label><spring:message code="invoice.add.label.prescription" /></label>
                                <c:if test="${invoice.id != null}">
                                    <form:select onchange="getDetails(this)" path="prescription" cssClass="form-control select" >
                                        <form:option value="0" label="Choose the prescription" />
                                        <c:forEach items="${prescriptions}" var="d">
                                            <c:if test="${d.id == invoice.prescription.id}">
                                                <form:option value="${d.id}" label="${d.displayName}" selected="true" />
                                            </c:if>
                                            <c:if test="${d.id != invoice.prescription.id}">
                                                <form:option value="${d.id}" label="${d.displayName}" />
                                            </c:if>
                                        </c:forEach>
                                    </form:select>
                                </c:if>
                                <c:if test="${invoice.id == null}">
                                    <form:select onchange="getDetails(this)" path="prescription"
                                                 cssClass="form-control select">
                                        <form:option value="0" label="Choose the prescription" />
                                        <c:forEach items="${prescriptions}" var="p">
                                            <form:option value="${p.id}" label="${p.displayName}" />
                                        </c:forEach>
                                    </form:select>
                                </c:if>
                                <form:errors path="prescription" cssClass="text-danger" />
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div class="form-group">
                                <label><spring:message code="invoice.add.label.date" /></label>
                                <c:if test="${invoice.id != null}">
                                    <form:input path="date" cssClass="form-control datetimepicker" value="${invoice.date}" />
                                </c:if>
                                <c:if test="${invoice.id == null}">
                                    <form:input path="date" cssClass="form-control datetimepicker" />
                                </c:if>
                                <form:errors path="date" cssClass="text-danger" />
                            </div>
                        </div>
                    </div>

                    <form:hidden path="medicines" />
                    <table id="table-modal" class="table table-responsive-md">
                        <thead>
                        <tr>
                            <th>STT</th>
                            <th>Ten thuoc</th>
                            <th>Mo ta</th>
                            <th>So luong</th>
                            <th>Don gia</th>
                            <th>Don vi</th>
                            <th>Thanh tien</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        </tbody>
                        <tfoot>
                        <tr>
                            <th colspan="7">Tien thuoc</th>
                            <td colspan="1">0 VND</td>
                        </tr>
                        <tr>
                            <th colspan="7">Tien kham</th>
                            <td colspan="1">0 VND</td>
                        </tr>
                        <tr>
                            <th colspan="7">Tong cong</th>
                            <td colspan="1">0 VND</td>
                        </tr>
                        </tfoot>
                    </table>

                    <div class="m-t-20 text-center">
                        <c:if test="${invoice.id != null}">
                            <button onclick="setUp()" class="btn btn-primary submit-btn"><spring:message code="invoice.edit.form.submit" /></button>
                        </c:if>
                        <c:if test="${invoice.id == null}">
                            <button onclick="setUp()" class="btn btn-primary submit-btn"><spring:message code="invoice.add.form.submit" /></button>
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
    function getDetails(obj) {
        let id = obj.value;
        $.getJSON("/invoice/api/getTienKham?idToaThuoc=" + id).done(function (res) {
            $.getJSON("/patient/api/getDiseaseDetails?id="+id).done(function (task) {
                let keys = Object.keys(task);
                let values = Object.values(task);
                let e = $("#table-modal > tbody");
                let tKham = $("#table-modal > tfoot > tr:nth-child(2) > td:last-child");
                let tThuoc = $("#table-modal > tfoot > tr:first-child > td:last-child");
                let tongCong = $("#table-modal > tfoot > tr:last-child > td:last-child");
                let tienThuoc = 0;
                let total = 0;
                e.empty();
                tThuoc.empty();
                tongCong.empty();

                for(let i = 0; i < keys.length; i++) {
                    let info = keys[i].substring(5).replaceAll("{", "").replaceAll("}", "").split(", ");

                    let id = info[0].substr(3);
                    let tenThuoc = info[1].substr(9).replaceAll("'", "");
                    let moTa = info[2].substr(5).replaceAll("'", "");
                    let donGia = info[3].substr(7);
                    let donVi = info[4].substr(6).replaceAll("'", "");
                    let soLuong = values[i];
                    let thanhTien = donGia * soLuong;
                    tienThuoc = tienThuoc + thanhTien;

                    let tienKham = 0;
                    if (res != null)
                        tienKham = res;

                    let newStt = i + 1;
                    e.append(
                        '<tr>'+
                        '<td class="stt">' + newStt +
                        '<input type="hidden" value="'+id+'" '+
                        '</td>'+
                        '<td class="medicine">' + tenThuoc +
                        '</td>'+
                        '<td class="description">' + moTa +
                        '</td>' +
                        '<td class="quantity">' + soLuong +
                        '</td>'+
                        '<td class="price">' + donGia +
                        '</td>'+
                        '<td class="unit">' + donVi +
                        '</td>'+
                        '<td class="total">' + thanhTien +
                        '</td>'+
                        '<td>'+
                        '<a class="btn btn-primary" href="javascript:;" onclick="deleteMedicine('+newStt+')">Delete'+
                        '</a>'+
                        '</td>'+
                        '</tr>'
                    );

                    tKham.empty();
                    tKham.append(tienKham + " VND");

                    total = total + tienKham / keys.length;
                }
                tThuoc.append(tienThuoc + " VND");
                total = total + tienThuoc;
                tongCong.append(total + " VND");
            })
        })
    }

    function deleteMedicine(stt) {
        let e = document.querySelector("#table-modal > tbody");
        let els = document.getElementsByClassName("stt");
        console.log("KhaKha");
        for(let i = 0; i < els.length; i++) {
            console.log(i);
            if (els[i].innerText == stt) {
                let thanhTien = e.rows[i].cells[6].innerText;
                console.log("Thanh tien: ", thanhTien);
                let tThuoc = $("#table-modal > tfoot > tr:first-child > td:last-child");
                let moneyThuoc = tThuoc[0].innerText.substr(0, tThuoc[0].innerText.indexOf("V") - 1);
                tThuoc.empty().append(parseInt(moneyThuoc) - parseInt(thanhTien) + " VND");
                let tongCong = $("#table-modal > tfoot > tr:last-child > td:last-child");
                let moneyTotal = tongCong[0].innerText.substr(0, tongCong[0].innerText.indexOf("V") - 1);
                tongCong.empty().append(parseInt(moneyTotal) - parseInt(thanhTien) + " VND");
                e.deleteRow(i);
                break;
            }
        }
    }

    function setUp() {
        let medicines = document.getElementsByClassName("stt");
        let quantities = document.getElementsByClassName("quantity");

        let hiddenInput = document.querySelector("input[type=hidden]#medicines");
        let str = "";
        for(let i = 0; i < medicines.length; i++) {
            let medicine = medicines[i].childNodes[1].value;
            let quantity = quantities[i].innerHTML;
            console.log("MEDICINE ID: ", medicine);
            console.log("QUANTITY: ", quantity);
            if (medicine !== "" && quantity !== "")
                str += medicine+"-"+quantity+";";
        }
        hiddenInput.value = str;
        console.log("VALUE: ", str);

        document.querySelector("form").submit();
    }

    <c:if test="${invoice.id != null}">
        setTimeout(function () {
            let e = document.getElementsByName("prescription")[0];
            getDetails(e);
        }, 500);
    </c:if>
</script>
