package lk.ijse.successInstitute.dao.custom.impl;

import lk.ijse.successInstitute.dao.CrudUtil;
import lk.ijse.successInstitute.dao.custom.BatchDAO;
import lk.ijse.successInstitute.entity.Attendance;
import lk.ijse.successInstitute.entity.Batch;

import java.sql.ResultSet;
import java.util.ArrayList;

public class BatchDAOImpl implements BatchDAO {
    @Override
    public boolean add(Batch batch) throws Exception {
        String sql = "INSERT INTO batch VALUES(?,?)";
        return CrudUtil.executeUpdate(sql, batch.getBid(), batch.getBname());
    }

    @Override
    public boolean delete(String s) throws Exception {
        String sql = "DELETE FROM batch where ID=?";
        return CrudUtil.executeUpdate(sql, s);
    }

    @Override
    public boolean update(Batch batch) throws Exception {
        String sql = "update batch set batchName=? where ID=?";
        return CrudUtil.executeUpdate(sql, batch.getBname(), batch.getBid());
    }


    @Override
    public Batch search(String s) throws Exception {
        String sql = "SELECT ID FROM Batch WHERE ID like '%?%'";
        ResultSet rst = CrudUtil.executeQuery(sql, s);
        if(rst.next()){
            return new Batch(
                    rst.getString(1),
                    rst.getString(2)
            );
        }
        return null;
    }

    @Override
    public ArrayList<Batch> getAll() throws Exception {
        String sql = "select * from batch";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<Batch>all= new ArrayList<>();
        while (rst.next()){
            all.add(new Batch(
                    rst.getString(1),
                    rst.getString(2)
            ));
        }
        return all;
    }

    @Override
    public String getLastBatchId() throws Exception {
        String  sql="select ID From batch order BY ID desc LIMIT 1";
        ResultSet rst = CrudUtil.executeQuery(sql);
        return rst.next()?rst.getString("ID"):null;
    }

    @Override
    public ArrayList<Batch> searchAllBatch(String search) throws Exception {
        String sql = "select * from batch where ID like "+"'%"+search+"%'"+" || batchName like "+"'%"+search+"%'";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<Batch> all = new ArrayList<>();
        while (rst.next()){
            all.add(new Batch(
               rst.getString(1),
               rst.getString(2)
            ));
        }
        return all;
    }
}
