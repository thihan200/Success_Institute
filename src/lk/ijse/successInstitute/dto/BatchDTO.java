package lk.ijse.successInstitute.dto;

public class BatchDTO {
    private String bid;
    private String bname;

    public BatchDTO() {
    }

    public BatchDTO(String bid) {
        this.bid = bid;
    }

    public BatchDTO(String bid, String bname) {
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