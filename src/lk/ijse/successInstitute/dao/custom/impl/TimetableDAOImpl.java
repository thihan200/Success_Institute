package lk.ijse.successInstitute.dao.custom.impl;

import lk.ijse.successInstitute.dao.CrudUtil;
import lk.ijse.successInstitute.dao.custom.TimetableDAO;
import lk.ijse.successInstitute.entity.Timetable;

import java.sql.ResultSet;
import java.util.ArrayList;

public class TimetableDAOImpl implements TimetableDAO {
    @Override
    public boolean add(Timetable timetable) throws Exception {
        String sql = "INSERT INTO Timetable VALUES(?,?,?,?,?)";
        return CrudUtil.executeUpdate(sql, timetable.getSuName(), timetable.gettName(), timetable.getBatchid(), timetable.getDate(), timetable.getTime());
    }

    @Override
    public boolean delete(String s) throws Exception {
        String sql = " DELETE from timetable where SubjectName=? and BID=? and day=?";
        return CrudUtil.executeUpdate(sql, s);
    }

    @Override
    public boolean update(Timetable timetable) throws Exception {
        String sql = "UPDATE timetable set TeacherName=?,tme=?,day=? WHERE BID=? and SubjectName=?";
        return CrudUtil.executeUpdate(sql, timetable.gettName(), timetable.getTime(), timetable.getDate(), timetable.getBatchid(), timetable.getSuName());
    }

    @Override
    public Timetable search(String s) throws Exception {
        String sql = "SELECT * FROM Timetable WHERE SubjectName=? and BID=? and day=?";
        ResultSet rst = CrudUtil.executeQuery(sql, s);
        if (rst.next()){
            return new Timetable(
                 rst.getString(1),
                 rst.getString(2),
                 rst.getString(3),
                 rst.getString(4),
                 rst.getString(5)
            );
        }
        return null;
    }

    @Override
    public ArrayList<Timetable> getAll() throws Exception {
        String sql = "SELECT * FROM Timetable";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<Timetable> all = new ArrayList<>();
        while (rst.next()){
            all.add(new Timetable(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
            ));
        }
        return all;
    }

    @Override
    public boolean deleteAll(String x, String y, String z) throws Exception {
        String sql = " DELETE from timetable where SubjectName=? and BID=? and day=?";
        return CrudUtil.executeUpdate(sql, x, y, z);

    }
}
