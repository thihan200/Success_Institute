package lk.ijse.successInstitute.dto;

public class PaymentDTO {
    private String pid;
    private String regfee;
    private String date;
    private String subname;
    private String sid;
    private double amount;

    public PaymentDTO() {
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getRegfee() {
        return regfee;
    }

    public void setRegfee(String regfee) {
        this.regfee = regfee;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSubname() {
        return subname;
    }

    public void setSubname(String subname) {
        this.subname = subname;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public PaymentDTO(String pid, String regfee, String date, String subname, String sid, double amount) {
        this.pid = pid;
        this.regfee = regfee;
        this.date = date;
        this.subname = subname;
        this.sid = sid;
        this.amount = amount;
    }
}
