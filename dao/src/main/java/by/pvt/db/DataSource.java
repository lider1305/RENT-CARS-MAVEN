package by.pvt.db;

import by.pvt.constans.ErrorMessages;
import by.pvt.constans.Settings;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.log4j.Logger;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Класс формирующий пул соединений
 */
public class DataSource {
    private static DataSource datasource;
    private static Logger logger = Logger.getLogger(DataSource.class);
    private ComboPooledDataSource cpds;

    private DataSource() {
        cpds = new ComboPooledDataSource();
        try {
            ResourceBundle resourceBundle = ResourceBundle.getBundle(Settings.RESOURCE_DATABASE);
            cpds.setDriverClass(resourceBundle.getString(Settings.DRIVER));
            cpds.setJdbcUrl(resourceBundle.getString(Settings.URL));
            cpds.setUser(resourceBundle.getString(Settings.USER));
            cpds.setPassword(resourceBundle.getString(Settings.PASSWORD));
            cpds.setMinPoolSize(5);
            cpds.setAcquireIncrement(5);
            cpds.setMaxPoolSize(20);
            cpds.setMaxStatements(180);
        } catch (PropertyVetoException e ) {
           logger.error(ErrorMessages.ERROR_SETTINGS + e);
        }
    }

    public static  synchronized DataSource getInstance() throws IOException, SQLException, PropertyVetoException {
        if (datasource == null) {
            datasource = new DataSource();
        }
        return datasource;
    }

    public  Connection getConnection() throws SQLException {
        return this.cpds.getConnection();
    }
}
