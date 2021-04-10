package lk.ijse.successInstitute.bo.custom;

import lk.ijse.successInstitute.bo.SuperBO;
import lk.ijse.successInstitute.dto.AttendanceDTO;
import lk.ijse.successInstitute.dto.StudentDTO;
import lk.ijse.successInstitute.dto.SubjectDTO;
import lk.ijse.successInstitute.entity.Attendance;

import java.util.ArrayList;

public interface AttendanceBO extends SuperBO {
    public boolean add(AttendanceDTO attendanceDTO)throws Exception;

    public boolean delete(String id)throws Exception;

    public boolean update(AttendanceDTO attendanceDTO)throws Exception;

    public AttendanceDTO search(String id)throws Exception;

    public ArrayList<AttendanceDTO> getAll() throws Exception;

    public ArrayList<StudentDTO> getAllStudent() throws Exception;

    public ArrayList<SubjectDTO> getAllSubject() throws Exception;

    public boolean deleteAll (String s,String y)throws Exception;

    public ArrayList<AttendanceDTO> searchAllAttend(String search)throws Exception;

    public ArrayList<StudentDTO> comboSearchStudent(String search)throws Exception;


}
