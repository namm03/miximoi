<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Phòng ban Form</title>
    <style>
        .form-container {
            width: 100%;
            margin: 0px auto;
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
    <%@ include file="header.jsp" %>
    
    <div class="form-container">
        <h2>Thêm phòng ban</h2>
        <form action="DepartmentServlet" method="post">
            <input type="hidden" name="action" value="${empty department ? 'insert' : 'update'}">
            <c:if test="${not empty department}">
                <input type="hidden" name="departmentID" value="${department.departmentID}">
            </c:if>
            <div class="form-group">
                <label for="departmentName">Tên phòng ban:</label>
                <input type="text" id="departmentName" name="departmentName" required
                       value="${empty department ? '' : department.departmentName}">
            </div>
            <div class="form-group">
                <button type="submit">${empty department ? 'Thêm' : 'Cập nhật'}</button>
                <a href="DepartmentServlet?action=list">Hủy</a>
            </div>
        </form>
    </div>

    <%@ include file="footer.jsp" %>
</body>
</html>
