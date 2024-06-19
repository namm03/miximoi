<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Form</title>
    <style>
        .form-container {
            width: 50%;
            margin: 20px auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        .form-group {
            margin-bottom: 10px;
        }
        .form-group label {
            display: inline-block;
            width: 120px;
            font-weight: bold;
        }
        .form-group input {
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
    </style>
</head>
<body>
    <%@ include file="header-admin.jsp" %>
    
    <div class="form-container">
        <h2>Thêm tài khoản</h2>
        <form action="UserServlet" method="post">
            <input type="hidden" name="action" value="${empty user ? 'insert' : 'update'}">
            <c:if test="${not empty user}">
                <input type="hidden" name="id" value="${user.id}">
            </c:if>
            <div class="form-group">
                <label for="username">Tài khoản:</label>
                <input type="text" id="username" name="username" required
                       value="${empty user ? '' : user.username}">
            </div>
            <div class="form-group">
                <label for="password">Mật khẩu:</label>
                <input type="password" id="password" name="password" required
                       value="${empty user ? '' : user.password}">
            </div>
            <div class="form-group">
                <button type="submit">${empty user ? 'Thêm' : 'Update User'}</button>
                <a href="UserServlet?action=list">Hủy</a>
            </div>
        </form>
    </div>

    <%@ include file="footer.jsp" %>
</body>
</html>
