package by.pvt.dao.impl;

import by.pvt.db.ConnectionUtil;
import by.pvt.entity.Client;
import by.pvt.exception.DAOException;
import by.pvt.interfaces.IClientDAO;
import by.pvt.util.DAOUtil;
import by.pvt.util.DateFormatUtil;
import by.pvt.util.SystemLogger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс обработки  Клиентского слоя базы данных
 */

public class ClientDAO implements IClientDAO {
    private static ClientDAO instance;
    private final String CREATE_PASSPORT_INFO = "INSERT INTO PASSPORTS (PASSPORTS_NUMBER, DATE_OF_ISSUE, DATE_OF_END) VALUES (?,?,?)";
    private final String SELECT_PASSPORT_INFO = "SELECT PASSPORT_NUMBER_ID FROM PASSPORTS WHERE PASSPORTS_NUMBER =?";
    private final String CREATE_CLIENT_INFO = "INSERT INTO CLIENTS (NAME, SURNAME,PHONE,MAIL, PASSWORD, PASSPORT,STATUS,ROLE) VALUES (?,?,?,?,?,?,2,2)";
    private final String GET_CLIENT_BY_ID = "SELECT c.NAME,c.SURNAME,c.PHONE,c.MAIL,p.PASSPORTS_NUMBER,p.DATE_OF_ISSUE,p.DATE_OF_END,c.PASSWORD,r.NAME, s.STATUS,c.PASSPORT\n" +
            "FROM CLIENTS c\n" +
            "JOIN  PASSPORTS p ON c.PASSPORT =p.PASSPORT_NUMBER_ID \n" +
            "JOIN CLIENT_STATUS s ON s.STATUS_ID=c.STATUS\n" +
            "JOIN ROLES r ON r.ROLE_ID=c.ROLE\n" +
            " WHERE c.CLIENT_ID =?";
    private final String UPDATE_CLIENT_INFO = "UPDATE CLIENTS SET NAME = ?, SURNAME =?, PHONE =?, MAIL =?, PASSWORD =? WHERE STATUS !=1 AND" +
            " CLIENT_ID = ?";
    private final String UPDATE_PASSPORT_INFO = "UPDATE PASSPORTS P JOIN CLIENTS C ON p.PASSPORT_NUMBER_ID=c.PASSPORT " +
            "SET  DATE_OF_ISSUE= ?, DATE_OF_END = ?,PASSPORTS_NUMBER = ?  WHERE c.CLIENT_ID =?";
    private final String DELETE_CLIENT = "UPDATE CLIENTS SET STATUS =1 WHERE CLIENT_ID =?";
    private final String GET_ALL_CLIENT = "SELECT c.CLIENT_ID, c.NAME,c.SURNAME,c.PHONE,c.MAIL,p.PASSPORTS_NUMBER," +
            "p.DATE_OF_ISSUE,p.DATE_OF_END FROM CLIENTS c JOIN  PASSPORTS p ON c.PASSPORT =p.PASSPORT_NUMBER_ID ";
    private final String ENTER_TO_SYSTEM = "SELECT c.CLIENT_ID,c.NAME,c.SURNAME,c.PHONE,c.MAIL,c.PASSWORD,p.PASSPORTS_NUMBER,p.DATE_OF_ISSUE,p.DATE_OF_END,r.NAME" +
            " FROM CLIENTS c JOIN  PASSPORTS p  ON  c.PASSPORT=p.PASSPORT_NUMBER_ID JOIN ROLES r ON c.ROLE=r.ROLE_ID WHERE STATUS !=1 AND c.MAIL =?";
    private final String FORGOT_PASSWORD = "SELECT * FROM CLIENTS WHERE STATUS !=1 AND MAIL = ?";
    private final String MAX_CLIENT_ID = "SELECT MAX(CLIENT_ID) FROM clients;";
    private final String ALL_CLIENTS = "SELECT COUNT(*) FROM clients;";

    private ClientDAO() {
    }

    public static synchronized ClientDAO getInstance() {
        if (instance == null) {
            instance = new ClientDAO();
        }
        return instance;
    }

