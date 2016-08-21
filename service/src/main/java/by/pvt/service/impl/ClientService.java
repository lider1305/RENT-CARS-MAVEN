package by.pvt.service.impl;

import by.pvt.constans.ErrorMessages;
import by.pvt.dao.impl.ClientDAO;
import by.pvt.db.ConnectionUtil;
import by.pvt.entity.Client;
import by.pvt.exception.DAOException;
import by.pvt.exception.ServiceException;
import by.pvt.service.IServices;
import by.pvt.util.SystemLogger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public class ClientService implements IServices {

    @Override
    public void create(Object o) throws ServiceException,SQLException{
        Client client = (Client) o;
        try (Connection connection = ConnectionUtil.getConnection()) {
            try {
                connection.setAutoCommit(false);
                ClientDAO.getInstance().createPassport(client);
                ClientDAO.getInstance().getPassport(client);
                ClientDAO.getInstance().create(client);
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
        Client client = null;

        try (Connection connection = ConnectionUtil.getConnection()) {
            try {
                connection.setAutoCommit(false);
                client = ClientDAO.getInstance().getId(id);
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
        return client;
    }

    @Override
    public void update(Object o) throws ServiceException,SQLException{
        Client client = (Client) o;
        try (Connection connection = ConnectionUtil.getConnection()) {
            try {
                connection.setAutoCommit(false);
                ClientDAO.getInstance().update(client);
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
    public void delete(long id) throws ServiceException,SQLException{
        try (Connection connection = ConnectionUtil.getConnection()) {
            try {
                connection.setAutoCommit(false);
                ClientDAO.getInstance().delete(id);
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
        List clientList = null;
        try (Connection connection = ConnectionUtil.getConnection()) {
            try {
                connection.setAutoCommit(false);
                clientList = ClientDAO.getInstance().getAll();
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
        return clientList;
    }

    public Client enterToSystem(String email, String password) throws ServiceException,SQLException{
        Client client = null;

        try (Connection connection = ConnectionUtil.getConnection()) {
            try {
                connection.setAutoCommit(false);
                client = ClientDAO.getInstance().enterToSystem(email, password);
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
        return client;
    }

    public String forgotPassword(String email) throws ServiceException,SQLException{
        String password = "";

        try (Connection connection = ConnectionUtil.getConnection()) {
            try {
                connection.setAutoCommit(false);
                password = ClientDAO.getInstance().forgotPassword(email);
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
        return password;
    }
}
