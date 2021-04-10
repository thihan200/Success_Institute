package lk.ijse.successInstitute.dao.custom.impl;

import lk.ijse.successInstitute.dao.CrudUtil;
import lk.ijse.successInstitute.dao.custom.AttendanceDAO;
import lk.ijse.successInstitute.entity.Attendance;

import java.sql.ResultSet;
import java.util.ArrayList;

public class AttendanceDAOImpl implements AttendanceDAO {
    @Override
    public boolean add(Attendance attendance) throws Exception {
        String sql = "INSERT INTO attendance VALUES(?,?,?,?)";
        return CrudUtil.executeUpdate(sql, attendance.getSid(), attendance.getDate(), attendance.getArrivaltime(), attendance.getSubname());
    }

    @Override
    public boolean delete(String s) throws Exception {
        String sql = "DELETE FROM attendance where StudentId=? and Date=?";
        return CrudUtil.executeUpdate(sql, s);
    }

    @Override
    public boolean update(Attendance attendance) throws Exception {
        String sql = "update attendance set ArrivalTime=?,SubjectName=? where StudentId=? and Date=?";
        return CrudUtil.executeUpdate(sql, attendance.getArrivaltime(), attendance.getSubname(), attendance.getSid(), attendance.getDate());
    }

    @Override
    public Attendance search(String s) throws Exception {
        String sql = "select * from attendance where StudentId=?";
        ResultSet rst = CrudUtil.executeQuery(sql, s);
        if(rst.next()){
            return new Attendance(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            );
        }
        return null;

    }

    @Override
    public ArrayList<Attendance> getAll() throws Exception {
        String sql = "select * from attendance";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<Attendance>all= new ArrayList<>();
        while (rst.next()){
            all.add(new Attendance(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            ));
        }
        return all;
    }

    @Override
    public boolean deleteAll(String s, String y) throws Exception {
        String sql = "DELETE FROM attendance where StudentId=? and Date=?";
        return CrudUtil.executeUpdate(sql, s, y);
    }

    @Override
    public ArrayList<Attendance> searchAllAttendance(String search) throws Exception {

        String sql = "select * from attendance where StudentId like "+"'%"+search+"%'"+" || Date like "+"'%"+search+"%'"+" || ArrivalTime like "+"'%"+search+"%'"+" || SubjectName like "+"'%"+search+"%'";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<Attendance> all = new ArrayList<>();
        while (rst.next()){
            all.add(new Attendance(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            ));
        }
        return  all;
    }


}
