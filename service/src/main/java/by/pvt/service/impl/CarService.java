package by.pvt.service.impl;

import by.pvt.service.IServices;
import by.pvt.constans.ErrorMessages;
import by.pvt.dao.impl.CarDAO;
import by.pvt.db.ConnectionUtil;
import by.pvt.entity.Car;
import by.pvt.exception.DAOException;
import by.pvt.exception.ServiceException;
import by.pvt.util.SystemLogger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public class CarService implements IServices {

    @Override
    public void create(Object o) throws ServiceException,SQLException {
        Car car = (Car) o;
        try (Connection connection = ConnectionUtil.getConnection()) {
            try {
            connection.setAutoCommit(false);
            CarDAO.getInstance().create(car);
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
    public Car getId(long id) throws ServiceException, SQLException{
        Car car = null;

        try (Connection connection = ConnectionUtil.getConnection()) {
            try {
                connection.setAutoCommit(false);
                car = CarDAO.getInstance().getId(id);
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
        return car;
    }

    @Override
    public void update(Object o) {

    }

    @Override
    public void delete(long id) throws ServiceException,SQLException {
        try (Connection connection = ConnectionUtil.getConnection()) {
            try {
                connection.setAutoCommit(false);
                CarDAO.getInstance().delete(id);
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
    public List getAll() throws ServiceException,SQLException {
        List carList = null;
        try (Connection connection = ConnectionUtil.getConnection()) {
            try {
                connection.setAutoCommit(false);
                carList = CarDAO.getInstance().getAll();
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
        return carList;
    }
    public List getAllRentCarForDate(long start, long end) throws ServiceException,SQLException {
        List carList = null;
        try (Connection connection = ConnectionUtil.getConnection()) {
            try {
                connection.setAutoCommit(false);
                carList = CarDAO.getInstance().getAllRentCarForDate(start,end);
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
        return carList;
    }

    public List getAllBrands() throws ServiceException,SQLException {
        List brandList = null;
        try (Connection connection = ConnectionUtil.getConnection()) {
            try {
                connection.setAutoCommit(false);
                brandList = CarDAO.getInstance().getAllBrands();
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
        return brandList;
    }

    public List getAllTransmissions() throws ServiceException,SQLException {
        List transList = null;
        try (Connection connection = ConnectionUtil.getConnection()) {
            try {
                connection.setAutoCommit(false);
                transList = CarDAO.getInstance().getAllTransmission();
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
        return transList;
    }

    public List getCarByBrand(long id) throws ServiceException,SQLException {
        List cars = null;
        try (Connection connection = ConnectionUtil.getConnection()) {
            try {
                connection.setAutoCommit(false);
                cars = CarDAO.getInstance().getCarByBrand(id);
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
        return cars;
    }

    public List getCarByTransmission(long id) throws ServiceException,SQLException {
        List cars = null;
        try (Connection connection = ConnectionUtil.getConnection()) {
            try {
                connection.setAutoCommit(false);
                cars = CarDAO.getInstance().getCarByTransmission(id);
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
        return cars;
    }
}

