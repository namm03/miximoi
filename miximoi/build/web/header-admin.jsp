<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quản lý tài khoản</title>
    <style>
        /* CSS styles for header can be placed here */
        .header {
            background-color: #333;
            color: white;
            padding: 10px;
            text-align: center;
        }
        .nav-link {
            color: white;
            text-decoration: none;
            padding: 10px;
        }
        .nav-link:hover {
            background-color: #555;
        }
    </style>
</head>
<body>
    <div class="header">
        <h1>Xin chào Admin!</h1>
        <nav>
            <a class="nav-link" href="index.jsp">Home</a>
            <a class="nav-link" href="UserServlet?action=new">Thêm tài khoản</a>
        </nav>
    </div>
    <hr>
</body>
</html>
