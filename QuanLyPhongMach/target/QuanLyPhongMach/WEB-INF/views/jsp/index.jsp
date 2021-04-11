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

<script>
    fetch("/api/ajax").then(response => {
           response.json()
        }).then(data => {
            console.log(data)
        });
    fetch("http://localhost:8080/api/ajax", {
        "headers": {
            "accept": "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
            "accept-language": "vi-VN,vi;q=0.9,fr-FR;q=0.8,fr;q=0.7,en-US;q=0.6,en;q=0.5",
            "cache-control": "max-age=0",
            "sec-ch-ua": "\"Google Chrome\";v=\"89\", \"Chromium\";v=\"89\", \";Not A Brand\";v=\"99\"",
            "sec-ch-ua-mobile": "?0",
            "sec-fetch-dest": "document",
            "sec-fetch-mode": "navigate",
            "sec-fetch-site": "none",
            "sec-fetch-user": "?1",
            "upgrade-insecure-requests": "1"
        },
        "referrerPolicy": "strict-origin-when-cross-origin",
        "body": null,
        "method": "GET",
        "mode": "cors",
        "credentials": "include"
    }).then(t => t.json()).then(d => console.log(d));

</script>
