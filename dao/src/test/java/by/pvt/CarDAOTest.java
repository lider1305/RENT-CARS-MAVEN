package by.pvt;

import by.pvt.db.ConnectionUtil;
import by.pvt.entity.Car;
import by.pvt.exception.DAOException;
import by.pvt.dao.impl.CarDAO;
import by.pvt.util.DAOUtil;
import by.pvt.util.SystemLogger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public class CarDAOTest {
    private Car car;
    private CarDAO carDAO;

    @Before
    public void setUp() throws Exception {
        carDAO = CarDAO.getInstance();
        car = new Car();
        car.setBrandId(2);
        car.setBrand("AUDI");
        car.setModel("A6");
        car.setBodyTypeId(2);
        car.setBodyType("УНИВЕРСАЛ");
        car.setEngineTypeId(2);
        car.setEngineType("ДИЗЕЛЬ");
        car.setTransmissionTypeId(1);
        car.setTransmissionType("МЕХАНИКА");
        car.setYearOfManufacture(2014);
        car.setAmount(30);
        car.setStatus("СВОБОДНО");
    }

    @Test
    public void getInstance() throws DAOException {
        CarDAO car1 = CarDAO.getInstance();
        CarDAO car2 = CarDAO.getInstance();
        Assert.assertEquals(car1, car2);
    }

    @Test
    public void testCreate() throws DAOException {
        carDAO.create(car);
        long carId = carDAO.getMaxCarId();
        car.setCarId(carId);
        Car actual = carDAO.getId(carId);
        Assert.assertEquals(car, actual);
        carDAO.delete(carId);

    }

    @Test
    public void testGetId() throws DAOException {
        carDAO.create(car);
        long carId = carDAO.getMaxCarId();
        Car car1 = carDAO.getId(carId);
        car.setCarId(carId);
        Assert.assertEquals(car1, car);
        carDAO.delete(carId);
    }

    @Test
    public void testUpdate() throws DAOException {
        carDAO.create(car);
        long carId = carDAO.getMaxCarId();
        Car car1 = carDAO.getId(carId);
        car1.setYearOfManufacture(car1.getYearOfManufacture() - 10);
        car1.setModel("A8");
        carDAO.update(car1);
        Assert.assertNotSame(car1, car);
        carDAO.delete(carId);


    }

    @Test
    public void testDelete() throws DAOException {
        carDAO.create(car);
        long carId = carDAO.getMaxCarId();
        carDAO.getId(carId);
        carDAO.delete(carId);
        long carIdAfter = carDAO.getMaxCarId();
        Assert.assertNotSame(carId, carIdAfter);


    }

    @Test
    public void testGetAll() throws DAOException {
        List list = carDAO.getAll();
        long size = list.size();
        long AllCars = carDAO.getAllCarsId();
        Assert.assertEquals(size, AllCars);
    }

    @Test
    public void getAllBrands() throws DAOException {
        List list = carDAO.getAllBrands();
        long actualSize = getAllId("SELECT COUNT(*) FROM BRANDS;");
        Assert.assertEquals(list.size(), actualSize);
    }

    @Test

    public void getCarByBrand() throws DAOException {
        List list = carDAO.getCarByBrand(1);
        long actual = getAllId("SELECT COUNT(*) FROM cars WHERE BRAND =1");
        Assert.assertEquals(list.size(), actual);
    }

    @Test
    public void getCarByTransmission() throws DAOException {
        List list = carDAO.getCarByTransmission(1);
        long actual = getAllId("SELECT COUNT(*) FROM cars WHERE TRANSMISSION_TYPE =1");
        Assert.assertEquals(list.size(), actual);
    }

    @Test
    public void getAllTransmission() throws DAOException {
        List list = carDAO.getAllTransmission();
        long actualSize = getAllId("SELECT COUNT(*) FROM TRANSMISSION_TYPE;");
        Assert.assertEquals(list.size(), actualSize);
    }

    @Test
    public void getAllRentCarForDate() throws DAOException {
        long start = 1470085200000l;
        long end = 1470171600000l;
        List list = carDAO.getAllRentCarForDate(start,end);
        long actual = getAllId("SELECT COUNT(*) FROM ORDERS WHERE  START_DATE >= 1470085200000 AND END_DATE <=1470171600000;");
        Assert.assertEquals(list.size(), actual);
    }

    public long getAllId(String sql) throws DAOException {
        long all = -1;
        try (Connection connection = ConnectionUtil.getConnection()) {
            all = DAOUtil.getCount(connection, sql);
        } catch (SQLException e) {
            SystemLogger.getInstance().setLogger(getClass(), "Unable to count all id in base");
            throw new DAOException(e.getMessage(), e);
        }
        return all;
    }
}