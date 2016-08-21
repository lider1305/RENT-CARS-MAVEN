package by.pvt.VO;

/**
 * Класс формирует объект автомобиля для показа на стороне клиента
 */
public class CarDTO {
    private int brand;
    private String model;
    private int bodyType;
    private int engineType;
    private int transmissionType;
    private int yearOfManufacture;
    private double amount;
    private int status;

    public int getBrand() {
        return brand;
    }

    public void setBrand(int brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getBodyType() {
        return bodyType;
    }

    public void setBodyType(int bodyType) {
        this.bodyType = bodyType;
    }

    public int getEngineType() {
        return engineType;
    }

    public void setEngineType(int engineType) {
        this.engineType = engineType;
    }

    public int getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(int transmissionType) {
        this.transmissionType = transmissionType;
    }

    public int getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(int yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public CarDTO() {
    }


}