package by.pvt.dao.impl;


import by.pvt.VO.BrandDTO;
import by.pvt.VO.TransmissionDTO;
import by.pvt.db.ConnectionUtil;
import by.pvt.entity.Car;
import by.pvt.exception.DAOException;
import by.pvt.interfaces.ICarDAO;
import by.pvt.manager.CarManager;
import by.pvt.util.DAOUtil;
import by.pvt.util.SystemLogger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс обработки запросов к базе данных для объекта авто
 */
public class CarDAO implements ICarDAO {
    private static CarDAO instance;
    String message;
    private final String GET_CAR_BY_ID = "SELECT  b.BRAND_NAME, MODEL, bod.BODY_NAME, e.ENGINE_NAME," +
            "t.TRANSMISSION_NAME, YEAR_OF_MANUFACTURE, s.STATUS_NAME,  AMOUNT\n" +
            "FROM CARS \n" +
            "JOIN BRANDS b ON  BRAND=b.ID\n" +
            "JOIN BODY_TYPE bod ON  BODY_TYPE= bod.BODY_TYPE_ID\n" +
            "JOIN ENGINE_TYPE e ON  ENGINE_TYPE= e.ENGINE_TYPE_ID\n" +
            "JOIN TRANSMISSION_TYPE t ON  TRANSMISSION_TYPE=t.TRANSMISSION_TYPE_ID\n" +
            "JOIN STATUS_OF_CAR s ON  STATUS=s.STATUS_OF_CAR_ID\n" +
            "WHERE CAR_ID =?;";
    private final String DELETE_CAR = "DELETE FROM CARS WHERE CAR_ID =?";
    private final String UPDATE_CAR = "UPDATE CARS SET MODEL=?, YEAR_OF_MANUFACTURE=? WHERE CAR_ID =?;";
    private final String GET_ALL_CARS = "SELECT b.BRAND_NAME, MODEL, bod.BODY_NAME, e.ENGINE_NAME," +
            "t.TRANSMISSION_NAME, YEAR_OF_MANUFACTURE, s.STATUS_NAME,  AMOUNT, CAR_ID\n" +
            "FROM CARS \n" +
            "JOIN BRANDS b ON  BRAND=b.ID\n" +
            "JOIN BODY_TYPE bod ON  BODY_TYPE= bod.BODY_TYPE_ID\n" +
            "JOIN ENGINE_TYPE e ON  ENGINE_TYPE= e.ENGINE_TYPE_ID\n" +
            "JOIN TRANSMISSION_TYPE t ON  TRANSMISSION_TYPE=t.TRANSMISSION_TYPE_ID\n" +
            "JOIN STATUS_OF_CAR s ON  STATUS=s.STATUS_OF_CAR_ID;";
    private final String CREATE_CAR = "INSERT INTO CARS (BRAND, MODEL, BODY_TYPE,ENGINE_TYPE,TRANSMISSION_TYPE," +
            "YEAR_OF_MANUFACTURE,STATUS,AMOUNT) VALUES(?,?,?,?,?,?,1,?);";
    private final String GET_CAR_FROM_PERIOD = "SELECT CAR FROM ORDERS WHERE  START_DATE >= ? AND END_DATE <=?;";
    private final String GET_CAR_BY_BRAND = "SELECT  b.BRAND_NAME, MODEL, bod.BODY_NAME, e.ENGINE_NAME,t.TRANSMISSION_NAME,\n" +
            " YEAR_OF_MANUFACTURE, s.STATUS_NAME,  AMOUNT, CAR_ID FROM CARS \n" +
            " JOIN BRANDS b ON  BRAND=b.ID\n" +
            " JOIN BODY_TYPE bod ON  BODY_TYPE= bod.BODY_TYPE_ID\n" +
            " JOIN ENGINE_TYPE e ON  ENGINE_TYPE= e.ENGINE_TYPE_ID\n" +
            " JOIN TRANSMISSION_TYPE t ON  TRANSMISSION_TYPE=t.TRANSMISSION_TYPE_ID\n" +
            " JOIN STATUS_OF_CAR s ON  STATUS=s.STATUS_OF_CAR_ID\n" +
            " WHERE b.ID =?;";
    private final String ALL_CARS = "SELECT COUNT(*) FROM cars;";
    private final String GET_CAR_BY_AMOUNT = "SELECT  b.BRAND_NAME, MODEL, bod.BODY_NAME, e.ENGINE_NAME,t.TRANSMISSION_NAME,\n" +
            " YEAR_OF_MANUFACTURE, s.STATUS_NAME,  AMOUNT FROM CARS \n" +
            " JOIN BRANDS b ON  BRAND=b.ID\n" +
            " JOIN BODY_TYPE bod ON  BODY_TYPE= bod.BODY_TYPE_ID\n" +
            " JOIN ENGINE_TYPE e ON  ENGINE_TYPE= e.ENGINE_TYPE_ID\n" +
            " JOIN TRANSMISSION_TYPE t ON  TRANSMISSION_TYPE=t.TRANSMISSION_TYPE_ID\n" +
            " JOIN STATUS_OF_CAR s ON  STATUS=s.STATUS_OF_CAR_ID\n" +
            " WHERE AMOUNT < ?;";
    private final String GET_CAR_BY_TRANSMISSION = "SELECT  b.BRAND_NAME, MODEL, bod.BODY_NAME, e.ENGINE_NAME,t.TRANSMISSION_NAME,\n" +
            " YEAR_OF_MANUFACTURE, s.STATUS_NAME,AMOUNT,BRAND,CAR_ID FROM CARS \n" +
            " JOIN BRANDS b ON  BRAND=b.ID\n" +
            " JOIN BODY_TYPE bod ON  BODY_TYPE= bod.BODY_TYPE_ID\n" +
            " JOIN ENGINE_TYPE e ON  ENGINE_TYPE= e.ENGINE_TYPE_ID\n" +
            " JOIN TRANSMISSION_TYPE t ON  TRANSMISSION_TYPE=t.TRANSMISSION_TYPE_ID\n" +
            " JOIN STATUS_OF_CAR s ON  STATUS=s.STATUS_OF_CAR_ID\n" +
            " WHERE  t.TRANSMISSION_TYPE_ID =?;";
    private final String GET_ALL_BRAND = "SELECT ID, BRAND_NAME FROM BRANDS;";
    private final String GET_ALL_TRANSMISSION = "SELECT TRANSMISSION_TYPE_ID, TRANSMISSION_NAME FROM TRANSMISSION_TYPE;";
    private final String MAX_CAR_ID = "SELECT MAX(CAR_ID) FROM cars;";

