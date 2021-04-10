package lk.ijse.successInstitute.bo.custom.impl;

import lk.ijse.successInstitute.bo.custom.ClassBO;
import lk.ijse.successInstitute.dao.DAOFactory;
import lk.ijse.successInstitute.dao.custom.BatchDAO;
import lk.ijse.successInstitute.dao.custom.ClassDAO;
import lk.ijse.successInstitute.dto.BatchDTO;
import lk.ijse.successInstitute.dto.ClassDTO;
import lk.ijse.successInstitute.entity.Class;

import java.util.ArrayList;

public class ClassBOImpl implements ClassBO {

    ClassDAO classDAO = (ClassDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CLASS);

    @Override
    public boolean add(ClassDTO classDTO) throws Exception {
        Class cls = new Class(classDTO.getClassid(), classDTO.getClassname(), classDTO.getStudentlimit());
        return classDAO.add(cls);
    }

    @Override
    public boolean delete(String id) throws Exception {
        return classDAO.delete(id);
    }

    @Override
    public boolean update(ClassDTO classDTO) throws Exception {
        return classDAO.update(new Class(classDTO.getClassid(), classDTO.getClassname(), classDTO.getStudentlimit()));
    }

    @Override
    public ClassDTO search(String id) throws Exception {
        Class search = classDAO.search(id);
        return new ClassDTO(search.getClassid(), search.getClassname(), search.getStudentlimit());
    }

    @Override
    public ArrayList<ClassDTO> getAll() throws Exception {
        ArrayList<Class> all = classDAO.getAll();
        ArrayList<ClassDTO> allClass = new ArrayList<>();
        for (Class c : all){
            allClass.add(new ClassDTO(c.getClassid(), c.getClassname(), c.getStudentlimit()));
        }
        return allClass;
    }

    @Override
    public String getLastCID() throws Exception {
        return classDAO.getLastClassId();
    }

    @Override
    public String getClassCount() throws Exception {
        return classDAO.getClassCount();
    }

    @Override
    public ArrayList<ClassDTO> searchAllClass(String search) throws Exception {
        ArrayList<Class> all = classDAO.searchAllClass(search);
        ArrayList<ClassDTO> allClass = new ArrayList<>();
        for (Class c : all){
            allClass.add(new ClassDTO(c.getClassid(), c.getClassname(), c.getStudentlimit()));
        }
        return allClass;

    }
}
