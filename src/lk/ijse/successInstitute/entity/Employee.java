package lk.ijse.successInstitute.entity;

public class Employee {
    private String eid;
    private String employeename;
    private String address;
    private String contact;
    private String dob;
    private String gender;
    private String position;

    public Employee() {
    }

    public Employee(String eid, String employeename, String address, String contact, String dob, String gender, String position) {
        this.eid = eid;
        this.employeename = employeename;
        this.address = address;
        this.contact = contact;
        this.dob = dob;
        this.gender = gender;
        this.position = position;
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public String getEmployeename() {
        return employeename;
    }

    public void setEmployeename(String employeename) {
        this.employeename = employeename;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