    private CarDAO() {
    }

    public static synchronized CarDAO getInstance() {
        if (instance == null) {
            instance = new CarDAO();
        }
        return instance;
    }

    @Override
    public void create(Car car) throws DAOException {

        try (Connection connection = ConnectionUtil.getConnection()) {
            try (PreparedStatement preparedStatement1 = connection.prepareStatement(CREATE_CAR)) {

                preparedStatement1.setInt(1, car.getBrandId());
                preparedStatement1.setString(2, car.getModel());
                preparedStatement1.setInt(3, car.getBodyTypeId());
                preparedStatement1.setInt(4, car.getEngineTypeId());
                preparedStatement1.setInt(5, car.getTransmissionTypeId());
                preparedStatement1.setInt(6, car.getYearOfManufacture());
                preparedStatement1.setDouble(7, car.getAmount());
                preparedStatement1.executeUpdate();
            }
        } catch (SQLException e) {
            message = "Unable to create new car";
            SystemLogger.getInstance().setLogger(getClass(), message);
            throw new DAOException(message, e);
        }
    }

    @Override
    public Car getId(long id) throws DAOException {
        Car car = new Car();
        ResultSet resultSet = null;

        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_CAR_BY_ID)) {
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            car.setBrand(resultSet.getString(1));
            car.setModel(resultSet.getString(2));
            car.setBodyType(resultSet.getString(3));
            car.setEngineType(resultSet.getString(4));
            car.setTransmissionType(resultSet.getString(5));
            car.setYearOfManufacture(resultSet.getInt(6));
            car.setStatus(resultSet.getString(7));
            car.setAmount(resultSet.getDouble(8));
            car.setCarId(id);
        } catch (SQLException e) {
            message = "Unable to return the car!";
            SystemLogger.getInstance().setLogger(getClass(), message);
            throw new DAOException(message, e);
        } finally {
            DAOUtil.closeResources(resultSet, null);
        }
        return car;
    }

    @Override
    public void update(Car car) throws DAOException {

        try (Connection connection = ConnectionUtil.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CAR)) {

                preparedStatement.setString(1, car.getModel());
                preparedStatement.setInt(2, car.getYearOfManufacture());
                preparedStatement.setLong(3,car.getCarId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            message = "Unable to update car information";
            SystemLogger.getInstance().setLogger(getClass(), message);
            throw new DAOException(message, e);
        }
    }

    @Override
    public void delete(long id) throws DAOException {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement1 = connection.prepareStatement(DELETE_CAR)) {

            preparedStatement1.setLong(1, id);
            preparedStatement1.executeUpdate();
        } catch (SQLException e) {
            message = "Unable to delete car from base!";
            SystemLogger.getInstance().setLogger(getClass(), message);
            throw new DAOException(message, e);
        }
    }

    @Override
    public List getAll() throws DAOException {
        ArrayList<Car> allCars = new ArrayList<>();
        ResultSet resultSet = null;

        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_CARS)) {
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Car car = CarManager.getInstance().getCar(resultSet);
                allCars.add(car);
            }

        } catch (SQLException e) {
            message = "Unable to get list of cars!";
            SystemLogger.getInstance().setLogger(getClass(), message);
            throw new DAOException(message,e);
        } finally {
            DAOUtil.closeResources(resultSet, null);
        }
        return allCars;
    }
    public List getAllRentCarForDate(long start, long end) throws DAOException {
        List<Integer> rentCars = new ArrayList<>();
        ResultSet resultSet = null;

        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_CAR_FROM_PERIOD)) {
            preparedStatement.setLong(1, start);
            preparedStatement.setLong(2, end);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                rentCars.add(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            message = "Unable to get list of cars!";
            SystemLogger.getInstance().setLogger(getClass(), message);
            throw new DAOException(message,e);
        } finally {
            DAOUtil.closeResources(resultSet, null);
        }
        return rentCars;
    }

    @Override
    public List getAllBrands() throws DAOException {
        List<BrandDTO> brandDTO = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;

        try (Connection connection = ConnectionUtil.getConnection()) {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(GET_ALL_BRAND);
            while (resultSet.next()) {
                BrandDTO brand = new BrandDTO();
                brand.setBrandId(resultSet.getLong("ID"));
                brand.setBrand(resultSet.getString("BRAND_NAME"));
                brandDTO.add(brand);
            }
        } catch (SQLException e) {
            message = "Unable to return list of brands!";
            SystemLogger.getInstance().setLogger(getClass(), message);
            throw  new DAOException(message,e);
        } finally {
            DAOUtil.closeResources(resultSet, statement);
        }
        return brandDTO;
    }

    @Override
    public List getCarByBrand(long id) throws DAOException {
        List<Car> list = new ArrayList<>();
        ResultSet resultSet = null;

        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_CAR_BY_BRAND)) {
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Car car = new Car();
                car.setBrand(resultSet.getString(1));
                car.setModel(resultSet.getString(2));
                car.setBodyType(resultSet.getString(3));
                car.setEngineType(resultSet.getString(4));
                car.setTransmissionType(resultSet.getString(5));
                car.setYearOfManufacture(resultSet.getInt(6));
                car.setStatus(resultSet.getString(7));
                car.setAmount(resultSet.getDouble(8));
                car.setCarId(resultSet.getInt(9));
                list.add(car);
            }
        } catch (SQLException e) {
            message = "Unable to return car by brand!";
            SystemLogger.getInstance().setLogger(getClass(),message);
            throw new DAOException(message,e);
        } finally {
            DAOUtil.closeResources(resultSet, null);
        }
        return list;
    }

    @Override
    public List getCarByTransmission(long id) throws DAOException {
        List<Car> list = new ArrayList<>();
        ResultSet resultSet = null;

        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_CAR_BY_TRANSMISSION)) {
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Car car = new Car();
                car.setBrand(resultSet.getString(1));
                car.setModel(resultSet.getString(2));
                car.setBodyType(resultSet.getString(3));
                car.setEngineType(resultSet.getString(4));
                car.setTransmissionType(resultSet.getString(5));
                car.setYearOfManufacture(resultSet.getInt(6));
                car.setStatus(resultSet.getString(7));
                car.setAmount(resultSet.getDouble(8));
                car.setBrandId((int) resultSet.getLong(9));
                car.setCarId(resultSet.getLong(10));
                list.add(car);
            }
        } catch (SQLException e) {
            message = "Unable to return car by transmission!";
            SystemLogger.getInstance().setLogger(getClass(),message);
            throw new DAOException(message,e);
        } finally {
            DAOUtil.closeResources(resultSet, null);
        }
        return list;
    }

    @Override
    public List getAllTransmission() throws DAOException {
        List<TransmissionDTO> transDTO = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;

        try (Connection connection = ConnectionUtil.getConnection()) {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(GET_ALL_TRANSMISSION);

            while (resultSet.next()) {
                TransmissionDTO trans = new TransmissionDTO();
                trans.setTransId(resultSet.getLong(1));
                trans.setTransName(resultSet.getString(2));
                transDTO.add(trans);
            }
        } catch (SQLException e) {
            message = "Unable to return transmission types!";
            SystemLogger.getInstance().setLogger(getClass(),message);
            throw new DAOException(message,e);
        } finally {
            DAOUtil.closeResources(resultSet, statement);
        }
        return transDTO;
    }

    public long getMaxCarId() throws DAOException {
        long max = -1;
        try (Connection connection = ConnectionUtil.getConnection()) {
            max =DAOUtil.getCount(connection,MAX_CAR_ID);
        } catch (SQLException e) {
            SystemLogger.getInstance().setLogger(getClass(),"Unable to return last car ID");
            throw new DAOException(e.getMessage(),e);
        }
        return max;
    }

    public long getAllCarsId() throws DAOException {
        long all = -1;
        try (Connection connection = ConnectionUtil.getConnection()) {
            all = DAOUtil.getCount(connection,ALL_CARS);
        } catch (SQLException e) {
            SystemLogger.getInstance().setLogger(getClass(), "Unable to count all cars in base");
            throw new DAOException(e.getMessage(),e);
        }
        return all;
    }
}
