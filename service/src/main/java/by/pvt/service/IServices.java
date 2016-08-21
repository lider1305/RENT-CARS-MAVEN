package by.pvt.service;


import by.pvt.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;

public interface IServices<T> {
    //Создает новую запись и соответствующий ей объект
    public void create(T t) throws ServiceException, SQLException;

    // Возвращает объект соответствующий записи с первичным ключом id или null
    public T getId(long id) throws ServiceException, SQLException;

    // Сохраняет состояние объекта group в базе данных
    public void update(T t) throws ServiceException, SQLException;

    // Удаляет запись об объекте из базы данных
    public void delete(long id) throws ServiceException, SQLException;

    // Возвращает список объектов соответствующих всем записям в таблице
    public List<T> getAll() throws ServiceException, SQLException;


}
