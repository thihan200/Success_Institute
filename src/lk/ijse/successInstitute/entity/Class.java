package lk.ijse.successInstitute.entity;

public class Class {
    private String classid;
    private String classname;
    private String studentlimit;

    public Class() {
    }

    public Class(String classid, String classname, String studentlimit) {
        this.classid = classid;
        this.classname = classname;
        this.studentlimit = studentlimit;
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
}
