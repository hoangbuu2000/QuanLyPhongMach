<%--
  Created by IntelliJ IDEA.
  User: Buu
  Date: 09/04/2021
  Time: 8:21 CH
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: Buu
  Date: 09/04/2021
  Time: 8:21 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="page-wrapper" style="min-height: 754px;">
    <div class="content">
        <h4>THONG TIN HOA DON</h4>
        <table class="table table-responsive-sm">
            <thead>
            <tr>
                <th style="min-width:200px;">Invoice ID</th>
                <th>Employee</th>
                <th>Patient</th>
                <th>Disease</th>
                <th style="min-width: 110px;">Date</th>
                <th class="text-right">Action</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>${invoice.id}</td>
                <td>${invoice.employee.ten}</td>
                <td>${invoice.prescription.benhNhan.ten}</td>
                <td>${invoice.prescription.loaiBenh.tenBenh}</td>
                <td>${invoice.date}</td>
                <td class="text-right">
                    <div class="dropdown dropdown-action">
                        <a href="#" class="action-icon dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><i class="fa fa-ellipsis-v"></i></a>
                        <div class="dropdown-menu dropdown-menu-right">
                            <a class="dropdown-item" href="<c:url value="/invoice/addorupdate?id=${invoice.id}" />"><i class="fa fa-pencil m-r-5"></i> Edit</a>
                            <a class="dropdown-item" href="#" data-toggle="modal" data-target="#delete_invoice${invoice.id}"><i class="fa fa-trash-o m-r-5"></i> Delete</a>
                        </div>
                    </div>
                    <div id="delete_invoice${invoice.id}" class="modal fade delete-modal" role="dialog">
                        <div class="modal-dialog modal-dialog-centered">
                            <div class="modal-content">
                                <div class="modal-body text-center">
                                    <h3>Are you sure want to delete this Invoice?</h3>
                                    <div class="m-t-20"> <a href="#" class="btn btn-white" data-dismiss="modal">Close</a>
                                        <form:form action="/invoice/delete?id=${invoice.id}" method="post">
                                            <button type="submit" class="btn btn-danger">Delete</button>
                                        </form:form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            </tbody>
            <tfoot>
            <tr>
                <td colspan="5">Tien kham</td>
                <td colspan="1">${invoice.prescription.loaiBenh.donGia} VND</td>
            </tr>
            </tfoot>
        </table>

        <h4>THONG TIN THUOC</h4>
        <table class="table table-responsive-md">
            <thead>
            <tr>
                <th>STT</th>
                <th>Ten thuoc</th>
                <th>So luong</th>
                <th>Don gia</th>
                <th>Don vi</th>
                <th>Thanh tien</th>
            </tr>
            </thead>
            <tbody>
            <c:set var="i" value="0" />
            <c:forEach items="${invoice.medicines.entrySet()}" var="set">
                <tr>
                    <td>${i = i + 1}</td>
                    <td>${set.getKey().tenThuoc}</td>
                    <td>${set.getValue()}</td>
                    <td>${set.getKey().donGia}</td>
                    <td>${set.getKey().donVi}</td>
                    <td class="total"><c:out value="${set.getKey().donGia * set.getValue()}" /> VND</td>
                </tr>
            </c:forEach>
            </tbody>
            <tfoot>
            <tr>
                <td colspan="5">Tien thuoc</td>
                <td id="price" colspan="1"></td>
            </tr>
            </tfoot>
        </table>
    </div>
</div>

<script>
    let e = document.getElementsByClassName("total");
    let totalAmount = 0;
    for (let i = 0; i < e.length; i++) {
        totalAmount = totalAmount + parseInt(e[i].innerHTML);
    }
    let price = document.getElementById("price");
    price.innerHTML = totalAmount + " VND";
</script>
