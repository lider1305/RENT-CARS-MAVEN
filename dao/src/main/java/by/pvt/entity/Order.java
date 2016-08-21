package by.pvt.entity;

import java.util.Date;

/**
 * Класс для формирования объекта заказа
 */
public class Order {
    private long orderId;
    public int carId;
    public int clientID;
    public Car car;
    public Client client;
    private Date startDate;
    private Date endDate;
    private String message;
    private String orderStatus;

    public Order() {
    }

    public Order(long orderId, int carId, int clientID, Date startDate, Date endDate, String message, String orderStatus) {
        this.orderId = orderId;
        this.carId = carId;
        this.clientID = clientID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.message = message;
        this.orderStatus = orderStatus;
    }

    public Order(long orderId, int carId, int clientID, Car car, Client client, Date startDate, Date endDate, String message, String orderStatus) {
        this.orderId = orderId;
        this.carId = carId;
        this.clientID = clientID;
        this.car = car;
        this.client = client;
        this.startDate = startDate;
        this.endDate = endDate;
        this.message = message;
        this.orderStatus = orderStatus;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;

        Order order = (Order) o;

        if (orderId != order.orderId) return false;
        if (carId != order.carId) return false;
        if (clientID != order.clientID) return false;
        if (car != null ? !car.equals(order.car) : order.car != null) return false;
        if (client != null ? !client.equals(order.client) : order.client != null) return false;
        if (startDate != null ? !startDate.equals(order.startDate) : order.startDate != null) return false;
        if (endDate != null ? !endDate.equals(order.endDate) : order.endDate != null) return false;
        if (message != null ? !message.equals(order.message) : order.message != null) return false;
        return orderStatus != null ? orderStatus.equals(order.orderStatus) : order.orderStatus == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (orderId ^ (orderId >>> 32));
        result = 31 * result + carId;
        result = 31 * result + clientID;
        result = 31 * result + (car != null ? car.hashCode() : 0);
        result = 31 * result + (client != null ? client.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (orderStatus != null ? orderStatus.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", carId=" + carId +
                ", clientID=" + clientID +
                ", car=" + car +
                ", client=" + client +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", message='" + message + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                '}';
    }
}
