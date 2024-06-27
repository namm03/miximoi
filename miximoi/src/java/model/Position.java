package model;

/**
 *
 * @author namng
 */
public class Position {
    private int positionID;
    private int departmentID;
    private String positionName;
    private int heso;
    
    public Position(){}

    public Position(int positionID, int departmentID, String positionName, int heso) {
        this.positionID = positionID;
        this.departmentID = departmentID;
        this.positionName = positionName;
        this.heso = heso;
    }

    public int getPositionID() {
        return positionID;
    }

    public void setPositionID(int positionID) {
        this.positionID = positionID;
    }

    public int getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public int getHeso() {
        return heso;
    }

    public void setHeso(int heso) {
        this.heso = heso;
    }
    
}
