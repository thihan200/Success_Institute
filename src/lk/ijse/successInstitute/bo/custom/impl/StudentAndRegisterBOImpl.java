package lk.ijse.successInstitute.bo.custom.impl;

import lk.ijse.successInstitute.bo.custom.StudentAndRegisterBO;
import lk.ijse.successInstitute.dao.DAOFactory;
import lk.ijse.successInstitute.dao.custom.BatchDAO;
import lk.ijse.successInstitute.dao.custom.RegistrationDAO;
import lk.ijse.successInstitute.dao.custom.StudentDAO;
import lk.ijse.successInstitute.db.DBConnection;
import lk.ijse.successInstitute.dto.BatchDTO;
import lk.ijse.successInstitute.dto.RegistrationDTO;
import lk.ijse.successInstitute.dto.StudentDTO;
import lk.ijse.successInstitute.entity.Batch;
import lk.ijse.successInstitute.entity.Registration;
import lk.ijse.successInstitute.entity.Student;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentAndRegisterBOImpl implements StudentAndRegisterBO {
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.STUDENT);
    BatchDAO batchDAO = (BatchDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.BATCH);
    RegistrationDAO registrationDAO = (RegistrationDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.REGISTRATION);



    @Override
    public boolean delete(String id) throws Exception {
        return studentDAO.delete(id);
    }

    @Override
    public boolean update(StudentDTO studentDTO) throws Exception {
        return studentDAO.update(new Student(studentDTO.getSid(), studentDTO.getStudentname(), studentDTO.getAddress(), studentDTO.getContact(), studentDTO.getDob(), studentDTO.getGender(), studentDTO.getBid(), studentDTO.getSchool(), studentDTO.getSubject()));
    }

    @Override
    public StudentDTO search(String id) throws Exception {
        Student search = studentDAO.search(id);
        return new StudentDTO(search.getSid(), search.getStudentname(), search.getAddress(), search.getContact(), search.getDob(), search.getGender(), search.getBid(), search.getSchool(), search.getSubject());
    }

    @Override
    public ArrayList<StudentDTO> getAll() throws Exception {
        ArrayList<Student> all =  studentDAO.getAll();
        ArrayList<StudentDTO> allStudent = new ArrayList<>();
        for(Student s : all){
            allStudent.add(new StudentDTO(s.getSid(), s.getStudentname(), s.getAddress(), s.getContact(), s.getDob(), s.getGender(), s.getBid(), s.getSchool(), s.getSubject()));

        }
        return allStudent;
    }

    @Override
    public ArrayList<RegistrationDTO> getAllRegistration() throws Exception {

        ArrayList<Registration> all = registrationDAO.getAll();
        ArrayList<RegistrationDTO> allRegister = new ArrayList<>();
        for (Registration r : all){
            allRegister.add(new RegistrationDTO(r.getRegId(), r.getDate(), r.getStuId()));
        }
        return allRegister;
    }

    @Override
    public boolean registerStudent(StudentDTO studentDTO) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        Student student = new Student(studentDTO.getSid(), studentDTO.getStudentname(), studentDTO.getAddress(), studentDTO.getContact(), studentDTO.getDob(), studentDTO.getGender(), studentDTO.getBid(), studentDTO.getSchool(), studentDTO.getSubject());
        boolean isAdd = false;
        try {
            isAdd = studentDAO.add(student);
            if (isAdd) {
                for (RegistrationDTO regis : studentDTO.getAllRegistration()) {
                    Registration registrationDTO = new Registration(regis.getRegId(), regis.getDate(), regis.getStuId());
                    boolean response = registrationDAO.add(registrationDTO);
                    if (!response) {
                        connection.rollback();
                        // connection.setAutoCommit(true);
                        return false;
                    }
                }
                connection.commit();
                //connection.setAutoCommit(true);
                return true;
            }else {
                connection.rollback();
                return false;
            }

        } finally {
            connection.setAutoCommit(true);
        }

        }



    @Override
    public String getLastStudentId() throws Exception {
        return studentDAO.getLastStudentId();
    }

    @Override
    public String getStudentCount() throws Exception {
        return studentDAO.getStudentCount();
    }

    @Override
    public ArrayList<BatchDTO> getBatchId() throws Exception {
        ArrayList<Batch> all = batchDAO.getAll();
        ArrayList<BatchDTO> allBatch = new ArrayList<>();
        for (Batch b : all){
            allBatch.add(new BatchDTO(b.getBid(), b.getBname()));
        }
        return allBatch;
    }

    @Override
    public ArrayList<StudentDTO> searchAllStudent(String search) throws Exception {
       ArrayList<Student> all = studentDAO.searchAllStudent(search);
        ArrayList<StudentDTO> allStudent = new ArrayList<>();
        for(Student s : all){
            allStudent.add(new StudentDTO(s.getSid(), s.getStudentname(), s.getAddress(), s.getContact(), s.getDob(), s.getGender(), s.getBid(), s.getSchool(), s.getSubject()));

        }
        return allStudent;
    }

    @Override
    public ArrayList<RegistrationDTO> searchAllRegistration(String search) throws Exception {
        ArrayList<Registration> all = registrationDAO.searchAllRegistration(search);
        ArrayList<RegistrationDTO> allRegister = new ArrayList<>();
        for (Registration r : all){
            allRegister.add(new RegistrationDTO(r.getRegId(), r.getDate(), r.getStuId()));
        }
        return allRegister;
    }

    @Override
    public String getLastPaymentId() throws Exception {
        return registrationDAO.getLastRegisterId();
    }


}