    @Override
    public void create(Client client) throws DAOException {

        try (Connection connection = ConnectionUtil.getConnection()) {
            try (PreparedStatement preparedStatement3 = connection.prepareStatement(CREATE_CLIENT_INFO)) {

                preparedStatement3.setString(1, client.getName());
                preparedStatement3.setString(2, client.getSurName());
                preparedStatement3.setString(3, client.getPhone());
                preparedStatement3.setString(4, client.getEmail());
                preparedStatement3.setString(5, client.getPassword());
                preparedStatement3.setLong(6, client.getPassportId());
                preparedStatement3.executeUpdate();
            }
        } catch (SQLException e) {
            SystemLogger.getInstance().setLogger(getClass(), "Unable to create new user!");
            throw new DAOException(e.getMessage(), e);
        }
    }

    @Override
    public Client getId(long id) throws DAOException {
        Client client = new Client();
        ResultSet resultSet = null;

        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_CLIENT_BY_ID)) {
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            client.setName(resultSet.getString(1));
            client.setSurName(resultSet.getString(2));
            client.setPhone(resultSet.getString(3));
            client.setEmail(resultSet.getString(4));
            client.setPassport(resultSet.getString(5));
            client.setPassportIssueDate(DateFormatUtil.dateFormatterFromLongToDate(resultSet.getLong(6)));
            client.setPassportEndDate(DateFormatUtil.dateFormatterFromLongToDate(resultSet.getLong(7)));
            client.setPassword(resultSet.getString(8));
            client.setRole(resultSet.getString(9));
            client.setStatus(resultSet.getString(10));
            client.setPassportId(resultSet.getLong(11));
            client.setClientID(id);
        } catch (SQLException e) {
            SystemLogger.getInstance().setLogger(getClass(), "Unable to return the user!");
            throw new DAOException(e.getMessage(), e);
        } finally {
            DAOUtil.closeResources(resultSet, null);
        }
        return client;
    }

    @Override
    public void update(Client client) throws DAOException {

        try (Connection connection = ConnectionUtil.getConnection()) {
            try (PreparedStatement preparedStatement1 = connection.prepareStatement(UPDATE_CLIENT_INFO);
                 PreparedStatement preparedStatement2 = connection.prepareStatement(UPDATE_PASSPORT_INFO)) {

                preparedStatement1.setString(1, client.getName());
                preparedStatement1.setString(2, client.getSurName());
                preparedStatement1.setString(3, client.getPhone());
                preparedStatement1.setString(4, client.getEmail());
                preparedStatement1.setString(5, client.getPassword());
                preparedStatement1.setLong(6, client.getClientID());
                preparedStatement1.executeUpdate();

                preparedStatement2.setLong(1, DateFormatUtil.dateFormatterFromDateToLong(client.getPassportIssueDate()));
                preparedStatement2.setLong(2, DateFormatUtil.dateFormatterFromDateToLong(client.getPassportEndDate()));
                preparedStatement2.setString(3, client.getPassport());
                preparedStatement2.setLong(4, client.getClientID());
                preparedStatement2.executeUpdate();

            }
        } catch (SQLException e) {
            SystemLogger.getInstance().setLogger(getClass(), "Unable to update user information!");
            throw new DAOException(e.getMessage(), e);
        }
    }

    @Override
    public void delete(long id) throws DAOException {

        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement1 = connection.prepareStatement(DELETE_CLIENT)) {

            preparedStatement1.setLong(1, id);
            preparedStatement1.executeUpdate();
        } catch (SQLException e) {
            SystemLogger.getInstance().setLogger(getClass(), "Unable to delete user! ");
            throw new DAOException(e.getMessage(), e);
        }
    }

    @Override
    public List getAll() throws DAOException {
        ArrayList<Client> allClients = new ArrayList<>();
        ResultSet resultSet = null;

        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_CLIENT)) {
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Client client = new Client();
                client.setClientID(resultSet.getLong(1));
                client.setName(resultSet.getString(2));
                client.setSurName(resultSet.getString(3));
                client.setPhone(resultSet.getString(4));
                client.setEmail(resultSet.getString(5));
                client.setPassport(resultSet.getString(6));
                client.setPassportIssueDate(DateFormatUtil.dateFormatterFromLongToDate(resultSet.getLong(7)));
                client.setPassportEndDate(DateFormatUtil.dateFormatterFromLongToDate(resultSet.getLong(8)));
                allClients.add(client);
            }
        } catch (SQLException e) {
            SystemLogger.getInstance().setLogger(getClass(),"Unable to return list of users!");
            throw new DAOException(e.getMessage(),e);
        } finally {
            DAOUtil.closeResources(resultSet, null);
        }
        return allClients;
    }

    @Override
    public Client enterToSystem(String email, String password) throws DAOException {
        Client client = new Client();
        ResultSet resultSet = null;

        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ENTER_TO_SYSTEM)) {
            preparedStatement.setString(1, email);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                client.setClientID(resultSet.getLong(1));
                client.setName(resultSet.getString(2));
                client.setSurName(resultSet.getString(3));
                client.setPhone(resultSet.getString(4));
                client.setEmail(resultSet.getString(5));
                client.setPassword(resultSet.getString(6));
                client.setPassport(resultSet.getString(7));
                client.setPassportIssueDate(DateFormatUtil.dateFormatterFromLongToDate(resultSet.getLong(8)));
                client.setPassportEndDate(DateFormatUtil.dateFormatterFromLongToDate(resultSet.getLong(9)));
                client.setRole(resultSet.getString(10));
            }
        } catch (SQLException e) {
            SystemLogger.getInstance().setLogger(getClass(),"Unable to enter to system");
            throw new DAOException(e.getMessage(),e);
        } finally {
            DAOUtil.closeResources(resultSet, null);
        }
        return client;
    }

    @Override
    public String forgotPassword(String email) throws DAOException {
        String password = "";

        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FORGOT_PASSWORD)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            password = resultSet.getString("PASSWORD");
        } catch (SQLException e) {
            SystemLogger.getInstance().setLogger(getClass(),"Unable to return password");
            throw new DAOException(e.getMessage(),e);
        }
        return password;
    }

    public void createPassport(Object object) throws DAOException {
        Client client = (Client) object;

        try (Connection connection = ConnectionUtil.getConnection()) {
            try (PreparedStatement preparedStatement1 = connection.prepareStatement(CREATE_PASSPORT_INFO)) {

                preparedStatement1.setString(1, client.getPassport());
                preparedStatement1.setLong(2, DateFormatUtil.dateFormatterFromDateToLong(client.getPassportIssueDate()));
                preparedStatement1.setLong(3, DateFormatUtil.dateFormatterFromDateToLong(client.getPassportEndDate()));
                preparedStatement1.executeUpdate();
            }
        } catch (SQLException e) {
            SystemLogger.getInstance().setLogger(getClass(), "Unable to create passport");
            throw new DAOException(e.getMessage(),e);
        }
    }

    public void getPassport(Object object) throws DAOException {
        Client client = (Client) object;
        ResultSet resultSet = null;

        try (Connection connection = ConnectionUtil.getConnection()) {
            try (PreparedStatement preparedStatement2 = connection.prepareStatement(SELECT_PASSPORT_INFO)) {

                preparedStatement2.setString(1, client.getPassport());
                resultSet = preparedStatement2.executeQuery();
                resultSet.next();
                client.setPassportId(resultSet.getLong(1));

            }
        } catch (SQLException e) {
            SystemLogger.getInstance().setLogger(getClass(),"Unable to return passport");
            throw new DAOException(e.getMessage(),e);
        } finally {
            DAOUtil.closeResources(resultSet, null);
        }
    }

    public long getMaxClintId() throws DAOException {
        long max = -1;
        try (Connection connection = ConnectionUtil.getConnection()) {
            max =DAOUtil.getCount(connection,MAX_CLIENT_ID);
        } catch (SQLException e) {
            SystemLogger.getInstance().setLogger(getClass(),"Unable to return last client ID");
            throw new DAOException(e.getMessage(),e);
        }
        return max;
    }

    public long getAllClientsId() throws DAOException {
        long all = -1;
        try (Connection connection = ConnectionUtil.getConnection()) {
            all = DAOUtil.getCount(connection,ALL_CLIENTS);
        } catch (SQLException e) {
            SystemLogger.getInstance().setLogger(getClass(), "Unable to count all clients in base");
            throw new DAOException(e.getMessage(),e);
        }
        return all;
    }
}
