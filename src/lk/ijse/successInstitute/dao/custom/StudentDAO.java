package lk.ijse.successInstitute.dao.custom;

import lk.ijse.successInstitute.dao.CrudDAO;
import lk.ijse.successInstitute.dto.BatchDTO;
import lk.ijse.successInstitute.dto.StudentDTO;
import lk.ijse.successInstitute.entity.Student;

import java.util.ArrayList;

public interface StudentDAO extends CrudDAO<Student, String> {
    public String getLastStudentId()throws Exception;

    public String getStudentCount()throws Exception;

    public ArrayList<Student> searchAllStudent(String search)throws Exception;

}
