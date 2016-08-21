package by.pvt.interfaces;

import by.pvt.exception.DAOException;

import java.util.List;

interface IDAO<T> {

    //Создает новую запись и соответствующий ей объект
     void create(T t) throws DAOException;

    // Возвращает объект соответствующий записи с первичным ключом id или null
     T getId(long id) throws DAOException;

    // Сохраняет состояние объекта group в базе данных
     void update(T t) throws DAOException;

    // Удаляет запись об объекте из базы данных
     void delete(long id) throws DAOException;

    // Возвращает список объектов соответствующих всем записям в таблице
     List<T> getAll() throws DAOException;
}
