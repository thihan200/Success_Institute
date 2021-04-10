package lk.ijse.successInstitute.bo.custom.impl;

import lk.ijse.successInstitute.bo.custom.AttendanceBO;
import lk.ijse.successInstitute.dao.DAOFactory;
import lk.ijse.successInstitute.dao.custom.AttendanceDAO;
import lk.ijse.successInstitute.dao.custom.StudentDAO;
import lk.ijse.successInstitute.dao.custom.SubjectDAO;
import lk.ijse.successInstitute.dto.AttendanceDTO;
import lk.ijse.successInstitute.dto.StudentDTO;
import lk.ijse.successInstitute.dto.SubjectDTO;
import lk.ijse.successInstitute.entity.Attendance;
import lk.ijse.successInstitute.entity.Student;
import lk.ijse.successInstitute.entity.Subject;

import java.util.ArrayList;

public class AttendanceBOImpl implements AttendanceBO {

    AttendanceDAO attendanceDAO = (AttendanceDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ATTENDANCE);
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.STUDENT);
    SubjectDAO subjectDAO = (SubjectDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.SUBJECT);

    @Override
    public boolean add(AttendanceDTO attendanceDTO) throws Exception {
        Attendance attendance = new Attendance(attendanceDTO.getSid(), attendanceDTO.getDate(), attendanceDTO.getArrivaltime(), attendanceDTO.getSubname());
        return attendanceDAO.add(attendance);
    }

    @Override
    public boolean delete(String id) throws Exception {
        return attendanceDAO.delete(id);
    }

    @Override
    public boolean update(AttendanceDTO attendanceDTO) throws Exception {
        return attendanceDAO.update(new Attendance(attendanceDTO.getSid(), attendanceDTO.getDate(), attendanceDTO.getArrivaltime(), attendanceDTO.getSubname()));
    }

    @Override
    public AttendanceDTO search(String id) throws Exception {
        Attendance search = attendanceDAO.search(id);
        return new AttendanceDTO(search.getSid(), search.getDate(), search.getArrivaltime(), search.getSubname());
    }

    @Override
    public ArrayList<AttendanceDTO> getAll() throws Exception {
        ArrayList<Attendance> all = attendanceDAO.getAll();
        ArrayList<AttendanceDTO> allAttendance = new ArrayList<>();
        for (Attendance a : all) {
            allAttendance.add(new AttendanceDTO(a.getSid(), a.getDate(), a.getArrivaltime(), a.getSubname()));
        }
        return allAttendance;
    }

    @Override
    public ArrayList<StudentDTO> getAllStudent() throws Exception {
        ArrayList<Student> all = studentDAO.getAll();
        ArrayList<StudentDTO> allStudent = new ArrayList<>();
        for (Student s :  all){
            allStudent.add(new StudentDTO(s.getSid(), s.getStudentname(), s.getAddress(), s.getContact(), s.getDob(), s.getGender(), s.getBid(), s.getSchool(), s.getSubject()));
        }
        return allStudent;
    }

    @Override
    public ArrayList<SubjectDTO> getAllSubject() throws Exception {
        ArrayList<Subject> all = subjectDAO.getAll();
        ArrayList<SubjectDTO> allSubject = new ArrayList<>();
        for (Subject s : all){
            allSubject.add(new SubjectDTO(s.getSuid(), s.getSubjectname(), s.getTeachername(), s.getTid()));
        }
        return allSubject;
    }

    @Override
    public boolean deleteAll(String s, String y) throws Exception {
        return attendanceDAO.deleteAll(s, y);
    }

    @Override
    public ArrayList<AttendanceDTO> searchAllAttend(String search) throws Exception {
        ArrayList<Attendance> all = attendanceDAO.searchAllAttendance(search);
        ArrayList<AttendanceDTO> allAttend = new ArrayList<>();
        for (Attendance a : all){
            allAttend.add(new AttendanceDTO(a.getSid(), a.getDate(), a.getArrivaltime(), a.getSubname()));
        }
        return allAttend;
    }

    @Override
    public ArrayList<StudentDTO> comboSearchStudent(String search) throws Exception {
        ArrayList<Student> all = studentDAO.searchAllStudent(search);
        ArrayList<StudentDTO> allStudent = new ArrayList<>();
        for (Student s :  all){
            allStudent.add(new StudentDTO(s.getSid(), s.getStudentname(), s.getAddress(), s.getContact(), s.getDob(), s.getGender(), s.getBid(), s.getSchool(), s.getSubject()));
        }
        return allStudent;
    }


}
