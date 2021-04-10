package lk.ijse.successInstitute.bo.custom;

import lk.ijse.successInstitute.bo.SuperBO;
import lk.ijse.successInstitute.dto.SubjectDTO;
import lk.ijse.successInstitute.dto.TeacherDTO;

import java.util.ArrayList;

public interface SubjectBO extends SuperBO {
    public boolean add(SubjectDTO subjectDTO)throws Exception;

    public boolean delete(String id)throws Exception;

    public boolean update(SubjectDTO subjectDTO)throws Exception;

    public SubjectDTO search(String id)throws Exception;

    public ArrayList<SubjectDTO> getAll() throws Exception;

    public ArrayList<TeacherDTO> getAllTeacher() throws Exception;

    public  TeacherDTO searchTeacher(String tid)throws Exception;

    public ArrayList<SubjectDTO> searchAllSubject (String search)throws Exception;
}

