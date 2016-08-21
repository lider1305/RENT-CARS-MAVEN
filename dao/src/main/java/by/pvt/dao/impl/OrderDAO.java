package by.pvt.dao.impl;


import by.pvt.VO.ClientOrdersDTO;
import by.pvt.VO.OrderDTO;
import by.pvt.db.ConnectionUtil;
import by.pvt.entity.Order;
import by.pvt.exception.DAOException;
import by.pvt.interfaces.IOrderDAO;
import by.pvt.util.DAOUtil;
import by.pvt.util.DateFormatUtil;
import by.pvt.util.SystemLogger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Метод для обработки запросов для объекта Заказа
 */
public class OrderDAO implements IOrderDAO {
    private static OrderDAO instance;
    private final String GET_ORDER_BY_ID = "SELECT CLIENT, CAR, START_DATE,END_DATE, MESSAGE, s.STATUS_NAME FROM ORDERS\n" +
            "JOIN STATUS_OF_ORDER s ON STATUS_OF_ORDER=s.STATUS_ID\n" +
            "WHERE ORDER_ID= ?;";
    private final String GET_ALL_ORDERS = "SELECT ORDER_ID,CLIENT, CAR, START_DATE,END_DATE, MESSAGE, s.STATUS_NAME FROM ORDERS\n" +
            "JOIN STATUS_OF_ORDER s ON STATUS_OF_ORDER=s.STATUS_ID ORDER BY ORDER_ID";
    private final String DELETE_ORDER = "DELETE FROM ORDERS WHERE ORDER_ID =?";
    private final String MAX_ORDER_ID = "SELECT MAX(ORDER_ID) FROM orders;";
    private final String ALL_ORDERS = "SELECT COUNT(*) FROM orders;";
    private final String CREATE_ORDER = "INSERT INTO ORDERS (CLIENT, CAR, START_DATE,END_DATE,MESSAGE,STATUS_OF_ORDER) \n" +
            "VALUES (?, ?, ?, ?, ?, 1);";
    private final String  GET_ORDERS_BY_CLIENT = "SELECT ORDER_ID, b.BRAND_NAME, c.MODEL, START_DATE,END_DATE, MESSAGE, s.STATUS_NAME, c.AMOUNT FROM ORDERS\n" +
            "JOIN STATUS_OF_ORDER s ON STATUS_OF_ORDER=s.STATUS_ID\n" +
            "JOIN CARS c ON c.CAR_ID=CAR\n" +
            "JOIN BRANDS b ON b.ID=BRAND\n" +
            "WHERE CLIENT= ?;";

    private OrderDAO() {
    }

    public static synchronized OrderDAO getInstance() {
        if (instance == null) {
            instance = new OrderDAO();
        }
        return instance;
    }

    @Override
    public void create(Order o) throws DAOException {

        try (Connection connection = ConnectionUtil.getConnection()) {
            try (PreparedStatement preparedStatement1 = connection.prepareStatement(CREATE_ORDER)) {

                preparedStatement1.setInt(1, o.getClientID());
                preparedStatement1.setInt(2, o.getCarId());
                long start = DateFormatUtil.dateFormatterFromDateToLong(o.getStartDate());
                preparedStatement1.setLong(3, start);
                long end = DateFormatUtil.dateFormatterFromDateToLong(o.getEndDate());
                preparedStatement1.setLong(4, end);
                preparedStatement1.setString(5, o.getMessage());
                preparedStatement1.executeUpdate();

            }
        } catch (SQLException e) {
            SystemLogger.getInstance().setLogger(getClass(),"Unable to create new order");
            throw new DAOException(e.getMessage(),e);
        }
    }

