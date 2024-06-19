<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Employee List</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            padding: 8px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>

<h2>Employee List</h2>

<table>
    <thead>
        <tr>
            <th>ID</th>
            <th>Position ID</th>
            <th>Name</th>
            <th>DOB</th>
            <th>Gender</th>
            <th>Phone</th>
            <th>Email</th>
            <th>Address</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="employee" items="${employees}">
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
                    <a href="EmployeeServlet?action=show&employeeID=${employee.employeeID}">Show</a>
                    |
                    <a href="EmployeeServlet?action=edit&employeeID=${employee.employeeID}">Edit</a>
                    |
                    <a href="EmployeeServlet?action=delete&employeeID=${employee.employeeID}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<p>
    <a href="EmployeeServlet?action=new">Add New Employee</a>
</p>

</body>
</html>
