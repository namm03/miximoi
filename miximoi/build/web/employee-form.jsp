<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Nhân viên</title>
    <style>
        .form-container {
            width: 100%;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: white;
            text-align: center;
        }
        .form-group {
            margin-bottom: 10px;
        }
        .form-group label {
            display: inline-block;
            width: 120px;
            font-weight: bold;
        }
        .form-group input, .form-group select {
            width: 300px;
            padding: 5px;
            font-size: 16px;
        }
        .form-group button {
            padding: 8px 20px;
            font-size: 16px;
            background-color: #007bff;
            color: white;
            border: none;
            cursor: pointer;
            border-radius: 5px;
        }
        .form-group button:hover {
            background-color: #0056b3;
        }
        .form-group a {
            text-decoration: none;
            padding: 8px 20px;
            background-color: #6c757d;
            color: white;
            border-radius: 5px;
            margin-left: 10px;
        }
        .form-group a:hover {
            background-color: #5a6268;
        }
    </style>
</head>
<body>
    <%@ include file="header.jsp" %>
    
    <div class="form-container">
        <h2>${empty employee ? 'Thêm nhân viên' : 'Cập nhật nhân viên'}</h2>
        
        <form action="EmployeeServlet" method="post">
            <input type="hidden" name="action" value="${empty employee ? 'insert' : 'update'}">
            <c:if test="${not empty employee}">
                <input type="hidden" name="employeeID" value="${employee.employeeID}">
            </c:if>
            <div class="form-group">
                <label for="hoten">Họ tên:</label>
                <input type="text" id="hoten" name="hoten" required
                       value="${empty employee ? '' : employee.hoten}">
            </div>
            <div class="form-group">
                <label for="ngaysinh">Ngày sinh:</label>
                <input type="date" id="ngaysinh" name="ngaysinh" required
                       value="${empty employee ? '' : employee.ngaysinh}">
            </div>
            <div class="form-group">
                <label for="gioitinh">Giới tính:</label>
                <select id="gioitinh" name="gioitinh" required>
                    <option value="Nam" ${employee.gioitinh == 'Nam' ? 'selected' : ''}>Nam</option>
                    <option value="Nữ" ${employee.gioitinh == 'Nữ' ? 'selected' : ''}>Nữ</option>
                    <option value="Khác" ${employee.gioitinh == 'Khác' ? 'selected' : ''}>Khác</option>
                </select>
            </div>
            <div class="form-group">
                <label for="sdt">Số điện thoại:</label>
                <input type="text" id="sdt" name="sdt" required
                       value="${empty employee ? '' : employee.sdt}">
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required
                       value="${empty employee ? '' : employee.email}">
            </div>
            <div class="form-group">
                <label for="diachi">Địa chỉ:</label>
                <input type="text" id="diachi" name="diachi" required
                       value="${empty employee ? '' : employee.diachi}">
            </div>  
            <div class="form-group">
                <label for="positionID">Chức vụ:</label>
                <select id="positionID" name="positionID" required>
                    <c:forEach var="position" items="${positionList}">
                        <option value="${position.positionID}"
                            ${not empty employee && employee.positionID == position.positionID ? 'selected' : ''}>
                            ${position.positionName}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <button type="submit">${empty employee ? 'Thêm' : 'Cập nhật'}</button>
                <a href="EmployeeServlet?action=list">Hủy</a>
            </div>
        </form>
    </div>

    <%@ include file="footer.jsp" %>
</body>
</html>
