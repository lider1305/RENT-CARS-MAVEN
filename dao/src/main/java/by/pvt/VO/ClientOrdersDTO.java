package by.pvt.VO;


import java.util.Date;

public class ClientOrdersDTO {
    private  long orderId;
    private  String  brand;
    private String model;
    private Date startDate;
    private Date endDate;
    private  String message;
    private String status;
    private long orderAmount;

    public ClientOrdersDTO() {
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(long orderAmount) {
        this.orderAmount = orderAmount;
    }
}
