package Unit7.Task2.Entity;

import java.util.List;

public class Contact {
    private Integer id;
    private String lastName;
    private String firstName;
    private String middleName;
    private String city;
    private List<Car> listCars;
    private int version;

    public Contact(int id, String lastName, String firstName, String middleName, String city,
                   int version, List<Car> listCars) {
        this(lastName, firstName, middleName, city, version, listCars);
        this.id = id;
    }

    public Contact(String lastName, String firstName, String middleName, String city, int version, List<Car> listCars) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.city = city;
        this.listCars = listCars;
        this.version = version;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getCity() {
        return city;
    }

    public Integer getId() {
        return id;
    }

    public String getFIO(){
        return lastName + " " + firstName + ((middleName == null) ? "" : " " + middleName);
    }

    public List<Car> getListCars() {
        return listCars;
    }

    public int getVersion() {
        return version;
    }

    @Override
    public String toString() {
        return "Contact{" +
            "id=" + id +
            ", lastName='" + lastName + '\'' +
            ", firstName='" + firstName + '\'' +
            ", middleName='" + middleName + '\'' +
            ", city='" + city + '\'' +
            ", listCars=" + listCars +
            '}';
    }
}
