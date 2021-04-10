package lk.ijse.successInstitute.entity;

public class Attendance {
    private String sid;
    private String date;
    private String arrivaltime;
    private String subname;

    public Attendance() {
    }

    public Attendance(String sid, String date, String arrivaltime, String subname) {
        this.sid = sid;
        this.date = date;
        this.arrivaltime = arrivaltime;
        this.subname = subname;
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
}
