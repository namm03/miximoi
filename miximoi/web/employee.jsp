<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Danh sách nhân viên</title>
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

            .add-button {
                margin-bottom: 10px;
                padding-bottom: 20px;
            }

            .add-button a {
                padding: 8px 20px;
                background-color: #28a745;
                color: #fff;
                text-decoration: none;
                border-radius: 3px;
            }

            .add-button a:hover {
                background-color: #218838;
            }
        </style>
    </head>
    <body>
        <%@ include file="header.jsp" %>

        <div class="container">
            <h2>Danh sách nhân viên</h2>

            <div class="search-form">
                <form action="EmployeeServlet" method="get">
                    <input type="hidden" name="action" value="search">
                    <label for="hoten">Tìm kiếm:</label>
                    <input type="text" id="hoten" name="hoten">
                    <button type="submit">Tìm kiếm</button>
                </form>
            </div>

            <div class="add-button">
                <a href="EmployeeServlet?action=new">Thêm nhân viên</a>
            </div>

            <table>
                <thead>
                    <tr>
                        <th>Mã nhân viên</th>
                        <th>Mã chức vụ</th>
                        <th>Họ tên</th>
                        <th>Ngày sinh</th>
                        <th>Giới tính</th>
                        <th>Số điện thoại</th>
                        <th>Email</th>
                        <th>Địa chỉ</th>
                        <th>Thao tác</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="employee" items="${employeeList}">
                        <tr>
                            <td>${employee.employeeID}</td>
                            <td>${employee.positionID}</td>
                            <td>${employee.hoten}</td>
                            <td>${employee.ngaysinh}</td>
                            <td>${employee.gioitinh}</td>
                            <td>${employee.sdt}</td>
                            <td>${employee.email}</td>
                            <td>${employee.diachi}</td>

                            <td>
                                <a class="edit-link" href="EmployeeServlet?action=edit&employeeID=${employee.employeeID}">Chỉnh sửa</a>
                                <a class="delete-link" href="EmployeeServlet?action=delete&employeeID=${employee.employeeID}">Xóa</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <%@ include file="footer.jsp" %>
    </body>
</html>
