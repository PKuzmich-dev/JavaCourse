package Unit6.Task3;

import java.util.HashSet;
import java.util.Set;

public class Client {
    private int id;
    private String name;
    private int age;
    private Set<Phone> phones;

    public Set<Phone> getPhones() {
        return phones;
    }

    public String getName() {
        return name;
    }

    public Client(int id, String name, int age, Set<Phone> phones) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.phones = phones;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", phones=" + phones +
                "}\n";
    }

    public int getAge() {
        return age;
    }

    public int getId() {
        return id;
    }
}
