package lk.ijse.successInstitute.view.tm;

import javafx.scene.control.Button;

public class PaymentTM {
    private String pid;
    private String regfee;
    private String date;
    private String subname;
    private String sid;
    private double amount;
    private Button btn;

    public PaymentTM() {
    }

    public PaymentTM(String pid, String regfee, String date, String subname, String sid, double amount, Button btn) {
        this.pid = pid;
        this.regfee = regfee;
        this.date = date;
        this.subname = subname;
        this.sid = sid;
        this.amount = amount;
        this.btn = btn;
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

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}
