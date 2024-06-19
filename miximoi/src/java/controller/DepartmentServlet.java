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
import model.Department;

@WebServlet("/DepartmentServlet")
public class DepartmentServlet extends HttpServlet {

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
            case "list" -> listDepartments(request, response);
            case "new" -> showNewForm(request, response);
            case "insert" -> insertDepartment(request, response);
            case "edit" -> showEditForm(request, response);
            case "update" -> updateDepartment(request, response);
            case "delete" -> deleteDepartment(request, response);
            case "search" -> searchDepartmentsByName(request, response);
            default -> listDepartments(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private void listDepartments(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Department> departmentList = new ArrayList<>();
        String query = "SELECT * FROM department";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Department department = new Department();
                department.setDepartmentID(rs.getInt("departmentID"));
                department.setDepartmentName(rs.getString("departmentName"));
                departmentList.add(department);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("departmentList", departmentList);
        request.getRequestDispatcher("department.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("department-form.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int departmentID = Integer.parseInt(request.getParameter("departmentID"));
        Department department = getDepartmentById(departmentID);
        request.setAttribute("department", department);
        request.getRequestDispatcher("department-form.jsp").forward(request, response);
    }

    private void insertDepartment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String departmentName = request.getParameter("departmentName");

        String query = "INSERT INTO department (departmentName) VALUES (?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, departmentName);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("DepartmentServlet?action=list");
    }

    private void updateDepartment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int departmentID = Integer.parseInt(request.getParameter("departmentID"));
        String departmentName = request.getParameter("departmentName");

        String query = "UPDATE department SET departmentName = ? WHERE departmentID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, departmentName);
            pstmt.setInt(2, departmentID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("DepartmentServlet?action=list");
    }

    private void deleteDepartment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int departmentID = Integer.parseInt(request.getParameter("departmentID"));

        String query = "DELETE FROM department WHERE departmentID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, departmentID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("DepartmentServlet?action=list");
    }

    private Department getDepartmentById(int departmentID) {
        String query = "SELECT * FROM department WHERE departmentID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, departmentID);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Department department = new Department();
                    department.setDepartmentID(rs.getInt("departmentID"));
                    department.setDepartmentName(rs.getString("departmentName"));
                    return department;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void searchDepartmentsByName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String departmentName = request.getParameter("departmentName");

        if (departmentName == null || departmentName.trim().isEmpty()) {
            listDepartments(request, response);
            return;
        }

        List<Department> departmentList = new ArrayList<>();
        String query = "SELECT * FROM department WHERE departmentName LIKE ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, "%" + departmentName + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Department department = new Department();
                    department.setDepartmentID(rs.getInt("departmentID"));
                    department.setDepartmentName(rs.getString("departmentName"));
                    departmentList.add(department);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("departmentList", departmentList);
        request.getRequestDispatcher("department.jsp").forward(request, response);
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
