package lk.ijse.successInstitute.entity;

public class Batch {
    private String bid;
    private String bname;

    public Batch() {
    }

    public Batch(String bid, String bname) {
        this.bid = bid;
        this.bname = bname;
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
}