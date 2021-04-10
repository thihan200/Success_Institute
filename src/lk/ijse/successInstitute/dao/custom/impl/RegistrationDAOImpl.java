package lk.ijse.successInstitute.dao.custom.impl;

import lk.ijse.successInstitute.dao.CrudUtil;
import lk.ijse.successInstitute.dao.custom.RegistrationDAO;
import lk.ijse.successInstitute.entity.Registration;
import rex.utils.S;

import java.sql.ResultSet;
import java.util.ArrayList;

public class RegistrationDAOImpl implements RegistrationDAO {
    @Override
    public boolean add(Registration registration) throws Exception {
        String sql = "insert into registration values(?,?,?)";
        return CrudUtil.executeUpdate(sql, registration.getRegId(), registration.getDate(), registration.getStuId());

    }

    @Override
    public boolean delete(String s) throws Exception {
        String sql = "delete from registration where RegID=?";
        return CrudUtil.executeUpdate(sql, s);
    }

    @Override
    public boolean update(Registration registration) throws Exception {
        String sql = "update registration set Date=?,StudentId=? where regID=?";
        return CrudUtil.executeUpdate(sql, registration.getDate(), registration.getStuId(), registration.getRegId());
    }

    @Override
    public Registration search(String s) throws Exception {
        String sql  = "select * from registration where regID=?";
        ResultSet rst = CrudUtil.executeQuery(sql);
        if (rst.next()){
            return new Registration(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3)
            );
        }
        return null;
    }

    @Override
    public ArrayList<Registration> getAll() throws Exception {
        String sql = "select * from registration";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<Registration> all = new ArrayList<>();
        while (rst.next()){
            all.add(new Registration(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3)
            ));
        }
        return all;
    }

    @Override
    public String getLastRegisterId() throws Exception {
        String  sql="select RegID From Registration order BY RegID desc LIMIT 1";
        ResultSet rst = CrudUtil.executeQuery(sql);
        return rst.next()?rst.getString("RegID"):null;
    }

    @Override
    public ArrayList<Registration> searchAllRegistration(String search) throws Exception {

        String sql = "select * from Registration where RegID like "+"'%"+search+"%'"+" || Date like "+"'%"+search+"%'"+" || StudentID like "+"'%"+search+"%'";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<Registration> all = new ArrayList<>();
        while (rst.next()){
            all.add(new Registration(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3)
            ));
        }
        return all;
    }
}
