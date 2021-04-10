package lk.ijse.successInstitute.dao.custom.impl;

import lk.ijse.successInstitute.dao.CrudUtil;
import lk.ijse.successInstitute.dao.custom.ClassDAO;
import lk.ijse.successInstitute.entity.Class;

import java.sql.ResultSet;
import java.util.ArrayList;

public class ClassDAOImpl implements ClassDAO {
    @Override
    public boolean add(Class aClass) throws Exception {
        String sql = "INSERT INTO Class VALUES(?,?,?)";
        return CrudUtil.executeUpdate(sql, aClass.getClassid(), aClass.getClassname(), aClass.getStudentlimit());
    }

    @Override
    public boolean delete(String s) throws Exception {
        String sql = "DELETE FROM Class WHERE CID=?";
        return CrudUtil.executeUpdate(sql, s);
    }

    @Override
    public boolean update(Class aClass) throws Exception {
        String sql = "UPDATE Class set className=?,StudentLimit=? WHERE CID=?";
        return CrudUtil.executeUpdate(sql, aClass.getClassname(), aClass.getStudentlimit(), aClass.getClassid());
    }

    @Override
    public Class search(String s) throws Exception {
        String sql = "SELECT * FROM Class WHERE CID=?";
        ResultSet rst = CrudUtil.executeQuery(sql, s);
        if(rst.next()){
            return new Class(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3)
            );
        }
        return null;
    }

    @Override
    public ArrayList<Class> getAll() throws Exception {
        String sql = "SELECT * FROM Class";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<Class> all = new ArrayList<>();
        while (rst.next()){
            all.add(new Class(
               rst.getString(1),
               rst.getString(2),
               rst.getString(3)
            ));
        }
        return all;
    }

    @Override
    public String getLastClassId() throws Exception {
        String sql = "select CID From Class ORDER BY CID DESC LIMIT 1";
        ResultSet rst = CrudUtil.executeQuery(sql);
        return rst.next()?rst.getString("CID"):null;
    }

    @Override
    public String getClassCount() throws Exception {

        String  sql="SELECT COUNT(CID) FROM Class";
        ResultSet rst = CrudUtil.executeQuery(sql);
        return rst.next()?rst.getString(1):null;
    }

    @Override
    public ArrayList<Class> searchAllClass(String search) throws Exception {
        String sql = "select * from class where CID like "+"'%"+search+"%'"+" || className like "+"'%"+search+"%'"+" || StudentLimit like "+"'%"+search+"%'";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<Class> all= new ArrayList<>();
        while (rst.next()){
            all.add(new Class(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3)
            ));
        }
        return all;
    }
}
