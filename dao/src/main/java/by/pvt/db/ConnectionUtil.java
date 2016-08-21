package by.pvt.db;

import by.pvt.constans.ErrorMessages;
import org.apache.log4j.Logger;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Класс для создания подключения к базе данных(с учетом многопоточности)
 */
public class ConnectionUtil {
    private static Logger logger = Logger.getLogger(ConnectionUtil.class);
    private static DataSource instance;
    private static ThreadLocal<Connection> connection = null;

    private ConnectionUtil() {
    }

    public static Connection getConnection() {
        try {
            if (instance == null) {
                instance = DataSource.getInstance();
            }
            if (connection == null) {
                connection = new ThreadLocal<>();
                connection.set(instance.getConnection());
            } else
                connection.set(instance.getConnection());
        } catch (IOException e) {
            logger.error(ErrorMessages.ERROR_IO + e);
        } catch (SQLException e) {
            logger.error(ErrorMessages.ERROR_SQL + e);
        } catch (PropertyVetoException e) {
            logger.error(ErrorMessages.ERROR_SETTINGS + e);
        }
        return connection.get();
    }
}

