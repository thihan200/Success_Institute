package lk.ijse.successInstitute.bo.custom;

import lk.ijse.successInstitute.bo.SuperBO;
import lk.ijse.successInstitute.dto.TeacherDTO;

import java.util.ArrayList;

public interface TeacherBO extends SuperBO {
    public boolean add(TeacherDTO teacherDTO)throws Exception;

    public boolean delete(String id)throws Exception;

    public boolean update(TeacherDTO teacherDTO)throws Exception;

    public TeacherDTO search(String id)throws Exception;

    public ArrayList<TeacherDTO> getAll() throws Exception;

    public String getTeacherCount()throws Exception;

    public ArrayList<TeacherDTO> searchAllTecher(String search) throws Exception;


}
