package model;
import model.Department;
import model.Position;
import model.Salary;

/**
 *
 * @author namng
 */
public class Employee {
    private int employeeID;
    private int positionID;
    private String hoten;
    private String ngaysinh;
    private String gioitinh;
    private int sdt;
    private String email;
    private String diachi;
    private Department department;
    private Position position;
    private Salary salary;

    public Employee(int employeeID, int positionID, String hoten, String ngaysinh, String gioitinh, int sdt, String email, String diachi, Department department, Position position, Salary salary) {
        this.employeeID = employeeID;
        this.positionID = positionID;
        this.hoten = hoten;
        this.ngaysinh = ngaysinh;
        this.gioitinh = gioitinh;
        this.sdt = sdt;
        this.email = email;
        this.diachi = diachi;
        this.department = department;
        this.position = position;
        this.salary = salary;
    }
    
    
    public Employee(){}

    public Employee(int employeeID, int positionID, String hoten, String ngaysinh, String gioitinh, int sdt, String email, String diachi) {
        this.employeeID = employeeID;
        this.positionID = positionID;
        this.hoten = hoten;
        this.ngaysinh = ngaysinh;
        this.gioitinh = gioitinh;
        this.sdt = sdt;
        this.email = email;
        this.diachi = diachi;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public int getPositionID() {
        return positionID;
    }

    public void setPositionID(int positionID) {
        this.positionID = positionID;
    }
    
    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public int getSdt() {
        return sdt;
    }

    public void setSdt(int sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Salary getSalary() {
        return salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }


}
