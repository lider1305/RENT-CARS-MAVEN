package by.pvt.VO;

import java.util.Date;

/**
 * Класс формирует объект заказа для отображения на стороне клиента
 */
public class OrderDTO {
    private long orderId;
    public int carId;
    public int clientID;
    private Date startDate;
    private Date endDate;
    private String message;
    private double amount;
    private String orderStatus;

    public OrderDTO() {
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "orderId=" + orderId +
                ", carId=" + carId +
                ", clientID=" + clientID +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", message='" + message + '\'' +
                ", amount=" + amount +
                ", orderStatus='" + orderStatus + '\'' +
                '}';
    }
}
