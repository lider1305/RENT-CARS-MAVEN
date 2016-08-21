package by.pvt.service.impl;


import by.pvt.constans.ErrorMessages;
import by.pvt.dao.impl.CarDAO;
import by.pvt.dao.impl.ClientDAO;
import by.pvt.dao.impl.OrderDAO;
import by.pvt.db.ConnectionUtil;
import by.pvt.entity.Car;
import by.pvt.entity.Client;
import by.pvt.entity.Order;
import by.pvt.exception.DAOException;
import by.pvt.exception.ServiceException;
import by.pvt.service.IServices;
import by.pvt.service.impl.util.OrderManager;
import by.pvt.util.SystemLogger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrderService implements IServices {

    @Override
    public void create(Object o) throws ServiceException,SQLException{
        Order order = (Order) o;
        try (Connection connection = ConnectionUtil.getConnection()) {
            try {
                connection.setAutoCommit(false);
                OrderDAO.getInstance().create(order);
                connection.commit();

            } catch (DAOException e) {
                connection.rollback();
                SystemLogger.getInstance().setLogger(getClass(), ErrorMessages.TRANSACTION_NOT);
                throw new ServiceException(e.getMessage(),e);
            }
        } catch (SQLException  e) {
            SystemLogger.getInstance().setLogger(getClass(), ErrorMessages.ERROR_SQL);
            throw new ServiceException(e.getMessage(),e);
        }
    }

    @Override
    public Object getId(long id) throws ServiceException,SQLException{
        Order order = null;
        Order orderPage = null;
        Car car= null;
        Client client = null;

        try (Connection connection = ConnectionUtil.getConnection()) {
            try {
                connection.setAutoCommit(false);
                order = OrderDAO.getInstance().getId(id);
                car= CarDAO.getInstance().getId(order.getCarId());
                client = ClientDAO.getInstance().getId(order.clientID);
                orderPage= OrderManager.getInstance().getOrder(order,car,client);
                connection.commit();

            } catch (DAOException e) {
                connection.rollback();
                SystemLogger.getInstance().setLogger(getClass(), ErrorMessages.TRANSACTION_NOT);
                throw new ServiceException(e.getMessage(),e);
            }
        } catch (SQLException  e) {
            SystemLogger.getInstance().setLogger(getClass(), ErrorMessages.ERROR_SQL);
            throw new ServiceException(e.getMessage(),e);
        }
        return orderPage;
    }

    @Override
    public void update(Object o) {

    }

    @Override
    public void delete(long id) throws ServiceException,SQLException{
        try (Connection connection = ConnectionUtil.getConnection()) {
            try {
                connection.setAutoCommit(false);
                OrderDAO.getInstance().delete(id);
                connection.commit();

            } catch (DAOException e) {
                connection.rollback();
                SystemLogger.getInstance().setLogger(getClass(), ErrorMessages.TRANSACTION_NOT);
                throw new ServiceException(e.getMessage(),e);
            }
        } catch (SQLException  e) {
            SystemLogger.getInstance().setLogger(getClass(), ErrorMessages.ERROR_SQL);
            throw new ServiceException(e.getMessage(),e);
        }
    }

    @Override
    public List getAll() throws ServiceException,SQLException{
        List ordersList = null;

        try (Connection connection = ConnectionUtil.getConnection()) {
            try {
                connection.setAutoCommit(false);
                ordersList = OrderDAO.getInstance().getAll();
                connection.commit();

            } catch (DAOException e) {
                connection.rollback();
                SystemLogger.getInstance().setLogger(getClass(), ErrorMessages.TRANSACTION_NOT);
                throw new ServiceException(e.getMessage(),e);
            }
        } catch (SQLException  e) {
            SystemLogger.getInstance().setLogger(getClass(), ErrorMessages.ERROR_SQL);
            throw new ServiceException(e.getMessage(),e);
        }
        return ordersList;
    }
    public List getClientOrders(long id) throws ServiceException,SQLException{
        List orders = null;


        try (Connection connection = ConnectionUtil.getConnection()) {
            try {
                connection.setAutoCommit(false);
                orders = OrderDAO.getInstance().getClientOrders(id);


                connection.commit();

            } catch (DAOException e) {
                connection.rollback();
                SystemLogger.getInstance().setLogger(getClass(), ErrorMessages.TRANSACTION_NOT);
                throw new ServiceException(e.getMessage(),e);
            }
        } catch (SQLException  e) {
            SystemLogger.getInstance().setLogger(getClass(), ErrorMessages.ERROR_SQL);
            throw new ServiceException(e.getMessage(),e);
        }
        return orders;

    }
}