    @Override
    public Order getId(long id) throws DAOException {
        Order order = new Order();
        ResultSet resultSet = null;
        Date start = null;
        Date end = null;

        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDER_BY_ID)) {
            preparedStatement.setLong(1, id);

            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            order.setClientID(resultSet.getInt(1));
            order.setCarId(resultSet.getInt(2));
            start = DateFormatUtil.dateFormatterFromLongToDate(resultSet.getLong(3));
            order.setStartDate(start);
            end = DateFormatUtil.dateFormatterFromLongToDate(resultSet.getLong(4));
            order.setEndDate(end);
            order.setMessage(resultSet.getString(5));
            order.setOrderStatus(resultSet.getString(6));
            order.setOrderId(id);
        } catch (SQLException e) {
            SystemLogger.getInstance().setLogger(getClass(),"Unable to return order");
            throw new DAOException(e.getMessage(),e);
        } finally {
            DAOUtil.closeResources(resultSet, null);
        }
        return order;
    }

    @Override
    public void update(Order object) {

    }

    @Override
    public void delete(long id) throws DAOException {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement1 = connection.prepareStatement(DELETE_ORDER)) {

            preparedStatement1.setLong(1, id);
            preparedStatement1.executeUpdate();
        } catch (SQLException e) {
            SystemLogger.getInstance().setLogger(getClass(),"Unable  to delete order");
            throw new DAOException(e.getMessage(),e);
        }
    }

    @Override
    public List getAll() throws DAOException {
        List<OrderDTO> allOrders = new ArrayList<>();
        ResultSet resultSet = null;
        Date start = null;
        Date end = null;
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_ORDERS)) {
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                OrderDTO order = new OrderDTO();
                order.setOrderId(resultSet.getLong(1));
                order.setClientID(resultSet.getInt(2));
                order.setCarId(resultSet.getInt(3));
                start = DateFormatUtil.dateFormatterFromLongToDate(resultSet.getLong(4));
                order.setStartDate(start);
                end = DateFormatUtil.dateFormatterFromLongToDate(resultSet.getLong(5));
                order.setEndDate(end);
                order.setMessage(resultSet.getString(6));
                order.setOrderStatus(resultSet.getString(7));
                allOrders.add(order);
            }
        } catch (SQLException e) {
            SystemLogger.getInstance().setLogger(getClass(),"Unable to return list of orders");
            throw new DAOException(e.getMessage(),e);
        } finally {
            DAOUtil.closeResources(resultSet, null);
        }
        return allOrders;
    }

    @Override
    public List getClientOrders(long id) throws DAOException {
        List<ClientOrdersDTO> allOrders = new ArrayList<>();
        ResultSet resultSet = null;
        Date start = null;
        Date end = null;

        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDERS_BY_CLIENT)) {
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ClientOrdersDTO order = new ClientOrdersDTO();
                order.setOrderId(resultSet.getLong(1));
                order.setBrand(resultSet.getString(2));
                order.setModel(resultSet.getString(3));
                start = DateFormatUtil.dateFormatterFromLongToDate(resultSet.getLong(4));
                order.setStartDate(start);
                end = DateFormatUtil.dateFormatterFromLongToDate(resultSet.getLong(5));
                order.setEndDate(end);
                int days = (int) ((resultSet.getLong(5)-resultSet.getLong(4))/(24*60*60*1000));
                order.setMessage(resultSet.getString(6));
                order.setStatus(resultSet.getString(7));
                order.setOrderAmount((long)resultSet.getDouble(8)*days);
                allOrders.add(order);
            }
        } catch (SQLException e) {
            SystemLogger.getInstance().setLogger(getClass(),"Unable to return list of user orders");
            throw new DAOException(e.getMessage(),e);
        } finally {
            DAOUtil.closeResources(resultSet, null);
        }
        return allOrders;
    }

    public long getMaxOrderId() throws DAOException {
        long max = -1;
        try (Connection connection = ConnectionUtil.getConnection()) {
            max =DAOUtil.getCount(connection,MAX_ORDER_ID);
        } catch (SQLException e) {
            SystemLogger.getInstance().setLogger(getClass(),"Unable to return last order ID");
            throw new DAOException(e.getMessage(),e);
        }
        return max;
    }

    public long getAllOrderId() throws DAOException {
        long all = -1;
        try (Connection connection = ConnectionUtil.getConnection()) {
            all = DAOUtil.getCount(connection,ALL_ORDERS);
        } catch (SQLException e) {
            SystemLogger.getInstance().setLogger(getClass(), "Unable to count all orders in base");
            throw new DAOException(e.getMessage(),e);
        }
        return all;
    }
}
