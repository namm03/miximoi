package controller;

import connect.DBConnect;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Employee;
import model.Position;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
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
            case "list" -> listEmployees(request, response);
            case "new" -> showNewForm(request, response);
            case "insert" -> insertEmployee(request, response);
            case "edit" -> showEditForm(request, response);
            case "update" -> updateEmployee(request, response);
            case "delete" -> deleteEmployee(request, response);
            case "search" -> searchEmployees(request, response);
            default -> listEmployees(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private void listEmployees(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Employee> employeeList = new ArrayList<>();
        String query = "SELECT * FROM employee";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setEmployeeID(rs.getInt("employeeID"));
                employee.setPositionID(rs.getInt("positionID"));
                employee.setHoten(rs.getString("hoten"));
                employee.setNgaysinh(rs.getString("ngaysinh"));
                employee.setGioitinh(rs.getString("gioitinh"));
                employee.setSdt(rs.getInt("sdt"));
                employee.setEmail(rs.getString("email"));
                employee.setDiachi(rs.getString("diachi"));
                employeeList.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("employeeList", employeeList);
        request.getRequestDispatcher("employee.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Position> positionList = getPositionList();
        request.setAttribute("positionList", positionList);
        request.getRequestDispatcher("employee-form.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int employeeID = Integer.parseInt(request.getParameter("employeeID"));
        Employee employee = getEmployeeById(employeeID);
        List<Position> positionList = getPositionList();
        request.setAttribute("employee", employee);
        request.setAttribute("positionList", positionList);
        request.getRequestDispatcher("employee-form.jsp").forward(request, response);
    }

    private void insertEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int positionID = Integer.parseInt(request.getParameter("positionID"));
        String hoten = request.getParameter("hoten");
        String ngaysinh = request.getParameter("ngaysinh");
        String gioitinh = request.getParameter("gioitinh");
        int sdt = Integer.parseInt(request.getParameter("sdt"));
        String email = request.getParameter("email");
        String diachi = request.getParameter("diachi");

        String query = "INSERT INTO employee (positionID, hoten, ngaysinh, gioitinh, sdt, email, diachi) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, positionID);
            pstmt.setString(2, hoten);
            pstmt.setString(3, ngaysinh);
            pstmt.setString(4, gioitinh);
            pstmt.setInt(5, sdt);
            pstmt.setString(6, email);
            pstmt.setString(7, diachi);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("EmployeeServlet?action=list");
    }

    private void updateEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int employeeID = Integer.parseInt(request.getParameter("employeeID"));
        int positionID = Integer.parseInt(request.getParameter("positionID"));
        String hoten = request.getParameter("hoten");
        String ngaysinh = request.getParameter("ngaysinh");
        String gioitinh = request.getParameter("gioitinh");
        int sdt = Integer.parseInt(request.getParameter("sdt"));
        String email = request.getParameter("email");
        String diachi = request.getParameter("diachi");

        String query = "UPDATE employee SET positionID = ?, hoten = ?, ngaysinh = ?, gioitinh = ?, sdt = ?, email = ?, diachi = ? WHERE employeeID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, positionID);
            pstmt.setString(2, hoten);
            pstmt.setString(3, ngaysinh);
            pstmt.setString(4, gioitinh);
            pstmt.setInt(5, sdt);
            pstmt.setString(6, email);
            pstmt.setString(7, diachi);
            pstmt.setInt(8, employeeID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("EmployeeServlet?action=list");
    }

    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int employeeID = Integer.parseInt(request.getParameter("employeeID"));

        String query = "DELETE FROM employee WHERE employeeID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, employeeID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("EmployeeServlet?action=list");
    }

    private void searchEmployees(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String hoten = request.getParameter("hoten");
        List<Employee> employeeList = new ArrayList<>();
        String query = "SELECT * FROM employee WHERE hoten LIKE ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, "%" + hoten + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Employee employee = new Employee();
                    employee.setEmployeeID(rs.getInt("employeeID"));
                    employee.setPositionID(rs.getInt("positionID"));
                    employee.setHoten(rs.getString("hoten"));
                    employee.setNgaysinh(rs.getString("ngaysinh"));
                    employee.setGioitinh(rs.getString("gioitinh"));
                    employee.setSdt(rs.getInt("sdt"));
                    employee.setEmail(rs.getString("email"));
                    employee.setDiachi(rs.getString("diachi"));
                    employeeList.add(employee);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("employeeList", employeeList);
        request.getRequestDispatcher("employee.jsp").forward(request, response);
    }

    private Employee getEmployeeById(int employeeID) {
        String query = "SELECT * FROM employee WHERE employeeID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, employeeID);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Employee employee = new Employee();
                    employee.setEmployeeID(rs.getInt("employeeID"));
                    employee.setPositionID(rs.getInt("positionID"));
                    employee.setHoten(rs.getString("hoten"));
                    employee.setNgaysinh(rs.getString("ngaysinh"));
                    employee.setGioitinh(rs.getString("gioitinh"));
                    employee.setSdt(rs.getInt("sdt"));
                    employee.setEmail(rs.getString("email"));
                    employee.setDiachi(rs.getString("diachi"));
                    return employee;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<Position> getPositionList() {
        List<Position> positionList = new ArrayList<>();
        String query = "SELECT * FROM position";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Position position = new Position();
                position.setPositionID(rs.getInt("positionID"));
                position.setDepartmentID(rs.getInt("departmentID"));
                position.setPositionName(rs.getString("positionName"));
                position.setHeso((int) rs.getFloat("heso"));
                positionList.add(position);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return positionList;
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
