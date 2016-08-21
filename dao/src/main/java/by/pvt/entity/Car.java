package by.pvt.entity;

/**
 * класс для формирования объекта автомобиля
 */
public class Car {
    private long carId;
    private String brand;
    private int brandId;
    private String model;
    private int engineTypeId;
    private int transmissionTypeId;
    private String bodyType;
    private int bodyTypeId;
    private String engineType;
    private String transmissionType;
    private int yearOfManufacture;
    private double amount;
    private String status;

    public Car() {
    }

    public Car(long carId, String brand, String model, String bodyType, String engineType, String transmissionType,
               int yearOfManufacture, double amount, String status) {
        this.carId = carId;
        this.brand = brand;
        this.model = model;
        this.bodyType = bodyType;
        this.engineType = engineType;
        this.transmissionType = transmissionType;
        this.yearOfManufacture = yearOfManufacture;
        this.amount = amount;
        this.status = status;
    }

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
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

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public String getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(String transmissionType) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public int getEngineTypeId() {
        return engineTypeId;
    }

    public void setEngineTypeId(int engineTypeId) {
        this.engineTypeId = engineTypeId;
    }

    public int getTransmissionTypeId() {
        return transmissionTypeId;
    }

    public void setTransmissionTypeId(int transmissionTypeId) {
        this.transmissionTypeId = transmissionTypeId;
    }

    public int getBodyTypeId() {
        return bodyTypeId;
    }

    public void setBodyTypeId(int bodyTypeId) {
        this.bodyTypeId = bodyTypeId;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " id " + carId + " Brand "
                + brand + " Model " + model + " Body type " + bodyType
                + " Engine type " + engineType + " Transmission " + transmissionType
                + " Year " + yearOfManufacture + " Amount per day " + amount
                + " Status " + status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;

        Car car = (Car) o;

        if (carId != car.carId) return false;
        if (yearOfManufacture != car.yearOfManufacture) return false;
        if (Double.compare(car.amount, amount) != 0) return false;
        if (brand != null ? !brand.equals(car.brand) : car.brand != null) return false;
        if (model != null ? !model.equals(car.model) : car.model != null) return false;
        if (bodyType != null ? !bodyType.equals(car.bodyType) : car.bodyType != null) return false;
        if (engineType != null ? !engineType.equals(car.engineType) : car.engineType != null) return false;
        if (transmissionType != null ? !transmissionType.equals(car.transmissionType) : car.transmissionType != null)
            return false;
        return status != null ? status.equals(car.status) : car.status == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (carId ^ (carId >>> 32));
        result = 31 * result + (brand != null ? brand.hashCode() : 0);
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (bodyType != null ? bodyType.hashCode() : 0);
        result = 31 * result + (engineType != null ? engineType.hashCode() : 0);
        result = 31 * result + (transmissionType != null ? transmissionType.hashCode() : 0);
        result = 31 * result + yearOfManufacture;
        temp = Double.doubleToLongBits(amount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
