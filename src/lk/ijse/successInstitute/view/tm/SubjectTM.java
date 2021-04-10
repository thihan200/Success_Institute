package lk.ijse.successInstitute.view.tm;

import javafx.scene.control.Button;

public class SubjectTM {
    private String suid;
    private String subjectname;
    private String teachername;
    private String tid;
    private Button btn;

    public SubjectTM() {
    }

    public SubjectTM(String suid, String subjectname, String teachername, String tid, Button btn) {
        this.suid = suid;
        this.subjectname = subjectname;
        this.teachername = teachername;
        this.tid = tid;
        this.btn = btn;
    }

    public String getSuid() {
        return suid;
    }

    public void setSuid(String suid) {
        this.suid = suid;
    }

    public String getSubjectname() {
        return subjectname;
    }

    public void setSubjectname(String subjectname) {
        this.subjectname = subjectname;
    }

    public String getTeachername() {
        return teachername;
    }

    public void setTeachername(String teachername) {
        this.teachername = teachername;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}
