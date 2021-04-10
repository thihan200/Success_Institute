package lk.ijse.successInstitute.entity;

public class Subject {
    private String suid;
    private String subjectname;
    private String teachername;
    private String tid;

    public Subject() {
    }

    public Subject(String suid, String subjectname, String teachername, String tid) {
        this.suid = suid;
        this.subjectname = subjectname;
        this.teachername = teachername;
        this.tid = tid;
    }

    public String getSuid() {
        return suid;
    }

    public void setSuid(String suid) {
        this.suid = suid;
    }

    public String getSubjectname() {
        return subjectname;
    }

    public void setSubjectname(String subjectname) {
        this.subjectname = subjectname;
    }

    public String getTeachername() {
        return teachername;
    }

    public void setTeachername(String teachername) {
        this.teachername = teachername;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }
}
