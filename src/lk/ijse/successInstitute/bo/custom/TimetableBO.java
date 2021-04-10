package lk.ijse.successInstitute.bo.custom;

import lk.ijse.successInstitute.bo.SuperBO;
import lk.ijse.successInstitute.dto.BatchDTO;
import lk.ijse.successInstitute.dto.SubjectDTO;
import lk.ijse.successInstitute.dto.TeacherDTO;
import lk.ijse.successInstitute.dto.TimetableDTO;

import java.util.ArrayList;

public interface TimetableBO extends SuperBO {
    public boolean add(TimetableDTO timetableDTO)throws Exception;

    public boolean delete(String id)throws Exception;

    public boolean update(TimetableDTO timetableDTO)throws Exception;

    public TimetableDTO search(String id)throws Exception;

    public ArrayList<TimetableDTO> getAll() throws Exception;

    public boolean deleteAll(String x, String y, String z)throws Exception;

    public  ArrayList<BatchDTO> getAllBath() throws Exception;

    public ArrayList<SubjectDTO> geyAllSubject() throws Exception;

    public ArrayList<TeacherDTO> getAllTeacher() throws Exception;
}
