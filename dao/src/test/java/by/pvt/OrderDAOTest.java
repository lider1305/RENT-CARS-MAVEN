package by.pvt;

import by.pvt.entity.Order;
import by.pvt.exception.DAOException;
import by.pvt.dao.impl.OrderDAO;
import by.pvt.util.DateFormatUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;


public class OrderDAOTest {
    private Order order;
    private OrderDAO orderDAO;

    @Before
    public void makeOrder() {
        orderDAO = OrderDAO.getInstance();
        order = new Order();
        int clientId = 1;
        int carId = 1;
        order.setCarId(carId);
        order.setClientID(clientId);
        order.setStartDate(DateFormatUtil.dateFormatterFromLongToDate(1465423200000L));
        order.setEndDate(DateFormatUtil.dateFormatterFromLongToDate(1465423200000L));
        order.setOrderStatus("ПРИНЯТ");
    }

    @Test
    public void testGetInstance() {
        OrderDAO order1 = OrderDAO.getInstance();
        OrderDAO order2 = OrderDAO.getInstance();
        Assert.assertEquals(order1, order2);
    }

    @Test
    public void create() throws DAOException {
        orderDAO.create(order);
        long orderID = orderDAO.getMaxOrderId();
        order.setOrderId(orderID);
        Order actual = orderDAO.getId(orderID);
        Assert.assertEquals(order, actual);
    }

    @Test
    public void getId() throws DAOException {
        Order orderBase = orderDAO.getId(1);
        Order order = new Order(1, 1, 2, DateFormatUtil.dateFormatterFromLongToDate(1465423200000L), DateFormatUtil.dateFormatterFromLongToDate(1465423200000L), "Необходимо детское кресло", "ПРИНЯТ");
        Assert.assertEquals(orderBase, order);


    }

    @Test
    public void update() throws DAOException {

    }

    @Test
    public void delete() throws DAOException{
        long orderID = orderDAO.getMaxOrderId();
        orderDAO.getId(orderID);
        orderDAO.delete(orderID);
        long orderIDAfter = orderDAO.getMaxOrderId();
        Assert.assertNotSame(orderID, orderIDAfter);
    }

    @Test
    public void getAll() throws DAOException {
        List list = orderDAO.getAll();
        long size = list.size();
        long AllOrders = orderDAO.getAllOrderId();
        Assert.assertEquals(size, AllOrders);
    }

    @Test
    public void getClientOrders() throws DAOException {
        List list = orderDAO.getClientOrders(order.getClientID());
        Assert.assertNotNull(list.size());
    }

}