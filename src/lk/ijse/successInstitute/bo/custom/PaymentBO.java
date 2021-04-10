package lk.ijse.successInstitute.bo.custom;

import lk.ijse.successInstitute.bo.SuperBO;
import lk.ijse.successInstitute.dto.PaymentDTO;
import lk.ijse.successInstitute.dto.StudentDTO;
import lk.ijse.successInstitute.dto.SubjectDTO;

import java.util.ArrayList;

public interface PaymentBO extends SuperBO {
    public boolean add(PaymentDTO paymentDTO)throws Exception;

    public boolean delete(String id)throws Exception;

    public boolean update(PaymentDTO paymentDTO)throws Exception;

    public PaymentDTO search(String id)throws Exception;

    public ArrayList<PaymentDTO> getAll() throws Exception;

    public String getLastPID()throws Exception;

   public ArrayList<SubjectDTO> getAllSubject()throws Exception;

   public ArrayList<StudentDTO> getAllStudent() throws Exception;

   public ArrayList<PaymentDTO> searchAllPayment(String search) throws Exception;

    public ArrayList<StudentDTO> comboSearchStudent(String search)throws Exception;
}
