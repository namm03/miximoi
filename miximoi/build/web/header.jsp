<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>MixiMoi - Quản lý nhân sự và tiền lương</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 100%;
            margin: 0 auto;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        header {
            text-align: center;
            padding: 10px 0;
            background-color: #4CAF50;
            color: white;
        }
        nav ul {
            list-style-type: none;
            padding: 10px;
            text-align: center;
            margin: 0;
        }
        nav ul li {
            display: inline;
            margin-right: 20px;
        }
        nav ul li a {
            text-decoration: none;
            color: #333;
            font-weight: bold;
            padding: 5px 10px;
        }
        nav ul li a:hover {
            color: #4CAF50;
            background-color: #f2f2f2;
            border-radius: 3px;
        }
    </style>
</head>
<body>
<div class="container">
    <header>
        <h1>Quản lý nhân sự và tiền lương - MixiMoi</h1>
    </header>
    <nav>
        <ul>
            <li><a href="EmployeeServlet?action=list">Quản lý Nhân viên</a></li>
            <li><a href="DepartmentServlet?action=list">Quản lý Phòng ban</a></li>
            <li><a href="PositionServlet?action=list">Quản lý Chức vụ</a></li>
            <li><a href="SalaryServlet?action=list">Quản lý Lương</a></li>
            <li><a href="ReportServlet?action=list">Thống kê, báo cáo</a></li>
            <li><a href="login.jsp">Đăng xuất</a></li>
        </ul>
    </nav>
</div>
</body>
</html>
