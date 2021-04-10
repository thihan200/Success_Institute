package lk.ijse.successInstitute.dao.custom.impl;

import lk.ijse.successInstitute.dao.CrudUtil;
import lk.ijse.successInstitute.dao.custom.PaymentDAO;
import lk.ijse.successInstitute.entity.Payment;

import java.sql.ResultSet;
import java.util.ArrayList;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public boolean add(Payment payment) throws Exception {
        String sql = "INSERT INTO paymentdetail VALUES(?,?,?,?,?,?)";
        return CrudUtil.executeUpdate(sql, payment.getPid(), payment.getRegfee(), payment.getDate(), payment.getSubname(), payment.getSid(), payment.getAmount());
    }

    @Override
    public boolean delete(String s) throws Exception {
        String sql = "DELETE FROM paymentdetail WHERE PID=?";
        return CrudUtil.executeUpdate(sql, s);
    }

    @Override
    public boolean update(Payment payment) throws Exception {
        String sql = "UPDATE paymentdetail SET RegFee=?,Date=?,SubjectName=?,StudentId=?,Amount=? WHERE PID=?";
        return CrudUtil.executeUpdate(sql, payment.getRegfee(), payment.getDate(), payment.getSubname(), payment.getSid(), payment.getAmount(), payment.getPid());
    }

    @Override
    public Payment search(String s) throws Exception {
        String sql = "SELECT * FROM paymentdetail WHERE PID=?";
        ResultSet rst = CrudUtil.executeQuery(sql, s);
        if (rst.next()){
            return new Payment(
                  rst.getString(1),
                  rst.getString(2),
                  rst.getString(3),
                  rst.getString(4),
                  rst.getString(5),
                  rst.getDouble(6)
            );
        }
        return null;
    }

    @Override
    public ArrayList<Payment> getAll() throws Exception {
        String sql = "SELECT * FROM paymentdetail";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<Payment> all = new ArrayList<>();
        while (rst.next()){
            all.add(new Payment(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getDouble(6)

            ));
        }
        return all;
    }

    @Override
    public String getLastPID() throws Exception {
        String sql = "select PID From paymentdetail ORDER BY PID DESC LIMIT 1";
        ResultSet rst = CrudUtil.executeQuery(sql);
        return rst.next()?rst.getString("PID"):null;
    }

    @Override
    public ArrayList<Payment> searchAllPayment(String search) throws Exception {
        String sql = "select * from paymentdetail where PID like "+"'%"+search+"%'"+" || RegFee like "+"'%"+search+"%'"+" || Date like "+"'%"+search+"%'"+" || SubjectName like "+"'%"+search+"%'"+" || StudentId like "+"'%"+search+"%'"+" || Amount like "+"'%"+search+"%'";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<Payment> all = new ArrayList<>();
        while (rst.next()){
            all.add(new Payment(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getDouble(6)

            ));
        }
        return all;
    }


}
