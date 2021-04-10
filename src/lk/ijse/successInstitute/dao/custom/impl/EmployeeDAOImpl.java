package lk.ijse.successInstitute.dao.custom.impl;

import lk.ijse.successInstitute.dao.CrudUtil;
import lk.ijse.successInstitute.dao.custom.EmployeeDAO;
import lk.ijse.successInstitute.entity.Employee;

import java.sql.ResultSet;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public boolean add(Employee employee) throws Exception {
        String sql = "INSERT INTO employee VALUES(?,?,?,?,?,?,?)";
        return CrudUtil.executeUpdate(sql, employee.getEid(), employee.getEmployeename(), employee.getAddress(), employee.getContact(), employee.getDob(), employee.getGender(), employee.getPosition());
    }

    @Override
    public boolean delete(String s) throws Exception {
        String sql = "DELETE FROM employee WHERE EID=?";
        return CrudUtil.executeUpdate(sql, s);
    }

    @Override
    public boolean update(Employee employee) throws Exception {
        String sql = "Update employee set EmployeeName=?,Address=?,Contact=?,DOB=?,Gender=?,Postion=? where EID=?";
        return CrudUtil.executeUpdate(sql, employee.getEmployeename(), employee.getAddress(), employee.getContact(), employee.getDob(), employee.getGender(), employee.getPosition(), employee.getEid());
    }

    @Override
    public Employee search(String s) throws Exception {
        String sql = "SELECT * FROM employee WHERE EID=?";
        ResultSet rst = CrudUtil.executeQuery(sql, s);
        if (rst.next()){
            return new Employee(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7)
            );
        }
        return null;
    }

    @Override
    public ArrayList<Employee> getAll() throws Exception {
        String sql = "SELECT * FROM employee";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<Employee> all = new ArrayList<>();
        while (rst.next()){
            all.add(new Employee(
               rst.getString(1),
               rst.getString(2),
               rst.getString(3),
               rst.getString(4),
               rst.getString(5),
               rst.getString(6),
               rst.getString(7)
            ));
        }
        return all;
    }

    @Override
    public String getLastEmployeeId() throws Exception {
        String  sql="select EID From employee order BY EID desc LIMIT 1";
        ResultSet rst = CrudUtil.executeQuery(sql);
        return rst.next()?rst.getString("EID"):null;
    }

    @Override
    public ArrayList<Employee> searchAllEmployee(String search) throws Exception {
        String sql = "select * from employee where EID like "+"'%"+search+"%'"+" || EmployeeName like "+"'%"+search+"%'"+" || Address like "+"'%"+search+"%'"+" || Contact like "+"'%"+search+"%'"+" || DOB like "+"'%"+search+"%'"+" || Gender like "+"'%"+search+"%'"+" || Postion like "+"'%"+search+"%'";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<Employee> all = new ArrayList<>();
        while (rst.next()){
            all.add(new Employee(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7)
            ));
        }
        return all;
    }
}
