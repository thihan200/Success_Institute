package lk.ijse.successInstitute.bo.custom.impl;

import lk.ijse.successInstitute.bo.custom.TeacherBO;
import lk.ijse.successInstitute.dao.CrudUtil;
import lk.ijse.successInstitute.dao.DAOFactory;
import lk.ijse.successInstitute.dao.custom.TeacherDAO;
import lk.ijse.successInstitute.dto.TeacherDTO;
import lk.ijse.successInstitute.entity.Teacher;

import java.sql.ResultSet;
import java.util.ArrayList;

public class TeacherBOImpl implements TeacherBO {

    TeacherDAO teacherDAO = (TeacherDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.TEACHER);

    @Override
    public boolean add(TeacherDTO teacherDTO) throws Exception {
        Teacher teacher = new Teacher(teacherDTO.getTid(), teacherDTO.getTeachername(), teacherDTO.getAddress(), teacherDTO.getContact(), teacherDTO.getDob(), teacherDTO.getGender(), teacherDTO.getQualification());
        return teacherDAO.add(teacher);
    }

    @Override
    public boolean delete(String id) throws Exception {
        return teacherDAO.delete(id);
    }

    @Override
    public boolean update(TeacherDTO teacherDTO) throws Exception {
        return teacherDAO.update(new Teacher(teacherDTO.getTid(), teacherDTO.getTeachername(), teacherDTO.getAddress(), teacherDTO.getContact(), teacherDTO.getDob(), teacherDTO.getGender(), teacherDTO.getQualification()));
    }

    @Override
    public TeacherDTO search(String id) throws Exception {
        Teacher search = teacherDAO.search(id);
        return new TeacherDTO(search.getTid(), search.getTeachername(), search.getAddress(), search.getContact(), search.getDob(), search.getGender(), search.getQualification());
    }

    @Override
    public ArrayList<TeacherDTO> getAll() throws Exception {
        ArrayList<Teacher> all = teacherDAO.getAll();
        ArrayList<TeacherDTO> allTeacher = new ArrayList<>();
        for (Teacher t : all){
            allTeacher.add(new TeacherDTO(t.getTid(), t.getTeachername(), t.getAddress(), t.getContact(), t.getDob(), t.getGender(), t.getQualification()));
        }
        return allTeacher;
    }

    @Override
    public String getTeacherCount() throws Exception {
       return teacherDAO.getTeacherCount();
    }

    @Override
    public ArrayList<TeacherDTO> searchAllTecher(String search) throws Exception {
        ArrayList<Teacher> all = teacherDAO.searchAllTeacher(search);
        ArrayList<TeacherDTO> allTeacher = new ArrayList<>();
        for (Teacher t : all){
            allTeacher.add(new TeacherDTO(t.getTid(), t.getTeachername(), t.getAddress(), t.getContact(), t.getDob(), t.getGender(), t.getQualification()));
        }
        return allTeacher;
    }


}
