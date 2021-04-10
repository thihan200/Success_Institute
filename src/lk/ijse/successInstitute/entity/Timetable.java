package lk.ijse.successInstitute.entity;

public class Timetable {
    private String suName;
    private String tName;
    private String batchid;
    private String date;
    private String time;

    public Timetable() {
    }

    public Timetable(String suName, String tName, String batchid, String date, String time) {
        this.suName = suName;
        this.tName = tName;
        this.batchid = batchid;
        this.date = date;
        this.time = time;
    }

    public String getSuName() {
        return suName;
    }

    public void setSuName(String suName) {
        this.suName = suName;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
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
}
