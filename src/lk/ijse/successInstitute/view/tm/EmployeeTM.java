package lk.ijse.successInstitute.view.tm;

import javafx.scene.control.Button;

public class EmployeeTM {
    private String eid;
    private String employeename;
    private String address;
    private String contact;
    private String dob;
    private String gender;
    private String position;
    private Button btn;

    public EmployeeTM() {
    }

    public EmployeeTM(String eid, String employeename, String address, String contact, String dob, String gender, String position, Button btn) {
        this.eid = eid;
        this.employeename = employeename;
        this.address = address;
        this.contact = contact;
        this.dob = dob;
        this.gender = gender;
        this.position = position;
        this.btn = btn;
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

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}
