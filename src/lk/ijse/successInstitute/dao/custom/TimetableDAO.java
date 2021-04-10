package lk.ijse.successInstitute.dao.custom;

import lk.ijse.successInstitute.dao.CrudDAO;
import lk.ijse.successInstitute.entity.Timetable;

public interface TimetableDAO extends CrudDAO<Timetable, String> {
    public boolean deleteAll(String x, String y, String z)throws Exception;
}
