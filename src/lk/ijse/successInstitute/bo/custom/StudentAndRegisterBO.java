package lk.ijse.successInstitute.bo.custom;

import lk.ijse.successInstitute.bo.SuperBO;
import lk.ijse.successInstitute.dto.BatchDTO;
import lk.ijse.successInstitute.dto.RegistrationDTO;
import lk.ijse.successInstitute.dto.StudentDTO;

import java.util.ArrayList;

public interface StudentAndRegisterBO extends SuperBO {

    public boolean delete(String id)throws Exception;

    public boolean update(StudentDTO studentDTO)throws Exception;

    public StudentDTO search(String id)throws Exception;

    public ArrayList<StudentDTO> getAll() throws Exception;

    public ArrayList<RegistrationDTO> getAllRegistration()throws Exception;

    public boolean registerStudent(StudentDTO studentDTO) throws Exception;

    public String getLastStudentId() throws Exception;

    public String getStudentCount()throws Exception;

    public ArrayList<BatchDTO> getBatchId()throws Exception;

    ArrayList<StudentDTO> searchAllStudent(String search)throws Exception;

    ArrayList<RegistrationDTO> searchAllRegistration(String search)throws Exception;

    public String getLastPaymentId() throws Exception;
}
