package lk.ijse.successInstitute.view.tm;

import javafx.scene.control.Button;

public class BatchTM {
    private String bid;
    private String bname;
    private Button btn1;


    public BatchTM() {
    }

    public BatchTM(String bid, String bname, Button btn1) {
        this.bid = bid;
        this.bname = bname;
        this.btn1 = btn1;

    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public Button getBtn1() {
        return btn1;
    }

    public void setBtn1(Button btn1) {
        this.btn1 = btn1;
    }


}
