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
import model.Position;

@WebServlet("/PositionServlet")
public class PositionServlet extends HttpServlet {

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
            case "list" -> listPositions(request, response);
            case "new" -> showNewForm(request, response);
            case "insert" -> insertPosition(request, response);
            case "edit" -> showEditForm(request, response);
            case "update" -> updatePosition(request, response);
            case "delete" -> deletePosition(request, response);
            case "search" -> searchPositions(request, response);
            default -> listPositions(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private void listPositions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Position> positionList = new ArrayList<>();
        String query = "SELECT * FROM position";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Position position = new Position();
                position.setPositionID(rs.getInt("positionID"));
                position.setDepartmentID(rs.getInt("departmentID"));
                position.setPositionName(rs.getString("positionName"));
                position.setHeso(rs.getInt("heso"));
                positionList.add(position);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("positionList", positionList);
        request.getRequestDispatcher("position.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Department> departmentList = getDepartments();
        request.setAttribute("departmentList", departmentList);
        request.getRequestDispatcher("position-form.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int positionID = Integer.parseInt(request.getParameter("positionID"));
        Position position = getPositionById(positionID);
        List<Department> departmentList = getDepartments();
        request.setAttribute("position", position);
        request.setAttribute("departmentList", departmentList);
        request.getRequestDispatcher("position-form.jsp").forward(request, response);
    }

    private void insertPosition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int departmentID = Integer.parseInt(request.getParameter("departmentID"));
        String positionName = request.getParameter("positionName");
        int heso = Integer.parseInt(request.getParameter("heso"));

        String query = "INSERT INTO position (departmentID, positionName, heso) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, departmentID);
            pstmt.setString(2, positionName);
            pstmt.setInt(3, heso);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("PositionServlet?action=list");
    }

    private void updatePosition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int positionID = Integer.parseInt(request.getParameter("positionID"));
        int departmentID = Integer.parseInt(request.getParameter("departmentID"));
        String positionName = request.getParameter("positionName");
        int heso = Integer.parseInt(request.getParameter("heso"));

        String query = "UPDATE position SET departmentID = ?, positionName = ?, heso = ? WHERE positionID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, departmentID);
            pstmt.setString(2, positionName);
            pstmt.setInt(3, heso);
            pstmt.setInt(4, positionID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("PositionServlet?action=list");
    }

    private void deletePosition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int positionID = Integer.parseInt(request.getParameter("positionID"));

        String query = "DELETE FROM position WHERE positionID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, positionID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("PositionServlet?action=list");
    }

    private void searchPositions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String positionName = request.getParameter("positionName");
        List<Position> positionList = new ArrayList<>();
        String query = "SELECT * FROM position WHERE positionName LIKE ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, "%" + positionName + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Position position = new Position();
                    position.setPositionID(rs.getInt("positionID"));
                    position.setDepartmentID(rs.getInt("departmentID"));
                    position.setPositionName(rs.getString("positionName"));
                    position.setHeso(rs.getInt("heso"));
                    positionList.add(position);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("positionList", positionList);
        request.getRequestDispatcher("position.jsp").forward(request, response);
    }

    private Position getPositionById(int positionID) {
        String query = "SELECT * FROM position WHERE positionID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, positionID);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Position position = new Position();
                    position.setPositionID(rs.getInt("positionID"));
                    position.setDepartmentID(rs.getInt("departmentID"));
                    position.setPositionName(rs.getString("positionName"));
                    position.setHeso(rs.getInt("heso"));
                    return position;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<Department> getDepartments() {
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
        return departmentList;
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
