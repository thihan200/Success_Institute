package lk.ijse.successInstitute.bo.custom.impl;

import lk.ijse.successInstitute.bo.custom.SubjectBO;
import lk.ijse.successInstitute.dao.DAOFactory;
import lk.ijse.successInstitute.dao.custom.SubjectDAO;
import lk.ijse.successInstitute.dao.custom.TeacherDAO;
import lk.ijse.successInstitute.dto.SubjectDTO;
import lk.ijse.successInstitute.dto.TeacherDTO;
import lk.ijse.successInstitute.entity.Subject;
import lk.ijse.successInstitute.entity.Teacher;

import java.util.ArrayList;

public class SubjectBOImpl implements SubjectBO {

    SubjectDAO subjectDAO = (SubjectDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.SUBJECT);
    TeacherDAO teacherDAO = (TeacherDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.TEACHER);

    @Override
    public boolean add(SubjectDTO subjectDTO) throws Exception {
        Subject subject = new Subject(subjectDTO.getSuid(), subjectDTO.getSubjectname(), subjectDTO.getTeachername(), subjectDTO.getTid());
        return subjectDAO.add(subject);
    }

    @Override
    public boolean delete(String id) throws Exception {
        return subjectDAO.delete(id);
    }

    @Override
    public boolean update(SubjectDTO subjectDTO) throws Exception {
        return subjectDAO.update(new Subject(subjectDTO.getSuid(), subjectDTO.getSubjectname(), subjectDTO.getTeachername(), subjectDTO.getTid()));
    }

    @Override
    public SubjectDTO search(String id) throws Exception {
        Subject search = subjectDAO.search(id);
        return new SubjectDTO(search.getSuid(), search.getSubjectname(), search.getTeachername(), search.getTid());
    }

    @Override
    public ArrayList<SubjectDTO> getAll() throws Exception {
        ArrayList<Subject> all = subjectDAO.getAll();
        ArrayList<SubjectDTO> allSubject = new ArrayList<>();
        for (Subject s : all){
            allSubject.add(new SubjectDTO(s.getSuid(), s.getSubjectname(), s.getTeachername(), s.getTid()));
        }
        return allSubject;
    }

    @Override
    public ArrayList<TeacherDTO> getAllTeacher() throws Exception {
        ArrayList<Teacher> all = teacherDAO.getAll();
        ArrayList<TeacherDTO> allTeacher = new ArrayList<>();
        for (Teacher t : all){
            allTeacher.add(new TeacherDTO(t.getTid(), t.getTeachername(), t.getAddress(), t.getContact(), t.getDob(), t.getGender(), t.getQualification()));
        }
        return allTeacher;
    }

    @Override
    public TeacherDTO searchTeacher(String tid) throws Exception {
        Teacher search = teacherDAO.search(tid);
        return new TeacherDTO(search.getTid(), search.getTeachername(), search.getAddress(), search.getContact(), search.getDob(), search.getGender(), search.getQualification());
    }

    @Override
    public ArrayList<SubjectDTO> searchAllSubject(String search) throws Exception {
        ArrayList<Subject> all = subjectDAO.searchAllSubject(search);
        ArrayList<SubjectDTO> allSubject = new ArrayList<>();
        for (Subject s : all){
            allSubject.add(new SubjectDTO(s.getSuid(), s.getSubjectname(), s.getTeachername(), s.getTid()));
        }
        return allSubject;
    }


}
