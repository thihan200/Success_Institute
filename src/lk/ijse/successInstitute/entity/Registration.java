package lk.ijse.successInstitute.entity;

public class Registration {
    private String regId;
    private String date;
    private String stuId;

    public Registration() {
    }

    public Registration(String regId, String date, String stuId) {
        this.regId = regId;
        this.date = date;
        this.stuId = stuId;
    }

    public String getRegId() {
        return regId;
    }

    public void setRegId(String regId) {
        this.regId = regId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }
}
