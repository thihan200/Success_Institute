package lk.ijse.successInstitute.bo.custom.impl;

import lk.ijse.successInstitute.bo.custom.EmployeeBO;
import lk.ijse.successInstitute.dao.DAOFactory;
import lk.ijse.successInstitute.dao.custom.EmployeeDAO;
import lk.ijse.successInstitute.dto.EmployeeDTO;
import lk.ijse.successInstitute.entity.Employee;

import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBO {
    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.EMPLOYEE);

    @Override
    public boolean add(EmployeeDTO employeeDTO) throws Exception {
        Employee emp = new Employee(employeeDTO.getEid(), employeeDTO.getEmployeename(), employeeDTO.getAddress(), employeeDTO.getContact(), employeeDTO.getDob(), employeeDTO.getGender(), employeeDTO.getPosition());
        return employeeDAO.add(emp);
    }

    @Override
    public boolean delete(String id) throws Exception {
        return employeeDAO.delete(id);
    }

    @Override
    public boolean update(EmployeeDTO employeeDTO) throws Exception {
        return employeeDAO.update(new Employee(employeeDTO.getEid(), employeeDTO.getEmployeename(), employeeDTO.getAddress(), employeeDTO.getContact(), employeeDTO.getDob(), employeeDTO.getGender(), employeeDTO.getPosition()));
    }

    @Override
    public EmployeeDTO search(String id) throws Exception {
        Employee search = employeeDAO.search(id);
        return new EmployeeDTO(search.getEid(), search.getEmployeename(), search.getAddress(), search.getContact(), search.getDob(), search.getGender(), search.getPosition());
    }

    @Override
    public ArrayList<EmployeeDTO> getAll() throws Exception {
        ArrayList<Employee> all = employeeDAO.getAll();
        ArrayList<EmployeeDTO> allEmployee = new ArrayList<>();
        for (Employee e:all){
            allEmployee.add(new EmployeeDTO(e.getEid(), e.getEmployeename(), e.getAddress(), e.getContact(), e.getDob(), e.getGender(), e.getPosition()));
        }
        return allEmployee;
    }

    @Override
    public String getLastEmployeeId() throws Exception {
        return employeeDAO.getLastEmployeeId();
    }

    @Override
    public ArrayList<EmployeeDTO> searchAllEmployee(String search) throws Exception {
        ArrayList<Employee> all = employeeDAO.searchAllEmployee(search);
        ArrayList<EmployeeDTO> allEmployee = new ArrayList<>();
        for (Employee e : all){
            allEmployee.add(new EmployeeDTO(e.getEid(), e.getEmployeename(), e.getAddress(), e.getContact(), e.getDob(), e.getGender(), e.getPosition()));
        }
        return allEmployee;
    }
}
