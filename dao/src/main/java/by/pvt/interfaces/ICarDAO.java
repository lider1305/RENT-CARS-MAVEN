package by.pvt.interfaces;

import by.pvt.exception.DAOException;
import by.pvt.entity.Car;

import java.util.List;


public interface ICarDAO extends IDAO<Car> {
    @Override
    void create(Car object) throws DAOException;

    @Override
    Car getId(long id) throws DAOException;

    @Override
    void update(Car object) throws DAOException;

    @Override
    void delete(long id) throws DAOException;

    @Override
    List getAll() throws DAOException;

    //метод отдает все существующие в базе бренды автомобилей
    List getAllBrands() throws DAOException;

    //метод отдает все авто по названию бренда
    List getCarByBrand(long id) throws DAOException;

    //метод отдает автомобили по типу коробки передая
    List getCarByTransmission(long id) throws DAOException;

    //метод отдает все типы коробок передач
    List getAllTransmission() throws DAOException;
}
