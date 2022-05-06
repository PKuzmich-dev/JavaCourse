package Unit7.Task2.Entity;

import java.util.Objects;

public class Car {
    private Integer id;
    private String num;
    private String brand;
    private String model;

    public String getNum() {
        return num;
    }

    public Integer getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public Car(String num, String brand, String model) {
        this.num = num;
        this.brand = brand;
        this.model = model;
    }

    public Car(int id, String num, String brand, String model) {
        this.id = id;
        this.num = num;
        this.brand = brand;
        this.model = model;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Car{" +
            "id=" + id +
            ", num='" + num + '\'' +
            ", brand='" + brand + '\'' +
            ", model='" + model + '\'' +
            '}';
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return id == car.id;
    }
}
