package lk.ijse.successInstitute.dao.custom;

import lk.ijse.successInstitute.dao.CrudDAO;
import lk.ijse.successInstitute.entity.Employee;
import org.hibernate.mapping.Array;

import java.util.ArrayList;

public interface EmployeeDAO extends CrudDAO<Employee, String> {
    public String getLastEmployeeId() throws Exception;

    public ArrayList<Employee> searchAllEmployee(String search)throws Exception;
}
