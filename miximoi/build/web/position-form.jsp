<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chức vụ</title>
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
        <h2>${empty position ? 'Thêm chức vụ' : 'Cập nhật chức vụ'}</h2>
        
        <form action="PositionServlet" method="post">
            <input type="hidden" name="action" value="${empty position ? 'insert' : 'update'}">
            <c:if test="${not empty position}">
                <input type="hidden" name="positionID" value="${position.positionID}">
            </c:if>
            <div class="form-group">
                <label for="positionName">Tên chức vụ:</label>
                <input type="text" id="positionName" name="positionName" required
                       value="${empty position ? '' : position.positionName}">
            </div>
            <div class="form-group">
                <label for="heso">Hệ số lương:</label>
                <input type="text" id="heso" name="heso" required
                       value="${empty position ? '' : position.heso}">
            </div>
            <div class="form-group">
                <label for="departmentID">Phòng ban:</label>
                <select id="departmentID" name="departmentID" required>
                    <c:forEach var="department" items="${departmentList}">
                        <option value="${department.departmentID}"
                            ${not empty position && position.departmentID == department.departmentID ? 'selected' : ''}>
                            ${department.departmentName}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <button type="submit">${empty position ? 'Thêm' : 'Cập nhật'}</button>
                <a href="PositionServlet?action=list">Hủy</a>
            </div>
        </form>
    </div>

    <%@ include file="footer.jsp" %>
</body>
</html>
