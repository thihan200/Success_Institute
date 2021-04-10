package lk.ijse.successInstitute.bo.custom.impl;

import lk.ijse.successInstitute.bo.custom.TimetableBO;
import lk.ijse.successInstitute.dao.DAOFactory;
import lk.ijse.successInstitute.dao.custom.BatchDAO;
import lk.ijse.successInstitute.dao.custom.SubjectDAO;
import lk.ijse.successInstitute.dao.custom.TeacherDAO;
import lk.ijse.successInstitute.dao.custom.TimetableDAO;
import lk.ijse.successInstitute.dto.BatchDTO;
import lk.ijse.successInstitute.dto.SubjectDTO;
import lk.ijse.successInstitute.dto.TeacherDTO;
import lk.ijse.successInstitute.dto.TimetableDTO;
import lk.ijse.successInstitute.entity.Batch;
import lk.ijse.successInstitute.entity.Subject;
import lk.ijse.successInstitute.entity.Teacher;
import lk.ijse.successInstitute.entity.Timetable;

import java.util.ArrayList;

public class TimetableBOImpl implements TimetableBO {

    TimetableDAO timetableDAO = (TimetableDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.TIMETABLE);
    SubjectDAO subjectDAO = (SubjectDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.SUBJECT);
    TeacherDAO teacherDAO = (TeacherDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.TEACHER);
    BatchDAO batchDAO = (BatchDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.BATCH);

    @Override
    public boolean add(TimetableDTO timetableDTO) throws Exception {
        Timetable timetable = new Timetable(timetableDTO.getSuName(), timetableDTO.gettName(), timetableDTO.getBatchid(), timetableDTO.getDate(), timetableDTO.getTime());
        return timetableDAO.add(timetable);
    }

    @Override
    public boolean delete(String id) throws Exception {
        return timetableDAO.delete(id);
    }

    @Override
    public boolean update(TimetableDTO timetableDTO) throws Exception {
        return timetableDAO.update(new Timetable(timetableDTO.getSuName(), timetableDTO.gettName(), timetableDTO.getBatchid(), timetableDTO.getDate(), timetableDTO.getTime()));
    }

    @Override
    public TimetableDTO search(String id) throws Exception {
        Timetable search = timetableDAO.search(id);
        return new TimetableDTO(search.getSuName(), search.gettName(), search.getBatchid(), search.getDate(), search.getTime());
    }

    @Override
    public ArrayList<TimetableDTO> getAll() throws Exception {
        ArrayList<Timetable> all = timetableDAO.getAll();
        ArrayList<TimetableDTO> allTime = new ArrayList<>();
        for (Timetable t : all){
            allTime.add(new TimetableDTO(t.getSuName(), t.gettName(), t.getBatchid(), t.getDate(), t.getTime()));
        }
        return allTime;
    }

    @Override
    public boolean deleteAll(String x, String y, String z) throws Exception {
        return timetableDAO.deleteAll(x, y, z);
    }

    @Override
    public ArrayList<BatchDTO> getAllBath() throws Exception {
        ArrayList<Batch> all = batchDAO.getAll();
        ArrayList<BatchDTO> allBatch = new ArrayList<>();
        for (Batch b : all){
            allBatch.add(new BatchDTO(b.getBid(), b.getBname()));
        }
        return allBatch;
    }
    @Override
    public ArrayList<SubjectDTO> geyAllSubject() throws Exception {
        ArrayList<Subject> all = subjectDAO.getAll();
        ArrayList<SubjectDTO> allSubject = new ArrayList<>();
        for (Subject s : all){
            allSubject.add(new SubjectDTO(s.getSuid(), s.getSubjectname(), s.getTeachername(), s.getTid()));
        }
        return allSubject;
    }

    @Override
    public ArrayList<TeacherDTO> getAllTeacher() throws Exception {
        ArrayList<Teacher>all = teacherDAO.getAll();
        ArrayList<TeacherDTO> allTeacher = new ArrayList<>();
        for (Teacher t : all){
            allTeacher.add(new TeacherDTO(t.getTid(), t.getTeachername(), t.getAddress(), t.getContact(), t.getDob(), t.getGender(), t.getQualification()));
        }
        return allTeacher;
    }


}
