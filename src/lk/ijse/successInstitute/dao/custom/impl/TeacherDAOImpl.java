package lk.ijse.successInstitute.dao.custom.impl;

import lk.ijse.successInstitute.dao.CrudUtil;
import lk.ijse.successInstitute.dao.custom.TeacherDAO;
import lk.ijse.successInstitute.entity.Teacher;

import java.sql.ResultSet;
import java.util.ArrayList;

public class TeacherDAOImpl implements TeacherDAO {
    @Override
    public boolean add(Teacher teacher) throws Exception {
        String sql = "INSERT INTO Teacher VALUES(?,?,?,?,?,?,?)";
        return CrudUtil.executeUpdate(sql, teacher.getTid(), teacher.getTeachername(), teacher.getAddress(), teacher.getContact(), teacher.getDob(), teacher.getGender(), teacher.getQualification());
    }

    @Override
    public boolean delete(String s) throws Exception {
        String sql = "DELETE FROM Teacher WHERE TID=?";
        return CrudUtil.executeUpdate(sql, s);
    }

    @Override
    public boolean update(Teacher teacher) throws Exception {
        String sql = "UPDATE Teacher SET TeacherName=?,Address=?,Contact=?,DOB=?,Gender=?,Qualification=? WHERE TID=?";
        return CrudUtil.executeUpdate(sql, teacher.getTeachername(), teacher.getAddress(), teacher.getContact(), teacher.getDob(), teacher.getGender(), teacher.getQualification(),  teacher.getTid());
    }

    @Override
    public Teacher search(String s) throws Exception {
        String sql = "SELECT * FROM Teacher WHERE TID=?";
        ResultSet rst = CrudUtil.executeQuery(sql, s);
        if (rst.next()){
            return new Teacher(
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
    public ArrayList<Teacher> getAll() throws Exception {
        String sql = "SELECT * FROM Teacher";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<Teacher> all = new ArrayList<>();
        while (rst.next()){
            all.add(new Teacher(
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
    public String getTeacherCount() throws Exception {
        String  sql="SELECT COUNT(TID) FROM Teacher";
        ResultSet rst = CrudUtil.executeQuery(sql);
        return rst.next()?rst.getString(1):null;
    }

    @Override
    public ArrayList<Teacher> searchAllTeacher(String search) throws Exception {
        String sql = "select * from teacher where TID like "+"'%"+search+"%'"+" || TeacherName like "+"'%"+search+"%'"+" || Address like "+"'%"+search+"%'"+" || Contact like "+"'%"+search+"%'"+" || DOB like "+"'%"+search+"%'"+" || Gender like "+"'%"+search+"%'"+" || Qualification like "+"'%"+search+"%'";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<Teacher> all = new ArrayList<>();
        while (rst.next()){
            all.add(new Teacher(
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
