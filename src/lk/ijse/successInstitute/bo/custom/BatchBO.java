package lk.ijse.successInstitute.bo.custom;

import lk.ijse.successInstitute.bo.SuperBO;
import lk.ijse.successInstitute.dto.BatchDTO;
import lk.ijse.successInstitute.entity.Batch;

import java.util.ArrayList;

public interface BatchBO extends SuperBO {
    public boolean add(BatchDTO batchDTO)throws Exception;

    public boolean delete(String id)throws Exception;

    public boolean update(BatchDTO batchDTO)throws Exception;

    public BatchDTO search(String id)throws Exception;

    public ArrayList<BatchDTO> getAll() throws Exception;

    public String getLastCode() throws Exception;

    public ArrayList<BatchDTO> searchAllBatch(String search)throws Exception;
}
