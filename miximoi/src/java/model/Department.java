package model;

/**
 *
 * @author namng
 */
public class Department {
    private int departmentID;
    private String departmentName;
    
    public Department(){}

    public Department(int departmentID, String departmentName, String location) {
        this.departmentID = departmentID;
        this.departmentName = departmentName;
    }

    public int getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
    
}
