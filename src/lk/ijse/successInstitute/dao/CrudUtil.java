package lk.ijse.successInstitute.dao;

import lk.ijse.successInstitute.db.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CrudUtil {

    private static PreparedStatement getPreparedStatement(String sql,Object...para) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(sql);
        for (int i = 0; i<para.length; i++){
            stm.setObject((i+1),para[i]);
        }
        return stm;
    }

    public static boolean executeUpdate(String sql,Object...para) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = getPreparedStatement(sql, para);
        return stm.executeUpdate()>0;
    }

    public static ResultSet executeQuery(String sql,Object...para) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = getPreparedStatement(sql, para);
        return stm.executeQuery();
    }


}
