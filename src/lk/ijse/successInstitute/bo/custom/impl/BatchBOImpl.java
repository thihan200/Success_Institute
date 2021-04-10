package lk.ijse.successInstitute.bo.custom.impl;

import lk.ijse.successInstitute.bo.custom.BatchBO;
import lk.ijse.successInstitute.dao.DAOFactory;
import lk.ijse.successInstitute.dao.custom.BatchDAO;
import lk.ijse.successInstitute.dto.BatchDTO;
import lk.ijse.successInstitute.entity.Batch;

import java.util.ArrayList;

public class BatchBOImpl implements BatchBO {
    BatchDAO batchDAO = (BatchDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.BATCH);

    @Override
    public boolean add(BatchDTO batchDTO) throws Exception {
        Batch batch = new Batch(batchDTO.getBid(), batchDTO.getBname());
        return batchDAO.add(batch);
    }

    @Override
    public boolean delete(String id) throws Exception {
        return batchDAO.delete(id);
    }

    @Override
    public boolean update(BatchDTO batchDTO) throws Exception {
        return batchDAO.update(new Batch(batchDTO.getBid(), batchDTO.getBname()));
    }

    @Override
    public BatchDTO search(String id) throws Exception {
        Batch search = batchDAO.search(id);
        return new BatchDTO(search.getBid(), search.getBname());
    }

    @Override
    public ArrayList<BatchDTO> getAll() throws Exception {
        ArrayList<Batch> all = batchDAO.getAll();
        ArrayList<BatchDTO> allBatch = new ArrayList<>();
        for (Batch b : all) {
            allBatch.add(new BatchDTO(b.getBid(), b.getBname()));
        }
        return allBatch;
    }

    @Override
    public String getLastCode() throws Exception {
        return batchDAO.getLastBatchId();
    }

    @Override
    public ArrayList<BatchDTO> searchAllBatch(String search) throws Exception {
        ArrayList<Batch> all = batchDAO.searchAllBatch(search);
        ArrayList<BatchDTO> allBatch = new ArrayList<>();
        for (Batch b : all){
            allBatch.add(new BatchDTO(b.getBid(), b.getBname()));
        }
        return allBatch;
    }
}
