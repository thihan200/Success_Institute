package lk.ijse.successInstitute.dao.custom;

import lk.ijse.successInstitute.dao.CrudDAO;
import lk.ijse.successInstitute.entity.Registration;

import java.util.ArrayList;

public interface RegistrationDAO extends CrudDAO<Registration, String> {
    public String getLastRegisterId()throws Exception;

    public ArrayList<Registration> searchAllRegistration(String search)throws Exception;
}
