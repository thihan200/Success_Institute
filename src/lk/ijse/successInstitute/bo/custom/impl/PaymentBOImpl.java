package lk.ijse.successInstitute.bo.custom.impl;

import lk.ijse.successInstitute.bo.custom.PaymentBO;
import lk.ijse.successInstitute.dao.DAOFactory;
import lk.ijse.successInstitute.dao.custom.PaymentDAO;
import lk.ijse.successInstitute.dao.custom.StudentDAO;
import lk.ijse.successInstitute.dao.custom.SubjectDAO;
import lk.ijse.successInstitute.dto.PaymentDTO;
import lk.ijse.successInstitute.dto.StudentDTO;
import lk.ijse.successInstitute.dto.SubjectDTO;
import lk.ijse.successInstitute.entity.Payment;
import lk.ijse.successInstitute.entity.Student;
import lk.ijse.successInstitute.entity.Subject;

import java.util.ArrayList;

public class PaymentBOImpl implements PaymentBO {

    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PAYMENT);
    SubjectDAO subjectDAO = (SubjectDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.SUBJECT);
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.STUDENT);

    @Override
    public boolean add(PaymentDTO paymentDTO) throws Exception {
        Payment payment = new Payment(paymentDTO.getPid(), paymentDTO.getRegfee(), paymentDTO.getDate(), paymentDTO.getSubname(), paymentDTO.getSid(), paymentDTO.getAmount());
        return paymentDAO.add(payment);
    }

    @Override
    public boolean delete(String id) throws Exception {
        return paymentDAO.delete(id);
    }

    @Override
    public boolean update(PaymentDTO paymentDTO) throws Exception {
        return paymentDAO.update(new Payment(paymentDTO.getPid(), paymentDTO.getRegfee(), paymentDTO.getDate(), paymentDTO.getSubname(), paymentDTO.getSid(), paymentDTO.getAmount()));
    }

    @Override
    public PaymentDTO search(String id) throws Exception {
        Payment search = paymentDAO.search(id);
        return new PaymentDTO(search.getPid(), search.getRegfee(), search.getDate(), search.getSubname(), search.getSid(), search.getAmount());
    }

    @Override
    public ArrayList<PaymentDTO> getAll() throws Exception {
        ArrayList<Payment> all = paymentDAO.getAll();
        ArrayList<PaymentDTO> allPayment = new ArrayList<>();
        for (Payment p:all){
            allPayment.add(new PaymentDTO(p.getPid(), p.getRegfee(), p.getDate(), p.getSubname(), p.getSid(), p.getAmount()));
        }
        return allPayment;
    }

    @Override
    public String getLastPID() throws Exception {
        return paymentDAO.getLastPID();
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
    public ArrayList<StudentDTO> getAllStudent() throws Exception {
        ArrayList<Student> all =  studentDAO.getAll();
        ArrayList<StudentDTO> allStudent = new ArrayList<>();
        for(Student s : all){
            allStudent.add(new StudentDTO(s.getSid(), s.getStudentname(), s.getAddress(), s.getContact(), s.getDob(), s.getGender(), s.getBid(), s.getSchool(), s.getSubject()));

        }
        return allStudent;
    }

    @Override
    public ArrayList<PaymentDTO> searchAllPayment(String search) throws Exception {

        ArrayList<Payment> all = paymentDAO.searchAllPayment(search);
        ArrayList<PaymentDTO> allPayment = new ArrayList<>();
        for (Payment p:all){
            allPayment.add(new PaymentDTO(p.getPid(), p.getRegfee(), p.getDate(), p.getSubname(), p.getSid(), p.getAmount()));
        }
        return allPayment;
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
