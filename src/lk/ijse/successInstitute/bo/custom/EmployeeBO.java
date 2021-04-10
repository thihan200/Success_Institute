package lk.ijse.successInstitute.bo.custom;

import lk.ijse.successInstitute.bo.SuperBO;
import lk.ijse.successInstitute.dto.EmployeeDTO;

import java.util.ArrayList;

public interface EmployeeBO extends SuperBO {
    public boolean add(EmployeeDTO employeeDTO)throws Exception;

    public boolean delete(String id)throws Exception;

    public boolean update(EmployeeDTO employeeDTO)throws Exception;

    public EmployeeDTO search(String id)throws Exception;

    public ArrayList<EmployeeDTO> getAll() throws Exception;

    public String getLastEmployeeId() throws Exception;

    public ArrayList<EmployeeDTO> searchAllEmployee(String search)throws Exception;
}
