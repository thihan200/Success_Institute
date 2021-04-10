package lk.ijse.successInstitute.view.tm;

import javafx.scene.control.Button;

public class TimetableTM {
    private String suName;
    private String teacherName;
    private String batchid;
    private String date;
    private String time;
    private Button btn;

    public TimetableTM() {
    }

    public TimetableTM(String suName, String teacherName, String batchid, String date, String time, Button btn) {
        this.suName = suName;
        this.teacherName = teacherName;
        this.batchid = batchid;
        this.date = date;
        this.time = time;
        this.btn = btn;
    }

    public String getSuName() {
        return suName;
    }

    public void setSuName(String suName) {
        this.suName = suName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getBatchid() {
        return batchid;
    }

    public void setBatchid(String batchid) {
        this.batchid = batchid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    @Override
    public String toString() {
        return "TimetableTM{" +
                "suName='" + suName + '\'' +
                ", tName='" + teacherName + '\'' +
                ", batchid='" + batchid + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", btn=" + btn +
                '}';
    }
}
