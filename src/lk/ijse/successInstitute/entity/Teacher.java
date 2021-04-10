package lk.ijse.successInstitute.entity;

public class Teacher {
    private String tid;
    private String teachername;
    private String address;
    private String contact;
    private String dob;
    private String gender;
    private String qualification;

    public Teacher() {
    }

    public Teacher(String tid, String teachername, String address, String contact, String dob, String gender, String qualification) {
        this.tid = tid;
        this.teachername = teachername;
        this.address = address;
        this.contact = contact;
        this.dob = dob;
        this.gender = gender;
        this.qualification = qualification;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getTeachername() {
        return teachername;
    }

    public void setTeachername(String teachername) {
        this.teachername = teachername;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }
}
