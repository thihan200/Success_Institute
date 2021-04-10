package lk.ijse.successInstitute.view.tm;

import javafx.scene.control.Button;

public class ClassTM {
    private String classid;
    private String classname;
    private String studentlimit;
    private Button btn;

    public ClassTM() {
    }

    public ClassTM(String classid, String classname, String studentlimit, Button btn) {
        this.classid = classid;
        this.classname = classname;
        this.studentlimit = studentlimit;
        this.btn = btn;
    }

    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getStudentlimit() {
        return studentlimit;
    }

    public void setStudentlimit(String studentlimit) {
        this.studentlimit = studentlimit;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}
