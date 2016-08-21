package by.pvt.manager;


import by.pvt.constans.ErrorMessages;
import by.pvt.entity.Car;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarManager {
    private static Logger logger = Logger.getLogger(CarManager.class);
    private static CarManager instance;

    private CarManager() {
    }

    public static synchronized CarManager getInstance() {
        if (instance == null) {
            instance = new CarManager();
        }
        return instance;
    }

    public Car getCar(ResultSet resultSet) {
        Car car = new Car();
        try {
            car.setBrand(resultSet.getString(1));
            car.setModel(resultSet.getString(2));
            car.setBodyType(resultSet.getString(3));
            car.setEngineType(resultSet.getString(4));
            car.setTransmissionType(resultSet.getString(5));
            car.setYearOfManufacture(resultSet.getInt(6));
            car.setStatus(resultSet.getString(7));
            car.setAmount(resultSet.getDouble(8));
            car.setCarId(resultSet.getLong(9));
        } catch (SQLException e) {
            logger.error(ErrorMessages.ERROR_IO + e);
        }
        return car;
    }
}
