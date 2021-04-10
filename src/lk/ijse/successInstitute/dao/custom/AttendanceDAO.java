package lk.ijse.successInstitute.dao.custom;

import lk.ijse.successInstitute.dao.CrudDAO;
import lk.ijse.successInstitute.entity.Attendance;

import java.util.ArrayList;

public interface AttendanceDAO extends CrudDAO<Attendance, String> {
    public boolean deleteAll (String s,String y)throws Exception;

    public ArrayList<Attendance> searchAllAttendance(String search)throws Exception;
}
