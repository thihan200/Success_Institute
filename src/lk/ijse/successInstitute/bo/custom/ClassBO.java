package lk.ijse.successInstitute.bo.custom;

import lk.ijse.successInstitute.bo.SuperBO;
import lk.ijse.successInstitute.dto.ClassDTO;

import java.util.ArrayList;

public interface ClassBO extends SuperBO {
    public boolean add(ClassDTO classDTO)throws Exception;

    public boolean delete(String id)throws Exception;

    public boolean update(ClassDTO classDTO)throws Exception;

    public ClassDTO search(String id)throws Exception;

    public ArrayList<ClassDTO> getAll() throws Exception;

    public String getLastCID()throws Exception;

    public String getClassCount() throws Exception;

    public ArrayList<ClassDTO> searchAllClass(String search) throws Exception;
}
