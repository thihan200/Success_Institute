package lk.ijse.successInstitute.dao.custom.impl;

import lk.ijse.successInstitute.dao.CrudUtil;
import lk.ijse.successInstitute.dao.custom.SubjectDAO;
import lk.ijse.successInstitute.entity.Subject;
import lk.ijse.successInstitute.entity.Teacher;

import java.sql.ResultSet;
import java.util.ArrayList;

public class SubjectDAOImpl implements SubjectDAO {
    @Override
    public boolean add(Subject subject) throws Exception {
        String sql = "INSERT INTO subject VALUES(?,?,?,?)";
        return CrudUtil.executeUpdate(sql, subject.getSuid(), subject.getSubjectname(), subject.getTeachername(), subject.getTid());
    }

    @Override
    public boolean delete(String s) throws Exception {
        String sql = "DELETE FROM subject WHERE SuID=?";
        return CrudUtil.executeUpdate(sql, s);
    }

    @Override
    public boolean update(Subject subject) throws Exception {
        String sql = "UPDATE subject SET SubjectName=?,TeacherName=?,TID=? WHERE SuID=?";
        return CrudUtil.executeUpdate(sql, subject.getSubjectname(), subject.getTeachername(), subject.getTid(), subject.getSuid());
    }

    @Override
    public Subject search(String s) throws Exception {
        String sql = "SELECT * FROM subject WHERE SuID=?";
        ResultSet rst = CrudUtil.executeQuery(sql, s);
        if(rst.next()){
            return new Subject(
              rst.getString(1),
              rst.getString(2),
              rst.getString(3),
              rst.getString(4)
            );
        }
        return null;
    }

    @Override
    public ArrayList<Subject> getAll() throws Exception {
        String sql = "SELECT * FROM subject";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<Subject> all = new ArrayList<>();
        while (rst.next()){
            all.add(new Subject(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            ));
        }
        return all;
    }

    @Override
    public ArrayList<Subject> searchAllSubject(String search) throws Exception {
        String sql  = "select * from  subject where SuID like "+"'%"+search+"%'"+" || SubjectName like "+"'%"+search+"%'"+" || TeacherName like "+"'%"+search+"%'"+" || TID like "+"'%"+search+"%'";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<Subject> all = new ArrayList<>();
        while (rst.next()){
            all.add(new Subject(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            ));
        }
        return all;
    }
}
