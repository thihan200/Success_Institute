package lk.ijse.successInstitute.dao.custom;

import lk.ijse.successInstitute.dao.CrudDAO;
import lk.ijse.successInstitute.dto.StudentDTO;
import lk.ijse.successInstitute.entity.Payment;

import java.util.ArrayList;

public interface PaymentDAO extends CrudDAO<Payment, String> {
    public String getLastPID()throws Exception;

    public ArrayList<Payment> searchAllPayment(String search)throws Exception;

}
