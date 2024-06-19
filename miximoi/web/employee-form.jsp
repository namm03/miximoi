<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Employee Form</title>
    <style>
        .form-container {
            max-width: 600px;
            margin: auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #f2f2f2;
        }
        .form-container label {
            font-weight: bold;
        }
        .form-container input[type=text], 
        .form-container input[type=date], 
        .form-container input[type=number], 
        .form-container input[type=email], 
        .form-container select {
            width: 100%;
            padding: 8px;
            margin: 5px 0 15px 0;
            display: inline-block;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }
        .form-container input[type=submit], 
        .form-container input[type=reset] {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            float: right;
        }
        .form-container input[type=submit]:hover, 
        .form-container input[type=reset]:hover {
            background-color: #45a049;
        }
        .form-container .cancel-btn {
            background-color: #f44336;
        }
        .form-container .cancel-btn:hover {
            background-color: #da190b;
        }
    </style>
</head>
<body>
    <div class="form-container">
        <h2>Employee Form</h2>
        <form action="EmployeeServlet" method="post">
            <input type="hidden" name="action" value="${action}">
            <input type="hidden" name="employeeID" value="${employee.employeeID}">
            <label for="departmentID">Department ID:</label>
            <input type="number" id="departmentID" name="departmentID" value="${employee.departmentID}" required>
            <br>
            <label for="hoten">Full Name:</label>
            <input type="text" id="hoten" name="hoten" value="${employee.hoten}" required>
            <br>
            <label for="ngaysinh">Date of Birth:</label>
            <input type="date" id="ngaysinh" name="ngaysinh" value="${employee.ngaysinh}" required>
            <br>
            <label for="gioitinh">Gender:</label>
            <select id="gioitinh" name="gioitinh" required>
                <option value="Male" ${employee.gioitinh == 'Male' ? 'selected' : ''}>Male</option>
                <option value="Female" ${employee.gioitinh == 'Female' ? 'selected' : ''}>Female</option>
            </select>
            <br>
            <label for="sdt">Phone Number:</label>
            <input type="text" id="sdt" name="sdt" value="${employee.sdt}" required>
            <br>
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" value="${employee.email}" required>
            <br>
            <label for="diachi">Address:</label>
            <input type="text" id="diachi" name="diachi" value="${employee.diachi}" required>
            <br>
            <br>
            <input type="submit" value="Save">
            <input type="reset" value="Reset">
            <a href="EmployeeServlet">Cancel</a>
        </form>
    </div>
</body>
</html>
