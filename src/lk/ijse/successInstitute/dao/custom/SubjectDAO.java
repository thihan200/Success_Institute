package lk.ijse.successInstitute.dao.custom;

import lk.ijse.successInstitute.dao.CrudDAO;
import lk.ijse.successInstitute.entity.Subject;

import java.util.ArrayList;

public interface SubjectDAO extends CrudDAO<Subject, String> {
    public ArrayList<Subject> searchAllSubject(String search)throws Exception;

}
