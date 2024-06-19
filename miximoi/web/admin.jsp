<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quản lý tài khoản</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid black;
            padding: 8px;
        }
        th {
            background-color: #f2f2f2;
        }
        .edit-link, .delete-link {
            text-decoration: none;
            padding: 4px 8px;
            margin: 2px;
            color: #333;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        .edit-link:hover, .delete-link:hover {
            background-color: #f0f0f0;
        }
        .search-form {
            margin-bottom: 20px;
            text-align: center;
        }
    </style>
</head>
<body>
    <%@ include file="header-admin.jsp" %>
    
    <h2>Danh sách tài khoản</h2>
    
    <div class="search-form">
        <form action="UserServlet" method="get">
            <input type="hidden" name="action" value="search">
            <label for="username">Tìm kiếm:</label>
            <input type="text" id="username" name="username">
            <button type="submit">Search</button>
        </form>
    </div>
    
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Username</th>
                <th>Password</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="user" items="${userList}">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.username}</td>
                    <td>${user.password}</td>
                    <td>
                        <a class="edit-link" href="UserServlet?action=edit&id=${user.id}">Edit</a>
                        <a class="delete-link" href="UserServlet?action=delete&id=${user.id}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    
    <br>
    <%@ include file="footer.jsp" %>
</body>
</html>
