<%--
  Created by IntelliJ IDEA.
  User: Buu
  Date: 30/03/2021
  Time: 12:03 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="page-wrapper">
    <div class="content">
        <table class="table table-responsive-md">
            <thead>
            <tr>
                <td><spring:message code="patient.details.table.fullname" /></td>
                <td><spring:message code="patient.details.table.gender" /></td>
                <td><spring:message code="patient.details.table.dob" /></td>
                <td><spring:message code="patient.details.table.age" /></td>
                <td><spring:message code="patient.details.table.country" /></td>
                <td><spring:message code="patient.details.table.phone" /></td>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>${patient.ho} ${patient.ten}</td>
                <td>${patient.gioiTinh}</td>
                <td><fmt:formatDate value="${patient.ngaySinh}" pattern="dd/MM/yyyy" /></td>
                <td>${patient.tuoi}</td>
                <td>...</td>
                <td>${patient.dienThoai}</td>
            </tr>
            </tbody>
            <tfoot></tfoot>
        </table>

        <c:if test="${diseases.size() > 0}">
            <table class="table table-response-md">
                <thead>
                <tr>
                    <!--Model chi tiet toa thuoc -->
                    <th>Toa thuoc</th>
                    <th>Ngay ke toa</th>
                    <th>Ten benh</th>
                    <th>Mo ta</th>
                    <th>Bac si</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${diseases}" var="d">
                    <tr>
                        <td>
                            <a href="javascript:;" onclick="getDetails('${d[0]}')" data-toggle="modal" data-target="#details">
                                    ${d[0]}
                            </a>
                        </td>
                        <td><fmt:formatDate value="${d[2]}" pattern="dd/MM/yyyy" /></td>
                        <td>${d[3]}</td>
                        <td>${d[4]}</td>
                        <td>${d[1]}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${diseases.size() <= 0}">
            <div class="alert-danger">
                <h4>Benh nhan nay chua toi kham benh lan nao</h4>
            </div>
        </c:if>


        <div class="modal fade" id="details" tabindex="-1" role="dialog" aria-labelledby="detailsTitle" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="detailsTitle">DETAILS</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <h4>CHI TIET TOA THUOC</h4>
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
                            </tr>
                            </thead>
                            <tbody>
                            </tbody>
                            <tfoot>
                            <tr>
                                <td colspan="6">Tien thuoc</td>
                                <td colspan="1">0 VND</td>
                            </tr>
                            <tr>
                                <td colspan="6">Tien kham</td>
                                <td colspan="1">0 VND</td>
                            </tr>
                            <tr>
                                <td colspan="6">Tong cong</td>
                                <td colspan="1">0 VND</td>
                            </tr>
                            </tfoot>
                        </table>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    function getDetails(id) {
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

            <c:set var="i" value="0" />
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

                <c:if test="${diseases.size() > 0}">
                let tienKham = ${diseases.get(i)[5]};
                ${i = i + 1}
                </c:if>

                let newStt = i + 1;
                e.append(
                    '<tr>'+
                    '<td class="stt">' + newStt +
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
    }
</script>
