package by.pvt.service.impl.util;

import by.pvt.entity.Car;
import by.pvt.entity.Client;
import by.pvt.entity.Order;
import by.pvt.util.DateFormatUtil;

import java.util.Date;

public class OrderManager {
    private static OrderManager instance;

    private OrderManager() {
    }

    public static synchronized OrderManager getInstance() {
        if (instance == null) {
            instance = new OrderManager();
        }
        return instance;
    }

    public Order getOrder(Order orderDTO, Car car, Client client) {
        Order order = new Order(1, 1, 2, (Date) DateFormatUtil.dateFormatterFromLongToDate(1465423200000L), (Date) DateFormatUtil.dateFormatterFromLongToDate(1465423200000L), "Необходимо детское кресло", "ПРИНЯТ");
        order.setOrderId(orderDTO.getOrderId());
        order.setCar(car);
        order.setClient(client);

        order.setStartDate(orderDTO.getStartDate());
        order.setEndDate(orderDTO.getEndDate());
        order.setMessage(orderDTO.getMessage());
        order.setOrderStatus(orderDTO.getOrderStatus());
        return order;
    }
}
