<%-- 
    Document   : report
    Created on : Jun 15, 2024, 1:26:12 PM
    Author     : namng
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Thống kê, báo cáo</title>
        <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        .container {
            width: 100%;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h2 {
            margin-top: 0;
            text-align: center;
            color: #333;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        table, th, td {
            border: 1px solid #ddd;
            padding: 10px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

        .edit-link, .delete-link {
            text-decoration: none;
            padding: 5px 10px;
            margin-right: 5px;
            color: #007bff;
            border: 1px solid #007bff;
            border-radius: 3px;
        }

        .edit-link:hover, .delete-link:hover {
            background-color: #007bff;
            color: #fff;
        }

        .search-form {
            text-align: center;
            margin-bottom: 20px;
        }

        .search-form label {
            font-weight: bold;
        }

        .search-form input[type="text"] {
            padding: 8px;
            margin-left: 5px;
        }
        .search-form select {
            width: 250px;
            padding: 5px;
            font-size: 16px;
        }
        .search-form button {
            padding: 8px 20px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 3px;
            cursor: pointer;
        }

        .search-form button:hover {
            background-color: #0056b3;
        }

        .print-button {
            margin-bottom: 10px;
            padding-bottom: 20px;
        }

        .print-button a {
            padding: 8px 20px;
            background-color: #28a745;
            color: #fff;
            text-decoration: none;
            border-radius: 3px;
        }

        .print-button a:hover {
            background-color: #218838;
        }
    </style>
    </head>
    <body>
        <%@ include file="header.jsp" %>
        
        <div class="container">
        <h2>Danh sách nhân viên và lương theo phòng ban</h2>
        
        <div class="search-form">
            <form action="ReportServlet" method="get">
                <input type="hidden" name="action" value="search">
                <label for="departmentID">Phòng ban</label>
                <select id="departmentID" name="departmentID" required>
                    <c:forEach var="department" items="${departmentList}">
                        <option value="${department.departmentID}">
                            ${department.departmentName}
                        </option>
                    </c:forEach>
                </select>
                <button type="submit">Lọc</button>
            </form>
        </div>
        
        <div class="print-button">
            <a href="ReportServlet?action=print">In danh sách</a>
        </div>
        
        <table>
            <thead>
                <tr>
                    <th>Mã Nhân Viên</th>
                    <th>Họ Tên</th>
                    <th>Ngày Sinh</th>
                    <th>Giới Tính</th>
                    <th>Số Điện Thoại</th>
                    <th>Phòng Ban</th>
                    <th>Chức Vụ</th>
                    <th>Lương</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="report" items="${employeeList}">
                    <tr>
                        <td>${employee.employeeID}</td>
                        <td>${employee.hoten}</td>
                        <td>${employee.ngaysinh}</td>
                        <td>${employee.gioitinh}</td>
                        <td>${employee.sdt}</td>
                        <td>${department.departmentName}</td>
                        <td>${position.positionName}</td>
                        <td>${salary.luong}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
        <%@ include file="footer.jsp" %>
    </body>
</html>
