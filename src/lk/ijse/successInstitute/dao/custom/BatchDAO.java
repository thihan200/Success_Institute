package lk.ijse.successInstitute.dao.custom;

import lk.ijse.successInstitute.dao.CrudDAO;
import lk.ijse.successInstitute.entity.Batch;

import java.util.ArrayList;

public interface BatchDAO extends CrudDAO<Batch, String> {
    public String getLastBatchId()throws Exception;

    public ArrayList<Batch> searchAllBatch(String search)throws Exception;
}
