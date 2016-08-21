package by.pvt.interfaces;

import by.pvt.exception.DAOException;
import by.pvt.entity.Order;

import java.util.List;

public interface IOrderDAO extends IDAO<Order> {
    @Override
    void create(Order o) throws DAOException;

    @Override
    Order getId(long id) throws DAOException;

    @Override
    void update(Order o);

    @Override
    void delete(long id) throws DAOException;

    @Override
    List getAll() throws DAOException;

    //метод отдает все заказы клиента
    List getClientOrders(long id) throws DAOException;
}
