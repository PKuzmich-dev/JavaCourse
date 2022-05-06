package Unit7.Task2.Entity;

public class Brand {
    private Integer id;
    private String brand;

    public void setId(int id) {
        this.id = id;
    }

    public Brand(String brand) {
        this.brand = brand;
    }

    public Brand(int id, String brand) {
        this.id = id;
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Brand{" +
            "id=" + id +
            ", brand='" + brand + '\'' +
            '}';
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
