package by.pvt.VO;

/**
 * Класс формирует объект бренда  автомобиля
 */
public class BrandDTO {
    private long brandId;
    private String brand;

    public BrandDTO() {
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public long getBrandId() {
        return brandId;
    }

    public void setBrandId(long brandId) {
        this.brandId = brandId;
    }
}
