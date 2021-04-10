package lk.ijse.successInstitute.dao.custom.impl;

import lk.ijse.successInstitute.dao.CrudUtil;
import lk.ijse.successInstitute.dao.custom.StudentDAO;
import lk.ijse.successInstitute.dto.BatchDTO;
import lk.ijse.successInstitute.dto.StudentDTO;
import lk.ijse.successInstitute.entity.Student;

import java.sql.ResultSet;
import java.util.ArrayList;

public class StudentDAOImpl implements StudentDAO {
    @Override
    public boolean add(Student student) throws Exception {
        String sql = "INSERT INTO Student VALUES(?,?,?,?,?,?,?,?,?)";
        return CrudUtil.executeUpdate(sql, student.getSid(), student.getStudentname(), student.getAddress(), student.getContact(), student.getDob(), student.getGender(), student.getBid(), student.getSchool(), student.getSubject());

    }

    @Override
    public boolean delete(String s) throws Exception {
        String sql = "DELETE FROM Student where ID=?";
        return CrudUtil.executeUpdate(sql, s);
    }

    @Override
    public boolean update(Student student) throws Exception {
        String sql = "UPDATE Student set StudentName=?,Address=?,Contact=?,DOB=?,Gender=?,batchID=?,School=?,Subject=? WHERE ID=?";
        return CrudUtil.executeUpdate(sql, student.getStudentname(), student.getAddress(), student.getContact(), student.getDob(),student.getGender(), student.getBid(), student.getSchool(), student.getSubject(),  student.getSid());
    }

    @Override
    public Student search(String s) throws Exception {
        String sql = "SELECT * FROM Student WHERE ID like %?%";
        ResultSet rst =  CrudUtil.executeQuery(sql, s);
        if(rst.next()){
            return new Student(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getString(9)
            );
        }
        return null;
    }

    @Override
    public ArrayList<Student> getAll() throws Exception {
       String sql = "SELECT * FROM Student";
       ResultSet rst = CrudUtil.executeQuery(sql);
       ArrayList<Student>all = new ArrayList<>();
       while (rst.next()){
           all.add(new Student(
              rst.getString(1),
              rst.getString(2),
              rst.getString(3),
              rst.getString(4),
              rst.getString(5),
              rst.getString(6),
              rst.getString(7),
                   rst.getString(8),
                   rst.getString(9)
           ));
       }
       return all;
    }


    @Override
    public String getLastStudentId() throws Exception {
        String  sql="select ID From Student order BY ID desc LIMIT 1";
        ResultSet rst = CrudUtil.executeQuery(sql);
        return rst.next()?rst.getString("ID"):null;
    }

    @Override
    public String getStudentCount() throws Exception {
        String  sql="SELECT COUNT(ID) FROM Student";
        ResultSet rst = CrudUtil.executeQuery(sql);
        return rst.next()?rst.getString(1):null;
    }

    @Override
    public ArrayList<Student> searchAllStudent(String search) throws Exception {
        String sql = "select * from student where ID like "+"'%"+search+"%'"+" || StudentName like "+"'%"+search+"%'"+" || Address like "+"'%"+search+"%'"+" || Contact like "+"'%"+search+"%'"+" || DOB like "+"'%"+search+"%'"+" || Gender like "+"'%"+search+"%'"+" || batchID like "+"'%"+search+"%'"+" || School like "+"'%"+search+"%'"+" || Subject like "+"'%"+search+"%'";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<Student>all = new ArrayList<>();
        while (rst.next()){
            all.add(new Student(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getString(9)
            ));
        }
        return all;
    }


}
