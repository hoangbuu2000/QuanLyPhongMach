<%--
  Created by IntelliJ IDEA.
  User: Buu
  Date: 02/03/2021
  Time: 8:16 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h2>Trang index</h2>
<c:if test="${bacsi != null}">
    <h2>Bac si</h2>
    <c:forEach items="${bacsi}" var="b">
        <p>${b.getHo()}</p>
        <p>${b.getTen()}</p>
    </c:forEach>
</c:if>

<c:if test="${admin != null}">
    <h2>Admin</h2>
    <c:forEach items="${admin}" var="a">
        <p>${a.getHo()}</p>
        <p>${a.getTen()}</p>
    </c:forEach>
</c:if>

<c:if test="${benhnhan != null}">
    <h2>Benh nhan</h2>
    <c:forEach items="${benhnhan}" var="a">
        <p>${a.getHo()}</p>
        <p>${a.getTen()}</p>
    </c:forEach>
</c:if>

<c:if test="${taikhoan != null}">
    <h2>Tai khoan</h2>
    <c:forEach items="${taikhoan}" var="t">
        <p>${t.getUsername()}</p>
        <p>${t.getPassword()}</p>
    </c:forEach>
</c:if>

<c:if test="${toathuoc != null}">
    <h2>Toa thuoc</h2>
    <c:forEach items="${toathuoc}" var="t">
        <p>${t.getId()}</p>
        <p>${t.getNgayKeToa().toString()}</p>
        <p>${t.getBenhNhan().getTen()}</p>
        <p>${t.getBacSi().getTen()}</p>
        <p>${t.getLoaiBenh().getTenBenh()}</p>
        <p>=======================</p>
        <c:forEach items="${t.getDsChiTietToaThuoc()}" var="item">
            <h3>Ten thuoc: ${item.getThuoc().getTenThuoc()}</h3>
            <h3>Don gia: ${item.getDonGia()}</h3>
            <h3>So luong: ${item.getSoLuong()}</h3>
            <hr>
        </c:forEach>
    </c:forEach>
</c:if>

<c:if test="${hoadon != null}">
    <c:forEach items="${hoadon}" var="h">
        <p>Ma hoa don: ${h.getId()}</p>
        <p>Ngay xuat hoa don: ${h.getNgayXuat()}</p>
        <p>Tong tien: ${h.getTongTien()}</p>
        <p>Nhan vien: ${h.getNhanVien().getTen()}</p>
        <p>Benh nhan: ${h.getToaThuoc().getBenhNhan().getTen()}</p>
        <p>Danh sach thuoc</p>
        <c:forEach items="${h.getToaThuoc().getDsChiTietToaThuoc()}" var="item">
            <p>Ten thuoc: ${item.getThuoc().getTenThuoc()}</p>
            <p>So luong: ${item.getSoLuong()}</p>
            <p>Don gia: ${item.getDonGia()}</p>
            <p>Thanh tien: ${item.getSoLuong() * item.getDonGia()}</p>
        </c:forEach>
    </c:forEach>
</c:if>
