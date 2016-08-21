package by.pvt.util;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Класс для закрытия Statement, ResultSet
 */
public class DAOUtil {


    public static void closeResources(ResultSet resultSet, Statement statement) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            String message="Can't close resources";
          SystemLogger.getInstance().setLogger(DAOUtil.class,message);
        }
    }
    public  static long getCount(Connection connection, String SQL) throws SQLException {
        Statement statement= null;
        ResultSet resultSet = null;
        long all = -1;
        try{
        statement = connection.createStatement();
        resultSet = statement.executeQuery(SQL);
        resultSet.next();
        all = resultSet.getLong(1);
        }catch (SQLException e){
            String message="Error with database connection";
            SystemLogger.getInstance().setLogger(DAOUtil.class,message);
        }finally {
            DAOUtil.closeResources(resultSet, statement);
        }
        return all;
    }
}
