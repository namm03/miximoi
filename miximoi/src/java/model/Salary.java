package model;

/**
 *
 * @author namng
 */
public class Salary {
    private int employeeID;
    private Double luongcoban;
    private int heso;
    private Double phucap;
    private Double thuong;
    private Double phat;
    private Double luong;

    public Salary(){}
    
    public Salary(int employeeID, Double luongcoban, int heso, Double phucap, Double thuong, Double phat, Double luong) {
        this.employeeID = employeeID;
        this.luongcoban = luongcoban;
        this.heso = heso;
        this.phucap = phucap;
        this.thuong = thuong;
        this.phat = phat;
        this.luong = luong;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public Double getLuongcoban() {
        return luongcoban;
    }

    public void setLuongcoban(Double luongcoban) {
        this.luongcoban = luongcoban;
    }

    public int getHeso() {
        return heso;
    }

    public void setHeso(int heso) {
        this.heso = heso;
    }

    public Double getPhucap() {
        return phucap;
    }

    public void setPhucap(Double phucap) {
        this.phucap = phucap;
    }

    public Double getThuong() {
        return thuong;
    }

    public void setThuong(Double thuong) {
        this.thuong = thuong;
    }

    public Double getPhat() {
        return phat;
    }

    public void setPhat(Double phat) {
        this.phat = phat;
    }

    public Double getLuong() {
        return luong;
    }

    public void setLuong(Double luong) {
        this.luong = luong;
    }
    
}
