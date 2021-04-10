package lk.ijse.successInstitute.dao.custom;

import lk.ijse.successInstitute.dao.CrudDAO;
import lk.ijse.successInstitute.entity.Teacher;

import java.util.ArrayList;

public interface TeacherDAO extends CrudDAO<Teacher, String> {
    public String getTeacherCount()throws Exception;

    public ArrayList<Teacher> searchAllTeacher(String search)throws Exception;
}
