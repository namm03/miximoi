package controller;

import connect.DBConnect;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Department;
import model.Employee;
import model.Position;
import model.Salary;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@WebServlet("/ReportServlet")
public class ReportServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private Connection connection;

    @Override
    public void init() throws ServletException {
        try {
            connection = DBConnect.getConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "list":
                listDepartments(request, response);
                break;
            case "search":
                searchEmployeesByDepartment(request, response);
                break;
            case "print":
                printEmployeeList(request, response);
                break;
            default:
                response.sendRedirect(request.getContextPath() + "report.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private void listDepartments(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Department> departmentList = getDepartments();
        request.setAttribute("departmentList", departmentList);
        request.getRequestDispatcher("/report.jsp").forward(request, response);
    }

    private List<Department> getDepartments() {
        List<Department> departmentList = new ArrayList<>();
        String query = "SELECT * FROM department";
        try (PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
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

    private void searchEmployeesByDepartment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int departmentID = Integer.parseInt(request.getParameter("departmentID"));
        List<Employee> employeeList = new ArrayList<>();
        List<Department> departmentList = new ArrayList<>();
        List<Position> positionList = new ArrayList<>();
        List<Salary> salaryList = new ArrayList<>();

        String query = "SELECT e.employeeID, e.hoten, e.ngaysinh, e.gioitinh, e.sdt, " +
                "d.departmentName, p.positionName, s.luong " +
                "FROM employee e " +
                "JOIN position p ON e.positionID = p.positionID " +
                "JOIN department d ON p.departmentID = d.departmentID " +
                "JOIN salary s ON e.employeeID = s.employeeID " +
                "WHERE d.departmentID = ? " +
                "ORDER BY d.departmentName, e.hoten";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, departmentID);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Employee employee = new Employee();
                employee.setEmployeeID(rs.getInt("employeeID"));
                employee.setHoten(rs.getString("hoten"));
                employee.setNgaysinh(rs.getString("ngaysinh"));
                employee.setGioitinh(rs.getString("gioitinh"));
                employee.setSdt(rs.getInt("sdt"));

                Department department = new Department();
                department.setDepartmentName(rs.getString("departmentName"));

                Position position = new Position();
                position.setPositionName(rs.getString("positionName"));

                Salary salary = new Salary();
                salary.setLuong(rs.getDouble("luong"));

                employeeList.add(employee);
                departmentList.add(department);
                positionList.add(position);
                salaryList.add(salary);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("employeeList", employeeList);
        request.setAttribute("departmentList", departmentList);
        request.setAttribute("positionList", positionList);
        request.setAttribute("salaryList", salaryList);

        request.getRequestDispatcher("/report.jsp").forward(request, response);
    }

    private void printEmployeeList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Employee> employeeList = (List<Employee>) request.getAttribute("employeeList");

        if (employeeList != null && !employeeList.isEmpty()) {
            try {
                Document document = new Document();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                PdfWriter.getInstance(document, baos);

                document.open();

                // Add title
                Paragraph title = new Paragraph("Employee List");
                title.setAlignment(com.lowagie.text.Element.ALIGN_CENTER);
                document.add(title);
                document.add(new Paragraph("\n"));

                // Create table
                PdfPTable table = new PdfPTable(4); // 4 columns
                table.setWidthPercentage(100);

                // Add table headers
                table.addCell("Employee ID");
                table.addCell("Full Name");
                table.addCell("Department");
                table.addCell("Position");

                // Add table rows
                for (Employee employee : employeeList) {
                    table.addCell(String.valueOf(employee.getEmployeeID()));
                    table.addCell(employee.getHoten());
//                    table.addCell(employee.getDepartment().getDepartmentName());
//                    table.addCell(employee.getPosition().getPositionName());
                }

                document.add(table);
                document.close();

                // Set response headers
                response.setContentType("application/pdf");
                response.setHeader("Content-Disposition", "attachment; filename=employee_list.pdf");

                // Write PDF to response output stream
                response.getOutputStream().write(baos.toByteArray());
                response.getOutputStream().flush();
                response.getOutputStream().close();
            } catch (DocumentException | IOException e) {
                e.printStackTrace();
            }
        }
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
