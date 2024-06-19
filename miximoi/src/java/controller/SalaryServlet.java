package controller;

import connect.DBConnect;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.salary;

@WebServlet("/SalaryServlet")
public class SalaryServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private Connection connection;

    @Override
    public void init() {
        try {
            connection = DBConnect.getConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "list":
                listSalaries(request, response);
                break;
            case "new":
                showNewForm(request, response);
                break;
            case "insert":
                insertSalary(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "update":
                updateSalary(request, response);
                break;
            case "delete":
                deleteSalary(request, response);
                break;
            case "search":
                searchSalary(request, response);
                break;
            default:
                listSalaries(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private void listSalaries(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<salary> salaryList = new ArrayList<>();
        String query = "SELECT * FROM salary";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                salary salary = new salary();
                salary.setEmployeeID(rs.getInt("employeeID"));
                salary.setLuongcoban(rs.getDouble("luongcoban"));
                salary.setHeso(rs.getInt("heso"));
                salary.setPhucap(rs.getDouble("phucap"));
                salary.setThuong(rs.getDouble("thuong"));
                salary.setPhat(rs.getDouble("phat"));
                salary.setLuong(rs.getDouble("luong")); // Set the total salary field
                salaryList.add(salary);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("salaryList", salaryList);
        request.getRequestDispatcher("salary.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("salary-form.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int employeeID = Integer.parseInt(request.getParameter("employeeID"));
        salary salary = getSalaryById(employeeID);
        request.setAttribute("salary", salary);
        request.getRequestDispatcher("salary-form.jsp").forward(request, response);
    }

    private void insertSalary(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int employeeID = Integer.parseInt(request.getParameter("employeeID"));
        Double luongcoban = Double.parseDouble(request.getParameter("luongcoban"));
        int heso = Integer.parseInt(request.getParameter("heso"));
        Double phucap = Double.parseDouble(request.getParameter("phucap"));
        Double thuong = Double.parseDouble(request.getParameter("thuong"));
        Double phat = Double.parseDouble(request.getParameter("phat"));

        // Calculate total salary
        Double luong = luongcoban * heso + phucap + thuong - phat;

        String query = "INSERT INTO salary (employeeID, luongcoban, heso, phucap, thuong, phat, luong) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, employeeID);
            pstmt.setDouble(2, luongcoban);
            pstmt.setInt(3, heso);
            pstmt.setDouble(4, phucap);
            pstmt.setDouble(5, thuong);
            pstmt.setDouble(6, phat);
            pstmt.setDouble(7, luong);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("SalaryServlet?action=list");
    }

    private void updateSalary(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int employeeID = Integer.parseInt(request.getParameter("employeeID"));
        Double luongcoban = Double.parseDouble(request.getParameter("luongcoban"));
        int heso = Integer.parseInt(request.getParameter("heso"));
        Double phucap = Double.parseDouble(request.getParameter("phucap"));
        Double thuong = Double.parseDouble(request.getParameter("thuong"));
        Double phat = Double.parseDouble(request.getParameter("phat"));

        // Calculate total salary
        Double luong = luongcoban * heso + phucap + thuong - phat;

        String query = "UPDATE salary SET luongcoban=?, heso=?, phucap=?, thuong=?, phat=?, luong=? WHERE employeeID=?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setDouble(1, luongcoban);
            pstmt.setInt(2, heso);
            pstmt.setDouble(3, phucap);
            pstmt.setDouble(4, thuong);
            pstmt.setDouble(5, phat);
            pstmt.setDouble(6, luong);
            pstmt.setInt(7, employeeID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("SalaryServlet?action=list");
    }

    private void deleteSalary(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int employeeID = Integer.parseInt(request.getParameter("employeeID"));

        String query = "DELETE FROM salary WHERE employeeID=?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, employeeID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("SalaryServlet?action=list");
    }

    private void searchSalary(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String employeeIDParam = request.getParameter("employeeID");
        List<salary> salaryList = new ArrayList<>();
        String query;
        PreparedStatement pstmt = null;

        try {
            // If employeeIDParam is empty or null, retrieve all salaries; otherwise, filter by employeeID
            if (employeeIDParam == null || employeeIDParam.isEmpty()) {
                query = "SELECT * FROM salary";
                pstmt = connection.prepareStatement(query);
            } else {
                query = "SELECT * FROM salary WHERE employeeID = ?";
                pstmt = connection.prepareStatement(query);
                pstmt.setInt(1, Integer.parseInt(employeeIDParam));
            }

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    salary salary = new salary();
                    salary.setEmployeeID(rs.getInt("employeeID"));
                    salary.setLuongcoban(rs.getDouble("luongcoban"));
                    salary.setHeso(rs.getInt("heso"));
                    salary.setPhucap(rs.getDouble("phucap"));
                    salary.setThuong(rs.getDouble("thuong"));
                    salary.setPhat(rs.getDouble("phat"));
                    salary.setLuong(rs.getDouble("luong")); // Set the total salary field
                    salaryList.add(salary);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        request.setAttribute("salaryList", salaryList);
        request.getRequestDispatcher("salary.jsp").forward(request, response);
    }

    private salary getSalaryById(int employeeID) {
        String query = "SELECT * FROM salary WHERE employeeID=?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, employeeID);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    salary salary = new salary();
                    salary.setEmployeeID(rs.getInt("employeeID"));
                    salary.setLuongcoban(rs.getDouble("luongcoban"));
                    salary.setHeso(rs.getInt("heso"));
                    salary.setPhucap(rs.getDouble("phucap"));
                    salary.setThuong(rs.getDouble("thuong"));
                    salary.setPhat(rs.getDouble("phat"));
                    salary.setLuong(rs.getDouble("luong")); // Set the total salary field
                    return salary;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void destroy() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
