package lk.ijse.successInstitute.view.tm;

import javafx.scene.control.Button;

public class AttendanceTM {
    private String sid;
    private String date;
    private String arrivaltime;
    private String subname;
    private Button btn;

    public AttendanceTM() {
    }

    public AttendanceTM(String sid, String date, String arrivaltime, String subname, Button btn) {
        this.sid = sid;
        this.date = date;
        this.arrivaltime = arrivaltime;
        this.subname = subname;
        this.btn = btn;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getArrivaltime() {
        return arrivaltime;
    }

    public void setArrivaltime(String arrivaltime) {
        this.arrivaltime = arrivaltime;
    }

    public String getSubname() {
        return subname;
    }

    public void setSubname(String subname) {
        this.subname = subname;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}
