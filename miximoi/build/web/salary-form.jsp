<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thông tin lương</title>
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
        .form-group .total-salary {
            font-weight: bold;
            font-size: 18px;
            margin-top: 10px;
        }
    </style>
</head>
<body>
    <%@ include file="header.jsp" %>
    
    <div class="form-container">
        <h2>${empty salary ? 'Thêm thông tin lương' : 'Cập nhật thông tin lương'}</h2>
        
        <form action="SalaryServlet" method="post">
             <input type="hidden" name="action" value="${empty salary ? 'insert' : 'update'}">
            <c:if test="${not empty salary}">
                    <input type="hidden" name="employeeID" value="${salary.employeeID}">
            </c:if>
            <div class="form-group">
                <label for="employeeID">Mã nhân viên:</label>
                <input type="text" id="employeeID" name="employeeID" required
                       value="${empty salary ? '' : salary.employeeID}" ${empty salary ? '' : 'readonly'}>
            </div>
            <div class="form-group">
                <label for="luongcoban">Lương cơ bản:</label>
                <input type="text" id="luongcoban" name="luongcoban" required
                       value="${empty salary ? '' : salary.luongcoban}">
            </div>
            <div class="form-group">
                <label for="heso">Hệ số:</label>
                <input type="text" id="heso" name="heso" required
                       value="${empty salary ? '' : salary.heso}">
            </div>
            <div class="form-group">
                <label for="phucap">Phụ cấp:</label>
                <input type="text" id="phucap" name="phucap" required
                       value="${empty salary ? '' : salary.phucap}">
            </div>
            <div class="form-group">
                <label for="thuong">Thưởng:</label>
                <input type="text" id="thuong" name="thuong" required
                       value="${empty salary ? '' : salary.thuong}">
            </div>
            <div class="form-group">
                <label for="phat">Phạt:</label>
                <input type="text" id="phat" name="phat" required
                       value="${empty salary ? '' : salary.phat}">
            </div>
<!--            <div class="form-group">
                <label for="luong">Tổng lương:</label>
                <input type="text" id="luong" name="luong" readonly
                       class="luong" value="${empty salary ? '' : salary.luong}">calculateTotalSalary(salary)}">
            </div>-->
            <div class="form-group">
                <button type="submit">${empty salary ? 'Thêm' : 'Cập nhật'}</button>
                <a href="SalaryServlet?action=list">Hủy</a>
            </div>
        </form>
    </div>

    <%@ include file="footer.jsp" %>
    
    <script>
//        // Function to calculate total salary
//        function calculateTotalSalary(salary) {
//            let luongcoban = parseFloat(document.getElementById('luongcoban').value);
//            let heso = parseInt(document.getElementById('heso').value);
//            let phucap = parseFloat(document.getElementById('phucap').value);
//            let thuong = parseFloat(document.getElementById('thuong').value);
//            let phat = parseFloat(document.getElementById('phat').value);
//
//            let luong = luongcoban * heso + phucap + thuong - phat;
//            return luong.toFixed(2); // Adjust the precision as needed
//        }
//
//        // Event listener to calculate total salary when inputs change
//        document.addEventListener('DOMContentLoaded', function() {
//            document.getElementById('luongcoban').addEventListener('input', function() {
//                document.getElementById('luong').value = calculateTotalSalary();
//            });
//
//            document.getElementById('heso').addEventListener('input', function() {
//                document.getElementById('luong').value = calculateTotalSalary();
//            });
//
//            document.getElementById('phucap').addEventListener('input', function() {
//                document.getElementById('luong').value = calculateTotalSalary();
//            });
//
//            document.getElementById('thuong').addEventListener('input', function() {
//                document.getElementById('luong').value = calculateTotalSalary();
//            });
//
//            document.getElementById('phat').addEventListener('input', function() {
//                document.getElementById('luong').value = calculateTotalSalary();
//            });
//        });
//    </script>
</body>
</html>
