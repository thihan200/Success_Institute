package lk.ijse.successInstitute.view.tm;

import javafx.scene.control.Button;

import java.awt.*;

public class StudentTM {
    private String sid;
    private String studentname;
    private String address;
    private String contact;
    private String dob;
    private String gender;
    private String bid;
    private String school;
    private String subject;
    private Button btn;

    public StudentTM() {
    }

    public StudentTM(String sid, String studentname, String address, String contact, String dob, String gender, String bid, String school, String subject, Button btn) {
        this.sid = sid;
        this.studentname = studentname;
        this.address = address;
        this.contact = contact;
        this.dob = dob;
        this.gender = gender;
        this.bid = bid;
        this.school = school;
        this.subject = subject;
        this.btn = btn;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
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

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}
