package lk.ijse.successInstitute.dao.custom;

import lk.ijse.successInstitute.dao.CrudDAO;
import lk.ijse.successInstitute.entity.Class;

import java.util.ArrayList;

public interface ClassDAO extends CrudDAO<Class, String> {
    public String getLastClassId()throws Exception;

    public String getClassCount()throws Exception;

    public ArrayList<Class> searchAllClass(String search) throws Exception;
}
