package by.pvt;

import by.pvt.dao.impl.CarDAO;
import by.pvt.entity.Car;
import by.pvt.exception.ServiceException;
import by.pvt.service.impl.CarService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;


public class CarServiceTest {
    private Car car;
    private CarService carService;
    private CarDAO carDAO;

    @Before
    public void setUp() throws ServiceException, SQLException {
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
    public void create() throws Exception {
        carDAO = CarDAO.getInstance();
        carService = new CarService();
        carService.create(car);
        long carId = carDAO.getMaxCarId();
        car.setCarId(carId);
        Car actual = carService.getId(carId);
        Assert.assertEquals(car, actual);
        carService.delete(carId);
    }

    @Test
    public void getId() throws Exception {
        carDAO = CarDAO.getInstance();
        carService = new CarService();
        carService.create(car);
        long carId = carDAO.getMaxCarId();
        car.setCarId(carId);
        Car actual = carService.getId(carId);
        Assert.assertEquals(car, actual);
        carService.delete(carId);

    }

    @Test
    public void update() throws Exception {
        carDAO = CarDAO.getInstance();
        carService = new CarService();
        carService.create(car);
        long carId = carDAO.getMaxCarId();
        car.setCarId(carId);
        Car actual = carService.getId(carId);
        actual.setModel("A8");
        actual.setYearOfManufacture(2000);
        Assert.assertNotSame(car, actual);
        carService.delete(carId);
    }

    @Test
    public void delete() throws Exception {
        carDAO = CarDAO.getInstance();
        carService = new CarService();
        carService.create(car);
        long carId = carDAO.getMaxCarId();
        carService.getId(carId);
        carService.delete(carId);
        long actual = carDAO.getMaxCarId();
        Assert.assertNotSame(carId, actual);
    }

    @Test
    public void getAll() throws Exception {
        carService = new CarService();
        carDAO = CarDAO.getInstance();
        List list =carService.getAll();
        long actual = carDAO.getAllCarsId();
        Assert.assertEquals(list.size(),actual);
    }

    @Test
    public void getAllRentCarForDate() throws Exception {

    }

    @Test
    public void getAllBrands() throws Exception {

    }

    @Test
    public void getAllTransmissions() throws Exception {

    }

    @Test
    public void getCarByBrand() throws Exception {

    }

    @Test
    public void getCarByTransmission() throws Exception {

    }

}